package com.ruoyi.job.service;

import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.dingtalk.api.response.OapiV2UserListResponse;
import com.taobao.api.ApiException;

import java.util.List;


public interface DeptService {


    /**
     * 获取钉钉部门列表
     */
    List<OapiV2DepartmentListsubResponse.DeptBaseResponse> getAllDepts() throws ApiException;

    /**
     * 将本地部门列表与钉钉部门列表进行对比，将本地部门列表中未同步的钉钉部门id进行同步
     *
     *
     */
    void UpdateSysDept() throws ApiException;

    /**
     * 获取钉钉部门成员列表
     */
    List<OapiV2UserListResponse.ListUserResponse> departmentUserInfo(Long DeptId) throws ApiException;
    /**
     * 检查钉钉部门信息是否与系统中已存在的部门信息不同
     *
     * @param dingTalkDept 钉钉部门信息
     * @return 如果系统中不存在相同的部门名称，则返回true；否则返回false。
     */
    boolean isChangedDept(OapiV2DepartmentListsubResponse.DeptBaseResponse dingTalkDept);
    /**
     * 检查给定的钉钉部门ID是否存在于系统部门列表中。
     *
     * @param deptId 钉钉部门ID，用于查找对应的系统部门。
     * @return 如果部门存在，则返回true；否则返回false。
     */
     boolean isExistsDept(Long deptId);

    /**
     * 同步本地和钉钉的用户信息
     */
     void UpdateSyUser(Long role_id) throws ApiException;

    /**
     * 获取本地钉钉列表
     */
    List<Long> getAllDeptList();

    /**
     *判断本钉钉用户信息是否与系统中已存在的用户信息不同
     */
    boolean isChangedUsersUsers(OapiV2UserListResponse.ListUserResponse user);
}
