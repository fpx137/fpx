package com.ruoyi.job.mapper;

import com.ruoyi.job.domain.AttendRecordDayStatics;
import com.ruoyi.job.domain.AttendRecordRegister;
import com.ruoyi.job.domain.SysFlow.AttendBusinessRegister;
import com.ruoyi.job.domain.SysFlow.AttendLeaveRegister;
import com.ruoyi.job.domain.SysFlow.AttendOutgoingRegister;
import com.ruoyi.job.vo.RecordRegisterVo;
import com.ruoyi.job.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 考勤记录按天统计Mapper接口
 *
 * @author ruoyi
 * @date 2024-11-21
 */
public interface AttendRecordDayStaticsMapper
{
    /**
     * 查询考勤记录按天统计
     *
     * @param id 考勤记录按天统计主键
     * @return 考勤记录按天统计
     */
    public AttendRecordDayStatics selectAttendRecordDayStaticsById(Long id);

    /**
     * 查询考勤记录按天统计列表
     *
     * @param attendRecordDayStatics 考勤记录按天统计
     * @return 考勤记录按天统计集合
     */
    public List<AttendRecordDayStatics> selectAttendRecordDayStaticsList(AttendRecordDayStatics attendRecordDayStatics);

    /**
     * 新增考勤记录按天统计
     *
     * @param attendRecordDayStatics 考勤记录按天统计
     * @return 结果
     */
    public int insertAttendRecordDayStatics(AttendRecordDayStatics attendRecordDayStatics);

    /**
     * 修改考勤记录按天统计
     *
     * @param attendRecordDayStatics 考勤记录按天统计
     * @return 结果
     */
    public int updateAttendRecordDayStatics(AttendRecordDayStatics attendRecordDayStatics);

    /**
     * 删除考勤记录按天统计
     *
     * @param id 考勤记录按天统计主键
     * @return 结果
     */
    public int deleteAttendRecordDayStaticsById(Long id);

    /**
     * 批量删除考勤记录按天统计
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttendRecordDayStaticsByIds(Long[] ids);

    int deleteByUserIdAndDate(@Param("userId") Long userId, @Param("date") Date date);
    List<RecordRegisterVo> selectRecordRegisterVoByUserIdAndDate(@Param("userId") Long userId, @Param("date") Date date);

    List<UserVo> findAllUser();

    //根据用户ID，日期（yyyy-MM-dd），查询当天的已审批通过的请假记录
    public List<AttendLeaveRegister> findLeaveByUserId(@Param("userId") Long userId, @Param("date") Date date);
    //根据用户ID，日期（yyyy-MM-dd），查询当天的已审批通过的出差记录
    public List<AttendBusinessRegister> findBusinessByUserId(@Param("userId") Long userId, @Param("date") Date date);
    //根据用户ID，日期（yyyy-MM-dd），查询当天的已审批通过的外记录
    public List<AttendOutgoingRegister> findOutgoingByUserId(@Param("userId") Long userId, @Param("date") Date date);
}
