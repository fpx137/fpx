package com.ruoyi.system.api.domain.SysFlow.flowexample.Request;

import com.ruoyi.system.api.domain.SysFlow.flowexample.FormComponentValues;

import java.util.List;

public class ApprovalNode {
    private static final long serialVersionUID = 1L;
    private String processCode;
    private int deptId;
    private String userId;
    private List<FormComponentValues> formComponentValues;


    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<FormComponentValues> getFormComponentValues() {
        return formComponentValues;
    }

    public void setFormComponentValues(List<FormComponentValues> formComponentValues) {
        this.formComponentValues = formComponentValues;
    }
}
