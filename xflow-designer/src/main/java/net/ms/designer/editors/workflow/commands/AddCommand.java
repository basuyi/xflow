/**
 * @author liuchunxia
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.workflow.commands;

import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;


public class AddCommand extends Command{


	private WorkflowSubPart child;
	private WorkflowDiagram parent;
	private int index = -1;

	public AddCommand() 
	{
		super("AddCommand");
	}

	public void execute()
	{
		if( index < 0 )
			parent.addChild(child);
		else
			parent.addChild(child,index);
	}

	public WorkflowDiagram getParent() 
	{
		return parent;
	}

	public void redo() 
	{
		if( index < 0 )
			parent.addChild(child);
		else
			parent.addChild(child,index);
	}

	public void setChild(WorkflowSubPart subpart)
	{
		child = subpart;
	}

	public void setIndex(int i)
	{
		index = i;
	}

	public void setParent(WorkflowDiagram newParent)
	{
		parent = newParent;
	}

	public void undo()
	{
		parent.removeChild(child);
	}

}
