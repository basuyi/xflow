package org.basuyi.xflow.core.config.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * �����������Ķ���
 * @author mashuai
 * @version 0.1
 * @date 2009-10-19
 */
public class WorkflowContext implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*����������*/
	private Workflow workflow = null;
	
	/*��ǰ����*/
	private WfConfigActivity currentActivity = null;
	
	/*��ǰ״̬*/
	private String currentStatus = null;
	
	/*�����������ɴ��ݸ�������*/
	private Map<String, Object> public_params = new HashMap<String, Object>();
	
	/*�������ڲ����������ᴫ�ݸ�������*/
	private Map<String, Object> private_params = new HashMap<String, Object>();
	
	/*��ǰ������ִ��״̬*/
	private int engineStatus = 0; 
	
	/*�Ƿ��ӹ�����*/
	private boolean isSubflow = false;
	
	/*������������*/
	private Workflow upperWorkflow = null;
	
	/*������ˮ��*/
	private Workflow work_seq = null;
	
	/**
	 * ������ʱ����
	 * @param key
	 * @param obj
	 */
	public void put(String key, Object obj) {
		public_params.put(key, obj);
	}
	
	/**
	 * ͨ��keyȡ����ʱ����
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return public_params.get(key);
	}
	
	/**
	 * ɾ��key��Ŀ
	 * @param key
	 * @return
	 */
	public Object remove(String key) {
		return public_params.remove(key);
	}
	
	/**
	 * ����˽�в���
	 * @param key
	 * @param obj
	 */
	public void putPrivate(String key, Object obj) {
		private_params.put(key, obj);
	}
	
	/**
	 * ͨ��keyȡ��˽�ж���
	 * @param key
	 * @return
	 */
	public Object getPrivate(String key) {
		return private_params.get(key);
	}
	
	/**
	 * ɾ��˽��key��Ŀ
	 * @param key
	 * @return
	 */
	public Object removePrivate(String key) {
		return private_params.remove(key);
	}
	
	/**
	 * @return ��ǰ������ִ��״̬
	 */
	public int getEngineStatus() {
		return engineStatus;
	}

	/**
	 * @param engineStatus ��ǰ������ִ��״̬
	 */
	public void setEngineStatus(int engineStatus) {
		this.engineStatus = engineStatus;
	}

	/**
	 * @return ���ݲ���
	 */
	public Map<String, Object> getParams() {
		return public_params;
	}

	/**
	 * @param params ���ݲ���
	 */
	public void setParams(Map<String, Object> params) {
		this.public_params = params;
	}

	/**
	 * @return ����������
	 */
	public Workflow getWorkflow() {
		return workflow;
	}
	
	/**
	 * @param workflowName ����������
	 */
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	
	/**
	 * @return ��ǰ����
	 */
	public WfConfigActivity getCurrentActivity() {
		return currentActivity;
	}
	
	/**
	 * @param currentActivity ��ǰ����
	 */
	public void setCurrentActivity(WfConfigActivity currentActivity) {
		this.currentActivity = currentActivity;
	}
	
	/**
	 * ȡ�õ�ǰ���ڱ�ʶ
	 * @return
	 */
	public String getCurrentActivityId() {
		return currentActivity.getActivityId();
	}

	/**
	 * ȡ�õ�ǰ����״̬
	 * @return
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * ���õ�ǰ����״̬
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * @return �Ƿ��ӹ�����
	 */
	public boolean isSubflow() {
		return isSubflow;
	}

	/**
	 * @param isSubflow �Ƿ��ӹ�����
	 */
	public void setSubflow(boolean isSubflow) {
		this.isSubflow = isSubflow;
	}

	/**
	 * @return ������������
	 */
	public Workflow getUpperWorkflow() {
		return upperWorkflow;
	}

	/**
	 * @param upperWorkflow ������������
	 */
	public void setUpperWorkflow(Workflow upperWorkflow) {
		this.upperWorkflow = upperWorkflow;
	}

	/**
	 * @return ������ˮ��
	 */
	public Workflow getWork_seq() {
		return work_seq;
	}

	/**
	 * @param work_seq ������ˮ��
	 */
	public void setWork_seq(Workflow work_seq) {
		this.work_seq = work_seq;
	}
}
