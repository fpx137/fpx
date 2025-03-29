package com.ruoyi.job.domain.SysFlow;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 出差登记对象 attend_business_register
 *
 * @author Qiuweihuang
 * @date 2023-07-24
 */
public class AttendBusinessRegister
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
    private Long id;

    /** 出差地点 */
    @ApiModelProperty(name = "出差地点")
    private String businessLocation;

    /** 出差原因 */
    @ApiModelProperty(name = "出差原因")
    private String businessReason;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date applyTime;

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
//    @ApiModelProperty(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
//    @ApiModelProperty(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 状态 */
    @ApiModelProperty(name = "状态")
    private Integer state;

    /** 流程实例id */
    @ApiModelProperty(name = "流程实例id")
    private String processInstanceId;

    /** 出差人员id */
    @ApiModelProperty(name = "出差人员")
    private String businessPeople;

    /** 审批人员 */
    @ApiModelProperty(name = "审批人员")
    private String approvalPeople;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @ApiModelProperty(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvalTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @ApiModelProperty(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
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
    private Long delFlag;

    private Date createTime;

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
    public void setBusinessLocation(String businessLocation)
    {
        this.businessLocation = businessLocation;
    }

    public String getBusinessLocation()
    {
        return businessLocation;
    }
    public void setBusinessReason(String businessReason)
    {
        this.businessReason = businessReason;
    }

    public String getBusinessReason()
    {
        return businessReason;
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
    public void setBusinessPeople(String businessPeople)
    {
        this.businessPeople = businessPeople;
    }

    public String getBusinessPeople()
    {
        return businessPeople;
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
    public void setDelFlag(Long delFlag)
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("businessLocation", getBusinessLocation())
            .append("businessReason", getBusinessReason())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("state", getState())
            .append("processInstanceId", getProcessInstanceId())
            .append("businessPeople", getBusinessPeople())
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
