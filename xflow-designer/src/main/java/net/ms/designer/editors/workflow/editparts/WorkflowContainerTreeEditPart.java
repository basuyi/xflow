/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.editparts;

import java.util.List;

import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.policies.WorkflowContainerEditPolicy;
import net.ms.designer.editors.workflow.policies.WorkflowTreeContainerEditPolicy;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;


/**
 * Tree EditPart for the Container.
 */
public class WorkflowContainerTreeEditPart
	extends WorkflowTreeEditPart
{

/**
 * Constructor, which initializes this using the
 * model given as input.
 */
public WorkflowContainerTreeEditPart(Object model) {
	super(model);
}
protected void refreshDiagramStatus(){
	if(getLogicDiagram().ifEditAble())
	{
	    installEditPolicy(EditPolicy.CONTAINER_ROLE, new WorkflowContainerEditPolicy());
	    installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE, new WorkflowTreeContainerEditPolicy());
		//If this editpart is the contents of the viewer, then it is not deletable!
		if (getParent() instanceof RootEditPart)
			installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
	}else
	{
	    removeEditPolicy(EditPolicy.CONTAINER_ROLE);
	    removeEditPolicy(EditPolicy.TREE_CONTAINER_ROLE);
		if (getParent() instanceof RootEditPart)
		    removeEditPolicy(EditPolicy.COMPONENT_ROLE);
	}
}

/**
 * Creates and installs pertinent EditPolicies.
 */
protected void createEditPolicies() {
	super.createEditPolicies();
	if(getLogicDiagram().ifEditAble())
	{
	    installEditPolicy(EditPolicy.CONTAINER_ROLE, new WorkflowContainerEditPolicy());
	    installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE, new WorkflowTreeContainerEditPolicy());
		//If this editpart is the contents of the viewer, then it is not deletable!
		if (getParent() instanceof RootEditPart)
			installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
	}else
	{
	    installEditPolicy(EditPolicy.CONTAINER_ROLE, null);
	    installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE,null);
		if (getParent() instanceof RootEditPart)
			installEditPolicy(EditPolicy.COMPONENT_ROLE, null);
	}
}

protected WorkflowDiagram getLogicDiagram() {
	return (WorkflowDiagram)getModel();
}

protected List getModelChildren() {
	return getLogicDiagram().getChildren();
}

}