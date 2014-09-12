package net.ms.designer.editors.packages.commands;

import net.ms.designer.editors.packages.dialog.PropertyDialog;
import net.ms.designer.editors.packages.models.Package;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;




public class PropertyCommand extends Command
{
	Shell shell;
	private Package obj;
	private Object container;
    
	public void execute()
	{
		PropertyDialog dialog = new PropertyDialog(shell,obj,container);
		dialog.status = 1;
    	dialog.open();
	}
	
	public Package getPropertyObject()
	{
		return obj;
	}
	public void setPropertyObject(Package t)
	{
		this.obj = t;
	}
	public Object getContainer()
	{
		return this.container;
	}
	public void setContainer(Object container)
	{
		this.container = container;
	}
}