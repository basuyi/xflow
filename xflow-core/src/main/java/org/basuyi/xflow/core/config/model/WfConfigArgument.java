package org.basuyi.xflow.core.config.model;

import java.io.Serializable;

/**
 * ��������
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class WfConfigArgument implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*��������*/
	private String argName = null;
	
	/*����ֵ*/
	private String argValue = null;
	
	/**
	 * @return ��������
	 */
	public String getArgName() {
		return argName;
	}
	
	/**
	 * @param argName ��������
	 */
	public void setArgName(String argName) {
		this.argName = argName;
	}
	
	/**
	 * @return ����ֵ
	 */
	public String getArgValue() {
		return argValue;
	}
	
	/**
	 * @param argValue ����ֵ
	 */
	public void setArgValue(String argValue) {
		this.argValue = argValue;
	}
}
