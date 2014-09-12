package net.ms.designer.editors.enumcomponentdetail.editpolicy;


import net.ms.designer.editors.enumcomponentdetail.command.CreateCommand;
import net.ms.designer.editors.enumcomponentdetail.editpart.ValueFieldEditPart;
import net.ms.designer.editors.enumcomponentdetail.model.Element;
import net.ms.designer.editors.enumcomponentdetail.model.ValueField;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;


public class TableFlowLayoutEditPolicy extends FlowLayoutEditPolicy 
{
    protected EditPolicy createChildEditPolicy(EditPart child) 
    {
        if(child instanceof ValueFieldEditPart)
            return new ValueFieldSelectionEditPolicy();
        return super.createChildEditPolicy(child);
    }

    protected Command createAddCommand(EditPart child, EditPart after) 
    {
        return null;
    }

    protected Command createMoveChildCommand(EditPart child, EditPart after) 
    {
        return null;
    }

    protected Command getCreateCommand(CreateRequest request) 
    {
        Object obj = request.getNewObject();
        if(obj != null && request.getNewObjectType() == ValueField.class)
        {
            CreateCommand command = new CreateCommand();
            command.setParent((Element)this.getHost().getModel());
            command.setChild((Element)obj);
            
            EditPart after = getInsertionReference(request);
            int index = getHost().getChildren().indexOf(after);
            command.setIndex(index);
            return command;
        }
        return null;
    }

    protected Command getDeleteDependantCommand(Request request) 
    {
        return null;
    }

    protected boolean isHorizontal() 
    {
        IFigure figure = ((GraphicalEditPart)getHost()).getContentPane();
        LayoutManager layout = figure.getLayoutManager();
        if(layout instanceof FlowLayout)
        	return ((FlowLayout)figure.getLayoutManager()).isHorizontal();
        if(layout instanceof ToolbarLayout)
        	return ((ToolbarLayout)figure.getLayoutManager()).isHorizontal();
        return false;
    }
}
