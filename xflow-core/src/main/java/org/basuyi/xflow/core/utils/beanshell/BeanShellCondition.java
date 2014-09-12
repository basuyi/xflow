package org.basuyi.xflow.core.utils.beanshell;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.TargetError;

import org.apache.log4j.Logger;
import org.basuyi.xflow.core.actions.WorkflowCondition;
import org.basuyi.xflow.core.config.model.WfConfigArgument;
import org.basuyi.xflow.core.config.model.WorkflowContext;
import org.basuyi.xflow.core.config.model.WorkflowDefination;
import org.basuyi.xflow.core.config.model.WorkflowException;

/**
 * ������BSH��������
 * @author mashuai
 * @version 0.1
 * @date 2009-10-19
 */
public class BeanShellCondition implements WorkflowCondition {
    //~ Static fields/initializers /////////////////////////////////////////////

    private static final Logger log = Logger.getLogger(BeanShellCondition.class);

    //~ Methods ////////////////////////////////////////////////////////////////

    public boolean processCondition(WorkflowContext context) throws WorkflowException {
        WfConfigArgument arg = (WfConfigArgument)context.get(WorkflowDefination.CONTEXT_KEY_ARGUMENT);
        String script = arg.getArgValue();
        
        Interpreter i = (Interpreter)context.get("Interpreter");
        try {
        	Object o = i.eval(script);
            if (o == null) {
                return false;
            } else {
                return (Boolean)o;
            }
        } catch (TargetError targetError) {
            if (targetError.getTarget() instanceof WorkflowException) {
                throw (WorkflowException) targetError.getTarget();
            } else {
            	String message = "Could not execute BeanShell script";
                throw new WorkflowException(message, targetError);
            }
        } catch (EvalError e) {
            String message = "Could not execute BeanShell script";
            log.error(message, e);
            throw new WorkflowException(message, e);
        }
    }
}
