package org.basuyi.xflow.core.actions;

import java.util.HashMap;
import java.util.Map;

import org.basuyi.xflow.core.WorflowEngine;
import org.basuyi.xflow.core.config.model.WorkflowContext;
import org.basuyi.xflow.core.config.model.WorkflowDefination;
import org.basuyi.xflow.core.config.model.WorkflowException;
import org.basuyi.xflow.core.utils.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �ӹ���������ִ�ж���,����������
 * @author mashuai
 * @version 0.1
 * @date 2009-10-23
 */
public class SubflowAction implements WorkflowAction {

	public void execute(WorkflowContext context) throws WorkflowException {
		String subflowId = (String)context.get(WorkflowDefination.CONTEXT_KEY_SUBFLOW_ID);
		//DefaultWorkflowEngine engine = new DefaultWorkflowEngine();
		ApplicationContext app_context = (ApplicationContext)(((Map)context.getPrivate(WorkflowDefination.IN_PARAMS)).get(WorkflowDefination.CONTEXT_KEY_APP_CONTEXT));
		WorflowEngine engine = (WorflowEngine)app_context.getBean("workflow");
		
		 /*����������*/
		Map<String, Object> params = new HashMap<String, Object>();
		String upper_workflowId = StringUtils.trimToString(context.get(WorkflowDefination.CONTEXT_KEY_WORKFLOW_ID));
		String upper_activityId = StringUtils.trimToString(context.get(WorkflowDefination.CONTEXT_KEY_UPPER_ACTIVITY_ID));
		String workflowId = upper_workflowId + "." + subflowId;
		if (!upper_activityId.equals("")) {
			String activityId = upper_activityId + "." + context.getCurrentActivity().getActivityId();
			params.put(WorkflowDefination.UPPER_ACTIVITY_ID, activityId);
		} else {
			params.put(WorkflowDefination.UPPER_ACTIVITY_ID, context.getCurrentActivity().getActivityId());
		}
		params.put(WorkflowDefination.WORKFLOW_ID, workflowId);
		params.put(WorkflowDefination.USER_ID, context.get(WorkflowDefination.CONTEXT_KEY_USER_ID));
		params.put(WorkflowDefination.POST_ID, context.get(WorkflowDefination.CONTEXT_KEY_POST_ID));
		params.put(WorkflowDefination.ORG_ID, context.get(WorkflowDefination.CONTEXT_KEY_ORG_ID));
		params.put(WorkflowDefination.IS_SUBFLOW, "1");
		params.put(WorkflowDefination.UPPER_WORKFLOW, context.getWorkflow());
		params.put(WorkflowDefination.UPPER_WF_PARAMS, context.getParams());
		params.put(WorkflowDefination.UPPER_WORKFLOW_ID, context.getWorkflow().getWorkflowId());
		Map<String, Object> out_params = engine.startWorkflow(params);
		WorkflowContext subContext = (WorkflowContext)out_params.get(WorkflowDefination.WORKFLOW_CONTEXT);
		context.setParams(subContext.getParams());
		context.put(WorkflowDefination.CONTEXT_KEY_WORKFLOW_ID, upper_workflowId);
		context.put(WorkflowDefination.CONTEXT_KEY_UPPER_ACTIVITY_ID, upper_activityId);
	}
}
