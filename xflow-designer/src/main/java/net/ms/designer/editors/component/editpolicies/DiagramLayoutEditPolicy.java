/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.editpolicies;

import net.ms.designer.editors.component.commands.CreateNodeCommand;
import net.ms.designer.editors.component.commands.MoveNodeCommand;
import net.ms.designer.editors.component.editparts.NodePart;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;

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
        return null;
    }

    protected Command createChangeConstraintCommand(EditPart child, Object constraint) 
    {
        if (!(child instanceof NodePart))
            return null;
        if (!(constraint instanceof Rectangle))
            return null;

        MoveNodeCommand cmd = new MoveNodeCommand();
        cmd.setNode((Component) child.getModel());
        cmd.setLocation(((Rectangle) constraint).getLocation());
        return cmd;

    }

    protected Command getCreateCommand(CreateRequest request) 
    {
        if (request.getNewObject() instanceof Component) 
        {
            CreateNodeCommand cmd = new CreateNodeCommand(getHost().getViewer().getControl().getShell());
            cmd.setDiagram((CompDiagram) getHost().getModel());
            cmd.setNode((Component) request.getNewObject());
            Rectangle constraint = (Rectangle) getConstraintFor(request);
            //System.out.println("test in policy:"+"("+constraint.getLocation().x+","+constraint.getLocation().y+")");
            cmd.setLocation(constraint.getLocation());
            return cmd;
        }
        return null;
    }

    protected Command getDeleteDependantCommand(Request request) 
    {
        return null;
    }
}