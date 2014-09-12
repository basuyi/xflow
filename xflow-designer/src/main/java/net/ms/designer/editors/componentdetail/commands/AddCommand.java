package net.ms.designer.editors.componentdetail.commands;

import net.ms.designer.editors.componentdetail.IEditorConstant;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;

import org.eclipse.gef.commands.Command;

/**
 * @author lili
 * @version 1.1.0
 *defind the AddCommand,it deal with the child's adding
 */
public class AddCommand extends Command 
{
	private Element child;

	private Container parent;

	private int index = -1;

	/**
	 * construct of the AddCommand()
	 *
	 */
	public AddCommand() 
	{
		
		super(IEditorConstant.ADD); 
	}

	/*
	 *  £¨·Ç Javadoc£©
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() 
	{
		//System.out.println("commands.execute");
		if (index < 0)
		{
			//System.out.println("commands.execute.addChild(child)");
			parent.addChild(child);
		}
		else
		{
			//System.out.println("commands.execute.addChild(child,index)");
			parent.addChild(child, index);
		}
	}

	/**
	 * 
	 * @return return the parent
	 */
	public Container getParent() 
	{
		//System.out.println("commands.AddCommand.getParent()");
		return parent;
	}

	/*
	 *  £¨·Ç Javadoc£©
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() 
	{
		if (index < 0)
		{
			//System.out.println("commands.AddCommand.redo().addChild(child)");
			parent.addChild(child);
		}
		else
		{
			//System.out.println("commands.AddCommand.redo().addChild(child,index)");
			parent.addChild(child, index);
		}
	}

	/**
	 * 
	 * @param subpart
	 *         Element subpart to set
	 */
	public void setChild(Element subpart) 
	{
		//System.out.println("commands.AddComand.setChild(Element subpart)");
		child = subpart;
	}

	/**
	 * 
	 * @param i
	 *         index i to set
	 */
	public void setIndex(int i) 
	{
		//System.out.println("commands.AddComand.setIndex(int i)");
		index = i;
	}

	/**
	 * 
	 * @param newParent
	 *         Container newParent to set
	 */
	public void setParent(Container newParent) 
	{
		//System.out.println("commands.AddComand.setParent(Container newParent)");
		parent = newParent;
	}

	/*
	 *  £¨·Ç Javadoc£©
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() 
	{
		//System.out.println("commands.AddComand.undo");
		parent.removeChild(child);
	}

	
}
