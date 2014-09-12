/**
 * @author liuchunxia
 * 
 * the start node 
 * 
 * extends SystemActivityNode
 */
package net.ms.designer.editors.workflow.models;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.WorkflowImages;

import org.eclipse.swt.graphics.Image;


public class StartNode extends SystemActivityNode
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the construction of startNode
	 *
	 */
	public StartNode()
	{
		super();
	    setName(Messages.getString("StartNode.name")); 
		this.setActivity_type(Messages.getString("StartNode.activityType"));
		this.setActivity_type_constant(Constants.WF_ACTIVITY_TYPE_START);
	}
	
	/**
	 * get image
	 */
	public Image getIconImage() 
	{
		return STARTNODE;
	}

	/**
	 * define image
	 */
	private static Image STARTNODE = WorkflowImages.getImage(WorkflowImages.STARTNODE);
}
