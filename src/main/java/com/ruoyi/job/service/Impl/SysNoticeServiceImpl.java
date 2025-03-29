package com.ruoyi.job.service.Impl;

import com.ruoyi.job.service.ISysNoticeService;
import com.ruoyi.job.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公告 服务层实现
 *
 * @author ruoyi
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{

    @Autowired
    private SysNoticeMapper noticeMapper;

    @Override
    public int unNoticeTop() throws Exception {
        return noticeMapper.unNoticeTop();
    }


}
