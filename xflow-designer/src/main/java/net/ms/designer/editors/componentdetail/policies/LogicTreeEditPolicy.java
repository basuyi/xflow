package net.ms.designer.editors.componentdetail.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

/**
 * @author lili
 * @version 1.1.0
 *defind the LogicTreeEditPolicy
 */
public class LogicTreeEditPolicy extends AbstractEditPolicy {

	/*
	 *  £¨·Ç Javadoc£©
	 * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request req) {
		if (REQ_MOVE.equals(req.getType()))
			return getMoveCommand(req);
		return null;
	}

	/**
	 * 
	 * @param req
	 * @return the request of command
	 */
	protected Command getMoveCommand(Request req) {
		EditPart parent = getHost().getParent();
		if (parent != null) {
			req.setType(REQ_MOVE_CHILDREN);
			Command cmd = parent.getCommand(req);
			req.setType(REQ_MOVE);
			return cmd;
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}

}