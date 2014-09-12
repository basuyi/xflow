package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * @author lili
 * @version 1.1.0
 * @explain defind the field's properties,it extends Element,it is the parent of StringField,EnumField,FloatField and DataField.
 */

public class CommonField extends Element
{


	public static final String FIELD_DEFVALUE = "field_DefValue"; 

    public static final String MIN_VALUE = "minvalue";

    public static final String MAX_VALUE = "maxvalue"; 

    public static final String FIELD_MUSTFILL = "field_MustBeFill"; 

    public static final String FIELD_CANBEQUERY = "field_CanBeQuery"; 

    public static final String FIELD_LISTABLE = "field_Listable"; 

    public static String TABLE_VIEWPRIVILEGE = "table_view_privilege"; 

    public static String TABLE_EDITPRIVILEGE = "table_edit_privilege"; 

    public static String TABLE_ISBIZKEY = "table_is_biz_key"; 
    
   
    public static String TABLE_ISPREKEY = "table_is_Pre_Key";

    private String minValue = "", maxValue = "";
    
   

    private int isuserField = 0; // 该字段是否是用户定义  1为用户定义，0为非用户定义 
    private int mustBeFilled = 0;  //0 stand for no,1 stand for yes

    private int canBeQuery = 0;    //该字段是否可作为查询条件

    private int listable = 0;      //该项查询后是否在页面显示

    private int isBizKey = 0;      //该字段是否是主键
    
    private int isPreKey = 0;      //判断字段是否是外犍

    private Integer viewPrivilege = new Integer(0);

    private Integer editPrivilege = new Integer(0);
    
    private String fieldTypeID ;

    /**
     * 
     * @return return the isuserField
     */
    public int getIsuserfield()
    {
    	return isuserField;
    }
    /**
     * 
     * @param isuserfield
     *        isuserfield to set
     */
    public void setIsuserfield(int isuserfield)
    {
    	this.isuserField = isuserfield;
    }
    /**
     * @return Returns the editPrivilege.
     */
    public Integer getEditPrivilege() 
    {
        return editPrivilege;
    }

    /**
     * @param editPrivilege
     *            The editPrivilege to set.
     */
    public void setEditPrivilege(Integer editPrivilege) 
    {
        this.editPrivilege = editPrivilege;
    }

    /**
     * @return Returns the viewPrivilege.
     */
    public Integer getViewPrivilege() 
    {
        return viewPrivilege;
    }

    /**
     * @param viewPrivilege
     *            The viewPrivilege to set.
     */
    public void setViewPrivilege(Integer viewPrivilege) 
    {
        this.viewPrivilege = viewPrivilege;
    }

    protected static List propDescriptors = new ArrayList();

    protected List getRangePropsList() 
    {
        List rangePropsList = new ArrayList();

        return rangePropsList;
    }

    /**
     * @explain: on condition to fill the Property
     */
    protected void filterPropertyDescriptor(List lPropertyDescriptorList) 
    {
        List list = new ArrayList();
        //if it isn't maintable, and doesn't hide
        if(getIsBizKey() == 1)
        {
        	setMustBeFilled(1);
        }
        if (getMustBeFilled() == 1) 
        {
            setIsHidden(new Integer(0));
            setIsReadOnly(new Integer(0));
            for (Iterator iterator = lPropertyDescriptorList.iterator(); iterator.hasNext();) 
            {
                Object o = iterator.next();
                if (o instanceof ComboBoxPropertyDescriptor) 
                {
                    if (((ComboBoxPropertyDescriptor) o).getId() == ID_ISHIDDEN) 
                    {
                        list.add(o);
                    }
                }
                if (o instanceof ComboBoxPropertyDescriptor) {
                    if (((ComboBoxPropertyDescriptor) o).getId() == ID_ISREADONLY) 
                    {
                        list.add(o);
                    }
                }
            }
        }

        if (getIsHidden().intValue() == 1) 
        {
            setMustBeFilled(0);
            setCanBeQuery(0);
            setListable(0);
            for (Iterator iterator = lPropertyDescriptorList.iterator(); iterator.hasNext();) 
            {
                Object o = iterator.next();
                if (o instanceof ComboBoxPropertyDescriptor) 
                {
                    if (((ComboBoxPropertyDescriptor) o).getId() == FIELD_MUSTFILL) 
                    {
                        list.add(o);
                    }
                }
                if (o instanceof ComboBoxPropertyDescriptor) 
                {
                    if (((ComboBoxPropertyDescriptor) o).getId() == FIELD_CANBEQUERY) 
                    {
                        list.add(o);
                    }
                }
                if(o instanceof ComboBoxPropertyDescriptor)
                {
                	if(((ComboBoxPropertyDescriptor)o).getId() == FIELD_LISTABLE)
                	{
                		list.add(o);
                	}
                }
            }
        }
        if (getIsReadOnly().intValue() == 1) 
        {
            setMustBeFilled(0);
            for (Iterator iterator = lPropertyDescriptorList.iterator(); iterator.hasNext();)
            {
                Object o = iterator.next();
                if (o instanceof ComboBoxPropertyDescriptor) 
                {
                    if (((ComboBoxPropertyDescriptor) o).getId() == FIELD_MUSTFILL) 
                    {
                        list.add(o);
                    }
                }
                				if(o instanceof ComboBoxPropertyDescriptor){
                					if(((ComboBoxPropertyDescriptor)o).getId() ==
                 FIELD_CANBEQUERY){
                						list.add(o);
                					}
                				}
            }
        }
       
    
        lPropertyDescriptorList.removeAll(list);

    }

    /**
     * @return Returns the propertyDescriptors.
     */
    public IPropertyDescriptor[] getPropertyDescriptors() 
    {

        return (IPropertyDescriptor[]) propDescriptors.toArray(new IPropertyDescriptor[propDescriptors.size()]);
    }

    /**
     * @return Returns the PropertyValue.
     */
    public Object getPropertyValue(Object propName) 
    {

            return super.getPropertyValue(propName);
    }

    /**
     * @param PropertyValue
     *             PropertyValue to reset。
     */
    public void resetPropertyValue(Object id) 
    {

            super.resetPropertyValue(id);
    }

    /**
     * @param PropertyValue
     *             Property to be set。
     */
    public void setPropertyValue(Object id, Object value) 
    {
            super.setPropertyValue(id, value);

    }

    /**
     * @return Returns the NewID.
     */
    protected String getNewID() 
    {
        return "";

    }

    /**
     * @return Returns the Value.
     */
    public int getValue() 
    {
        return 1;
    }

    /**
     * @return Returns the canBeQuery。
     */
    public int getCanBeQuery() 
    {
        return canBeQuery;
    }

    /**
     * @param canBeQuery
     *             canBeQuery to be set。
     */
    public void setCanBeQuery(int canBeQuery) 
    {
        this.canBeQuery = canBeQuery;
    }

    /**
     * @return return listable。
     */
    public int getListable() 
    {
        return listable;
    }

    /**
     * @param listable
     *             listable to be set。
     */
    public void setListable(int listable) 
    {
        this.listable = listable;
    }

    /**
     * @return return the mustBeFilled。
     */
    public int getMustBeFilled() 
    {
        return mustBeFilled;
    }

    /**
     * @param mustBeFilled
     *          mustBeFilled to be set。
     */
    public void setMustBeFilled(int mustBeFilled) 
    {
        this.mustBeFilled = mustBeFilled;
    }

    /**
     * @return Returns the maxValue.
     */
    public String getMaxValue() 
    {
     
        return maxValue;
    }

    /**
     * @param maxValue
     *         maxValue to set.
     */
    public void setMaxValue(String maxValue) 
    {
            this.maxValue = maxValue;
            this.firePropertyChange(MAX_VALUE ,null,maxValue);
    }

    /**
     * @return Returns the minValue.
     */
    public String getMinValue() 
    {
    
        return minValue;
    }

    /**
     * @param minValue
     *         minValue to set.
     */
    public void setMinValue(String minValue) 
    {
            this.minValue = minValue;
            this.firePropertyChange(MIN_VALUE,null,minValue);
    }

    /**
     * @return Returns the isBizKey.
     */
    public int getIsBizKey() 
    {
        return isBizKey;
    }

    /**
     * @param isBizKey
     *            The isBizKey to set.
     */
    public void setIsBizKey(int isBizKey) 
    {
        this.isBizKey = isBizKey;
    }
    
    public int getIsPreKey()
    {
    	return isPreKey;
    }
    
    public void setIsPreKey(int isPreKey)  
    {
     
    	this.isPreKey = isPreKey;
    }
    

    /**
     * @return Returns the IconImage.
     */
	public Image getIconImage() {
		return null;
	}

	 /**
     * @return Returns the viewPrivilege.
     */
	public String getField_Type() {
		return null;
	}

	 /**
     * @return Returns the FieldTypeID.
     */
	public int getFieldTypeID() {
		return (new Integer(this.fieldTypeID)).intValue();
	}

	public void setFieldTypeID(String fieldTypeID){
		this.fieldTypeID = fieldTypeID;
	}
	 /**
     * @return Returns the FieldTypeLength.
     */
	public int getFieldTypeLength() {
		return 0;
	}

	 /**
     * @return Returns the FieldTypeScale.
     */
	public int getFieldTypeScale() {
		return 0;
	}


	

}
