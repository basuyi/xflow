package net.ms.designer.editors.enumcomponentdetail.editpolicy;

import net.ms.designer.editors.enumcomponentdetail.command.PropertyCommand;
import net.ms.designer.editors.enumcomponentdetail.model.Element;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;


public class PropertyEditPolicy extends ContainerLayoutEditPolicy
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
    	command.setCopyObject((Element)getHost().getModel());
    	return command;
    }
}