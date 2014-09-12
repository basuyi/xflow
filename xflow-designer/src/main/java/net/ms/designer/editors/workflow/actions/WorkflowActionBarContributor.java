/**
 * @author liuchunxia
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.workflow.actions;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.actions.ActionFactory;

public class WorkflowActionBarContributor extends ActionBarContributor 
{


    protected void buildActions() 
    {
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        addRetargetAction(new DeleteRetargetAction());

    }
    protected void declareGlobalActionKeys() 
    {
        // TODO Auto-generated method stub

    }
    public void contributeToToolBar(IToolBarManager toolBarManager) 
    {
        toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
        toolBarManager.add(getAction(ActionFactory.REDO.getId()));
    }
}
