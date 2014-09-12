/**
 * @author liuchunxia
 * 
 * edit wire
 */
package net.ms.designer.editors.workflow.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.editors.workflow.figures.FigureFactory;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WireBendPoint;
import net.ms.designer.editors.workflow.policies.WireBendPointEditPolicy;
import net.ms.designer.editors.workflow.policies.WireEditPolicy;
import net.ms.designer.editors.workflow.policies.WireEndpointEditPolicy;
import net.ms.designer.editors.workflow.policies.WirePropertyEditPolicy;
import net.ms.designer.editors.workflow.policies.WorkflowPropertyEditPolicy;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;



public class WireEditPart extends AbstractConnectionEditPart implements PropertyChangeListener, NodeEditPart{

	AccessibleEditPart acc;
	
	public static final Color alive = new Color(Display.getDefault(), 0, 74,
			168), dead = new Color(Display.getDefault(), 0, 0, 0);

	public void activate() {
//		//System.out.println("WireEditAPart.activate");
		super.activate();
		((Wire) getModel()).addPropertyChangeListener(this);
	}
	
	public void deactivate() {
		super.deactivate();
		((Wire) getModel()).removePropertyChangeListener(this);
	}
	
	public void activateFigure() {
		super.activateFigure();
		getFigure().addPropertyChangeListener(
				Connection.PROPERTY_CONNECTION_ROUTER, this);
	}
	
//	public void propertyChange(PropertyChangeEvent event) {
//		// TODO Auto-generated method stub
//		String property = event.getPropertyName();
//		if (Wire.PROP_BENDPOINT.toLowerCase().equals(property.toLowerCase())) {
//			refreshBendpoints();
//		}
//	}
	
	/**
	 * Listens to changes in properties of the Wire (like the contents being
	 * carried), and reflects is in the visuals.
	 * 
	 * @param event
	 *            Event notifying the change.
	 */
	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getPropertyName();
		if (Connection.PROPERTY_CONNECTION_ROUTER.equals(property)) {
			refreshBendpoints();
			refreshBendpointEditPolicy();
		}else if(Wire.ID_DESCRIPTION.equals(property))
			refreshDescription();
		if ("value".equals(property)) //$NON-NLS-1$
			refreshVisuals();
		if ("bendpoint".equals(property)) //$NON-NLS-1$
			refreshBendpoints();
	}
	
	public void refreshDescription(){
		List l = getFigure().getChildren();
		for(Iterator i = l.iterator();i.hasNext();){
			IFigure f = (IFigure)i.next();
			if(f instanceof Label){
			    Label label = (Label)f;
			    String strTemp = ((Wire)getModel()).getDescription();
			    if(strTemp.trim().equals("")){
			        label.setOpaque(false);
			    }else{
			        label.setOpaque(true);
			    }
			    label.setText(strTemp);
			}
		}
	}
	
	protected void refreshBendpoints() {
		Wire conn = (Wire) getModel();
		List modelConstraint = conn.getBendPointsList();
		List figureConstraint = new ArrayList();
		for (int i = 0; i < modelConstraint.size(); i++) {
			WireBendPoint cbp = (WireBendPoint) modelConstraint
					.get(i);
			RelativeBendpoint rbp = new RelativeBendpoint(getConnectionFigure());
			rbp.setRelativeDimensions(cbp.getFirstRelativeDimension(), cbp
					.getSecondRelativeDimension());
			rbp.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
			figureConstraint.add(rbp);
		}
		getConnectionFigure().setRoutingConstraint(figureConstraint);
	}
	
	private void refreshBendpointEditPolicy() {
//		if (getConnectionFigure().getConnectionRouter() instanceof ManhattanConnectionRouter
//				||
//				!((Wire)getModel()).ifEditAble())
//			installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, null);
//		else
			installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
					new WireBendPointEditPolicy());
	}
	
	protected void refreshVisuals() {
		refreshBendpoints();
	}
	
	/**
	 * Adds extra EditPolicies as required.
	 */
	protected void createEditPolicies() {		
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new WireEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new WireEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
				new WireBendPointEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE,new WirePropertyEditPolicy());
	}
	
	public void setSelected(int value) {
		super.setSelected(value);
		if (value != EditPart.SELECTED_NONE)
			((PolylineConnection) getFigure()).setLineWidth(2);
		else
			((PolylineConnection) getFigure()).setLineWidth(1);
	}
	
	/**
	 * Returns a newly created Figure to represent the connection.
	 * 
	 * @return The created Figure.
	 */
	protected IFigure createFigure() {
		if ((Wire)getModel() == null)
			return null;
		Connection connx = FigureFactory.createNewBendableWire((Wire) getModel());
//		//System.out.println("wireEditPart.createFigure");
		return connx;
	}
	
	/**
	 * Returns the Figure associated with this, which draws the Wire.
	 * 
	 * @return Figure of this.
	 */
	protected IFigure getWireFigure() {
		return (PolylineConnection) getFigure();
	}
	
	public AccessibleEditPart getAccessibleEditPart() {
		if (acc == null)
			acc = new AccessibleGraphicalEditPart() {
				public void getName(AccessibleEvent e) {
					e.result = "LogicMessages.Wire_LabelText";
				}
			};
//			//System.out.println("wireEditPart.getAccessibleEditPart");
		return acc;
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


}
