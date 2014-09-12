package org.basuyi.xflow.core.config.model;

import java.io.Serializable;
import java.util.Map;

/**
 * ������״ִ̬�ж���
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class WfConfigAction implements Serializable {

	private static final long serialVersionUID = 1L;

	/*������ʶ*/
	private String actionId = null;

	/*��������*/
	private String actionName = null;
	
	/*��������*/
	private String description = null;
	
	/*��������*/
	private String type = null;
	
	/*��������*/
	private WfConfigArgument arg= null;

	/*���������б�*/
	private Map<String, WfConfigParameter> wfConfigParameters = null;
	/**
	 * @return ������ʶ
	 */
	public String getActionId() {
		return actionId;
	}
	
	/**
	 * @param actionId ������ʶ
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	/**
	 * @return ��������
	 */
	public String getActionName() {
		return actionName;
	}
	
	/**
	 * @param actionId ��������
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	/**
	 * @return ��������
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description ��������
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return ��������
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type ��������
	 */
	public void setType(String type) {
		this.type = type;
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
