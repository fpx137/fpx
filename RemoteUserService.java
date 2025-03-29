package com.ruoyi.system.api;

import com.ruoyi.system.api.domain.DdUserVo;
import com.ruoyi.system.api.model.SysAuthUserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.factory.RemoteUserFallbackFactory;
import com.ruoyi.system.api.model.LoginUser;

import java.util.List;

/**
 * 用户服务
 *
 * @author ruoyi
 */
@Service
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody DdUserVo sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 获取部门ID
     *
     * @param username 用户名称
     * @return 结果
     */
    @PostMapping("/user/deptID")
    public String gainDeptID(@RequestBody String username);
    /**
     * 新增用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/insertRoleUser")
    public R<SysUser> insertRoleUser(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 删除用户信息
     *
     * @param userId 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @DeleteMapping ("/user/removeUser")
    public R<SysUser> deleteRoleUser(@RequestParam("userId") Long userId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 修改用户状态信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PutMapping("/user/changeStatus")
    public R<SysUser> updateStatusRoleUser(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 修改用户密码
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PutMapping("/user/resetPwd")
    public R<SysUser> resetPwd(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    /**
     * 校验扫码用户唯一
     * @param ddUserVo
     * @param source
     * @return
     */
    @PostMapping("/user/registrationCheck")
    public Boolean registrationCheck(@RequestBody DdUserVo ddUserVo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PostMapping("/user/saveSysAuthUser")
    public Boolean saveSysAuthUser(@RequestBody SysAuthUserModel sysAuthUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
    @PostMapping("/user/getUserDeptList")
    public R<List<Long>> selectUserDeptList(@RequestParam("userId") Long userId);
    /**
     * 更新用户部门信息
     */
    @PostMapping("/user/updateUserDept")
    public R<Boolean> updateUserDept(@RequestBody SysAuthUserModel sysAuthUser) throws Exception;
}
