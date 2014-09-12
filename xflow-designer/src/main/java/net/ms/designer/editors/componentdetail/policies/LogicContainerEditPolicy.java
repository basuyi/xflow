package net.ms.designer.editors.componentdetail.policies;

import java.util.List;

import net.ms.designer.editors.componentdetail.commands.CreateCommand;
import net.ms.designer.editors.componentdetail.editparts.DiagramEditPart;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.Messages;
import net.ms.designer.editors.componentdetail.models.Table;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;


/**
 * @author lili
 * @version 1.1.0
 *defind the LogicContainerEditPolicy 
 */
public class LogicContainerEditPolicy extends ContainerEditPolicy {

	/*
	 *  £¨·Ç Javadoc£©
	 * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request) {
		
		return null;
	}

	public Command getOrphanChildrenCommand(GroupRequest request) {
		List parts = request.getEditParts();
		CompoundCommand result = null;
		
		if (getHost().getModel() instanceof Container) {
			result = new CompoundCommand(Messages.getString("")); //$NON-NLS-1$
			for (int i = 0; i < parts.size(); i++) {
				CreateCommand orphan = new CreateCommand();
				orphan
						.setChild((Element) ((EditPart) parts.get(i))
								.getModel());
				orphan.setParent((Container) getHost().getModel());
//				orphan.setLabel(Messages.getString("LogicContainerEditPolicy.OrphanCommand")); //$NON-NLS-1$
				result.add(orphan);
			}
		}

		if (result != null)
			return result.unwrap();
		else
			return null;
	}

}