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

import net.ms.designer.editors.component.models.CompDiagram;

import org.eclipse.gef.editparts.AbstractTreeEditPart;


public class DiagramTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener
{
    public DiagramTreeEditPart(Object model) 
    {
        super(model);
     }

    public void propertyChange(PropertyChangeEvent evt) 
    {
        refreshChildren();
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
    protected List getModelChildren() 
    {
        return ((CompDiagram) getModel()).getNodes();
    }
}
