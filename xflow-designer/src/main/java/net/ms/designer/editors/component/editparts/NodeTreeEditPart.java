/*
 * Created on 2005-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.ms.designer.editors.component.models.Component;

import org.eclipse.gef.editparts.AbstractTreeEditPart;


public class NodeTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener 
{

    public NodeTreeEditPart(Object model) 
    {
        super(model);
    }

    public void propertyChange(PropertyChangeEvent evt) 
    {
        refreshVisuals();
    }

    public void activate() 
    {
        super.activate();
        ((Component) getModel()).addPropertyChangeListener(this);
    }

    public void deactivate() 
    {
        super.deactivate();
        ((Component) getModel()).removePropertyChangeListener(this);
    }

    protected void refreshVisuals() 
    {
        setWidgetText(((Component) getModel()).getName());
    }

}