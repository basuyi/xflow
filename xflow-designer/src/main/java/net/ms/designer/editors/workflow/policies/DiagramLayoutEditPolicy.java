package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.CreateNodeCommand;
import net.ms.designer.editors.workflow.commands.MoveNodeCommand;
import net.ms.designer.editors.workflow.commands.WireCommand;
import net.ms.designer.editors.workflow.editparts.WorkflowDiagramEditPart;
import net.ms.designer.editors.workflow.editparts.WorkflowEditPart;
import net.ms.designer.editors.workflow.figures.WorkflowActivityFigure;
import net.ms.designer.editors.workflow.figures.WorkflowColorConstants;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;





public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy
{

	protected Command createAddCommand(EditPart child, Object constraint)
	{
		// TODO Auto-generated method stub
//		//System.out.println("DiagramLayoutEditPolicy.createAddCommand");
		return null;
	}

	protected Command createChangeConstraintCommand(EditPart child, Object constraint) 
	{
//		//System.out.println("DiagramLayoutEditPolicy.createChangeConstraintCommand(");
		// TODO Auto-generated method stub
		
		
		if ((child instanceof WorkflowDiagramEditPart))
		{
//			//System.out.println("is workflowDiagramEditPart");
            return null;
		}
        if (!(constraint instanceof Rectangle))
        {
//        	//System.out.println("is not rectangle");
            return null;
        }

        MoveNodeCommand cmd = new MoveNodeCommand();
        cmd.setNode((WorkflowBaseActivity) child.getModel());
        cmd.setLocation(((Rectangle) constraint).getLocation());
        return cmd;
	}

	protected Command getCreateCommand(CreateRequest request) 
	{
		// TODO Auto-generated method stub
//		//System.out.println("DiagramLayoutEditPolicy.getCreateCommand()");
		if (request.getNewObject() instanceof WorkflowSubPart) 
		{
            CreateNodeCommand cmd = new CreateNodeCommand();
            cmd.setParent((WorkflowDiagram) getHost().getModel());
            cmd.setChild((WorkflowSubPart) request.getNewObject());
            Rectangle constraint = (Rectangle) getConstraintFor(request);
//            constraint.height = 30;
//            constraint.setSize(20,20);
//            constraint.width = 30;
            cmd.setLocation(constraint.getLocation());
//            //System.out.println("cmd.location:"+constraint.getLocation());
            return cmd;
        }
//		if(request.getNewObject() instanceof Wire)
//		{
//			WireCommand wireCmd = new WireCommand();
////			wireCmd.s
//		}
        return null;
	}

	protected Command getDeleteDependantCommand(Request request)
	{
//		//System.out.println("DiagramLayoutEditPolicy.getDeleteDependantCommand");
		// TODO Auto-generated method stub
		return null;
	}
	
	protected IFigure createSizeOnDropFeedback(CreateRequest createRequest) {
		IFigure figure;
		Object oTemp = createRequest.getNewObject();
		if (oTemp instanceof WorkflowBaseActivity)
			figure = new WorkflowActivityFigure(((WorkflowBaseActivity)oTemp).getName(),((WorkflowBaseActivity)oTemp).getIconImage());
		else {
			figure = new RectangleFigure();
			((RectangleFigure)figure).setXOR(true);
			((RectangleFigure)figure).setFill(true);
			figure.setBackgroundColor(WorkflowColorConstants.ghostFillColor);
			figure.setForegroundColor(ColorConstants.white);
		}
		
		addFeedback(figure);
		
		return figure;
	}

}
