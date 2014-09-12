/**
 * @author liuchunxia
 * 
 * the SubFlow Activity 
 * extends ComplexActivity
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.WorkflowImages;

import org.eclipse.swt.graphics.Image;


public class SubFlowActivity extends ComplexActivity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the execute model's id
	 */
	private String execMode = Constants.WF_SUBFLOW_EXECUTE_MODE_SYNCHRO;   //the executeModel
	
	/**
	 * all subflow param
	 */
	private List subflowParam = new ArrayList();     //all parameter
	
	/**
	 * the subflow's name
	 */
	private String subflowName = "";   
	
	private String subflowId = "";
	
	/**
	 * the subflow's path
	 */
	private String subflowPath = "";
	
	private WorkflowDiagram subflowDiagram = null;

	public static final String SUBFLOWPARAM = "subflowParam";

	public static final String SUBFLOW = "subflow"; 

	public static final String WFEXECMODE = "wfExecMode";
	
	public static final String SUBFLOWNAME = "subflowName";
	
	public static final String SUBFLOWPATH = "subflowPath";
	
	public static final String SUBFLOWID = "subflow id";
	
	public static final String SUBFLOWDIAGRAM = "subflowDiagram";

	/**
	 * the count of subflow node
	 */
	public static int count = 0;

	/**
	 * define the image
	 */
	private static Image SUBFLOW_ICON = WorkflowImages
			.getImage(WorkflowImages.SUBFLOW);

	/**
	 * the construction of SubFlowactivity
	 *
	 */
	public SubFlowActivity() 
	{
		super();
		setName(Messages.getString("SubFlowActivity.name") + getNewID()); 
		this.setActivity_type(Messages.getString("SubFlowActivity.activityType"));
		this.setActivity_type_constant(Constants.WF_ACTIVITY_TYPE_SUBFLOW);
	}

	/**
	 * get a new id
	 */
	public String getNewID()
	{
		return Integer.toString(count++);
	}
	
	/**
	 * get image
	 */
	public Image getIconImage() 
	{
		return SUBFLOW_ICON;
	}
	

	/**
	 * set execute mode
	 * @param execMode
	 */
	public void setExecMode(String execMode)
	{
		this.execMode = execMode;
		this.firePropertyChange(WFEXECMODE,null,execMode);
	}
	
	/**
	 * get execute mode
	 * @return
	 */
	public String getExecMode()
	{
		return this.execMode;
	}
	
	/**
	 * set subflowName
	 * @param subflow
	 */
	public void setSubflowName(String subflowName)
	{
		this.subflowName = subflowName;
		this.firePropertyChange(SUBFLOWNAME,null,subflowName);
	}
	
	/**
	 * get subflowName
	 * @return
	 */
	public String getSubflowName()
	{
		return this.subflowName;
	}
	
	/**
	 * set subflow path
	 * @param subflowPath
	 */
	public void setSubflowPath(String subflowPath)
	{
		this.subflowPath = subflowPath;
		this.firePropertyChange(SUBFLOWPATH,null,subflowPath);
	}
	
	/**
	 * get subflowPath
	 * @return
	 */
	public String getSubflowPath()
	{
		return this.subflowPath;
	}
	
	public void setSubflowId(String subflowId)
	{
		this.subflowId = subflowId;
		this.firePropertyChange(SUBFLOWID,null,subflowId);
	}
	
	public String getSubflowId()
	{
		return this.subflowId;
	}
	
	/**
	 * set subflow parameters
	 * @param subflowParam
	 */
	public void setSubflowParam(List subflowParam)
	{
		this.subflowParam = subflowParam;
		this.firePropertyChange(SUBFLOWPARAM,null,subflowParam);
	}
	
	/**
	 * get subflow parameters
	 * @return
	 */
	public List getSubflowParam()
	{
		return this.subflowParam;
	}
	
	public void setSubflowDiagram (WorkflowDiagram subflowDiagram)
	{
		this.subflowDiagram = subflowDiagram;
		this.firePropertyChange(SUBFLOWDIAGRAM,null,subflowDiagram);
	}

	public WorkflowDiagram getSubflowDiagram()
	{
		return this.subflowDiagram;
	}
	
}
