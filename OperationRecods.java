package com.ruoyi.system.api.domain.SysFlow.flowexample;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Attachments;

import java.util.List;
@JsonSerialize
public class OperationRecods {
private static final long serialVersionUID = 1L;

private String userId;
private String type;
private String result;
private String remark;
private String date;
private String showName;
private List<Attachments> attachments;
private String activityId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public List<Attachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachments> attachments) {
        this.attachments = attachments;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
