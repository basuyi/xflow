package net.ms.designer.editors.componentdetail.commands;

import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.Messages;

import org.eclipse.gef.commands.Command;


public class ReorderPartCommand extends Command
{

	private int oldIndex, newIndex;

	private Element child;

	private Container parent;

	public ReorderPartCommand(Element child, Container parent,
			int oldIndex, int newIndex) 
	{
		super(Messages.getString("ReorderPartCommand.Label")); 
		this.child = child;
		this.parent = parent;
		this.oldIndex = oldIndex;
		this.newIndex = newIndex;
	}

	public void execute() 
	{
		parent.removeChild(child);
		parent.addChild(child, newIndex);
	}

	public void undo() 
	{
		parent.removeChild(child);
		parent.addChild(child, oldIndex);
	}

}
