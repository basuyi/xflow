package org.basuyi.xflow.core.config.model;

import java.util.List;

/**
 * �������ӹ��������ڶ���
 * @author mashuai
 * @version 0.1
 * @date 2009-11-20
 */
public class WfConfigSubflowActivity extends WfConfigActivity {

	private static final long serialVersionUID = 1L;
	/*ִ��ǰʵ���б�*/
	private List<WfConfigAction> preActions = null;
	/*ִ�к�ʵ���б�*/
	private List<WfConfigAction> postActions = null;
	/*�ӹ�������ʶ*/
	private String subflowId = null;
	
	/**
	 * @return ִ��ǰʵ���б�
	 */
	public List<WfConfigAction> getPreActions() {
		return preActions;
	}
	
	/**
	 * @param preActions ִ��ǰʵ���б�
	 */
	public void setPreActions(List<WfConfigAction> preActions) {
		this.preActions = preActions;
	}
	
	/**
	 * @return ִ�к�ʵ���б�
	 */
	public List<WfConfigAction> getPostActions() {
		return postActions;
	}
	
	/**
	 * @param postActions ִ�к�ʵ���б�
	 */
	public void setPostActions(List<WfConfigAction> postActions) {
		this.postActions = postActions;
	}
	
	/**
	 * @return �ӹ�������ʶ
	 */
	public String getSubflowId() {
		return subflowId;
	}
	
	/**
	 * @param subflowId �ӹ�������ʶ
	 */
	public void setSubflowId(String subflowId) {
		this.subflowId = subflowId;
	}
}
