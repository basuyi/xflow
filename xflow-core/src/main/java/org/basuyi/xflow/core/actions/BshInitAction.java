package org.basuyi.xflow.core.actions;

import org.basuyi.xflow.core.config.model.WorkflowContext;
import org.basuyi.xflow.core.config.model.WorkflowException;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * ��������ʼ��BSH����
 * @author mashuai
 * @version 0.1
 * @date 2010-12-10
 */
public class BshInitAction implements WorkflowAction {

	public void execute(WorkflowContext context) throws WorkflowException {
		Interpreter i = new Interpreter();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader != null) {
            i.setClassLoader(loader);
        }
        try {
			i.set("context", context);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.put("Interpreter", i);
	}

}
