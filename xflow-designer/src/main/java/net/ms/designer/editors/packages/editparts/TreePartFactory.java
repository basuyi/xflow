/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.editparts;

import net.ms.designer.editors.packages.models.Package;
import net.ms.designer.editors.packages.models.PackageDiagram;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;


public class TreePartFactory implements EditPartFactory
{

    public EditPart createEditPart(EditPart context, Object model) 
    {
        if (model instanceof PackageDiagram) 
        {
            return new DiagramTreeEditPart(model);
         }
         else if (model instanceof Package) 
         {
            return new NodeTreeEditPart(model);
         }
         else 
         {
            return null;
         }
    }
}
