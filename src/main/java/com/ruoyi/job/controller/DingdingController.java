package com.ruoyi.job.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.job.service.DeptService;
import com.ruoyi.job.service.DingDingService;
import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Component("task")
@Api(tags = {"定时任务"})
@RequestMapping("/task")
public class DingdingController extends BaseController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private DingDingService dingdingService;
    /**
     * 更新部门列表
     */
    @PostMapping("/updateContacts")
    public AjaxResult updateContacts() throws ApiException {
        //同步部门
        deptService.UpdateSysDept();
        //同步用户和用户角色 参数角色id
        deptService.UpdateSyUser(2L);
        return success("同步钉钉通讯录成功");
    }
    /**
     * 更新状态列表
     */

    public void updateApproval() throws ApiException {
        //更新状态
        dingdingService.updateStatus();
    }

    /**
     * 更新手机发起审批列表
     */
    public void updateApprovalList() throws Exception {
        //更新状态
        dingdingService.updateApprovalList();
    }

    /**
     * 更新采购进程
     */

    public AjaxResult updateProcurement() throws ApiException {

        logger.info("采购进度追踪");
        return  toAjax(dingdingService.updateProgressTracking());

    }



}
