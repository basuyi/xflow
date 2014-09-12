package org.basuyi.xflow.common.web.interceptors;

import java.lang.reflect.Field;
import java.util.Map;

import org.basuyi.xflow.common.AppDefination;
import org.basuyi.xflow.common.BeanUtils;
import org.basuyi.xflow.common.annotation.Service;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class BusinessServiceInterceptor implements Interceptor {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Object serviceObj = null;
        Object action = (Action)invocation.getAction();
      
    	Field [] fields = action.getClass().getDeclaredFields();
    	
    	for (Field field : fields) {
    		
    		Service service = field.getAnnotation(Service.class);
    		
    		if (service != null) {
	            String serviceName = service.name();
            	Map servletContext = invocation.getInvocationContext().getApplication();
            	ApplicationContext context = (ApplicationContext)servletContext.get(AppDefination.C_APPLICATION_CONTEXT);
            	serviceObj = context.getBean(serviceName);
	            BeanUtils.setProperty(action, field.getName(), serviceObj);
    		}
        }
        return invocation.invoke();
	}
}
