package com.ruoyi.file.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传接口
 *
 * @author ruoyi
 */
public interface ISysFileService
{
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;

    public void downloadFile(HttpServletResponse response, String url);
    public void downloadZipFile(HttpServletResponse response, String url,String fileName) throws Exception;

    public void downloadFilePdf(HttpServletResponse response, String url);

    public void deleteFile(String url);

}
