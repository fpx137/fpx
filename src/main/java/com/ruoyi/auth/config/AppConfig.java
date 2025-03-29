package com.ruoyi.auth.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Value("${dingtalk.app_key}")
    @Value("${dingtalk.app_key}")
    private String appKey;

    @Value("${dingtalk.app_secret}")
    private String appSecret;

//    @Value("${dingtalk.corp_id}")
//    private String corpId;


    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
