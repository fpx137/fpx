package com.ruoyi.system.api.domain.SysFlow.flowexample;

import java.io.Serializable;
import java.util.List;

public class WorkRecordVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //待办跳转链接
    private String url;
    //待办任务ID
    private String taskId;
    //实例ID
    private String instanceId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    //待办标题
    private String title;
    //表单列表
    private List<FormItemVo> forms;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FormItemVo> getForms() {
        return forms;
    }

    public void setForms(List<FormItemVo> forms) {
        this.forms = forms;
    }
}
