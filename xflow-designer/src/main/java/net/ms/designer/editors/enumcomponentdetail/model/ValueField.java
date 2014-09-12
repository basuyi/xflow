package net.ms.designer.editors.enumcomponentdetail.model;

import java.io.Serializable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ValueField extends Element implements IPropertySource,Serializable
{
	public static final String ID_NAME = "__id__name";
    
    public static final String ID_ISPK = "__id__ispk";
    
//     fieldName
     protected String fieldName = "";
     
//     international name
     protected String iname = "";
     
//     desc
     protected String desc = "";
//     nullable 
     protected boolean nullable = true;  
     
//     field size 
     protected int size = 0;
     
     transient protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[]
     {
             new TextPropertyDescriptor(ID_NAME,"Name")
     };

    public String getFieldName() 
    {
        return fieldName;
    }

    public void setFieldName(String fieldName) 
    {
        this.fieldName = fieldName;
    }
    
    public String getIname() 
    {
        return iname;
    }

    public void setIname(String iname) 
    {
        this.iname = iname;
    }

    public boolean isNullable() 
    {
        return nullable;
    }

    public void setNullable(boolean nullable) 
    {
        this.nullable = nullable;
    }

    public int getSize() 
    {
        return size;
    }

    public void setSize(int size) 
    {
        this.size = size;
    }
    
    public String getDesc() 
    {
        return desc;
    }

    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public Object getEditableValue() 
    {
        return null;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() 
    {
        return descriptors;
    }

    public Object getPropertyValue(Object id) 
    {
       if(id.equals(ID_NAME)) 
    	   return this.getFieldName();
       return null;
    }

    public boolean isPropertySet(Object id) 
    {
        return true;
    }

    public void resetPropertyValue(Object id) 
    {
    }

    public void setPropertyValue(Object id, Object value) 
    {
        if(id.equals(ID_NAME)) setFieldName((String)value);
    }
    
    
    public void fireConnectionChange(String type)
    {
        support.firePropertyChange(type,new Object(),null);
    }
}
