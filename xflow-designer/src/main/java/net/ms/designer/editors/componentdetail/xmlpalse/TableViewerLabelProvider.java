package net.ms.designer.editors.componentdetail.xmlpalse;

import net.ms.designer.core.MsProject;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;


public class TableViewerLabelProvider implements ITableLabelProvider{



	public Image getColumnImage(Object element, int columnIndex) {
		// TODO �Զ����ɷ������
		return null;
	}

	public String getColumnText(Object element, int col) {
		// TODO �Զ����ɷ������
		
//		Enmu e = (Enmu)element;
		MsProject e = (MsProject)element;
		if(col == 0) return e.getEnumCompName();
		if(col == 1) return e.getDesc();
		return "";
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO �Զ����ɷ������
		
	}

	public void dispose() {
		// TODO �Զ����ɷ������
		
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO �Զ����ɷ������
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO �Զ����ɷ������
		
	}

}
