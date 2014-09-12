package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.IEditorConstant;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


public class IntegerField extends CommonField
{

	public IntegerField() 
	{
        super();
//        this.setBizField(ConvertObjectTree.convertKCGFieldToBizField(this,
//		        null));
    }
	static final long serialVersionUID = 1;

	private final String field_Type = "Integer"; //$NON-NLS-1$
	
	public static final String INTEGER_IS_SECURITYBASE = "securitybase";

	private Integer isSecurityBase;
	private Integer defaultValue = null;
	private static Image LED_ICON = new Image(null, TemplateConstants.class
			.getResourceAsStream("icons/Integer16.gif")); //$NON-NLS-1$

	public Image getIconImage() 
	{
		return LED_ICON;
	}
	
	public IPropertyDescriptor[] getPropertyDescriptors() 
	{
		List propList = new ArrayList();
		
		propList.addAll(propDescriptors);
		
		propList.addAll(getRangePropsList());
		
		filterPropertyDescriptor(propList);
		
		return (IPropertyDescriptor[])propList.toArray(new IPropertyDescriptor[propList.size()]);
	}
	
	public Object getPropertyValue(Object propName) 
	{
		if (FIELD_DEFVALUE.equals(propName))
			return (getDefaultValue()==null?"":getDefaultValue().toString());
		else if(INTEGER_IS_SECURITYBASE.equals(propName))
		{
			return getIsSecurityBase();
		}
		else
			return super.getPropertyValue(propName);
	}

	public void resetPropertyValue(Object id) 
	{
		if (FIELD_DEFVALUE.equals(id))
			setDefaultValue(null);
		else if(INTEGER_IS_SECURITYBASE.equals(id))
		{
			setIsSecurityBase(new Integer(0));
		}
		else
			super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) 
	{
		if (FIELD_DEFVALUE.equals(id))
		{
			String v = (String)value;
			if(v!="")
				setDefaultValue(Integer.valueOf(v));
			else
				setDefaultValue(null);
		}
		else if(INTEGER_IS_SECURITYBASE.equals(id))
		{
			setIsSecurityBase((Integer)value);
		}
			super.setPropertyValue(id, value);
	}

	public String getField_Type() 
	{
		return field_Type;
	}

	public int getFieldTypeID() 
	{
		return super.getFieldTypeID();
	}
	
	public void setFieldTypeID()
	{
		super.setFieldTypeID(IEditorConstant.INTEGERFIELD_TYPE);
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
	public Integer getIsSecurityBase() 
	{
		if(isSecurityBase==null)
			isSecurityBase = new Integer(0);
		return isSecurityBase;
	}
	public void setIsSecurityBase(Integer isSecurityBase) 
	{
		this.isSecurityBase = isSecurityBase;
	}


}
