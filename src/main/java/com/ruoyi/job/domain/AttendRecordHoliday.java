package com.ruoyi.job.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 节假日登记对象 attend_record_holiday
 *
 * @author ruoyi
 * @date 2024-12-10
 */
public class AttendRecordHoliday extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 年份 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date year;

    /** 类型，1假期，2上班日 */
    @Excel(name = "类型，1假期，2上班日")
    private String holidayType;

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 创建者 */
    @Excel(name = "创建者")
    private Long creator;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setYear(Date year)
    {
        this.year = year;
    }

    public Date getYear()
    {
        return year;
    }
    public void setHolidayType(String holidayType)
    {
        this.holidayType = holidayType;
    }

    public String getHolidayType()
    {
        return holidayType;
    }
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }
    public void setCreator(Long creator)
    {
        this.creator = creator;
    }

    public Long getCreator()
    {
        return creator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("year", getYear())
            .append("holidayType", getHolidayType())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("createTime", getCreateTime())
            .append("creator", getCreator())
            .append("remark", getRemark())
            .toString();
    }
}
