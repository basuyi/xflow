package net.ms.designer.editors.componentdetail.models;

import java.io.File;
import java.io.IOException;

import net.ms.designer.core.MsProject;
import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.componentdetail.IEditorConstant;
import net.ms.designer.editors.componentdetail.ui.CompDetailEditor;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.UIPlugin;



public class EnumField extends CommonField
{

	 public EnumField() 
	 {
	        super();
//	        this.setBizField(ConvertObjectTree.convertKCGFieldToBizField(this, null));
	 }

	    static final long serialVersionUID = 1;

	    private final String field_Type = "Enum"; //$NON-NLS-1$

	    public static final String FIELD_PATH = "path"; //$NON-NLS-1$
	     

	    public static final String SELECT_FORM = "select from";
	    
	    private int selectFrom =-1;
	    
	    private Integer defaultValue = new Integer(-1);
	    
	    private Object selectFromEnum = null;
	    
	    private String selectedEnum = "";

	    private static Image LED_ICON = new Image(null, TemplateConstants.class
				.getResourceAsStream("icons/Boolean16.gif")); //$NON-NLS-1$

	    
	    public Object getSelectFromEnum() {
	    	
	    	String name = getSelectedEnum();
//	    	if(name != null)
//	    	{
	    	IOStreams stream = new IOStreams();
	    	MsProject project = ((CompDetailEditor)UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getProject();
	    	String path = project.getConfigPath();
	    	StringBuffer sb= new StringBuffer(path);
	    	sb.append(name);
	    	String filePath = sb.toString();
	    	File file = new File(filePath);
    		if(file.exists())
    		{
    			try 
    			{
					selectFromEnum = stream.inputs(filePath);
				} 
    			catch (IOException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			catch (ClassNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//    		}
	    	
	        return selectFromEnum;
	    	}else{
	    		return null;
	    	}
	    
	    }

	    public void setSelectFromEnum(Object selectFromEnum) {
	        this.selectFromEnum = selectFromEnum;
	    }
	    
	    public String getSelectedEnum()
	    { 
	    	return this.selectedEnum;
	    }
	    
	    public void setSelectedEnum(String name)
	    {
	    	this.selectedEnum = name;
	    }
	    
	    
	    public Image getIconImage() 
	    {
	        return LED_ICON;
	    }
 
	    public String getField_Type() 
	    {
	        return field_Type;
	    }

	    public int getSelectFrom()
	    {
	    	return this.selectFrom;
	    }
	    
	    public void setSelectFrom(int selectFrom)
	    {
	    	this.selectFrom = selectFrom;
	    	this.firePropertyChange(SELECT_FORM,null,""+selectFrom);
	    }
	    public int getFieldTypeID() 
	    {
	    	return super.getFieldTypeID();
	    }
	    
	    public void setFieldTypeID()
		{
			super.setFieldTypeID(IEditorConstant.ENUMFIELD_TYPE);
		}
		
	    
	    public int getFieldTypeLength() 
	    {
	        return 10;
	    }
	    public int getFieldTypeScale() 
	    {
	        return 0;
	    }

	    public Integer getDefaultValue() 
	    {
	        return defaultValue;
	    }

	    public void setDefaultValue(Integer defaultValue) 
	    {

	        this.defaultValue = defaultValue;
	    }

	    public void setFieldName(String fieldName) 
	    {
	
	        super.setName(fieldName);
	    }



}
