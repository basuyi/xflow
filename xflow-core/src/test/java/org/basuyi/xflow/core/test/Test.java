package org.basuyi.xflow.core.test;

import java.util.HashMap;
import java.util.Map;

import org.basuyi.xflow.core.WorflowEngine;
import org.basuyi.xflow.core.config.DBWFConfig;
import org.basuyi.xflow.core.config.model.WorkflowDefination;
import org.basuyi.xflow.core.config.model.WorkflowException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("workflow_context.xml");
		//testWorkflow(context);
		testDBWF(context);
	}

	private static void testDBWF(ApplicationContext context) {
		DBWFConfig config = (DBWFConfig)context.getBean("dbwfConfig");
		config.initialize();
		testWorkflow(context);
	}

	private static void testWorkflow(ApplicationContext context) {
		WorflowEngine engine = (WorflowEngine)context.getBean("workflowProxy");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(WorkflowDefination.WORKFLOW_ID, "example");
		params.put(WorkflowDefination.USER_ID, "0");
		params.put(WorkflowDefination.POST_ID, "0");
		params.put(WorkflowDefination.ORG_ID, "0");
//		params.put(WorkflowDefination.WF_INST_ID, "000000000000019");
//		params.put(WorkflowDefination.WF_PROCESS_SEQ, "000000000000128");
//		params.put(WorkflowDefination.USER_ENTRY, "p001");
		try {
			engine.startWorkflow(params);
		} catch (WorkflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
