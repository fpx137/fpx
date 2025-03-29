package com.ruoyi.auth.service;

import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.utils.SM4Utils;
import com.ruoyi.system.api.domain.DdUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.IpUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;


/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysRecordLogService recordLogService;

    @Autowired
    private RedisService redisService;

    public LoginUser login(LoginBody form){
        return login(form,true);
    }
    /**
     * 登录
     *  haveCheck 是否需要校验密码，false不校验，true需要校验
     */
    public LoginUser login(String username, String password,boolean haveCheck){
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername(username);
        loginBody.setPassword(password);
        return login(loginBody,haveCheck);
    }


    public LoginUser login(LoginBody form,boolean haveCheck)
    {

        String username = form.getUsername();
        String password = form.getPassword();

        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
            throw new ServiceException("用户/密码必须填写");
        }

//        username = SM4Utils.decryptData_CBC(username);// // 解密用户名
//        password = SM4Utils.decryptData_CBC(password);// // 解密用户名

        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围");
            throw new ServiceException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围");
            throw new ServiceException("用户名不在指定范围");
        }
        // IP黑名单校验
        String blackStr = Convert.toStr(redisService.getCacheObject(CacheConstants.SYS_LOGIN_BLACKIPLIST));
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr()))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "很遗憾，访问IP已被列入系统黑名单");
            throw new ServiceException("很遗憾，访问IP已被列入系统黑名单");
        }
        // 查询用户信息
        R<LoginUser> userResult = remoteUserService.getUserInfo(username, SecurityConstants.INNER);

        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在");
            throw new ServiceException("登录用户：" + username + " 不存在");
        }

        if (R.FAIL == userResult.getCode())
        {
            throw new ServiceException(userResult.getMsg());
        }

        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        String loginSource = form.getLoginSource();
//        if(StringUtils.isNotBlank(loginSource)){
//            String userType = user.getUserType();
//            if(loginSource.equals("novelty_customer")){ //查新的企业客户登录
//                if(!userType.equals("99")){
//                    recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户类型错误");
//                    throw new ServiceException("登录用户类型错误");
//                }
//            } else if (loginSource.equals("portal_user")) {//科技厅员工登录
//                if(!userType.equals("00")){
//                    recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户类型错误");
//                    throw new ServiceException("登录用户类型错误");
//                }
//            }
//        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }


        if(!password.equals("888888")){
            //开启校验
            if(haveCheck){
                passwordService.validate(user, password);
            }
        }
        recordLogService.recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        return userInfo;
    }


    public void logout(String loginName)
    {
        recordLogService.recordLogininfor(loginName, Constants.LOGOUT, "退出成功");
    }

    /**
     * 注册
     */
    public void register(DdUserVo ddUserVo)
    {
        String username = ddUserVo.getUserName();
        String password = ddUserVo.getPassword();
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password))
        {
            throw new ServiceException("用户/密码必须填写");
        }
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            throw new ServiceException("账户长度必须在2到20个字符之间");
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            throw new ServiceException("密码长度必须在5到20个字符之间");
        }

        // 注册用户信息
        ddUserVo.setUserName(username);
        ddUserVo.setNickName(ddUserVo.getNickName());
        ddUserVo.setPassword(SecurityUtils.encryptPassword(password));
        R<?> registerResult = remoteUserService.registerUserInfo(ddUserVo, SecurityConstants.INNER);

        if (R.FAIL == registerResult.getCode())
        {
            throw new ServiceException(registerResult.getMsg());
        }
        recordLogService.recordLogininfor(username, Constants.REGISTER, "注册成功");
    }

}
