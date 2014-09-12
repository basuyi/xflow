/**
 * @author liuchunxia
 * 
 * the RouteOnlyActivity
 * 
 * extends WorkflowBaseActivity
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.WorkflowImages;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;


public class RouteOnlyActivity extends WorkflowBaseActivity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the count of this kind of node
	 */
	private static int count = 0;

	/**
	 * the construction of RouteOnlyActivity
	 *
	 */
	public RouteOnlyActivity() 
	{
		setName(Messages.getString("RouteOnlyActivity.name") + getNewID()); 
		this.setActivity_type(Messages.getString("RouteOnlyActivity.activityType"));
//		this.setActivity_type_constant(Constants.WF_ACTIVITY_TYPE_ROUTE_ONLY);
	}

	/**
	 * define the image
	 */
	private static Image ROUTEONLY_ICON = WorkflowImages
			.getImage(WorkflowImages.ROUTE);

	/**
	 * get image
	 */
	public Image getIconImage()
	{
		return ROUTEONLY_ICON;
	}

	/**
	 * get new id
	 */
	public String getNewID() 
	{
		return Integer.toString(count++);
	}
	
}
