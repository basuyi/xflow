/*
 * qing.yang : 2005-7-19
 */
package net.ms.designer.editors.componentdetail.editparts;

import net.ms.designer.editors.componentdetail.figures.FigureFactory;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleEditPart;


/**
 * @author qing.yang
 *  
 */
public class BringbackFieldEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return null;
	}

	protected void createEditPolicies() {
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		return figure;
//		return FigureFactory
//				.createBringbackFigure((BringbackFieldModel) getModel());
	}

}