package net.ms.designer.editors.componentdetail.editparts;

import java.util.List;

import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.policies.LogicContainerEditPolicy;
import net.ms.designer.editors.componentdetail.policies.LogicTreeContainerEditPolicy;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;


/**
 * @author lili
 * @version 1.1.0
 *defind the LogicContainerTreeEditPart
 */
public class LogicContainerTreeEditPart extends LogicTreeEditPart {

	/**
	 * Constructor, which initializes this using the model given as input.
	 */
	public LogicContainerTreeEditPart(Object model) {
		super(model);
	}

	/**
	 * Creates and installs pertinent EditPolicies.
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new LogicContainerEditPolicy());
		installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE,
				new LogicTreeContainerEditPolicy());
		//If this editpart is the contents of the viewer, then it is not
		// deletable!
		if (getParent() instanceof RootEditPart)
			installEditPolicy(EditPolicy.COMPONENT_ROLE,
					new RootComponentEditPolicy());
	}

	/**
	 * Returns the model of this as a LogicDiagram.
	 * 
	 * @return Model of this.
	 */
	protected Container getContainer() {
		return (Container) getModel();
	}

	/**
	 * Returns the children of this from the model, as this is capable enough of
	 * holding EditParts.
	 * 
	 * @return List of children.
	 */
	protected List getModelChildren() {
		return getContainer().getChildren();
	}

}