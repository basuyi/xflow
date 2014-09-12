package net.ms.designer.editors.component.commands;

import net.ms.designer.editors.component.dialog.PropertyDialog;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;





public class PropertyCommand extends Command
{
	Shell shell;
	private Component obj;
	private CompDiagram diagram;
    
	public void execute()
	{
		PropertyDialog dialog = new PropertyDialog(shell,obj,diagram);
		dialog.status = 1;
    	dialog.open();
	}
	
	public Component getPropertyObject()
	{
		return obj;
	}
	public void setPropertyObject(Component t)
	{
		this.obj = t;
	}
	
	public void setDiagram(CompDiagram diagram) 
	{
		this.diagram = diagram;
	}
}