package net.ms.designer.editors.workflow.commands;

import java.util.Iterator;
import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.editors.packages.ui.PackageEditor;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.dialog.WorkflowBaseActivityPropertyDialog;
import net.ms.designer.editors.workflow.models.StartNode;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.internal.UIPlugin;

public class CreateNodeCommand extends Command {

	private WorkflowSubPart child;
	// private Rectangle rect;
	private WorkflowDiagram parent;
	private int index = -1;
	private Point location;
	private PackageEditor editor;

	public CreateNodeCommand() {
		super(Messages.getString("CreateCommand.0")); //$NON-NLS-1$
		this.editor = (PackageEditor) UIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}

	public boolean canExecute() {
		List lchild = parent.getChildren();
		for (Iterator i = lchild.iterator(); i.hasNext();) {
			WorkflowBaseActivity temp = (WorkflowBaseActivity) i.next();
			if (child instanceof StartNode)
				if (temp instanceof StartNode)
					return false;
		}
		return true;
	}

	public void execute() {
		if (this.child instanceof SubFlowActivity) {
			WorkflowBaseActivityPropertyDialog nodeDialog = new WorkflowBaseActivityPropertyDialog(
					UIPlugin.getDefault().getWorkbench()
							.getActiveWorkbenchWindow().getShell(), parent,
					child);
			if (Window.OK == nodeDialog.open()) {
				if (this.location != null) {
					this.child.setLocation(this.location);
				}
				this.parent.addChild((WorkflowBaseActivity) this.child);
				MsContext context = editor.getContext();
				context.updateContext(child, "subflow");
			}
		} else {
			if (this.location != null) {
				this.child.setLocation(this.location);
			}
			this.parent.addChild((WorkflowBaseActivity) this.child);
		}
	}

	private Insets getInsets() {
		return new Insets(0, 0, 0, 0);
	}

	public WorkflowDiagram getParent() {
		return parent;
	}

	public void redo() {
		parent.addChild(child, index);
	}

	public void setChild(WorkflowSubPart subpart) {
		child = subpart;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	// public void setRect (Rectangle r)
	// {
	// rect = r;
	// }

	public void setParent(WorkflowDiagram newParent) {
		parent = newParent;
	}

	public void undo() {
		parent.removeChild(child);
	}

	public void setLocation(Point location) {
		// TODO Auto-generated method stub
		this.location = location;
	}

	// public void setLocation(Point location) {
	// TODO Auto-generated method stub
	// //System.out.println("location:"+location.x+"\t"+location.y);
	//
	// this.rect.x = location.x;
	// this.rect.y = location.y;
	//
	// //System.out.println("location:"+location.x+"\t"+location.y);
	//
	// //未指定模型大小---------------
	// }

}
