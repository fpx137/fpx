package com.ruoyi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class DingTalkConfig {

    @Value("${dingtalk.appKey}")
    private String appKey;

    @Value("${dingtalk.appSecret}")
    private String appSecret;

    @Value("${dingtalk.agentId}")
    private Long agentId;

//    @Value("${dingtalk.OutprocessCode}")
//    private String outProcessCode;
//
//    @Value("${dingtalk.BusinessprocessCode}")
//    private String businessProcessCode;
//
//    @Value("${dingtalk.LeaveprocessCode}")
//    private String leaveProcessCode;
//    @Value("${dingtalk.OvertimeprocessCode}")
//    private String overtimeProcessCode;
//    //采购
//    @Value("${dingtalk.ProcurementprocessCode}")
//    private String procurementProcessCode;
//    //新闻
//    @Value("${dingtalk.ManagementprocessCode}")
//    private String managementProcessCode;




    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public Long getAgentId() {
        return agentId;
    }

//    public String getOutProcessCode() {
//        return outProcessCode;
//    }
//
//    public String getBusinessProcessCode() {
//        return businessProcessCode;
//    }
//
//    public String getLeaveProcessCode() {
//        return leaveProcessCode;
//    }
//
//    public String getOvertimeProcessCode() {
//        return overtimeProcessCode;
//    }
//
//    public void setAppKey(String appKey) {
//        this.appKey = appKey;
//    }
//
//    public void setAppSecret(String appSecret) {
//        this.appSecret = appSecret;
//    }
//
//    public void setAgentId(Long agentId) {
//        this.agentId = agentId;
//    }
//
//    public void setOutProcessCode(String outProcessCode) {
//        this.outProcessCode = outProcessCode;
//    }
//
//    public void setBusinessProcessCode(String businessProcessCode) {
//        this.businessProcessCode = businessProcessCode;
//    }
//
//    public void setLeaveProcessCode(String leaveProcessCode) {
//        this.leaveProcessCode = leaveProcessCode;
//    }
//
//    public void setOvertimeProcessCode(String overtimeProcessCode) {
//        this.overtimeProcessCode = overtimeProcessCode;
//    }
//
//    public String getProcurementProcessCode() {
//        return procurementProcessCode;
//    }
//
//    public void setProcurementProcessCode(String procurementProcessCode) {
//        this.procurementProcessCode = procurementProcessCode;
//    }
//
//    public String getManagementProcessCode() {
//        return managementProcessCode;
//    }
//
//    public void setManagementProcessCode(String managementProcessCode) {
//        this.managementProcessCode = managementProcessCode;
//    }
//


}

