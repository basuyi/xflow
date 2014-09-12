package net.ms.designer.editors.packages.actions;

import java.util.List;

import msdesigner.MsdesignerPlugin;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;



public class PropertyAction extends SelectionAction 
{

	private static final String PROPERTY_REQUEST = "PROPERTY";  //$NON-NLS-1$
	public static final String PROPERTY = "PROPERTY";   //$NON-NLS-1$

	Request request;

	public PropertyAction(IWorkbenchPart part) 
	{
		super(part);
		request = new Request(PROPERTY_REQUEST);
		setText(" Ù–‘");
		setId(PROPERTY);
		setImageDescriptor(ImageDescriptor.createFromFile(MsdesignerPlugin.class,"../../icons/property.gif")); //$NON-NLS-1$
		setHoverImageDescriptor(getImageDescriptor());
	}

	protected boolean calculateEnabled() 
	{
		return canPerformAction();
	}

	private boolean canPerformAction() 
	{
		if (getSelectedObjects().isEmpty())
			return false;
		List parts = getSelectedObjects();
		for (int i=0; i<parts.size(); i++)
		{
			Object o = parts.get(i);
			if (!(o instanceof EditPart))
				return false;
			EditPart part = (EditPart)o;
			// This menu item will only be activated if an object of Typ "Example" is selected.
//			if (!(part.getModel() instanceof Package))
//			{
//				return false;
//			}
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
