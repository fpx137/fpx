package com.ruoyi.job.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author kano
 * @date 2023/8/1
 */
public class UserVo{

    private String nickname;// 昵称
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;// 用户I
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orgId;// 机构ID

    @Override
    public String toString() {
        return "UserVo{" +
                "nickname='" + nickname + '\'' +
                ", userId=" + userId +
                ", orgId=" + orgId +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
