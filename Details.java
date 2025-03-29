package com.ruoyi.system.api.domain.SysFlow.flowexample;

import java.util.List;

public class Details {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String value;
    private String extValue;
    private String bizAlias;
    private String componentType;
    private List<Details> details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExtValue() {
        return extValue;
    }

    public void setExtValue(String extValue) {
        this.extValue = extValue;
    }

    public String getBizAlias() {
        return bizAlias;
    }

    public void setBizAlias(String bizAlias) {
        this.bizAlias = bizAlias;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }
}
