package com.ruoyi.job.service.Impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;

import com.dingtalk.api.request.OapiV2DepartmentListsubRequest;
import com.dingtalk.api.request.OapiV2UserListRequest;

import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.dingtalk.api.response.OapiV2UserListResponse;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.job.config.DingdingConfig;
import com.ruoyi.job.domain.*;

import com.ruoyi.job.mapper.*;

import com.ruoyi.job.service.DeptService;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author kano
 * @date 2023/10/23
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysAuthUserMapper sysAuthUserMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private DingdingConfig dingdingConfig;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysUserDeptMapper sysUserDeptMapper;
    /**
     * 获取钉钉部门列表
     */
    @Override
    public List<OapiV2DepartmentListsubResponse.DeptBaseResponse> getAllDepts() throws ApiException {
        String access_token = dingdingConfig.getToken();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsub");
        OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
        req.setDeptId(1L);
        req.setLanguage("zh_CN");
        OapiV2DepartmentListsubResponse rsp = client.execute(req, access_token);
        return rsp.getResult();
    }

    /**
     * 获取钉钉部门用户列表
     */
    public  List<OapiV2UserListResponse.ListUserResponse> departmentUserInfo(Long DeptId) throws ApiException {
        String access_token = dingdingConfig.getToken();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/list");
        OapiV2UserListRequest req = new OapiV2UserListRequest();
        req.setDeptId(DeptId);
        req.setCursor(0L);
        req.setSize(10L);
        req.setContainAccessLimit(true);
        req.setLanguage("zh_CN");
        OapiV2UserListResponse rsp = client.execute(req, access_token);
        if (rsp.getResult().getList().isEmpty()){
          return new ArrayList<>();
        }
        return  rsp.getResult().getList();
    }


    /**
     * 将本地部门列表与钉钉部门列表进行对比，将本地部门列表中未同步的钉钉部门id进行同步
     */
    @Override
    public void UpdateSysDept() throws ApiException {
        String username = SecurityUtils.getUsername();// 获取当前用户ID
        //获取钉钉部门列表
        List<OapiV2DepartmentListsubResponse.DeptBaseResponse> DingTalklist = getAllDepts();

        // 遍历钉钉部门列表，针对每个部门检查其在系统中是否存在以及是否需要更新
        for (OapiV2DepartmentListsubResponse.DeptBaseResponse dingTalkDept : DingTalklist) {
            // 检查部门是否已存在于系统中
            if (isExistsDept(dingTalkDept.getDeptId())) {
                // 检查部门信息是否发生变化，如果发生变化，则更新系统中的部门信息
                if (isChangedDept(dingTalkDept)) {
                    SysDept dept = sysDeptMapper.selectSysDeptByDingTalkDeptId(dingTalkDept.getDeptId());
                    dept.setDeptName(dingTalkDept.getName());
                    dept.setUpdateBy(username);
                    dept.setUpdateTime(new Date());
                    // 在系统中更新部门信息
                    sysDeptMapper.updateSysDept(dept);
                }
            } else {
                // 部门在系统中不存在，创建新的部门记录
                SysDept dept = new SysDept();
                dept.setDingTalkDeptId(dingTalkDept.getDeptId());
                dept.setParentId(100L);
                dept.setAncestors("0,100");
                dept.setCreateBy(username);
                dept.setDeptName(dingTalkDept.getName());
                dept.setCreateTime(new Date());
                sysDeptMapper.insertSysDept(dept);
            }
        }

        // 遍历本地部门列表，针对每个部门检查其是否需要停用
        List<SysDept> SysDeptList = sysDeptMapper.selectSysDeptList();
        for (SysDept dept : SysDeptList) {
            // 检查部门是否在钉钉部门列表中存在 不存在staus设置为1(停用) 存在就设置状态为0(正常)，如果删除的就设置0(正常)
           if (dept.getDingTalkDeptId()!=null)
            if (!isExistsDept(dept.getDingTalkDeptId())) {
                dept.setStatus("1");
                dept.setUpdateBy(username);
                sysDeptMapper.updateSysDept(dept);
            } else {
                dept.setStatus("0");
                dept.setDelFlag("0");
                dept.setUpdateBy(username);
                sysDeptMapper.updateSysDept(dept);
            }
        }
    }


    /**
     * 检查给定的钉钉部门ID是否存在于系统部门列表中。
     *
     * @param deptId 钉钉部门ID，用于查找对应的系统部门。
     * @return 如果部门存在，则返回true；否则返回false。
     */
    public boolean isExistsDept(Long deptId) {
        //获取本地部门列表
        List<SysDept> SysDeptList = sysDeptMapper.selectSysDeptList();
        for (SysDept dept : SysDeptList) {
            if (dept.getDingTalkDeptId() != null && dept.getDingTalkDeptId().equals(deptId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查钉钉部门信息是否与系统中已存在的部门信息不同
     *
     * @param dingTalkDept 钉钉部门信息
     * @return 如果系统中不存在相同的部门名称，则返回true；否则返回false。
     */
    public boolean isChangedDept(OapiV2DepartmentListsubResponse.DeptBaseResponse dingTalkDept) {
        //根据钉钉部门id 获取本地部门信息
        SysDept dept = sysDeptMapper.selectSysDeptByDingTalkDeptId(dingTalkDept.getDeptId());
        return !dept.getDeptName().equals(dingTalkDept.getName());
    }


    /**
     * 获取本地钉钉列表
     */
    public List<Long> getAllDeptList(){
        List<SysDept> sysDeptList = sysDeptMapper.selectSysDeptList();
        List<Long> deptList = new ArrayList<>();
        for (SysDept sysDept : sysDeptList) {
            if (sysDept.getDingTalkDeptId() != null) {
                deptList.add(sysDept.getDingTalkDeptId());
            }
        }
        return deptList;
    }
    /**
     * 同步本地和钉钉的用户信息
     */
    public void UpdateSyUser(Long role_id) throws ApiException {
        String username = SecurityUtils.getUsername();// 获取当前用户ID
        List<Long> deptList = new ArrayList<>();
        //添加根部门1L
        //添加子部门列表
        deptList.addAll(getAllDeptList());
        for (Long deptId : deptList) {
                List<OapiV2UserListResponse.ListUserResponse> departmentUserInfo = departmentUserInfo(deptId);
                if (departmentUserInfo == null){
                    continue;
                }
                for (OapiV2UserListResponse.ListUserResponse user : departmentUserInfo){
                    //判断本地有没有用户数据 没有就插入 有判断有没有用户信息更新
                    if (!isExistsUsers(user.getUserid())){
                        SysUser sysUser = new SysUser();
                        sysUser.setEmail(user.getEmail());
                        sysUser.setUserName(user.getMobile());
                        sysUser.setNickName(user.getName());
                        sysUser.setUserType("00");
                        sysUser.setPhonenumber(user.getMobile());
                        sysUser.setRemark(user.getRemark());
                        sysUser.setDelFlag("0");
                        sysUser.setStatus("0");
                        sysUser.setPassword(SecurityUtils.encryptPassword(("admin")));
                        sysUser.setCreateTime(new Date());
                        sysUser.setCreateBy(username);
                        int i = sysUserMapper.insertSysUser(sysUser);
                        if (i>0){
                            //插入用户授权信息
                            SysAuthUser sysAuthUser = new SysAuthUser();
                            sysAuthUser.setDdUserId(user.getUserid());
                            sysAuthUser.setUserId(sysUser.getUserId());
                            sysAuthUser.setUnionId(user.getUnionid());
                            sysAuthUserMapper.insertSysAuthUser(sysAuthUser);
                            //插入用户角色信息
                            SysUserRole sysUserRole = new SysUserRole();
                            sysUserRole.setRoleId(role_id);
                            sysUserRole.setUserId(sysUser.getUserId());
                            sysUserRoleMapper.insertSysUserRole(sysUserRole);
                            //插入用户部门信息
                            List<Long> deptIdList =    user.getDeptIdList();
                            //判断是否是默认部门
                            boolean isFirst = true;
                            //判断用户部门列表是否为空,非空进行插入用户与部门关系表
                            if(!deptIdList.isEmpty()) {
                                for (Long userDeptId : deptIdList) {
                                    SysDept sysDept = sysDeptMapper.selectSysDeptByDingTalkDeptId(userDeptId);
                                    SysUserDept sysUserDept = new SysUserDept();
                                    if (userDeptId==1L){
                                        sysUserDept.setDeptId(100L);
                                    }else {
                                        sysUserDept.setDeptId(sysDept.getDeptId());
                                    }
                                    sysUserDept.setUserId(sysUser.getUserId());
                                    if (isFirst) {
                                        sysUserDept.setIsDefault(0L);
                                        isFirst = false;
                                    } else {
                                        sysUserDept.setIsDefault(1L);
                                    }
                                    sysUserDeptMapper.insertSysUserDept(sysUserDept);
                                }
                            }
                        }
                    }else  {
                        //对本地数据进行更新
                        if (isChangedUsersUsers(user)){
                            SysUser sysUser = sysUserMapper.selectSysUserBydingTalkUserId(user.getUserid());
                            if (sysUser!=null){
                                String staues = user.getActive() ? "0" : "1";
                                sysUser.setEmail(user.getEmail());
                                sysUser.setNickName(user.getName());
                                sysUser.setPhonenumber(user.getMobile());
                                sysUser.setRemark(user.getRemark());
                                sysUser.setStatus(staues);
                                sysUser.setUpdateBy(username);
                                sysUser.setUpdateTime(new Date());
                                sysUserMapper.updateSysUser(sysUser);
                            }
                        }
                    }
                }
        }
    }

    /**
     *判断本地有没有用户数据
     */
    public boolean isExistsUsers(String dingTalkUserId) {
        //获取本地部门列表
        SysAuthUser sysAuthUser = new SysAuthUser();
        sysAuthUser.setDdUserId(dingTalkUserId);
        List<SysAuthUser> SysAuthUse = sysAuthUserMapper.selectSysAuthUserList(sysAuthUser);
        if (!SysAuthUse.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     *判断本钉钉用户信息是否与系统中已存在的用户信息不同
     */
    public boolean isChangedUsersUsers(OapiV2UserListResponse.ListUserResponse user) {
        //获取本地用户信息
        String staues = user.getActive() ? "0" : "1";

        SysUser sysUser = sysUserMapper.selectSysUserBydingTalkUserId(user.getUserid());
        if (sysUser != null&& sysUser.getDeptId()!=null){
            SysDept dept = sysDeptMapper.selectSysDeptByDingTalkDeptId(sysUser.getDeptId());
            if (dept!=null) {
                // 使用Objects.equals进行安全的空值检查比较
                // 对sysUser和user的各属性进行比较，Objects.equals能优雅地处理null值
                return !Objects.equals(sysUser.getEmail(), user.getEmail())
                        || !Objects.equals(sysUser.getPhonenumber(), user.getMobile())
                        || !Objects.equals(sysUser.getRemark(), user.getRemark())
                        || !Objects.equals(sysUser.getStatus(), staues)
                        || !Objects.equals(sysUser.getNickName(), user.getName());
            }
        }
        return true;
    }

}

