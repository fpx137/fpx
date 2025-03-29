package com.ruoyi.job.task;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.job.domain.AttendRecordHoliday;
import com.ruoyi.job.domain.AttendRecordRegister;
import com.ruoyi.job.mapper.AttendRecordHolidayMapper;
import com.ruoyi.job.mapper.AttendRecordRegisterMapper;
import com.ruoyi.job.service.IAttendRecordDayStaticsService;
import com.ruoyi.job.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 考勤统计定时任务
 */
@Component("attendRecordRegisterTask")
public class AttendRecordRegisterTask {
    private static final Logger logger = LoggerFactory.getLogger(AttendRecordRegisterTask.class);
    @Autowired
    private AttendRecordRegisterMapper attendRecordRegisterMapper;

    @Autowired
    private AttendRecordHolidayMapper attendRecordHolidayMapper;

    /**
     * @throws Exception
     */
    public void insertData(String userIds) throws Exception {

        String isHoliday = isHoliday(new Date());
        if(StringUtils.isNotBlank(isHoliday)){
            return;
        }

        String[] userIdArray = userIds.split(",");
        for (String userId : userIdArray) {
            AttendRecordRegister register = new AttendRecordRegister();
            register.setUserId(Long.valueOf(userId));
            register.setRecordConfigId(getConfigId(new Date()));
            register.setRegisterTime(getRegisterTime());
            register.setCreateTime(new Date());
            register.setDelFlag(0);
            attendRecordRegisterMapper.insertAttendRecord(register);
        }
    }

    private String isHoliday(Date date){
        List<AttendRecordHoliday> list = attendRecordHolidayMapper.justHoliday(date);
        if(list != null && list.size() > 0){
            return "节假日";
        }
        list = attendRecordHolidayMapper.justWorkDay(date);
        if(list != null && list.size() > 0){
            //工作日
            return null;
        }
        if(DateUtils.isWeekend(date)){
            //周末
            return "周末";
        }
        return null;
    }

    public static void main(String[] args) {

    }

    private static Date getRegisterTime(){
        //取前后20分钟内随机的时间
        Random random  = new Random();
        int randomInt = random.nextInt(1200);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,-20);
        cal.add(Calendar.SECOND,randomInt);
        return cal.getTime();
    }

    private static long getConfigId(Date date){
        Date date1 = getDate("10:00:00");
        Date date2 = getDate("13:30:00");
        Date date3 = getDate("16:00:00");
        if(date.getTime() < date1.getTime()){
            return 1L;
        }else if(date.getTime() < date2.getTime()){
            return 2L;
        }else if(date.getTime() < date3.getTime()){
            return 3L;
        }else{
            return 4L;
        }
    }

    private static  Date getDate(String timeStr){
        Date curDate = new Date();
        String date = DateUtils.dateToString(curDate,"yyyy-MM-dd");
        String dateTime = date+" "+timeStr;
        return DateUtils.getDate(dateTime,"yyyy-MM-dd HH:mm:ss");
    }

}
