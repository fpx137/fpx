package com.ruoyi.system.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteSmsService;
import com.ruoyi.system.api.domain.SysSms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 短信服务降级处理
 *
 * @author ruoyi
 */
@Component
public class RemoteSmsFallbackFactory implements FallbackFactory<RemoteSmsService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteSmsFallbackFactory.class);

    @Override
    public RemoteSmsService create(Throwable throwable)
    {
        log.error("短信服务调用失败:{}", throwable.getMessage());
        return new RemoteSmsService()
        {
            @Override
            public R<SysSms> sendSmsCode(String phoneNumber, String code)
            {
                return R.fail("短信发送失败:" + throwable.getMessage());
            }

            @Override
            public R<SysSms> sendPaySms(String phoneNumber, String ...message)
            {
                return R.fail("短信发送失败:" + throwable.getMessage());
            }
        };
    }
}
