/**
 * @author liuchunxia
 * 
 * the workflowBaseActivity
 * extends WorkflowSubPart
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;


public abstract class WorkflowBaseActivity extends WorkflowSubPart
{
	/**
	 * the activity's type
	 */
    private String activity_type="System node";  //the activity's type
    
    /**
     * the activity's type's constant :1,2,3……
     */
    private String activity_type_constant = "";     //the constant   :1,2,3……
    
    /**
     * all of priority when running;
     */
    private String[] priorities = new String[] { "1", "2", "3", "4", "5" } ;  //all of priority when running;
    
    /**
     * the node's priority
     */
    private String priority = "";  //the node's priority
    
    /**
     * the description
     */
    private String activity_desc ="";     //the description
    
    /**
     * the join mode
     */
    private String activity_join_type ;    //the join mode
    
    /**
     * the node's location
     */
    private Point Location = new Point(0,0);    //the node's location
    
    /**
     * the finish type
     */
    private String activity_finish_type = "";   //the finish type
    
    /**
     * distribute the join to somebody
     */
    private String activity_participant = "";   //distribute the join to somebody
    
    /**
     * distribute the join to somebody
     */
    private List activity_param = new ArrayList();   //all parameter
    private List activity_actions = new ArrayList(); 

    public static final String ACTIVITY_DESC = "ACTIVITY DESC";  //the activity's description
	public static final String ACTIVITY_JOIN_TYPE = "JOIN TYPE"; // the activity's join mode
	public static final String ACTIVITY_PRIORITY = "PRIORITY"; //the priority when running
	public static final String ACTIVITY_LOCATION = "ACTIVITY LOCATION"; // the activity's location
	public static final String ACTIVITY_SIZE = "ACTIVITY SIZE"; // the activity's size
	public static final String ACTIVITY_TYPE = "ACTIVITY_TYPE"; // the activity's type
	public static final String ACTIVITY_TYPE_CONSTANT = "activity_type_constant";
	public static final String APPLICATION = "APPLICATION"; // the application activity	
	public static final String PARAM = "ACTIVITY_PARAM"; // the parameter
	public static final String PARTICIPANT = "PARTICIPANT"; // the participant(参与者，共享者)
//	public static final String DURATION = "DURATION"; // the duration when running(持续时间)
	public static final String FINISHTYPE = "FINISHTYPE"; //the finish type
	public static final String ACTIVITY_ACTIONS = "ACTIVITY_ACTIONS"; // the parameter
	/**
	 * the construction of WorkflowActivity
	 *
	 */
	WorkflowBaseActivity()
	{
		this.priority = (new Integer(3)).toString();
	}

	/**
	 * set activity_type
	 * @param activity_type
	 */
	public void setActivity_type(String activity_type)
	{
		this.activity_type = activity_type;
		this.firePropertyChange(ACTIVITY_TYPE,null,activity_type);
	}
	
	/**
	 * get activity_type
	 * @return
	 */
	public String getActivity_type()
	{
		return this.activity_type;
	}
	
	/**
	 * set Activity_type_constant
	 * @param type
	 */
	public void setActivity_type_constant(String type)
	{
		this.activity_type_constant = type;
		this.firePropertyChange(ACTIVITY_TYPE_CONSTANT,null,type);
	}
	
	/**
	 * get Activity_type_constant
	 * @return
	 */
	public String getActivity_type_constant()
	{
		return this.activity_type_constant;
	}

	
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

	/**
	 * set Activity_desc
	 * @param activity_desc
	 */
	public void setActivity_desc(String activity_desc)
	{
		if(this.activity_desc.equals(activity_desc))
			return;
		this.activity_desc = activity_desc;
		this.firePropertyChange(ACTIVITY_DESC,null,activity_desc);
	}
	
	/**
	 * get Activity_desc
	 * @return
	 */
	public String getActivity_desc()
	{
		return this.activity_desc;
	}

	/**
	 * set activity join type
	 * @param activity_join_type  0:single route  1:all route
	 */
	public void setActivity_join_type(String activity_join_type)
	{
		if(activity_join_type.equals(Constants.WF_JOIN_TYPE_SINGLE))
		{
			this.activity_join_type = Constants.WF_JOIN_TYPE_SINGLE;
		}
		else if(activity_join_type.equals(Constants.WF_JOIN_TYPE_WHOLE))
		{
			this.activity_join_type = Constants.WF_JOIN_TYPE_WHOLE;
		}
		this.firePropertyChange(ACTIVITY_JOIN_TYPE,null,activity_join_type);
	}
	
	/**
	 * get Activity_join_type
	 * @return
	 */
	public String getActivity_join_type()
	{
		return this.activity_join_type;
	}
	
	/**
	 * get Priority
	 * @return
	 */
	public String getPriority()
	{
		return this.priority;
	}
	
	/**
	 * set Priority
	 * @param priority
	 */
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
			this.firePropertyChange(ACTIVITY_PRIORITY,null,priority);
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

	
	/**
	 * get image
	 * @return
	 */
	public Image getIcon() 
	{
		return getIconImage();
	}

	/**
	 * get image
	 */
	abstract public Image getIconImage();
	
	/**
	 * set location
	 */
	public void setLocation(Point p)
	{
		this.Location.x = p.x;
		this.Location.y = p.y;
//		if(this.Location.equals(p)){
//			return ;
//		}
//		this.Location = p;
		this.firePropertyChange(ACTIVITY_LOCATION,null,p);
	}
	
	/**
	 * get location
	 */
	public Point getLocation()
	{
		return this.Location;
//		return new Point(0,0);
	}
	
//	public void setDuration(String duration){
//		this.duration = duration;
//	}
//
//	public String getDuration(){
//		return this.duration;
//	}

	/**
	 * set Activity_finish_type
	 */
	public void setActivity_finish_type(String activity_finish_type)
	{
		this.activity_finish_type = activity_finish_type;
		this.firePropertyChange(FINISHTYPE,null,activity_finish_type);
	}
	
	/**
	 * get Activity_finish_type
	 * @return
	 */
	public String getActivity_finish_type()
	{
		return this.activity_finish_type;
	}
	
	/**
	 * set Activity_participant
	 * @param activity_participant
	 */
	public void setActivity_participant(String activity_participant)
	{
		this.activity_participant = activity_participant;
		this.firePropertyChange(PARTICIPANT,null,activity_participant);
	}
	
	/**
	 * get Activity_participant
	 * @return
	 */
	public String getActivity_participant()
	{
		return this.activity_participant;
	}
	
	/**
	 * set Activity_param
	 * @param activity_param
	 */
	public void setActivity_param(List activity_param)
	{
		this.activity_param = activity_param;
		this.firePropertyChange(PARAM ,null,activity_param);
	}
	
	/**
	 * get Activity_param
	 * @return
	 */
	public List getActivity_param()
	{
		return this.activity_param;
	}

	/**
	 * @return the activity_actions
	 */
	public List getActivity_actions() {
		return activity_actions;
	}

	/**
	 * @param activity_actions the activity_actions to set
	 */
	public void setActivity_actions(List activity_actions) {
		this.activity_actions = activity_actions;
		this.firePropertyChange(ACTIVITY_ACTIONS ,null,activity_actions);
	}
	

}
