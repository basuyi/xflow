/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.dialog;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.models.ApplicationActivity;
import net.ms.designer.editors.workflow.models.ParameterEntire;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;



/**
 * ��ǩ�������˵�������Ƕ�setInput��������ݼ���������ô��ǩ �����Ƕ����ݼ���ʵ������е��ֶ����ݽ��д����ɱ�ǩ�����������ݼ�¼�ڱ���ÿ
 * һ������ô��ʾ��
 */
public class ApplicationTableViewerLabelProvider implements ITableLabelProvider {
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
    	ApplicationActivity o = (ApplicationActivity) element; //Ҫ����ת��
        if (col == 0)
            return o.getApplicationName();
        if (col == 1)
        {
        	return o.getApplicationDesc();
        }
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