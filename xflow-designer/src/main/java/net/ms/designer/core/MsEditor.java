package net.ms.designer.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.part.EditorPart;

public class MsEditor extends EditorPart
{
	public void init(IEditorSite site , IEditorInput input)
	{
		setSite(site);
		setInput(input);
	}
	public boolean isDirty()
	{
		return false;
	}
	public void doSaveAs()
	{
		
	}
	public void setFocus()
	{
		
	}
	public void createPartControl(Composite parent)
	{
		
	}
	public boolean isSaveAsAllowed()
	{
		return false;
	}
	public void doSave(IProgressMonitor monitor)
	{
		
	}
}