package com.ruoyi.system.api.domain;


import java.math.BigInteger;

public class DdUserVo extends SysUser {

    String ddUserId;

    Integer id;

    String avatarUrl;

    String mobile;

    String nick;

    String openId;

    String stateCode;

    String unionId;


    public String getDdUserId() {
        return ddUserId;
    }

    public void setDdUserId(String ddUserId) {
        this.ddUserId = ddUserId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DdUserVo{" +
                "ddUserId='" + ddUserId + '\'' +
                ", id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nick='" + nick + '\'' +
                ", openId='" + openId + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", unionId='" + unionId + '\'' +
                '}';
    }
}
