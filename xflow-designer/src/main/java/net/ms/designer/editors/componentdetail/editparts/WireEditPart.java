package net.ms.designer.editors.componentdetail.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.figures.FigureFactory;
import net.ms.designer.editors.componentdetail.models.Messages;
import net.ms.designer.editors.componentdetail.models.Wire;
import net.ms.designer.editors.componentdetail.models.WireBendPoint;
import net.ms.designer.editors.componentdetail.policies.WireEditPolicy;
import net.ms.designer.editors.workflow.policies.WireEndpointEditPolicy;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;



/**
 * @author lili
 * @version 1.1.0
 *defind the WireEditPart,it can deal with the wire connection
 */
public class WireEditPart extends AbstractConnectionEditPart implements
PropertyChangeListener,NodeEditPart {

	AccessibleEditPart acc;

	public static final Color alive = new Color(Display.getDefault(), 0, 74,
			168), dead = new Color(Display.getDefault(), 0, 0, 0); //defind the color

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		super.activate();
		getWire().addPropertyChangeListener(this);
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#activateFigure()
	 */
	public void activateFigure() {
		super.activateFigure();
		getFigure().addPropertyChangeListener(
				Connection.PROPERTY_CONNECTION_ROUTER, this);
	}

	/**
	 * Adds extra EditPolicies as required.
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new WireEndpointEditPolicy());
//				installEditPolicy(EditPolicy.COMPONENT_ROLE, new ConnectionEditPolicy());

		refreshBendpointEditPolicy();
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new WireEditPolicy());
	}
	private void refreshBendpointEditPolicy() {
		if (getConnectionFigure().getConnectionRouter() instanceof ManhattanConnectionRouter)
			installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, null);
		else
			installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
					new WireBendpointEditPolicy());
	}
	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		if (getWire() == null)
			return null;
		PolylineConnection connx = FigureFactory
				.createNewBendableWire(getWire());
		connx.setTargetDecoration(new PolygonDecoration());
		return connx;
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate() {
		getWire().removePropertyChangeListener(this);
		super.deactivate();
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#deactivateFigure()
	 */
	public void deactivateFigure() {
		getFigure().removePropertyChangeListener(
				Connection.PROPERTY_CONNECTION_ROUTER, this);
		super.deactivateFigure();
	}

	public AccessibleEditPart getAccessibleEditPart() {
		if (acc == null)
			acc = new AccessibleGraphicalEditPart() {
				public void getName(AccessibleEvent e) {
					e.result = null;
				}
			};
		return acc;
	}

	/**
	 * @return return the model of wire
	 */
	protected Wire getWire() {
		return (Wire) getModel();
	}

	/**
	 * @return return the figure of wire
	 */
	protected IFigure getWireFigure() {
		return (PolylineConnection) getFigure();
	}

	/*
	 *  （非 Javadoc）
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getPropertyName();
		if (Connection.PROPERTY_CONNECTION_ROUTER.equals(property)) 
//			refreshBendpoints();
//			refreshBendpointEditPolicy();
//		}
//		if ("value".equals(property)) //$NON-NLS-1$
			refreshVisuals();
	}


	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		refreshBendpoints();
		if (getWire().getValue())
			getWireFigure().setForegroundColor(alive);
		else
			getWireFigure().setForegroundColor(dead);
	}
	protected void refreshBendpoints() {
		if (getConnectionFigure().getConnectionRouter() instanceof ManhattanConnectionRouter)
			return;
		List modelConstraint = getWire().getBendpoints();
		List figureConstraint = new ArrayList();
		for (int i = 0; i < modelConstraint.size(); i++) {
			WireBendPoint wbp = (WireBendPoint) modelConstraint.get(i);
			RelativeBendpoint rbp = new RelativeBendpoint(getConnectionFigure());
			rbp.setRelativeDimensions(wbp.getFirstRelativeDimension(), wbp
					.getSecondRelativeDimension());
			rbp.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
			figureConstraint.add(rbp);
		}
		getConnectionFigure().setRoutingConstraint(figureConstraint);
	}

	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
//    	//System.out.println("com.example.parts.NodePart.getSourceConnectionAnchor(ConnectionEditPart connection)");
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
//    	//System.out.println("com.example.parts.NodePart.getSourceConnectionAnchor(Request request)");
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
//    	//System.out.println("com.example.parts.NodePart.getTargetConnectionAnchor(ConnectionEditPart connection)");
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
//    	//System.out.println("com.example.parts.NodePart.getTargetConnectionAnchor(Request request)");
        return new ChopboxAnchor(getFigure());
    }
    public void setSelected(int value) {
		super.setSelected(value);
		if (value != EditPart.SELECTED_NONE)
			((PolylineConnection) getFigure()).setLineWidth(2);
		else
			((PolylineConnection) getFigure()).setLineWidth(1);
	}
}
