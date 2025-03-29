package com.ruoyi.system.api.factory;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastResponseBody;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteFlowService;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Request.*;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Return.*;
import com.ruoyi.system.api.domain.SysFlow.flowrole.request.SimpleListRequest;
import com.ruoyi.system.api.domain.SysFlow.flowrole.returnrole.SimpleListReturn;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Request.HandelTask;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Return.HandelTaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 工作流服务降级处理
 *
 * @author ruoyi
 */
@Component
public class RemoteFlowFallbackFactory implements FallbackFactory<RemoteFlowService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteFlowFallbackFactory.class);

    @Override
    public RemoteFlowService create(Throwable throwable)
    {
        log.error("工作流调用失败:{}", throwable.getMessage());
        return new RemoteFlowService()
        {
            @Override
            public R<BuildApprovalResult> starApproval(BuildApproval buildApproval)
            {
                return R.fail("发起审批失败:" + throwable.getMessage());
            }

            @Override
            public R<CancelApprovalResult> revokeApproval(CancelApproval cancelApproval)
            {
                return R.fail("撤销审批失败:" + throwable.getMessage());
            }

            @Override
            public R<GainApprovalResult> detailsApproval(GainApproval gainApproval)
            {
                return R.fail("获取审批失败:" + throwable.getMessage());
            }

            @Override
            public R<GainApprovalIdResult> gainApprovalList(GainApprovalId gainApprovalId)
            {
                return R.fail("获取审批列表失败:" + throwable.getMessage());
            }

            @Override
            public R<GainInstanceListResult> gainInstanceList(GainInstanceList gainInstanceList)
            {
                return R.fail("获取代办列表失败:" + throwable.getMessage());
            }

            @Override
            public R<HandelTaskResult> handelTask(HandelTask handelTask)
            {
                return R.fail("审批失败:" + throwable.getMessage());
            }

            @Override
            public R<DownloadAttachmentResult> downloadAttachment(DownloadAttachment downloadAttachment)
            {
                return R.fail("下载附件失败:" + throwable.getMessage());
            }

            @Override
            public R<ProcessForecastResponseBody.ProcessForecastResponseBodyResult> ForecastingProcessthrows(ApprovalNode approvalNode){
                return R.fail("流程预测失败:" + throwable.getMessage());
            }
            @Override
            public R<Long> getSpaceId( String userId) throws Exception {
                return R.fail("获取盯盘空间id失败:" + throwable.getMessage());
            }
            @Override
            public R<UploadInformationResult> getUploadInformation(UploadInformation uploadInformation) throws Exception {
                return R.fail("获取上传信息失败:" + throwable.getMessage());
            }
            @Override
            public R<uploadFileResult> uploadFile(UploadFile uploadFile) throws Exception{
                return R.fail("上传文件失败:" + throwable.getMessage());
            }
            @Override
            public R<List<Long>> getDeptList(String userid) throws Exception {
                return R.fail("获取部门列表失败:" + throwable.getMessage());
            }
            @Override
            public R<List<SimpleListReturn>> getRoleUsersList(SimpleListRequest simpleListRequest) throws Exception {
                return R.fail("获取指定角色的员工列表失败:" + throwable.getMessage());
            }
        };
    }
}
