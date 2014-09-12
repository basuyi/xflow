/**
 * @���ߣ��¸�
 * @Email��glchengang@yeah.net
 * @Blog��http://blog.csdn.net/glchengang
 */
package net.ms.designer.ui.view;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * ������������������Щ�����¼Ӧ�������TreeViewer����ʾ
 */
public class TreeViewerContentProvider implements ITreeContentProvider {
    /**
     * �����������������һ����ʾ��Щ����
     * 
     * @param inputElement
     *            ����tv.setInput()����������Ǹ�����
     * @return Object[]һ�����飬������һ��Ԫ�ؾ���һ�����
     */
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof List) {
            List list = (List) inputElement;
            return list.toArray();
        } else {
            return new Object[0]; //����һ��������
        }
    }

    /**
     * �ж�ĳ����Ƿ����ӽ�㡣������ӽ�㣬��ʱ���ǰ����һ����������ͼ��
     * 
     * @param element
     *            ��Ҫ�ж��Ƿ����ӵĽ��
     * @return true���ӽ�㣬false���ӽ��
     */
    public boolean hasChildren(Object element) {
        ITreeEntry entry = (ITreeEntry) element;
        List list = entry.getChildren();
        if (list == null || list.isEmpty()) //�ж��Ƿ����ӽ��
            return false;
        else
            return true;
    }

    /**
     * ������������������Ӧ����ʾ��Щ�ӽ�㡣
     * 
     * @param parentElement
     *            ��ǰ������Ľ�����
     * @return ���ӽ����ΪԪ�ص�����
     */
    public Object[] getChildren(Object parentElement) {
        ITreeEntry entry = (ITreeEntry) parentElement;
        List list = entry.getChildren();
        if (list == null || list.isEmpty())
            return new Object[0];
        else
            return list.toArray();
    }

    //--------------���·������ã���ʵ��----------------
    public Object getParent(Object element) {
        return null;
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}