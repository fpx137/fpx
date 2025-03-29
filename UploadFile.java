package com.ruoyi.system.api.domain.SysFlow.flowexample.Request;

public class UploadFile {
    String unionId ;
    String uploadKey ;
    Long spaceId;
    String fileName;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getUploadKey() {
        return uploadKey;
    }

    public void setUploadKey(String uploadKey) {
        this.uploadKey = uploadKey;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
