/**
 * @author liuchunxia
 * 
 * deal with orphan child
 * 处理孤立的结点
 */
package net.ms.designer.editors.workflow.commands;

import java.util.List;

import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;



public class OrphanChildCommand
	extends Command
{

private Point oldLocation;
private WorkflowDiagram diagram;
private WorkflowSubPart child;
private int index;

public OrphanChildCommand () {
	super("OrphanChildCommand "); //$NON-NLS-1$
}

public void execute() {
	List children = diagram.getChildren();
	index = children.indexOf(child);
	oldLocation = child.getLocation();
	diagram.removeChild(child);
}

public void redo() {
	diagram.removeChild(child);
}

public void setChild(WorkflowSubPart child) {
	this.child = child;
}

public void setParent(WorkflowDiagram parent) { 
	diagram = parent;
}

public void undo() {
	child.setLocation(oldLocation);
	diagram.addChild(child, index);
}

}
