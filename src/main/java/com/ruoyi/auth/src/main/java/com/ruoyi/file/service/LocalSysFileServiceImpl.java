package com.ruoyi.file.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.file.controller.SysFileController;
import com.ruoyi.file.utils.PDFUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.file.utils.FileUploadUtils;

import com.ruoyi.common.core.utils.file.FileDownloadUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地文件存储
 *
 * @author ruoyi
 */
@Primary   //默认使用本地存储
@Service
@Configuration
public class LocalSysFileServiceImpl implements ISysFileService
{
    private static final Logger log = LoggerFactory.getLogger(LocalSysFileServiceImpl.class);
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    public String localFilePath;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        String name = FileUploadUtils.upload(localFilePath, file);
        String url = localFilePrefix + name;
        return url;
    }

    @Override
    public void downloadFile(HttpServletResponse response, String url) {
        if(url == null || url.isEmpty()){
            System.out.println("url为空");
        }else {
            System.out.println("初始url========="+url);
            url = localFilePath + url;
            int lastSlashIndex = url.lastIndexOf("/");
            String fileName = url.substring(lastSlashIndex + 1);
            System.out.println("file的url========="+url);
            System.out.println("file的fileName========="+fileName);

            try {
                FileDownloadUtils.downloadFile(fileName,url,response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @Override
    public void downloadFilePdf(HttpServletResponse response, String url) {
        if(url == null || url.isEmpty()){
            log.info("url为空");
        }else {
            log.info("初始Pdfurl========="+url);
            url = localFilePath + url;
            // 确保文件扩展名是.doc
            String fileExtension = FilenameUtils.getExtension(url);
            if (!"doc".equalsIgnoreCase(fileExtension) && !"docx".equalsIgnoreCase(fileExtension)) {
                throw new IllegalArgumentException("文件不是Word文档，无法转换为PDF");
            }

            log.info("wordToPdf-before-url="+url);
            PDFUtils.wordToPdf(new File(url));


            int lastSlashIndex = url.lastIndexOf("/");
            // 替换文件扩展名为.pdf
            String newUrl = url.replaceAll("\\.docx$", ".pdf");
            newUrl = newUrl.replaceAll("\\.docx$", ".pdf");
            String fileName = newUrl.substring(lastSlashIndex + 1);
            log.info("filePdf的url========="+newUrl);
            log.info("filePdf的fileName========="+fileName);

            try {
                FileDownloadUtils.downloadFile(fileName,newUrl,response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

//    新增删除文件方法（weboffice）
    public void deleteFile(String url){
        String prefix = localFilePrefix;
        // 截取从前缀结束位置开始的子字符串
        String resultUrl = url.substring(prefix.length());
        String pathUrl = localFilePath + resultUrl;
        log.info("pathUrl======="+pathUrl);
        File file = new File(pathUrl);
        if(file.exists()){
//            deleteOnExit() 方法会在 JVM 退出时删除文件，而不是立即删除
//            file.deleteOnExit();
            file.delete();
        }

    }


    public static void main(String[] args) {
        String localFilePrefix = "/statics";
        String s = "/statics/1.zip";
        System.out.println(localFilePrefix.length());
        System.out.println(s.indexOf(localFilePrefix.length()));
        String s2 = s.substring(localFilePrefix.length());
        System.out.println(s2);
    }

    /**
     * 将文件压缩成zip后下载
     * @param response
     * @param url
     */
    @Override
    public void downloadZipFile(HttpServletResponse response, String url,String fileName) throws Exception{
        if(StringUtils.isBlank(fileName)){
            fileName = "压缩文档.zip";
        }
        String[] urls = url.split(",");
        //将文件压缩成zip
        List<File> files = new ArrayList<>();
        for (String s : urls) {
            if(s.startsWith(localFilePrefix)){
                s = s.substring(localFilePrefix.length());
            }
            String filePath = localFilePath + s;
            files.add(FileUtil.file(filePath));
        }
        //zip的临时目录的文件
        String tmpFile = localFilePath + "temp/"+ System.currentTimeMillis()+".zip";
        File outZipFile = FileUtil.file(tmpFile);
        if(outZipFile.exists()){
            outZipFile.deleteOnExit();//如果存在先删掉
        }
        File[] filesFile = files.toArray(new File[files.size()]);
        File zipFile = ZipUtil.zip(outZipFile,false,filesFile);
        FileDownloadUtils.downloadFile(fileName,zipFile,response);
    }
}
