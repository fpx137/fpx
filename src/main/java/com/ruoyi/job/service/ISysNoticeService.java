package com.ruoyi.job.service;

/**
 * 公告 服务层
 *
 * @author ruoyi
 */
public interface ISysNoticeService
{
    /**
     * 取消置顶公告（定时器使用）
     */
    public int unNoticeTop () throws Exception;
}
