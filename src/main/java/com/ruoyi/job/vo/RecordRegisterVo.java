package com.ruoyi.job.vo;

import java.util.Date;

public class RecordRegisterVo {

    private Long id;

    private Long userId;

    private Long recordConfigId;
    //登记次序
    private Integer recordOrder;

    /** 登记类型,字段1上班签到，2下班签退 */
    private String recordType;

    /** 规定时间 */
    private Date recordTime;

    /** 签到时间 */
    private Date registerTime;
    /** 备注 */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public Long getRecordConfigId() {
        return recordConfigId;
    }

    public void setRecordConfigId(Long recordConfigId) {
        this.recordConfigId = recordConfigId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRecordOrder() {
        return recordOrder;
    }

    public void setRecordOrder(Integer recordOrder) {
        this.recordOrder = recordOrder;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
