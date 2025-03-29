package com.ruoyi.job.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 考勤记录按天统计对象 attend_record_day_statics
 *
 * @author ruoyi
 * @date 2024-11-21
 */
public class AttendRecordDayStatics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 登记日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date staticsDate;

    /** 第一次登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第一次登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dutyTime1;

    /** 第二次登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第二次登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dutyTime2;

    /** 第三次登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第三次登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dutyTime3;

    /** 第四次登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第四次登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dutyTime4;

    /** 第一次登记状态：迟到、早退 */
    @Excel(name = "第一次登记状态：迟到、早退")
    private String dutyTime1State;

    /** 第二次登记状态：迟到、早退 */
    @Excel(name = "第二次登记状态：迟到、早退")
    private String dutyTime2State;

    /** 第三次登记状态：迟到、早退 */
    @Excel(name = "第三次登记状态：迟到、早退")
    private String dutyTime3State;

    /** 第四次登记状态：迟到、早退 */
    @Excel(name = "第四次登记状态：迟到、早退")
    private String dutyTime4State;

    /** 第一次登记备注 */
    @Excel(name = "第一次登记备注")
    private String dutyTime1Remark;

    /** 第二次登记备注 */
    @Excel(name = "第二次登记备注")
    private String dutyTime2Remark;

    /** 第三次登记备注 */
    @Excel(name = "第三次登记备注")
    private String dutyTime3Remark;

    /** 第四次登记备注 */
    @Excel(name = "第四次登记备注")
    private String dutyTime4Remark;

    /** 迟到次数 */
    @Excel(name = "迟到次数")
    private Integer delayTimes;

    /** 迟到时长(秒) */
    @Excel(name = "迟到时长(秒)")
    private Long delayDurationTime;

    /** 早退次数 */
    @Excel(name = "早退次数")
    private Integer earlyTimes;

    /** 早退时长(秒) */
    @Excel(name = "早退时长(秒)")
    private Long earlyDurationTime;

    /** 上班未签到次数 */
    @Excel(name = "上班未签到次数")
    private Integer unUpWorkTimes;

    /** 下班未签到次数 */
    @Excel(name = "下班未签到次数")
    private Integer unDownWorkTimes;

    /** 外出天数 */
    @Excel(name = "外出天数")
    private BigDecimal outgoingDays;

    /** 请假天数 */
    @Excel(name = "请假天数")
    private BigDecimal leaveDays;

    /** 出差天数 */
    @Excel(name = "出差天数")
    private BigDecimal businessDays;

    /** 调休天数 */
    @Excel(name = "调休天数")
    private BigDecimal overtimeDays;

    /** 旷工天数 */
    @Excel(name = "旷工天数")
    private BigDecimal absenteeismDays;

    /** 正常考勤天数 */
    @Excel(name = "正常考勤天数")
    private BigDecimal normalDays;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setStaticsDate(Date staticsDate)
    {
        this.staticsDate = staticsDate;
    }

    public Date getStaticsDate()
    {
        return staticsDate;
    }
    public void setDutyTime1(Date dutyTime1)
    {
        this.dutyTime1 = dutyTime1;
    }

    public Date getDutyTime1()
    {
        return dutyTime1;
    }
    public void setDutyTime2(Date dutyTime2)
    {
        this.dutyTime2 = dutyTime2;
    }

    public Date getDutyTime2()
    {
        return dutyTime2;
    }
    public void setDutyTime3(Date dutyTime3)
    {
        this.dutyTime3 = dutyTime3;
    }

    public Date getDutyTime3()
    {
        return dutyTime3;
    }
    public void setDutyTime4(Date dutyTime4)
    {
        this.dutyTime4 = dutyTime4;
    }

    public Date getDutyTime4()
    {
        return dutyTime4;
    }
    public void setDutyTime1State(String dutyTime1State)
    {
        this.dutyTime1State = dutyTime1State;
    }

    public String getDutyTime1State()
    {
        return dutyTime1State;
    }
    public void setDutyTime2State(String dutyTime2State)
    {
        this.dutyTime2State = dutyTime2State;
    }

    public String getDutyTime2State()
    {
        return dutyTime2State;
    }
    public void setDutyTime3State(String dutyTime3State)
    {
        this.dutyTime3State = dutyTime3State;
    }

    public String getDutyTime3State()
    {
        return dutyTime3State;
    }
    public void setDutyTime4State(String dutyTime4State)
    {
        this.dutyTime4State = dutyTime4State;
    }

    public String getDutyTime4State()
    {
        return dutyTime4State;
    }
    public void setDutyTime1Remark(String dutyTime1Remark)
    {
        this.dutyTime1Remark = dutyTime1Remark;
    }

    public String getDutyTime1Remark()
    {
        return dutyTime1Remark;
    }
    public void setDutyTime2Remark(String dutyTime2Remark)
    {
        this.dutyTime2Remark = dutyTime2Remark;
    }

    public String getDutyTime2Remark()
    {
        return dutyTime2Remark;
    }
    public void setDutyTime3Remark(String dutyTime3Remark)
    {
        this.dutyTime3Remark = dutyTime3Remark;
    }

    public String getDutyTime3Remark()
    {
        return dutyTime3Remark;
    }
    public void setDutyTime4Remark(String dutyTime4Remark)
    {
        this.dutyTime4Remark = dutyTime4Remark;
    }

    public String getDutyTime4Remark()
    {
        return dutyTime4Remark;
    }

    public Integer getDelayTimes() {
        return delayTimes;
    }

    public void setDelayTimes(Integer delayTimes) {
        this.delayTimes = delayTimes;
    }

    public Long getDelayDurationTime() {
        return delayDurationTime;
    }

    public void setDelayDurationTime(Long delayDurationTime) {
        this.delayDurationTime = delayDurationTime;
    }

    public Integer getEarlyTimes() {
        return earlyTimes;
    }

    public void setEarlyTimes(Integer earlyTimes) {
        this.earlyTimes = earlyTimes;
    }

    public void setEarlyDurationTime(Long earlyDurationTime)
    {
        this.earlyDurationTime = earlyDurationTime;
    }

    public Long getEarlyDurationTime()
    {
        return earlyDurationTime;
    }

    public void setOutgoingDays(BigDecimal outgoingDays)
    {
        this.outgoingDays = outgoingDays;
    }

    public BigDecimal getOutgoingDays()
    {
        return outgoingDays;
    }
    public void setLeaveDays(BigDecimal leaveDays)
    {
        this.leaveDays = leaveDays;
    }

    public BigDecimal getLeaveDays()
    {
        return leaveDays;
    }
    public void setBusinessDays(BigDecimal businessDays)
    {
        this.businessDays = businessDays;
    }

    public BigDecimal getBusinessDays()
    {
        return businessDays;
    }
    public void setOvertimeDays(BigDecimal overtimeDays)
    {
        this.overtimeDays = overtimeDays;
    }

    public BigDecimal getOvertimeDays()
    {
        return overtimeDays;
    }
    public void setAbsenteeismDays(BigDecimal absenteeismDays)
    {
        this.absenteeismDays = absenteeismDays;
    }

    public BigDecimal getAbsenteeismDays()
    {
        return absenteeismDays;
    }
    public void setNormalDays(BigDecimal normalDays)
    {
        this.normalDays = normalDays;
    }

    public BigDecimal getNormalDays()
    {
        return normalDays;
    }

    public Integer getUnUpWorkTimes() {
        return unUpWorkTimes;
    }

    public void setUnUpWorkTimes(Integer unUpWorkTimes) {
        this.unUpWorkTimes = unUpWorkTimes;
    }

    public Integer getUnDownWorkTimes() {
        return unDownWorkTimes;
    }

    public void setUnDownWorkTimes(Integer unDownWorkTimes) {
        this.unDownWorkTimes = unDownWorkTimes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("staticsDate", getStaticsDate())
            .append("createTime", getCreateTime())
            .append("dutyTime1", getDutyTime1())
            .append("dutyTime2", getDutyTime2())
            .append("dutyTime3", getDutyTime3())
            .append("dutyTime4", getDutyTime4())
            .append("dutyTime1State", getDutyTime1State())
            .append("dutyTime2State", getDutyTime2State())
            .append("dutyTime3State", getDutyTime3State())
            .append("dutyTime4State", getDutyTime4State())
            .append("dutyTime1Remark", getDutyTime1Remark())
            .append("dutyTime2Remark", getDutyTime2Remark())
            .append("dutyTime3Remark", getDutyTime3Remark())
            .append("dutyTime4Remark", getDutyTime4Remark())
            .append("delayTimes", getDelayTimes())
            .append("delayDurationTime", getDelayDurationTime())
            .append("earlyTimes", getEarlyTimes())
            .append("earlyDurationTime", getEarlyDurationTime())
            .append("outgoingDays", getOutgoingDays())
            .append("leaveDays", getLeaveDays())
            .append("businessDays", getBusinessDays())
            .append("overtimeDays", getOvertimeDays())
            .append("absenteeismDays", getAbsenteeismDays())
            .append("normalDays", getNormalDays())
            .toString();
    }
}
