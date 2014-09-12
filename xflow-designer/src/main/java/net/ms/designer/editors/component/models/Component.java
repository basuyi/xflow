package net.ms.designer.editors.component.models;

import net.ms.designer.editors.component.ComponentImages;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;






public  class Component extends Element
{
	
//	static string variables mapping to the class properties 
	final public static String prop_Name = "Name";
	final public static String prop_Location = "Location";
	final public static String prop_Visible = "Visible";
	public static final String ACTIVITY_SIZE = "ACTIVITY SIZE"; // the activity's size
	protected Dimension size = new Dimension(-1, -1);  
//	properties
	String componentID = "";
	String name = "";					//EnumComponent Name
	String iname = "";
	String desc = "";
	Point location = new Point(0,0);	//the location of the figure
	boolean visible = true;				//whether the figure is visible
	
//	private static Image LOGIC_ICON = ComponentImages.getImage(ComponentImages.ENDNODE);
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
		return this.iname;
	}
	public void setIname(String iname)
	{
		this.iname = iname;
	}
	
	public String getDesc()
	{
		return this.desc;
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
	public String getComponentID()
	{
		return this.componentID;
	}
	public void setComponentID(String componentID)
	{
		this.componentID = componentID;
	}
	//----------lili
	
//	public Image getIcon() 
//	{
//		return getIconImage();
//	}
//
//	public Image getIconImage()
//	{
//		// TODO Auto-generated method stub
//		return LOGIC_ICON;
//	}
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
}