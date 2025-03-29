package com.ruoyi.job.mapper.SysFlowMapper;


import com.ruoyi.job.domain.SysFlow.AttendBusinessRegister;
import com.ruoyi.job.domain.SysFlow.AttendOvertimeRegister;
import com.ruoyi.job.vo.AttendOvertimeRegisterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AttendOvertimeRegisterMapper
{

    void updateStateByProcessInstanceId(AttendOvertimeRegister attendOvertimeRegister);
    public int insertAttendOvertimeRegister(AttendOvertimeRegister attendOvertimeRegister);
    public List<AttendOvertimeRegisterVo> selectAttendOvertimeRegisterList(AttendOvertimeRegisterVo attendOvertimeRegisterVo, @Param("userId")Long userId);

    List<String> selectProcessInstanceId();
    String selectProcessInstanceIds(String processInstanceId);

    String findIdByProcessInstance(Long id);
}
