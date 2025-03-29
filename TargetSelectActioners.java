package com.ruoyi.system.api.domain.SysFlow.flowexample;

import java.util.List;

public class TargetSelectActioners {
    private static final long serialVersionUID = 1L;
    private String actionerKey;
    private List<String> actionerUserIds;

    public String getActionerKey() {
        return actionerKey;
    }

    public void setActionerKey(String actionerKey) {
        this.actionerKey = actionerKey;
    }

    public List<String> getActionerUserIds() {
        return actionerUserIds;
    }

    public void setActionerUserIds(List<String> actionerUserIds) {
        this.actionerUserIds = actionerUserIds;
    }
}
