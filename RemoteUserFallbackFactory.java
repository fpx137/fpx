package com.ruoyi.system.api.factory;

import com.ruoyi.system.api.domain.DdUserVo;
import com.ruoyi.system.api.model.SysAuthUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户服务降级处理
 *
 * @author ruoyi
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<LoginUser> getUserInfo(String username, String source)
            {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> registerUserInfo(DdUserVo sysUser, String source)
            {
                return R.fail("注册用户失败:" + throwable.getMessage());
            }

            @Override
            public String gainDeptID(String username)
            {
                return "获取部门ID失败"+throwable.getMessage();
            }

            @Override
            public R<SysUser> insertRoleUser(SysUser sysUser, String source)
            {
                return R.fail("新增失败:" + throwable.getMessage());
            }

            @Override
            public R<SysUser> deleteRoleUser(Long userId, String source)
            {
                return R.fail("删除失败:" + throwable.getMessage());
            }

            @Override
            public R<SysUser> updateStatusRoleUser(SysUser sysUser, String source)
            {
                return R.fail("状态更改失败:" + throwable.getMessage());
            }

            @Override
            public R<SysUser> resetPwd(SysUser sysUser, String source)
            {
                return R.fail("重置密码失败:" + throwable.getMessage());
            }

            @Override
            public Boolean registrationCheck(DdUserVo ddUserVo, String source) {
                return false;
            }

            @Override
            public Boolean saveSysAuthUser(SysAuthUserModel ddUserVo, String source) {
                return false;
            }

            @Override
            public R<List<Long>> selectUserDeptList(Long userId){
                return R.fail("获取用户列表失败："+throwable.getMessage());
            };

            @Override
            public R<Boolean> updateUserDept(@RequestBody SysAuthUserModel sysAuthUser) throws Exception {
                return R.fail("更新用户部门失败:" + throwable.getMessage());
            }
        };
    }
}
