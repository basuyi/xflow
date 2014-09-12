package net.ms.designer.core;
import net.ms.designer.editors.componentdetail.models.Container;

import org.eclipse.ui.IEditorInput;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;



public class MsEditorInput implements IEditorInput
{
	private MsProject project;
	private String name;
	private Object treeView;
	private Object object;
	private MsContext context;
	private String editorType;
	private String editorName;
	
	// added 2006/11/16
	//*********begin******************
	private Container container;
	private boolean isSubflow = false;
	//**********end*******************
	
	/**
     * 返回true，则打开该编辑器后它会出现在Eclipse主菜单“文件”
     * 最下部的最近打开的文档栏中。返回flase则不出现在其中。
     */
    public boolean exists() 
    {
        return false;
    }

    /**
     * 编辑器标题栏的图标，不过它还需要在ChinaEditor中用
     * setTitleImage方法设置，才能出现在标题栏中
     */
    public ImageDescriptor getImageDescriptor() 
    {
//    	//System.out.println("EditorInput.getImageDescriptor()");
//        return WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_HOME_NAV);
    	return null;
    }

    /**
     * 编辑器标题栏的显示名称，和上面的getImageDescriptor
     * 一样也要在ChinaEditor中用setPartName方法设置，才
     * 能出现在标题栏中。
     */
    public String getName() 
    {
        return this.name;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
    

    public MsProject getProject()
    {
    	return this.project;
    }
    public void setProject(MsProject project)
    {
    	this.project = project;
    }
    
    
    /**
     * 编辑器标题栏的小黄条提示文字，不需象getName那样在ChinaEditor中再设置
     */
    public String getToolTipText() 
    {
        return getName();
    }

    /**
     * 返回一个可以用做保存本编辑输入数据状态的对象 
     */
    public IPersistableElement getPersistable() 
    {
        return null;
    }

    /**
     * 得到一个编辑器的适配器
     *     IAdaptable a = new ChinaEditorInput();
     *     IFoo x = (IFoo)a.getAdapter(IFoo.class);
     *     if (x != null)
     *         [用x来做IFoo的事情]
     */
    public Object getAdapter(Class adapter) 
    {
        return null;
    }
    
    public void setTreeView(Object treeView){
    	this.treeView = treeView;
    }
    
    public Object getTreeView(){
    	return this.treeView;
    }

    public Object getObject()
    {
    	return this.object;
    }
    public void setObject(Object object)
    {
    	this.object = object;
    }
    
    public MsContext getContext()
    {
    	return this.context;
    }
    public void setContext(MsContext context)
    {
    	this.context = context;
    }
    
    public String getEditorName()
    {
    	return this.editorName;
    }
    public void setEditorName(String editorName)
    {
    	this.editorName = editorName;
    }
    
    public String getEditorType()
    {
    	return this.editorType;
    }
    public void setEditorType(String editorType)
    {
    	this.editorType = editorType;
    }
    // added 2006/11/16
	//*********begin******************
    public Container getContainer()
    {
    	return this.container;
    }
    public void setContainer(Container container)
    {
    	this.container = container;
    }
    public void setIsSubflow(boolean isSubflow)
    {
    	this.isSubflow = isSubflow;
    }
    public boolean getIsSubflow()
    {
    	return this.isSubflow;
    }
    //**********end*******************
}
