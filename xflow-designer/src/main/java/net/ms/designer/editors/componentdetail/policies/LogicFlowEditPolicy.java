package net.ms.designer.editors.componentdetail.policies;

import java.util.Iterator;

import net.ms.designer.editors.componentdetail.commands.AddCommand;
import net.ms.designer.editors.componentdetail.commands.CopyCommand;
import net.ms.designer.editors.componentdetail.commands.CreateCommand;
import net.ms.designer.editors.componentdetail.commands.ReorderPartCommand;
import net.ms.designer.editors.componentdetail.editparts.CommonEditPart;
import net.ms.designer.editors.componentdetail.editparts.CompTableEditPart;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.enumcomponentdetail.editpart.ValueFieldEditPart;
import net.ms.designer.editors.enumcomponentdetail.editpolicy.ValueFieldSelectionEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;


public class LogicFlowEditPolicy extends
		org.eclipse.gef.editpolicies.FlowLayoutEditPolicy {

	/**
	 * Override to return the <code>Command</code> to perform an {@link
	 * RequestConstants#REQ_CLONE CLONE}. By default, <code>null</code> is
	 * returned.
	 * 
	 * @param request
	 *            the Clone Request
	 * @return A command to perform the Clone.
	 */
	protected Command getCloneCommand(ChangeBoundsRequest request) {
		CopyCommand clone = null;

		//System.out.println("policies.LogicFlowEditPolicy.getClonecommand()");
		if (getHost().getModel() instanceof Container) {
			clone = new CopyCommand();
			clone.setParent((Container) getHost().getModel());

			EditPart after = getInsertionReference(request);
			int index = getHost().getChildren().indexOf(after);

			Iterator i = request.getEditParts().iterator();
			GraphicalEditPart currPart = null;

			while (i.hasNext()) {
				currPart = (GraphicalEditPart) i.next();
				clone.addPart((Element) currPart.getModel(), index++);
			}
		}

		return clone;
	}

	protected Command createAddCommand(EditPart child, EditPart after) {
		AddCommand command = null;
		//拖放加入
		if (getHost().getModel() instanceof Container && !(child instanceof CompTableEditPart)) {
			command = new AddCommand();
			command.setChild((Element) child.getModel());
			command.setParent((Container) getHost().getModel());
			int index = getHost().getChildren().indexOf(after);
			command.setIndex(index);
		}

		return command;
	}

	/**
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
	 */
	protected EditPolicy createChildEditPolicy(EditPart child) {
		if(child instanceof CommonEditPart)
            return new CommonFieldSelectionEditPolicy();
//        return super.createChildEditPolicy(child);
		else
		{
			LogicResizableEditPolicy policy = new LogicResizableEditPolicy();
			policy.setResizeDirections(0);
			return policy;
		}
	}

	protected Command createMoveChildCommand(EditPart child, EditPart after) {
		ReorderPartCommand command = null;

		if (getHost().getModel() instanceof Container) {
			Element childModel = (Element) child.getModel();
			Container parentModel = (Container) getHost().getModel();
			int oldIndex = getHost().getChildren().indexOf(child);
			int newIndex = getHost().getChildren().indexOf(after);
			if (newIndex > oldIndex)
				newIndex--;
			command = new ReorderPartCommand(childModel, parentModel, oldIndex,
					newIndex);
		}

		return command;
	}

	protected Command getCreateCommand(CreateRequest request) {
		CreateCommand command = null;
		
		if (getHost().getModel() instanceof Container
				&& !(request.getNewObject() instanceof ChildTable)) {
			//System.out.println("LogicFlowEditPolicy.getCreatCommand()");
			//表不能嵌入到另一表中
			command = new CreateCommand();
			EditPart after = getInsertionReference(request);
			command.setChild((Element) request.getNewObject());
			command.setParent((Container) getHost().getModel());
//			command.setParent((ComponentTable) getHost().getModel());
			int index = getHost().getChildren().indexOf(after);
			command.setIndex(index);
		}
		return command;
	}

	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	protected Command getOrphanChildrenCommand(Request request) {
		return null;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.gef.editpolicies.FlowLayoutEditPolicy#isHorizontal()
	 */
	protected boolean isHorizontal() {
		IFigure figure = ((GraphicalEditPart) getHost()).getContentPane();
		if (figure.getLayoutManager() instanceof ToolbarLayout)
			return ((ToolbarLayout) figure.getLayoutManager()).isHorizontal();
		else if (figure.getLayoutManager() instanceof StackLayout)
			return false;
		else
			return super.isHorizontal();
	}
}