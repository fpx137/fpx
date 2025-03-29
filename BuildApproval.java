package com.ruoyi.system.api.domain.SysFlow.flowexample.Request;



import com.ruoyi.system.api.domain.SysFlow.flowexample.FormComponentValues;
import com.ruoyi.system.api.domain.SysFlow.flowexample.TargetSelectActioners;

import java.util.List;

public class BuildApproval {
    private String processCode;
    private List<TargetSelectActioners> targetSelectActioners;
    private String originatorUserId;
    private Long deptId;
    private List<FormComponentValues> formComponentValues;
    private String InstanceId;
    private Long microappAgentId;
    private List<String> ccList;
    private String ccPosition;
    private static final long serialVersionUID = 1L;

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public List<TargetSelectActioners> getTargetSelectActioners() {
        return targetSelectActioners;
    }

    public void setTargetSelectActioners(List<TargetSelectActioners> targetSelectActioners) {
        this.targetSelectActioners = targetSelectActioners;
    }

    public String getOriginatorUserId() {
        return originatorUserId;
    }

    public void setOriginatorUserId(String originatorUserId) {
        this.originatorUserId = originatorUserId;
    }

    public Long getDeptid() {
        return deptId;
    }

    public void setDeptid(Long deptId) {
        this.deptId = deptId;
    }

    public List<FormComponentValues> getFormComponentValues() {
        return formComponentValues;
    }

    public void setFormComponentValues(List<FormComponentValues> formComponentValues) {
        this.formComponentValues = formComponentValues;
    }

    public String getInstanceId() {
        return InstanceId;
    }

    public void setInstanceId(String instanceId) {
        InstanceId = instanceId;
    }

    public Long getMicroappAgentId() {
        return microappAgentId;
    }

    public void setMicroappAgentId(Long microappAgentId) {
        this.microappAgentId = microappAgentId;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public String getCcPosition() {
        return ccPosition;
    }

    public void setCcPosition(String ccPosition) {
        this.ccPosition = ccPosition;
    }
}
