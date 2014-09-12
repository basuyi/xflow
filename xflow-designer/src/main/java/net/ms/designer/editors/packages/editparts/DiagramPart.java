/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.ms.designer.editors.packages.editpolicies.DiagramLayoutEditPolicy;
import net.ms.designer.editors.packages.editpolicies.PropertyEditPolicy;
import net.ms.designer.editors.packages.models.PackageDiagram;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;



public class DiagramPart extends BaseEditPart implements PropertyChangeListener 
{

    public void performRequest(Request req) 
    {
		super.performRequest(req);
	}

	protected List getModelChildren() 
	{
        return ((PackageDiagram) this.getModel()).getNodes();
    }

    public void activate() 
    {
        super.activate();
        ((PackageDiagram) getModel()).addPropertyChangeListener(this);
    }

    public void deactivate() 
    {
        super.deactivate();
        ((PackageDiagram) getModel()).removePropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) 
    {
        String prop = evt.getPropertyName();
        if (PackageDiagram.prop_Node.equals(prop))
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
    }

}