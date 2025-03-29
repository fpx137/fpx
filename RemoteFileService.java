package com.ruoyi.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.factory.RemoteFileFallbackFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService
{
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<SysFile> upload(@RequestPart(value = "file") MultipartFile file);

//    客户端发送删除请求至文件模块
    @PostMapping(value = "/delete")
    public R<Boolean> delete(@RequestParam(value = "url") String url);
//    @PostMapping(value = "/downloadFile")
//    public void downloadFile(HttpServletResponse response, @RequestParam(name = "url") String url);
    @GetMapping(value = "/downloadFile")
    public void downloadFile(@RequestParam("url") String url, HttpServletResponse response);


}
