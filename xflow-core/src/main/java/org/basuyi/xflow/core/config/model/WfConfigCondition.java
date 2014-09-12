package org.basuyi.xflow.core.config.model;

import java.io.Serializable;
import java.util.Map;

/**
 * ��������������
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class WfConfigCondition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*������ʶ*/
	private String conditionId = null;
	
	/*��������*/
	private String conditionName = null;
	
	/*��������*/
	private String conditionType = null;
	
	/*��������*/
	private WfConfigArgument arg= null;
	
	/*���������б�*/
	private Map<String, WfConfigParameter> wfConfigParameters = null;
	
	/**
	 * @return ������ʶ
	 */
	public String getConditionId() {
		return conditionId;
	}
	
	/**
	 * @param conditionId ������ʶ
	 */
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	
	/**
	 * @return ��������
	 */
	public String getConditionName() {
		return conditionName;
	}
	
	/**
	 * @param conditionName ��������
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	
	/**
	 * @return ��������
	 */
	public String getConditionType() {
		return conditionType;
	}
	
	/**
	 * @param conditionType ��������
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	
	/**
	 * @return ��������
	 */
	public WfConfigArgument getArgument() {
		return arg;
	}
	
	/**
	 * @param arg ��������
	 */
	public void setArgument(WfConfigArgument arg) {
		this.arg = arg;
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
