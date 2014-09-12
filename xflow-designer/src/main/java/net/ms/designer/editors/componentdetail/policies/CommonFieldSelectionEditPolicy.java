package net.ms.designer.editors.componentdetail.policies;

import net.ms.designer.editors.componentdetail.editparts.CommonEditPart;
import net.ms.designer.editors.componentdetail.figures.CommonFigure;
import net.ms.designer.editors.enumcomponentdetail.editpart.ValueFieldEditPart;
import net.ms.designer.editors.enumcomponentdetail.figure.ValueFieldFigure;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;


public class CommonFieldSelectionEditPolicy extends NonResizableEditPolicy 
{
    private CommonFigure getLabel() 
    {
    	CommonEditPart part = (CommonEditPart)getHost();
    	return ((CommonFigure)part.getFigure());
    }	
    protected void hideFocus() 
    {
    	getLabel().setFocus(false);
    }

    protected void hideSelection() 
    {
    	getLabel().setSelected(false);
    	getLabel().setFocus(false);	
    }

    protected void showFocus() 
    {
    	getLabel().setFocus(true);
    }

    protected void showPrimarySelection() 
    {
    	getLabel().setSelected(true);
    	getLabel().setFocus(true);
    }

    protected void showSelection() {
    	getLabel().setSelected(true);
    	getLabel().setFocus(false);
    }
}
