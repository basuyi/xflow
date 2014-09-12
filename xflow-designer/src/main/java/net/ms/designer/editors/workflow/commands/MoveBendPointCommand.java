package net.ms.designer.editors.workflow.commands;

import net.ms.designer.editors.workflow.models.WireBendPoint;

import org.eclipse.draw2d.Bendpoint;


public class MoveBendPointCommand extends BendPointCommand
{
	private Bendpoint oldBendpoint;

	public boolean canExecute()
	{
	    if(!getWire().ifEditAble())
	        return false;
	    return true;
	}

	public void execute() 
	{
		WireBendPoint bp = new WireBendPoint();
		bp.setRelativeDimensions(getFirstRelativeDimension(), 
						getSecondRelativeDimension());
		setOldBendpoint((Bendpoint)getWire().getBendPointsList().get(getIndex()));
		getWire().setBendPoint(getIndex(), bp);
		super.execute();
	}

	protected Bendpoint getOldBendpoint() 
	{
		return oldBendpoint;
	}

	public void setOldBendpoint(Bendpoint bp) 
	{
		oldBendpoint = bp;
	}

	public void undo()
	{
		super.undo();
		getWire().setBendPoint(getIndex(), getOldBendpoint());
	}

}
