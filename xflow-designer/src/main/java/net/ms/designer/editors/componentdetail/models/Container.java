package net.ms.designer.editors.componentdetail.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ms.designer.core.MsProject;
import net.ms.designer.editors.componentdetail.models.Messages;
import net.ms.designer.editors.componentdetail.models.TemplateConstants;

import org.eclipse.swt.graphics.Image;


/**
 * @author lili
 * @version 1.1.0
 * @explain defind the Container's properties,it extends Element
 */
public class Container extends Element
{

	static final long serialVersionUID = 1;

	public static String ID_ROUTER = "router"; 

	public static Integer ROUTER_MANUAL = new Integer(0);    //automatic select router

	public static Integer ROUTER_MANHATTAN = new Integer(1);  //select router by hand

	public static Integer ROUTER_SHORTEST_PATH = new Integer(2);  // the shortest router

	private static Image LOGIC_ICON = new Image(null, TemplateConstants.class  //define the path of image
			.getResourceAsStream("icons/Component16.gif")); 

	protected List children = new ArrayList();

	protected Integer connectionRouter = null;

	private double zoom = 1.0;
	
	private int iFieldCount = 0; //the element count of the container
	
	private String creatorName = "";
	
	private String createDate = "";
	
	private String version = "";
	
	private boolean mainTable = false; 
	
//	private String componentName = null;    //the component's name
	private String packageName = null;    //the package's name
	private String projectName = null;    //the project's name
	private int ChildTableCount = 0;
	private int ChildTableCount1 = 0;
	private MsProject project;
	
	public int flag = 0;
	
	 /**
     * @return  iFieldCount¡£
     */
	public  int getFieldCount() 
	{
		return iFieldCount;
	}
	
	 /**
     * @param iFieldCount
     *         
     */
	public  void setFieldCount(int iFieldCount) 
	{
		this.iFieldCount = iFieldCount;
	}
	public int getChildTableCount()
	{
		return this.ChildTableCount;
	}
	public void setChildTableCount(int ChildTableCount)
	{
		
		this.ChildTableCount = ChildTableCount;
	}
	 /**
     * @param iFieldCount which had been plused 1
     *         
     */
	public int setFieldCountPlus()
	{
		iFieldCount++;
		return iFieldCount;
	}	
	public int setChildTableCountPlus()
	{
		ChildTableCount++;
		return ChildTableCount;
	}
	public MsProject getProject()
	{
		return this.project;
	}
	public void setProject(MsProject project)
	{
		this.project = project;
	}
	public int getChildTableCount1()
	{
		return this.ChildTableCount1;
	}
	public void setChildTableCount1(int ChildTableCount1)
	{
		this.ChildTableCount1 = ChildTableCount1;
	}
	
	 /**
     * @return  CreateDate¡£
     */
	public String getCreateDate() 
	{
		return createDate;
	}
	
	 /**
     * @param creatDate
     *         
     */
	public void setCreateDate(String createDate) 
	{
		this.createDate = createDate;
	}
	 /**
     * @return  creatorName¡£
     */
	public String getCreatorName() 
	{
		return creatorName;
	}
	
	 /**
     * @param creatorName
     *         
     */
	public void setCreatorName(String creatorName) 
	{
		this.creatorName = creatorName;
	}
	
	 /**
     * @return  version¡£
     */
	public String getVersion() 
	{
		return version;
	}
	
	 /**
     * @param version
     *         
     */
	public void setVersion(String version) 
	{
		this.version = version;
	}
	

	 /**
     * @param size,location
     * @value width,heigth,x,y       
     */
	public Container() 
	{
		size.width = 100;
		size.height = 100;
		location.x = 20;
		location.y = 20;
	}
	
	 /**
     * @return  children¡£
     */
	public List getChildren()
	{
		return children;
	}
	
	 /**
     * @explain the children element which is placed on bottom should use Field,but not Element
     */
	public void addChild(Element child) 
	{
		addChild(child,-1);
	}
	
	public void addChild(Element child, int index) 
	{
		if(index == -1)
		{
			getChildren().add(child);
		}
		else
		{
			getChildren().add(index,child);
			
		}
		//System.out.println("Container.addChild");
//		if(!child.getField_Type().equals("Autonum"))
//		{
			this.fireStructureChange(Element.CHILDREN,children);
			this.fireChildRemoved(Element.CHILDREN,children);
//		}
	}
		

	 /**
     * @return  connectionRouter¡£
     */
	public Integer getConnectionRouter() 
	{
		if (connectionRouter == null)
			connectionRouter = ROUTER_MANUAL;
		return connectionRouter;
	}

	 /**
     * @return  LOGIC_ICON¡£
     */
	public Image getIconImage() 
	{
		return LOGIC_ICON;
	}

	 /**
     * @return  newID¡£
     */
	protected String getNewID() 
	{
		return Integer.toString(count++);
	}

	 /**
     * @return  zoom¡£
     */
	public double getZoom() 
	{
		return zoom;
	}

	 /**
     * @return  PropertyValue ¡£
     */
	public Object getPropertyValue(Object propName) 
	{
		if (propName.equals(ID_ROUTER))
			return connectionRouter;
		return super.getPropertyValue(propName);
	}

	private void readObject(java.io.ObjectInputStream s) throws IOException,ClassNotFoundException 
	{
		s.defaultReadObject();
	}

	 /**
     * @explain remove the child,it should minus 1 and remove the child form the ArrayList Children()
     *         
     */
	public void removeChild(Element child)
	{
	    setFieldCount(getFieldCount() - 1);
		children.remove(child);
		this.fireChildRemoved(Element.CHILDREN,child);
//		fireChildRemoved(CHILDREN, child);
	}

	 /**
     * @param connectionRouter
     *         
     */
	public void setConnectionRouter(Integer router) 
	{
		Integer oldConnectionRouter = connectionRouter;
		connectionRouter = router;
		firePropertyChange(ID_ROUTER, oldConnectionRouter, connectionRouter);
	}

	 /**
     * @param setPropertyValue(id,value)
     *         
     */
	public void setPropertyValue(Object id, Object value) 
	{
		if (ID_ROUTER.equals(id))
			setConnectionRouter((Integer) value);
		else
			super.setPropertyValue(id, value);
	}

	 /**
     * @param zoom
     *         
     */
	public void setZoom(double zoom) 
	{
		this.zoom = zoom;
	}

	
	public String toString() 
	{
		return Messages.getString(""); 
	}

	/**
	 * @see net.ms.designer.editors.componentdetail.common.Element#getField_Type()
	 */
	public String getField_Type() 
	{
		return "Container"; 
	}
	/**
	 * @return hasMainTable¡£
	 */
	public boolean hasMainTable() 
	{
		return mainTable;
	}
	/**
	 * @param  hasMainTable¡£
	 */
	public void setHasMainTable(boolean hasMainTable) 
	{
		this.mainTable = hasMainTable;
	}

	/**
	 * @return null
	 */
	public ComponentTable getMainTable() 
	{
		Container parent = this;
	    if(parent.getParent() != null){
	        parent = parent.getParent();
	    }
		List children = parent.getChildren();
		for(int i=0;i<children.size();i++){
			if(children.get(i) instanceof ComponentTable)
				return (ComponentTable)children.get(i);
		}
		return null;
	  
	}
	
	public FlowField getFlowField()
	{
		Container parent = this;
		 if(parent.getParent() != null){
		        parent = parent.getParent();
		    }
		 List children1 = parent.getChildren();
		 for(int i=0;i<children1.size();i++){
				if(children1.get(i) instanceof FlowField)
					return (FlowField)children1.get(i);
			}
			return null;
	}
//	public void setComponentName(String componentName){
//		this.componentName = componentName;
//	}
//	
//	public String getComponentName(){
//		return this.componentName;
//	}
	
	public void setPackageName(String packageName){
		this.packageName = packageName;
	}
	
	public String getPackageName(){
		return this.packageName;
	}
	
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
	
	public String getProjectName(){
		return this.projectName;
	}
	

	/* £¨·Ç Javadoc£©
	 * @net.ms.designer.editors.componentdetail.common.Field#getFieldTypeID()
	 */
	public int getFieldTypeID() 
	{
		return 0;
	}

	/* £¨·Ç Javadoc£©
	 * @net.ms.designer.editors.componentdetail.common.KCGField#getFieldTypeLength()
	 */
	public int getFieldTypeLength() 
	{
		return 0;
	}

	/* £¨·Ç Javadoc£©
	 * @net.ms.designer.editors.componentdetail.common.KCGField#getFieldTypeScale()
	 */
	public int getFieldTypeScale() 
	{
		return 0;
	}
	
	public void setMainTable(boolean mainTable)
	{
		this.mainTable = mainTable;
	}
}
