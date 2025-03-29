package com.ruoyi.job.mapper;

import com.ruoyi.job.domain.SysDept;

import java.util.List;

/**
 * 部门Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-09
 */
public interface SysDeptMapper
{
    /**
     * 查询部门
     *
     * @param deptId 部门主键
     * @return 部门
     */
    public SysDept selectSysDeptByDeptId(Long deptId);

    /**
     * 根据钉钉部门id查找本地部门信息
     *
     * @param dingTalkDeptId 部门主键
     * @return 部门
     */
    public SysDept selectSysDeptByDingTalkDeptId(Long dingTalkDeptId);
    /**
     * 查询部门列表
     *
     *
     * @return 部门集合
     */
    public List<SysDept> selectSysDeptList();

    /**
     * 新增部门
     *
     * @param sysDept 部门
     * @return 结果
     */
    public int insertSysDept(SysDept sysDept);

    /**
     * 修改部门
     *
     * @param sysDept 部门
     * @return 结果
     */
    public int updateSysDept(SysDept sysDept);

    /**
     * 删除部门
     *
     * @param deptId 部门主键
     * @return 结果
     */
    public int deleteSysDeptByDeptId(Long deptId);

    /**
     * 批量删除部门
     *
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDeptByDeptIds(Long[] deptIds);
}
