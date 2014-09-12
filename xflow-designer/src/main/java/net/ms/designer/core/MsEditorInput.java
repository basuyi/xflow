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
     * ����true����򿪸ñ༭�������������Eclipse���˵����ļ���
     * ���²�������򿪵��ĵ����С�����flase�򲻳��������С�
     */
    public boolean exists() 
    {
        return false;
    }

    /**
     * �༭����������ͼ�꣬����������Ҫ��ChinaEditor����
     * setTitleImage�������ã����ܳ����ڱ�������
     */
    public ImageDescriptor getImageDescriptor() 
    {
//    	//System.out.println("EditorInput.getImageDescriptor()");
//        return WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_HOME_NAV);
    	return null;
    }

    /**
     * �༭������������ʾ���ƣ��������getImageDescriptor
     * һ��ҲҪ��ChinaEditor����setPartName�������ã���
     * �ܳ����ڱ������С�
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
     * �༭����������С������ʾ���֣�������getName������ChinaEditor��������
     */
    public String getToolTipText() 
    {
        return getName();
    }

    /**
     * ����һ�������������汾�༭��������״̬�Ķ��� 
     */
    public IPersistableElement getPersistable() 
    {
        return null;
    }

    /**
     * �õ�һ���༭����������
     *     IAdaptable a = new ChinaEditorInput();
     *     IFoo x = (IFoo)a.getAdapter(IFoo.class);
     *     if (x != null)
     *         [��x����IFoo������]
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
