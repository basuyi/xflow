package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfActivity implements Serializable {

    /** identifier field */
    private Long wfActivityId;

    /** nullable persistent field */
    private String workflowId;

    /** nullable persistent field */
    private String activityId;

    /** full constructor */
    public WfActivity(Long wfActivityId, String workflowId, String activityId) {
        this.wfActivityId = wfActivityId;
        this.workflowId = workflowId;
        this.activityId = activityId;
    }

    /** default constructor */
    public WfActivity() {
    }

    /** minimal constructor */
    public WfActivity(Long wfActivityId) {
        this.wfActivityId = wfActivityId;
    }

    public Long getWfActivityId() {
        return this.wfActivityId;
    }

    public void setWfActivityId(Long wfActivityId) {
        this.wfActivityId = wfActivityId;
    }

    public String getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("wfActivityId", getWfActivityId())
            .toString();
    }

}
