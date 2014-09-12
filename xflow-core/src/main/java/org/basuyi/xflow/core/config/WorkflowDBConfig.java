package org.basuyi.xflow.core.config;

import org.basuyi.xflow.core.WorkflowStatus;
import org.basuyi.xflow.core.config.model.Workflow;
import org.basuyi.xflow.core.config.model.WorkflowContext;

public class WorkflowDBConfig extends AbstractWorkflowConfig {

	@Override
	public WorkflowContext initContext(String workflowId) {
		Workflow workflow = DBWFConfig.getWorkflowByID(workflowId);
		context = new WorkflowContext();
		context.setWorkflow(workflow);
		context.setEngineStatus(WorkflowStatus.NORMAL);
		return context;
	}

}
