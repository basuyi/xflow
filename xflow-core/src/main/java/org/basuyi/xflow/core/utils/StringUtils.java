package org.basuyi.xflow.core.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.dom4j.Element;

/**
 * �ַ������ò���������
 * @author mashuai
 * @version 0.1
 * @date 2009-10-20
 */
public class StringUtils {
	
	/**
	 * String��������null���򷵻ذ��space�������޲���
	 * @param text
	 * @return
	 */
	public static String nullToSpace(String text) {
		if (text == null) {
			return " "; 
		}
		return text;
	}
	
	/**
	 * String���������null�򷵻ؿ��ַ��������򷵻�trim����
	 * @param text
	 * @return
	 */
	public static String trimToString(String text) {
		if (text == null) {
			return ""; 
		}
		return text.trim();
	}
	
	/**
	 * ���������null�򷵻ؿ��ַ���������ת��ΪStringȻ��trim
	 * @param obj
	 * @return
	 */
	public static String trimToString(Object obj) {
		if (obj == null) {
			return ""; 
		}
		return obj.toString().trim();
	}
	
	/**
	 * ���������null�򷵻ذ��space������ת��ΪString����
	 * @param obj
	 * @return
	 */
	public static String nullToSpace(Object obj) {
		if (obj == null) {
			return " "; 
		}
		return obj.toString();
	}
	
	/**
	 * ȡ�ڵ㣨nodeName���ڵ�����
	 * @param upperNode nodeName���ϲ�ڵ����
	 * @param nodeName �ڵ���
	 * @return
	 */
	public static String getElementValue(Element upperNode, String nodeName) {
		if (upperNode == null || upperNode.element(nodeName) == null) {
			return ""; 
		}
		return trimToString(upperNode.element(nodeName).getStringValue());
	}
	
	/**
	 * ȡ�ڵ㣨nodeName���ڵ�����
	 * @param node nodeName�ڵ����
	 * @return
	 */
	public static String getElementValue(Element node) {
		if (node == null) {
			return ""; 
		}
		return trimToString(node.getStringValue());
	}
	
	/**
	 * ȡ�ڵ㣨node��������(attributeName)��ֵ
	 * @param node �ڵ����
	 * @param attributeName ������
	 * @return
	 */
	public static String getAttributeValue(Element node, String attributeName) {
		if (node == null || node.attribute(attributeName) == null) {
			return ""; 
		}
		return trimToString(node.attribute(attributeName).getStringValue());
	}

	public static Long transToLong(Object object) {
		if (object == null || "".equals(trimToString(object)) ) 
			return null;
		
		return new Long(trimToString(object));
	}

	public static BigDecimal transToBigDecimal(Object object) {
		if (object == null || "".equals(trimToString(object)) ) 
			return new BigDecimal(0);
		
		return new BigDecimal(trimToString(object));
	}
	
	public static Date getCurrentDateTime() {
		Date now = new Date();
	    String str_now = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	    	str_now = sdf.format(now);
	    	now = sdf.parse(str_now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return now;
	}
	
	public static String transToPlainDateFormat(Date date) {
		if (date == null) {
			return "";
		}
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String strDate = null;
	    try {
	    	strDate = sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return StringUtils.trimToString(strDate);
	}
}
