/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.BendPointCommand;
import net.ms.designer.editors.workflow.commands.CreateBendPointCommand;
import net.ms.designer.editors.workflow.commands.DeleteBendPointCommand;
import net.ms.designer.editors.workflow.commands.MoveBendPointCommand;
import net.ms.designer.editors.workflow.models.Wire;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;


public class WireEndpointEditPolicy
	extends org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy
{
	
	protected void addSelectionHandles(){
		super.addSelectionHandles();
		getConnectionFigure().setLineWidth(2);
	}
	
	protected PolylineConnection getConnectionFigure(){
		return (PolylineConnection)((GraphicalEditPart)getHost()).getFigure();
	}
	
	protected void removeSelectionHandles(){
		super.removeSelectionHandles();
		getConnectionFigure().setLineWidth(0);
	}
	
	protected Command getCreateBendpointCommand(BendpointRequest request) {
	//	//System.out.println("com.example.policies.ConnectionBendPointEditPolicy.getCreateBendpointCommand(BendpointRequest request)");
		CreateBendPointCommand cmd = new CreateBendPointCommand();
		Point p = request.getLocation();
		Connection conn = getConnection();
	
		conn.translateToRelative(p);
	
		Point ref1 = getConnection().getSourceAnchor().getReferencePoint();
		Point ref2 = getConnection().getTargetAnchor().getReferencePoint();
	
		conn.translateToRelative(ref1);
		conn.translateToRelative(ref2);
	
		cmd.setRelativeDimensions(p.getDifference(ref1), p.getDifference(ref2));
		cmd.setWire((Wire) request.getSource()
				.getModel());
		cmd.setIndex(request.getIndex());
		return cmd;
	}
	
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
//		//System.out.println("com.example.policies.ConnectionBendPointEditPolicy.getDeleteBendpointCommand(BendpointRequest request)");
		BendPointCommand cmd = new DeleteBendPointCommand();
		Point p = request.getLocation();
		cmd.setWire((Wire) request.getSource().getModel());
		cmd.setIndex(request.getIndex());
		return cmd;
	}
	
	protected Command getMoveBendpointCommand(BendpointRequest request) {
//		//System.out.println("com.example.policies.ConnectionBendPointEditPolicy.getMoveBendpointCommand(BendpointRequest request)");
		MoveBendPointCommand cmd = new MoveBendPointCommand();
		Point p = request.getLocation();
		Connection conn = getConnection();
	
		conn.translateToRelative(p);
	
		Point ref1 = getConnection().getSourceAnchor().getReferencePoint();
		Point ref2 = getConnection().getTargetAnchor().getReferencePoint();
	
		conn.translateToRelative(ref1);
		conn.translateToRelative(ref2);
	
		cmd.setRelativeDimensions(p.getDifference(ref1), p.getDifference(ref2));
		cmd.setWire((Wire) request.getSource()
				.getModel());
		cmd.setIndex(request.getIndex());
		return cmd;
	}

}