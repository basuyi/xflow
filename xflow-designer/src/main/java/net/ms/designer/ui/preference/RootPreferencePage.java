package net.ms.designer.ui.preference;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RootPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
	public void init(IWorkbench workbench)
	{
		
	}
	public Control createContents(Composite parent)
	{
		Composite topComp = new Composite(parent,SWT.NONE);
		topComp.setLayout(new RowLayout());
		Label label = new Label(topComp,SWT.NONE);
		label.setText("欢迎使用MS设计器");
		return topComp;
	}
}