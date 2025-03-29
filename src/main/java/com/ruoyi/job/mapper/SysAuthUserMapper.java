package com.ruoyi.job.mapper;

import com.ruoyi.job.domain.SysAuthUser;

import java.util.List;


/**
 * 第三方授权Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-14
 */
public interface SysAuthUserMapper
{
    /**
     * 查询第三方授权
     *
     * @param id 第三方授权主键
     * @return 第三方授权
     */
    public SysAuthUser selectSysAuthUserById(Long id);

    /**
     * 查询第三方授权列表
     *
     * @param sysAuthUser 第三方授权
     * @return 第三方授权集合
     */
    public List<SysAuthUser> selectSysAuthUserList(SysAuthUser sysAuthUser);

    /**
     * 新增第三方授权
     *
     * @param sysAuthUser 第三方授权
     * @return 结果
     */
    public int insertSysAuthUser(SysAuthUser sysAuthUser);

    /**
     * 修改第三方授权
     *
     * @param sysAuthUser 第三方授权
     * @return 结果
     */
    public int updateSysAuthUser(SysAuthUser sysAuthUser);

    /**
     * 删除第三方授权
     *
     * @param id 第三方授权主键
     * @return 结果
     */
    public int deleteSysAuthUserById(Long id);

    /**
     * 批量删除第三方授权
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAuthUserByIds(Long[] ids);
}
