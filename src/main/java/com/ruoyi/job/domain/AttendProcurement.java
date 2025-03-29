package com.ruoyi.job.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 采购申请对象 attend_procurement
 *
 * @author ruoyi
 *
 */
public class AttendProcurement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户主键id */
    @Excel(name = "用户主键id")
    private Long userId;

    /** 字典:1.网超采购 2.委托招标 */
    @Excel(name = "字典:1.网超采购 2.委托招标")
    private Long procurementMethod;

    /** 字典:1.科研设备类 2.电子信息服务类 3.其他家具类 4.印刷类 */
    @Excel(name = "字典:1.科研设备类 2.电子信息服务类 3.其他家具类 4.印刷类")
    private Long procurementCategory;

    /** 采购内容 */
    @Excel(name = "采购内容")
    private String procurementContent;

    /** 总金额 */
    @Excel(name = "总金额")
    private Long amount;

    /** 合同附件 */
    @Excel(name = "合同附件")
    private String contractAnnex;

    /** 字典:1.待审核 2.审核中 3.审核通过 4.驳回 */
    @Excel(name = "字典:1.待审核 2.审核中 3.审核通过 4.驳回")
    private Long state;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String processInstanceId;

    /** 创建者 */
    @Excel(name = "创建者")
    private String creator;

    /** 更新者 */
    @Excel(name = "更新者")
    private String updater;

    /** 字典:0正常，1删除 */
    private Long delFlag;

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
    public void setProcurementMethod(Long procurementMethod)
    {
        this.procurementMethod = procurementMethod;
    }

    public Long getProcurementMethod()
    {
        return procurementMethod;
    }
    public void setProcurementCategory(Long procurementCategory)
    {
        this.procurementCategory = procurementCategory;
    }

    public Long getProcurementCategory()
    {
        return procurementCategory;
    }
    public void setProcurementContent(String procurementContent)
    {
        this.procurementContent = procurementContent;
    }

    public String getProcurementContent()
    {
        return procurementContent;
    }
    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAmount()
    {
        return amount;
    }
    public void setContractAnnex(String contractAnnex)
    {
        this.contractAnnex = contractAnnex;
    }

    public String getContractAnnex()
    {
        return contractAnnex;
    }
    public void setState(Long states)
    {
        this.state = state;
    }

    public Long getState()
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("procurementMethod", getProcurementMethod())
            .append("procurementCategory", getProcurementCategory())
            .append("procurementContent", getProcurementContent())
            .append("amount", getAmount())
            .append("contractAnnex", getContractAnnex())
            .append("state", getState())
            .append("processInstanceId", getProcessInstanceId())
            .append("createTime", getCreateTime())
            .append("creator", getCreator())
            .append("updateTime", getUpdateTime())
            .append("updater", getUpdater())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
