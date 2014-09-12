package net.ms.designer.editors.componentdetail.editparts;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.figures.ShortestPathConnectionRouter;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.policies.LogicContainerEditPolicy;
import net.ms.designer.editors.componentdetail.policies.LogicXYLayoutEditPolicy;

import org.eclipse.draw2d.AutomaticRouter;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FanRouter;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.tools.DeselectAllTracker;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.swt.accessibility.AccessibleEvent;


/**
 * @author lili
 * @version 1.1.0
 * defind the DiagramEditPart,it extends containerEditPart
 * it include connetion's idea and adapter idea.
 */
public class DiagramEditPart extends ContainerEditPart implements
LayerConstants {

	private FreeformLayout layout = new FreeformLayout();

	/*
	 *  (non-Javadoc)
	 * @see net.ms.designer.editors.componentdetail.editparts.BaseEditPart#createAccessible()
	 */
	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getName(AccessibleEvent e) {
				e.result = getDiagram().getName();
			}
		};
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LogicXYLayoutEditPolicy(
				(XYLayout) getContentPane().getLayoutManager()));

		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); 
		
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new LogicContainerEditPolicy());
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		Figure f = new FreeformLayer();
		//	f.setBorder(new GroupBoxBorder("Diagram"));
		f.setLayoutManager(layout);
		f.setBorder(new MarginBorder(5));
		return f;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		////System.out.println(adapter);
		if (adapter == SnapToHelper.class) {
			List snapStrategies = new ArrayList();
			Boolean val = (Boolean) getViewer().getProperty(
					RulerProvider.PROPERTY_RULER_VISIBILITY);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGuides(this));
			val = (Boolean) getViewer().getProperty(
					SnapToGeometry.PROPERTY_SNAP_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGeometry(this));
			val = (Boolean) getViewer().getProperty(
					SnapToGrid.PROPERTY_GRID_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGrid(this));

			if (snapStrategies.size() == 0)
				return null;
			if (snapStrategies.size() == 1)
				return (SnapToHelper) snapStrategies.get(0);

			SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
			for (int i = 0; i < snapStrategies.size(); i++)
				ss[i] = (SnapToHelper) snapStrategies.get(i);
			return new CompoundSnapToHelper(ss);
		}
		return super.getAdapter(adapter);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#getDragTracker(org.eclipse.gef.Request)
	 */
	public DragTracker getDragTracker(Request req) {
		if (req instanceof SelectionRequest
				&& ((SelectionRequest) req).getLastButtonPressed() == 3)
			return new DeselectAllTracker(this);
		return new MarqueeDragTracker();
	}

	/**
	 * Returns <code>NULL</code> as it does not hold any connections.
	 * 
	 * @return ConnectionAnchor
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart editPart) {
		return null;
	}

	/**
	 * Returns <code>NULL</code> as it does not hold any connections.
	 * 
	 * @return ConnectionAnchor
	 */
	public ConnectionAnchor getSourceConnectionAnchor(int x, int y) {
		return null;
	}

	/**
	 * Returns <code>NULL</code> as it does not hold any connections.
	 * 
	 * @return ConnectionAnchor
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart editPart) {
		return null;
	}

	/**
	 * Returns <code>NULL</code> as it does not hold any connections.
	 * 
	 * @return ConnectionAnchor
	 */
	public ConnectionAnchor getTargetConnectionAnchor(int x, int y) {
		return null;
	}

	/*
	 *  (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (Container.ID_ROUTER.equals(evt.getPropertyName()))
			refreshVisuals();
		
		else
			super.propertyChange(evt);
	}

	/**
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 * do the connection
	 */
	protected void refreshVisuals() {
		ConnectionLayer cLayer = (ConnectionLayer) getLayer(CONNECTION_LAYER);
		getFigure().setLayoutManager(layout);
		if (getDiagram().getConnectionRouter().equals(
				Container.ROUTER_MANUAL)) {
			AutomaticRouter router = new FanRouter();
			router.setNextRouter(new BendpointConnectionRouter());
			cLayer.setConnectionRouter(router);
		} else if (getDiagram().getConnectionRouter().equals(
				Container.ROUTER_MANHATTAN))
			cLayer.setConnectionRouter(new ManhattanConnectionRouter());
		else {
			ShortestPathConnectionRouter router = new ShortestPathConnectionRouter(
					getFigure());
			cLayer.setConnectionRouter(router);
			getFigure().setLayoutManager(
					new ShortestPathConnectionRouter.LayoutWrapper(layout,
							router));
		}
	}
	
	/**
	 * Returns the model of this as a LogicDiagram.
	 * 
	 * @return LogicDiagram of this.
	 */
	public Container getDiagram() {
		return (Container) getModel();
	}

	/**
	 * Returns the children of this through the model.
	 * 
	 * @return Children of this as a List.
	 */
	protected List getModelChildren() {
		return getDiagram().getChildren();
	}

}
