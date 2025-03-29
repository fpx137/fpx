package com.ruoyi.job.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.job.domain.AttendRecordDayStatics;
import com.ruoyi.job.domain.AttendRecordHoliday;
import com.ruoyi.job.domain.AttendRecordRegister;
import com.ruoyi.job.domain.SysFlow.AttendBusinessRegister;
import com.ruoyi.job.domain.SysFlow.AttendLeaveRegister;
import com.ruoyi.job.domain.SysFlow.AttendOutgoingRegister;
import com.ruoyi.job.mapper.AttendRecordDayStaticsMapper;
import com.ruoyi.job.mapper.AttendRecordHolidayMapper;
import com.ruoyi.job.mapper.RecordConfigCountMapper;
import com.ruoyi.job.service.IAttendRecordDayStaticsService;
import com.ruoyi.job.util.DateUtils;
import com.ruoyi.job.vo.RecordRegisterVo;
import com.ruoyi.job.vo.UserVo;
import com.ruoyi.system.api.factory.RemoteFlowFallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 考勤记录按天统计Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-21
 */
@Service
public class AttendRecordDayStaticsServiceImpl implements IAttendRecordDayStaticsService
{
    private static final Logger logger = LoggerFactory.getLogger(AttendRecordDayStaticsServiceImpl.class);
    @Autowired
    private AttendRecordDayStaticsMapper attendRecordDayStaticsMapper;
    @Autowired
    private RecordConfigCountMapper recordConfigCountMapper;
    @Autowired
    private AttendRecordHolidayMapper attendRecordHolidayMapper;


    /**
     *
     * @param userId    用户ID
     * @param date      日期
     */
    public void statics(long userId, Date date) {
        AttendRecordDayStatics statics = new AttendRecordDayStatics();
        logger.info("开始统计-userId="+userId+",date="+DateUtils.dateToString(date,"yyyy-MM-dd"));

        //判断是否是周末或节假日
        String isHoliday = isHoliday(date);
        if(StringUtils.isNotBlank(isHoliday)){
            statics.setDutyTime1State(isHoliday);
            statics.setDutyTime2State(isHoliday);
            statics.setDutyTime3State(isHoliday);
            statics.setDutyTime4State(isHoliday);
        }else{
            staticsWorkDay(statics,userId,date);
        }
        statics.setUserId(userId);
        statics.setStaticsDate(date);
        statics.setCreateTime(new Date());
        attendRecordDayStaticsMapper.deleteByUserIdAndDate(userId,date);
        attendRecordDayStaticsMapper.insertAttendRecordDayStatics(statics);
        logger.info("结束统计-userId="+userId+",date="+DateUtils.dateToString(date,"yyyy-MM-dd"));
    }

    //设置第1-4的登记时间和备注
    private void setRegisterAndRemark(AttendRecordDayStatics statics,List<RecordRegisterVo> recordRegisterVoList){
        for (RecordRegisterVo registerVo : recordRegisterVoList) {
            if(registerVo.getRecordOrder() == 1 && StringUtils.isBlank(statics.getDutyTime1State())){
                statics.setDutyTime1(registerVo.getRegisterTime());
                statics.setDutyTime1Remark(registerVo.getRemark());
            }else if(registerVo.getRecordOrder() == 2 && StringUtils.isBlank(statics.getDutyTime2State())){
                statics.setDutyTime2(registerVo.getRegisterTime());
                statics.setDutyTime2Remark(registerVo.getRemark());
            } else if(registerVo.getRecordOrder() == 3 && StringUtils.isBlank(statics.getDutyTime3State())){
                statics.setDutyTime3(registerVo.getRegisterTime());
                statics.setDutyTime3Remark(registerVo.getRemark());
            } else if(registerVo.getRecordOrder() == 4 && StringUtils.isBlank(statics.getDutyTime4State())){
                statics.setDutyTime4(registerVo.getRegisterTime());
                statics.setDutyTime4Remark(registerVo.getRemark());
            }
        }
    }

    //登记状态：迟到、早退,迟到次数,迟到时长(秒),早退次数,早退时长(秒)
    private void setDelayState(AttendRecordDayStatics statics,List<RecordRegisterVo> recordRegisterVoList){
        statics.setDelayTimes(0);//初始迟到次数
        statics.setDelayDurationTime(0L);//初始迟到时长(秒)
        statics.setEarlyTimes(0);//初始早退次数
        statics.setEarlyDurationTime(0L);//初始早退时长(秒)
        for (RecordRegisterVo registerVo : recordRegisterVoList) {
            if(registerVo.getRecordOrder() == 1 && StringUtils.isBlank(statics.getDutyTime1State())){//DutyTimexState==null,意味着已经有状态了，不需要设置迟到早退
                statics.setDutyTime1State(createDutyTime1State(statics,registerVo));
            }else if(registerVo.getRecordOrder() == 2 && StringUtils.isBlank(statics.getDutyTime2State())){
                statics.setDutyTime2State(createDutyTime1State(statics,registerVo));
            } else if(registerVo.getRecordOrder() == 3 && StringUtils.isBlank(statics.getDutyTime3State())){
                statics.setDutyTime3State(createDutyTime1State(statics,registerVo));
            } else if(registerVo.getRecordOrder() == 4 && StringUtils.isBlank(statics.getDutyTime4State())){
                statics.setDutyTime4State(createDutyTime1State(statics,registerVo));
            }
        }
    }


    private String createDutyTime1State(AttendRecordDayStatics statics,RecordRegisterVo registerVo){
        String state = null;
        Date registerTime = registerVo.getRegisterTime();
        Date recordTime = getRecordTime(registerVo.getRecordTime(),registerTime);
        if(registerVo.getRecordType().equals("1")){
            //签到
            if(registerTime.getTime() > recordTime.getTime()){
                //迟到
                state = "迟到";
                statics.setDelayTimes(statics.getDelayTimes()+1);
                statics.setDelayDurationTime(DateUtils.getDiffSecond(registerTime,recordTime));
            }
        }else{
            //签退
            if(registerTime.getTime() < recordTime.getTime()){
                //早退
                state = "早退";
                statics.setEarlyTimes(statics.getEarlyTimes()+1);
                statics.setEarlyDurationTime(DateUtils.getDiffSecond(recordTime,registerTime));
            }
        }
        return state;
    }

    //设置上班未签到次数,下班未签到次数
    private void setUnUpDownWorkTimes(AttendRecordDayStatics statics,List<RecordRegisterVo> recordRegisterVoList){
        int upWorkTimes = 0;//上班未签到次数
        int downWorkTimes = 0;//下班未签退次数
        int countUpTimes = recordConfigCountMapper.countUpTimes();
        int countDownTimes = recordConfigCountMapper.countDownTimes();
        for (RecordRegisterVo registerVo : recordRegisterVoList) {
            if(registerVo.getRecordType().equals("1")){
                //签到
                upWorkTimes++;
            }else{
                //签退
                downWorkTimes++;
            }
        }
        statics.setUnUpWorkTimes(countUpTimes-upWorkTimes);
        statics.setUnDownWorkTimes(countDownTimes-downWorkTimes);
    }


    private void setDutyTimeState(AttendRecordDayStatics statics,AttendRecordDayStatics record){
        if(StringUtils.isBlank(statics.getDutyTime1State())){
            statics.setDutyTime1State(record.getDutyTime1State());
        }
        if(StringUtils.isBlank(statics.getDutyTime2State())){
            statics.setDutyTime2State(record.getDutyTime2State());
        }
        if(StringUtils.isBlank(statics.getDutyTime3State())){
            statics.setDutyTime3State(record.getDutyTime3State());
        }
        if(StringUtils.isBlank(statics.getDutyTime4State())){
            statics.setDutyTime4State(record.getDutyTime4State());
        }
    }


    //设置请假状态
    private void setLeaveState(AttendRecordDayStatics statics,Long userId,Date date){
        AttendRecordDayStatics record = staticsLeaveUserId(userId,date);
        setDutyTimeState(statics,record);
        statics.setLeaveDays(record.getLeaveDays());
    }

    //设置请假状态
    private void setBusinessState(AttendRecordDayStatics statics,Long userId,Date date){
        AttendRecordDayStatics record = staticsBusinessUserId(userId,date);
        setDutyTimeState(statics,record);
        statics.setBusinessDays(record.getBusinessDays());
    }

    //设置出差状态
    private void setOutgoingState(AttendRecordDayStatics statics,Long userId,Date date){
        AttendRecordDayStatics record = staticsOutgoingUserId(userId,date);
        setDutyTimeState(statics,record);
        statics.setOutgoingDays(record.getOutgoingDays());
    }

    /**
     * 工作日的各种情况统计
     * @param statics
     * @param userId
     * @param date
     */
    private void staticsWorkDay(AttendRecordDayStatics statics,Long userId,Date date){

        List<RecordRegisterVo> recordRegisterVoList = attendRecordDayStaticsMapper.selectRecordRegisterVoByUserIdAndDate(userId,date);
        //去掉多次签到签退的记录，只保留一次
        recordRegisterVoList = removeRepeat(recordRegisterVoList);



        //设置请假状态
        setLeaveState(statics,userId,date);

        //设置出差状态
        setBusinessState(statics,userId,date);

        //设置出差状态
        setOutgoingState(statics,userId,date);

        //登记状态：迟到、早退,迟到次数,迟到时长(秒),早退次数,早退时长(秒)
        setDelayState(statics,recordRegisterVoList);

        //设置第1-4的登记时间和备注
        setRegisterAndRemark(statics,recordRegisterVoList);

        //设置上班未签到次数,下班未签到次数
        setUnUpDownWorkTimes(statics,recordRegisterVoList);



    }

    public static void main(String[] args) {
        Date date = DateUtils.parseDate("2024-12-07","yyyy-MM-dd");
        System.out.println(DateUtils.isWeekend(date));
    }

    /**
     * 判断是否是节假日或周末
     * @param date
     * @return 返回null(工作日)，周末，节假日
     */
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



    //去掉多次签到签退的记录，只保留一次
    //相同的recordConfigId,当是签到时保留最早记录，当是前台时保留最晚记录
    private List<RecordRegisterVo> removeRepeat(List<RecordRegisterVo> recordRegisterVoList){
        List<RecordRegisterVo> resList = new ArrayList<>();
        for (RecordRegisterVo recordRegisterVo : recordRegisterVoList) {
            resList = removeRepeat(resList,recordRegisterVo);
        }
        return resList;
    }

    private List<RecordRegisterVo> removeRepeat(List<RecordRegisterVo> recordRegisterVoList,RecordRegisterVo vo){
        List<RecordRegisterVo> resList = new ArrayList<>();
        boolean flag = false;//list中是否存在vo相同类型的
        for (RecordRegisterVo registerVo : recordRegisterVoList) {
            if(registerVo.getRecordConfigId().longValue() == vo.getRecordConfigId().longValue()){
                flag = true;
                if(registerVo.getRecordType().equals("1")){
                    //签到
                    if(registerVo.getRegisterTime().getTime() > vo.getRegisterTime().getTime()){
                        resList.add(vo);
                    }else{
                        resList.add(registerVo);
                    }
                }else{
                    //签退
                    if(registerVo.getRegisterTime().getTime() < vo.getRegisterTime().getTime()){
                        resList.add(vo);
                    }else{
                        resList.add(registerVo);
                    }
                }
            }else{
                resList.add(registerVo);
            }
        }
        if(!flag){
            resList.add(vo);
        }
        return resList;
    }


    /**
     *  根据规定时间：HH:mm:ss，获取签到当天的规定时间
     * @param recordTime
     * @return
     */
    private Date getRecordTime(Date recordTime,Date registerTime){
        String time = DateUtils.dateToString(recordTime,"HH:mm:ss");
        return getRecordTime(time,registerTime);
    }

    /**
     *  根据规定时间：HH:mm:ss，获取签到当天的规定时间
     * @param time 格式：HH:mm:ss
     * @return
     */
    private Date getRecordTime(String time,Date registerTime){
        String date = DateUtils.dateToString(registerTime,"yyyy-MM-dd");
        String dateTime = date+" "+time;
        return DateUtils.getDate(dateTime,"yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void statics(long userId, Date startDate, Date endDate) {
        List<Date> dateList = DateUtils.getDates(startDate, endDate);
        if(dateList ==null || dateList.size()==0){
            logger.error("日期区间为空");
            return;
        }

        for (Date date : dateList) {
            statics(userId,date);
        }
    }

    @Override
    public void staticsAll(Date startDate, Date endDate) {
        List<UserVo> list = attendRecordDayStaticsMapper.findAllUser();
        for (UserVo userVo : list) {
            statics(userVo.getUserId(),startDate,endDate);
        }
    }


    //统计是否请假，最小力度只能半天半天统计。
    //当天时间：开始时间<=11点前，早上请假。结束时间>=15点后，下午请假。
    private AttendRecordDayStatics staticsLeaveUserId(Long userId,Date date){
        AttendRecordDayStatics result = new AttendRecordDayStatics();
        result.setLeaveDays(BigDecimal.ZERO);
        List<AttendLeaveRegister> list = attendRecordDayStaticsMapper.findLeaveByUserId(userId,date);
        if(list == null || list.size() == 0){
            return result;
        }

        for (AttendLeaveRegister register : list) {
            Date startTime = register.getStartTime();
            Date endTime = register.getEndTime();
            //早上
            if(!isWorkMorning(startTime,endTime,date)){
                result.setDutyTime1State(getLeaveType(register));
                result.setDutyTime2State(getLeaveType(register));
                result.setLeaveDays(result.getLeaveDays().add(new BigDecimal(0.5)));
            }
            //下午
            if(!isWorkAfternoon(startTime,endTime,date)){
                result.setDutyTime3State(getLeaveType(register));
                result.setDutyTime4State(getLeaveType(register));
                result.setLeaveDays(result.getLeaveDays().add(new BigDecimal(0.5)));
            }
        }
        return result;
    }

    //统计是否出差，最小力度只能半天半天统计。
    //当天时间：开始时间<=11点前，早上出差。结束时间>=15点后，下午出差。
    private AttendRecordDayStatics staticsBusinessUserId(Long userId,Date date){
        AttendRecordDayStatics result = new AttendRecordDayStatics();
        result.setBusinessDays(BigDecimal.ZERO);
        List<AttendBusinessRegister> list = attendRecordDayStaticsMapper.findBusinessByUserId(userId,date);
        if(list == null || list.size() == 0){
            return result;
        }

        for (AttendBusinessRegister register : list) {
            Date startTime = register.getStartTime();
            Date endTime = register.getEndTime();
            //早上
            if(!isWorkMorning(startTime,endTime,date)){
                result.setDutyTime1State("出差");
                result.setDutyTime2State("出差");
                result.setBusinessDays(result.getBusinessDays().add(new BigDecimal(0.5)));
            }
            //下午
            if(!isWorkAfternoon(startTime,endTime,date)){
                result.setDutyTime3State("出差");
                result.setDutyTime4State("出差");
                result.setBusinessDays(result.getBusinessDays().add(new BigDecimal(0.5)));
            }
        }
        return result;
    }


    //统计是否外出，最小力度只能半天半天统计。
    //当天时间：开始时间<=11点前，早上外出。结束时间>=15点后，下午外出。
    private AttendRecordDayStatics staticsOutgoingUserId(Long userId,Date date){
        AttendRecordDayStatics result = new AttendRecordDayStatics();
        result.setOutgoingDays(BigDecimal.ZERO);
        List<AttendOutgoingRegister> list = attendRecordDayStaticsMapper.findOutgoingByUserId(userId,date);
        if(list == null || list.size() == 0){
            return result;
        }

        for (AttendOutgoingRegister register : list) {
            Date startTime = register.getStartTime();
            Date endTime = register.getEndTime();
            //早上
            if(!isWorkMorning(startTime,endTime,date)){
                result.setDutyTime1State("外出");
                result.setDutyTime2State("外出");
                result.setOutgoingDays(result.getOutgoingDays().add(new BigDecimal(0.5)));
            }
            //下午
            if(!isWorkAfternoon(startTime,endTime,date)){
                result.setDutyTime3State("外出");
                result.setDutyTime4State("外出");
                result.setOutgoingDays(result.getOutgoingDays().add(new BigDecimal(0.5)));
            }
        }
        return result;
    }


    /**
     * 根据传进来的不在岗时间，判断早上是否在岗
     * @param startTime     不在岗的开始时间，格式：YYYY-MM-dd HH:mm:ss
     * @param endTime       不在岗的结束时间，格式：YYYY-MM-dd HH:mm:ss
     * @param date          判断的日期，格式：YYYY-MM-dd
     * @return               true:早上在岗，false:早上不在岗
     */
    public boolean isWorkMorning(Date startTime,Date endTime,Date date){

        //情况1：日期比开始日期早，在岗
        if(DateUtils.compareDate(date,startTime) <= -1){
            return true;
        }
        //情况2：日期比结束日期晚，在岗
        if(DateUtils.compareDate(date,endTime) >= 1){
            return true;
        }

        //情况3：日期>开始日期,不在岗
        if(DateUtils.compareDate(date,startTime) >= 1){
            return false;
        }

        //情况4： 日期==开始日期，开始时间<=11点，不在岗，开始时间>11点，在岗
        Date compareTime11 = getRecordTime("11:00:00",startTime);
        if(startTime.getTime() <= compareTime11.getTime()){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 根据传进来的不在岗时间，判断下午是否在岗
     * @param startTime     不在岗的开始时间，格式：YYYY-MM-dd HH:mm:ss
     * @param endTime       不在岗的结束时间，格式：YYYY-MM-dd HH:mm:ss
     * @param date          判断的日期，格式：YYYY-MM-dd
     * @return               true:早上在岗，false:早上不在岗
     */
    public boolean isWorkAfternoon(Date startTime,Date endTime,Date date){

        //情况1：日期比开始日期早，在岗
        if(DateUtils.compareDate(date,startTime) <= -1){
            return true;
        }
        //情况2：日期比结束日期晚，在岗
        if(DateUtils.compareDate(date,endTime) >= 1){
            return true;
        }

        //情况3：日期<结束日期,不在岗
        if(DateUtils.compareDate(date,endTime) <= -1){
            return false;
        }

        //情况4： 日期==开始日期，结束时间>=15点，不在岗，开始时间<15点，在岗
        Date compareTime15 = getRecordTime("15:00:00",endTime);
        if(endTime.getTime() >= compareTime15.getTime()){
            return false;
        }else {
            return true;
        }
    }


    //判断请假类型，年休，其他类型是：请假
    private String getLeaveType(AttendLeaveRegister leaveRegister){
        if(leaveRegister.getLeaveType().equals("年休")){
            return "年休";
        }else{
            return "请假";
        }
    }
}
