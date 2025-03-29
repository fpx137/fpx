package com.ruoyi.job.task;

import com.ruoyi.job.service.IAttendRecordDayStaticsService;
import com.ruoyi.job.service.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("meetTask")
public class MeetTask {
    @Autowired
    private MeetService meetService;
    public void updateProgress() {
        System.out.println("执行定时任务: meetTask.updateProgress");
        meetService.updateProgress();

    }
}
