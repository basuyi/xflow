package org.basuyi.xflow.core.config;

import org.basuyi.xflow.core.config.model.WorkflowDefination;

public class WorkflowConfigFactory {
	public WorkflowConfig getWorkflowConfig(String workflow_config_policy) {
		WorkflowConfig configure = null;
		if (WorkflowDefination.WF_CONFIG_XML.equals(workflow_config_policy)) {
			configure = new WorkflowXMLConfig();
		} else if (WorkflowDefination.WF_CONFIG_DB.equals(workflow_config_policy)) {
			configure = new WorkflowDBConfig();
		}
		return configure;
	}
}
