package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.WireCommand;
import net.ms.designer.editors.workflow.commands.WorkflowPropertyCommand;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;


public class WireEditPolicy extends ConnectionEditPolicy
{
	private static final String PROPERTY_REQUEST = "WORKFLOW_PROPERTY"; 

	protected Command getDeleteCommand(GroupRequest request)
	{
		// TODO Auto-generated method stub
		WireCommand c = new WireCommand();
		c.setWire((Wire)getHost().getModel());
		c.setFlag("delete");
		return c;
	}
	
	    
    public Command getCommand(Request request) 
    {
    	if (PROPERTY_REQUEST.equals(request.getType()))
    		return getCopyCommand();
    	return super.getCommand(request);
    }

    protected Command getCopyCommand()
    {
//    	 The implementation of CopyCommand is simple, will not be explained here.
    	WireCommand command = new WireCommand();
    	command.setWire((Wire)getHost().getModel());
    	command.setFlag("property");
    	return command;
    }

}
