package org.basuyi.xflow.core.service;

import java.io.Serializable;
import java.util.List;

import org.basuyi.xflow.model.WfControl;
import org.basuyi.xflow.model.WfProcess;
import org.basuyi.xflow.model.WfProcessDispatch;

/**
 * ����������ӿ�
 * @author mashuai
 * @version 0.1
 * @date 2009-12-05
 */
public interface WorkflowService {
	
	/**
	 * ���湤�������Ʊ�
	 * @param wf_control
	 */
	public void saveWfControl(WfControl wf_control);
	
	/**
	 * ���湤����ִ�б��
	 * @param wf_control
	 */
	public void saveWfProcess(WfProcess wf_process);
	
	/**
	 * ��ѯ������ʵ����
	 * @param wf_control
	 */
	public WfControl getWfControl(WfControl wf_control);
	
	/**
	 * ��ѯ������ִ�б�
	 * @param wf_process
	 */
	public WfProcess getWfProcess(WfProcess wf_process);
	
	/**
	 * ��ѯ���������ɱ�
	 * @param wf_dispatch
	 */
	public WfProcessDispatch getWfProcessDispatch(WfProcessDispatch wf_dispatch);

	/**
	 * ��ѯ���������ɱ�
	 * @param wf_dispatch
	 */
	public List<WfProcessDispatch> getWfProcessDispatchByModel(
			WfProcessDispatch i_wf_dispatch);

	/**
	 * ���湤����������ɱ�
	 * @param wf_control
	 */
	public void saveWfProcessDispatch(WfProcessDispatch wf_dispatch);
	
	public void saveEntity(Object entity);
	
	public Object getEntity(Class cls, Serializable id);

	public List getEntityByModel(Object entity);
	
	public List getAllEntities(Class cls);
}
