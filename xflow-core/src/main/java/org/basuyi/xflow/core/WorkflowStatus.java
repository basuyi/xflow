package org.basuyi.xflow.core;

/**
 * ����������״̬����
 * @author mashuai
 * @version 0.1
 * @date 2009-10-19
 */
public interface WorkflowStatus {
	public static final int NORMAL = 0;		//����ִ����
	public static final int TERMINAL = 1;	//������ֹ
	public static final int SUSPEND = 2;		//����
	public static final int ABEND = 3;		//�쳣��ֹ
}
