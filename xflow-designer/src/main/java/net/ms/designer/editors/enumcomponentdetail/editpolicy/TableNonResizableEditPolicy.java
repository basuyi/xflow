package net.ms.designer.editors.enumcomponentdetail.editpolicy;

import net.ms.designer.editors.enumcomponentdetail.command.TableMoveCommand;
import net.ms.designer.editors.enumcomponentdetail.model.Table;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;


public class TableNonResizableEditPolicy extends NonResizableEditPolicy 
{
    protected Command getMoveCommand(ChangeBoundsRequest request) 
    {
        TableMoveCommand command = new TableMoveCommand();
        command.setModel((Table)getHost().getModel());
        command.setRequest(request);
        return command;
    }
}
