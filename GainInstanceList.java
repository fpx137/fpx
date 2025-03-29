package com.ruoyi.system.api.domain.SysFlow.flowexample.Request;

public class GainInstanceList {
    private static final long serialVersionUID = 1L;
    //钉钉用户id
    String userId ;
    //偏移量
    Long Offset;
    //数量
    Long Count;
    //状态 0:待办 -1:已经移除
    Long Status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getOffset() {
        return Offset;
    }

    public void setOffset(Long offset) {
        Offset = offset;
    }

    public Long getCount() {
        return Count;
    }

    public void setCount(Long count) {
        Count = count;
    }

    public Long getStatus() {
        return Status;
    }

    public void setStatus(Long status) {
        Status = status;
    }
}
