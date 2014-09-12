package net.ms.designer.editors.componentdetail.commands;

import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.Messages;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;


public class SetConstraintCommand extends Command
{

	private Point newPos;

	private Dimension newSize;

	private Point oldPos;

	private Dimension oldSize;

	private Element part;

	public void execute() 
	{
		oldSize = part.getSize();
		oldPos = part.getLocation();
		redo();
	}

	public String getLabel() 
	{
		if (oldSize.equals(newSize))
			return Messages.getString("SetConstraintCommand.Label.Location"); 
		return Messages.getString("SetConstraintCommand.Label.Size"); 
	}

	public void redo() 
	{
		part.setSize(newSize);
		part.setLocation(newPos);
	}

	public void setLocation(Rectangle r) 
	{
		setLocation(r.getLocation());
		setSize(r.getSize());
	}

	public void setLocation(Point p) 
	{
		newPos = p;
	}

	public void setPart(Element part) 
	{
		this.part = part;
	}

	public void setSize(Dimension p) 
	{
		newSize = p;
	}

	public void undo() 
	{
		part.setSize(oldSize);
		part.setLocation(oldPos);
	}


}
