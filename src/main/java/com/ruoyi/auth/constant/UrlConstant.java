package com.ruoyi.auth.constant;

/**
 * 钉钉开放接口网关常量
 */
public class UrlConstant {

    /**
     * 获取access_token url
     */
    public static final String GET_ACCESS_TOKEN_URL = "https://oapi.dingtalk.com/gettoken";
    /**
     * 通过免登授权码获取用户信息 url
     */
    public static final String GET_USER_INFO_URL = "https://oapi.dingtalk.com/topapi/v2/user/getuserinfo";

    /**
     * 通过unionId获取userId
     */
    public static final String GET_BY_UNIONID_URL = "https://oapi.dingtalk.com/topapi/user/getbyunionid";

    /**
     * 根据AppKey和AppSecret获取token
     */
    public static final String GET_TOKEN_URL = "https://oapi.dingtalk.com/gettoken";

    /**
     * 根据用户id获取用户详情 url
     */
    public static final String USER_GET_URL = "https://oapi.dingtalk.com/topapi/v2/user/get";

}
