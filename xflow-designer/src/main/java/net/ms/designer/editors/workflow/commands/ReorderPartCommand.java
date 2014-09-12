/**
 * @author liuchunxia
 * 
 * reorder node
 */
package net.ms.designer.editors.workflow.commands;

import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;



public class ReorderPartCommand extends Command {

private int oldIndex, newIndex;
private WorkflowSubPart child;
private WorkflowDiagram parent;

public ReorderPartCommand(WorkflowSubPart child, WorkflowDiagram parent, int oldIndex, int newIndex ) {
	super("ReorderPartCommand_Label"); //$NON-NLS-1$
	this.child = child;
	this.parent = parent;
	this.oldIndex = oldIndex;
	this.newIndex = newIndex;
}

public void execute() {
	parent.removeChild(child);
	parent.addChild(child, newIndex);
}

public void undo() {
	parent.removeChild(child);
	parent.addChild(child, oldIndex);
}

}