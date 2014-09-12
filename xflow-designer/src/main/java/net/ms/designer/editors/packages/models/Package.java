package net.ms.designer.editors.packages.models;

import net.ms.designer.core.MsProject;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;




public class Package extends Element
{
//	static string variables mapping to the class properties 
	final public static String prop_Name = "Name";
	final public static String prop_Location = "Location";
	final public static String prop_Visible = "Visible";
//	properties
	String packageID = "";
	String name = "";					//EnumComponent Name
	String iname = "";
	String desc = "";
	Point location = new Point(0,0);	//the location of the figure
	boolean visible = true;				//whether the figure is visible
	
	public static final String ACTIVITY_SIZE = "ACTIVITY SIZE"; // the activity's size
	protected Dimension size = new Dimension(-1, -1);  
	MsProject project;
	
	public MsProject getProject()
    {
    	return this.project;
    }
    public void setProject(MsProject project)
    {
    	this.project = project;
    }
	
    public String getPackageID()
    {
    	return this.packageID;
    }
    public void setPackageID(String packageID)
    {
    	this.packageID = packageID;
    }
    
//	getter and setter methods
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
//		if(this.name.equals(name))
//			return;
		this.name = name;
		this.firePropertyChange(prop_Name,null,name);
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
	
	public Point getLocation()
	{
		return this.location;
	}
	public void setLocation(Point location)
	{
		if(this.location.equals(location))
			return;
		this.location = location;
		this.firePropertyChange(prop_Location,null,name);
	}
	public boolean getVisible()
	{
		return this.visible;
	}
	public void setVisible(boolean visible)
	{
		if(this.visible == visible)
			return;
		this.visible = visible;
		this.firePropertyChange(prop_Visible,null,Boolean.valueOf(visible));
	}
	
	//----------lili start
	
	/**
	 * set size
	 */
	public void setSize(Dimension size)
	{
		if (this.size.equals(size))
			return;
		this.size = size;
		firePropertyChange(ACTIVITY_SIZE, null, size);
	}

	/**
	 * get size
	 */
	public Dimension getSize()
	{
		return size;
	}
	//-----------lili end
}