package net.ms.designer.editors.workflow.editparts;

import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;


public class PartFactory implements EditPartFactory{

	public EditPart createEditPart(EditPart context, Object model) {

		 EditPart part = null;
	        if (model instanceof WorkflowDiagram){
//	        	//System.out.println("PartFactory.model of WorkflowDiagram");
	      
	            part = new WorkflowDiagramEditPart();
	        }
	        if (model instanceof Wire) {
	        	
//	        	//System.out.println("PartFactory.model of wire");
	            part = new WireEditPart();
	        }
	        if (model instanceof WorkflowBaseActivity)
	        {
//	        	//System.out.println("PartFactory.model of WorkflowBaseActivity");
	            part = new WorkflowBaseActivityEditPart();
	        }
	        part.setModel(model);
	        return part;
	}

}
