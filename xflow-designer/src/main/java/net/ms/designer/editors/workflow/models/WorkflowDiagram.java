/**
 * @author liuchunxia
 * 
 * the workflow diagram
 * extends workflowsubpart
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import net.ms.designer.core.MsProject;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.WorkflowImages;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;


public class WorkflowDiagram extends WorkflowSubPart 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the diagram's status
	 */
	private String status = Constants.DRAFT;   //the diagram's status
	
	/**
	 * the diagram's name
	 */
	private String wfName = null;    //the diagram's name
	/**
	 * this diagram's desc
	 */
	private String wfDesc = null;
	/**
	 * all of the nodes in the diagram
	 */
	private List children = new ArrayList();    //all of the nodes in the diagram
	
	/**
	 * all of the parameters in the diagram
	 */
	private List paraList = new ArrayList();    //all of the parameters in the diagram
	
	/**
	 * all applications of this project
	 */
//	private List applicationList = new ArrayList();
	
	private boolean rulersVisibility = false;
	private String[] priorities = new String[] { "1", "2", "3", "4", "5" } ;  //all of priority when running;
	private String priority = null;
	private String createTime = null;
	private String beginTime = null;
	private String endTime = null;
	private String infor1 = "";
	private String infor2 = "";
	private String allInfor = "";
	private String opEntity = "";  //业务对象
	
	private String connectionType = null;     //the connection type
	private static int count;  //diagram's number
	public  int NODEID = 0;
	public  int WIREID = 0;
	public String wfId = null;
	private boolean isSubflow = false;
	
	private MsProject project ;
//	private Container container;
	
//	private String componentName = null;    //the component's name
//	private String packageName = null;    //the package's name
//	private String projectName = null;    //the project's name
	
	public static final String PROP_NAME = "workflowName";
	public static final String PROP_DESC = "workflowDesc";
	public static final String PROP_CHILDREN = "children";
	public static final String PROP_PARALIST = "paraList";
	public static final String APPLICATIONLIST = "applicationList";
	public static final String STATUS = "STATUS";
	public static final String LEFTRULER = "leftRuler";
	public static final String TOPRULER = "topRuler";
	public static final String CONNECTIONTYPE = "connectionType";
	public static final String PRIORITIES = "priorities";
	public static final String PRIORITY = "priority";
	public static final String CREATETIME = "createTime";
	public static final String BEGINTIME = "beginTime";
	public static final String ENDTIME = "endTime";
	public static final String PROJECT = "project";
	public static final String INFOR= "infor";
	public static final String OPENTITY = "opEntity";
	public static final String WORKFLOWID = "workflow id";
	public static final String ISSUBFLOW = "isSubflow";
//	public static final String CONTAINER = "container";
//	private static final String RULERS_VISIBILITY = "rulersVisibility";
	
	private static Image LOGIC_ICON = WorkflowImages.getImage(WorkflowImages.ENDNODE);
	
	private static final String[] PROPERTIES = new String[]
	            {
				// label
				"creationdate", Messages.getString("PropertyActivityDialog.createDate"),
				"validfromdate", Messages.getString("PropertyActivityDialog.startDate"),
				"validtodate", Messages.getString("PropertyActivityDialog.endDate"),
				"revisionnumber", Messages.getString("PropertyActivityDialog.version"), 
				"wfProcessDefStatus", Messages.getString("PropertyActivityDialog.state"),
				// edit text
				"name", Messages.getString("PropertyActivityDialog.name"), 
				"description", Messages.getString("PropertyActivityDialog.property.description"), 
				"documentation", Messages.getString("PropertyActivityDialog.documentation"), 
				"icon", Messages.getString("PropertyActivityDialog.icon"),
				// combo
				"priority", Messages.getString("PropertyActivityDialog.priority")
				};
	
	public WorkflowDiagram() {
		size.width = 100;
		size.height = 100;
//		createRulers();
//		this.editor = editor;
	}

//	private transient WorkflowEditor editor;
//
//	public WorkflowEditor getEditor() {
//		return editor;
//	}
	
	private IPropertyDescriptor[] getPropertyDescriptor() 
	{
		int labelCount = 5, editCount = 4;
		IPropertyDescriptor[] properties = new IPropertyDescriptor[PROPERTIES.length / 2];
		for (int i = 0; i < properties.length; i++) 
		{
			if (i < labelCount) 
			{
				properties[i] = new PropertyDescriptor(PROPERTIES[2 * i], PROPERTIES[2 * i + 1]);
			} 
			else if (i >= labelCount && i < labelCount + editCount) 
			{
				properties[i] = new TextPropertyDescriptor(PROPERTIES[2 * i], PROPERTIES[2 * i + 1]);
			} 
			else if (PROPERTIES[2 * i].equals("priority")) 
			{
				properties[i] = new ComboBoxPropertyDescriptor(PROPERTIES[2 * i], PROPERTIES[2 * i + 1], new String[] { "1", "2", "3", "4", "5" });
			}
		}
		return properties;
	}
	
	public void setProcessStatus(String status) 
	{
		setNodeStatus(status);
		/**
		 * Comments by Xiaofeng for you can set element's status directly,
		 * otherwise all the node will be newed again.
		 */
		List l = this.getChildren();
		for (Iterator i = l.iterator(); i.hasNext();) 
		{
			WorkflowElement listener = (WorkflowElement) i.next();
			listener.setNodeStatus(status);
		}
		this.firePropertyChange(STATUS,null,status);
	}
	
	
	public void setChildren(List children)
	{
		this.children = children;
		this.firePropertyChange(PROP_CHILDREN,null,children);
	}
	
	public List getChildren() 
	{
		return children;
	}
	
	public void setWfName(String name)
	{
		this.wfName = name;
		this.firePropertyChange(PROP_NAME,null,name);
	}
	
	public String getWfName()
	{
		return this.wfName;
	}
	
	public void setParaList(List paraList)
	{
		this.paraList = paraList;
		this.firePropertyChange(PROP_PARALIST,null,paraList);
	}
	
	public List getParaList()
	{
		return this.paraList;
	}
	
	public Image getIconImage()
	{
		// TODO Auto-generated method stub
		return LOGIC_ICON;
	}

	public Point getLocation() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getNewID() 
	{
		// TODO Auto-generated method stub
		return Integer.toString(count++);
	}

	public void setNodeStatus(String nodeStatus) 
	{
		// TODO Auto-generated method stub
		this.processDefStatus = nodeStatus;
		firePropertyChange(WorkflowDiagram.STATUS, null, nodeStatus);
	}

	public String getNodeStatus()
	{
		// TODO Auto-generated method stub
		return this.processDefStatus;
	}
	
	public void setConnectionType(String connectionType)
	{
		this.connectionType = connectionType;
		this.firePropertyChange(CONNECTIONTYPE,null,connectionType);
	}
	
	public String getConnectionType()
	{
		return this.connectionType;
	}
	
	public void setRulersVisibility(boolean rulersVisibility)
	{
		this.rulersVisibility = rulersVisibility;
//		this.firePropertyChange(RULERS_VISIBILITY,null,null);
	}
	
	public boolean getRulersVisibility()
	{
		return this.rulersVisibility;
	}
	
	public void addChild(WorkflowSubPart child)
	{
		child.setParent(this);
		addChild(child, -1);
	}

	public void addChild(WorkflowSubPart child, int index)
	{
		child.setParent(this);
		if (index >= 0)
		{
			child.setNodeId(new Integer(++NODEID).toString());
			children.add(index, child);
		}
		else
		{
			child.setNodeId(new Integer(++NODEID).toString());
			children.add(child);
		}
		this.fireStructureChange(PROP_CHILDREN,children);
	}
	
	public void setPriorities(String[] priorities)
	{
		this.priorities = priorities;
		this.firePropertyChange(PRIORITIES,null,priorities);
	}
	
	public String[] getPriorities()
	{
		return this.priorities;
	}
	
	public void setPriority(String priority)
	{
		int flag = 0;
		for(int i=0;i<this.priorities.length;i++)
		{			
			if((this.priorities[i]).toLowerCase().equals(priority.toLowerCase()))
			{
				flag = 1;
				break;
			}
		}
		if(flag == 1)
		{
			this.priority = priority;
			this.firePropertyChange(PRIORITY,null,priority);
		}
		else
		{
			IWorkbenchWindow window = null;
			MessageDialog.openInformation(
					window.getShell(),
					"error",
					"the property is out of range");
		}
	}
	
	public String getPriority()
	{
		return this.priority;
	}
	
	public String getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
		this.firePropertyChange(CREATETIME,null,createTime);
	}
	
	public String getBeginTime()
	{
		return this.beginTime;
	}
	
	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
		this.firePropertyChange(BEGINTIME,null,beginTime);
	}
	
	public String getEndTime()
	{
		return this.endTime;
	}
	
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
		this.firePropertyChange(ENDTIME,null,endTime);
	}
	
	public void setProject(MsProject project)
	{
		this.project = project;
		this.firePropertyChange(PROJECT,null,project);
	}
	
	public MsProject getProject(){
		return this.project;
	}

	public void setWfDesc(String wfDesc)
	{
		this.wfDesc = wfDesc;
		this.firePropertyChange(PROP_DESC,null,wfDesc);
	}
	
	public String getWfDesc()
	{
		return this.wfDesc;
	}
	
	public void setInfor1(String infor1)
	{
		this.infor1 = infor1;
		this.firePropertyChange(INFOR,null,infor1);
	}
	
	public String getInfor1()
	{
		return this.infor1;
	}
	
	public void setInfor2(String infor2)
	{
		this.infor2 = infor2;
		this.firePropertyChange(INFOR,null,infor2);
	}
	
	public String getInfor2()
	{
		return this.infor2;
	}
	
	public void setAllInfor(String allInfor)
	{
		this.allInfor = allInfor;
		this.firePropertyChange(INFOR,null,allInfor);
	}
	
	public String getAllInfor()
	{
		return this.allInfor;
	}
	
	public void setOpEntity(String opEntity)
	{
		this.opEntity = opEntity;
		this.firePropertyChange(OPENTITY,null,opEntity);
	}
	
	public String getOpEntity()
	{
		return this.opEntity;
	}
	
	public void setWfId(String workflowId)
	{
		this.wfId = workflowId;
		this.firePropertyChange(WORKFLOWID,null,workflowId);
	}
	
	public String getWfId()
	{
		return this.wfId;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
		this.firePropertyChange(STATUS,null,status);
	}
	
	public String getStatus()
	{
		return this.status;
	}
	
	public void removeChild(WorkflowSubPart child) 
	{
		child.setParent(null);
		children.remove(child);
		this.firePropertyChange(PROP_CHILDREN,null, child);
	}

	public void setLocation(Point p) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void setIsSubflow(boolean isSubflow)
	{
		this.isSubflow = isSubflow;
		this.firePropertyChange(ISSUBFLOW,null,new Boolean(isSubflow));
	}
	
	public boolean getIsSubflow()
	{
		return this.isSubflow;
	}
	
//	public void setApplicationList(List applicationList)
//	{
//		this.applicationList = applicationList;
//	}
//	
//	public List getApplicationList()
//	{
//		return this.applicationList;
//	}
	
//	public void setContainer(Container container)
//	{
//		this.container = container;
//		this.firePropertyChange(CONTAINER,null,container);
//	}
//	
//	public Container getContainer()
//	{
//		return this.container;
//	}
}
