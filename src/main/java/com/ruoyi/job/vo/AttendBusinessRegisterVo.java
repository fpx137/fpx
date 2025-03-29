package com.ruoyi.job.vo;


import com.ruoyi.job.domain.SysFlow.AttendBusinessRegister;
import org.springframework.stereotype.Component;

/**
 * @author kano
 * @date 2023/8/10
 */
@Component
public class AttendBusinessRegisterVo extends AttendBusinessRegister {

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
