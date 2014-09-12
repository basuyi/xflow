package net.ms.designer.editors.componentdetail.editparts;

import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;


/**
 * @author lili
 * @version 1.1.0
 *defind the TreePartFactory,on different conditions it can new different models
 */
public class TreePartFactory implements EditPartFactory {

	/*
	 *  £¨·Ç Javadoc£©
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Container)
			return new LogicContainerTreeEditPart(model);
		if (model instanceof Element)
			return new LogicTreeEditPart(model);
		return new LogicTreeEditPart(model);
	}

}