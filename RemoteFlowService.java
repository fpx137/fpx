package com.ruoyi.system.api;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastResponseBody;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Request.*;

import com.ruoyi.system.api.domain.SysFlow.flowexample.Return.*;
import com.ruoyi.system.api.domain.SysFlow.flowrole.request.SimpleListRequest;
import com.ruoyi.system.api.domain.SysFlow.flowrole.returnrole.SimpleListReturn;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Request.HandelTask;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Return.HandelTaskResult;
import com.ruoyi.system.api.factory.RemoteFlowFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.List;

/**
 * 文件服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteFlowService", value = ServiceNameConstants.FLOW_SERVICE, fallbackFactory = RemoteFlowFallbackFactory.class)
public interface RemoteFlowService
{
    /**
     * 发起审批实例
     *
     * @param buildApproval 发起审批
     * @return 结果
     */
    @PostMapping(value = "/flow/buildApproval")
    public R<BuildApprovalResult> starApproval(@RequestBody BuildApproval buildApproval);

    /**
     * 撤销审批实例详情
     * @param
     * @param
     * @return
     */
    @PostMapping("/flow/cancelApproval")
    public R<CancelApprovalResult> revokeApproval(@RequestBody CancelApproval cancelApproval) ;

    /**
     * 获取单个审批实例详情
     * @param
     * @param
     * @return
     */
    @PostMapping("/flow/gainApproval")
    public R<GainApprovalResult> detailsApproval(@RequestBody GainApproval gainApproval) ;

    /**
     * 获取审批实例列表
     * @param
     * @param
     * @return
     */
    @PostMapping("/flow/gainApprovalId")
    public R<GainApprovalIdResult> gainApprovalList(@RequestBody GainApprovalId gainApprovalId) ;

    /**
     * 获取用户待审批列表
     * @param
     * @param
     * @return
     */
    @PostMapping("/flow/gainInstanceList")
    public R<GainInstanceListResult> gainInstanceList(@RequestBody GainInstanceList gainInstanceList) ;

    /**
     * 同意或拒绝审批任务
     * @param
     * @param
     * @return
     */
    @PostMapping("/flow/handelTask")
    public R<HandelTaskResult> handelTask(@RequestBody HandelTask handelTask) ;

    /**
     * 下载审批附件
     * @param
     * @param
     * @return
     */
    @PostMapping("/flow/downloadAttachment")
    public R<DownloadAttachmentResult> downloadAttachment(@RequestBody DownloadAttachment downloadAttachment);
    /*
    * 获取审批节点信息
     */
    @PostMapping("/flow/getNode")
    public R<ProcessForecastResponseBody.ProcessForecastResponseBodyResult> ForecastingProcessthrows(@RequestBody ApprovalNode approvalNode);
    /*
    * 获取盯盘空间id
     */
    @PostMapping("/flow/getSpaceId")
    public R<Long> getSpaceId(@RequestBody String userId) throws Exception ;
    /*
    * 获取盯盘上传信息
     */
    @PostMapping("/flow/getUploadInformation")
    public R<UploadInformationResult> getUploadInformation(@RequestBody UploadInformation uploadInformation) throws Exception ;
    /*
    * 上传文件
     */
    @PostMapping("/flow/uploadFile")
    public   R<uploadFileResult> uploadFile(@RequestBody UploadFile uploadFile) throws Exception;

    /*
     * 获取部门列表
     */
    @PostMapping("/flow/getDeptList")
    public R<List<Long>> getDeptList(@RequestBody String userid) throws Exception;

    @PostMapping("/flow/simpleList/getRoleUsersList")
    public R<List<SimpleListReturn>> getRoleUsersList(@RequestBody SimpleListRequest simpleListRequest) throws Exception;


}
