/**
 * @author liuchunxia 
 * 
 * action registry
 */
package net.ms.designer.editors.workflow.tools;

import net.ms.designer.editors.workflow.actions.WorkflowPropertyAction;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;


public class WorkflowContextMenuProvider extends ContextMenuProvider
{

	private ActionRegistry actionRegistry;
	
	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	private void setActionRegistry(ActionRegistry registry) {
		actionRegistry = registry;
	}

	public WorkflowContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}

	public void buildContextMenu(IMenuManager manager) 
	{
		GEFActionConstants.addStandardActionGroups(manager);

		IAction action;

		action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		manager.appendToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.DELETE.getId()));
		
		manager.add(new Separator());
		
		action = getActionRegistry().getAction(WorkflowPropertyAction.WORKFLOW_PROPERTY);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

	}
	
	private IAction getAction(String actionId) 
	{
        return actionRegistry.getAction(actionId);
    }
}
