package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfParameter implements Serializable {

    /** identifier field */
    private Long wfParamId;

    /** nullable persistent field */
    private String workflowId;

    /** nullable persistent field */
    private Long paramId;

    /** full constructor */
    public WfParameter(Long wfParamId, String workflowId, Long paramId) {
        this.wfParamId = wfParamId;
        this.workflowId = workflowId;
        this.paramId = paramId;
    }

    /** default constructor */
    public WfParameter() {
    }

    /** minimal constructor */
    public WfParameter(Long wfParamId) {
        this.wfParamId = wfParamId;
    }

    public Long getWfParamId() {
        return this.wfParamId;
    }

    public void setWfParamId(Long wfParamId) {
        this.wfParamId = wfParamId;
    }

    public String getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public Long getParamId() {
        return this.paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("wfParamId", getWfParamId())
            .toString();
    }

}
