package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

/**
 * @author lili
 * @version 1.1.0
 * @explain defind the Table's properties,it extends Container,it is the parent of ComponentTable.
 */
public class Table extends Container
{

		private int deletedFlag = 0; // 1 is yes, 0 is no

	    private int genMenuItem = 0; // defind the popedom,0 deputy no popedom,1 deputy only have popedom,2 deputy menu and popedom
	    
	    private String dbTableName = "";

	    private Integer listprivilege = new Integer(0);

	    private Integer viewprivilege = new Integer(0);

	    private Integer createprivilege = new Integer(0);

	    private Integer editprivilege = new Integer(0);

	    private Integer deleteprivilege = new Integer(0);

	    public static String TABLE_LISTPRIVILEGE = "table_list_privilege"; 

	    public static String TABLE_VIEWPRIVILEGE = "table_view_privilege"; 

	    public static String TABLE_CREATPRIVILEGE = "table_create_privilege"; 

	    public static String TABLE_EDITPRIVILEGE = "table_edit_privilege"; 

	    public static String TABLE_DELETEPRIVILEGE = "table_delete_privilege"; 

	    public static String TABLE_DELETEDFLAG = "deleted flag"; 

	    public static String TABLE_GENMENUITEM = "gen menu item"; 

	    public static String TABLE_DESCTIPTION = "table description"; 

	    public static String TABLE_TABLENAME = "DB table name"; 

	    protected static List props = new ArrayList();
	    
	    protected  List subTable = new ArrayList();
	    
	    /**
	     * @return  dbTableName.
	     */
	    public String getDbTableName() 
	    {
	       
	        return dbTableName;
	    }

	    /**
	     * @param dbTableName
	     *            The dbTableName to set.
	     */
	    public void setDbTableName(String dbTableName) 
	    {
	        this.dbTableName = dbTableName;
	        this.firePropertyChange(TABLE_TABLENAME,null,dbTableName);
	    }

	    /**
	     * @return Returns the createprivilege.
	     */
	    public Integer getCreateprivilege() 
	    {
	        return createprivilege;
	    }

	    /**
	     * @param createprivilege
	     *            The createprivilege to set.
	     */
	    public void setCreateprivilege(Integer createprivilege) 
	    {
	        this.createprivilege = createprivilege;
	    }

	    /**
	     * @return Returns the deleteprivilege.
	     */
	    public Integer getDeleteprivilege() 
	    {
	        return deleteprivilege;
	    }

	    /**
	     * @param deleteprivilege
	     *            The deleteprivilege to set.
	     */
	    public void setDeleteprivilege(Integer deleteprivilege) 
	    {
	        this.deleteprivilege = deleteprivilege;
	    }

	    /**
	     * @return Returns the editprivilege.
	     */
	    public Integer getEditprivilege() 
	    {
	        return editprivilege;
	    }

	    /**
	     * @param editprivilege
	     *            The editprivilege to set.
	     */
	    public void setEditprivilege(Integer editprivilege) 
	    {
	        this.editprivilege = editprivilege;
	        this.firePropertyChange(TABLE_EDITPRIVILEGE,null,editprivilege);
	    }

	    /**
	     * @return Returns the listprivilege.
	     */
	    public Integer getListprivilege() 
	    {
	        return listprivilege;
	    }

	    /**
	     * @param listprivilege
	     *            The listprivilege to set.
	     */
	    public void setListprivilege(Integer listprivilege) 
	    {
	        this.listprivilege = listprivilege;
	    }

	    /**
	     * @return Returns the viewprivilege.
	     */
	    public Integer getViewprivilege() 
	    {
	        return viewprivilege;
	    }

	    /**
	     * @param viewprivilege
	     *            The viewprivilege to set.
	     */
	    public void setViewprivilege(Integer viewprivilege) 
	    {
	        this.viewprivilege = viewprivilege;
	    }

	    /**
	     * @return Returns the propertyValue(Object).
	     */
	    public Object getPropertyValue(Object propName) 
	    {
			return propName;

//	        if (propName.equals(TABLE_LISTPRIVILEGE))
//	            return getListprivilege();
//	        else if (propName.equals(TABLE_VIEWPRIVILEGE))
//	        {
//	            return getViewprivilege();
//	        }
//	        else if (propName.equals(TABLE_CREATPRIVILEGE))
//	            return getCreateprivilege();
//	        else if (propName.equals(TABLE_EDITPRIVILEGE))
//	            return getEditprivilege();
//	        else if (propName.equals(TABLE_DELETEPRIVILEGE))
//	            return getDeleteprivilege();
//	        else if (propName.equals(TABLE_TABLENAME))
//	            return getDbTableName();
//
//	        if (propName.equals(TABLE_DELETEDFLAG))
//	            return new Integer(getDeletedFlag());
//	        else if (propName.equals(TABLE_GENMENUITEM))
//	            return new Integer(getGenMenuItem());
//	        else if (propName.equals(TABLE_DESCTIPTION))
//	            return getDesc();
//	        else
//	            return super.getPropertyValue(propName);
	    }

	    /**
	     * @param propertyValue
	     *            The propertyValue to reset.
	     */
	    public void resetPropertyValue(Object propName) 
	    {

//	        if (propName.equals(TABLE_DELETEDFLAG))
//	            setDeletedFlag(0);
//	        else if (propName.equals(TABLE_GENMENUITEM))
//	            setGenMenuItem(0);
//	        else
	            super.resetPropertyValue(propName);
	    }

	    /**
	     * @param propertyValue(Object,Object)
	     *            The propertyValue to set.
	     */
	    public void setPropertyValue(Object propName, Object value) 
	    {

//	        if (propName.equals(TABLE_LISTPRIVILEGE)) 
//	        {
//	            Integer listValue = (Integer) value;
//	            setListprivilege(listValue);
//	        } else if (propName.equals(TABLE_VIEWPRIVILEGE))
//	            setViewprivilege((Integer) value);
//	        else if (propName.equals(TABLE_CREATPRIVILEGE))
//	            setCreateprivilege((Integer) value);
//	        else if (propName.equals(TABLE_EDITPRIVILEGE))
//	            setEditprivilege((Integer) value);
//	        else if (propName.equals(TABLE_DELETEPRIVILEGE))
//	            setDeleteprivilege((Integer) value);
//	        else if (propName.equals(TABLE_TABLENAME)) {
//	            setDbTableName((String) value);
//	        }
//
//	        if (propName.equals(TABLE_DELETEDFLAG))
//	            setDeletedFlag(Integer.parseInt(value.toString()));
//	        else if (propName.equals(TABLE_GENMENUITEM))
//	            setGenMenuItem(Integer.parseInt(value.toString()));
//	        else if (propName.equals(TABLE_DESCTIPTION))
//	            setDesc(value.toString());
//	        else
	            super.setPropertyValue(propName, value);
	    }

	    /**
	     * @param size,location
	     */
	    public Table() 
	    {
	        size.width = 100;
	        size.height = 150;
	        location.x = 20;
	        location.y = 20;
	    }

	  

		public String toString() 
	    {
	        if (getName().trim() != "") 
	        { 
	            return getName();
	        } else
	            return Messages.getString("Table.TableLabel") + getId(); 
	    }

//	    /**
//	     * @return Returns the Field_Type id Enumeration.
//	     */
//	    public String getField_Type()
//	    {
//	        return "Enumeration"; 
//	    }

	    /**
	     * @return Returns the deletdFlag.
	     */
	    public int getDeletedFlag() 
	    {
	        return deletedFlag;
	    }

	    /**
	     * @param deleteFlag
	     *            The deleteFlag to set.
	     */
	    public void setDeletedFlag(int deletedFlag) 
	    {
	        this.deletedFlag = deletedFlag;
	    }



	    /**
	     * @return Returns the genMenuItem.
	     */
	    public int getGenMenuItem() 
	    {
	        return genMenuItem;
	    }

	    /**
	     * @param genMenuItem
	     *            The genMenuItem to set.
	     */
	    public void setGenMenuItem(int genMenuItem) 
	    {
	        this.genMenuItem = genMenuItem;
	        this.firePropertyChange(TABLE_GENMENUITEM,null,""+genMenuItem);
	    }

	    public List getSubTabel()
	    {
	    	return this.subTable;
	    }
//	    public Table getSubChildTabel()
//	    {
//	    	return this.subChildTabel;
//	    }
//	    public void setSubChildTabel(Table subChildTabel)
//	    {
//	    	this.subChildTabel = subChildTabel;
//	    }
}
