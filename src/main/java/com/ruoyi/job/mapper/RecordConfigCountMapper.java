package com.ruoyi.job.mapper;

import com.ruoyi.job.domain.AttendRecordDayStatics;
import com.ruoyi.job.vo.RecordRegisterVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author ruoyi
 * @date 2024-11-21
 */
public interface RecordConfigCountMapper
{
    //统计配置的签到次数
    int countUpTimes();
    //统计配置的签退次数
    int countDownTimes();
}
