/**
 * @author liuchunxia
 * 
 * the end node 
 * 
 * extends SystemActivityNode
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.WorkflowImages;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


public class EndNode extends SystemActivityNode
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * this kind of node 's count
	 */
	private static int count=0;
	
	/**
	 * get a new id
	 */
	public String getNewID()
	{
		return Integer.toString(count++);
	}
	
	/**
	 * set image to this kind of node
	 */
	private static Image ENDNODE = WorkflowImages.getImage(WorkflowImages.ENDNODE);
	
	/**
	 * the construction of EndNode
	 * 
	 */
	public EndNode()
	{
	    setName(Messages.getString("EndNode.name")+getNewID()); 
	    this.setActivity_type(Messages.getString("EndNode.activityType"));
		this.setActivity_type_constant(Constants.WF_ACTIVITY_TYPE_END);
	}

	/**
	 * get image
	 */
	public Image getIconImage() 
	{
		return ENDNODE;
	}
	
}
