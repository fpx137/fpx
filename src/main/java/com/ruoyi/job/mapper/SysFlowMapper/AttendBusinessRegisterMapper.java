package com.ruoyi.job.mapper.SysFlowMapper;


import com.ruoyi.job.domain.SysFlow.AttendBusinessRegister;
import com.ruoyi.job.domain.SysFlow.AttendLeaveRegister;
import com.ruoyi.job.vo.AttendBusinessRegisterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AttendBusinessRegisterMapper
{

    void updateStateByProcessInstanceId(AttendBusinessRegister attendBusinessRegister);

    List<String> selectProcessInstanceId();
    String selectProcessInstanceIds(String processInstanceId);

    public int insertAttendBusinessRegister(AttendBusinessRegister attendBusinessRegister);
    public List<AttendBusinessRegisterVo> selectAttendBusinessRegisterList(AttendBusinessRegisterVo attendBusinessRegisterVo, @Param("userId")Long userId);

    String findIdByProcessInstance(Long id);
}
