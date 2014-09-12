package net.ms.designer.editors.componentdetail.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.TableViewer;


public class MyActionGroup extends Action{

	private TableViewer tv;
	private CheckboxTableViewer ctv;
	
	public MyActionGroup(TableViewer v)
	{
		this.tv = v;
	}
	
	public MyActionGroup(TableViewer v,CheckboxTableViewer ctv)
	{
		this.tv = v;
		this.ctv = ctv;
	}
	
	public void run()
	{
		if(ctv != null)
		{
			Object checkObj = ctv.getCheckedElements();
			
		}
	}
	

}
