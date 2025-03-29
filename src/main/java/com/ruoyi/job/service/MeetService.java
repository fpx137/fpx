package com.ruoyi.job.service;

import com.ruoyi.job.mapper.ScienceMeetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetService {
    @Autowired
    private ScienceMeetMapper scienceMeetMapper;
    public void updateProgress(){
        scienceMeetMapper.updateProgress();
    }

}
