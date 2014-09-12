package net.ms.designer.editors.componentdetail.tools;

import net.ms.designer.editors.componentdetail.actions.PropertyAction;

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
		
		action = getActionRegistry().getAction(PropertyAction.C);
//		//System.out.println(action);
		//System.out.println(action);
        if (action.isEnabled()){
        
        	//System.out.println("MyContextMenuPorpety.getActionRegistry()");
    
        	manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
        }
        //System.out.println("MyContextMenuPorpety.getActionRegistry().appendToGroup");
		manager.add(new Separator());
		//System.out.println("MyContextMenuPorpety.getActionRegistry().add");
//
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