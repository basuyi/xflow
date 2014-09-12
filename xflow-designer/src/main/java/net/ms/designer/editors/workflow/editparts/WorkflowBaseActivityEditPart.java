package net.ms.designer.editors.workflow.editparts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.editors.packages.ui.PackageEditor;
import net.ms.designer.editors.workflow.commands.MoveNodeCommand;
import net.ms.designer.editors.workflow.figures.FigureFactory;
import net.ms.designer.editors.workflow.figures.NodeFigure;
import net.ms.designer.editors.workflow.figures.WorkflowActivityFigure;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.editors.workflow.policies.NodeDirectEditPolicy;
import net.ms.designer.editors.workflow.policies.NodeGraphicalNodeEditPolicy;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.UIPlugin;


public class WorkflowBaseActivityEditPart extends WorkflowEditPart {

	protected DirectEditManager manager;
	
	/**
	 * the construct function
	 *
	 */
	public WorkflowBaseActivityEditPart() {
		super();
	}
	
	 public void performRequest(Request req) {
//		 //System.out.println("((WorkflowBaseActivity) getModel()).ifEditAble():"+((WorkflowBaseActivity) getModel()).ifEditAble());
		 
		 if (((WorkflowBaseActivity) getModel()).ifEditAble()) {
	        if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
	            if (manager == null) {
	            	WorkflowActivityFigure figure = (WorkflowActivityFigure) getFigure();
	                manager = new NodeDirectEditManager(this, TextCellEditor.class, new NodeCellEditLocator(figure));
	            }
	            manager.show();
	        }
	        if(req.getType().equals(RequestConstants.REQ_OPEN) && getModel() instanceof SubFlowActivity)
	        {
	        	MsContext context;
	        	IWorkbenchPage page = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
	        	PackageEditor editor = (PackageEditor)page.getActiveEditor();;
	        	if(editor.getContext() != null)
	        		context = editor.getContext();
	        	else
	        	{
	        		context = new MsContext();
	        		editor.setContext(context);
	        	}
	        	context.change(this , "workflow");
	        }
	    }
	 }

	 /**
	  * create the figure of the node
	  */
		protected IFigure createFigure() {
			return FigureFactory.createWFFigure(((WorkflowBaseActivity) getModel())
					.getName(), ((WorkflowBaseActivity) getModel()).getIcon());
		}


	/**
	 * TODO create the edit policies
	 */
	public void createEditPolicies() {
		// TODO Auto-generated method stub
		if(((WorkflowSubPart)getModel()).ifEditAble())
		{
			installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new NodeDirectEditPolicy());
			installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
		}
		else
		    removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
		
		super.createEditPolicies();
	}

//	/**
//	 * the property change listener
//	 */
//	public void propertyChange(PropertyChangeEvent evt) {
//		String prop = evt.getPropertyName();
//		if (prop.equals(WorkflowBaseActivity.ACTIVITY_SIZE)
//				|| prop.equals(WorkflowBaseActivity.ACTIVITY_LOCATION)
//				|| prop.equals(WorkflowBaseActivity.PRO_NAME))
//			refreshVisuals();
//		
//			super.propertyChange(evt);
//	}
	
	  public void propertyChange(PropertyChangeEvent evt) {
//	    	//System.out.println("com.example.parts.NodePart.propertyChange(PropertyChangeEvent evt)");
	        if (evt.getPropertyName().equals(WorkflowBaseActivity.ACTIVITY_LOCATION))
	            refreshVisuals();
	        else if (evt.getPropertyName().equals(WorkflowBaseActivity.PRO_NAME))       	
	            refreshVisuals();
	        else if (evt.getPropertyName().equals(WorkflowBaseActivity.INPUTS))
	            refreshTargetConnections();
	        else if (evt.getPropertyName().toLowerCase().equals(WorkflowBaseActivity.OUTPUTS))
	        {
	            refreshSourceConnections();
	        }
	        
	        super.propertyChange(evt);
	    }

	/**
	 * refresh the visual aspect of this
	 */
    protected void refreshVisuals() {
    	WorkflowBaseActivity node = (WorkflowBaseActivity) getModel();
        Point loc = node.getLocation();
//        Point loc = new Point(300,300);
        Dimension size = node.getSize();
        Rectangle rectangle = new Rectangle(loc, size);
        ((NodeFigure) this.getFigure()).setName(((WorkflowBaseActivity) this.getModel()).getName());
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
        super.refreshVisuals();
    }


	protected AccessibleEditPart createAccessible() {
		// TODO Auto-generated method stub
		return null;
	}

    protected void IRefreshStatus() {
		if(((WorkflowSubPart)getModel()).ifEditAble())
			installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new NodeDirectEditPolicy());
		else
		    removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
    }

	 public ConnectionAnchor getTargetConnectionAnchor(Request request) {
//	    	//System.out.println("com.example.parts.NodePart.getTargetConnectionAnchor(Request request)");
	        return new ChopboxAnchor(getFigure());
	    }
	 
	 public ConnectionAnchor getSourceConnectionAnchor(Request request) {
//	    	//System.out.println("com.example.parts.NodePart.getSourceConnectionAnchor(Request request)");
	        return new ChopboxAnchor(getFigure());
	    }
	 
	    protected List getModelSourceConnections() {
//	    	//System.out.println("com.example.parts.NodePart.getModelSourceConnection()");
	        return ((WorkflowBaseActivity) this.getModel()).getOutputs();
	    }

	    protected List getModelTargetConnections() {
//	    	//System.out.println("com.example.parts.NodePart.getModelTargetConnections()");
	        return ((WorkflowBaseActivity) this.getModel()).getInputs();
	    }

}
