package net.ms.designer.editors.enumcomponentdetail.editpolicy;

import net.ms.designer.editors.enumcomponentdetail.editpart.ValueFieldEditPart;
import net.ms.designer.editors.enumcomponentdetail.figure.ValueFieldFigure;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;


public class ValueFieldSelectionEditPolicy extends NonResizableEditPolicy 
{
    private ValueFieldFigure getLabel() 
    {
    	ValueFieldEditPart part = (ValueFieldEditPart)getHost();
    	return ((ValueFieldFigure)part.getFigure());
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
