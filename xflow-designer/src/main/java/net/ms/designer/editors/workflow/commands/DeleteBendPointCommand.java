package net.ms.designer.editors.workflow.commands;

import net.ms.designer.editors.workflow.models.WireBendPoint;

import org.eclipse.draw2d.Bendpoint;



public class DeleteBendPointCommand extends BendPointCommand 
{
	private Bendpoint bendpoint;	
	public void execute() 
	{
		bendpoint = (Bendpoint)getWire().getBendPointsList().get(getIndex());
		getWire().removeBendPoint(getIndex());
		super.execute();
	}
	
	public void undo() 
	{
		super.undo();
		getWire().addBendPoint(getIndex(), (WireBendPoint)bendpoint);
	}

}