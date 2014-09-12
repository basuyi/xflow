/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

public class WorkflowTreeEditPolicy
	extends AbstractEditPolicy
{

public Command getCommand(Request req){
	if (REQ_MOVE.equals(req.getType()))
		return getMoveCommand(req);
	return null;	
}

protected Command getMoveCommand(Request req){
	EditPart parent = getHost().getParent();
	if(parent != null){
		req.setType(REQ_MOVE_CHILDREN);
		Command cmd = parent.getCommand(req);
		req.setType(REQ_MOVE);
		return cmd;
	} else {
		return UnexecutableCommand.INSTANCE;
	}
}

}