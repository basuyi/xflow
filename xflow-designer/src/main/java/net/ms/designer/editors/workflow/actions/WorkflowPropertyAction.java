/**
 * @author liuchunxia
 * 
 * property action
 */
package net.ms.designer.editors.workflow.actions;

import java.util.List;

import net.ms.designer.editors.workflow.WorkflowPlugin;
import net.ms.designer.editors.workflow.dialog.WorkflowPropertyDialog;
import net.ms.designer.editors.workflow.editparts.WireEditPart;
import net.ms.designer.editors.workflow.editparts.WorkflowBaseActivityEditPart;
import net.ms.designer.editors.workflow.editparts.WorkflowDiagramEditPart;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

//import net.ms.designer.editors.workflow.models.PropertyActivityDialog;

public class WorkflowPropertyAction extends SelectionAction {
	private GraphicalEditorWithFlyoutPalette editor = null;
	public static String WORKFLOW_PROPERTY = "WORKFLOW_ACTION_PROPERTY";

	private static final String PROPERTY_REQUEST = "WORKFLOW_PROPERTY";  //$NON-NLS-1$

	Request request;

	
	public WorkflowPropertyAction(IWorkbenchPart part) 
	{
		super(part);
		request = new Request(PROPERTY_REQUEST);
		setText("Property");
		setId(WORKFLOW_PROPERTY );
		setImageDescriptor(ImageDescriptor.createFromFile(WorkflowPlugin.class,"/icons/sample.gif")); //$NON-NLS-1$
		setHoverImageDescriptor(getImageDescriptor());
	}

    public void run() {
    	execute(getCommand());
//    	if(getSelectedObjects().get(0) instanceof WorkflowDiagramEditPart)
    	try {
    		WorkflowDiagramEditPart part = (WorkflowDiagramEditPart)getSelectedObjects().get(0);
    		WorkflowPropertyDialog p = new WorkflowPropertyDialog(editor.getSite().getShell(),null,null);
    		p.open(); 
    	} catch (ClassCastException e) {
    		Display.getCurrent().beep();
    	} catch (IndexOutOfBoundsException e) {
    		Display.getCurrent().beep();
    	}
    }
    
	//What happened for this action is defined by the edit part of this object.
	private Command getCommand() 
	{
		List editparts = getSelectedObjects();
		CompoundCommand cc = new CompoundCommand();
		cc.setDebugLabel("debug");//$NON-NLS-1$
		for (int i=0; i < editparts.size(); i++) 
		{
			EditPart part = (EditPart)editparts.get(i);
			cc.add(part.getCommand(request));
		}
		return cc;
	}


	protected boolean calculateEnabled() {
		if (getSelectedObjects().size() == 1
				  && ((getSelectedObjects().get(0) instanceof WorkflowDiagramEditPart)))
		{
			return true;
		}
		if(getSelectedObjects().size() == 1
				&& ((getSelectedObjects().get(0)instanceof WireEditPart)))
		{
			return true;
		}
		if(getSelectedObjects().size() == 1
				&& ((getSelectedObjects().get(0) instanceof WorkflowBaseActivityEditPart)))
		{
			return true;
		}
		return false;
	}
}
