package com.ruoyi.system.api.domain.SysFlow.flowexample.Return;



import com.ruoyi.system.api.domain.SysFlow.flowexample.FormComponentValues;
import com.ruoyi.system.api.domain.SysFlow.flowexample.OperationRecods;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Tasks;

import java.util.List;

public class GainApprovalResult {
    private static final long serialVersionUID = 1L;
    private String title;
    private String finishTime;
    private String originatorUserId;
    private String originatorDeptId;
    private String originatorDeptName;
    private String status;
    private List<String> approverUserIds;
    private List<String> ccUserIds;
    private String result;
    private String businessId;
    private List<OperationRecods> operationRecords;
    private List<Tasks> tasks;
    private String bizAction;
    private String bizData;
    private List<String> attachedProcessInstanceIds;
    private String mainProcessInstanceId;
    private List<FormComponentValues>formComponentValues;
    private String createTime;
    private String success;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getOriginatorUserId() {
        return originatorUserId;
    }

    public void setOriginatorUserId(String originatorUserId) {
        this.originatorUserId = originatorUserId;
    }

    public String getOriginatorDeptId() {
        return originatorDeptId;
    }

    public void setOriginatorDeptId(String originatorDeptId) {
        this.originatorDeptId = originatorDeptId;
    }

    public String getOriginatorDeptName() {
        return originatorDeptName;
    }

    public void setOriginatorDeptName(String originatorDeptName) {
        this.originatorDeptName = originatorDeptName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getApproverUserIds() {
        return approverUserIds;
    }

    public void setApproverUserIds(List<String> approverUserIds) {
        this.approverUserIds = approverUserIds;
    }

    public List<String> getCcUserIds() {
        return ccUserIds;
    }

    public void setCcUserIds(List<String> ccUserIds) {
        this.ccUserIds = ccUserIds;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public List<OperationRecods> getOperationRecords() {
        return operationRecords;
    }

    public void setOperationRecords(List<OperationRecods> operationRecords) {
        this.operationRecords = operationRecords;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public String getBizAction() {
        return bizAction;
    }

    public void setBizAction(String bizAction) {
        this.bizAction = bizAction;
    }

    public String getBizData() {
        return bizData;
    }

    public void setBizData(String bizData) {
        this.bizData = bizData;
    }

    public List<String> getAttachedProcessInstanceIds() {
        return attachedProcessInstanceIds;
    }

    public void setAttachedProcessInstanceIds(List<String> attachedProcessInstanceIds) {
        this.attachedProcessInstanceIds = attachedProcessInstanceIds;
    }

    public String getMainProcessInstanceId() {
        return mainProcessInstanceId;
    }

    public void setMainProcessInstanceId(String mainProcessInstanceId) {
        this.mainProcessInstanceId = mainProcessInstanceId;
    }

    public List<FormComponentValues> getFormComponentValues() {
        return formComponentValues;
    }

    public void setFormComponentValues(List<FormComponentValues> formComponentValues) {
        this.formComponentValues = formComponentValues;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
