package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfAction implements Serializable {

    /** identifier field */
    private String actionId;

    /** nullable persistent field */
    private String actionName;

    /** nullable persistent field */
    private String actionDesc;

    /** nullable persistent field */
    private Long actionType;

    /** nullable persistent field */
    private String actionValue;
    
    private Set actionParams;

    /** full constructor */
    public WfAction(String actionId, String actionName, String actionDesc, Long actionType, String actionValue) {
        this.actionId = actionId;
        this.actionName = actionName;
        this.actionDesc = actionDesc;
        this.actionType = actionType;
        this.actionValue = actionValue;
    }

    /** default constructor */
    public WfAction() {
    }

    /** minimal constructor */
    public WfAction(String actionId) {
        this.actionId = actionId;
    }

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionDesc() {
        return this.actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public Long getActionType() {
        return this.actionType;
    }

    public void setActionType(Long actionType) {
        this.actionType = actionType;
    }

    public String getActionValue() {
        return this.actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    /**
	 * @return the actionParams
	 */
	public Set getActionParams() {
		return actionParams;
	}

	/**
	 * @param actionParams the actionParams to set
	 */
	public void setActionParams(Set actionParams) {
		this.actionParams = actionParams;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("actionId", getActionId())
            .toString();
    }

}
