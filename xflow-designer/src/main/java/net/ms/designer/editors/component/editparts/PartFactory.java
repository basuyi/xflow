/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.editparts;

import net.ms.designer.editors.component.models.CompDiagram;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;


public class PartFactory implements EditPartFactory {

    public EditPart createEditPart(EditPart context, Object model) {
        EditPart part = null;
        if (model instanceof CompDiagram)
            part = new DiagramPart();
        else
            part = new NodePart();
        part.setModel(model);
        return part;
    }
}