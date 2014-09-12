package net.ms.designer.editors.workflow.dnd;

import net.ms.designer.editors.workflow.tools.WorkflowElementFactory;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;



public class WorkflowTemplateTransferDropTargetListener  extends TemplateTransferDropTargetListener
{

	public WorkflowTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
		// TODO Auto-generated constructor stub
	}

	protected CreationFactory getFactory(Object template) {
		// TODO Auto-generated method stub
		return new WorkflowElementFactory(template);
	}

}
