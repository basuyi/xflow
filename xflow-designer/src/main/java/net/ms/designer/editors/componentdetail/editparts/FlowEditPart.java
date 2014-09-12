package net.ms.designer.editors.componentdetail.editparts;

import java.beans.PropertyChangeListener;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.component.editparts.NodeCellEditorLocator;
import net.ms.designer.editors.component.editparts.NodeDirectEditManager;
import net.ms.designer.editors.component.models.WorkFlow;
import net.ms.designer.editors.componentdetail.core.ITableContentProvider;
import net.ms.designer.editors.componentdetail.figures.NodeFigure;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.policies.FlowGraphicalNodeEditPolicy;
import net.ms.designer.editors.componentdetail.ui.CompDetailEditor;
import net.ms.designer.editors.packages.ui.PackageEditor;
import net.ms.designer.editors.workflow.WorkflowImages;
import net.ms.designer.editors.workflow.figures.FigureFactory;
import net.ms.designer.editors.workflow.figures.WorkflowActivityFigure;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.ui.WorkflowEditor;
import net.ms.designer.ui.view.MsTreeView;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.UIPlugin;






public class FlowEditPart extends BaseEditPart  implements PropertyChangeListener,NodeEditPart{

	MsProject project;
    protected DirectEditManager manager;
    IWorkbenchPage page = null;
    Container container;
    private PackageEditor editor;
    
    public void performRequest(Request req) 
    {
        if(req.getType().equals(RequestConstants.REQ_OPEN))
        {

        	editor = (PackageEditor)UIPlugin.getDefault()
						.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().getActiveEditor();
        	MsContext context;
			if(editor.getContext() != null)
				context = editor.getContext();
			else
			{
				context = new MsContext();
				editor.setContext(context);
			}
			context.change(this , "componentdetail");
        }
    }
	protected AccessibleEditPart createAccessible() {

		return null;
	}

	protected IFigure createFigure() {
	
		//return new NodeFigure();
		return new WorkflowActivityFigure("×ÓÁ÷³Ì", WorkflowImages
				.getImage(WorkflowImages.SUBFLOW));
		

	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new FlowGraphicalNodeEditPolicy());

		
	}


	  public void activate() {
	        if (isActive()) {
	            return;
	        }
	        super.activate();
	        ((FlowField) getModel()).addPropertyChangeListener(this);
	    }

 
	    protected void refreshVisuals() {
	    	//System.out.println("com.example.parts.NodePart.refreshVisuals()");
	        FlowField node = (FlowField) getModel();
	        Point loc = node.getLocation();
	        Dimension size = new Dimension(60, 60);
	        Rectangle rectangle = new Rectangle(loc, size);
	        ((WorkflowActivityFigure) this.getFigure()).setName(((FlowField) this.getModel()).getName());
	        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	    }

}
