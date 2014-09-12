package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfActivityTran implements Serializable {

    /** identifier field */
    private Long activityTransId;

    /** nullable persistent field */
    private Long fromWfActivityId;

    /** nullable persistent field */
    private Long toWfActivityId;

    /** nullable persistent field */
    private Long conditionPolicy;
    
    private Set wfTranActions;
    
    private Set wfTranConditions;

    /** full constructor */
    public WfActivityTran(Long activityTransId, Long fromWfActivityId, Long toWfActivityId, Long conditionPolicy) {
        this.activityTransId = activityTransId;
        this.fromWfActivityId = fromWfActivityId;
        this.toWfActivityId = toWfActivityId;
        this.conditionPolicy = conditionPolicy;
    }

    /** default constructor */
    public WfActivityTran() {
    }

    /** minimal constructor */
    public WfActivityTran(Long activityTransId) {
        this.activityTransId = activityTransId;
    }

    public Long getActivityTransId() {
        return this.activityTransId;
    }

    public void setActivityTransId(Long activityTransId) {
        this.activityTransId = activityTransId;
    }

    public Long getFromWfActivityId() {
        return this.fromWfActivityId;
    }

    public void setFromWfActivityId(Long fromWfActivityId) {
        this.fromWfActivityId = fromWfActivityId;
    }

    public Long getToWfActivityId() {
        return this.toWfActivityId;
    }

    public void setToWfActivityId(Long toWfActivityId) {
        this.toWfActivityId = toWfActivityId;
    }

    public Long getConditionPolicy() {
        return this.conditionPolicy;
    }

    public void setConditionPolicy(Long conditionPolicy) {
        this.conditionPolicy = conditionPolicy;
    }

    /**
	 * @return the wfTranActions
	 */
	public Set getWfTranActions() {
		return wfTranActions;
	}

	/**
	 * @param wfTranActions the wfTranActions to set
	 */
	public void setWfTranActions(Set wfTranActions) {
		this.wfTranActions = wfTranActions;
	}

	/**
	 * @return the wfTranConditions
	 */
	public Set getWfTranConditions() {
		return wfTranConditions;
	}

	/**
	 * @param wfTranConditions the wfTranConditions to set
	 */
	public void setWfTranConditions(Set wfTranConditions) {
		this.wfTranConditions = wfTranConditions;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("activityTransId", getActivityTransId())
            .toString();
    }

}
