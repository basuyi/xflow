package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WorkflowPosition extends Position implements Serializable {

	private Long comWorkflowId;

	public Long getComWorkflowId() {
		return comWorkflowId;
	}

	public void setComWorkflowId(Long comWorkflowId) {
		this.comWorkflowId = comWorkflowId;
	}
	
	

}
