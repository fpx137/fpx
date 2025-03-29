package com.ruoyi;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.aliyun.dingtalkstorage_1_0.models.CommitFileResponseBody;
import com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoResponseBody;
import com.aliyun.dingtalktodo_1_0.models.QueryOrgTodoTasksHeaders;
import com.aliyun.dingtalktodo_1_0.models.QueryOrgTodoTasksRequest;
import com.aliyun.dingtalktodo_1_0.models.QueryOrgTodoTasksResponse;
import com.aliyun.dingtalktodo_1_0.models.QueryOrgTodoTasksResponseBody;
import com.aliyun.dingtalkworkflow_1_0.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.ruoyi.config.DingTalkConfig;
import com.ruoyi.config.FlowConfig;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Details;
import com.ruoyi.system.api.domain.SysFlow.flowexample.FormComponentValues;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Return.*;
import com.ruoyi.system.api.domain.SysFlow.flowexample.TargetSelectActioners;
import com.ruoyi.system.api.domain.SysFlow.flowrole.request.SimpleListRequest;
import com.ruoyi.system.api.domain.SysFlow.flowrole.returnrole.SimpleListReturn;
import com.ruoyi.system.api.domain.SysFlow.flowschedule.Return.*;
import com.ruoyi.system.api.domain.SysFlow.flowschedule.TodoCards;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Attachments;
import com.ruoyi.system.api.domain.SysFlow.flowtask.File;
import com.ruoyi.system.api.domain.SysFlow.flowtask.Return.*;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * @author 董业晟
 */
@Primary   //默认使用本地存储
@Component
public class DingdingFlowSDk {
    private static final Logger logger = LoggerFactory.getLogger(DingdingFlowSDk.class);
    public static FlowConfig flowConfig;
    @Autowired
    public DingTalkConfig dingTalkConfig;

//    public static void main(String[] args_) throws Exception {
//        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
//        config.protocol = "https";
//        config.regionId = "central";
//        com.aliyun.dingtalkoauth2_1_0.Client client = FlowConfig.createClient();
//        com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest getAccessTokenRequest = new com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest()
//                .setAppKey("dingeqqpkv3xxxxxx")
//                .setAppSecret("GT-lsu-taDAxxxsTsxxxx");
//        try {
//            client.getAccessToken(getAccessTokenRequest);
//        } catch (TeaException err) {
//            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
//                // err 中含有 code 和 message 属性，可帮助开发定位问题
//            }
//
//        } catch (Exception _err) {
//            TeaException err = new TeaException(_err.getMessage(), _err);
//            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
//                // err 中含有 code 和 message 属性，可帮助开发定位问题
//            }
//
//        }
//    }

    /**
     * 获取AccessToken
     */
    public String getAccessToken() throws Exception {
        com.aliyun.dingtalkoauth2_1_0.Client client = FlowConfig.createClientAsstoken();
        com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest getAccessTokenRequest = new com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest()
                .setAppKey(dingTalkConfig.getAppKey())
                .setAppSecret(dingTalkConfig.getAppSecret());
        try {
            return client.getAccessToken(getAccessTokenRequest).getBody().getAccessToken();
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.getMessage(), err);
                throw new RuntimeException("err! code" + err.getCode() + ",message" + err.getMessage());
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(_err.getMessage(), _err);
                throw new RuntimeException("err! code" + err.getCode() + ",message" + err.getMessage());
            }

        }
        return null;
    }


    public SearchListResult searchList(String nextToken, Boolean isDone) throws Exception {
        com.aliyun.dingtalktodo_1_0.Client client = FlowConfig.createClientTodo();
        QueryOrgTodoTasksHeaders queryOrgTodoTasksHeaders = new QueryOrgTodoTasksHeaders();
        queryOrgTodoTasksHeaders.xAcsDingtalkAccessToken = getAccessToken();

        QueryOrgTodoTasksRequest queryOrgTodoTasksRequest = new QueryOrgTodoTasksRequest()
                .setNextToken(nextToken)
                .setIsDone(isDone);
        try {
            QueryOrgTodoTasksResponse res = client.queryOrgTodoTasksWithOptions("B4PNzgzWPzN3uNTJ7DZ0EgiEiE", queryOrgTodoTasksRequest, queryOrgTodoTasksHeaders, new RuntimeOptions());
            if (res.getStatusCode() == 200) {
                // 处理业务逻辑
                SearchListResult searchListResult = new SearchListResult();
                searchListResult.setNextToken(res.getBody().nextToken);
                List<TodoCards> todoCards = new ArrayList<>();
                for (QueryOrgTodoTasksResponseBody.QueryOrgTodoTasksResponseBodyTodoCards todoCardList : res.getBody().todoCards) {
                    TodoCards tc = new TodoCards();
                    tc.setCreatedTime(todoCardList.getCreatedTime());
                    tc.setTaskId(todoCardList.getTaskId());
                    tc.setSubject(todoCardList.getSubject());
                    tc.setDueTime(todoCardList.getDueTime());
                    tc.setModeifiedTime(todoCardList.getModifiedTime());
                    tc.setCreatorId(todoCardList.getCreatorId());
                    tc.setDone(todoCardList.getIsDone());
                    todoCards.add(tc);
                }
                searchListResult.setTodoCards(todoCards);
                return searchListResult;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode());
            }


        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.getMessage(), err);
                throw new RuntimeException("err! code" + err.getCode() + ",message" + err.getMessage());
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(_err.getMessage(), _err);
                throw new RuntimeException("err! code" + err.getCode() + ",message" + err.getMessage());
            }

        }

        return null;
    }


    //递归
//    private void builderDetails(com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetails componentDetails,Details details){
//            if(details == null || details.getDetails() == null || details.getDetails().size() == 0){
//                return;
//            }
//        for (Details detail : details.getDetails()) {
//            com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetailsDetails formComponentValues0Details0Details0 = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetailsDetails()
//                         .setId(detail.getId())
//                         .setBizAlias(detail.getBizAlias())
//                         .setName(detail.getName())
//                         .setValue(detail.getExtValue())
//                         .setExtValue(detail.getExtValue())
//                         .setComponentType(detail.getComponentType());
//
//            List<StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetailsDetails> subComponentDetails = componentDetails.getDetails();
//            if(subComponentDetails == null){
//                subComponentDetails = new ArrayList<>();
//            }
//            subComponentDetails.add(formComponentValues0Details0Details0);
//            componentDetails.setDetails(subComponentDetails);
//
//
//            builderDetails(formComponentValues0Details0Details0,detail);
//
//        }
//
//    }


    /**
     * 发起审批实例
     **/
    public BuildApprovalResult buildInstance(String originatorUserId, String processCode, Long deptid, Long microappAgentId, List<String> ccList, String ccPosition, List<FormComponentValues> formComponentValues, List<TargetSelectActioners> targetSelectActioners) throws Exception {

        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceHeaders startProcessInstanceHeaders = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceHeaders();
        startProcessInstanceHeaders.xAcsDingtalkAccessToken = getAccessToken();


        //第一层
        List<com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValues> formComponentValuesList = new ArrayList<>();
        for (FormComponentValues componentValue : formComponentValues) {
            List<Details> details = componentValue.getDetails();
            if (details != null) {
                //第二层
                List<StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetails> attchDetailsList = new ArrayList<StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetails>();
                for (Details detail : details) {
                    List<Details> detailsDetails = detail.getDetails();
                    if (detailsDetails != null) {
                        //第三层
                        List<StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetailsDetails> attchDetailsDetailsList = new ArrayList<StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetailsDetails>();
                        for (Details detailsDetail : detailsDetails) {

                            com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetailsDetails formComponentValues0Details0Details0 = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetailsDetails()
                                    .setId(detailsDetail.getId())
                                    .setBizAlias(detailsDetail.getBizAlias())
                                    .setName(detailsDetail.getName())
                                    .setValue(detailsDetail.getExtValue())
                                    .setExtValue(detailsDetail.getExtValue())
                                    .setComponentType(detailsDetail.getComponentType());
                            attchDetailsDetailsList.add(formComponentValues0Details0Details0);
                        }

                        com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetails formComponentValues0Details0 = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValuesDetails()
                                .setId(detail.getId())
                                .setBizAlias(detail.getBizAlias())
                                .setName(detail.getName())
                                .setValue(detail.getValue())
                                .setExtValue(detail.getExtValue())
                                .setDetails(attchDetailsDetailsList);

                        attchDetailsList.add(formComponentValues0Details0);
                    }
                }
                com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValues formComponentValues0 = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValues()
                        .setId(componentValue.getId())
                        .setBizAlias(componentValue.getBizAlias())
                        .setName(componentValue.getName())
                        .setValue(componentValue.getValue())
                        .setExtValue(componentValue.getExtValue())
                        .setComponentType(componentValue.getComponentType())
                        .setDetails(attchDetailsList);

                formComponentValuesList.add(formComponentValues0);
            }
            com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValues formComponentValues0 = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestFormComponentValues()
                    .setId(componentValue.getId())
                    .setBizAlias(componentValue.getBizAlias())
                    .setName(componentValue.getName())
                    .setValue(componentValue.getValue())
                    .setExtValue(componentValue.getExtValue())
                    .setComponentType(componentValue.getComponentType());
            formComponentValuesList.add(formComponentValues0);
        }
        List<StartProcessInstanceRequest.StartProcessInstanceRequestTargetSelectActioners> attchTargetList = new ArrayList<StartProcessInstanceRequest.StartProcessInstanceRequestTargetSelectActioners>();
        if (targetSelectActioners != null) {
            for (TargetSelectActioners targetSelect : targetSelectActioners) {

                com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestTargetSelectActioners targetSelectActioners0 = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestTargetSelectActioners()
                        .setActionerKey(targetSelect.getActionerKey())
                        .setActionerUserIds(targetSelect.getActionerUserIds());
                attchTargetList.add(targetSelectActioners0);
            }
        }

//    com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestApprovers approvers0 = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest.StartProcessInstanceRequestApprovers()
//            .setActionType("AND")
//            .setUserIds(java.util.Arrays.asList(
//                    "26652461xxxx5992"
//            ));
        com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest startProcessInstanceRequest = new com.aliyun.dingtalkworkflow_1_0.models.StartProcessInstanceRequest()
                .setOriginatorUserId(originatorUserId)
                .setProcessCode(processCode)
                .setDeptId(deptid)
                .setMicroappAgentId(microappAgentId)
//            .setApprovers(java.util.Arrays.asList(
//                    approvers0
//            ))
                .setCcList(ccList)
                .setCcPosition(ccPosition)
                .setTargetSelectActioners(attchTargetList)
                .setFormComponentValues(formComponentValuesList);
        try {
            logger.info("BuildApproval" + "originatorUserId:" + originatorUserId + " ,processCode:" + processCode + ",deptid:", deptid + " ,microappAgentId:" + microappAgentId + " ,ccList:" + ccList + " ,ccPosition:" + ccPosition + " ,formComponentValues:" + formComponentValues + " ,targetSelectActioners:" + targetSelectActioners);

            StartProcessInstanceResponse res = client.startProcessInstanceWithOptions(startProcessInstanceRequest, startProcessInstanceHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("BuildApproval" + JSON.toJSONString(res));
            if (res.getStatusCode() == 200) {

                // 处理业务逻辑
                BuildApprovalResult buildApprovalResult = new BuildApprovalResult();
                buildApprovalResult.setInstanceId(res.body.getInstanceId());
                return buildApprovalResult;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode());
            }

        } catch (TeaException err) {
            logger.error("BuildApproval" + "originatorUserId:" + originatorUserId + " ,processCode:" + processCode + ",deptid:", deptid + " ,microappAgentId:" + microappAgentId + " ,ccList:" + ccList + " ,ccPosition:" + ccPosition + " ,formComponentValues:" + formComponentValues + " ,targetSelectActioners:" + targetSelectActioners, "," + err.message, err);
            throw new RuntimeException("err! code" + err.getCode() + ",message" + err.getMessage());
        } catch (Exception _err) {
            logger.error("BuildApproval" + "originatorUserId:" + originatorUserId + " ,processCode:" + processCode + ",deptid:", deptid + " ,microappAgentId:" + microappAgentId + " ,ccList:" + ccList + " ,ccPosition:" + ccPosition + " ,formComponentValues:" + formComponentValues + " ,targetSelectActioners:" + targetSelectActioners, "," + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err! code" + err.getCode() + ",message" + err.getMessage());
        }
    }

    /**
     * 获取单个审批实例详情
     **/
    public GainApprovalResult gainInstance(String processInstanceId) throws Exception {
//        java.util.List<String> flowExample = java.util.Arrays.asList( processInstanceId);
        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceHeaders getProcessInstanceHeaders = new com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceHeaders();
        getProcessInstanceHeaders.xAcsDingtalkAccessToken = getAccessToken();
        com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceRequest getProcessInstanceRequest = new com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceRequest()
                .setProcessInstanceId(processInstanceId);
        try {
            logger.info("GainApproval:" + processInstanceId);
            GetProcessInstanceResponse res = client.getProcessInstanceWithOptions(getProcessInstanceRequest, getProcessInstanceHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("GainApproval res:" + JSON.toJSONString(res));
            String success = res.body.getSuccess();
            boolean isSuccess = "true".equals(success.toLowerCase());
            if (res.getStatusCode() == 200 && isSuccess) {
                // 处理业务逻辑
//                GainApprovalResult gainApprovalResult = new GainApprovalResult();
//                gainApprovalResult.setResults(res.body.getResult());

                GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResult resResult = res.body.getResult();
                GainApprovalResult returnResult = JSON.parseObject(JSON.toJSONString(resResult), GainApprovalResult.class);
                return returnResult;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode() + ",success=" + res.body.getSuccess());
            }
        } catch (TeaException err) {
            logger.error("err! GainApproval:" + processInstanceId + "," + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());

        } catch (Exception _err) {
            logger.error("err! GainApproval:" + processInstanceId + "," + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }
    }

    ;

    /**
     * 撤销审批实例
     **/
    public CancelApprovalResult cancelInstance(String processInstanceId, String operatingUserId, Boolean isSystem, String remark) throws Exception {
//        java.util.List<String> flowExample = java.util.Arrays.asList(processInstanceId, actionerUserId);
        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.TerminateProcessInstanceHeaders terminateProcessInstanceHeaders = new com.aliyun.dingtalkworkflow_1_0.models.TerminateProcessInstanceHeaders();
        terminateProcessInstanceHeaders.xAcsDingtalkAccessToken = getAccessToken();
        com.aliyun.dingtalkworkflow_1_0.models.TerminateProcessInstanceRequest terminateProcessInstanceRequest = new com.aliyun.dingtalkworkflow_1_0.models.TerminateProcessInstanceRequest()
                .setProcessInstanceId(processInstanceId)
                .setIsSystem(isSystem)
                .setRemark(remark)
                .setOperatingUserId(operatingUserId);
        try {
            logger.info("CancelApproval:" + processInstanceId + "operatingUserId:" + operatingUserId + ",isSystem:" + isSystem + ",remark:" + remark);
            TerminateProcessInstanceResponse res = client.terminateProcessInstanceWithOptions(terminateProcessInstanceRequest, terminateProcessInstanceHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("CancelApproval res:" + JSON.toJSONString(res));
            if (res.getStatusCode() == 200 && res.body.getSuccess()) {
                // 处理业务逻辑
                CancelApprovalResult cancelApprovalResult = new CancelApprovalResult();
                cancelApprovalResult.setResult(res.body.getResult());
                return cancelApprovalResult;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode() + ",success=" + res.body.getSuccess());
            }
        } catch (TeaException err) {
            logger.error("err! CancelApproval:" + processInstanceId + "operatingUserId:" + operatingUserId + ",isSystem:" + isSystem + ",remark:" + remark + "," + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            logger.error("err! CancelApproval:" + processInstanceId + "operatingUserId:" + operatingUserId + ",isSystem:" + isSystem + ",remark:" + remark + "," + err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }
    }

    ;


    /**
     * 添加审批评论
     **/
    public AddApprovalResult addComment(String processInstanceId, String text, String commentUserId, File fileRes) throws Exception {
//        java.util.List<T> flowExample = new ArrayList<>();
//        flowExample.add(text,processInstanceId)

        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.AddProcessInstanceCommentHeaders addProcessInstanceCommentHeaders = new com.aliyun.dingtalkworkflow_1_0.models.AddProcessInstanceCommentHeaders();
        addProcessInstanceCommentHeaders.xAcsDingtalkAccessToken = "<your access token>";

        List<AddProcessInstanceCommentRequest.AddProcessInstanceCommentRequestFileAttachments> attachList = new ArrayList<AddProcessInstanceCommentRequest.AddProcessInstanceCommentRequestFileAttachments>();
        for (Attachments attachments : fileRes.getAttachments()) {
            com.aliyun.dingtalkworkflow_1_0.models.AddProcessInstanceCommentRequest.AddProcessInstanceCommentRequestFileAttachments fileAttachments0 = new com.aliyun.dingtalkworkflow_1_0.models.AddProcessInstanceCommentRequest.AddProcessInstanceCommentRequestFileAttachments()
                    .setFileSize(attachments.getFileSize())
                    .setFileId(attachments.getFileId())
                    .setFileName(attachments.getFileName())
                    .setFileType(attachments.getFileType());
            attachList.add(fileAttachments0);
        }

        com.aliyun.dingtalkworkflow_1_0.models.AddProcessInstanceCommentRequest.AddProcessInstanceCommentRequestFile file = new com.aliyun.dingtalkworkflow_1_0.models.AddProcessInstanceCommentRequest.AddProcessInstanceCommentRequestFile()
                .setPhotos(fileRes.getPhotos()
                )
                .setAttachments(attachList);
        com.aliyun.dingtalkworkflow_1_0.models.AddProcessInstanceCommentRequest addProcessInstanceCommentRequest = new com.aliyun.dingtalkworkflow_1_0.models.AddProcessInstanceCommentRequest()
                .setProcessInstanceId(processInstanceId)
                .setText(text)
                .setCommentUserId(commentUserId)
                .setFile(file);
        try {
            logger.info("AddApproval:" + "processInstanceId:" + processInstanceId + ",text:" + text + ",commentUerId:" + commentUserId + "file:" + fileRes);
            AddProcessInstanceCommentResponse res = client.addProcessInstanceCommentWithOptions(addProcessInstanceCommentRequest, addProcessInstanceCommentHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("AddApproval res:" + JSON.toJSONString(res));
            if (res.getStatusCode() == 200 && res.body.getSuccess()) {
                // 处理业务逻辑
                AddApprovalResult addApprovalResult = new AddApprovalResult();
                addApprovalResult.setResult(res.body.getResult());
                return addApprovalResult;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode() + ",success=" + res.body.getSuccess());

            }
        } catch (TeaException err) {
            logger.error("err! AddApproval:" + "processInstanceId:" + processInstanceId + ",text:" + text + ",commentUerId:" + commentUserId + "file:" + fileRes + "," + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());

        } catch (Exception _err) {
            logger.error("err! AddApproval:" + "processInstanceId:" + processInstanceId + ",text:" + text + ",commentUerId:" + commentUserId + "file:" + fileRes + "," + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }

    }

    ;


    /**
     * 获取审批实例ID列表
     **/
    public GainApprovalIdResult gainInstanceList(Long starTime, Long endTime, String processCode, Long nextToken, Long maxResults, List<String> userIds, List<String> statuses) throws Exception {
//        java.util.List<FlowExample> flowExample = java.util.Arrays.asList(processCode, deptId, userIds, statuses,starTime,endTime);
        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsHeaders listProcessInstanceIdsHeaders = new com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsHeaders();
        listProcessInstanceIdsHeaders.xAcsDingtalkAccessToken = getAccessToken();
        com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsRequest listProcessInstanceIdsRequest = new com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsRequest()

                .setProcessCode(processCode)
                .setStartTime(starTime)
                .setEndTime(endTime)
                .setNextToken(nextToken)
                .setMaxResults(maxResults)
                .setUserIds(userIds)
                .setStatuses(statuses);
        try {
            logger.info("GainApprovalId:" + "starTime:" + starTime + ",endTime:" + endTime + ",processCode:" + processCode + ",nextToken:" + nextToken + ",maxResults:" + maxResults + ",userIds:" + userIds + ",statuses:" + statuses);
            ListProcessInstanceIdsResponse res = client.listProcessInstanceIdsWithOptions(listProcessInstanceIdsRequest, listProcessInstanceIdsHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("GainApprovalId res:" + JSON.toJSONString(res));
            if (res.getStatusCode() == 200 && res.body.getSuccess()) {
                // 处理业务逻辑
                GainApprovalIdResult result = new GainApprovalIdResult();

                result.setList(res.body.getResult().getList());
                result.setNextToken(res.body.getResult().getNextToken());

                return result;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode() + ",success=" + res.body.getSuccess());
            }
        } catch (TeaException err) {
            logger.error("err! GainApprovalId:" + "starTime:" + starTime + ",endTime:" + endTime + ",processCode:" + processCode + ",nextToken:" + nextToken + ",maxResults:" + maxResults + ",userIds:" + userIds + ",statuses:" + statuses + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());

        } catch (Exception _err) {
            logger.error("err! GainApprovalId:" + "starTime:" + starTime + ",endTime:" + endTime + ",processCode:" + processCode + ",nextToken:" + nextToken + ",maxResults:" + maxResults + ",userIds:" + userIds + ",statuses:" + statuses + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }
    }

    ;

    /**
     * 同意或拒绝审批实例
     **/

    public HandelTaskResult handelInstance(String processInstanceId, Long taskId, String actionerUserId, String result, String remark, File fileRes) throws Exception {
        logger.info("HandelTask:processInstanceId:" + processInstanceId + ",taskId:" + taskId + ",actionerUserId:" + actionerUserId + ",result:" + result + ",remark:" + remark + "file:" + fileRes);
//        java.util.List<Object>flowTask = java.util.Arrays.asList( processInstanceId, actionerUserId,  result, remark,processCode, file,taskId);
        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceHeaders executeProcessInstanceHeaders = new com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceHeaders();
        executeProcessInstanceHeaders.xAcsDingtalkAccessToken = getAccessToken();


        List<com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest.ExecuteProcessInstanceRequestFileAttachments> attachList = new ArrayList<>();
        com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest.ExecuteProcessInstanceRequestFile file =
                new com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest.ExecuteProcessInstanceRequestFile()
                        .setPhotos(fileRes == null ? null : fileRes.getPhotos())
                        .setAttachments(attachList);

        // 首先检查 fileRes 是否为 null，然后检查 getAttachments() 方法返回的列表是否为 null 或空
        if (fileRes != null && fileRes.getAttachments() != null && !fileRes.getAttachments().isEmpty()) {
            attachList = new ArrayList<com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest.ExecuteProcessInstanceRequestFileAttachments>();
            for (Attachments attachment : fileRes.getAttachments()) {
                // 再次检查每个 attachment 是否为 null
                if (attachment != null) {
                    com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest.ExecuteProcessInstanceRequestFileAttachments fileAttachments0 =
                            new com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest.ExecuteProcessInstanceRequestFileAttachments()
                                    .setFileSize(attachment.getFileSize())
                                    .setFileId(attachment.getFileId())
                                    .setFileName(attachment.getFileName())
                                    .setFileType(attachment.getFileType());
                    attachList.add(fileAttachments0);
                }
                file = new com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest.ExecuteProcessInstanceRequestFile()
                        .setPhotos(fileRes.getPhotos())
                        .setAttachments(attachList);
            }


        }
        com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest executeProcessInstanceRequest = new com.aliyun.dingtalkworkflow_1_0.models.ExecuteProcessInstanceRequest()
                .setProcessInstanceId(processInstanceId)
                .setResult(result)
                .setActionerUserId(actionerUserId)
                .setTaskId(taskId)
                .setFile(file);
        String safeRemark = StringUtils.defaultIfEmpty(remark, "无");
        executeProcessInstanceRequest.setRemark(safeRemark);
        String formComponentValues = "{\"field1\":\"new_value1\",\"field2\":\"new_value2\"}";

        try {
            logger.info("HandleTask :" + "processInstanceId:" + processInstanceId + ",taskId:" + taskId + ",actionUserId:" + actionerUserId + ",result:" + result + ",remark:" + remark + ",file:" + fileRes);
            ExecuteProcessInstanceResponse res = client.executeProcessInstanceWithOptions(executeProcessInstanceRequest, executeProcessInstanceHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("HandleTask res:" + JSON.toJSONString(res));
            if (res.getStatusCode() == 200 && res.body.getSuccess()) {
                // 处理业务逻辑
                HandelTaskResult handelTaskResult = new HandelTaskResult();
                handelTaskResult.setResult(res.body.getResult());
                System.out.println("对象===="+JSON.toJSONString(handelTaskResult));
                return handelTaskResult;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode() + ",success=" + res.body.getSuccess());
            }
        } catch (TeaException err) {
            logger.error("err! HandleTask :" + "processInstanceId:" + processInstanceId + ",taskId:" + taskId + ",actionUserId:" + actionerUserId + ",result:" + result + ",remark:" + remark + ",file:" + fileRes + "," + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        } catch (Exception _err) {
            logger.error("err! HandleTask :" + "processInstanceId:" + processInstanceId + ",taskId:" + taskId + ",actionUserId:" + actionerUserId + ",result:" + result + ",remark:" + remark + ",file:" + fileRes + "," + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }
    }

    ;

    /**
     * 转交OA审批任务
     **/
    public SwapTaskResult swapInstance(String toUserId, Long taskId, String operateUserId, String actionName, String remark, File fileRes) throws Exception {
//        java.util.List<Object> flowTask = java.util.Arrays.asList(toUserId, operateUserId, actionName, remark, file,taskId);
        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskHeaders redirectWorkflowTaskHeaders = new com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskHeaders();
        redirectWorkflowTaskHeaders.xAcsDingtalkAccessToken = "<your access token>";

        List<com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskRequest.RedirectWorkflowTaskRequestFileAttachments> attachList = new ArrayList<RedirectWorkflowTaskRequest.RedirectWorkflowTaskRequestFileAttachments>();
        for (Attachments attachment : fileRes.getAttachments()) {
            com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskRequest.RedirectWorkflowTaskRequestFileAttachments fileAttachments0 = new com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskRequest.RedirectWorkflowTaskRequestFileAttachments()
                    .setFileSize(attachment.getFileSize())
                    .setFileId(attachment.getFileId())
                    .setFileName(attachment.getFileName())
                    .setFileType(attachment.getFileType());
            attachList.add(fileAttachments0);
        }

        com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskRequest.RedirectWorkflowTaskRequestFile file = new com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskRequest.RedirectWorkflowTaskRequestFile()
                .setPhotos(fileRes.getPhotos())
                .setAttachments(attachList);

        com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskRequest redirectWorkflowTaskRequest = new com.aliyun.dingtalkworkflow_1_0.models.RedirectWorkflowTaskRequest()
                .setTaskId(taskId)
                .setToUserId(toUserId)
                .setRemark(remark)
                .setOperateUserId(operateUserId)
                .setActionName(actionName)
                .setFile(file);
        try {
            logger.info("SwapTask" + "toUserId:" + toUserId + ",taskId:" + taskId + ",operateUserId" + operateUserId + ",actionName:" + actionName + ",remark:" + remark + ",file:" + fileRes);
            RedirectWorkflowTaskResponse res = client.redirectWorkflowTaskWithOptions(redirectWorkflowTaskRequest, redirectWorkflowTaskHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("SwapTask res:" + JSON.toJSONString(res));
            if (res.getStatusCode() == 200) {
                // 处理业务逻辑
                SwapTaskResult swapTaskResult = new SwapTaskResult();
                swapTaskResult.setResult(res.body.getResult());
                return swapTaskResult;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode());
            }
        } catch (TeaException err) {
            logger.error("err! SwapTask" + "toUserId:" + toUserId + ",taskId:" + taskId + ",operateUserId" + operateUserId + ",actionName:" + actionName + ",remark:" + remark + ",file:" + fileRes + "," + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        } catch (Exception _err) {
            logger.error("_err! SwapTask" + "toUserId:" + toUserId + ",taskId:" + taskId + ",operateUserId" + operateUserId + ",actionName:" + actionName + ",remark:" + remark + ",file:" + fileRes + "," + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }

    }

    ;


    /**
     * 获取用户待审批数量
     **/
    public GainTaskNumberResult gainInstanceNumber(String userId) throws Exception {
//        java.util.List<String> flowTask = java.util.Arrays.asList(userId);
//        java.util.List<String> flowTask = new ArrayList<>();
//        flowTask.add(userId);
        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.GetUserTodoTaskSumHeaders getUserTodoTaskSumHeaders = new com.aliyun.dingtalkworkflow_1_0.models.GetUserTodoTaskSumHeaders();
        getUserTodoTaskSumHeaders.xAcsDingtalkAccessToken = "<your access token>";
        com.aliyun.dingtalkworkflow_1_0.models.GetUserTodoTaskSumRequest getUserTodoTaskSumRequest = new com.aliyun.dingtalkworkflow_1_0.models.GetUserTodoTaskSumRequest()
                .setUserId(userId);
        try {
            logger.info("gainExampleNumber userId:" + userId);
            GetUserTodoTaskSumResponse res = client.getUserTodoTaskSumWithOptions(getUserTodoTaskSumRequest, getUserTodoTaskSumHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("gainExampleNumber res:" + JSON.toJSONString(res));
            if (res.getStatusCode() == 200) {
                // 处理业务逻辑
                GainTaskNumberResult gainTaskNumberResult = new GainTaskNumberResult();
                gainTaskNumberResult.setResult(res.body.getResult());
                return gainTaskNumberResult;
            } else {
                throw new RuntimeException("statusCode=" + res.getStatusCode());

            }
        } catch (TeaException err) {
            logger.error("err! gainExampleNumber userId:" + userId + "," + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        } catch (Exception _err) {
            logger.error("_err!gainExampleNumber userId" + userId + "," + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }

    }

    /**
     * 获取用户待审批列表
     *
     * @param userId 用户id
     * @param Offset 偏移量
     * @param Count  页大小
     * @param Status 状态 0:未处理 -1:已处理
     **/
    public GainInstanceListResult gainInstanceList(String userId, Long Offset, Long Count, Long Status) throws Exception {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/process/workrecord/task/query");
        OapiProcessWorkrecordTaskQueryRequest req = new OapiProcessWorkrecordTaskQueryRequest();
        req.setUserid(userId);
        req.setOffset(Offset);
        req.setCount(Count);
        req.setStatus(Status);
        OapiProcessWorkrecordTaskQueryResponse rsp = client.execute(req, getAccessToken());
        if (rsp.getErrcode() != 0) {
            logger.error("err! gainInstanceList:" + rsp.getErrmsg());
            throw new RuntimeException("err!code=" + rsp.getErrorCode() + ",message=" + rsp.getErrmsg());
        }
        return JSON.parseObject(JSON.toJSONString(rsp.getResult()), GainInstanceListResult.class);
    }

    public DownloadAttachmentResult downloadAttachment(String fileId, String processInstanceId) throws Exception {

        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.GrantProcessInstanceForDownloadFileHeaders grantProcessInstanceForDownloadFileHeaders = new com.aliyun.dingtalkworkflow_1_0.models.GrantProcessInstanceForDownloadFileHeaders();
        grantProcessInstanceForDownloadFileHeaders.xAcsDingtalkAccessToken = getAccessToken();
        com.aliyun.dingtalkworkflow_1_0.models.GrantProcessInstanceForDownloadFileRequest grantProcessInstanceForDownloadFileRequest = new com.aliyun.dingtalkworkflow_1_0.models.GrantProcessInstanceForDownloadFileRequest()
                .setProcessInstanceId(processInstanceId)
                .setFileId(fileId);
        try {
            GrantProcessInstanceForDownloadFileResponse rsp = client.grantProcessInstanceForDownloadFileWithOptions(grantProcessInstanceForDownloadFileRequest, grantProcessInstanceForDownloadFileHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            return JSON.parseObject(JSON.toJSONString(rsp.getBody()), DownloadAttachmentResult.class);
        } catch (TeaException err) {
            logger.error("err! downloadAttachment:" + "processInstanceId:" + processInstanceId + "err" + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());

        } catch (Exception _err) {
            logger.error("err! downloadAttachment:" + "processInstanceId:" + processInstanceId + "_err" + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }

    }

    public String getProcessCode(String title) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/process/get_by_name");
        OapiProcessGetByNameRequest req = new OapiProcessGetByNameRequest();
        // 获取 "的" 的索引位置
        int index = title.indexOf("的");
        // 从 "的" 的位置之后开始截取
        String result = title.substring(index + 1);

        req.setName(result);
        OapiProcessGetByNameResponse rsp = client.execute(req, getAccessToken());
        if (rsp.getErrcode() != 0) {
            logger.error("err! getProcessCode:" + rsp.getErrmsg());
            throw new RuntimeException("err!code=" + rsp.getErrorCode() + ",message=" + rsp.getErrmsg());
        }
        return rsp.getProcessCode();
    }

    //预测流程
    public ProcessForecastResponseBody.ProcessForecastResponseBodyResult ForecastingProcessthrows(String processCode, int deptId, String userId, List<FormComponentValues> formComponentValues) throws Exception {
        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        ProcessForecastHeaders processForecastHeaders = new ProcessForecastHeaders();
        processForecastHeaders.xAcsDingtalkAccessToken = getAccessToken();


        // First layer
        List<com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValues> formComponentValuesList = new ArrayList<>();
        for (FormComponentValues componentValue : formComponentValues) {
            if (componentValue.getName() == null || componentValue.getName().isEmpty() ||
                    componentValue.getValue() == null || componentValue.getValue().isEmpty()) {
                continue; // Skip if name or value is null or empty
            }

            List<Details> details = componentValue.getDetails();
            if (details != null) {
                // Second layer
                List<ProcessForecastRequest.ProcessForecastRequestFormComponentValuesDetails> attchDetailsList = new ArrayList<>();
                for (Details detail : details) {
                    if (detail.getName() == null || detail.getName().isEmpty() ||
                            detail.getValue() == null || detail.getValue().isEmpty()) {
                        continue; // Skip if name or value is null or empty
                    }

                    List<Details> detailsDetails = detail.getDetails();
                    if (detailsDetails != null) {
                        // Third layer
                        List<ProcessForecastRequest.ProcessForecastRequestFormComponentValuesDetailsDetails> attchDetailsDetailsList = new ArrayList<>();
                        for (Details detailsDetail : detailsDetails) {
                            if (detailsDetail.getName() == null || detailsDetail.getName().isEmpty() ||
                                    detailsDetail.getValue() == null || detailsDetail.getValue().isEmpty()) {
                                continue; // Skip if name or value is null or empty
                            }

                            com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValuesDetailsDetails formComponentValues0Details0Details0 = new com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValuesDetailsDetails()
                                    .setId(detailsDetail.getId())
                                    .setBizAlias(detailsDetail.getBizAlias())
                                    .setName(detailsDetail.getName())
                                    .setValue(detailsDetail.getValue())
                                    .setExtValue(detailsDetail.getExtValue())
                                    .setComponentType(detailsDetail.getComponentType());
                            attchDetailsDetailsList.add(formComponentValues0Details0Details0);
                        }

                        com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValuesDetails formComponentValues0Details0 = new com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValuesDetails()
                                .setId(detail.getId())
                                .setBizAlias(detail.getBizAlias())
                                .setName(detail.getName())
                                .setValue(detail.getValue())
                                .setExtValue(detail.getExtValue())
                                .setDetails(attchDetailsDetailsList);

                        attchDetailsList.add(formComponentValues0Details0);
                    }

                    com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValues formComponentValues0 = new com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValues()
                            .setId(componentValue.getId())
                            .setBizAlias(componentValue.getBizAlias())
                            .setName(componentValue.getName())
                            .setValue(componentValue.getValue())
                            .setExtValue(componentValue.getExtValue())
                            .setComponentType(componentValue.getComponentType())
                            .setDetails(attchDetailsList); // Add the second layer details

                    formComponentValuesList.add(formComponentValues0);
                }
            } else {
                // If there are no details, just create a simple ProcessForecastRequestFormComponentValues
                com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValues formComponentValues0 = new com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastRequest.ProcessForecastRequestFormComponentValues()
                        .setId(componentValue.getId())
                        .setBizAlias(componentValue.getBizAlias())
                        .setName(componentValue.getName())
                        .setValue(componentValue.getValue())
                        .setExtValue(componentValue.getExtValue())
                        .setComponentType(componentValue.getComponentType());

                formComponentValuesList.add(formComponentValues0);
            }
        }

        ProcessForecastRequest processForecastRequest = new ProcessForecastRequest()
                .setProcessCode(processCode)
                .setDeptId(deptId)
                .setUserId(userId)
                .setFormComponentValues(formComponentValuesList);
        try {
            com.aliyun.dingtalkworkflow_1_0.models.ProcessForecastResponse response = client.processForecastWithOptions(processForecastRequest, processForecastHeaders, new RuntimeOptions());
            logger.info("ForecastingProcess: " + response.getBody().getResult());
            return response.getBody().getResult();

        } catch (TeaException err) {
            logger.error("err! ForecastingProcessthrows: " + "err" + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());

        } catch (Exception _err) {
            logger.error("err! ForecastingProcessthrows: " + "_err" + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());

        }
    }
    // 获取spaceId
    public Long getSpaceId(String userId) throws Exception {

        com.aliyun.dingtalkworkflow_1_0.Client client = FlowConfig.createClientFlow();
        com.aliyun.dingtalkworkflow_1_0.models.GetAttachmentSpaceHeaders getAttachmentSpaceHeaders = new com.aliyun.dingtalkworkflow_1_0.models.GetAttachmentSpaceHeaders();
        getAttachmentSpaceHeaders.xAcsDingtalkAccessToken = getAccessToken();

        com.aliyun.dingtalkworkflow_1_0.models.GetAttachmentSpaceRequest getAttachmentSpaceRequest = new com.aliyun.dingtalkworkflow_1_0.models.GetAttachmentSpaceRequest()
                .setUserId(userId)
                .setAgentId(dingTalkConfig.getAgentId());
        try {
            com.aliyun.dingtalkworkflow_1_0.models.GetAttachmentSpaceResponse response = client.getAttachmentSpaceWithOptions(getAttachmentSpaceRequest, getAttachmentSpaceHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            return response.getBody().getResult().getSpaceId();
        } catch (TeaException err) {
            logger.error("err! getSpaceId: " + "err" + err.getMessage(), err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());

        } catch (Exception _err) {
            logger.error("err! getSpaceId: " + "_err" + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("err!code=" + err.getCode() + ",message=" + err.getMessage());
        }
    }
    //获取上传信息
    public UploadInformationResult  getUploadInformation(Long spaceId, String unionId) throws Exception {
        com.aliyun.dingtalkstorage_1_0.Client client = FlowConfig.createClientStorage();
        com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoHeaders getFileUploadInfoHeaders = new com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoHeaders();
        getFileUploadInfoHeaders.xAcsDingtalkAccessToken = getAccessToken();


        com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoRequest getFileUploadInfoRequest = new com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoRequest()
                .setUnionId(unionId)
                .setProtocol("HEADER_SIGNATURE")
                .setMultipart(false);
        try {
            com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoResponse response =   client.getFileUploadInfoWithOptions(String.valueOf(spaceId), getFileUploadInfoRequest, getFileUploadInfoHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            logger.info("getUploadInformation: " + response.getBody().getHeaderSignatureInfo());

            return JSON.parseObject(JSON.toJSONString(response.getBody()), UploadInformationResult.class);
        } catch (TeaException err) {
            logger.error("err! getUploadInformation: " + "err" + err.getMessage(), err);
            throw new RuntimeException(" getUploadInformation: err!code=" + err.getCode() + ",message=" + err.getMessage());

        } catch (Exception _err) {
            logger.error("err! getUploadInformation: " + "_err" + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("getUploadInformation: err!code=" + err.getCode() + ",message=" + err.getMessage());
        }
    }
    //完成上传文件
    public uploadFileResult uploadFile(String unionId ,String uploadKey ,Long spaceId,String fileName) throws Exception {
        com.aliyun.dingtalkstorage_1_0.Client client = FlowConfig.createClientStorage();
        com.aliyun.dingtalkstorage_1_0.models.CommitFileHeaders commitFileHeaders = new com.aliyun.dingtalkstorage_1_0.models.CommitFileHeaders();
        commitFileHeaders.xAcsDingtalkAccessToken = getAccessToken();

        com.aliyun.dingtalkstorage_1_0.models.CommitFileRequest commitFileRequest = new com.aliyun.dingtalkstorage_1_0.models.CommitFileRequest()
                .setUnionId(unionId)
                .setUploadKey(uploadKey)
                .setName(fileName)
                .setParentId("0");
        try {
            com.aliyun.dingtalkstorage_1_0.models.CommitFileResponse response   =  client.commitFileWithOptions(String.valueOf(spaceId), commitFileRequest, commitFileHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            CommitFileResponseBody.CommitFileResponseBodyDentry dentry = response.getBody().getDentry();
            uploadFileResult result = new uploadFileResult();
            result.setFileId(Long.valueOf(dentry.getId()));
            result.setFileName(dentry.getName());
            result.setFileSize(dentry.getSize());
            result.setFileType(dentry.getExtension());
            result.setSpaceId(Long.valueOf(dentry.getSpaceId()));
            logger.info("uploadFile: " + result);
            return  result;
        } catch (TeaException err) {
            logger.error("err!uploadFile: " + "err" + err.getMessage(), err);
            throw new RuntimeException("uploadFile: err!code=" + err.getCode() + ",message=" + err.getMessage());

        } catch (Exception _err) {
            logger.error("err! uploadFile: " + "_err" + _err.getMessage(), _err);
            TeaException err = new TeaException(_err.getMessage(), _err);
            throw new RuntimeException("uploadFile: err!code=" + err.getCode() + ",message=" + err.getMessage());
        }

    }

    /**
     * // 获取部门列表
     * @return
     * @throws Exception
     */

    public List<Long> getDeptList(String userId) throws Exception {
        List<Long> deptIdList = new ArrayList<>();

        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest req = new OapiV2UserGetRequest();
            req.setUserid(userId);
            req.setLanguage("en_US");
            OapiV2UserGetResponse rsp = client.execute(req, getAccessToken());


            logger.info("getAccessToken(): " + getAccessToken());
            if (rsp.isSuccess()) {
                deptIdList = rsp.getResult().getDeptIdList();
                logger.info("getDeptList: " + deptIdList);
            } else {
                logger.error("API call failed with error code: " + rsp.getErrcode() + ", error message: " + rsp.getErrmsg());
            }
        } catch (ApiException e) {
            logger.error("err! getDeptList: " + e.getMessage(), e);
            throw new RuntimeException("Failed to get department list for user: " + userId, e);
        }

        return deptIdList;
    }


    /**
     * // 获取部门列表
     * @return
     * @throws Exception
     */

    public List<SimpleListReturn> simplelist(SimpleListRequest simpleListRequest) throws Exception {

        List<SimpleListReturn> deptIdList = new ArrayList<>();
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/simplelist");
            OapiRoleSimplelistRequest req = new OapiRoleSimplelistRequest();
            req.setRoleId(simpleListRequest.getRoleId());
            req.setSize(simpleListRequest.getSize());
            req.setOffset(simpleListRequest.getOffset());
            OapiRoleSimplelistResponse rsp = client.execute(req, getAccessToken());


            logger.info("getAccessToken(): " + getAccessToken());
            if (rsp.isSuccess()) {
                List<OapiRoleSimplelistResponse.OpenEmpSimple> resList = rsp.getResult().getList();
                for (OapiRoleSimplelistResponse.OpenEmpSimple simple : resList) {
                    SimpleListReturn ret = new SimpleListReturn();
                    ret.setName(simple.getName());
                    ret.setUserId(simple.getUserid());
                    deptIdList.add(ret);
                }
                logger.info("getDeptList: " + deptIdList);
            } else {
                logger.error("API call failed with error code: " + rsp.getErrcode() + ", error message: " + rsp.getErrmsg());
            }
        } catch (ApiException e) {
            logger.error("err! getDeptList: " + e.getMessage(), e);
            throw new RuntimeException("Failed to get department list for user: " + JSON.toJSONString(simpleListRequest), e);
        }

        return deptIdList;
    }
}






