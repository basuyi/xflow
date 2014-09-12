package net.ms.designer.editors.enumcomponentdetail.model;

import java.io.Serializable;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class Table extends Element implements IPropertySource,Serializable
{
    
    public static final String ID_NAME = "__id__name";
    
    public static final String ID_LOCATION = "__id__point";
    
    protected Point location = new Point(0,0);
    
    protected String tableName = "";
//    international name
    protected String iname = "";
//    description
    protected String desc = "";
    
    transient protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[]
    {
            new TextPropertyDescriptor(ID_NAME,"Name"),
            new PropertyDescriptor(ID_LOCATION,"Location")
    };

    public Point getLocation() 
    {
        return location;
    }

    public void setLocation(Point location) 
    {
        Point old = this.location;
        this.location = location;
        this.fireFigurePropertyChange(old,this.location);
    }

    public String getTableName() 
    {
        return tableName;
    }

    public void setTableName(String tableName) 
    {
        String old = this.tableName;
        this.tableName = tableName;
        this.fireFigurePropertyChange(old,this.tableName);
    }
    
    public String getIname() 
    {
        return iname;
    }

    public void setIname(String iname) 
    {
        this.iname = iname;
    }
    
    public String getDesc() 
    {
        return desc;
    }

    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public Object getEditableValue() 
    {
        return null;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() 
    {
        return this.descriptors;
    }

    public Object getPropertyValue(Object id) 
    {
        if(id.equals(ID_NAME)) return this.getTableName();
//        if(id.equals(ID_LOCATION)) return new PointPropertySource(this.getLocation());
        return null;
    }

    public boolean isPropertySet(Object id) 
    {
        return true;
    }

    public void resetPropertyValue(Object id) 
    {
    }

    public void setPropertyValue(Object id, Object value) 
    {
        if(id.equals(ID_NAME)) 
        	setTableName((String)value);
        if(id.equals(ID_LOCATION)) 
        	this.setLocation((Point)value);
    }
}