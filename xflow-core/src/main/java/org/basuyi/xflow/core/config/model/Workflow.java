package org.basuyi.xflow.core.config.model;

import java.io.Serializable;
import java.util.Map;

/**
 * ����������
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class Workflow implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*��������ʶ*/
	private String workflowId = null;
	
	/*����������*/
	private String workflowName = null;
	
	/*����������*/
	private String description = null;
	
	/*�����������б�*/
	private Map<String, WfConfigActivity> wfConfigActivities = null;
	
	/*�����������б�*/
	private Map<String, WfConfigParameter> wfConfigParameters = null;
	
	/**
	 * @return the wfConfigParameters
	 */
	public Map<String, WfConfigParameter> getWfConfigParameters() {
		return wfConfigParameters;
	}

	/**
	 * @param wfConfigParameters the wfConfigParameters to set
	 */
	public void setWfConfigParameters(
			Map<String, WfConfigParameter> wfConfigParameters) {
		this.wfConfigParameters = wfConfigParameters;
	}

	/*��ʼ����*/
	private WfConfigActivity startActivity = null;
	
	/**
	 * @return ����������
	 */
	public String getWorkflowName() {
		return workflowName;
	}
	
	/**
	 * @param workflowName ����������
	 */
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	
	/**
	 * @return ��������ʶ
	 */
	public String getWorkflowId() {
		return workflowId;
	}
	
	/**
	 * @param workflowId ��������ʶ
	 */
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	
	/**
	 * @return ����������
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description ����������
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return �����������б�
	 */
	public Map<String, WfConfigActivity> getActivities() {
		return wfConfigActivities;
	}
	
	/**
	 * @param wfConfigActivities �����������б�
	 */
	public void setActivities(Map<String, WfConfigActivity> wfConfigActivities) {
		this.wfConfigActivities = wfConfigActivities;
	}
	
	/**
	 * @return ��ʼ����
	 */
	public WfConfigActivity getStartActivity() {
		return startActivity;
	}
	
	/**
	 * @param startActivity ��ʼ����
	 */
	public void setStartActivity(WfConfigActivity startActivity) {
		this.startActivity = startActivity;
	}
	
	public WfConfigActivity getActivityById(String activityId) {
		return this.wfConfigActivities.get(activityId);
	}
}
