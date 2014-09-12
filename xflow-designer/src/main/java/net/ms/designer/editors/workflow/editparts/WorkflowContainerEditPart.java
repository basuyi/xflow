package net.ms.designer.editors.workflow.editparts;

import java.util.List;

import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.editors.workflow.policies.WorkflowElementEditPolicy;

import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.accessibility.AccessibleEvent;


abstract public class WorkflowContainerEditPart extends WorkflowEditPart
{
	protected AccessibleEditPart createAccessible() {
	return new AccessibleGraphicalEditPart(){
		public void getName(AccessibleEvent e) {
			e.result = getWorkflowDiagram().toString();
		}
	};
	}
	
	/**
	* Installs the desired EditPolicies for this.
	*/
	public void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new WorkflowElementEditPolicy());
	}
	protected void IRefreshStatus() {
		if(((WorkflowSubPart)getModel()).ifEditAble())
			installEditPolicy(EditPolicy.CONTAINER_ROLE, new WorkflowElementEditPolicy());
		else
			removeEditPolicy(EditPolicy.CONTAINER_ROLE);
	}
	/**
	* Returns the model of this as a LogicDiagram.
	*
	* @return  LogicDiagram of this.
	*/
	protected WorkflowDiagram getWorkflowDiagram() {
		return (WorkflowDiagram)getModel();
	}
	
	/**
	* Returns the children of this through the model.
	*
	* @return  Children of this as a List.
	*/
	protected List getModelChildren() {
		return getWorkflowDiagram().getChildren();
	}

}
