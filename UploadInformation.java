package com.ruoyi.system.api.domain.SysFlow.flowexample.Request;

import org.springframework.web.multipart.MultipartFile;

public class UploadInformation {
    private    Long spaceId;
    private    String unionId;


    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }


}
