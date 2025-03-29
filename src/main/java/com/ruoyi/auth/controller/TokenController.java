package com.ruoyi.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.DdUserVo;
import com.ruoyi.system.api.model.SysAuthUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.form.RegisterBody;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.auth.AuthUtil;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;

/**
 * token 控制
 *
 * @author ruoyi
 */
@RestController
public class TokenController
{
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;
    @Autowired
    private RemoteUserService remoteUserService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) throws Exception {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form);

        if(userInfo != null){
            SysAuthUserModel sysAuthUser = userInfo.getSysAuthUser();
            logger.info("sysAuthUser:"+ JSON.toJSONString(sysAuthUser));
            remoteUserService.updateUserDept(sysAuthUser);
        }

        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        DdUserVo ddUserVo = new DdUserVo();
        ddUserVo.setUserName(registerBody.getUsername());
        ddUserVo.setPassword(registerBody.getPassword());
        sysLoginService.register(ddUserVo);
        return R.ok();
    }

}
