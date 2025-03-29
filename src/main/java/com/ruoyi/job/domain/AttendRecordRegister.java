package com.ruoyi.job.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 考勤登记明细对象 attend_record_register
 *
 * @author Qiuweihuang
 * @date 2023-08-09
 */
public class AttendRecordRegister implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户id */
    private Long userId;

    /** 考勤配置表_ID */
    private Long recordConfigId;

    /** 登记的次序;登记的次序 */
    private Integer registerOrder;

    /** 登记时间;登记的具体日期和时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date delTime;

    /** 删除人(登录名) */
    private String delUser;

    /** 创建者(登录名) */
    private String creator;

    /** 更新者(登录名) */
    private String updater;

    /** 是否删除;是否删除 ，0正常，1删除 */
    private Integer delFlag;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Long getRecordConfigId() {
        return recordConfigId;
    }

    public void setRecordConfigId(Long recordConfigId) {
        this.recordConfigId = recordConfigId;
    }

    public String getRemark() {return remark;}
    public void setRemark(String remark){
        this.remark=remark;
}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setRegisterOrder(Integer registerOrder)
    {
        this.registerOrder = registerOrder;
    }

    public Integer getRegisterOrder()
    {
        return registerOrder;
    }
    public void setRegisterTime(Date registerTime)
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime()
    {
        return registerTime;
    }
    public void setDelTime(Date delTime)
    {
        this.delTime = delTime;
    }

    public Date getDelTime()
    {
        return delTime;
    }
    public void setDelUser(String delUser)
    {
        this.delUser = delUser;
    }

    public String getDelUser()
    {
        return delUser;
    }
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreator()
    {
        return creator;
    }
    public void setUpdater(String updater)
    {
        this.updater = updater;
    }

    public String getUpdater()
    {
        return updater;
    }
    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("registerOrder", getRegisterOrder())
            .append("registerTime", getRegisterTime())
            .append("createTime", getCreateTime())
            .append("delTime", getDelTime())
            .append("delUser", getDelUser())
            .append("creator", getCreator())
            .append("updateTime", getUpdateTime())
            .append("updater", getUpdater())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
                .append("recordConfigId", getRecordConfigId())
            .toString();
    }
}
