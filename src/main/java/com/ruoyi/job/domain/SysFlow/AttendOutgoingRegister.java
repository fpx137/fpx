package com.ruoyi.job.domain.SysFlow;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 外出登记对象 attend_outgoing_register
 *
 * @author kano
 * @date 2023-07-25
 */
public class AttendOutgoingRegister implements Serializable
{
    private static final Long serialVersionUID = 1L;

    /** 获取流程定义id */
    @TableField(exist = false)
    private  String processDefinitionId;

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /** 主键id */
    private Long id;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;

    /** 外出原因 */
    private String outgoingReason;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    /** 外出地点 */
    private String outgoingLocaltion;

    /** 状态 */
    private Integer state;

    /** 流程实例id */
    private String processInstanceId;

    /** 外出人员id */
    private String outgoingPeople;

    /** 审批人员 */
    private String approvalPeople;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approvalTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date delTime;

    /** 删除人(登录名) */
    private String delUser;

    /** 创建者(登录名) */
    private String creator;

    /** 更新者(登录名) */
    private String updater;

    /** 是否删除;是否删除 ，0正常，1删除 */
    private Integer delFlag;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

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
    public void setApplyTime(Date applyTime)
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime()
    {
        return applyTime;
    }
    public void setOutgoingReason(String outgoingReason)
    {
        this.outgoingReason = outgoingReason;
    }

    public String getOutgoingReason()
    {
        return outgoingReason;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setOutgoingLocaltion(String outgoingLocaltion)
    {
        this.outgoingLocaltion = outgoingLocaltion;
    }

    public String getOutgoingLocaltion()
    {
        return outgoingLocaltion;
    }
    public void setState(Integer state)
    {
        this.state = state;
    }

    public Integer getState()
    {
        return state;
    }
    public void setProcessInstanceId(String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceId()
    {
        return processInstanceId;
    }
    public void setOutgoingPeople(String outgoingPeople)
    {
        this.outgoingPeople = outgoingPeople;
    }

    public String getOutgoingPeople()
    {
        return outgoingPeople;
    }
    public void setApprovalPeople(String approvalPeople)
    {
        this.approvalPeople = approvalPeople;
    }

    public String getApprovalPeople()
    {
        return approvalPeople;
    }
    public void setApprovalTime(Date approvalTime)
    {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTime()
    {
        return approvalTime;
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
        return "AttendOutgoingRegister{" +
                "processDefinitionId='" + processDefinitionId + '\'' +
                ", id=" + id +
                ", applyTime=" + applyTime +
                ", outgoingReason='" + outgoingReason + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", outgoingLocaltion='" + outgoingLocaltion + '\'' +
                ", state=" + state +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", outgoingPeople='" + outgoingPeople + '\'' +
                ", approvalPeople='" + approvalPeople + '\'' +
                ", approvalTime=" + approvalTime +
                ", delTime=" + delTime +
                ", delUser='" + delUser + '\'' +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                ", delFlag=" + delFlag +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
