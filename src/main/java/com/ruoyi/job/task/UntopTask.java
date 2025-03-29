package com.ruoyi.job.task;

import com.ruoyi.job.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("UntopTask")
public class UntopTask {

    @Autowired
    private ISysNoticeService sysNoticeService;

    public void unTopNotice() throws Exception {
        sysNoticeService.unNoticeTop();
    }

}
