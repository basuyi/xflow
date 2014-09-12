package net.ms.designer.editors.packages.editpolicies;

import net.ms.designer.editors.packages.commands.PropertyCommand;
import net.ms.designer.editors.packages.models.Package;

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
    	command.setPropertyObject((Package)getHost().getModel());
    	command.setContainer(getHost().getParent().getModel());
    	return command;
    }
}