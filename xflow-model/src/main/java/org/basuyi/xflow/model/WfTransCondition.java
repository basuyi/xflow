package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfTransCondition implements Serializable {

    /** identifier field */
    private Long transConditionId;

    /** nullable persistent field */
    private Long activityTransId;

    /** nullable persistent field */
    private String conditionId;

    /** full constructor */
    public WfTransCondition(Long transConditionId, Long activityTransId, String conditionId) {
        this.transConditionId = transConditionId;
        this.activityTransId = activityTransId;
        this.conditionId = conditionId;
    }

    /** default constructor */
    public WfTransCondition() {
    }

    /** minimal constructor */
    public WfTransCondition(Long transConditionId) {
        this.transConditionId = transConditionId;
    }

    public Long getTransConditionId() {
        return this.transConditionId;
    }

    public void setTransConditionId(Long transConditionId) {
        this.transConditionId = transConditionId;
    }

    public Long getActivityTransId() {
        return this.activityTransId;
    }

    public void setActivityTransId(Long activityTransId) {
        this.activityTransId = activityTransId;
    }

    public String getConditionId() {
        return this.conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("transConditionId", getTransConditionId())
            .toString();
    }

}
