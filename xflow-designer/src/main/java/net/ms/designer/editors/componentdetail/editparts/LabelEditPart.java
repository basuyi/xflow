
package net.ms.designer.editors.componentdetail.editparts;

import net.ms.designer.editors.componentdetail.figures.LabelFigure;
import net.ms.designer.editors.componentdetail.models.Label;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;


/**
 * @author lili
 * @version 1.1.0
 * defind the LabelEditPart.
 */
public class LabelEditPart extends BaseEditPart {

	protected IFigure createFigure() {
		return new LabelFigure();
	}

	/*
	 *  (non-Javadoc)
	 * @see net.ms.designer.editors.componentdetail.editparts.BaseEditPart#createAccessible()
	 */
	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
//				e.result = getFieldBase().getFieldLabel();
				e.result = null;
			}

			/*
			 *  (non-Javadoc)
			 * @see org.eclipse.gef.AccessibleEditPart#getName(org.eclipse.swt.accessibility.AccessibleEvent)
			 */
			public void getName(AccessibleEvent e) {
				e.result = getFieldBase().getName();
			}
		};
	}
	
	/**
	 * 
	 * @return the model 
	 */
	private Label getLogicLabel() {
		return (Label) getModel();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		((LabelFigure) getFigure()).setShowText(getLogicLabel()
				.getLabelContents());
		super.refreshVisuals();
	}
}
