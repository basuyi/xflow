/**
 * @author liuchunxia
 */
package net.ms.designer.editors.workflow.dialog;

import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.ParameterPartial;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;



/**
 * 标签器。如果说内容器是对setInput输入的数据集做处理，那么标签 器则是对数据集中实体对象中的字段数据进行处理，由标签器来决定数据记录在表格的每
 * 一列是怎么显示的
 */
public class NodeTableViewerLabelProvider implements ITableLabelProvider {
    /**
     * 由此方法决定数据记录在表格的每一列是显示什么文字
     * 
     * @param element
     *            实体类对象
     * @param col
     *            列号，0是第一列
     * @return 返回值一定要避免NULL值,否则出错
     */
    public String getColumnText(Object element, int col) {
    	ParameterPartial o = (ParameterPartial) element; //要类型转换
        if (col == 0)
            return o.getFormalPara().getParaName();
        if (col == 1)
        {
        	if(o.getRealPara()!=null)
        		return o.getRealPara().getParaName();
        	else
        		return null;
        }

       
        return "";
    }

    /**
     * 返回每条记录前面的图标
     */
    public Image getColumnImage(Object element, int columnIndex) {
        return null;//和getColumnText()方法不同，此方法可返回NULL值
    }

    //-------------以下方法用处不大,让它们空实现，不用管-----------------
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