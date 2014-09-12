package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

public class AutoNumField extends CommonField
{

		static final long serialVersionUID = 1;

		private final String field_Type = "Autonum";

		public static final String FIELD_PRIFIX = "Autonum Prefix"; 

		public static final String FIELD_SUFFIX = "Autonum suffix"; 

		public static final String FIELD_STARTNUM = "Autonum StartNum"; 

		private String prifix = ""; 

		private String suffix = "";

		private int startnum = 1; 

		private static Image LED_ICON = new Image(null, TemplateConstants.class
				.getResourceAsStream("icons/AutoNum16.gif"));

		protected static List newPropList = new ArrayList();

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
			return 2;
		}
		
		public int getFieldTypeLength()
		{
			return 10;
		}
		
		public int getFieldTypeScale()
		{
			return 0;
		}

		public String getPrifix() 
		{
			return prifix;
		}

		public void setPrifix(String prifix) 
		{
			this.prifix = prifix;
		}

		public int getStartnum() 
		{
			return startnum;
		}

		public void setStartnum(int startnum) 
		{
			this.startnum = startnum;
		}

		public String getSuffix() 
		{
			return suffix;
		}

		public void setSuffix(String suffix) 
		{
			this.suffix = suffix;
		}
	

}
