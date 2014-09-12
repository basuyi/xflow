package net.ms.designer.editors.enumcomponentdetail.tools;

import net.ms.designer.editors.enumcomponentdetail.action.PropertyAction;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;


public class MyContextMenuProvider extends ContextMenuProvider 
{

	private ActionRegistry actionRegistry;

	public MyContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) 
	{
		super(viewer);
		setActionRegistry(registry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ContextMenuProvider#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
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
		
		action = getActionRegistry().getAction(PropertyAction.COPY);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

		manager.add(new Separator());

		action = getActionRegistry().getAction(ActionFactory.SAVE.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_SAVE, action);

	}

	private IAction getAction(String actionId) 
	{
        return actionRegistry.getAction(actionId);
    }
	
	private ActionRegistry getActionRegistry() 
	{
		return actionRegistry;
	}

	private void setActionRegistry(ActionRegistry registry) 
	{
		actionRegistry = registry;
	}

}