package net.ms.designer.editors.workflow.commands;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsElement;
import net.ms.designer.editors.packages.ui.PackageEditor;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.models.StartNode;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.internal.UIPlugin;

public class DeleteNodeCommand extends Command {

	private WorkflowSubPart child;
	private WorkflowDiagram parent;
	// private WorkflowGuide vGuide, hGuide;
	// private int vAlign, hAlign;
	private int index = -1;
	private List sourceConnections = new ArrayList();
	private List targetConnections = new ArrayList();

	public DeleteNodeCommand() {
		super(Messages.getString("DeleteCommand.name")); //$NON-NLS-1$
	}

	public void setParent(WorkflowDiagram parent) {
		this.parent = parent;
	}

	public void setChild(WorkflowSubPart child) {
		this.child = child;
	}

	public void execute() {
		index = this.parent.getChildren().indexOf(child);
		this.deleteConnections(child);
		this.parent.removeChild(child);
		if (this.child instanceof SubFlowActivity) {
			MsContext context = ((PackageEditor) UIPlugin.getDefault()
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor()).getContext();
			MsElement childElement = context.getElement("subflow_" + child.getName());
			MsElement parentElement = childElement.getParent();
			if (parentElement != null)
				parentElement.removeChild(childElement);
			context.removeElement("subflow_" + child.getName());
		}
	}

	private void deleteConnections(WorkflowSubPart part) {
		if (part instanceof WorkflowDiagram) {
			List children = ((WorkflowDiagram) part).getChildren();
			for (int i = 0; i < children.size(); i++)
				deleteConnections((WorkflowSubPart) children.get(i));
		}
		sourceConnections.addAll(part.getInputs());
		for (int i = 0; i < sourceConnections.size(); i++) {
			Wire wire = (Wire) sourceConnections.get(i);
			wire.removeSource();
			wire.removeTarget();
		}
		targetConnections.addAll(part.getOutputs());
		for (int i = 0; i < targetConnections.size(); i++) {
			Wire wire = (Wire) targetConnections.get(i);
			wire.removeSource();
			wire.removeTarget();
		}
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		this.parent.addChild(child, index);
		this.restoreConnections();
	}

	public boolean canExecute() {
		if (child instanceof StartNode || child.ifEditAble() == false) {
			return false;
		}
		return true;
	}

	private void restoreConnections() {
		for (int i = 0; i < sourceConnections.size(); i++) {
			Wire wire = (Wire) sourceConnections.get(i);
			wire.addSource();
			wire.addTarget();
		}
		sourceConnections.clear();
		for (int i = 0; i < targetConnections.size(); i++) {
			Wire wire = (Wire) targetConnections.get(i);
			wire.addSource();
			wire.addTarget();
		}
		targetConnections.clear();
	}
}
