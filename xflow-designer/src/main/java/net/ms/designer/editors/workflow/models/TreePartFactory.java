/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.models;

import net.ms.designer.editors.workflow.editparts.WorkflowContainerTreeEditPart;
import net.ms.designer.editors.workflow.editparts.WorkflowTreeEditPart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;



public class TreePartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {

		if (model instanceof WorkflowDiagram)
			return new WorkflowContainerTreeEditPart(model);
		return new WorkflowTreeEditPart(model);
	}

}