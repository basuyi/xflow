package org.basuyi.xflow.core.actions;

import org.basuyi.xflow.core.config.model.WorkflowContext;
import org.basuyi.xflow.core.config.model.WorkflowException;

/**
 * ��������������
 * @author mashuai
 * @version 0.1
 * @date 2009-10-19
 */
public interface WorkflowCondition {
	
	/**
	 * ��������
	 * @param context
	 * @return
	 */
	public boolean processCondition(WorkflowContext context) throws WorkflowException;
}
