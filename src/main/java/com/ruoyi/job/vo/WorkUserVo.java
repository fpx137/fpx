package com.ruoyi.job.vo;

import java.util.Date;

/**
 * @author kano
 * @date 2023/8/2
 */

public class WorkUserVo {

    private Long id;

    private Date createTime;

    @Override
    public String toString() {
        return "WorkUserVo{" +
                "id=" + id +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}



