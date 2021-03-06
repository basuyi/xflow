/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.editpolicies;

import net.ms.designer.editors.packages.commands.DeleteNodeCommand;
import net.ms.designer.editors.packages.models.Package;
import net.ms.designer.editors.packages.models.PackageDiagram;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;


/**
 * @author zhanghao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodeEditPolicy extends ComponentEditPolicy
{

    protected Command createDeleteCommand(GroupRequest deleteRequest) 
    {
        DeleteNodeCommand deleteCommand=new DeleteNodeCommand();
        deleteCommand.setDiagram((PackageDiagram)getHost().getParent().getModel());
        deleteCommand.setNode((Package)getHost().getModel());
        return deleteCommand;
    }
}
