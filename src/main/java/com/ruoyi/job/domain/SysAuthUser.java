package com.ruoyi.job.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 第三方授权对象 sys_auth_user
 *
 * @author ruoyi
 * @date 2024-07-14
 */
public class SysAuthUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String unionId;

    /** 系统用户id */
    @Excel(name = "系统用户id")
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String openId;

    /** 钉钉userid */
    @Excel(name = "钉钉userid")
    private String ddUserId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUnionId(String unionId)
    {
        this.unionId = unionId;
    }

    public String getUnionId()
    {
        return unionId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public String getOpenId()
    {
        return openId;
    }
    public void setDdUserId(String ddUserId)
    {
        this.ddUserId = ddUserId;
    }

    public String getDdUserId()
    {
        return ddUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unionId", getUnionId())
            .append("userId", getUserId())
            .append("openId", getOpenId())
            .append("ddUserId", getDdUserId())
            .toString();
    }
}
