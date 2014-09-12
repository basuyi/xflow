package net.ms.designer.editors.workflow.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.editors.workflow.figures.NodeFigure;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.editors.workflow.policies.NodeGraphicalNodeEditPolicy;
import net.ms.designer.editors.workflow.policies.WorkflowElementEditPolicy;
import net.ms.designer.editors.workflow.policies.WorkflowPropertyEditPolicy;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;


abstract public class WorkflowEditPart extends AbstractGraphicalEditPart
	implements NodeEditPart, PropertyChangeListener
{
	private AccessibleEditPart acc;

	public void activate(){
		if (isActive())
			return;
		super.activate();
		getWorkflowSubpart().addPropertyChangeListener(this);
	}

	public void createEditPolicies(){
		installEditPolicy(EditPolicy.LAYOUT_ROLE,new WorkflowPropertyEditPolicy());
		refreshStatus();
	}

	abstract protected AccessibleEditPart createAccessible();

	public void deactivate(){
		if (!isActive())
			return;
		super.deactivate();
		getWorkflowSubpart().removePropertyChangeListener(this);
	}

	protected AccessibleEditPart getAccessibleEditPart() {
		if (acc == null)
			acc = createAccessible();
		return acc;
	}

	protected WorkflowSubPart getWorkflowSubpart(){
		return (WorkflowSubPart)getModel();
	}

	protected List getModelInputs(){
		return getWorkflowSubpart().getInputs();
	}

	protected List getModelOutputs(){
		return getWorkflowSubpart().getOutputs();
	}

	protected NodeFigure getNodeFigure(){
		return (NodeFigure) getFigure();
	}

	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connEditPart) {
	    return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
	    return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connEditPart) {
	    return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
	    return new ChopboxAnchor(getFigure());
	}

	public void propertyChange(PropertyChangeEvent evt){
		String prop = evt.getPropertyName();
		if (WorkflowDiagram.PROP_CHILDREN.equals(prop))
			refreshChildren();
		else if (WorkflowSubPart.INPUTS.equals(prop))
			refreshTargetConnections();
		else if (WorkflowSubPart.OUTPUTS.equals(prop))
			refreshSourceConnections();
		else if(WorkflowDiagram.STATUS.equals(prop))
			this.refreshStatus();
		else if (prop.equals(WorkflowSubPart.ID_SIZE) || prop.equals(WorkflowSubPart.ID_LOCATION)
				||prop.equals(WorkflowSubPart.PRO_NAME) )
			refreshVisuals();
	}

	/**
	 * Updates the visual aspect of this. 
	 */
	protected void refreshVisuals() {
//		//System.out.println("base");
//		//System.out.println("Model,109");
		Point loc = getWorkflowSubpart().getLocation();
//		//System.out.println("loc:"+loc);
//		//System.out.println("Model,111");
		Dimension size= getWorkflowSubpart().getSize();
//		//System.out.println("Model,113");
		Rectangle r = new Rectangle(loc ,size);
//		//System.out.println("Model,115");
		((NodeFigure) this.getFigure()).setText(((WorkflowSubPart) this.getModel()).getName());
//		//System.out.println("Model,117");
		((GraphicalEditPart) getParent()).setLayoutConstraint(
			this,
			getFigure(),
			r);
//		//System.out.println("Model,122");
//		//System.out.println("Model,123");
//		//System.out.println("Model,124"+getModel());
	}

	private void refreshStatus(){
		String i = ((WorkflowSubPart)getModel()).getNodeStatus();
		if(((WorkflowSubPart)getModel()).ifEditAble())
		{
			installEditPolicy(EditPolicy.COMPONENT_ROLE, new WorkflowElementEditPolicy());
			installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new WorkflowElementEditPolicy());
			installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
		}
		else
		{
		    removeEditPolicy(EditPolicy.COMPONENT_ROLE);
		    removeEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE);
		}
		IRefreshStatus();
		refreshWireSource(new Long(i));
	}
	abstract protected void IRefreshStatus();
	public void refreshWireSource(Long i1){
		List l = getWorkflowSubpart().getInputs();
		for(Iterator i = l.iterator();i.hasNext();){
			Wire temp = (Wire)i.next();
			temp.setNodeStatus(i1.toString());
		}
	}
	
	public List getMoreChildren()
	{
		return ((WorkflowDiagram)this.getModel()).getChildren();
	}

}
