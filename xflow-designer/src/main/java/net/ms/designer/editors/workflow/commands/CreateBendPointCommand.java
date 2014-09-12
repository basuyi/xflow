package net.ms.designer.editors.workflow.commands;

import net.ms.designer.editors.workflow.models.WireBendPoint;

public class CreateBendPointCommand extends BendPointCommand 
{

	public void execute() 
	{
		WireBendPoint wbp = new WireBendPoint();
		wbp.setRelativeDimensions(getFirstRelativeDimension(), 
					getSecondRelativeDimension());
		getWire().addBendPoint(getIndex(), wbp);
		super.execute();
	}
	
	public boolean canExecute()
	{
		if(!getWire().ifEditAble())
			return false;
		return true;
	}
	
	public void undo() 
	{
		super.undo();
		getWire().removeBendPoint(getIndex());
	}

}