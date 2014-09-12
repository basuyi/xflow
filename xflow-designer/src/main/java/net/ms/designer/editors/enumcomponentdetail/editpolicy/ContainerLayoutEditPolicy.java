package net.ms.designer.editors.enumcomponentdetail.editpolicy;

import net.ms.designer.editors.enumcomponentdetail.command.CreateCommand;
import net.ms.designer.editors.enumcomponentdetail.editpart.TableEditPart;
import net.ms.designer.editors.enumcomponentdetail.model.Element;
import net.ms.designer.editors.enumcomponentdetail.model.Table;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;


public class ContainerLayoutEditPolicy extends LayoutEditPolicy 
{
    protected EditPolicy createChildEditPolicy(EditPart child) 
    {        
    	if(child instanceof TableEditPart) 
    		return new TableNonResizableEditPolicy();
        return new NonResizableEditPolicy();
    }

    protected Command getCreateCommand(CreateRequest request) 
    {
        Object obj = request.getNewObject();
        if(obj != null && request.getNewObjectType() == Table.class)
        {            
        	CreateCommand command = new CreateCommand();
            command.setParent((Element)this.getHost().getModel());
            command.setChild((Element)obj);
            ((Table)obj).setLocation(request.getLocation());
            return command;
        }
        return null;
    }

    protected Command getDeleteDependantCommand(Request request) 
    {
        return null;
    }

    protected Command getMoveChildrenCommand(Request request) 
    {
        return null;
    }
}
