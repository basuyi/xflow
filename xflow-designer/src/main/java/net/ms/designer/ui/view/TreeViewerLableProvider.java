/**
 * @作者：陈刚
 * @Email：glchengang@yeah.net
 * @Blog：http://blog.csdn.net/glchengang
 */
package net.ms.designer.ui.view;

import msdesigner.MsdesignerPlugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * 标签提供器。控制记录在树中显示的文字和图像等
 */
public class TreeViewerLableProvider implements ILabelProvider {
    /**
     * 记录显示的文字。不能返回NULL值
     */
    public String getText(Object element) {
        ITreeEntry entry = (ITreeEntry) element;
        return entry.getName();
    }

    /**
     * 记录显示的图像，可以返回NULL值
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

    //--------以下方法暂不用，空实现----------
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