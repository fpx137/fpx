package com.ruoyi.job.config;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.ruoyi.job.util.AccessTokenUtil;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dingtalk")
public class DingdingConfig {
    private String appkey;
    private String appsecret;

    @Value("${dingtalk.BusinessprocessCode}")
    private String businessProcessCode;

    @Value("${dingtalk.LeaveprocessCode}")
    private String leaveProcessCode;

    @Value("${dingtalk.OvertimeprocessCode}")
    private String overtimeProcessCode;
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public String getAppsecret() {
        return appsecret;
    }
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getBusinessProcessCode() {
        return businessProcessCode;
    }

    public void setBusinessProcessCode(String businessProcessCode) {
        this.businessProcessCode = businessProcessCode;
    }

    public String getLeaveProcessCode() {
        return leaveProcessCode;
    }

    public void setLeaveProcessCode(String leaveProcessCode) {
        this.leaveProcessCode = leaveProcessCode;
    }

    public String getOvertimeProcessCode() {
        return overtimeProcessCode;
    }

    public void setOvertimeProcessCode(String overtimeProcessCode) {
        this.overtimeProcessCode = overtimeProcessCode;
    }

    private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);
    public String getToken() throws ApiException {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(getAppkey());
            request.setAppsecret(getAppsecret());
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            String accessToken = response.getAccessToken();
            return accessToken;
        } catch (Exception e) {
            // 记录在获取访问令牌过程中发生的任何异常
            bizLogger.info("获取访问令牌失败: {}", e.getMessage(), e);
            throw new ApiException("获取访问令牌失败", e);
        }
    }
}

