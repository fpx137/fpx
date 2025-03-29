package com.ruoyi.config;

import com.aliyun.teaopenapi.models.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 读取代码生成相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "flow")
//@PropertySource(value = { "classpath:sms.yml" }, encoding="UTF-8")
public class FlowConfig {

    /**
     * 使用 Token 初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dingtalkworkflow_1_0.Client createClientFlow() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkworkflow_1_0.Client(config);
    }
    public static com.aliyun.dingtalktodo_1_0.Client createClientTodo() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalktodo_1_0.Client(config);
    }
    public static com.aliyun.dingtalkoauth2_1_0.Client createClientAsstoken() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }
    public static com.aliyun.dingtalkstorage_1_0.Client createClientStorage() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkstorage_1_0.Client(config);
    }

//    public static com.aliyun.dingtalkoauth2_1_0.Client createClient() throws Exception {
//        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
//        config.protocol = "https";
//        config.regionId = "central";
//        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
//    }


//    @Value("${dingtalk.appKey}")
//    private String appKey;
//
//    @Value("${dingtalk.appSecret}")
//    private String appSecret;
//
//    @Value("${dingtalk.agentId}")
//    private Long agentId;
//
//    @Value("${dingtalk.OutprocessCode}")
//    private String outProcessCode;
//
//    @Value("${dingtalk.BusinessprocessCode}")
//    private String businessProcessCode;
//
//    @Value("${dingtalk.LeaveprocessCode}")
//    private String leaveProcessCode;
//
//    @Value("${dingtalk.OvertimeprocessCode}")
//    private String overtimeProcessCode;
//
//    public String getAppKey() {
//        return appKey;
//    }
//
//    public String getAppSecret() {
//        return appSecret;
//    }
//
//    public Long getAgentId() {
//        return agentId;
//    }
//
//    public String getOutProcessCode() {
//        return outProcessCode;
//    }
//
//    public String getBusinessProcessCode() {
//        return businessProcessCode;
//    }

//    public String getLeaveProcessCode() {
//        return leaveProcessCode;
//    }
//
//    public String getOvertimeProcessCode() {
//        return overtimeProcessCode;
//    }
}
