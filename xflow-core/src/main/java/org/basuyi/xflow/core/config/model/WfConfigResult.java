package org.basuyi.xflow.core.config.model;

import java.io.Serializable;
import java.util.List;

/**
 * ����������������
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class WfConfigResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/*��������ʶ*/
	private String resultId = null;
	
	/*�����������*/
	private String resultName = null;
	
	/*�����������*/
	private String resultType = null;
	
	/*ת���󻷽ڱ�ʶ*/
	private String activityId = null;
	
	/*��ǰ״̬*/
	private String status = null;
	
	/*ת����״̬*/
	private String nextStatus = null;
	
	/*��������*/
	private List<WfConfigCondition> wfConfigConditions = null;
	
	/*��������*/
	private List<WfConfigAction> wfConfigActions = null;
	
	/*��������*/
	private String policy = null;
	
	/**
	 * @return ��������ʶ
	 */
	public String getResultId() {
		return resultId;
	}
	
	/**
	 * @param resultId ��������ʶ
	 */
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	
	/**
	 * @return �����������
	 */
	public String getResultName() {
		return resultName;
	}
	
	/**
	 * @param resultName �����������
	 */
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	
	/**
	 * @return �����������
	 */
	public String getResultType() {
		return resultType;
	}
	
	/**
	 * @param resultType �����������
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
	/**
	 * @return ת���󻷽ڱ�ʶ
	 */
	public String getActivityId() {
		return activityId;
	}
	
	/**
	 * @param activity ת���󻷽ڱ�ʶ
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	/**
	 * @return ��ǰ״̬
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status ��ǰ״̬
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return ת����״̬
	 */
	public String getNextStatus() {
		return nextStatus;
	}
	
	/**
	 * @param nextStatus ת����״̬
	 */
	public void setNextStatus(String nextStatus) {
		this.nextStatus = nextStatus;
	}

	/**
	 * @return ��������
	 */
	public List<WfConfigCondition> getConditions() {
		return wfConfigConditions;
	}

	/**
	 * @param wfConfigCondition ��������
	 */
	public void setConditions(List<WfConfigCondition> wfConfigConditions) {
		this.wfConfigConditions = wfConfigConditions;
	}

	/**
	 * @return the policy
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * @param policy the policy to set
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}

	/**
	 * @return the wfConfigActions
	 */
	public List<WfConfigAction> getActions() {
		return wfConfigActions;
	}

	/**
	 * @param wfConfigActions the wfConfigActions to set
	 */
	public void setActions(List<WfConfigAction> wfConfigActions) {
		this.wfConfigActions = wfConfigActions;
	}
	
}
