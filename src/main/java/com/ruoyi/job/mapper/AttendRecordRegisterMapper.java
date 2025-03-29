package com.ruoyi.job.mapper;

import com.ruoyi.job.domain.AttendRecordRegister;

/**
 * 考勤登记明细Mapper接口
 *
 * @author Qiuweihuang
 * @date 2023-08-09
 */
public interface AttendRecordRegisterMapper
{
    //考勤操作
    public int insertAttendRecord(AttendRecordRegister attendRecordRegister);
}
