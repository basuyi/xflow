package net.ms.designer.editors.workflow.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.editparts.AbstractTreeEditPart;



public class NodeTreeEditPart  extends AbstractTreeEditPart implements PropertyChangeListener{

	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		refreshVisuals();
	}

	public NodeTreeEditPart(Object model) {
        super(model);
    }
	
	public void activate() {
	    super.activate();
	    ((WorkflowSubPart) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
	    super.deactivate();
	    ((WorkflowSubPart) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
	    setWidgetText(((WorkflowSubPart) getModel()).getName());
	}
}
