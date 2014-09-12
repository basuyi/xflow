package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Activity implements Serializable {

    /** identifier field */
    private String activityId;

    /** nullable persistent field */
    private String activityName;

    /** nullable persistent field */
    private String activityDesc;

    /** nullable persistent field */
    private Long activityType;
    
    private Set wfActivityParams;
    
    private Set activityActions;
    
    private Set activityPreActions;
    
    private Set activityPostActions;

    /**
	 * @return the wfActivityParams
	 */
	public Set getWfActivityParams() {
		return wfActivityParams;
	}

	/**
	 * @param wfActivityParams the wfActivityParams to set
	 */
	public void setWfActivityParams(Set wfActivityParams) {
		this.wfActivityParams = wfActivityParams;
	}

	/** full constructor */
    public Activity(String activityId, String activityName, String activityDesc, Long activityType) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityDesc = activityDesc;
        this.activityType = activityType;
    }

    /** default constructor */
    public Activity() {
    }

    /** minimal constructor */
    public Activity(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return this.activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public Long getActivityType() {
        return this.activityType;
    }

    public void setActivityType(Long activityType) {
        this.activityType = activityType;
    }

    /**
	 * @return the activityActions
	 */
	public Set getActivityActions() {
		return activityActions;
	}

	/**
	 * @param activityActions the activityActions to set
	 */
	public void setActivityActions(Set activityActions) {
		this.activityActions = activityActions;
	}

	/**
	 * @return the activityPreActions
	 */
	public Set getActivityPreActions() {
		return activityPreActions;
	}

	/**
	 * @param activityPreActions the activityPreActions to set
	 */
	public void setActivityPreActions(Set activityPreActions) {
		this.activityPreActions = activityPreActions;
	}

	/**
	 * @return the activityPostActions
	 */
	public Set getActivityPostActions() {
		return activityPostActions;
	}

	/**
	 * @param activityPostActions the activityPostActions to set
	 */
	public void setActivityPostActions(Set activityPostActions) {
		this.activityPostActions = activityPostActions;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("activityId", getActivityId())
            .toString();
    }

}
