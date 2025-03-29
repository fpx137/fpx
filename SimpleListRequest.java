package com.ruoyi.system.api.domain.SysFlow.flowrole.request;

public class SimpleListRequest {
    private Long roleId;
    private Long size;

    private Long offset;

    public SimpleListRequest(){}
    public SimpleListRequest(Long roleId, Long size, Long offset){
        this.roleId = roleId;
        this.size = size;
        this.offset = offset;
    }



    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }
}
