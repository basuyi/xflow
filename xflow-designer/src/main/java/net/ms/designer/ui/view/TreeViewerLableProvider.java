/**
 * @���ߣ��¸�
 * @Email��glchengang@yeah.net
 * @Blog��http://blog.csdn.net/glchengang
 */
package net.ms.designer.ui.view;

import msdesigner.MsdesignerPlugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * ��ǩ�ṩ�������Ƽ�¼��������ʾ�����ֺ�ͼ���
 */
public class TreeViewerLableProvider implements ILabelProvider {
    /**
     * ��¼��ʾ�����֡����ܷ���NULLֵ
     */
    public String getText(Object element) {
        ITreeEntry entry = (ITreeEntry) element;
        return entry.getName();
    }

    /**
     * ��¼��ʾ��ͼ�񣬿��Է���NULLֵ
     */
    public Image getImage(Object element) {
    	if (element instanceof ProjectEntity) {
    		return ImageDescriptor.createFromFile(MsdesignerPlugin.class,"../../icons/prj_mode.gif").createImage();
    	} else if (element instanceof PackageEntity) {
    		return ImageDescriptor.createFromFile(MsdesignerPlugin.class,"../../icons/package_mode.gif").createImage();
    	} else if (element instanceof ComponentEntity) {
    		return ImageDescriptor.createFromFile(MsdesignerPlugin.class,"../../icons/plugin_obj.gif").createImage();
    	} else if (element instanceof WorkflowEntity) {
    		return ImageDescriptor.createFromFile(MsdesignerPlugin.class,"../../icons/link_obj.gif").createImage();
    	} else {
    		return null;
    	}
    }

    //--------���·����ݲ��ã���ʵ��----------
    public void addListener(ILabelProviderListener listener) {
    }

    public void dispose() {
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
    }
}