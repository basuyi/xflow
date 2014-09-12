package org.basuyi.xflow.core.utils.web;

import java.util.HashMap;
import java.util.Map;

public class WebWorkflowContext {
	private static ThreadLocal<Map<String, Object>> thread = new ThreadLocal<Map<String, Object>>();
	private static final String HTTP_SERVLET_REQUEST = "http_servlet_request";
	private static final String HTTP_SERVLET_RESPONSE = "http_servlet_response";
	private static Map<String, Object> context = new HashMap<String, Object>();
	
	{
		thread.set(context);
	}
	
	private static Map<String, Object> getContext() {
		return thread.get();
	}
	
	/**
	 * �����󻺴浽ThreadLocal��
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value) {
		getContext().put(key, value);
	}
	
	/**
	 * ��ThreadLocal��ȡ����
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return getContext().get(key);
	}
	
	/**
	 * ȡ��HttpServletRequest
	 * @return
	 */
	public static Object getRequest() {
		return get(HTTP_SERVLET_REQUEST);
	}
	
	/**
	 * ȡ��HttpServletResponse
	 * @return
	 */
	public static Object getResponse() {
		return get(HTTP_SERVLET_RESPONSE);
	}
	
	/**
	 * ��HttpServletRequest���浽ThreadLocal��
	 * @param request
	 */
	public static void setRequest(Object request) {
		put(HTTP_SERVLET_REQUEST, request);
	}
	
	/**
	 * ��HttpServletResponse���浽ThreadLocal��
	 * @param request
	 */
	public static void setResponse(Object response) {
		put(HTTP_SERVLET_RESPONSE, response);
	}
}
