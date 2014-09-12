package org.basuyi.xflow.core.actions;

import org.basuyi.xflow.core.config.model.WorkflowContext;
import org.basuyi.xflow.core.config.model.WorkflowException;

/**
 * ����������ִ�нӿ�
 * @author mashuai
 * @version 0.1
 * @date 2009-10-19
 */
public interface WorkflowAction {
	public void execute(WorkflowContext context) throws WorkflowException;
}
