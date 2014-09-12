/**
 * @author liuchunxia
 * 
 * the application activity node 
 * the parent of UserAppActivity and SystemAppActivity
 * 
 * extends ComplexActivity
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.List;

public class ApplicationActivity extends ComplexActivity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the application's parameters' list
	 */
	private List wfApplicationParam = new ArrayList();  //parameter list
	
	/**
	 * the application's name
	 */
	private String applicationName = null;   //application's name
	
	/**
	 * the application's type
	 */
	private String applicationType = null;   //the application's type
	
	/**
	 * the application's id
	 */
	private String applicationId = null;
	
	private String applicationPath = null;
	
	private String applicationDesc = null;
	
	private static String WFAPPLICATION = "wfApplicationParam";
	private static String APPLICATIONNAME = "applicationName";
	private static String APPLICATIONTYPE = "applicationType";
	private static String APPLICATIONID = "applicationId";
	private static String APPLICATIONPATH = "applicationPath";
	private static String APPLICATIONDESC = "applicationDesc";
	
	/**
	 * set wfApplicationParam
	 * @param wfApplicationParam
	 */
	public void setWfApplicationParam(List wfApplicationParam)
	{
		this.wfApplicationParam = wfApplicationParam;
		this.firePropertyChange(WFAPPLICATION,null,null);
	}
	
	/**
	 * get workflow application's all param
	 * @return wfApplicationParam
	 */
	public List getWfApplicationParam()
	{
		return this.wfApplicationParam;
	}
	
	/**
	 * set applicatinName
	 * @param applicationName
	 */
	public void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
		this.firePropertyChange(APPLICATIONNAME,null,null);
	}
	
	/**
	 * get applicationName
	 * @return applicationName
	 */
	public String getApplicationName()
	{
		return this.applicationName;
	}
	
	/**
	 * set applicationType
	 * @param applicationType
	 */
	public void setApplicationType(String applicationType)
	{
		this.applicationType = applicationType;
		this.firePropertyChange(APPLICATIONTYPE,null,null);
	}
	
	/**
	 * get applicationType
	 * @return applicationType
	 */
	public String getApplicationType()
	{
		return this.applicationType;
	}
	
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
		this.firePropertyChange(APPLICATIONID,null,applicationId);
	}
	
	public String getApplicationId()
	{
		return this.applicationId;
	}
	
	public void setApplicationPath(String applicationPath)
	{
		this.applicationPath = applicationPath;
		this.firePropertyChange(APPLICATIONPATH,null,applicationPath);
	}
	
	public String getApplicationPath()
	{
		return this.applicationPath;
	}
	
	public void setApplicationDesc(String applicationDesc)
	{
		this.applicationDesc = applicationDesc;
		this.firePropertyChange(APPLICATIONDESC,null,applicationDesc);
	}
	
	public String getApplicationDesc()
	{
		return this.applicationDesc;
	}
	
}
