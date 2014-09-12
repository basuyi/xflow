package org.basuyi.xflow.core.config;

import org.basuyi.xflow.core.config.model.WorkflowContext;

public interface WorkflowConfig {
	/**
	 * ��ʼ�������������Ķ���
	 * @param workflowId
	 * @return
	 */
	public WorkflowContext initContext(String workflowId);
}
