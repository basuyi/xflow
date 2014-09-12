package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.DeleteNodeCommand;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;


public class WorkflowElementEditPolicy extends ComponentEditPolicy
{
	protected Command createDeleteCommand(GroupRequest request) 
	{
		Object parent = getHost().getParent().getModel();
		DeleteNodeCommand deleteCmd = new DeleteNodeCommand();
		deleteCmd.setParent((WorkflowDiagram)parent);
		deleteCmd.setChild((WorkflowSubPart)getHost().getModel());
		return deleteCmd;
	}

}
