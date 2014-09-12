/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.commands;

import java.util.Date;

import net.ms.designer.core.MsContext;
import net.ms.designer.editors.component.dialog.PropertyDialog;
import net.ms.designer.editors.component.models.BizComponent;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;
import net.ms.designer.editors.component.models.EnumComponent;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.UIPlugin;


/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateNodeCommand extends Command 
{
	protected CompDiagram diagram;

	protected Component node;

	protected Point location;

	protected Shell shell;
	
	protected PackageEditor editor;

	// setters

	public CreateNodeCommand(Shell shell) 
	{
		super();
		this.shell = shell;
		this.editor = (PackageEditor)UIPlugin.getDefault()
						.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().getActiveEditor();
	}

	public void setDiagram(CompDiagram diagram) 
	{
		this.diagram = diagram;
	}

	public void setNode(Component node) 
	{
		this.node = node;
	}

	public void setLocation(Point location) 
	{
		this.location = location;
	}

	public void execute() 
	{
		if (this.location != null) 
		{
			this.node.setLocation(this.location);
		}
		PropertyDialog dialog = new PropertyDialog(shell , node , diagram);
		dialog.status = 0;
		Date data = new Date();
		long time =  data.getTime();
		node.setComponentID(Long.toString(time));
		
		int flag = dialog.open();
		if(Window.OK == flag)
		{
			this.diagram.addNode(this.node);
			
			MsContext context = editor.getContext();
			if(node instanceof BizComponent)
				context.updateContext(node , "componentdetail");
			if(node instanceof EnumComponent)
				context.updateContext(node , "enumdetail");
		}
		else if(Window.CANCEL == flag)
			return;
	}

	public String getLabel() 
	{
		return "Create Node";
	}

	public void redo() 
	{
		this.execute();
	}

	public void undo() 
	{
		diagram.removeNode(node);
	}
}