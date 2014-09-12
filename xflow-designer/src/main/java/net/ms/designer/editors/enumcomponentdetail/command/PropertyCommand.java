package net.ms.designer.editors.enumcomponentdetail.command;

import net.ms.designer.editors.enumcomponentdetail.dialog.PropertyDialog;
import net.ms.designer.editors.enumcomponentdetail.model.Element;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.UIPlugin;


public class PropertyCommand extends Command
{
	Shell shell;
	private Element obj;
    
	public void execute()
	{
		PropertyDialog dialog = new PropertyDialog(shell,obj);
		dialog.status = 1;
    	dialog.open();
	}
	
	public Element getCopyObject()
	{
		return obj;
	}
	public void setCopyObject(Element t)
	{
		this.obj = t;
	}
}