package net.ms.designer.editors.componentdetail.policies;

import net.ms.designer.editors.componentdetail.commands.PropertyCommand;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;



public class PropertyEditPolicy extends LayoutEditPolicy{

	   private static final String
	    COPY_REQUEST = "PROPERTY"; //$NON-NLS-1$
	    
	    public Command getCommand(Request request) 
	    {
	    	if (COPY_REQUEST.equals(request.getType()))
	    		return getCopyCommand();
	    	return super.getCommand(request);
	    }

	    protected Command getCopyCommand()
	    {
//	    	 The implementation of CopyCommand is simple, will not be explained here.
	    	PropertyCommand command = new PropertyCommand();
	    	command.setCopyObject((Element)getHost().getModel());
//	    	command.setCopyObject((CommonField)getHost().getModel());
	    	command.setContainer((Container)getHost().getParent().getModel());
	    	return command;
	    }

	protected EditPolicy createChildEditPolicy(EditPart arg0) {
		// TODO 自动生成方法存根
		return null;
	}

	protected Command getCreateCommand(CreateRequest arg0) {
		// TODO 自动生成方法存根
		return null;
	}

	protected Command getDeleteDependantCommand(Request arg0) {
		// TODO 自动生成方法存根
		return null;
	}

	protected Command getMoveChildrenCommand(Request arg0) {
		// TODO 自动生成方法存根
		return null;
	}

}
