package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


public class AutoPField extends CommonField
{

	public AutoPField() 
	{
        super();

    }
	static final long serialVersionUID = 1;
	
	public static final String AUTOPNUM= "AutoPNum";
	

	private final String field_Type = "AutoPNum"; //$NON-NLS-1$
		
	public int AutoPFieldID = 0;

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

	public String getField_Type() 
	{
		return field_Type;
	}
	
//	public final int getFieldTypeID()
//	{
//		return IknDataType.BOOLEAN;
//	}
	
	public int getFieldTypeScale() 
	{
		return 0;
	}
	public int getAutoPFieldID() 
	{
		return AutoPFieldID;
	}
	public void setAutoPFieldID(int AutoPFieldID) 
	{
		
		this.AutoPFieldID = AutoPFieldID;
		this.firePropertyChange(AUTOPNUM,null,""+AutoPFieldID);
	}

}
