package net.ms.designer.editors.packages.tools;

import net.ms.designer.editors.packages.models.Package;

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
		Package node=new Package();
		InputDialog dlg = new InputDialog(shell, "Gef Practice", "Node name:", "Node", null);
		dlg.open();
		node.setName(dlg.getValue());
		return node;
	}

	public Object getObjectType() 
	{
		return Package.class;
	}

}
