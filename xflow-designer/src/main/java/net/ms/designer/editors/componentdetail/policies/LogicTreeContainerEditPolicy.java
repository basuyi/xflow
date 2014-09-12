package net.ms.designer.editors.componentdetail.policies;

import java.util.List;

import net.ms.designer.editors.componentdetail.commands.CreateCommand;
import net.ms.designer.editors.componentdetail.commands.ReorderPartCommand;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.TreeContainerEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;



public class LogicTreeContainerEditPolicy extends TreeContainerEditPolicy {

	protected Command createCreateCommand(Element child, Rectangle r,
			int index, String label) {
		CreateCommand cmd = new CreateCommand();
		Rectangle rect;
		//System.out.println(r);
		if (r == null) {
			rect = new Rectangle();
			rect.setSize(new Dimension(-1, -1));
		} else {
			rect = r;
		}
		cmd.setLocation(rect);
		cmd.setParent((Container) getHost().getModel());
		cmd.setChild(child);
		cmd.setLabel(label);
		if (index >= 0)
			cmd.setIndex(index);
		return cmd;
	}

	protected Command getAddCommand(ChangeBoundsRequest request) {
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel("Add in LogicTreeContainerEditPolicy");
		List editparts = request.getEditParts();
		int index = findIndexOfTreeItemAt(request.getLocation());

		for (int i = 0; i < editparts.size(); i++) {
			EditPart child = (EditPart) editparts.get(i);
			if (isAncestor(child, getHost()))
				command.add(UnexecutableCommand.INSTANCE);
			else {
				//                KCGField childModel = (KCGField) child.getModel();
				//                command.add(createCreateCommand(childModel, new Rectangle(
				//                        new org.eclipse.draw2d.geometry.Point(), childModel
				//                                .getSize()), index, "Reparent KCGField"));//$NON-NLS-1$
			}
		}
		return command;
	}

	protected Command getCreateCommand(CreateRequest request) {
		Element child = (Element) request.getNewObject();
		int index = findIndexOfTreeItemAt(request.getLocation());
		return createCreateCommand(child, null, index, "Create Field");
	}

	protected Command getMoveChildrenCommand(ChangeBoundsRequest request) {
		CompoundCommand command = new CompoundCommand();
		List editparts = request.getEditParts();
		List children = getHost().getChildren();
		int newIndex = findIndexOfTreeItemAt(request.getLocation());

		for (int i = 0; i < editparts.size(); i++) {
			EditPart child = (EditPart) editparts.get(i);
			int tempIndex = newIndex;
			int oldIndex = children.indexOf(child);
			if (oldIndex == tempIndex || oldIndex + 1 == tempIndex) {
				command.add(UnexecutableCommand.INSTANCE);
				return command;
			} else if (oldIndex < tempIndex) {
				tempIndex--;
			}
			command.add(new ReorderPartCommand((Element) child.getModel(),
					(Container) getHost().getModel(), oldIndex, tempIndex));
		}
		return command;
	}

	protected boolean isAncestor(EditPart source, EditPart target) {
		if (source == target)
			return true;
		if (target.getParent() != null)
			return isAncestor(source, target.getParent());
		return false;
	}

}