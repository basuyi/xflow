/**
 * @author liuchunxia
 * 
 * System Application Activity
 * extends Application
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


public class SystemAppActivity extends ApplicationActivity 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the construction of System Application Activity
	 *
	 */
	public SystemAppActivity()
	{
		super();
	    setName(Messages.getString("JavaAppActivity.name")+getNewID()); 
		this.setActivity_type(Messages.getString("JavaAppActivity.activityType")); 
		this.setActivity_type_constant(Constants.WF_ACTIVITY_TYPE_SYS_APP);
		this.setApplicationType(Constants.WF_APPLICATION_TYPE_SYS);
		Date da = new Date();
		long time = da.getTime();
		this.setApplicationId(Long.toString(time));
	}
	
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
	private static Image JAVAAPP_ICON = WorkflowImages
			.getImage(WorkflowImages.JAVAAPPLICATION);

	/**
	 * get image
	 */
	public Image getIconImage()
	{
		return JAVAAPP_ICON;
	}
	
	/**
	 * get application type
	 */
	 public String getApplicationType() 
	{
	    return Constants.WF_APPLICATION_TYPE_SYS;
	}

}
