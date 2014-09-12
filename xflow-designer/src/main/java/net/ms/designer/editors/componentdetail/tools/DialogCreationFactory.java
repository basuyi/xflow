package net.ms.designer.editors.componentdetail.tools;

import net.ms.designer.editors.componentdetail.models.CommonField;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;


public class DialogCreationFactory implements CreationFactory 
{

	private Shell shell;

	public void setShell(Shell shell) 
	{
		this.shell = shell;
	}

	public Object getNewObject() 
	{
		CommonField node=new CommonField();
		InputDialog dlg = new InputDialog(shell, "Gef Practice", "Node name:", "Node", null);
		dlg.open();
		node.setName(dlg.getValue());
		return node;
	}

	public Object getObjectType() 
	{
		return CommonField.class;
	}

}
