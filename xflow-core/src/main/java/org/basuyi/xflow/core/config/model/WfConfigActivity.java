package org.basuyi.xflow.core.config.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ���������ڶ���
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class WfConfigActivity implements Serializable {

	private static final long serialVersionUID = 1L;
	/*���ڱ�ʶ*/
	private String activityId = null;
	/*��������*/
	private String activityName = null;
	/*��������*/
	private String activityType = null;
	/*��������*/
	private String decription = null;
	/*����ִ��ʵ���б�*/
	private List<WfConfigAction> wfConfigActions = null;
	/*����ִ�н���б�*/
	private Map<String, WfConfigResult> wfConfigResults = null;
	/*���ڲ����б�*/
	private Map<String, WfConfigParameter> wfConfigParameters = null;
	
	/**
	 * @return ���ڱ�ʶ
	 */
	public String getActivityId() {
		return activityId;
	}
	
	/**
	 * @param activityId ���ڱ�ʶ
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	/**
	 * @return ��������
	 */
	public String getDecription() {
		return decription;
	}
	
	/**
	 * @param decription ��������
	 */
	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	/**
	 * @return ����ִ�н���б�
	 */
	public Map<String, WfConfigResult> getResults() {
		return wfConfigResults;
	}
	
	/**
	 * @param wfConfigResults ����ִ�н���б�
	 */
	public void setResults(Map<String, WfConfigResult> wfConfigResults) {
		this.wfConfigResults = wfConfigResults;
	}
	
	/**
	 * @return ����ִ��ʵ���б�
	 */
	public List<WfConfigAction> getActions() {
		return wfConfigActions;
	}
	
	/**
	 * @param wfConfigActions ����ִ��ʵ���б�
	 */
	public void setActions(List<WfConfigAction> wfConfigActions) {
		this.wfConfigActions = wfConfigActions;
	}
	
	/**
	 * @return ��������
	 */
	public String getActivityName() {
		return activityName;
	}
	
	/**
	 * @param activityName ��������
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	/**
	 * @return ��������
	 */
	public String getActivityType() {
		return activityType;
	}
	
	/**
	 * @param activityType ��������
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

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
	
}
