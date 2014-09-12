
package net.ms.designer.editors.componentdetail.policies;

import net.ms.designer.editors.componentdetail.commands.ConnectionCommand;
import net.ms.designer.editors.componentdetail.editparts.WireEditPart;
import net.ms.designer.editors.componentdetail.models.Wire;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;


/**
 * @author lili
 * @version 1.1.0
 *defind the WireEditPolicy, it do the delectCommand request and get the connectionfigure of wire
 */
public class WireEditPolicy extends
		org.eclipse.gef.editpolicies.ConnectionEditPolicy {

	/*
	 *  £¨·Ç Javadoc£©
	 * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getDeleteCommand(GroupRequest request) {
		ConnectionCommand c = new ConnectionCommand();
		c.setWire((Wire) getHost().getModel());
		return c;
	}
	
	/**
	 * 
	 * @return the figure of wire
	 */
	private PolylineConnection getConnectionFigure() {
		return ((PolylineConnection)((WireEditPart)getHost()).getFigure());
	}

}