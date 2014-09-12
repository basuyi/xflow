package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ActivityPreaction implements Serializable {

    /** identifier field */
    private Long activityPreactionId;

    /** nullable persistent field */
    private String activityId;

    /** nullable persistent field */
    private String actionId;

    /** full constructor */
    public ActivityPreaction(Long activityPreactionId, String activityId, String actionId) {
        this.activityPreactionId = activityPreactionId;
        this.activityId = activityId;
        this.actionId = actionId;
    }

    /** default constructor */
    public ActivityPreaction() {
    }

    /** minimal constructor */
    public ActivityPreaction(Long activityPreactionId) {
        this.activityPreactionId = activityPreactionId;
    }

    public Long getActivityPreactionId() {
        return this.activityPreactionId;
    }

    public void setActivityPreactionId(Long activityPreactionId) {
        this.activityPreactionId = activityPreactionId;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("activityPreactionId", getActivityPreactionId())
            .toString();
    }

}
