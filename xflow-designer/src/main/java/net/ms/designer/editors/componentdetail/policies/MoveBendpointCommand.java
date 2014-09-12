package net.ms.designer.editors.componentdetail.policies;

import net.ms.designer.editors.componentdetail.models.WireBendPoint;

import org.eclipse.draw2d.Bendpoint;


public class MoveBendpointCommand extends BendpointCommand {

	private Bendpoint oldBendpoint;

	public void execute() {
		WireBendPoint bp = new WireBendPoint();
		bp.setRelativeDimensions(getFirstRelativeDimension(),
				getSecondRelativeDimension());
		setOldBendpoint((Bendpoint) getWire().getBendpoints().get(getIndex()));
		getWire().setBendpoint(getIndex(), bp);
		super.execute();
	}

	protected Bendpoint getOldBendpoint() {
		return oldBendpoint;
	}

	public void setOldBendpoint(Bendpoint bp) {
		oldBendpoint = bp;
	}

	public void undo() {
		super.undo();
		getWire().setBendpoint(getIndex(), getOldBendpoint());
	}

}

