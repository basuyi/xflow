package org.basuyi.xflow.core.config;

import org.basuyi.xflow.core.config.model.WorkflowContext;

public abstract class AbstractWorkflowConfig implements WorkflowConfig {
	
	/*�����������Ķ���*/
	protected WorkflowContext context = null;

	/**
	 * ��ʼ�������������Ķ���
	 * @param workflowId
	 * @return
	 */
	public abstract WorkflowContext initContext(String workflowId);
}
