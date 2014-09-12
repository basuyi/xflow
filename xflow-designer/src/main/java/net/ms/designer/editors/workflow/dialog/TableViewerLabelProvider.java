/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.dialog;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.models.ParameterEntire;

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
    	ParameterEntire o = (ParameterEntire) element; //Ҫ����ת��
        if (col == 0)
            return o.getParaName();
        if (col == 1)
        {
        	String paraType = "";
//        	if(o.getParaType().equals(Constants.WF_PARA_TYPE_BOOLEAN))
//        		paraType = Messages.getString("WorkflowParameterType.boolean");
        	if(o.getParaType().equals(Constants.WF_PARA_TYPE_DATE))
        		paraType = Messages.getString("WorkflowParameterType.date");
        	else if(o.getParaType().equals(Constants.WF_PARA_TYPE_DOUBLE))
        		paraType = Messages.getString("WorkflowParameterType.double");
        	else if(o.getParaType().equals(Constants.WF_PARA_TYPE_NONE))
        		paraType = Messages.getString("WorkflowParameterType.none");
        	else if(o.getParaType().equals(Constants.WF_PARA_TYPE_LONG))
        		paraType = Messages.getString("WorkflowParameterType.long");
        	else if(o.getParaType().equals(Constants.WF_PARA_TYPE_STRING))
        		paraType = Messages.getString("WorkflowParameterType.string");
        	else if(o.getParaType().equals(Constants.WF_PARA_TYPE_OBJECT))
        		paraType = Messages.getString("WorkflowParameterType.object");
        	else
        		paraType = o.getParaType();
            return paraType;
        }
        if(col == 2)
        {
        	String strInputOrOutput = null;
        	if(o.getIsInput() == true && o.getIsOutput() == false)
        		strInputOrOutput = "����";
        	if(o.getIsInput() == false && o.getIsOutput() == true)
        		strInputOrOutput = "���";
        	if(o.getIsInput() == true && o.getIsOutput() == true)
        		strInputOrOutput = "����+���";
        	return strInputOrOutput;
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