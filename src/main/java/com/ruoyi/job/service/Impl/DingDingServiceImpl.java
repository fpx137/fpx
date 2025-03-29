package com.ruoyi.job.service.Impl;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.job.config.DingdingConfig;
import com.ruoyi.job.domain.AttendProcurement;
import com.ruoyi.job.domain.SysFlow.AttendBusinessRegister;
import com.ruoyi.job.domain.SysFlow.AttendLeaveRegister;
import com.ruoyi.job.domain.SysFlow.AttendOvertimeRegister;
import com.ruoyi.job.mapper.AttendProcurementMapper;
import com.ruoyi.job.mapper.SysFlowMapper.AttendBusinessRegisterMapper;
import com.ruoyi.job.mapper.SysFlowMapper.AttendLeaveRegisterMapper;
import com.ruoyi.job.mapper.SysFlowMapper.AttendOvertimeRegisterMapper;
import com.ruoyi.job.service.DingDingService;
import com.ruoyi.job.vo.AttendBusinessRegisterVo;
import com.ruoyi.job.vo.AttendLeaveRegisterVo;
import com.ruoyi.job.vo.AttendOvertimeRegisterVo;
import com.ruoyi.system.api.RemoteFlowService;
import com.ruoyi.system.api.domain.SysFlow.flowexample.FormComponentValues;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Request.GainApproval;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Request.GainApprovalId;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Return.GainApprovalIdResult;
import com.ruoyi.system.api.domain.SysFlow.flowexample.Return.GainApprovalResult;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DingDingServiceImpl implements DingDingService {
    @Autowired
    private AttendLeaveRegisterMapper attendLeaveRegisterMapper;
    @Autowired
    private RemoteFlowService remoteFlowService;
    @Autowired
    private AttendBusinessRegisterMapper attendBusinessRegisterMapper;
    @Autowired
    private AttendOvertimeRegisterMapper attendOvertimeRegisterMapper;
    @Autowired
    private DingdingConfig dingdingConfig;
    @Autowired
    private AttendProcurementMapper attendProcurementMapper;
    /**
     * 更新审批状态
     */
    @Override
    public void updateStatus() throws ApiException {

        List<String> dbList0 = attendLeaveRegisterMapper.selectProcessInstanceId();// 获取要处理的流程实例ID列表
        List<String> dbList1= attendBusinessRegisterMapper.selectProcessInstanceId();
        List<String> dbList2= attendOvertimeRegisterMapper.selectProcessInstanceId();
        //遍历请假登记
        for (String instanceId : dbList0) {
            GainApproval gainApproval = new GainApproval();
            gainApproval.setProessInstanceId(instanceId);
            R<GainApprovalResult> gainApprovalResultR = remoteFlowService.detailsApproval(gainApproval);

//                request.setProcessInstanceId(instanceId); //存放流程实例ID
//                OapiProcessinstanceGetResponse response = client.execute(request, AccessTokenUtil.getToken(dingTalkConfig.getAppKey(),dingTalkConfig.getAppSecret()));
//                int state = processResponse(response);  //获取实例状态

            AttendLeaveRegister attendLeaveRegister = new AttendLeaveRegister();
            attendLeaveRegister.setProcessInstanceId(instanceId);
            String ddStatus = gainApprovalResultR.getData().getStatus();

            Integer status = null;
            if (gainApprovalResultR.getData().getResult().equals("refuse")){
                status = 2;
            }else if(ddStatus.equals("RUNNING")){
                status = 0;
            }else if(ddStatus.equals("TERMINATED")) {
                status = 3;
            } else if (ddStatus.equals("COMPLETED")) {
                status = 1;
            }

            attendLeaveRegister.setState(status);
            attendLeaveRegister.setUpdateTime(new Date());
            // 更新状态
            attendLeaveRegisterMapper.updateStateByProcessInstanceId(attendLeaveRegister);
        }
        //遍历出差登记
        for (String instanceId : dbList1) {
            GainApproval gainApproval = new GainApproval();
            gainApproval.setProessInstanceId(instanceId);
            R<GainApprovalResult> gainApprovalResultR = remoteFlowService.detailsApproval(gainApproval);

            AttendBusinessRegister attendBusinessRegister = new AttendBusinessRegister();
            attendBusinessRegister.setProcessInstanceId(instanceId);
            String ddStatus = gainApprovalResultR.getData().getStatus();

            Integer status = null;
            if (gainApprovalResultR.getData().getResult().equals("refuse")){
                status = 2;
            }else if(ddStatus.equals("RUNNING")){
                status = 0;
            }else if(ddStatus.equals("TERMINATED")) {
                status = 3;
            } else if (ddStatus.equals("COMPLETED")) {
                status = 1;
            }
            attendBusinessRegister.setState(status);
            attendBusinessRegister.setUpdateTime(new Date());
            attendBusinessRegisterMapper.updateStateByProcessInstanceId(attendBusinessRegister);
        }
        //遍历加班调休登记
        for (String instanceId : dbList2) {
            GainApproval gainApproval = new GainApproval();
            gainApproval.setProessInstanceId(instanceId);
            R<GainApprovalResult> gainApprovalResultR = remoteFlowService.detailsApproval(gainApproval);

            AttendOvertimeRegister attendOvertimeRegister = new AttendOvertimeRegister();
            attendOvertimeRegister.setProcessInstanceId(instanceId);
            String ddStatus = gainApprovalResultR.getData().getStatus();

            Integer status = null;
            if (gainApprovalResultR.getData().getResult().equals("refuse")){
                status = 2;
            }else if(ddStatus.equals("RUNNING")){
                status = 0;
            }else if(ddStatus.equals("TERMINATED")) {
                status = 3;
            } else if (ddStatus.equals("COMPLETED")) {
                status = 1;
            }
            attendOvertimeRegister.setState(status);
            attendOvertimeRegister.setUpdateTime(new Date());
            attendOvertimeRegisterMapper.updateStateByProcessInstanceId(attendOvertimeRegister);
        }
    }

    /**
     * 同步移动端审批记录
     */
    @Override
    public void updateApprovalList() throws Exception {
        AttendLeaveRegisterVo attendLeaveRegisterVo = new AttendLeaveRegisterVo();
        AttendBusinessRegisterVo attendBusinessRegisterVo = new AttendBusinessRegisterVo();
        AttendOvertimeRegisterVo attendOvertimeRegisterVo = new AttendOvertimeRegisterVo();

        String leaveProcessCode = dingdingConfig.getLeaveProcessCode();
        String businessProcessCode = dingdingConfig.getBusinessProcessCode();
        String overtimeProcessCode = dingdingConfig.getOvertimeProcessCode();

        Long userId = SecurityUtils.getUserId();
        Long nextToken = 0L;

        List<AttendLeaveRegisterVo> startTimeList = attendLeaveRegisterMapper.selectAttendLeaveRegisterList(attendLeaveRegisterVo, userId);
        List<AttendBusinessRegisterVo> startTimeList1 = attendBusinessRegisterMapper.selectAttendBusinessRegisterList(attendBusinessRegisterVo, userId);
        List<AttendOvertimeRegisterVo> startTimeList2 = attendOvertimeRegisterMapper.selectAttendOvertimeRegisterList(attendOvertimeRegisterVo, userId);

        for (AttendLeaveRegisterVo attendLeaveRegisterVo1 : startTimeList) {
            GainApprovalId gainApprovalId0 = new GainApprovalId();
            gainApprovalId0.setProcessCode(leaveProcessCode);
            gainApprovalId0.setStartTime(attendLeaveRegisterVo1.getStartTime().getTime());

            gainApprovalId0.setNextToken(nextToken);
            gainApprovalId0.setMaxResults(10L);
            R<GainApprovalIdResult> gainApprovalIdResult = remoteFlowService.gainApprovalList(gainApprovalId0);
            nextToken = Long.valueOf(gainApprovalIdResult.getData().getNextToken());

            for (String instanceId : gainApprovalIdResult.getData().getList()) {
                String res = attendLeaveRegisterMapper.selectProcessInstanceIds(instanceId);

                //判断是否需要新增
                if (res == null) {
                    GainApproval gainApproval = new GainApproval();
                    AttendLeaveRegister attendLeaveRegister = new AttendLeaveRegister();
                    gainApproval.setProessInstanceId(instanceId);
                    R<GainApprovalResult> gainApprovalResultR = remoteFlowService.detailsApproval(gainApproval);

                    String ddStatus = gainApprovalResultR.getData().getStatus();
                    Integer status = null;
                    if (gainApprovalResultR.getData().getResult().equals("refuse")){
                        status = 2;
                    }else if(ddStatus.equals("RUNNING")){
                        status = 0;
                    }else if(ddStatus.equals("TERMINATED")) {
                        status = 3;
                    } else if (ddStatus.equals("COMPLETED")) {
                        status = 1;
                    }

                    attendLeaveRegister.setState(status);
                    attendLeaveRegister.setUpdateTime(new Date());
                    attendLeaveRegister.setProcessInstanceId(instanceId);

                    List<FormComponentValues> result = gainApprovalResultR.getData().getFormComponentValues();
                    for (FormComponentValues formComponentValues : result) {
                        if (formComponentValues.getComponentType().equals("DDTextField")) {
                            attendLeaveRegister.setLeaveReason(formComponentValues.getValue());
                        }
                        if (formComponentValues.getComponentType().equals("DDSelectField")) {
                            attendLeaveRegister.setLeaveType(formComponentValues.getValue());
                        }
                        if (formComponentValues.getComponentType().equals("DDDateRangeField") || formComponentValues.getName().equals("[\"开始时间\",\"结束时间\"]")) {
                            String[] dataRange = formComponentValues.getValue().split("~");
                            attendLeaveRegister.setStartTime(DateUtils.dateTime("yyyy-MM-dd'T'HH:mm", dataRange[0]));
                            attendLeaveRegister.setEndTime(DateUtils.dateTime("yyyy-MM-dd'T'HH:mm", dataRange[1]));
                        }
                    }
                    attendLeaveRegisterMapper.insertAttendLeaveRegister(attendLeaveRegister);
                }

            }
        }

        for (AttendBusinessRegisterVo attendBusinessRegisterVo1 : startTimeList1) {
            GainApprovalId gainApprovalId0 = new GainApprovalId();
            gainApprovalId0.setProcessCode(leaveProcessCode);
            gainApprovalId0.setStartTime(attendBusinessRegisterVo1.getStartTime().getTime());

            gainApprovalId0.setNextToken(nextToken);
            gainApprovalId0.setMaxResults(10L);
            R<GainApprovalIdResult> gainApprovalIdResult = remoteFlowService.gainApprovalList(gainApprovalId0);
            nextToken = Long.valueOf(gainApprovalIdResult.getData().getNextToken());

            for (String instanceId : gainApprovalIdResult.getData().getList()) {
                String res = attendBusinessRegisterMapper.selectProcessInstanceIds(instanceId);

                //判断是否需要新增
                if (res == null) {
                    GainApproval gainApproval = new GainApproval();
                    AttendBusinessRegister attendBusinessRegister = new AttendBusinessRegister();
                    gainApproval.setProessInstanceId(instanceId);
                    R<GainApprovalResult> gainApprovalResultR = remoteFlowService.detailsApproval(gainApproval);

                    String ddStatus = gainApprovalResultR.getData().getStatus();
                    Integer status = null;
                    if (gainApprovalResultR.getData().getResult().equals("refuse")){
                        status = 2;
                    }else if(ddStatus.equals("RUNNING")){
                        status = 0;
                    }else if(ddStatus.equals("TERMINATED")) {
                        status = 3;
                    } else if (ddStatus.equals("COMPLETED")) {
                        status = 1;
                    }

                    attendBusinessRegister.setState(status);
                    attendBusinessRegister.setUpdateTime(new Date());
                    attendBusinessRegister.setProcessInstanceId(instanceId);

                    List<FormComponentValues> result = gainApprovalResultR.getData().getFormComponentValues();
                    for (FormComponentValues formComponentValues : result) {
                        if (formComponentValues.getComponentType().equals("DDTextField") || formComponentValues.getName().equals("出差地点")) {
                            attendBusinessRegister.setBusinessLocation(formComponentValues.getValue());
                        }
                        if (formComponentValues.getComponentType().equals("DDTextField") || formComponentValues.getName().equals("出差事由")) {
                            attendBusinessRegister.setBusinessLocation(formComponentValues.getValue());
                        }
                        if (formComponentValues.getComponentType().equals("DDDateRangeField") || formComponentValues.getName().equals("[\"开始时间\",\"结束时间\"]")) {
                            String[] dataRange = formComponentValues.getValue().split("~");
                            attendBusinessRegister.setStartTime(DateUtils.dateTime("yyyy-MM-dd'T'HH:mm", dataRange[0]));
                            attendBusinessRegister.setEndTime(DateUtils.dateTime("yyyy-MM-dd'T'HH:mm", dataRange[1]));
                        }
                        attendBusinessRegisterMapper.insertAttendBusinessRegister(attendBusinessRegister);
                    }

                }
            }
        }
            for (AttendOvertimeRegisterVo attendOvertimeRegisterVo1 : startTimeList2) {
                GainApprovalId gainApprovalId0 = new GainApprovalId();
                gainApprovalId0.setProcessCode(leaveProcessCode);
                gainApprovalId0.setStartTime(attendOvertimeRegisterVo1.getOvertimeStartTime().getTime());

                gainApprovalId0.setNextToken(nextToken);
                gainApprovalId0.setMaxResults(10L);
                R<GainApprovalIdResult> gainApprovalIdResult = remoteFlowService.gainApprovalList(gainApprovalId0);
                nextToken = Long.valueOf(gainApprovalIdResult.getData().getNextToken());

                for (String instanceId : gainApprovalIdResult.getData().getList()) {
                    String res = attendOvertimeRegisterMapper.selectProcessInstanceIds(instanceId);

                    //判断是否需要新增
                    if (res == null) {
                        GainApproval gainApproval = new GainApproval();
                        AttendOvertimeRegister attendOvertimeRegister = new AttendOvertimeRegister();
                        gainApproval.setProessInstanceId(instanceId);
                        R<GainApprovalResult> gainApprovalResultR = remoteFlowService.detailsApproval(gainApproval);

                        String ddStatus = gainApprovalResultR.getData().getStatus();
                        Integer status = null;
                        if (gainApprovalResultR.getData().getResult().equals("refuse")){
                            status = 2;
                        }else if(ddStatus.equals("RUNNING")){
                            status = 0;
                        }else if(ddStatus.equals("TERMINATED")) {
                            status = 3;
                        } else if (ddStatus.equals("COMPLETED")) {
                            status = 1;
                        }
                        attendOvertimeRegister.setState(status);
                        attendOvertimeRegister.setUpdateTime(new Date());
                        attendOvertimeRegister.setProcessInstanceId(instanceId);

                        List<FormComponentValues> result = gainApprovalResultR.getData().getFormComponentValues();
                        for (FormComponentValues formComponentValues : result) {
                            if (formComponentValues.getComponentType().equals("DDTextField") || formComponentValues.getName().equals("加班事由")) {
                                attendOvertimeRegister.setReason(formComponentValues.getValue());
                            }
                            if (formComponentValues.getComponentType().equals("DDDateRangeField") || formComponentValues.getName().equals("[\"加班开始时间\",\"加班结束时间\"]")) {
                                String[] dataRange = formComponentValues.getValue().split("~");
                                attendOvertimeRegister.setOvertimeStartTime(DateUtils.dateTime("yyyy-MM-dd'T'HH:mm", dataRange[0]));
                                attendOvertimeRegister.setOvertimeEndTime(DateUtils.dateTime("yyyy-MM-dd'T'HH:mm", dataRange[1]));
                            }
                            if (formComponentValues.getComponentType().equals("DDDateRangeField") || formComponentValues.getName().equals("[\"调休开始时间\",\"调休结束时间\"]")) {
                                String[] dataRange = formComponentValues.getValue().split("~");
                                attendOvertimeRegister.setRestTimeStartTime(DateUtils.dateTime("yyyy-MM-dd'T'HH:mm", dataRange[0]));
                                attendOvertimeRegister.setRestTimeEndTime(DateUtils.dateTime("yyyy-MM-dd'T'HH:mm", dataRange[1]));
                            }
                        }
                        attendOvertimeRegisterMapper.insertAttendOvertimeRegister(attendOvertimeRegister);
                    }

                }
            }

        }




    @Override
    public int updateProgressTracking() throws ApiException {
        List<AttendProcurement> attendProcurementList = attendProcurementMapper.selectAttendProcurementList(new AttendProcurement());
        int updatedCount = 0;

        for (AttendProcurement attendProcurement : attendProcurementList) {
            if ( attendProcurement.getState() != 3) {
                GainApproval gainApproval = new GainApproval();
                gainApproval.setProessInstanceId(attendProcurement.getProcessInstanceId());
                R<GainApprovalResult> result = remoteFlowService.detailsApproval(gainApproval);

                if (result != null && result.getCode() == 200) {
                    GainApprovalResult gainApprovalResult = result.getData();
                    switch (gainApprovalResult.getStatus()) {
                        case "RUNNING":
                            attendProcurement.setState(2L);
                            break;
                        case "TERMINATED":
                            attendProcurement.setState(5L);
                            break;
                        case "COMPLETED":
                            attendProcurement.setState(3L);
                            break;
                    }
                    if ("refuse".equals(gainApprovalResult.getResult())) {
                        attendProcurement.setState(4L);
                    }

                    if (attendProcurement.getContractAnnex() == null ||attendProcurement.getProcurementMethod() == null) {
                        for (FormComponentValues formComponentValues : gainApprovalResult.getFormComponentValues())
                            if (formComponentValues.getComponentType().equals("DDAttachment")) {

                                attendProcurement.setContractAnnex(formComponentValues.getValue());
                            }else if
                            ("DDSelectField-method".equals(formComponentValues.getId())) {
                            if ("网超采购".equals(formComponentValues.getValue())) {
                                attendProcurement.setProcurementMethod(1L);
                            } else if ("委托招标".equals(formComponentValues.getValue())) {
                                attendProcurement.setProcurementMethod(2L);
                            }
                        }
                    }
                    attendProcurement.setUpdateTime(new Date());
                    int updateResult = attendProcurementMapper.updateAttendProcurement(attendProcurement);
                    if (updateResult > 0) {
                        updatedCount++;
                    }
                }
            }
        }
        return updatedCount;
    }
//
//    /**
//     * 同步采购钉钉列表
//     * @throws ApiException
//     */
//    public void  updateProgressList() throws ApiException {
//        // 获取当前时间的 Instant 对象
//        Instant now = Instant.now();
//
//        // 减去7天得到七天前的时间
//        Instant sevenDaysAgo = now.minus(7, ChronoUnit.DAYS);
//
//        // 转换成时间戳（毫秒）
//        long timestamp = sevenDaysAgo.toEpochMilli();
//        GainApprovalId gainApprovalId = new GainApprovalId();
//        gainApprovalId.setStartTime(timestamp);
//        gainApprovalId.setNextToken(0L);
//        gainApprovalId.setProcessCode(dingdingConfig.getBusinessProcessCode());
//        gainApprovalId.setMaxResults(10L);
//        R<GainApprovalIdResult> result = remoteFlowService.gainApprovalList(gainApprovalId);
//        while (result.getData().getNextToken() != null) {
//            for (String processInstanceId : result.getData().getList()) {
//
//            }
//        }
//
//        R<GainApprovalIdResult> result = remoteFlowService.gainApprovalList(gainApprovalId);
//    }
}
