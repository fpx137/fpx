package com.ruoyi.job.mapper;


import com.ruoyi.job.domain.AttendRecordHoliday;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 节假日登记Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-10
 */
public interface AttendRecordHolidayMapper
{
    /**
     * 查询节假日登记
     *
     * @param id 节假日登记主键
     * @return 节假日登记
     */
    public AttendRecordHoliday selectAttendRecordHolidayById(Long id);

    /**
     * 查询节假日登记列表
     *
     * @param attendRecordHoliday 节假日登记
     * @return 节假日登记集合
     */
    public List<AttendRecordHoliday> selectAttendRecordHolidayList(AttendRecordHoliday attendRecordHoliday);

    /**
     * 新增节假日登记
     *
     * @param attendRecordHoliday 节假日登记
     * @return 结果
     */
    public int insertAttendRecordHoliday(AttendRecordHoliday attendRecordHoliday);

    /**
     * 修改节假日登记
     *
     * @param attendRecordHoliday 节假日登记
     * @return 结果
     */
    public int updateAttendRecordHoliday(AttendRecordHoliday attendRecordHoliday);

    /**
     * 删除节假日登记
     *
     * @param id 节假日登记主键
     * @return 结果
     */
    public int deleteAttendRecordHolidayById(Long id);

    /**
     * 批量删除节假日登记
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttendRecordHolidayByIds(Long[] ids);

    public List<AttendRecordHoliday> justHoliday(@Param("date") Date date);
    public List<AttendRecordHoliday> justWorkDay(@Param("date") Date date);
}
