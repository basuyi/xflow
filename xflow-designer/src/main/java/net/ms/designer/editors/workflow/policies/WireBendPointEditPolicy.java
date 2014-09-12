package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.BendPointCommand;
import net.ms.designer.editors.workflow.commands.CreateBendPointCommand;
import net.ms.designer.editors.workflow.commands.DeleteBendPointCommand;
import net.ms.designer.editors.workflow.commands.MoveBendPointCommand;
import net.ms.designer.editors.workflow.models.Wire;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;


public class WireBendPointEditPolicy extends BendpointEditPolicy
{

	protected Command getCreateBendpointCommand(BendpointRequest request) 
	{
		// TODO Auto-generated method stub
		CreateBendPointCommand com = new CreateBendPointCommand();
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

	protected Command getDeleteBendpointCommand(BendpointRequest request) 
	{
		// TODO Auto-generated method stub
		MoveBendPointCommand com = new MoveBendPointCommand();
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

	protected Command getMoveBendpointCommand(BendpointRequest request) 
	{
		// TODO Auto-generated method stub
		BendPointCommand com = new DeleteBendPointCommand();
		Point p = request.getLocation();
		com.setLocation(p);
		com.setWire((Wire)request.getSource().getModel());
		com.setIndex(request.getIndex());
		return com;
	}

}
