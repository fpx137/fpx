package com.ruoyi.cache.bean;

import java.math.BigDecimal;

public class RlctConfigData {
    /** 配置表主键id */
    private Long id;
    private String attribute;
    private String param;
    private BigDecimal score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
