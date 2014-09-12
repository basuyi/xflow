
package net.ms.designer.editors.componentdetail.editparts;

import java.beans.PropertyChangeEvent;

import net.ms.designer.editors.componentdetail.core.ITableContentProvider;
import net.ms.designer.editors.componentdetail.figures.CommonFigure;
import net.ms.designer.editors.componentdetail.figures.FigureFactory;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.Element;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;


/**
 * @author lili
 * @version 1.1.0
 * @explain defind the CommonEditPart,it can do some figure's order
 */
public class CommonEditPart extends BaseEditPart implements ITableContentProvider{

	/**
	 * @return return the Element model
	 */
	protected Element getField() {
//		if(((CommonField)getModel()).getField_Type().equals("Autonum"))
//		{
//			return null;
//		}
		return (Element) getModel();
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

			public void getName(AccessibleEvent e) {
				e.result = getFieldBase().getName();
			}
		};
	}
	
	/**
	 * @return return the CommonFigure's figure
	 */
	protected CommonFigure getFieldFigure() {
		
		return (CommonFigure) getFigure();
	}

	/*
	 *  (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Element.ID_FIELD_LABEL))
			getFieldFigure().setLabelText((String)evt.getNewValue());
		else if (evt.getPropertyName().equals(Element.ID_NAME))
		{
			getFieldFigure().setLabelText((String)evt.getNewValue());
		}
		else
			super.propertyChange(evt);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		
			IFigure f = FigureFactory.createFigure(getField());
		
		Rectangle r = new Rectangle();
		r = f.getBounds();
		Point p = new Point(r.x,r.y);
		getField().setLocation(p);
//		if(getField().getField_Type().equals("Autonum"))
//		{
//			r.y = r.y -1;
//			r.setSize(new Dimension(0,0));
////			f.setVisible(false);
//		}
		return f;
		
//		return FigureFactory.createFigure(getField());
	}

	/*
	 *  (non-Javadoc)
	 * @see net.ms.designer.editors.componentdetail.core.ITableContentProvider#getTableContents()
	 */
	public String[][] getTableContents() {
		EditPart parent = getParent();
		if(parent instanceof ITableContentProvider){
			return ((ITableContentProvider)parent).getTableContents();
		}
		return new String[0][0];
	}

}
