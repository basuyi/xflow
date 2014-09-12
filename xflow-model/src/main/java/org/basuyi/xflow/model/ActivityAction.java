package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ActivityAction implements Serializable {

    /** identifier field */
    private Long activityActionId;

    /** nullable persistent field */
    private String activityId;

    /** nullable persistent field */
    private String actionId;

    /** full constructor */
    public ActivityAction(Long activityActionId, String activityId, String actionId) {
        this.activityActionId = activityActionId;
        this.activityId = activityId;
        this.actionId = actionId;
    }

    /** default constructor */
    public ActivityAction() {
    }

    /** minimal constructor */
    public ActivityAction(Long activityActionId) {
        this.activityActionId = activityActionId;
    }

    public Long getActivityActionId() {
        return this.activityActionId;
    }

    public void setActivityActionId(Long activityActionId) {
        this.activityActionId = activityActionId;
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
            .append("activityActionId", getActivityActionId())
            .toString();
    }

}
