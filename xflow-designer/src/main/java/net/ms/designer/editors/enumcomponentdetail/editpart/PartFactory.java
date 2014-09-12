package net.ms.designer.editors.enumcomponentdetail.editpart;

import net.ms.designer.editors.enumcomponentdetail.editpart.ContainerEditPart;
import net.ms.designer.editors.enumcomponentdetail.model.Container;
import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.model.ValueField;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;


public class PartFactory implements EditPartFactory 
{
    public EditPart createEditPart(EditPart context, Object model) 
    {
        EditPart part = null;
        
        if(model instanceof Container)
        {
            part = new ContainerEditPart();
        }
        if(model instanceof Table)
        {
            part = new TableEditPart();
        }
        if(model instanceof ValueField)
        {
            part = new ValueFieldEditPart();
        }
        if(part != null) 
        	part.setModel(model);
        return part;
    }

}
