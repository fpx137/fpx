package com.ruoyi.system.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysSms;
import com.ruoyi.system.api.factory.RemoteSmsFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 短信服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteSmsService", value = ServiceNameConstants.SMS_SERVICE, fallbackFactory = RemoteSmsFallbackFactory.class)
public interface RemoteSmsService
{
    /**
     * 发送短信验证码
     *
     * @param phoneNumber 电话号码
     * @param code 验证码
     * @return 结果
     */
    @PostMapping(value = "/sendSms")
     R<SysSms> sendSmsCode(@RequestParam("phoneNumber") String phoneNumber,@RequestParam("code") String code);

    /**
     * 发送缴费短信
     *
     * @param phoneNumber 电话号码
     * @param message 缴费信息
     * @return 结果
     */
    @PostMapping(value = "/sendPaySms")
    R<SysSms> sendPaySms(@RequestParam("phoneNumber") String phoneNumber,@RequestParam("message") String ... message);
}
