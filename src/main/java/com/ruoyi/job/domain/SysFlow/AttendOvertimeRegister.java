package com.ruoyi.job.domain.SysFlow;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * 加班登记对象 attend_overtime_register
 *
 * @author kano
 * @date 2023-07-25
 */
public class AttendOvertimeRegister implements Serializable
{
    private static final long serialVersionUID = 1L;

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

    /**加班人员id */
    @ApiModelProperty(name = "加班人员")
    private String overtimePeople;

    /** 加班申请时间;记录加班的日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date date;

    /** 审批人员 */
    private String approvalPeople;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date approvalTime;

    /** 加班原因/内容;记录员工加班的原因/内容 */
    private String reason;

    /** 加班开始时间;记录员工加班的开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date overtimeStartTime;

    /** 加班结束时间;记录员工加班的结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date overtimeEndTime;

    /** 调休开始时间;记录员工调休的开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date restTimeStartTime;

    /** 调休结束时间;记录员工调休的开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date restTimeEndTime;

    /** 调休时长;调修时长的小时表示 */
    private Integer restTimeDurationHour;

    /** 调休时长;调休时长的分钟表示 */
    private Integer restTimeDurationMinute;

    /** 状态;待审批/已确认/驳回 */
    private Integer state;

    /** 倒休;是否选择 */
    private String fallback;

    /** 流程实例id */
    private String processInstanceId;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
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
    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
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
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
    public void setOvertimeStartTime(Date overtimeStartTime)
    {
        this.overtimeStartTime = overtimeStartTime;
    }

    public Date getOvertimeStartTime()
    {
        return overtimeStartTime;
    }
    public void setOvertimeEndTime(Date overtimeEndTime)
    {
        this.overtimeEndTime = overtimeEndTime;
    }

    public Date getOvertimeEndTime()
    {
        return overtimeEndTime;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }
    public Integer getState()
    {
        return state;
    }

    public void setFallback(String fallback)
    {
        this.fallback = fallback;
    }

    public String getFallback()
    {
        return fallback;
    }
    public void setProcessInstanceId(String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceId()
    {
        return processInstanceId;
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

    public String getOvertimePeople() {
        return overtimePeople;
    }

    public void setOvertimePeople(String overtimePeople) {
        this.overtimePeople = overtimePeople;
    }

    public Date getRestTimeStartTime() {
        return restTimeStartTime;
    }

    public void setRestTimeStartTime(Date restTimeStartTime) {
        this.restTimeStartTime = restTimeStartTime;
    }

    public Date getRestTimeEndTime() {
        return restTimeEndTime;
    }

    public void setRestTimeEndTime(Date restTimeEndTime) {
        this.restTimeEndTime = restTimeEndTime;
    }

    public Integer getRestTimeDurationHour() {
        return restTimeDurationHour;
    }

    public void setRestTimeDurationHour(Integer restTimeDurationHour) {
        this.restTimeDurationHour = restTimeDurationHour;
    }

    public Integer getRestTimeDurationMinute() {
        return restTimeDurationMinute;
    }

    public void setRestTimeDurationMinute(Integer restTimeDurationMinute) {
        this.restTimeDurationMinute = restTimeDurationMinute;
    }

    @Override
    public String toString() {
        return "AttendOvertimeRegister{" +
                "processDefinitionId='" + processDefinitionId + '\'' +
                ", id=" + id +
                ", overtimePeople='" + overtimePeople + '\'' +
                ", date=" + date +
                ", approvalPeople='" + approvalPeople + '\'' +
                ", approvalTime=" + approvalTime +
                ", reason='" + reason + '\'' +
                ", overtimeStartTime=" + overtimeStartTime +
                ", overtimeEndTime=" + overtimeEndTime +
                ", restTimeStartTime=" + restTimeStartTime +
                ", restTimeEndTime=" + restTimeEndTime +
                ", overtimeDurationHour=" + restTimeDurationHour +
                ", overtimeDurationMinute=" + restTimeDurationMinute +
                ", state='" + state + '\'' +
                ", fallback='" + fallback + '\'' +
                ", processInstanceId='" + processInstanceId + '\'' +
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
