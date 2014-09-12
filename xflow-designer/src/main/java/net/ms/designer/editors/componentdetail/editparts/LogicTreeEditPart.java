package net.ms.designer.editors.componentdetail.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;

import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.policies.ElementEditPolicy;
import net.ms.designer.editors.componentdetail.policies.LogicTreeEditPolicy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


/**
 * EditPart for Logic components in the Tree.
 */
public class LogicTreeEditPart extends
		org.eclipse.gef.editparts.AbstractTreeEditPart implements
		PropertyChangeListener {

	/**
	 * Constructor initializes this with the given model.
	 * 
	 * @param model
	 *            Model for this.
	 */
	public LogicTreeEditPart(Object model) {
		super(model);
	}

	public void activate() {
		super.activate();
		getFieldBase().addPropertyChangeListener(this);
	}

	/**
	 * Creates and installs pertinent EditPolicies for this.
	 */
	protected void createEditPolicies() {
		EditPolicy component=null;
		if (getModel() instanceof Element)
			component = new ElementEditPolicy();
		    
		else
			component = new ElementEditPolicy();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, component);
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new LogicTreeEditPolicy());
	}

	public void deactivate() {
		getFieldBase().removePropertyChangeListener(this);
		super.deactivate();
	}

	/**
	 * Returns the model of this as a LogicSubPart.
	 * 
	 * @return Model of this.
	 */
	protected Element getFieldBase() {
		return (Element) getModel();
	}

	/**
	 * Returns <code>null</code> as a Tree EditPart holds no children under
	 * it.
	 * 
	 * @return <code>null</code>
	 */
	protected List getModelChildren() {
		return children;
//	    if(getModel() instanceof LookupField)
//	        return ((LookupField)getModel()).getChildren();
//		return Collections.EMPTY_LIST;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// TODO 自动生成方法存根
		
	}

//	public void propertyChange(PropertyChangeEvent change) {
//		if (change.getPropertyName().equals(Container.CHILDREN)) {
//			if (change.getOldValue() instanceof Integer)
//				// new child
//				addChild(createChild(change.getNewValue()), ((Integer) change
//						.getOldValue()).intValue());
//			else {
//				// remove child
//				Object o = getViewer().getEditPartRegistry().get(
//						change.getOldValue());
//				if (o != null)
//					removeChild((EditPart) o);
//			}
//		} else
//			refreshVisuals();
//	}
//
//	/**
//	 * Refreshes the Widget of this based on the property given to update. All
//	 * major properties are updated irrespective of the property input.
//	 * 
//	 * @param property
//	 *            Property to be refreshed.
//	 */
//	protected void refreshVisuals() {
//		if (getWidget() instanceof Tree)
//			return;
//		Image image = getFieldBase().getIcon();
//		TreeItem item = (TreeItem) getWidget();
//		if (image != null)
//			image.setBackground(item.getParent().getBackground());
//		setWidgetImage(image);
//		setWidgetText(getFieldBase().toString());
//	}

}