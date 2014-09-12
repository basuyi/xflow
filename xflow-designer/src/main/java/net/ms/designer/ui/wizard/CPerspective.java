package net.ms.designer.ui.wizard;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class CPerspective implements IPerspectiveFactory
{
	public void createInitialLayout(IPageLayout layout) 
	{
		// TODO Auto-generated method stub
		String editorArea = layout.getEditorArea();
		IFolderLayout topLeft = layout.createFolder("topLeft",IPageLayout.LEFT,0.2f,editorArea);
		topLeft.addView("net.ms.designer.ui.view.MsTreeView");
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft",IPageLayout.BOTTOM,0.5f,"topLeft");
		bottomLeft.addView("org.eclipse.jdt.ui.PackageExplorer");	
	}
}
