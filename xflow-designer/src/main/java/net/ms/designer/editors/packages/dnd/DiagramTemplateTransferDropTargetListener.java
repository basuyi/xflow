/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.dnd;

import net.ms.designer.editors.packages.tools.ElementFactory;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;


/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DiagramTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

    /**
     * @param viewer
     */
    public DiagramTemplateTransferDropTargetListener(EditPartViewer viewer) {
        super(viewer);
    }

    protected CreationFactory getFactory(Object template) {
        return new ElementFactory(template);
    }
}