package net.ms.designer.editors.componentdetail.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.IEditorConstant;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


/**
 * @author lili
 * @version 1.1.0
 * @explain defind the Floatfield's properties,it extends CommonField
 */
public class FloatField extends CommonField
{

	/**
	 * @explain construct of FloatField()
	 *
	 */
	  public FloatField() 
	  {
	        super();
	  }
		static final long serialVersionUID = 1;
		
		private final String field_Type = "Float"; 

		public static final String FIELD_DIGITS = "Fraction Digits"; //defind the digits which behind the point

		private int digits = 2; //defind the digits which front the point
		
		private BigDecimal defaultValue = null;
		
		private String fractionDigits = null;
		
		private static Image LED_ICON = new Image(null, TemplateConstants.class
				.getResourceAsStream("icons/Float16.gif")); 

		protected static List newPropList = new ArrayList();

		/*
		 *  （非 Javadoc）
		 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
		 */
		public IPropertyDescriptor[] getPropertyDescriptors() 
		{
			
			    List propList = new ArrayList();
			    propList.addAll(getRangePropsList());
				propList.addAll(newPropList);
				filterPropertyDescriptor(propList);
				
				return (IPropertyDescriptor[]) propList
						.toArray(new IPropertyDescriptor[propList.size()]);
		}

		/*
		 *  （非 Javadoc）
		 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
		 */
		public Object getPropertyValue(Object propName) 
		{
			if (propName.equals(FIELD_DIGITS))
				return getDigits()+"";
			else if(propName.equals(FIELD_DEFVALUE))
				return getDefaultValue()==null?"":getDefaultValue().toString();
			else
				return super.getPropertyValue(propName);
		}

		/*
		 *  （非 Javadoc）
		 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
		 */
		public void resetPropertyValue(Object id) 
		{
			if (id.equals(FIELD_DIGITS))
				setDigits(0);
			else if(id.equals(FIELD_DEFVALUE))
				setDefaultValue(null);
			else
				super.resetPropertyValue(id);
		}
		
		/**
		 * 
		 * @param fractionDigits
		 *         fractionDigits to set
		 */
		public void setFractionDigitals(String fractionDigits)
		{
			this.fractionDigits = fractionDigits;
			this.firePropertyChange(FIELD_DIGITS,null,fractionDigits);
		}
		
	
		/**
		 * 
		 * @return return the fractionDigits
		 */
		public String getFractionDigitals()
		{
			return this.fractionDigits;
		}

		/*
		 *  （非 Javadoc）
		 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
		 */
		public void setPropertyValue(Object id, Object value) 
		{
			if (id.equals(FIELD_DIGITS))
				try
			{
					setDigits(Integer.parseInt(value.toString()));
			 }catch(NumberFormatException e)
				{
					e.printStackTrace();
				 }
				
			else if(id.equals(FIELD_DEFVALUE))
			    if(((String)value).equals(""))
			       setDefaultValue(null);
			    else
				   setDefaultValue(new BigDecimal((String)value));
				super.setPropertyValue(id, value);
		}


		/*
		 *  （非 Javadoc）
		 * @see net.ms.designer.editors.componentdetail.models.Element#getIconImage()
		 */
		public Image getIconImage() 
		{
			return LED_ICON;
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
			super.setFieldTypeID(IEditorConstant.FLOATFIELD_TYPE);
		}
		

		/**
		 * 
		 * @return return the digits
		 */
		public int getDigits() 
		{
			return digits;
		}

		/**
		 * @param dispFormat
		 *         dispFormat to set
		 */
		public void setDigits(int dispFormat) 
		{
			this.digits = dispFormat;
		}

		/*
		 *  （非 Javadoc）
		 * @see net.ms.designer.editors.componentdetail.models.Element#getFieldTypeLength()
		 */
		public int getFieldTypeLength() 
		{
			return 24;
		}

		/*
		 *  （非 Javadoc）
		 * @see net.ms.designer.editors.componentdetail.models.Element#getFieldTypeScale()
		 */
		public int getFieldTypeScale() 
		{
			return 8;
		}
		
		/**
		 * @return return the defaultValue
		 */
		public BigDecimal getDefaultValue() 
		{
			return defaultValue;
		}
		
		/**
		 * @param defaultValue
		 *         defaultValue to set
		 */
		public void setDefaultValue(BigDecimal defaultValue) 
		{
			this.defaultValue = defaultValue;
		}

}
