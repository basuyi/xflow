package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfTransAction implements Serializable {

    /** identifier field */
    private Long transConditionId;

    /** nullable persistent field */
    private Long activityTransId;

    /** nullable persistent field */
    private String actionId;

    /** full constructor */
    public WfTransAction(Long transConditionId, Long activityTransId, String actionId) {
        this.transConditionId = transConditionId;
        this.activityTransId = activityTransId;
        this.actionId = actionId;
    }

    /** default constructor */
    public WfTransAction() {
    }

    /** minimal constructor */
    public WfTransAction(Long transConditionId) {
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

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("transConditionId", getTransConditionId())
            .toString();
    }

}
