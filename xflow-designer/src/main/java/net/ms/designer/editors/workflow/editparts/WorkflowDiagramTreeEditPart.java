package net.ms.designer.editors.workflow.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.ms.designer.editors.workflow.models.WorkflowDiagram;

import org.eclipse.gef.editparts.AbstractTreeEditPart;


public class WorkflowDiagramTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener{

	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		refreshChildren();
	}

	public WorkflowDiagramTreeEditPart(Object model) {
        super(model);
     }
	
	public void activate() {
	    super.activate();
	    ((WorkflowDiagram) getModel()).addPropertyChangeListener(this);
	}
	public void deactivate() {
	    super.deactivate();
	    ((WorkflowDiagram) getModel()).removePropertyChangeListener(this);
	}
	protected List getModelChildren() {
	    return ((WorkflowDiagram) getModel()).getChildren();
	}
}
