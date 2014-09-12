/**
 * @author liuchunxia
 * 
 * the User Application Activity
 * extends ApplicationActivity
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.WorkflowImages;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;



public class UserAppActivity extends ApplicationActivity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the count of this kind of node
	 */
	public static int count=0;
	
	/**
	 * get a new id
	 */
	public String getNewID()
	{
		return Integer.toString(count++);
	}
	
	/**
	 * define the image
	 */
	private static Image WEBAPP_ICON = WorkflowImages.getImage(WorkflowImages.WEBBAPPLICATION);
	
	/**
	 * get image
	 */
	public Image getIconImage() 
	{
		return WEBAPP_ICON;
	}
	
	/**
	 * the construction of UserAppActivity
	 *
	 */
	public UserAppActivity()
	{
		super();
	    setName(Messages.getString("WebAppActivity.name")+getNewID()); 
		this.setActivity_type(Messages.getString("WebAppActivity.activityType"));
		this.setApplicationType(Constants.WF_APPLICATION_TYPE_USER);
		this.setActivity_type_constant(Constants.WF_ACTIVITY_TYPE_USER_APP);
		this.setActivity_participant(null);
		Date da = new Date();
		long time = da.getTime();
		this.setApplicationId(Long.toString(time));
	}
	
	/**
	 * get applicationType
	 */
	 public String getApplicationType()
	 {
	     return Constants.WF_APPLICATION_TYPE_USER;
	 }
	
	 private Long policyType;
	 private String policyValue;
	 private Long entryType;
	 private String entryValue;

	/**
	 * @return the policyType
	 */
	public Long getPolicyType() {
		return policyType;
	}

	/**
	 * @param policyType the policyType to set
	 */
	public void setPolicyType(Long policyType) {
		this.policyType = policyType;
	}

	/**
	 * @return the policyValue
	 */
	public String getPolicyValue() {
		return policyValue;
	}

	/**
	 * @param policyValue the policyValue to set
	 */
	public void setPolicyValue(String policyValue) {
		this.policyValue = policyValue;
	}

	/**
	 * @return the entryType
	 */
	public Long getEntryType() {
		return entryType;
	}

	/**
	 * @param entryType the entryType to set
	 */
	public void setEntryType(Long entryType) {
		this.entryType = entryType;
	}

	/**
	 * @return the entryValue
	 */
	public String getEntryValue() {
		return entryValue;
	}

	/**
	 * @param entryValue the entryValue to set
	 */
	public void setEntryValue(String entryValue) {
		this.entryValue = entryValue;
	}
}
