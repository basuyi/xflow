package net.ms.designer.editors.enumcomponentdetail.editpart;

import java.beans.PropertyChangeEvent;

import net.ms.designer.editors.enumcomponentdetail.editpolicy.ValueFieldNodeEditPolicy;
import net.ms.designer.editors.enumcomponentdetail.figure.ValueFieldFigure;
import net.ms.designer.editors.enumcomponentdetail.model.ValueField;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;


public class ValueFieldEditPart extends BaseEditPart implements NodeEditPart
{
    protected IFigure createFigure() 
    {
        return new ValueFieldFigure((ValueField)getModel());
    }

    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) 
    {
        return null;
    }

    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) 
    {
        return null;
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request) 
    {
    	return null;
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) 
    {
    	return null;
    }

    protected void createEditPolicies() 
    {
        super.createEditPolicies();
        this.installEditPolicy(EditPolicy.CONNECTION_ROLE,new ValueFieldNodeEditPolicy());
    }

    public void propertyChange(PropertyChangeEvent evt) 
    {
        super.propertyChange(evt);
    }
}
