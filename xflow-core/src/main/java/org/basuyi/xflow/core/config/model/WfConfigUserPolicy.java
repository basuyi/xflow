package org.basuyi.xflow.core.config.model;

import java.io.Serializable;

/**
 * �û����Զ���
 * @author mashuai
 * @version 0.1
 * @date 2009-12-07
 */
public class WfConfigUserPolicy implements Serializable {
	private static final long serialVersionUID = 1L;
	/*�û���������*/
	private String type;
	/*��ɫ��ʶ*/
	private String roleId;
	/*�û���ڱ�ʶ*/
	private String entry;
	/*�û��������*/
	private String entryType;
	/*�û���ʶ*/
	private String userId;
	
	/**
	 * @return �û���������
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type �û���������
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return ��ɫ��ʶ
	 */
	public String getRoleId() {
		return roleId;
	}
	
	/**
	 * @param roleId ��ɫ��ʶ
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * @return �û���ڱ�ʶ
	 */
	public String getEntry() {
		return entry;
	}
	
	/**
	 * @param entry �û���ڱ�ʶ
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	/**
	 * @return �û��������
	 */
	public String getEntryType() {
		return entryType;
	}
	
	/**
	 * @param entryType �û��������
	 */
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	
	/**
	 * @return �û���ʶ
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * @param userId �û���ʶ
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
