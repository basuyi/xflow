/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.editpolicies;

import net.ms.designer.editors.packages.commands.RenameNodeCommand;
import net.ms.designer.editors.packages.figures.NodeFigure;
import net.ms.designer.editors.packages.models.Package;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;



/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodeDirectEditPolicy extends DirectEditPolicy
{

    protected Command getDirectEditCommand(DirectEditRequest request) 
    {
        RenameNodeCommand cmd = new RenameNodeCommand();
        cmd.setNode((Package) getHost().getModel());
        cmd.setName((String) request.getCellEditor().getValue());
        return cmd;
    }
    protected void showCurrentEditValue(DirectEditRequest request) 
    {
        String value = (String) request.getCellEditor().getValue();
        ((NodeFigure) getHostFigure()).setName(value);
    }
}
