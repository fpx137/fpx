package com.ruoyi.system.api.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 短信信息
 *
 * @author ruoyi
 */
public class SysSms
{
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 验证码
     */
    private String code;
    /**
     * 缴费信息
     */
    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }





    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phoneNumber", getPhoneNumber())
            .append("code", getCode())
            .append("message", getMessage())
            .toString();
    }
}
