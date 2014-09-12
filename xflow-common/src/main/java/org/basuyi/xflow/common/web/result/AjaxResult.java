package org.basuyi.xflow.common.web.result;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;


import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.Result;
import com.opensymphony.webwork.ServletActionContext;

public class AjaxResult implements Result {
	
	private String property;

	public void setProperty(String property) {
		this.property = property;
	}

	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = (HttpServletResponse)invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		if ("success".equals(invocation.getResultCode())) {
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");
			PrintWriter writer = response.getWriter(); 
			writer.print("OK");
		} else if ("report".equals(invocation.getResultCode())) {
			Action action = (Action)invocation.getAction();
			String methodName = "get" + property.substring(0, 1).toUpperCase() +
								property.substring(1);
			Method method = action .getClass().getMethod(methodName, new Class[0]);
			String report = (String)method.invoke(action, new Object[0]);

			response.setContentType("text/xml;charset=gb2312");
			response.setHeader("Cache-Control","no-cache");
			PrintWriter writer = response.getWriter(); 
			writer.print(report);
		}
	}

}
