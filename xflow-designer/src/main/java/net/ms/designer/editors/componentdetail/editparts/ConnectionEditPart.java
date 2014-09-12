package net.ms.designer.editors.componentdetail.editparts;


import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

/**
 * @author lili
 * @version 1.1.0
 * @explain defind the ConnectionEditPart,it can order the ConnectionEndpointeditPolicy to do somethings
 */
public class ConnectionEditPart extends AbstractConnectionEditPart {

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
    protected IFigure createFigure() {
        PolylineConnection conn = new PolylineConnection();
        conn.setConnectionRouter(new BendpointConnectionRouter());
        conn.setTargetDecoration(new PolygonDecoration());
        return conn;
    }
    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
    }

}
