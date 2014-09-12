package net.ms.designer.editors.component.editpolicies;

import net.ms.designer.editors.component.commands.PropertyCommand;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;





public class PropertyEditPolicy extends DiagramLayoutEditPolicy
{
    private static final String
    PROPERTY_REQUEST = "PROPERTY"; //$NON-NLS-1$
    
    public Command getCommand(Request request) 
    {
    	if (PROPERTY_REQUEST.equals(request.getType()))
    		return getCopyCommand();
    	return super.getCommand(request);
    }

    protected Command getCopyCommand()
    {
//    	 The implementation of CopyCommand is simple, will not be explained here.
    	PropertyCommand command = new PropertyCommand();
    	command.setPropertyObject((Component)getHost().getModel());
    	command.setDiagram((CompDiagram)getHost().getParent().getModel());
    	return command;
    }
}