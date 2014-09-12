/**
 * @���ߣ��¸�
 * @Email��glchengang@yeah.net
 * @Blog��http://blog.csdn.net/glchengang
 */
package net.ms.designer.ui.preference;

import net.ms.designer.core.DBTool;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * ��ǩ�������˵�������Ƕ�setInput��������ݼ���������ô��ǩ �����Ƕ����ݼ���ʵ������е��ֶ����ݽ��д����ɱ�ǩ�����������ݼ�¼�ڱ���ÿ
 * һ������ô��ʾ��
 */
public class TableViewerLabelProvider implements ITableLabelProvider {
    /**
     * �ɴ˷����������ݼ�¼�ڱ���ÿһ������ʾʲô����
     * 
     * @param element
     *            ʵ�������
     * @param col
     *            �кţ�0�ǵ�һ��
     * @return ����ֵһ��Ҫ����NULLֵ,�������
     */
    public String getColumnText(Object element, int col) {
    	DBTool o = (DBTool) element; //Ҫ����ת��
        if (col == 0)
            return o.getConName();
        if (col == 1)
            return o.getDbType();
        if (col == 2)
            return o.getServer();
       
        return "";
    }

    /**
     * ����ÿ����¼ǰ���ͼ��
     */
    public Image getColumnImage(Object element, int columnIndex) {
        return null;//��getColumnText()������ͬ���˷����ɷ���NULLֵ
    }

    //-------------���·����ô�����,�����ǿ�ʵ�֣����ù�-----------------
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