package com.ruoyi.auth.controller;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkcontact_1_0.models.GetUserHeaders;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetbyunionidRequest;
import com.dingtalk.api.request.OapiV2UserGetuserinfoRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetbyunionidResponse;
import com.dingtalk.api.response.OapiV2UserGetuserinfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.auth.config.AppConfig;
import com.ruoyi.auth.constant.UrlConstant;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.DdUserVo;
import com.ruoyi.system.api.factory.RemoteLogFallbackFactory;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.SysAuthUserModel;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.math.BigInteger;
import java.util.List;

/**
 * @author: sanzhi
 * @date: 2023/9/21 15:22
 * 演示:
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AppConfig appConfig;



    public static com.aliyun.dingtalkoauth2_1_0.Client authClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }
    /**
     * 获取用户token
     * @param authCode
     * @return
     * @throws Exception
     */

    //接口地址：注意/auth与钉钉登录与分享的回调域名地址一致
    @RequestMapping(value = "/ddauth", method = RequestMethod.GET)
    public R<?> getAccessToken(@RequestParam(value = "authCode")String authCode) throws Exception {
        com.aliyun.dingtalkoauth2_1_0.Client client = authClient();
        GetUserTokenRequest getUserTokenRequest = new GetUserTokenRequest()

                //应用基础信息-应用信息的AppKey,请务必替换为开发的应用AppKey
                .setClientId(appConfig.getAppKey())
                //应用基础信息-应用信息的AppSecret，,请务必替换为开发的应用AppSecret
                .setClientSecret(appConfig.getAppSecret())
                .setCode(authCode)
                .setGrantType("authorization_code");
        GetUserTokenResponse getUserTokenResponse = client.getUserToken(getUserTokenRequest);
        //获取用户个人token
        String accessToken = getUserTokenResponse.getBody().getAccessToken();
        return getUserinfo(accessToken);

    }

    private String getUserId(String unionId) {
        String userId = "";
        try {
            DingTalkClient getToken = new DefaultDingTalkClient(UrlConstant.GET_TOKEN_URL);
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(appConfig.getAppKey());
            request.setAppsecret(appConfig.getAppSecret());
            request.setHttpMethod("GET");
            OapiGettokenResponse response = getToken.execute(request);

            DingTalkClient getByUnionId = new DefaultDingTalkClient(UrlConstant.GET_BY_UNIONID_URL);
            OapiUserGetbyunionidRequest req = new OapiUserGetbyunionidRequest();
            req.setUnionid(unionId);
            OapiUserGetbyunionidResponse rsp = getByUnionId.execute(req, response.getAccessToken());

            JsonObject jsonObject = JsonParser.parseString(rsp.getBody()).getAsJsonObject();
            userId = jsonObject.getAsJsonObject("result").get("userid").getAsString();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        return userId;
    }


    public static com.aliyun.dingtalkcontact_1_0.Client contactClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkcontact_1_0.Client(config);
    }
    /**
     * 获取用户个人信息
     * @param accessToken
     * @return
     * @throws Exception
     */
    public R<?> getUserinfo(String accessToken) throws Exception {
        com.aliyun.dingtalkcontact_1_0.Client client = contactClient();
        GetUserHeaders getUserHeaders = new GetUserHeaders();
        getUserHeaders.xAcsDingtalkAccessToken = accessToken;
        //获取用户个人信息，如需获取当前授权人的信息，unionId参数必须传me
        ObjectMapper objectMapper = new ObjectMapper();
        DdUserVo ddUserVo = objectMapper.readValue(JSON.toJSONString(client.getUserWithOptions("me", getUserHeaders, new RuntimeOptions()).getBody()),DdUserVo.class);

        logger.info("ddUserVo="+ com.alibaba.fastjson2.JSON.toJSONString(ddUserVo));

        //将从钉钉获得的用户信息对应到系统的用户信息
        ddUserVo.setUserName(ddUserVo.getMobile());
        ddUserVo.setNickName(ddUserVo.getNick());
        ddUserVo.setAvatar(ddUserVo.getAvatarUrl());
        ddUserVo.setPhonenumber(ddUserVo.getMobile());
        ddUserVo.setDdUserId(getUserId(ddUserVo.getUnionId()));

        R<LoginUser> loginUserR = remoteUserService.getUserInfo(ddUserVo.getUserName(),SecurityConstants.INNER);
        logger.info("loginUserR="+ com.alibaba.fastjson2.JSON.toJSONString(loginUserR));
        LoginUser loginUser = loginUserR.getData();
        logger.info("loginUser="+ com.alibaba.fastjson2.JSON.toJSONString(loginUser));
        if(loginUser == null){
            //用户不存在，注册一个
            ddUserVo.setPassword("admin");//扫码登录设置初始密码
            //帐号唯一，注册帐号
            sysLoginService.register(ddUserVo);
        }


        //帐号已存在，登录
        LoginUser userInfo = sysLoginService.login(ddUserVo.getUserName(), "admin",false);

        SysAuthUserModel sysAuthUser = null;
        if(loginUser != null){
            sysAuthUser =  loginUser.getSysAuthUser();
            logger.info("sysAuthUser:"+ JSON.toJSONString(sysAuthUser));
            remoteUserService.updateUserDept(sysAuthUser);
        }
        if(sysAuthUser == null){
            sysAuthUser = new SysAuthUserModel();
        }
        sysAuthUser.setUserId(userInfo.getSysUser().getUserId());
        sysAuthUser.setUnionId(ddUserVo.getUnionId());
        sysAuthUser.setOpenId(ddUserVo.getOpenId());
        sysAuthUser.setDdUserId(ddUserVo.getDdUserId());
        remoteUserService.saveSysAuthUser(sysAuthUser,SecurityConstants.INNER);

        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }
}
