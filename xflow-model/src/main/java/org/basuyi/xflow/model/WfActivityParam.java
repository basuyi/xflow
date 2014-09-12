package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfActivityParam implements Serializable {

    /** identifier field */
    private Long activityParamId;

    /** nullable persistent field */
    private String activityId;

    /** nullable persistent field */
    private Long paramId;

    /** full constructor */
    public WfActivityParam(Long activityParamId, String activityId, Long paramId) {
        this.activityParamId = activityParamId;
        this.activityId = activityId;
        this.paramId = paramId;
    }

    /** default constructor */
    public WfActivityParam() {
    }

    /** minimal constructor */
    public WfActivityParam(Long activityParamId) {
        this.activityParamId = activityParamId;
    }

    public Long getActivityParamId() {
        return this.activityParamId;
    }

    public void setActivityParamId(Long activityParamId) {
        this.activityParamId = activityParamId;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Long getParamId() {
        return this.paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("activityParamId", getActivityParamId())
            .toString();
    }

}
