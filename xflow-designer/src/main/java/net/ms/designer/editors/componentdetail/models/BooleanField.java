package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


public class BooleanField extends CommonField
{

	public BooleanField() 
	{
        super();
//        this.setBizField(ConvertObjectTree.convertKCGFieldToBizField(this,
//		        null));
    }
	static final long serialVersionUID = 1;

	private final String field_Type = "Boolean"; //$NON-NLS-1$

	public static final String FIELD_STRING_LENGTH = "Boolean Length"; //$NON-NLS-1$

	public static final String FIELD_MULTILINE = "MultiLine"; //$NON-NLS-1$

	private int strLength = 50;

	private int isMultiline = 0; // 0 = ·ñ £»1 = ÊÇ
	
	private Integer defaultValue = new Integer(0);

	private static Image LED_ICON = new Image(null, TemplateConstants.class
			.getResourceAsStream("icons/Boolean16.gif")); //$NON-NLS-1$
	protected static List newPropList = new ArrayList();
	static 
	{
	
		newPropList.addAll(propDescriptors);

	}

	public IPropertyDescriptor[] getPropertyDescriptors() 
	{
		List propList = new ArrayList();
		propList.addAll(newPropList);
		filterPropertyDescriptor(propList);
		return (IPropertyDescriptor[]) propList
				.toArray(new IPropertyDescriptor[propList.size()]);
	}

	public Object getPropertyValue(Object propName) 
	{
		if (propName.equals(FIELD_STRING_LENGTH))
			return new Integer(getStrLength()).toString();
		else if (propName.equals(FIELD_MULTILINE))
			return new Integer(getStrMultiline());
		else if(propName.equals(FIELD_DEFVALUE))
			return getDefaultValue();
		else
			return super.getPropertyValue(propName);
	}

	public void resetPropertyValue(Object id) 
	{
		if (id.equals(FIELD_STRING_LENGTH))
			setStrLength(50);
		else if (id.equals(FIELD_MULTILINE))
			setStrMultiline(0);
		else if(id.equals(FIELD_DEFVALUE))
			setDefaultValue(new Integer(0));
		else
			super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) 
	{
		if (id.equals(FIELD_STRING_LENGTH))
			setStrLength(Integer.parseInt(value.toString()));
		else if (id.equals(FIELD_MULTILINE))
			setStrMultiline(Integer.parseInt(value.toString()));
//		else if(id.equals(FIELD_DEFVALUE))
//			setDefaultValue((Integer)value);
			super.setPropertyValue(id, value);
	}


	public Image getIconImage() 
	{
		return LED_ICON;
	}

	public String getField_Type() 
	{
		return field_Type;
	}
	
	public final int getFieldTypeID()
	{
		return IknDataType.BOOLEAN;
	}
	

	public int getStrLength() 
	{
		return strLength;
	}

	public void setStrLength(int strLength) 
	{
		this.strLength = strLength;
	}

	public int getStrMultiline() 
	{
		return isMultiline;
	}

	public void setStrMultiline(int strMultiline) 
	{
		this.isMultiline = strMultiline;
	}

	public int getFieldTypeLength() 
	{ 
		return getStrLength();
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

}
