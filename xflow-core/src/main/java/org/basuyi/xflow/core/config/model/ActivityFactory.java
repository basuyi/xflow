package org.basuyi.xflow.core.config.model;

/**
 * ���ڶ��󹤳���
 * @author mashuai
 * @version 0.1
 * @date 2009-11-20
 */
public class ActivityFactory {

	/**
	 * ���ݻ������ͷ��ػ���ʵ��
	 * @param activityType ��������
	 * @return
	 */
	public static WfConfigActivity getActivity(String activityType) {
		if ("subflow".equals(activityType)) {//������
			return new WfConfigSubflowActivity();
		} else if ("user".equals(activityType)) {//�û�
			return new WfConfigUserActivity();
		} else {//����
			return new WfConfigActivity();
		}
	}
}
