package com.ruoyi.cache.bean;

import org.junit.Test;

public class DeptData {

    private Long deptId;
    private Long parentId;
    private String deptName;

    public Long getDeptId() {
        return deptId;
    }

    public void  setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
