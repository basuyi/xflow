package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ActionParameter implements Serializable {

    /** identifier field */
    private String actionId;

    /** identifier field */
    private Long paramId;

    /** identifier field */
    private Long actionParamId;
    
    private Set parameters;

    /** full constructor */
    public ActionParameter(String actionId, Long paramId, Long actionParamId) {
        this.actionId = actionId;
        this.paramId = paramId;
        this.actionParamId = actionParamId;
    }

    /** default constructor */
    public ActionParameter() {
    }

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public Long getParamId() {
        return this.paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public Long getActionParamId() {
        return this.actionParamId;
    }

    public void setActionParamId(Long actionParamId) {
        this.actionParamId = actionParamId;
    }

    /**
	 * @return the parameters
	 */
	public Set getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Set parameters) {
		this.parameters = parameters;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("actionId", getActionId())
            .append("paramId", getParamId())
            .append("actionParamId", getActionParamId())
            .toString();
    }

}
