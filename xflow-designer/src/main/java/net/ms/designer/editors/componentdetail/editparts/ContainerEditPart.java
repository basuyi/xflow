package net.ms.designer.editors.componentdetail.editparts;

import java.util.List;

import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.policies.LogicContainerEditPolicy;

import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.accessibility.AccessibleEvent;


/**
 * @author lili
 * @version 1.1.0
 * Provides support for Container EditParts.
 */
abstract public class ContainerEditPart extends BaseEditPart{
	
	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getName(AccessibleEvent e) {
				e.result = getDiagram().getName();
			}
			public void getValue(AccessibleEvent e) {
				e.result = null;
			}
		};
	}

	/**
	 * Installs the desired EditPolicies for this.
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new LogicContainerEditPolicy());
	}

	/**
	 * Returns the model of this as a LogicDiagram.
	 * 
	 * @return LogicDiagram of this.
	 */
	protected Container getDiagram() {
		return (Container) getModel();
	}

	/**
	 * Returns the children of this through the model.
	 * 
	 * @return Children of this as a List.
	 */
	protected List getModelChildren() {
		return getDiagram().getChildren();
	}

}
