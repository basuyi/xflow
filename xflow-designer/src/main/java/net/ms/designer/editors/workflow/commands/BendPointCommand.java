package net.ms.designer.editors.workflow.commands;

import net.ms.designer.editors.workflow.models.Wire;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;


public class BendPointCommand extends Command 
{

	protected int index;
	protected Point location;
	protected Wire wire;
	private Dimension d1, d2;
	
	protected Dimension getFirstRelativeDimension() 
	{
		return d1;
	}
	
	protected Dimension getSecondRelativeDimension() 
	{
		return d2;
	}

	protected int getIndex() 
	{
		return index;
	}
	
	protected Point getLocation() 
	{
		return location;
	}
	
	protected Wire getWire() 
	{
		return wire;
	}
	
	public void redo() 
	{
		execute();
	}
	
	public void setRelativeDimensions(Dimension dim1, Dimension dim2) 
	{
		d1 = dim1;
		d2 = dim2;
	}
	
	public void setIndex(int i) 
	{
		index = i;
	}
	
	public void setLocation(Point p) 
	{
		location = p;
	}
	
	public void setWire(Wire w) 
	{
		wire = w;
	}

}
