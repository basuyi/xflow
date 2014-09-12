/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;

import net.ms.designer.editors.workflow.WorkflowImages;
import net.ms.designer.editors.workflow.models.EndNode;
import net.ms.designer.editors.workflow.models.RouteOnlyActivity;
import net.ms.designer.editors.workflow.models.StartNode;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.SystemAppActivity;
import net.ms.designer.editors.workflow.models.UserAppActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.editors.workflow.policies.WorkflowElementEditPolicy;
import net.ms.designer.editors.workflow.policies.WorkflowTreeEditPolicy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


public class WorkflowTreeEditPart
	extends org.eclipse.gef.editparts.AbstractTreeEditPart
	implements PropertyChangeListener
{

public WorkflowTreeEditPart(Object model) {
	super (model);
}

public void activate(){
	super.activate();
	getLogicSubpart().addPropertyChangeListener(this);
}

protected void createEditPolicies() {
	EditPolicy component;
	component = new WorkflowElementEditPolicy();
	installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new WorkflowTreeEditPolicy());
	installEditPolicy(EditPolicy.COMPONENT_ROLE, component);
}

public void deactivate(){
	getLogicSubpart().removePropertyChangeListener(this);
	super.deactivate();
}

protected WorkflowSubPart getLogicSubpart() {
	return (WorkflowSubPart)getModel();
}

protected List getModelChildren() {
	return Collections.EMPTY_LIST;
}

public void propertyChange(PropertyChangeEvent change){
	if (change.getPropertyName().equals(WorkflowDiagram.PROP_CHILDREN)) {
		if (change.getOldValue() instanceof Integer)
			// new child
			addChild(createChild(change.getNewValue()), ((Integer)change.getOldValue()).intValue());	
		else
			// remove child
			removeChild((EditPart)getViewer().getEditPartRegistry().get(change.getOldValue()));
	} else if(change.getPropertyName().equals(WorkflowDiagram.STATUS)){
	    refreshStatus();
	}else
		refreshVisuals();
}
protected void refreshStatus(){
	EditPolicy component;
	component = new WorkflowElementEditPolicy();
	if(getLogicSubpart().ifEditAble()){
	    installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new WorkflowTreeEditPolicy());
	    installEditPolicy(EditPolicy.COMPONENT_ROLE, component);
	}else{
	    removeEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
	    removeEditPolicy(EditPolicy.COMPONENT_ROLE);
	}
	refreshDiagramStatus();
}

protected void refreshDiagramStatus(){
    
}
protected void refreshVisuals(){
	if (getWidget() instanceof Tree)
		return;
	Image image = getLogicSubpart().getIconImage();
	
	if(getLogicSubpart() instanceof RouteOnlyActivity)
		image = WorkflowImages.getImage(WorkflowImages.ROUTE_16);
	else if(getLogicSubpart() instanceof SystemAppActivity)
		image = WorkflowImages.getImage(WorkflowImages.JAVAAPPLICATION_16);
	else if(getLogicSubpart() instanceof UserAppActivity)
		image = WorkflowImages.getImage(WorkflowImages.WEBBAPPLICATION_16);
	else if(getLogicSubpart() instanceof SubFlowActivity)
		image = WorkflowImages.getImage(WorkflowImages.SUBFLOW_16);
	else if(getLogicSubpart() instanceof StartNode)
		image = WorkflowImages.getImage(WorkflowImages.STARTNODE_16);
	else if(getLogicSubpart() instanceof EndNode)
		image = WorkflowImages.getImage(WorkflowImages.ENDNODE_16);
	
	TreeItem item = (TreeItem)getWidget();
	if (image != null)
		image.setBackground(item.getParent().getBackground());
	setWidgetImage(image);
	setWidgetText(getLogicSubpart().toString());
}

}