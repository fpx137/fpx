package com.ruoyi.job.mapper.SysFlowMapper;


import com.ruoyi.job.domain.SysFlow.AttendLeaveRegister;
import com.ruoyi.job.vo.AttendLeaveRegisterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AttendLeaveRegisterMapper
{

    void updateStateByProcessInstanceId(AttendLeaveRegister attendLeaveRegister);

    List<String> selectProcessInstanceId();
    public int insertAttendLeaveRegister(AttendLeaveRegister attendLeaveRegister);

    String selectProcessInstanceIds(String processInstanceId);
    public List<AttendLeaveRegisterVo> selectAttendLeaveRegisterList(AttendLeaveRegisterVo attendLeaveRegisterVo, @Param("userId")Long userId);

    String findIdByProcessInstance(Long id);
}
