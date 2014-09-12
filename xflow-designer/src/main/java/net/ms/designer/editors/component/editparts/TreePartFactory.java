/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.editparts;

import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;


public class TreePartFactory implements EditPartFactory
{

    public EditPart createEditPart(EditPart context, Object model) 
    {
        if (model instanceof CompDiagram) 
        {
            return new DiagramTreeEditPart(model);
         }
         else if (model instanceof Component) 
         {
            return new NodeTreeEditPart(model);
         }
         else 
         {
            return null;
         }
    }
}
