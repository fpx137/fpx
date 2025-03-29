package com.ruoyi.job.mapper;


import com.ruoyi.job.domain.AttendProcurement;
import com.ruoyi.job.vo.SysUserVo;


import java.util.List;

/**
 * 采购申请Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-12
 */
public interface AttendProcurementMapper
{
    /**
     * 查询采购申请
     *
     * @param id 采购申请主键
     * @return 采购申请
     */
     AttendProcurement selectAttendProcurementById(Long id);

    /**
     * 查询采购申请列表
     *
     * @param attendProcurement 采购申请
     * @return 采购申请集合
     */
     List<AttendProcurement> selectAttendProcurementList(AttendProcurement attendProcurement);




    /**
     * 新增采购申请
     *
     * @param attendProcurement 采购申请
     * @return 结果
     */
     int insertAttendProcurement(AttendProcurement attendProcurement);

    /**
     * 修改采购申请
     *
     * @param attendProcurement 采购申请
     * @return 结果
     */
     int updateAttendProcurement(AttendProcurement attendProcurement);

    /**
     * 删除采购申请
     *
     * @param id 采购申请主键
     * @return 结果
     */
     int deleteAttendProcurementById(Long id);

    /**
     * 批量删除采购申请
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
     int deleteAttendProcurementByIds(Long[] ids);

    /**
     * 根据用户id查询钉钉用户id
     * @param userId
     * @return
     */
     SysUserVo selectDingTalkUserIdByUserId(Long userId);
}
