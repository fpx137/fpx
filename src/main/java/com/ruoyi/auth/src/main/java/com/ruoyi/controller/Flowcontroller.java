package com.ruoyi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastResponseBody;
import com.dingtalk.api.request.OapiProcessWorkrecordTaskQueryRequest;
import com.ruoyi.DingdingFlowSDk;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Request.*;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Return.*;
import com.ruoyi.system.api.domain.SysFlow.flowrole.request.SimpleListRequest;
import com.ruoyi.system.api.domain.SysFlow.flowrole.returnrole.SimpleListReturn;
import com.ruoyi.system.api.domain.SysFlow.flowschedule.Return.SearchListResult;
import com.ruoyi.system.api.domain.SysFlow.flowtask.File;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Request.HandelTask;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Request.SwapTask;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Return.GainTaskNumberResult;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Return.HandelTaskResult;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Return.SwapTaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flow")
public class Flowcontroller extends BaseController {
    @Autowired
    private DingdingFlowSDk dingdingFlowSDk;


    /**
     * 查询待办
     *
     * @param
     * @param
     * @return
     */
    @RequiresPermissions("flow:searchList")
    @PostMapping("/searchList")
    public SearchListResult searchList(@RequestBody String nextToken, Boolean isDone) throws Exception {
        return dingdingFlowSDk.searchList(nextToken, isDone);

    }

    /**
     * 发起审批实例
     *
     * @param buildApproval 发起审批实例
     * @param
     * @return 结构
     */
//    @RequiresPermissions("flow:instance:buildApproval")
    @PostMapping("/buildApproval")
    public R<BuildApprovalResult> buildApproval(@RequestBody BuildApproval buildApproval) throws Exception {
        BuildApprovalResult result = dingdingFlowSDk.buildInstance(buildApproval.getOriginatorUserId(), buildApproval.getProcessCode(), buildApproval.getDeptid(), buildApproval.getMicroappAgentId(), buildApproval.getCcList(), buildApproval.getCcPosition(), buildApproval.getFormComponentValues(), buildApproval.getTargetSelectActioners());
        return R.ok(result);
    }

    /**
     * 获取单个审批实例详情
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/gainApproval")
    public R<GainApprovalResult> gainApproval(@RequestBody GainApproval gainApproval) throws Exception {
        return R.ok(dingdingFlowSDk.gainInstance(gainApproval.getProessInstanceId()));
    }

    /**
     * 撤销审批实例详情
     *
     * @param
     * @param
     * @return
     */
//    @RequiresPermissions("flow:instance:cancelApproval")
    @PostMapping("/cancelApproval")
    public R<CancelApprovalResult> cancelApproval(@RequestBody CancelApproval cancelApproval) throws Exception {
        return R.ok(dingdingFlowSDk.cancelInstance(cancelApproval.getProessInstanceId(), cancelApproval.getOperatingUserId(), cancelApproval.getSystem(), cancelApproval.getRemark()));
    }

    /**
     * 添加审批评论
     *
     * @param
     * @param
     * @return
     */
    @RequiresPermissions("flow:instance:addApproval")
    @PostMapping("/addApproval")
    public AddApprovalResult addApproval(@RequestBody String processInstanceId, String text, String commentUserId, File fileRes) throws Exception {
//        DingdingFlowSDk.addComment(processInstanceId, text, commentUserId, file);
        return dingdingFlowSDk.addComment(processInstanceId, text, commentUserId, fileRes);
    }

    /**
     * 获取审批实例ID列表
     *
     * @param
     * @param
     * @return
     */
    @RequiresPermissions("flow:instance:gainApprovalId")
    @PostMapping("/gainApprovalId")
    public GainApprovalIdResult gainApprovaId(@RequestBody GainApprovalId gainApprovalId) throws Exception {
//        DingdingFlowSDk.gainExampleList(starTime, endTime,statuses,userids , processCode, deptid);
        return dingdingFlowSDk.gainInstanceList(gainApprovalId.getStartTime(), gainApprovalId.getEndTime(), gainApprovalId.getProcessCode(), gainApprovalId.getNextToken(), gainApprovalId.getMaxResults(), gainApprovalId.getUserids(), gainApprovalId.getStatuses());
    }

    /**
     * 同意或拒绝审批任务
     *
     * @param
     * @param
     * @return
     */
//    @RequiresPermissions("flow:task:handeTask")
    @PostMapping("/handelTask")
    public R<HandelTaskResult> handelTask(@RequestBody HandelTask handelTask) throws Exception {
        logger.info("ProcessInstanceId="+handelTask.getProcessInstanceId()+",TaskId="+handelTask.getTaskId()+",ActionerUserId="+handelTask.getActionerUserId()+",Result="+handelTask.getResult()+",Remark="+handelTask.getRemark());
        HandelTaskResult handelTaskResult =  dingdingFlowSDk.handelInstance(handelTask.getProcessInstanceId(), handelTask.getTaskId(), handelTask.getActionerUserId(), handelTask.getResult(), handelTask.getRemark(), handelTask.getFile());
        System.out.println("远程======="+ JSON.toJSONString(handelTaskResult));
//        return handelTaskResult;
        return R.ok(handelTaskResult);
    }

    /**
     * 转交OA审批任务
     *
     * @param
     * @param
     * @return
     */
    @RequiresPermissions("flow:task:swapTask")
    @PostMapping("/swapTask")
    public SwapTaskResult swapTask(@RequestBody SwapTask swapTask) throws Exception {
//        DingdingFlowSDk.swapExample(toUserId, taskId, operateUserId, actionName, remark, file);
        return dingdingFlowSDk.swapInstance(swapTask.getToUserId(), swapTask.getTaskId(), swapTask.getOperateUserId(), swapTask.getActionName(), swapTask.getRemark(), swapTask.getFile());
    }

    /**
     * 获取用户待审批数量
     *
     * @param
     * @param
     * @return
     */
    @RequiresPermissions("flow:task:gainTaskNumber")
    @PostMapping("/gainTaskNumber")
    public GainTaskNumberResult gainTaskNumber(@RequestBody String userId) throws Exception {
        return dingdingFlowSDk.gainInstanceNumber(userId);
    }

    /**
     * 获取用户待审批列表
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/gainInstanceList")
    public R<GainInstanceListResult> gainInstanceList(@RequestBody GainInstanceList gainInstanceList) throws Exception {
        return R.ok(dingdingFlowSDk.gainInstanceList(gainInstanceList.getUserId(), gainInstanceList.getOffset(), gainInstanceList.getCount(), gainInstanceList.getStatus()));
    }

    /**
     * 下载审批附件
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/downloadAttachment")
    public R<DownloadAttachmentResult> downloadAttachment(@RequestBody DownloadAttachment downloadAttachment) throws Exception {
        return R.ok(dingdingFlowSDk.downloadAttachment(downloadAttachment.getFileId(), downloadAttachment.getProcessInstanceId()));
    }

    /**
     * 预测审批流程
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/getNode")
    public R<ProcessForecastResponseBody.ProcessForecastResponseBodyResult> ForecastingProcessthrows(@RequestBody ApprovalNode approvalNode) throws Exception {

        return R.ok(dingdingFlowSDk.ForecastingProcessthrows(approvalNode.getProcessCode(), approvalNode.getDeptId(), approvalNode.getUserId(), approvalNode.getFormComponentValues()));
    }

    /*
     * 获取盯盘空间id
     */
    @PostMapping("/getSpaceId")
    public R<Long> getSpaceId(@RequestBody String userId) throws Exception {
        return R.ok(dingdingFlowSDk.getSpaceId(userId));
    }
    /*
     * 获取上传文件信息
     */
    @PostMapping("/getUploadInformation")
    public R<UploadInformationResult> getUploadInformation(@RequestBody UploadInformation uploadInformation) throws Exception {
        return R.ok(dingdingFlowSDk.getUploadInformation(uploadInformation.getSpaceId(), uploadInformation.getUnionId()));
    }
    @PostMapping("/uploadFile")
    public R<uploadFileResult> uploadFile(@RequestBody UploadFile uploadFile) throws Exception {
        return R.ok(dingdingFlowSDk.uploadFile(uploadFile.getUnionId(), uploadFile.getUploadKey(), uploadFile.getSpaceId(), uploadFile.getFileName()));
    }

    // 获取部门列表
    @PostMapping("/getDeptList")
    public R<List<Long>> getDeptList(@RequestBody String userid) throws Exception{
        return R.ok(dingdingFlowSDk.getDeptList(userid));
    }

    // 获取指定角色的员工列表
    @PostMapping("/simpleList/getRoleUsersList")
    public R<List<SimpleListReturn>> simpleList(@RequestBody SimpleListRequest simpleListRequest) throws Exception{
        return R.ok(dingdingFlowSDk.simplelist(simpleListRequest));
    }
}
