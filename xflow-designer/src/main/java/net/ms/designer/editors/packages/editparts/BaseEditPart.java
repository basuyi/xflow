package net.ms.designer.editors.packages.editparts;

import net.ms.designer.editors.packages.editpolicies.PropertyEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;


public class BaseEditPart extends AbstractGraphicalEditPart
{
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.LAYOUT_ROLE,new PropertyEditPolicy());
	}
	protected IFigure createFigure()
	{
		return null;
	}
}
