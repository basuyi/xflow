package net.ms.designer.editors.componentdetail.actions;

import java.util.List;

import net.ms.designer.editors.componentdetail.ComponentdetailPlugin;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Table;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;




public class PropertyAction extends SelectionAction 
{

	private static final String COPY_REQUEST = "COPY";  //$NON-NLS-1$
	public static final String C = "COP";   //$NON-NLS-1$

	Request request;

	public PropertyAction(IWorkbenchPart part) 
	{
		super(part);
		request = new Request(COPY_REQUEST);
		setText("Property");
		setId(C);
		setImageDescriptor(ImageDescriptor.createFromFile(ComponentdetailPlugin.class,"/icons/bringback.gif")); //$NON-NLS-1$
		setHoverImageDescriptor(getImageDescriptor());
	}

	protected boolean calculateEnabled() 
	{
		//System.out.println(canPerformAction());
		return canPerformAction();
	}

	private boolean canPerformAction() 
	{
		if (getSelectedObjects().isEmpty())
			return false;
		List parts = getSelectedObjects();
		//System.out.println("getSelectedObjects()");
		for (int i=0; i<parts.size(); i++)
		{
			Object o = parts.get(i);
			if (!(o instanceof EditPart))
				return false;
			EditPart part = (EditPart)o;
			// This menu item will only be activated if an object of Typ "Example" is selected.
			if (!(part.getModel() instanceof Table)&&!(part.getModel() instanceof CommonField))
			{
				//System.out.println("actions.PropertyAction.canPerformAction()");
				return false;
			}
		}
		return true;
	
	}

	//What happened for this action is defined by the edit part of this object.
	private Command getCommand() 
	{
	
		List editparts = getSelectedObjects();
		CompoundCommand cc = new CompoundCommand();
		cc.setDebugLabel("debug");//$NON-NLS-1$
		for (int i=0; i < editparts.size(); i++) 
		{
			EditPart part = (EditPart)editparts.get(i);
			cc.add(part.getCommand(request));
		}
		return cc;
	}

	public void run() 
	{
		execute(getCommand());
	}
}
