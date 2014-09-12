
package net.ms.designer.editors.workflow.editparts;

import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;

public class RouteOnlyActivityPart extends WorkflowBaseActivityEditPart {
	public RouteOnlyActivityPart(){
		super();
	}

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart(){

			public void getName(AccessibleEvent e) {
				e.result = "RouteOnlyActivityPart";
			}
			
			public void getValue(AccessibleControlEvent e) {
				e.result = Integer.toString(123);
			}

		};
	}
}
