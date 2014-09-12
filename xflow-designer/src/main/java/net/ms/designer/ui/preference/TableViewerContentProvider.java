/**
 * @作者：陈刚
 * @Email：glchengang@yeah.net
 * @Blog：http://blog.csdn.net/glchengang
 */
package net.ms.designer.ui.preference;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * 内容器。由此类对List集合中的数据记录进行筛选转化。此类要实现
 * 接口的三个方法，其中getElements是主要方法，另外两个方法很少用到，空实现就行了。
 */
public class TableViewerContentProvider implements IStructuredContentProvider {
    /**
     * 此方法接收输入到表格的数据集合，然后转化成数组返回，每一个数组元素就是一个
     * 实体类对象，也即一条记录。参数element就是通过setInput(getPeoples())输入
     * 的对象，因为getPeoples()返回的是一个List，所以参数element的类型也是List。
     */
    public Object[] getElements(Object element) {
        if (element instanceof List)//加一个类型判断
            return ((List) element).toArray(); //将数据集List转化为数组
        else
            return new Object[0]; //如非List类型则返回一个空数组
    }

    //此方法当TableViewer对象被关闭时执行。主窗口关闭了当然TableViewer也跟着关闭
    public void dispose() {
    }

    //此方法当TableViewer再次调用setInput()时执行
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}