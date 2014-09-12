package net.ms.framework.service.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtils {
	
	public static void setProperty(Object obj, String propertyName, Object value) {
		invoke(obj, propertyName, new Object[]{value});
	}
	
	public static Object getProperty(Object obj, String propertyName) {
		return invoke(obj, propertyName);
	}
	
	public static Class getClass(Object obj) {
		Class cls;
		cls = obj.getClass();
		return cls;
	}
	
	public static Class getClass(String clsName) {
		Class cls = null;
		try {
			cls = Class.forName(clsName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cls;
	}
	
	public static Object newInstance(String clsName) {
		Class cls = getClass(clsName);
		Object obj = newInstance(cls);
		return obj;
	}
	
	public static Object newInstance(Class cls) {
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public static Method getMethod(Object obj, String propertyName) throws Exception {
		Method method;
		Class cls = getClass(obj);
		method = getMethod(cls, propertyName);
		return method;
	}
	
	public static Method getMethod(Class cls, String propertyName) throws Exception {
		Method result = null;
		Method [] methodArr = cls.getMethods();
		for (int i = 0; i < methodArr.length; i++) {
			Method method = methodArr[i];
			String methodName = method.getName();
			if (methodName.length() > 3) {
				String tmp = methodName.substring(3);
				String prefixToken = tmp.substring(0,1);
				if (tmp.length() > 1) {
					String subToken = tmp.substring(1);
					prefixToken = prefixToken.toLowerCase();
					tmp = prefixToken + subToken;
					if (tmp.equals(propertyName)) {
						result = method;
					}
				} else {
					if (tmp.equalsIgnoreCase(propertyName)) {
						result = method;
					}
				}
			}
		}
		if (result == null) {
			throw new Exception();
		}
		return result;
	}
	
	public static boolean isPopropertyContained(Object obj, String propertyName) {
		Class cls = getClass(obj);
		return isPopropertyContained(cls, propertyName);
	}
	
	public static boolean isPopropertyContained(Class cls, String propertyName) {
		boolean result = false;
		Method [] methodArr = cls.getMethods();
		for (int i = 0; i < methodArr.length; i++) {
			Method method = methodArr[i];
			String methodName = method.getName();
			if (methodName.length() > 3) {
				String tmp = methodName.substring(3);
				String prefixToken = tmp.substring(0,1);
				if (tmp.length() > 1) {
					String subToken = tmp.substring(1);
					prefixToken = prefixToken.toLowerCase();
					tmp = prefixToken + subToken;
					if (tmp.equals(propertyName)) {
						result = true;
					}
				} else {
					if (tmp.equalsIgnoreCase(propertyName)) {
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	private static Object invoke(Object obj, String propertyName, Object[] args) {
		Object result = null;
		try {
			Method method = getMethod(obj, propertyName);
			result = method.invoke(obj, args);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private static Object invoke(Object obj, String propertyName) {
		Object result = null;
		try {
			Method method = getMethod(obj, propertyName);
			result = method.invoke(obj, new Object[]{});
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
