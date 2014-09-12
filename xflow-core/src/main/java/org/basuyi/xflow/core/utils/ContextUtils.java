package org.basuyi.xflow.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * �����������Ĺ�����
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class ContextUtils {
	
	/**
	 * �������ָ���ļ�
	 * @param obj
	 * @param file
	 */
	public static void outputs(Object obj,String file) {
		FileOutputStream ostream = null;
		ObjectOutputStream outputs = null;
		try {
			ostream = new FileOutputStream(file);// �����ļ������
			outputs = new ObjectOutputStream(ostream);// ��
			outputs.writeObject(obj); // ����̳������л��ӿڵ���
			outputs.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*�رն���������*/
			if (outputs != null) {
				try {
					outputs.close();
				} catch (IOException e) {
					outputs = null;
				}
			}
			/*�ر��ļ�������*/
			if (ostream != null) {
				try {
					ostream.close();
				} catch (IOException e) {
					ostream = null;
				}
			}
		}
	}
	
	/**
	 * ��ָ���ļ���ȡ����
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Object inputs(String file) {
		FileInputStream istream = null;
		ObjectInputStream inputs = null;
		Object obj=new Object();
		try {
			istream = new FileInputStream(file); // �����ļ�������
			inputs = new ObjectInputStream(istream); // ��
	        obj = (Object)inputs.readObject(); // �������л�����
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			/*�رն���������*/
			if (inputs != null) {
				try {
					inputs.close();
				} catch (IOException e) {
					inputs = null;
				}
			}
			/*�ر��ļ�������*/
			if (istream != null) {
				try {
					istream.close();
				} catch (IOException e) {
					istream = null;
				}
			}
			/*ɾ���ļ�*/
			if (file != null) {
				File fileObject = new File(file);
				if (fileObject.isFile()) {
					fileObject.deleteOnExit();
				}
			}
		}
		return obj;
	}
}
