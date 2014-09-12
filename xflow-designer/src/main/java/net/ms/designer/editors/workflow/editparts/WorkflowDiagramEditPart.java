package net.ms.designer.editors.workflow.editparts;

import java.beans.PropertyChangeEvent;

import net.ms.designer.editors.workflow.figures.NodeFigure;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.editors.workflow.policies.DiagramLayoutEditPolicy;
import net.ms.designer.editors.workflow.policies.WorkflowPropertyEditPolicy;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;




public class WorkflowDiagramEditPart extends WorkflowContainerEditPart implements LayerConstants
{

	protected IFigure createFigure() 
	{
		Figure figure = new FreeformLayer();
        figure.setLayoutManager(new FreeformLayout());
        return figure;
	}

	public void createEditPolicies()
	{
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE,new WorkflowPropertyEditPolicy());
	}

	public void propertyChange(PropertyChangeEvent event) 
	{
		// TODO Auto-generated method stub
//		String prop = event.getPropertyName();
//		//System.out.println("52");
//        if (WorkflowDiagram.PROP_CHILDREN.equals(prop))
//        {
//        	//System.out.println("refreshChildren()");
//            refreshChildren();
//        }
		super.propertyChange(event);
	}

	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	public void refreshVisuals()
	{
//		//System.out.println("72");
		
//		Point loc = ((WorkflowBaseActivity)getModel()).getLocation();
//		Dimension size= ((WorkflowBaseActivity)getModel()).getSize();
//		Rectangle r = new Rectangle(loc ,size);
//		((NodeFigure) this.getFigure()).setText(((WorkflowSubPart) this.getModel()).getName());
//		((GraphicalEditPart) getParent()).setLayoutConstraint(
//			this,
//			getFigure(),
//			r);
//		super.refreshVisuals();
	}

}
