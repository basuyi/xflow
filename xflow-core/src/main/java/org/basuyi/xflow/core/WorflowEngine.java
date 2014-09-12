package org.basuyi.xflow.core;

import java.util.Map;

import org.basuyi.xflow.core.config.model.WorkflowException;

/**
 * ����������ͳһ�ӿ�
 * @author mashuai
 * @version 0.1
 * @date 2009-11-20
 */
public interface WorflowEngine {
	/**
	 * ����������
	 * @throws WorkflowException 
	 */
	public Map<String, Object> startWorkflow(Map<String, Object> params) throws WorkflowException;
//	/**
//	 * ��ͣ����������
//	 */
//	public void suspend();
//	/**
//	 * �ָ�����������
//	 */
//	public void resume();
//	/**
//	 * ֹͣ����������
//	 */
//	public void terminate();
}
