/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.policies;

import java.util.List;

import net.ms.designer.editors.workflow.commands.CreateNodeCommand;
import net.ms.designer.editors.workflow.commands.ReorderPartCommand;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.TreeContainerEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;



public class WorkflowTreeContainerEditPolicy 
	extends TreeContainerEditPolicy 
{
	
protected Command createCreateCommand(WorkflowSubPart child, Rectangle r, 
						 int index, String label){
	CreateNodeCommand cmd = new CreateNodeCommand();
	Rectangle rect;
	if(r == null){
		rect = new Rectangle();
		rect.setSize(new Dimension(-1,-1));
	} else {
		rect = r;
	}
	cmd.setLocation(rect.getLocation());
	cmd.setParent((WorkflowDiagram)getHost().getModel());
	cmd.setChild(child);
	cmd.setLabel(label);
	if(index >= 0)
		cmd.setIndex(index);
	return cmd;
}

protected Command getAddCommand(ChangeBoundsRequest request){
	CompoundCommand command = new CompoundCommand();
	command.setDebugLabel("Add in LogicTreeContainerEditPolicy");//$NON-NLS-1$
	List editparts = request.getEditParts();
	int index = findIndexOfTreeItemAt(request.getLocation());
	
	for(int i = 0; i < editparts.size(); i++){
		EditPart child = (EditPart)editparts.get(i);
		if(isAncestor(child,getHost()))
			command.add(UnexecutableCommand.INSTANCE);
		else {
			WorkflowSubPart childModel = (WorkflowSubPart)child.getModel();
			command.add(createCreateCommand(
						childModel,
						new Rectangle(new org.eclipse.draw2d.geometry.Point(),
							childModel.getSize()),
						index, "Reparent LogicSubpart"));//$NON-NLS-1$
		}
	}
	return command;
}

protected Command getCreateCommand(CreateRequest request){
	WorkflowSubPart child = (WorkflowSubPart)request.getNewObject();
	int index = findIndexOfTreeItemAt(request.getLocation());
	return createCreateCommand(child, null, index, "Create LogicSubpart");//$NON-NLS-1$
}

protected Command getMoveChildrenCommand(ChangeBoundsRequest request){
	CompoundCommand command = new CompoundCommand();
	List editparts = request.getEditParts();
	List children = getHost().getChildren();
	int newIndex = findIndexOfTreeItemAt(request.getLocation());
		
	for(int i = 0; i < editparts.size(); i++){
		EditPart child = (EditPart)editparts.get(i);
		int tempIndex = newIndex;
		int oldIndex = children.indexOf(child);
		if(oldIndex == tempIndex || oldIndex + 1 == tempIndex){
			command.add(UnexecutableCommand.INSTANCE);
			return command;
		} else if(oldIndex < tempIndex){
			tempIndex--;
		}
		command.add(new ReorderPartCommand(
					(WorkflowSubPart)child.getModel(), 
					(WorkflowDiagram)getHost().getModel(), 
					oldIndex, tempIndex)); 
	}
	return command;
}

protected boolean isAncestor(EditPart source, EditPart target){
	if(source == target)
		return true;
	if(target.getParent() != null)
		return isAncestor(source, target.getParent());
	return false;
}

}