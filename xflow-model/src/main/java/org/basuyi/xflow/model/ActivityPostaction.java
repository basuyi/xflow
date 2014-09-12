package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ActivityPostaction implements Serializable {

    /** identifier field */
    private Long activityPostactionId;

    /** nullable persistent field */
    private String activityId;

    /** nullable persistent field */
    private String actionId;

    /** full constructor */
    public ActivityPostaction(Long activityPostactionId, String activityId, String actionId) {
        this.activityPostactionId = activityPostactionId;
        this.activityId = activityId;
        this.actionId = actionId;
    }

    /** default constructor */
    public ActivityPostaction() {
    }

    /** minimal constructor */
    public ActivityPostaction(Long activityPostactionId) {
        this.activityPostactionId = activityPostactionId;
    }

    public Long getActivityPostactionId() {
        return this.activityPostactionId;
    }

    public void setActivityPostactionId(Long activityPostactionId) {
        this.activityPostactionId = activityPostactionId;
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
            .append("activityPostactionId", getActivityPostactionId())
            .toString();
    }

}
