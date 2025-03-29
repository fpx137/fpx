package com.ruoyi.job.service;

import com.taobao.api.ApiException;

public interface DingDingService {
     void updateStatus() throws ApiException;

     void updateApprovalList() throws Exception;

     int updateProgressTracking() throws ApiException;

}
