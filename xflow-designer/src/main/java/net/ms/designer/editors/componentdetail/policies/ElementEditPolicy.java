
package net.ms.designer.editors.componentdetail.policies;

import net.ms.designer.editors.componentdetail.commands.DeleteCommand;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.Table;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;


/**
 * @author lili
 * @version 1.1.0
 *defind the ElementEditPolicy to deal with the deleteRequest
 */
public class ElementEditPolicy extends ComponentEditPolicy {

	/*
	 *  £¨·Ç Javadoc£©
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		
		if((Element)getHost().getModel() instanceof ComponentTable)
		{
			return null;
		}
		if(!((Element)getHost().getModel() instanceof ComponentTable)&& !((Element)getHost().getModel() instanceof FlowField)
				&&!((Element)getHost().getModel() instanceof CommonField))
		{
			if(((Table)((Element)getHost().getModel())).getSubTabel().size()>0)
			{
				return null;
			}
		}
		Object parent = getHost().getParent().getModel();
		DeleteCommand deleteCmd = new DeleteCommand();
		deleteCmd.setParent((Container) parent);
		deleteCmd.setChild((Element) getHost().getModel());
		return deleteCmd;
	}
}
