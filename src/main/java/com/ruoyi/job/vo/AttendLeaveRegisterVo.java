package com.ruoyi.job.vo;

import com.ruoyi.job.domain.SysFlow.AttendLeaveRegister;

/**
 * @author kano
 * @date 2023/8/10
 */
public class AttendLeaveRegisterVo extends AttendLeaveRegister {

    private String nickname;

    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
