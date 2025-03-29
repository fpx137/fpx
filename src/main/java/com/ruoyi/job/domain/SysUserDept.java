package com.ruoyi.job.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 多部门管理对象 sys_user_dept
 *
 * @author ruoyi
 * @date 2024-09-02
 */
public class SysUserDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;
    /** 是否是默认科室*/
    private Long isDefault;

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
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public Long getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Long isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "SysUserDept{" +
                "id=" + id +
                ", userId=" + userId +
                ", deptId=" + deptId +
                ", isDefault=" + isDefault +
                '}';
    }
}
