package net.ms.designer.editors.componentdetail.editparts;

import net.ms.designer.editors.componentdetail.models.Wire;
import net.ms.designer.editors.componentdetail.policies.BendpointCommand;
import net.ms.designer.editors.componentdetail.policies.CreateBendpointCommand;
import net.ms.designer.editors.componentdetail.policies.DeleteBendpointCommand;
import net.ms.designer.editors.componentdetail.policies.MoveBendpointCommand;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;

public class WireBendpointEditPolicy extends org.eclipse.gef.editpolicies.BendpointEditPolicy {

	public void activate() {
		// TODO Auto-generated method stub

	}

	public void deactivate() {
		// TODO Auto-generated method stub

	}

	public void eraseSourceFeedback(Request arg0) {
		// TODO Auto-generated method stub

	}

	public void eraseTargetFeedback(Request arg0) {
		// TODO Auto-generated method stub

	}

	public Command getCommand(Request arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public EditPart getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	public EditPart getTargetEditPart(Request arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setHost(EditPart arg0) {
		// TODO Auto-generated method stub

	}

	public void showSourceFeedback(Request arg0) {
		// TODO Auto-generated method stub

	}

	public void showTargetFeedback(Request arg0) {
		// TODO Auto-generated method stub

	}

	public boolean understandsRequest(Request arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		CreateBendpointCommand com = new CreateBendpointCommand();
		Point p = request.getLocation();
		Connection conn = getConnection();
		
		conn.translateToRelative(p);
		
		com.setLocation(p);
		Point ref1 = getConnection().getSourceAnchor().getReferencePoint();
		Point ref2 = getConnection().getTargetAnchor().getReferencePoint();
		
		conn.translateToRelative(ref1);
		conn.translateToRelative(ref2);
		
		
		com.setRelativeDimensions(p.getDifference(ref1),
						p.getDifference(ref2));
		com.setWire((Wire)request.getSource().getModel());
		com.setIndex(request.getIndex());
		return com;
	}

	protected Command getMoveBendpointCommand(BendpointRequest request) {
		MoveBendpointCommand com = new MoveBendpointCommand();
		Point p = request.getLocation();
		Connection conn = getConnection();
		
		conn.translateToRelative(p);
		
		com.setLocation(p);
		
		Point ref1 = getConnection().getSourceAnchor().getReferencePoint();
		Point ref2 = getConnection().getTargetAnchor().getReferencePoint();
		
		conn.translateToRelative(ref1);
		conn.translateToRelative(ref2);
		
		com.setRelativeDimensions(p.getDifference(ref1),
						p.getDifference(ref2));
		com.setWire((Wire)request.getSource().getModel());
		com.setIndex(request.getIndex());
		return com;
	}

	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		BendpointCommand com = new DeleteBendpointCommand();
		Point p = request.getLocation();
		com.setLocation(p);
		com.setWire((Wire)request.getSource().getModel());
		com.setIndex(request.getIndex());
		return com;
	}
}
