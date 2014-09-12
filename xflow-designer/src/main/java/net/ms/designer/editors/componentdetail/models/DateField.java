package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.IEditorConstant;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


/**
 * @author lili
 * @version 1.1.0
 * @explain defind the Datefield's properties,it extends CommonField
 */

public class DateField extends CommonField
{

	/**
	 * @explain construt of DateField()
	 *
	 */
	public DateField() 
	{
		super();
	}

	static final long serialVersionUID = 1;

	private final String field_Type = "Date"; 

	public static final String FIELD_DATE_FORMAT = "date"; // defind the format of Date

//	public static final String DateFormatPattern = "yyyy-MM-dd";
	
    public static String dateFormatPattern = "1";

	private int dispFormat = 0; // 0 = Date ; 1 = DateTime

	private int resultID = 11; //init the FieldID = 11

	private String defaultValue = null;

	private static Image LED_ICON = new Image(null, TemplateConstants.class.getResourceAsStream("icons/Date16.gif"));

	protected static List newPropList = new ArrayList();
	static 
	{
		newPropList.addAll(propDescriptors);
	}

	 /**
     * @return Returns the propertyDescriptors.
     */
	public IPropertyDescriptor[] getPropertyDescriptors() 
	{
		List propList = new ArrayList();
		propList.addAll(getRangePropsList());
		propList.addAll(newPropList);
		filterPropertyDescriptor(propList);

		return (IPropertyDescriptor[]) propList.toArray(new IPropertyDescriptor[propList.size()]);

	}

	 /**
     * @return Returns the PropertyValue.
     */
	public Object getPropertyValue(Object propName) 
	{
		if (propName.equals(FIELD_DATE_FORMAT))
			return new Integer(getDispFormat());
		else if (propName.equals(FIELD_DEFVALUE)) 
		{
			String defValue = getDefaultValue();
			if ("${now}".equalsIgnoreCase(defValue))
				return defValue;
			else
				return getDefaultValue() == null ? "" : getDefaultValue();
		} else
			return super.getPropertyValue(propName);
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	public void resetPropertyValue(Object id) 
	{
		if (id.equals(FIELD_DATE_FORMAT))
			setDispFormat(0);
		else if (id.equals(FIELD_DEFVALUE))
			setDefaultValue(null);
		else
			super.resetPropertyValue(id);
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) 
	{
		if (id.equals(FIELD_DATE_FORMAT)) 
		{

			// 如果选择“Date”则返回值为11，否则为datetime，即4
			if (value.equals(new Integer(1)))
				resultID = 4;
			else
				resultID = 11;

			setDispFormat(Integer.parseInt(value.toString()));
		} else if (id.equals(FIELD_DEFVALUE)) 
		{
			setDefaultValue((String) value);
		}
		super.setPropertyValue(id, value);
	}

	/**
	 * @return LED_ICON of Date
	 * 
	 */
	public Image getIconImage() 
	{
		return LED_ICON;
	}

	
	public String getDateFormatPattern()
	{
		return dateFormatPattern;
	}
	
	public void setDateFormatPattern(String dateFormatPattern)
	{
		this.dateFormatPattern = dateFormatPattern;
		
	}
	
	/*
	 *  （非 Javadoc）
	 * @see net.ms.designer.editors.componentdetail.models.Element#getField_Type()
	 */
	public String getField_Type() 
	{
		return field_Type;
	}

	/*
	 *  （非 Javadoc）
	 * @see net.ms.designer.editors.componentdetail.models.Element#getFieldTypeID()
	 */
	public int getFieldTypeID() 
	{
		return super.getFieldTypeID();
	}
	
	public void setFieldTypeID()
	{
		super.setFieldTypeID(IEditorConstant.DATEFIELD_TYPE);
	}


	/**
	 * 
	 * @return dispFormat
	 */
	public int getDispFormat() 
	{
		return dispFormat;
	}

	/**
	 * @param dispFormat
	 *         dispFormat to set
	 */
	public void setDispFormat(int dispFormat) 
	{
		this.dispFormat = dispFormat;
	}

	/*
	 *  （非 Javadoc）
	 * @see net.ms.designer.editors.componentdetail.models.Element#getFieldTypeLength()
	 */
	public int getFieldTypeLength() 
	{
		return 23;
	}

	/*
	 *  （非 Javadoc）
	 * @see net.ms.designer.editors.componentdetail.models.Element#getFieldTypeScale()
	 */
	public int getFieldTypeScale() 
	{
		return 3;
	}


	/**
	 * @return return defaultValue
	 */
	public String getDefaultValue() 
	{
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 *         defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) 
	{
		this.defaultValue = defaultValue;
		this.firePropertyChange(DEFAULT_VALUE,null,defaultValue);
	}

}
