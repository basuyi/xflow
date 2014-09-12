package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.RenameNodeCommand;
import net.ms.designer.editors.workflow.figures.NodeFigure;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;


public class NodeDirectEditPolicy extends DirectEditPolicy
{

	protected Command getDirectEditCommand(DirectEditRequest request) 
	{
		// TODO Auto-generated method stub
		RenameNodeCommand cmd = new RenameNodeCommand();
        cmd.setNode((WorkflowSubPart) getHost().getModel());
        cmd.setName((String) request.getCellEditor().getValue());
        return cmd;
	}

	protected void showCurrentEditValue(DirectEditRequest request) 
	{
		// TODO Auto-generated method stub
		String value = (String) request.getCellEditor().getValue();
        ((NodeFigure) getHostFigure()).setName(value);
	}

}
