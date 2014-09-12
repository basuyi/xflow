package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfCondition implements Serializable {

    /** identifier field */
    private String conditionId;

    /** nullable persistent field */
    private String conditionName;

    /** nullable persistent field */
    private String conditionDesc;

    /** nullable persistent field */
    private Long conditionType;

    /** nullable persistent field */
    private String conditionValue;
    
    private Set conditionParams;

    /** full constructor */
    public WfCondition(String conditionId, String conditionName, String conditionDesc, Long conditionType, String conditionValue) {
        this.conditionId = conditionId;
        this.conditionName = conditionName;
        this.conditionDesc = conditionDesc;
        this.conditionType = conditionType;
        this.conditionValue = conditionValue;
    }

    /** default constructor */
    public WfCondition() {
    }

    /** minimal constructor */
    public WfCondition(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionId() {
        return this.conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionName() {
        return this.conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionDesc() {
        return this.conditionDesc;
    }

    public void setConditionDesc(String conditionDesc) {
        this.conditionDesc = conditionDesc;
    }

    public Long getConditionType() {
        return this.conditionType;
    }

    public void setConditionType(Long conditionType) {
        this.conditionType = conditionType;
    }

    public String getConditionValue() {
        return this.conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    /**
	 * @return the conditionParams
	 */
	public Set getConditionParams() {
		return conditionParams;
	}

	/**
	 * @param conditionParams the conditionParams to set
	 */
	public void setConditionParams(Set conditionParams) {
		this.conditionParams = conditionParams;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("conditionId", getConditionId())
            .toString();
    }

}
