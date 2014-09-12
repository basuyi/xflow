package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.IEditorConstant;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


public class StringField extends CommonField
{

	 public StringField() 
	 {
	        super();
	  }
		static final long serialVersionUID = 1;

		private final String field_Type = "String"; //$NON-NLS-1$
		

		public static final String FIELD_STRING_LENGTH = "String Length"; //String's length

		public static final String STRING_ISMULTILINE = "ismultiple"; //$NON-NLS-1$	
		public static final String STRING_EXTRAPROP = "extraprop";

		private String strLength = "50";
		private int extraProperty;
		private String defaultValue = "";
		private static Image LED_ICON = new Image(null, TemplateConstants.class
				.getResourceAsStream("icons/String16.gif")); //$NON-NLS-1$

		// Properties relatively
		protected static List newPropList = new ArrayList();
		static 
		{
			newPropList.addAll(propDescriptors);
		}

		public IPropertyDescriptor[] getPropertyDescriptors() 
		{
			List propList = new ArrayList();	
			return (IPropertyDescriptor[]) propList
					.toArray(new IPropertyDescriptor[propList.size()]);
		}

		public Object getPropertyValue(Object propName) 
		{
				return super.getPropertyValue(propName);
		}

		public void resetPropertyValue(Object id) 
		{
				super.resetPropertyValue(id);
		}

		public void setPropertyValue(Object id, Object value) 
		{
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
		
		public int getFieldTypeID()
		{
			
			return super.getFieldTypeID();
		}
		
		public void setFieldTypeID()
		{
			super.setFieldTypeID(IEditorConstant.STRINGFIELD_TYPE);
		}

		public String getStrLength() 
		{
			return strLength;
		}

		public void setStrLength(String strLength) 
		{
			this.strLength = strLength;
			this.firePropertyChange(FIELD_STRING_LENGTH,null,strLength);
		}

		/* £¨·Ç Javadoc£©
		 * @see com.kenoah.kde.editors.kcg.model.common.KCGField#getFieldTypeLength()
		 */
		public int getFieldTypeLength() 
		{
			// TODO 
			return 0;
		}

		/* £¨·Ç Javadoc£©
		 * @see com.kenoah.kde.editors.kcg.model.common.KCGField#getFieldTypeScale()
		 */
		public int getFieldTypeScale() 
		{
			// TODO 
			return 0;
		}
		public String getDefaultValue() 
		{
			return defaultValue;
		}
		public void setDefaultValue(String defaultValue) 
		{
			this.defaultValue = defaultValue;
		}
	    public int getExtraProperty() 
	    {
	        return extraProperty;
	    }
	    public void setExtraProperty(int extraProperty) 
	    {
	        this.extraProperty = extraProperty;
	    }

	
}
