package net.ms.designer.editors.enumcomponentdetail.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.ms.designer.editors.enumcomponentdetail.editpolicy.PropertyEditPolicy;
import net.ms.designer.editors.enumcomponentdetail.model.Element;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;


public class BaseEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
    public void activate() 
    {
        if(getModel() != null && getModel() instanceof Element)
        {
            ((Element)getModel()).addPropertyChangeListener(this);
        }
        super.activate();
    }

    public void deactivate() 
    {
        if(getModel() != null && getModel() instanceof Element)
        {
            ((Element)getModel()).removePropertyChangeListener(this);
        }
        super.deactivate();
    }

    protected IFigure createFigure() 
    {
        return null;
    }

    protected void createEditPolicies() 
    {
    	this.installEditPolicy(EditPolicy.LAYOUT_ROLE,new PropertyEditPolicy());
    }

    protected List getModelChildren() 
    {
        if(getModel() instanceof Element)
        {
            return ((Element)getModel()).getChildren();
        }
        return super.getModelChildren();
    }

    public void propertyChange(PropertyChangeEvent evt) 
    {
       String pName = evt.getPropertyName();
       if(pName.equals(Element.PRO_FIGURE))
       {
           this.refreshVisuals();
       }
       if(pName.equals(Element.PRO_CHILD))
       {
           this.refreshChildren();
           this.refreshVisuals();
       }
    }
}
