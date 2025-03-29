package com.ruoyi.job.service;

import com.ruoyi.job.domain.AttendRecordDayStatics;

import java.util.Date;
import java.util.List;

/**
 * 考勤记录按天统计Service接口
 *
 * @author ruoyi
 * @date 2024-11-21
 */
public interface IAttendRecordDayStaticsService {

    /**
     * 考勤记录按天统计-统计个人
     * @param userId
     */
    public void statics(long userId, Date startDate, Date endDate);


    /**
     * 考勤记录按天统计-统计所有人
     */
    public void staticsAll(Date startDate, Date endDate);
}
