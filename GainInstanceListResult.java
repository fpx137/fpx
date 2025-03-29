package com.ruoyi.system.api.domain.SysFlow.flowexample.Return;

import com.ruoyi.system.api.domain.SysFlow.flowexample.WorkRecordVo;

import java.util.List;

public class GainInstanceListResult {
    private static final long serialVersionUID = 1L;
    //是否还有更多数据
    boolean has_more ;
    //事项列表
    private List<WorkRecordVo> list;


    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<WorkRecordVo> getList() {
        return list;
    }

    public void setList(List<WorkRecordVo> list) {
        this.list = list;
    }
}
