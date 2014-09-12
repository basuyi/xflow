package net.ms.designer.editors.componentdetail.xmlpalse;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TableViewerContentProvider implements IStructuredContentProvider{


	public Object[] getElements(Object inputElement) {
		// TODO 自动生成方法存根
		if (inputElement instanceof List)
			return ((List)inputElement).toArray();
		else
			return new Object[0];
	}

	public void dispose() {
		// TODO 自动生成方法存根
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO 自动生成方法存根
		
	}

}
