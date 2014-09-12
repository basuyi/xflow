package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.WorkflowPropertyCommand;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;




public class WorkflowPropertyEditPolicy extends DiagramLayoutEditPolicy
{
	 private static final String
	    PROPERTY_REQUEST = "WORKFLOW_PROPERTY"; //$NON-NLS-1$
	    
	    public Command getCommand(Request request) 
	    {
	    	//if (PROPERTY_REQUEST.equals(request.getType()))
	    	if ("PROPERTY".equals(request.getType()))
	    		return getCopyCommand();
	    	return super.getCommand(request);
	    }

	    protected Command getCopyCommand()
	    {
//	    	 The implementation of CopyCommand is simple, will not be explained here.
	    	WorkflowPropertyCommand command = new WorkflowPropertyCommand();
	    	command.setSubpart((WorkflowSubPart)getHost().getModel());
	    	if(getHost().getModel() instanceof WorkflowBaseActivity)
	    	{
	    		//若为节点则取出diagram，方便取出全局变量
	    		command.setDiagram((WorkflowDiagram)getHost().getParent().getModel());
	    	}
	    	return command;
	    }
}
