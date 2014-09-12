/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.ms.designer.editors.component.editpolicies.DiagramLayoutEditPolicy;
import net.ms.designer.editors.component.editpolicies.PropertyEditPolicy;
import net.ms.designer.editors.component.models.CompDiagram;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;



public class DiagramPart extends AbstractGraphicalEditPart implements PropertyChangeListener 
{

    public void performRequest(Request req) 
    {
		super.performRequest(req);
	}

	protected List getModelChildren() 
	{
        return ((CompDiagram) this.getModel()).getNodes();
    }

    public void activate() 
    {
        super.activate();
        ((CompDiagram) getModel()).addPropertyChangeListener(this);
    }

    public void deactivate() 
    {
        super.deactivate();
        ((CompDiagram) getModel()).removePropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) 
    {
        String prop = evt.getPropertyName();
        if (CompDiagram.prop_Node.equals(prop))
            refreshChildren();
    }

    protected IFigure createFigure() 
    {
        Figure figure = new FreeformLayer();
        figure.setLayoutManager(new FreeformLayout());
        return figure;
    }

    protected void createEditPolicies() 
    {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new PropertyEditPolicy());
    }

}