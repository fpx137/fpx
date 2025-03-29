package com.ruoyi.system.api.domain.SysFlow.flowexample.Request;

public class CancelApproval {
    private String proessInstanceId;
    private Boolean isSystem;
    private String remark;
    private String operatingUserId;
    private static final long serialVersionUID = 1L;

    public String getProessInstanceId() {
        return proessInstanceId;
    }

    public void setProessInstanceId(String proessInstanceId) {
        this.proessInstanceId = proessInstanceId;
    }

    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean system) {
        isSystem = system;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperatingUserId() {
        return operatingUserId;
    }

    public void setOperatingUserId(String operatingUserId) {
        this.operatingUserId = operatingUserId;
    }


}
