package com.ruoyi.system.api.model;

/**
 * 第三方授权表 sys_auth_user
 *
 * @author ruoyi
 */
public class SysAuthUserModel
{
    private static final long serialVersionUID = 1L;

    /** 授权ID */
    private Long id;

    /** unionId */
    private String unionId;

    /** 系统用户ID */
    private Long userId;

    /** 钉钉的openId */
    private String openId;

    /** 钉钉userId */
    private String ddUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getDdUserId() {
        return ddUserId;
    }

    public void setDdUserId(String ddUserId) {
        this.ddUserId = ddUserId;
    }
}
