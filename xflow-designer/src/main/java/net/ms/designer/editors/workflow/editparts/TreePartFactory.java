package net.ms.designer.editors.workflow.editparts;

import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;



public class TreePartFactory implements EditPartFactory{

    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof WorkflowDiagram) {
            return new WorkflowDiagramTreeEditPart(model);
         }
         else if (model instanceof WorkflowBaseActivity) {
            return new NodeTreeEditPart(model);
         }
         else {
            return null;
         }
    }
}