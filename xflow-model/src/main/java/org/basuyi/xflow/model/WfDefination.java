package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfDefination implements Serializable {

    /** identifier field */
    private String workflowId;

    /** nullable persistent field */
    private String workflowName;

    /** nullable persistent field */
    private String workflowDesc;

    /** nullable persistent field */
    private Long creator;

    /** nullable persistent field */
    private Date createDate;
    
    private Set wfActivities;
    
    private Set wfParameters;

    /**
	 * @return the wfParameters
	 */
	public Set getWfParameters() {
		return wfParameters;
	}

	/**
	 * @param wfParameters the wfParameters to set
	 */
	public void setWfParameters(Set wfParameters) {
		this.wfParameters = wfParameters;
	}

	/**
	 * @return the wfActivities
	 */
	public Set getWfActivities() {
		return wfActivities;
	}

	/**
	 * @param wfActivities the wfActivities to set
	 */
	public void setWfActivities(Set wfActivities) {
		this.wfActivities = wfActivities;
	}

	/** full constructor */
    public WfDefination(String workflowId, String workflowName, String workflowDesc, Long creator, Date createDate) {
        this.workflowId = workflowId;
        this.workflowName = workflowName;
        this.workflowDesc = workflowDesc;
        this.creator = creator;
        this.createDate = createDate;
    }

    /** default constructor */
    public WfDefination() {
    }

    /** minimal constructor */
    public WfDefination(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return this.workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getWorkflowDesc() {
        return this.workflowDesc;
    }

    public void setWorkflowDesc(String workflowDesc) {
        this.workflowDesc = workflowDesc;
    }

    public Long getCreator() {
        return this.creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("workflowId", getWorkflowId())
            .toString();
    }

}
