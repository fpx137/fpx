package com.ruoyi.file.controller;


import com.ruoyi.file.service.LocalSysFileServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.system.api.domain.SysFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件请求处理
 *
 * @author ruoyi
 */
@RestController
public class SysFileController
{
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private ISysFileService sysFileService;



    /**
     * 文件上传请求
     */

    @PostMapping("upload")
    public R<SysFile> upload(MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            System.out.println(url);
            return R.ok(sysFile);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

//    删除文件
    @PostMapping("delete")
    public R<Boolean> delete(@RequestParam(value = "url") String url){
        sysFileService.deleteFile(url);
        return R.ok(Boolean.TRUE);
    }

    /**
     * 下载文件
     *
     * @param request          请求对象
     * @param response         响应对象
     * @param originalFilename 文件名称
     * @param url              下载URL
     */
    @ApiOperation(value = "下载文件")
//    @PostMapping(path = "/downloadFile")
    @RequestMapping(value = "/downloadFile", method = {RequestMethod.GET,RequestMethod.POST})
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,@RequestParam(name = "url", required = true) String url) {
        System.out.println("文件控制器中的url======="+url);
        try {

            if (url.startsWith("/statics")){
                url = url.substring("/statics".length());
            }

            sysFileService.downloadFile(response, url);
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    @ApiOperation(value = "下载查新报告")
//    @PostMapping(path = "/downloadFile")
    @RequestMapping(value = "/downloadFilePdf", method = {RequestMethod.GET,RequestMethod.POST})
    public void downloadFilePdf(HttpServletRequest request, HttpServletResponse response,@RequestParam(name = "url", required = true) String url) {
        log.info("文件控制器中的url======="+url);
        try {

            if (url.startsWith("/statics")){
                url = url.substring("/statics".length());
            }
            log.info("downloadFilePdf======url="+url);
            sysFileService.downloadFilePdf(response, url);
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

@ApiOperation(value = "下载文件")
@RequestMapping(value = "/downloadFileWebOffice/{url:.+}", method = {RequestMethod.GET, RequestMethod.POST})
public void downloadFileWebOffice(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = "url") String url) {
    try {
        // 解码URL
        String decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString());
        sysFileService.downloadFile(response, decodedUrl);
    } catch (Exception e) {
        e.printStackTrace();
    }
}



    /**
     * 下载文件
     *
     * @param request          请求对象
     * @param response         响应对象
     * @param originalFilename 文件名称
     * @param url              下载URL
     */
    @ApiOperation(value = "下载Zip文件")
    @PostMapping(path = "/downloadZipFile")
    public void downloadZipFile(HttpServletRequest request, HttpServletResponse response
            ,@RequestParam(name = "url", required = true) String url,@RequestParam(name = "fileName", required = false) String fileName) throws Exception {
//        try {
            sysFileService.downloadZipFile(response, url,fileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
