/**
 * @author liuchunxia
 * 
 * get orphan Children command
 */
package net.ms.designer.editors.workflow.policies;

import java.util.List;

import net.ms.designer.editors.workflow.commands.OrphanChildCommand;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;



public class WorkflowContainerEditPolicy
	extends ContainerEditPolicy
{

protected Command getCreateCommand(CreateRequest request) {
	return null;
}

public Command getOrphanChildrenCommand(GroupRequest request) {
	List parts = request.getEditParts();
	CompoundCommand result = 
		new CompoundCommand("OrphanCommandLabelText");
	for (int i = 0; i < parts.size(); i++) {
		OrphanChildCommand orphan = new OrphanChildCommand();
		orphan.setChild((WorkflowSubPart)((EditPart)parts.get(i)).getModel());
		orphan.setParent((WorkflowDiagram)getHost().getModel());
		orphan.setLabel("OrphanCommandLabelText");
		result.add(orphan);
	}
	return result.unwrap();
}

}