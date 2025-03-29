package com.ruoyi.job.domain.SysFlow;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 请假登记对象 attend_leave_register
 *
 * @author Qiuweihuang
 * @date 2023-07-24
 */
public class AttendLeaveRegister implements Serializable
{
    private static final long serialVersionUID = 1L;


    @TableField(exist = false)
    private  String processDefinitionId;

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /** 主键id */
    private Integer id;

    /** 请假原因 */
    @ApiModelProperty(name = "请假原因")
    private String leaveReason;

    /** 请假类型 */
    @ApiModelProperty(name = "请假类型")
    private String leaveType;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date applyTime;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    /** 申请稍假时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date terminationTime;

    /** 状态 */
    @ApiModelProperty(name = "流程状态")
    private Integer state;

    /** 流程实例id */
    @ApiModelProperty(name = "流程实例id")
    private String processInstanceId;

    /** 请假人员id */
    @ApiModelProperty(name = "请假人员")
    private String leavePeople;

    /** 审批人员 */
    @ApiModelProperty(name = "审批人员")
    private String approvalPeople;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approvalTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date delTime;

    /** 删除人(登录名) */
    @ApiModelProperty(name = "删除人(登录名)")
    private String delUser;

    /** 创建者(登录名) */
    @ApiModelProperty(name = "创建者(登录名)")
    private String creator;

    /** 更新者(登录名) */
    @ApiModelProperty(name = "更新者(登录名)")
    private String updater;

    /** 是否删除;是否删除 ，0正常，1删除 */
    private Integer delFlag;

    private Date CreateTime;

    private Date UpdateTime;

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }



    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setLeaveReason(String leaveReason)
    {
        this.leaveReason = leaveReason;
    }

    public String getLeaveReason()
    {
        return leaveReason;
    }
    public void setLeaveType(String leaveType)
    {
        this.leaveType = leaveType;
    }

    public String getLeaveType()
    {
        return leaveType;
    }
    public void setApplyTime(Date applyTime)
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime()
    {
        return applyTime;
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
    public void setTerminationTime(Date terminationTime)
    {
        this.terminationTime = terminationTime;
    }

    public Date getTerminationTime()
    {
        return terminationTime;
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
    public void setLeavePeople(String leavePeople)
    {
        this.leavePeople = leavePeople;
    }

    public String getLeavePeople()
    {
        return leavePeople;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("leaveReason", getLeaveReason())
            .append("leaveType", getLeaveType())
            .append("applyTime", getApplyTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("terminationTime", getTerminationTime())
            .append("state", getState())
            .append("processInstanceId", getProcessInstanceId())
            .append("leavePeople", getLeavePeople())
            .append("approvalPeople", getApprovalPeople())
            .append("approvalTime", getApprovalTime())
            .append("delTime", getDelTime())
            .append("delUser", getDelUser())
            .append("createTime", getCreateTime())
            .append("creator", getCreator())
            .append("updateTime", getUpdateTime())
            .append("updater", getUpdater())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
