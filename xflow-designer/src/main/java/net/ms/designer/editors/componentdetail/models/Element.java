package net.ms.designer.editors.componentdetail.models;

import java.util.Vector;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * @author lili
 * 
 * 元素基类
 */

public abstract class Element extends AbstractElement
{
	
		private LogicGuide verticalGuide, horizontalGuide;  //defind vertical wire and horizontal wire

	    private String id = null;

	    private String name = "";
	    
	    private String iname = "";
	    
	    private String desc = "";

	    private Integer isHidden;

	    private Integer isReadOnly;
	    
	  

	    protected Point location = new Point(0, 0); //defind location

	    protected Dimension size = new Dimension(-1,-1);  //defind dimension
	    
	    public static final String DEFAULT_VALUE = "defaultValue";

	    public static final String ID_NAME = "Name"; 
	    
	    public static final String ID_INAME = "IName";

	    public static final String ID_FIELD_LABEL = "fieldLabel";

	
	    public static final String ID_SIZE = "size"; 

	    public static final String ID_LOCATION = "location"; 

	  
	    public static final String ID_ISHIDDEN = "ishidden"; 

	    public static final String ID_ISREADONLY = "isreadonly"; 

	    public static final String FIELD_TYPE = "field_Type"; 
	    
	    public static final String ID = "id";
	    
	    public static final String PARENTISMAINTABLE = "bParentIsMainTable";

	    protected static int count = 0;

	    private boolean bParentIsMainTable = false;  //judge the parent is MainTable(ChildTable) or not
	    
	    private Container parent;

	    /**
	     * @return  location。
	     */
	    public Point getLocation() 
	    {
	        return location;
	    }

	    /**
	     * @param location
	     *         
	     */
	    public void setLocation(Point p) 
	    {
//	        if (location.equals(p))
//	            return;
	      this.location = p;
	        this.firePropertyChange(ID_LOCATION,null,location);
	      
	    }

	    /**
	     * @return  size。
	     */
	    public Dimension getSize() 
	    {
	        return size;
	        
	    }

	    /**
	     * @param size
	     *          
	     */
	    public void setSize(Dimension d) 
	    {
//	        if (size.equals(d))
//	            return;
	       this.size = d;
	       this.firePropertyChange(ID_SIZE,null,d);
	    }

	    /**
	     *  @explain: construct of Element
	     */
	    public Element() 
	    {
	    	
	    }

	    /**
	     * 
	     * @return IconImage
	     */
	    public Image getIcon() 
	    {
	        return getIconImage();
	    }

	    abstract public Image getIconImage();

	    protected abstract String getNewID();

	    abstract public String getField_Type();

	    abstract public int getFieldTypeID();
	   

	    abstract public int getFieldTypeLength();

	    abstract public int getFieldTypeScale();

	    /**
	     * @return  fieldName。
	     */
	    public String getName() 
	    {
	        return name;
	    }

	    /**
	     * @param fieldName
	     *          
	     */
	    public void setName(String name) 
	    {

//	        if (name == null) 
//	        {
//	            return;
//	        }
//	        if (name.equals(this.getName())) 
//	        {
//	            return;
//	        }
	            this.name = name;
	            this.firePropertyChange(ID_NAME,null,name);
	    }
	    
	    
	    /**
	     * @return  fieldIName。
	     */
	    public String getIName() 
	    {
//	        return iname==null?"":iname;
	    	return this.iname;
	    	
	    }

	    /**
	     * @param fieldIName
	     *          
	     */
	    public void setIName(String iname) 
	    {

//	        if (iname == null) 
//	        {
//	            return;
//	        }
//	        if (iname.equals(this.getIName())) 
//	        {
//	            return;
//	        }
	            this.iname = iname;
	            this.firePropertyChange(ID_INAME,null,iname);
	    }
	    

	    /**
	     * @return Returns the desc.
	     */
	    public String getDesc() 
	    {
	        if (desc == null)
	            desc = "";
	        return desc;
	    }

	    /**
	     * @param desc
	     *            The desc to set.
	     */
	    public void setDesc(String desc) 
	    {
	        this.desc = desc;
	    }
	    
	    
	    /**
	     * @return 返回 id。
	     */
	    public String getId() 
	    {
	        return this.id;
	 
	    }

	    /**
	     * @param id
	     *            
	     */
	    public void setId(String id) 
	    {
	        this.id = id;
	        this.firePropertyChange(ID,null,""+id);
	    }

	    /*
	     * （非 Javadoc）
	     * 
	     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	     */
	    public IPropertyDescriptor[] getPropertyDescriptors() 
	    {
//	        return (IPropertyDescriptor[]) descriptors.toArray(new IPropertyDescriptor[descriptors.size()]);
	    	return null;
	    }
	    /*
	     * （非 Javadoc）
	     * 
	     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	     */
	    public Object getPropertyValue(Object propName) 
	    {
			return propName;
//	        if (FIELD_TYPE.equals(propName))
//	            return getField_Type();
//	        else if (ID_NAME.equals(propName))
//	            return this.getName();
//	        else if (ID_ISHIDDEN.equals(propName))
//	            return getIsHidden();
//	        else if (ID_ISREADONLY.equals(propName))
//	            return getIsReadOnly();
//	        return super.getPropertyValue(propName);
	    }

	    /*
	     * （非 Javadoc）
	     * 
	     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
	     *      java.lang.Object)
	     */
	    public void setPropertyValue(Object propName, Object value) 
	    {
//	        if (ID_NAME.equals(propName))
//	            this.setName((String) value);
//	        else if (ID_SIZE.equals(id))
//	            setSize((Dimension) value);
//	        else if (ID_LOCATION.equals(id))
//	            setLocation((Point) value);
//	        else if (ID_ISHIDDEN.equals(propName))
//	            setIsHidden((Integer) value);
//	        else if (ID_ISREADONLY.equals(propName))
//	            setIsReadOnly((Integer) value);
//	        super.setPropertyValue(propName, value);
	    }

	    /*
	     * （非 Javadoc）
	     * 
	     * @see java.lang.Object#toString()
	     */
	    public String toString() 
	    {
			return null;
	    }

	    public LogicGuide getHorizontalGuide() 
	    {
	        return horizontalGuide;
	    }

	    public void setHorizontalGuide(LogicGuide horizontalGuide) 
	    {
	        this.horizontalGuide = horizontalGuide;
	       
	    }

	    public LogicGuide getVerticalGuide() 
	    {
	        return verticalGuide;
	    }

	    public void setVerticalGuide(LogicGuide verticalGuide) 
	    {
	        this.verticalGuide = verticalGuide;
	    }

	    protected Vector inputs = new Vector();

	    protected Vector outputs = new Vector();

	    public Vector getTargetConnections() 
	    {
	        Vector v = (Vector) inputs.clone();
	        return v;
	    }

	    public Vector getSourceConnections() 
	    {
	        Vector v = (Vector) outputs.clone();
	        return v;
	    }

	    public Container getParent()
	    {
	    	return parent;
	    }
	    
	    /**
	     * @param wire
	     */
	    public void connectOutput(Wire wire) 
	    {
	        outputs.add(wire);
	        fireStructureChange(OUTPUTS, wire);
	    }

	    /**
	     * @param wire
	     */
	    public void connectInput(Wire wire) 
	    {
	        inputs.add(wire);
	        fireStructureChange(INPUTS, wire);
	    }

	    /**
	     * @param wire
	     */
	    public void disconnectOutput(Wire wire) 
	    {
	        if (outputs.contains(wire))
	            outputs.remove(wire);
	        fireStructureChange(OUTPUTS, wire);
	    }

	    /**
	     * @param wire
	     */
	    public void disconnectInput(Wire wire) 
	    {
	        if (inputs.contains(wire))
	            inputs.remove(wire);
	        fireStructureChange(INPUTS, wire);
	    }

	    /**
	     * @return
	     */
	    public Vector getConnections() 
	    {
	        Vector v = (Vector) inputs.clone();
	        v.addAll((Vector) outputs.clone());
	        return v;
	    }

	    public static final String INPUTS = "inputs"; 

	    public static final String OUTPUTS = "outputs"; 

	    public Integer getIsHidden() 
	    {
	        if (isHidden == null)
	            isHidden = new Integer(0);
	        return isHidden;
	    }

	    public void setIsHidden(Integer isHidden) 
	    {
	        this.isHidden = isHidden;
	    }

	    public Integer getIsReadOnly() 
	    {
//	        if (isReadOnly == null)
//	            isReadOnly = new Integer(0);
	        return isReadOnly;
	    }

	    public void setIsReadOnly(Integer isReadOnly) 
	    {
	        this.isReadOnly = isReadOnly;
	        this.firePropertyChange(ID_ISREADONLY,null,isReadOnly);
	    }

	    public boolean getParentIsMainTable() 
	    {
	        return bParentIsMainTable;
	    }

	    public void setParentIsMainTable(boolean isMaintable) 
	    {
	        bParentIsMainTable = isMaintable;
	        this.firePropertyChange(PARENTISMAINTABLE,null,""+isMaintable);
	    }

		public Vector getInputs() {
			return inputs;
		}

		public void setInputs(Vector inputs) {
			this.inputs = inputs;
	        this.firePropertyChange(INPUTS,null,""+inputs);
		}

		public Vector getOutputs() {
			return outputs;
		}

		public void setOutputs(Vector outputs) {
			this.outputs = outputs;
	        this.firePropertyChange(OUTPUTS,null,""+outputs);
		}
	    
	
}
