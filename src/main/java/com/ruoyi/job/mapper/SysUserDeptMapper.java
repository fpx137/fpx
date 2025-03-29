package com.ruoyi.job.mapper;

import com.ruoyi.job.domain.SysUserDept;

import java.util.List;

/**
 * 多部门管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-02
 */
public interface SysUserDeptMapper
{
    /**
     * 查询多部门管理
     *
     * @param id 多部门管理主键
     * @return 多部门管理
     */
    public SysUserDept selectSysUserDeptById(Long id);

    /**
     * 查询多部门管理列表
     *
     * @param sysUserDept 多部门管理
     * @return 多部门管理集合
     */
    public List<SysUserDept> selectSysUserDeptList(SysUserDept sysUserDept);

    /**
     * 新增多部门管理
     *
     * @param sysUserDept 多部门管理
     * @return 结果
     */
    public int insertSysUserDept(SysUserDept sysUserDept);

    /**
     * 修改多部门管理
     *
     * @param sysUserDept 多部门管理
     * @return 结果
     */
    public int updateSysUserDept(SysUserDept sysUserDept);

    /**
     * 删除多部门管理
     *
     * @param id 多部门管理主键
     * @return 结果
     */
    public int deleteSysUserDeptById(Long id);

    /**
     * 批量删除多部门管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserDeptByIds(Long[] ids);
}
