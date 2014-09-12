package net.ms.designer.editors.enumcomponentdetail.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

public class ValueFieldNodeEditPolicy extends GraphicalNodeEditPolicy 
{
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) 
    {        
    	return null;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) 
    {       
    	return null;
    }

    protected Command getReconnectTargetCommand(ReconnectRequest request) 
    {
        return null;
    }
    
    protected Command getReconnectSourceCommand(ReconnectRequest request) 
    {
        return null;
    }
}
