/**
 * @���ߣ��¸�
 * @Email��glchengang@yeah.net
 * @Blog��http://blog.csdn.net/glchengang
 */
package net.ms.designer.ui.preference;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * ���������ɴ����List�����е����ݼ�¼����ɸѡת��������Ҫʵ��
 * �ӿڵ���������������getElements����Ҫ�����������������������õ�����ʵ�־����ˡ�
 */
public class TableViewerContentProvider implements IStructuredContentProvider {
    /**
     * �˷����������뵽�������ݼ��ϣ�Ȼ��ת�������鷵�أ�ÿһ������Ԫ�ؾ���һ��
     * ʵ�������Ҳ��һ����¼������element����ͨ��setInput(getPeoples())����
     * �Ķ�����ΪgetPeoples()���ص���һ��List�����Բ���element������Ҳ��List��
     */
    public Object[] getElements(Object element) {
        if (element instanceof List)//��һ�������ж�
            return ((List) element).toArray(); //�����ݼ�Listת��Ϊ����
        else
            return new Object[0]; //���List�����򷵻�һ��������
    }

    //�˷�����TableViewer���󱻹ر�ʱִ�С������ڹر��˵�ȻTableViewerҲ���Źر�
    public void dispose() {
    }

    //�˷�����TableViewer�ٴε���setInput()ʱִ��
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}