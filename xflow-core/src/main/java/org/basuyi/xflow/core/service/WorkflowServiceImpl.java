package org.basuyi.xflow.core.service;

import java.io.Serializable;
import java.util.List;

import org.basuyi.xflow.model.WfControl;
import org.basuyi.xflow.model.WfProcess;
import org.basuyi.xflow.model.WfProcessDispatch;

import org.basuyi.xflow.common.dao.EntityManager;

/**
 * �������������
 * @author mashuai
 * @version 0.1
 * @date 2009-12-05
 */
public class WorkflowServiceImpl implements WorkflowService {
	public EntityManager entityManager;
	
	/**
	 * ���湤�������Ʊ�
	 * @param wf_control
	 */
	public void saveWfControl(WfControl wf_control) {
		entityManager.saveOrUpdate(wf_control);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void saveWfProcess(WfProcess wf_process) {
		entityManager.saveOrUpdate(wf_process);
	}

	public WfControl getWfControl(WfControl wf_control) {
		WfControl wfControl = (WfControl)entityManager.get(wf_control.getClass(), wf_control.getWfInstId());
		return wfControl;
	}

	public void saveWfProcessDispatch(WfProcessDispatch wf_dispatch) {
		entityManager.saveOrUpdate(wf_dispatch);
	}

	public WfProcessDispatch getWfProcessDispatch(WfProcessDispatch wf_dispatch) {
		WfProcessDispatch wfTaskDispatch = (WfProcessDispatch)entityManager.get(wf_dispatch.getClass(), wf_dispatch.getWfProcessSeq());
		return wfTaskDispatch;
	}

	public List<WfProcessDispatch> getWfProcessDispatchByModel(WfProcessDispatch wf_dispatch) {
		List<WfProcessDispatch> wfTaskDispatch_list = (List<WfProcessDispatch>)entityManager.find(wf_dispatch);
		return wfTaskDispatch_list;
	}

	public WfProcess getWfProcess(WfProcess wf_process) {
		WfProcess wfProcess = (WfProcess)entityManager.get(wf_process.getClass(), wf_process.getWfProcessSeq());
		return wfProcess;
	}

	public void saveEntity(Object entity) {
		entityManager.saveOrUpdate(entity);
	}

	public Object getEntity(Class cls, Serializable id) {
		return entityManager.get(cls, id);
	}

	public List getEntityByModel(Object entity) {
		return entityManager.find(entity);
	}

	public List getAllEntities(Class cls) {
		return entityManager.load(cls);
	}
}
