package net.ms.designer.editors.componentdetail.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.policies.ElementEditPolicy;
import net.ms.designer.editors.componentdetail.policies.NodeEditPolicy;
import net.ms.designer.editors.componentdetail.policies.PropertyEditPolicy;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;


/**
 * @author lili
 * @version 1.1.0
 * @explain defind the BaseEditPart,it is the parent of the other EditPart,it can let EditPolicy to do somethings
 */
public abstract class BaseEditPart extends AbstractGraphicalEditPart
implements NodeEditPart, PropertyChangeListener 
{

	private AccessibleEditPart acc;    //defind the record change

	abstract protected AccessibleEditPart createAccessible();

	/**
	 * @return return the AccessibleEditPart acc
	 */
	protected AccessibleEditPart getAccessibleEditPart() {
		if (acc == null)
			acc = createAccessible();
		return acc;
	}

	/**
	 * @explain: construct of BaseEditPart()
	 *
	 */
	public BaseEditPart() {
		super();
	}

	/**
	 * @explain: defind the idea of EditPolicies,let the Policies work on somethings
	 */
	protected void createEditPolicies() {
//		installEditPolicy(EditPolicy.COMPONENT_ROLE, new KCGElementEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new NodeEditPolicy());
		this.installEditPolicy(EditPolicy.LAYOUT_ROLE,new PropertyEditPolicy());
		this.installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
	}
	
	/*
	 *  (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (Element.CHILDREN.equals(prop)){
			refreshChildren();
		}else if (Table.INPUTS.equals(prop))
			refreshTargetConnections();
		else if (Table.OUTPUTS.equals(prop))
			refreshSourceConnections();
		else if (prop.equals(Container.ID_SIZE)
				|| prop.equals(Container.ID_LOCATION)
				|| prop.equals(Element.ID_NAME)
				|| prop.equals((Element.ID_FIELD_LABEL)))
			refreshVisuals();
	}

	protected void refreshVisuals() {
		Point loc = getFieldBase().getLocation();
		Dimension size = getFieldBase().getSize();
		Rectangle r = new Rectangle(loc, size);
		/*Temp solution for show*/
//		if(getFigure() instanceof BringbackFigure)
//		    ((KCGBringbackFigure)getFigure()).setLabelText(((BringbackFieldModel)getModel()).getFieldLabel());
	
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), r);
	}

	/*
	 * £¨·Ç Javadoc£©
	 * 
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		if (isActive())
			return;
		super.activate();
		getFieldBase().addPropertyChangeListener(this);
	}

	/**
	 * @return return the model
	 */
	protected Element getFieldBase() {
		return (Element) getModel();
	}

	/*
	 * £¨·Ç Javadoc£©
	 * 
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate() {
		if (!isActive())
			return;
		getFieldBase().removePropertyChangeListener(this);
		super.deactivate();
	}

	/**
	 * The ChopboxAnchor's location is found by calculating the intersection of a line drawn
	 * from the center point of its owner's box to a reference point on that box. Thus 
	 * {@link Connection Connections} using the ChopBoxAnchor will be oriented such that they
	 * point to their owner's center.
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

	/**
	 * @explain: if there is a request ,new ChopboxAnchor
	 */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * @explain: if there is connection,new ChopboxAnchor
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * @explain: if there is a request ,new ChopboxAnchor
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * @explain:if model instancof table,return SourceConnections
     */
	protected List getModelSourceConnections() 
	{
		if (getFieldBase() instanceof Table) 
		{
			Table kcgt = (Table) getFieldBase();
			return kcgt.getSourceConnections();
		}
		else if(getFieldBase() instanceof FlowField )
		{
			FlowField fField = (FlowField)getFieldBase();
			return fField.getSourceConnections();
		}
		return super.getModelSourceConnections();
	}

	/**
	 * @explain: if model instanceof table,return TargeConnections
	 */
	protected List getModelTargetConnections() {
		if (getFieldBase() instanceof Table) {
			Table kcgt = (Table) getFieldBase();
			return kcgt.getTargetConnections();
		}
		return super.getModelTargetConnections();
	}


}
