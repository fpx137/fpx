package com.ruoyi.system.api.domain.SysFlow.flowexample.Request;

import java.util.List;

public class GainApprovalId {
    private static final long serialVersionUID = 1L;
    private String processCode;
    private Long startTime;
    private Long endTime;
    private Long nextToken;
    private Long maxResults;
    private List<String> userids;
    private List<String> statuses;

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getNextToken() {
        return nextToken;
    }

    public void setNextToken(Long nextToken) {
        this.nextToken = nextToken;
    }

    public Long getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Long maxResults) {
        this.maxResults = maxResults;
    }

    public List<String> getUserids() {
        return userids;
    }

    public void setUserids(List<String> userids) {
        this.userids = userids;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }
}
