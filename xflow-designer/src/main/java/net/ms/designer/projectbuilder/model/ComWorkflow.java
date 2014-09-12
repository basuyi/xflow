package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ComWorkflow implements Serializable {

    /** identifier field */
    private Long comWorkflowId;

    /** nullable persistent field */
    private String workflowId;

    /** nullable persistent field */
    private net.ms.designer.projectbuilder.model.Component component;

    /** persistent field */
    private WorkflowPosition workflowPosition;

    /** full constructor */
    public ComWorkflow(String workflowId, net.ms.designer.projectbuilder.model.Component component, WorkflowPosition workflowPosition) {
        this.workflowId = workflowId;
        this.component = component;
        this.workflowPosition = workflowPosition;
    }

    /** default constructor */
    public ComWorkflow() {
    }

    /** minimal constructor */
    public ComWorkflow(WorkflowPosition workflowPosition) {
        this.workflowPosition = workflowPosition;
    }

    public Long getComWorkflowId() {
        return this.comWorkflowId;
    }

    public void setComWorkflowId(Long comWorkflowId) {
        this.comWorkflowId = comWorkflowId;
    }

    public String getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public net.ms.designer.projectbuilder.model.Component getComponent() {
        return this.component;
    }

    public void setComponent(net.ms.designer.projectbuilder.model.Component component) {
        this.component = component;
    }

    public WorkflowPosition getWorkflowPosition() {
        return this.workflowPosition;
    }

    public void setWorkflowPosition(WorkflowPosition workflowPosition) {
        this.workflowPosition = workflowPosition;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comWorkflowId", getComWorkflowId())
            .toString();
    }

}
