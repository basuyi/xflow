package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class ChildTable extends Table
{

	public static String CHILD_TABLE_NEED_EXTEND = "table need extend";

	public static String CHILD_TABLE_MULTI_TENANT_FLAG = "multi Tenant Flag";

	protected TableExtendModel tem = null; // new KCGTableExtendModel();

	public static String CHILD_TABLE_EXTEND = "table extend"; //$NON-NLS-1$

	public static String MANUAL_VIEW = "manual view";

	public static String MANUAL_SP = "manual sp";

	public static String CHILD_TABLE_OPTIMIZED_LOCK = "Optimized Lock"; //$NON-NLS-1$
	
	public static String CHILD_TABLE_PKEY_ID = "childTable primary key";
	
	public static String CHILD_TABLE_EXTEND_ID = "childTable extend key";
	
	public int PKeyID = 0; //Ö÷¼ü
	
	public int EKeyID = 0; //Íâ¼ü
	
	public int childID = 0;
	
	public String manualView = "";

	public String manualSP = "";
	
	public String mainTableName = "";
	
	/**
	 * 
	 * @return return the mainTableName
	 */
	public String getMainTableName()
	{
		return this.mainTableName;
	}
	
	/**
	 * 
	 * @param mainTableName
	 *         mainTableName to set
	 */
	public void setMainTableName(String mainTableName)

	{
		this.mainTableName = mainTableName;
	}
	/**
	 * @return Returns the multiTenantFlag.
	 */
	public int getMultiTenantFlag() 
	{
		return multiTenantFlag;
	}

	/**
	 * 
	 * @return return ths PKeyID
	 */
	public int getPKeyID()
	{
		return this.PKeyID;
	}
	
	public int getChildID()
	{
		return this.childID;
	}
	public void setChildID(int childID)
	{
		this.childID = childID;
	}
	/**
	 * 
	 * @return return the EKeyID
	 */
	public int getEKeyID()
	{
		return this.EKeyID;
	}
	
	/**
	 * 
	 * @param PKeyID
	 *         PKeyID to set
	 */
	public void setPKeyID(int PKeyID)
	{
		this.PKeyID = PKeyID;
	}
	
	/**
	 * 
	 * @param EKeyID
	 *         EKeyID to set
	 */
	public void setEKeyID(int EKeyID)
	{
		this.EKeyID = EKeyID;
	}
	/**
	 * @param multiTenantFlag
	 *            The multiTenantFlag to set.
	 */
	public void setMultiTenantFlag(int multiTenantFlag) 
	{
		this.multiTenantFlag = multiTenantFlag;
	}

	private int multiTenantFlag = 0;

	private boolean optimizedLock = true;

	/**
	 * @return Returns the optimizedLock.
	 */
	public boolean isOptimizedLock() 
	{
		return optimizedLock;
	}

	/**
	 * @param optimizedLock
	 *            The optimizedLock to set.
	 */
	public void setOptimizedLock(boolean optimizedLock) 
	{
		this.optimizedLock = optimizedLock;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() 
	{
		List proplist = new ArrayList();

		IPropertyDescriptor[] suprop = super.getPropertyDescriptors();
		for (int i = 0; i < suprop.length; i++)
			proplist.add(suprop[i]);
		
		PropertyDescriptor pd =null;
		

		return (IPropertyDescriptor[]) proplist.toArray(new IPropertyDescriptor[proplist.size()]);
	}
//------------------------------------------------------------------------------------------
	public Object getPropertyValue(Object propName) 
	{

		if (propName.equals(CHILD_TABLE_NEED_EXTEND))
			return new Integer(tem == null ? 0 : 1);
		else if (propName.equals(MANUAL_VIEW)) 
		{
			return getManualView();
		} else if (propName.equals(MANUAL_SP)) 
		{
			return getManualSP();
		} else if (propName.equals(CHILD_TABLE_OPTIMIZED_LOCK)) 
		{
			return isOptimizedLock() ? new Integer(1) : new Integer(0);
		} else if (propName.equals(CHILD_TABLE_MULTI_TENANT_FLAG)) 
		{
			return new Integer(getMultiTenantFlag());
		}
		return propName;

	}

	public void resetPropertyValue(Object propName) 
	{

	}

	public void setPropertyValue(Object propName, Object value) 
	{

	}

	public TableExtendModel getTem() 
	{
		return tem;
	}

	public void setTem(TableExtendModel tem) 
	{
		this.tem = tem;
	}

	public String getField_Type() 
	{
		return "Child"; //$NON-NLS-1$
	}

	public void setFieldName(String fieldName) 
	{
//		String name = fieldName;
//		if (fieldName.length() > 0)
//			name = name.substring(0, 1).toUpperCase() + name.substring(1);
//		super.setName(name);
		this.setName(fieldName);
		this.firePropertyChange(ID_NAME,null,fieldName);
	}

	public String getManualSP() 
	{
		return manualSP;
	}

	public void setManualSP(String manualSP) 
	{
		this.manualSP = manualSP;
	}

	public String getManualView() 
	{
		return manualView;
	}

	public void setManualView(String manualView) 
	{
		this.manualView = manualView;
	}

}
