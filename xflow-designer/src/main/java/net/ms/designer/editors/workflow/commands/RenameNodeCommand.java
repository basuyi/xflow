package net.ms.designer.editors.workflow.commands;

import java.util.Iterator;
import java.util.List;

import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;


public class RenameNodeCommand extends Command 
{

    private WorkflowSubPart node;

    private String newName;

    private String oldName;

	public boolean canExecute() 
	{
		List lChild = node.getParent().getChildren();
		for (Iterator it = lChild.iterator(); it.hasNext();) 
		{
			String childName = ((WorkflowSubPart) it.next()).getName();
			if (childName.equalsIgnoreCase(newName)) 
			{
				return false;
			}
		}
		return true;
	}
    public void setName(String name) 
    {
        this.newName = name;
    }

    public void setNode(WorkflowSubPart node) 
    {
        this.node = node;
    }

    public void execute() 
    {
        oldName = this.node.getName();
        this.node.setName(newName);
    }

    public void redo() 
    {
        node.setName(newName);
    }

    public void undo() 
    {
        node.setName(oldName);
    }

    public String getLabel() 
    {
        return "Rename Node";
    }
}