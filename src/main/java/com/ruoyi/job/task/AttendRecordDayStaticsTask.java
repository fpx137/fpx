package com.ruoyi.job.task;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.job.service.IAttendRecordDayStaticsService;
import com.ruoyi.job.service.ISysNoticeService;
import com.ruoyi.job.service.Impl.AttendRecordDayStaticsServiceImpl;
import com.ruoyi.job.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 考勤统计定时任务
 */
@Component("attendRecordDayStaticsTask")
public class AttendRecordDayStaticsTask {
    private static final Logger logger = LoggerFactory.getLogger(AttendRecordDayStaticsTask.class);
    @Autowired
    private IAttendRecordDayStaticsService attendRecordDayStaticsService;


    /**
     *统计考勤记录-个人
     * @param userId        用户ID，必填
     * @param startDate     开始时间，格式：yyyy-MM-dd，必填
     * @param endDate       结束时间，格式：yyyy-MM-dd，选填
     * @throws Exception
     */
    public void statics(Long userId,String startDate,String endDate) throws Exception {

        if(userId == null){
            logger.error("userId is null");
            return;
        }
        if(StringUtils.isBlank(startDate)){
            logger.error("startDate is null");
            return;
        }

        Date start =DateUtils.parseDate(startDate,"yyyy-MM-dd");
        Date end = null;
        if(StringUtils.isBlank(endDate)){
            end = start;
        }else{
            DateUtils.parseDate(endDate,"yyyy-MM-dd");
        }
        attendRecordDayStaticsService.statics(userId,start,end);
    }

    public void staticsAll(String startDate) throws Exception {
        staticsAll(startDate,startDate);
    }

    /**
     *统计考勤记录-所有人
     * @param startDate     开始时间，格式：yyyy-MM-dd，必填
     * @param endDate       结束时间，格式：yyyy-MM-dd，选填
     * @throws Exception
     */
    public void staticsAll(String startDate,String endDate) throws Exception {
        if(StringUtils.isBlank(startDate)){
            logger.error("startDate is null");
            return;
        }
        if(StringUtils.isBlank(startDate)){
            logger.error("startDate is null");
            return;
        }

        Date start =DateUtils.parseDate(startDate,"yyyy-MM-dd");
        Date end = null;
        if(StringUtils.isBlank(endDate)){
            end = start;
        }else{
            DateUtils.parseDate(endDate,"yyyy-MM-dd");
        }
        attendRecordDayStaticsService.staticsAll(start,end);
    }

    public void staticsYesterday() throws Exception {
        Date date = DateUtils.getYesterday();
        attendRecordDayStaticsService.staticsAll(date,date);
    }

}
