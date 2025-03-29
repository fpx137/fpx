package com.ruoyi.system.api.domain.SysFlow.flowexample.Return;

import java.util.List;

public class GainApprovalIdResult {
    private List<String> list;
    private String nextToken;
    private Boolean success;
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
