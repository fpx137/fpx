package com.ruoyi.system.api.domain.SysFlow.flowexample.Return;

import java.util.List;
import java.util.Map;

public class UploadInformationResult {
    //上传唯一标识。
    private String uploadKey;
    //文件存储类型。
    private String storageDriver;
    //上传协议。
    private  String protocol;
    //Header加签上传信息。
    private  HeaderSignatureInfo headerSignatureInfo;

    public String getUploadKey() {
        return uploadKey;
    }

    public void setUploadKey(String uploadKey) {
        this.uploadKey = uploadKey;
    }

    public String getStorageDriver() {
        return storageDriver;
    }

    public void setStorageDriver(String storageDriver) {
        this.storageDriver = storageDriver;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public HeaderSignatureInfo getHeaderSignatureInfo() {
        return headerSignatureInfo;
    }

    public void setHeaderSignatureInfo(HeaderSignatureInfo headerSignatureInfo) {
        this.headerSignatureInfo = headerSignatureInfo;
    }

    public class HeaderSignatureInfo {
        //多个上传下载URL列表。
      private List<String> resourceUrls;
      //请求头信息。
      private Map<String, String> headers;

      //地域
      private String region;
      //过期时间，单位秒
      private int expirationSeconds;
      //内网访问地址。
      private List<String> internalResourceUrls;

        public List<String> getResourceUrls() {
            return resourceUrls;
        }

        public void setResourceUrls(List<String> resourceUrls) {
            this.resourceUrls = resourceUrls;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getExpirationSeconds() {
            return expirationSeconds;
        }

        public void setExpirationSeconds(int expirationSeconds) {
            this.expirationSeconds = expirationSeconds;
        }

        public List<String> getInternalResourceUrls() {
            return internalResourceUrls;
        }

        public void setInternalResourceUrls(List<String> internalResourceUrls) {
            this.internalResourceUrls = internalResourceUrls;
        }
    }
}
