package com.ruoyi.system.api.domain.SysFlow.flowexample.Request;



import com.ruoyi.system.api.domain.SysFlow.flowtask.File;

import java.util.List;

public class AddApproval {
    private String proessInstanceId;
    private String text;
    private String commentUserId;
    private List<File> file;
    private static final long serialVersionUID = 1L;

    public String getProessInstanceId() {
        return proessInstanceId;
    }

    public void setProessInstanceId(String proessInstanceId) {
        this.proessInstanceId = proessInstanceId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }


}
