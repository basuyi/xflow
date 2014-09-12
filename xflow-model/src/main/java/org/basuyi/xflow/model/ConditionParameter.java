package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConditionParameter implements Serializable {

    /** identifier field */
    private String conditionId;

    /** identifier field */
    private Long paramId;

    /** identifier field */
    private Long conditionParamId;

    /** full constructor */
    public ConditionParameter(String conditionId, Long paramId, Long conditionParamId) {
        this.conditionId = conditionId;
        this.paramId = paramId;
        this.conditionParamId = conditionParamId;
    }

    /** default constructor */
    public ConditionParameter() {
    }

    public String getConditionId() {
        return this.conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public Long getParamId() {
        return this.paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public Long getConditionParamId() {
        return this.conditionParamId;
    }

    public void setConditionParamId(Long conditionParamId) {
        this.conditionParamId = conditionParamId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("conditionId", getConditionId())
            .append("paramId", getParamId())
            .append("conditionParamId", getConditionParamId())
            .toString();
    }

}
