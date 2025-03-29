package com.ruoyi.job.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 获取access_token工具类
 */
@Component
public class AccessTokenUtil {
    private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);

    public static String getToken(String appkey, String appsecret) throws ApiException {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(appkey);
            request.setAppsecret(appsecret);
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
    public static String getToken() throws ApiException {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
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



