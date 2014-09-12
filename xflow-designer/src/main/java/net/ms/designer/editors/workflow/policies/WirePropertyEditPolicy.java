package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.WireCommand;
import net.ms.designer.editors.workflow.commands.WorkflowPropertyCommand;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;

public class WirePropertyEditPolicy extends DiagramLayoutEditPolicy {

	 private static final String
	    PROPERTY_REQUEST = "WIRE_PROPERTY"; //$NON-NLS-1$
	    
	    public Command getCommand(Request request) 
	    {
	    	//if (PROPERTY_REQUEST.equals(request.getType()))
	    	if ("PROPERTY".equals(request.getType()))
	    		return getWirePropertyCommand();
	    	return super.getCommand(request);
	    }

	    protected Command getWirePropertyCommand()
	    {
//	    	 The implementation of CopyCommand is simple, will not be explained here.
	    	WireCommand command = new WireCommand();
	    	command.setWire((Wire)getHost().getModel());
	    	command.setFlag(PROPERTY_REQUEST);
	    	return command;
	    }
}
