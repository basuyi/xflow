package net.ms.designer.editors.componentdetail.policies;

import net.ms.designer.editors.componentdetail.models.WireBendPoint;


public class CreateBendpointCommand extends BendpointCommand {

	public void execute() {
		WireBendPoint wbp = new WireBendPoint();
		wbp.setRelativeDimensions(getFirstRelativeDimension(),
				getSecondRelativeDimension());
		getWire().insertBendpoint(getIndex(), wbp);
		super.execute();
	}

	public void undo() {
		super.undo();
		getWire().removeBendpoint(getIndex());
	}

}

