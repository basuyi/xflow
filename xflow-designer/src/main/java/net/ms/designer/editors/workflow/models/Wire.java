/**
 * @author liuchunxia
 * 
 * the class of Wire
 * extends WorkflowElement
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.workflow.Messages;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;


public class Wire extends WorkflowElement
{	
	/**
	* the default serial version ID
	*/
	private static final long serialVersionUID = 1L;
	private String wireId;
	
	final public static String PROP_BENDPOINT = "BENDPOINT";     //the property used when property changed
	public static final String ID_DESCRIPTION = "DESCRIPTION"; //$NON-NLS-1$
	public static final String ID_TRANSITIONCONDITION = "TRANSITIONCONDITION"; //$NON-NLS-1$
	public static final String ID_NAME = "NAME";
	public static final String WIREID = "wire id";
	
	/**
	 * the wire's source
	 */
	private WorkflowSubPart source ;    //the wire's source
	
	/**
	 * the wire's target
	 */
	private WorkflowSubPart target ;    //the wire's target
	
	/**
	 * the bendPointsList of a wire
	 */
	private List bendPointsList = new ArrayList();    //the wire's bendpoints list
	
	/**
	 * the wire's description
	 */
	private String description = "";      //the wire's description
	private String name = "";
	
	/**
	 * the transition condition of the wire
	 */
	private String transitionCondision = "";    //the condition that is needed when is transiting

	private Long conditionType;
	
	/**
	 * set source
	 * @param source
	 */
	public void setSource(WorkflowSubPart source) 
	{
		Object old = this.source;
		this.source = source;
		this.firePropertyChange("source",old,source);
	
	}
	
	/**
	 * get source
	 * @return
	 */
	public WorkflowSubPart getSource()
	{
		return this.source;
	}
	
	/**
	 * set target
	 * @param target
	 */
	public void setTarget(WorkflowSubPart target)
	{
		Object old = this.target;
		this.target = target;
		this.firePropertyChange("target",old,target);
	}
	
	/**
	 * get target
	 * @return
	 */
	public WorkflowSubPart getTarget()
	{
		return this.target;
	}
	
	/**
	 * set bendPointsList
	 * @param bendPointsList
	 */
	public void setBendPointsList(List bendPointsList)
	{
		this.bendPointsList = bendPointsList;
		this.firePropertyChange(PROP_BENDPOINT,null,bendPointsList);
	}
	
	/**
	 * get bendPointsList
	 * @return
	 */
	public List getBendPointsList()
	{
		return this.bendPointsList;
	}
	
	/**
	 * set description
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
		this.firePropertyChange(ID_DESCRIPTION, null, description);
	}
	
	/**
	 * get description
	 * @return
	 */
	public String getDescription()
	{
		if(this.description == null)
			this.description = "";
		return this.description;
	}
	
	/**
	 * set transitionCondition
	 * @param transitionCondition
	 */
	public void setTransitionCondition(String transitionCondition)
	{
		this.transitionCondision = transitionCondition;
		this.firePropertyChange(ID_TRANSITIONCONDITION, null, transitionCondision);
	}
	
	/**
	 * get transitionCondition
	 * @return
	 */
	public String getTransitionCondition()
	{
		if(this.transitionCondision == null)
			this.transitionCondision = "";
		return this.transitionCondision;
	}
	
	/**
	 * add  bendPoint at index
	 * @param index
	 * @param wireBendPoint
	 */
	public void addBendPoint(int index,WireBendPoint wireBendPoint)
	{
		this.bendPointsList.add(index,wireBendPoint);
		this.firePropertyChange(PROP_BENDPOINT,null,wireBendPoint);
	}
	
	/**
	 * remove the bendPoint at index
	 * @param index
	 */
	public void removeBendPoint(int index)
	{
		this.bendPointsList.remove(index);
		this.firePropertyChange(PROP_BENDPOINT,null,null);
	}
	
	/**
	 * set bendPoint at index
	 * @param index
	 * @param wireBendPoint
	 */
	public void setBendPoint(int index,WireBendPoint wireBendPoint)
	{
		this.getBendPointsList().set(index,wireBendPoint);
		this.firePropertyChange(PROP_BENDPOINT,null,null);
	}
	

	/**
	 * getPropertyDescriptors
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() 
	{
		// TODO Auto-generated method stub
		TextPropertyDescriptor description = new TextPropertyDescriptor(
				ID_DESCRIPTION, Messages.getString("Wire.description")); //$NON-NLS-1$
		TextPropertyDescriptor transitioncondition = new TextPropertyDescriptor(
				ID_TRANSITIONCONDITION, Messages
						.getString("Wire.transitioncondition")); //$NON-NLS-1$
		IPropertyDescriptor[] result = new IPropertyDescriptor[] { description,
				transitioncondition };

		if (ifEditAble())
			return result;
		else 
		{
			List l = new ArrayList();
			for (int i = 0; i < result.length; i++) 
			{
				PropertyDescriptor p = (PropertyDescriptor) result[i];
				PropertyDescriptor p1 = new PropertyDescriptor(p.getId(), p
						.getDisplayName());
				l.add(p1);
			}
			return (IPropertyDescriptor[]) l.toArray(new IPropertyDescriptor[l
					.size()]);
		}
	}
	

	/**
	 * set Node status
	 */
	public void setNodeStatus(String nodeStatus)
	{
		// TODO Auto-generated method stub
		this.processDefStatus = nodeStatus;
	}

	/**
	 * get node status
	 */
	public String getNodeStatus() 
	{
		// TODO Auto-generated method stub
		return this.processDefStatus;
	}
	
	/**
	 * construct of the wire
	 * @param part: the source
	 * @param part2: the target
	 */
	public Wire(WorkflowSubPart part, WorkflowSubPart part2) 
	{
		this.source = part;
		this.target = part2;
 		source.addOutput(this);
		target.addInput(this);
		this.wireId = new Integer(++(this.source.getParent().WIREID)).toString();
	}

	/**
	 * remove source
	 *
	 */
	public void removeSource()
	{
		if(this.source == null)
			return ;
		this.getSource().removeOutput(this);
	}

	/**
	 * remove target
	 *
	 */
	public void removeTarget()
	{
		if(this.target == null)
			return ;
		this.getTarget().removeInput(this);
	}
	
	/**
	 * add source
	 *
	 */
	public void addSource()
	{
		if(this.getSource() == null)
			return;
		else
			this.getSource().addOutput(this);
	}

	/**
	 * add target
	 *
	 */
	public void addTarget(){
		if(this.getTarget() == null || this.getTarget().getInputs().contains(this))
			return ;
		else
			this.getTarget().addInput(this);
	}
	
	/**
	 * set bendpoint at index
	 * @param index
	 * @param point
	 */
	public void setBendPoint(int index, Bendpoint point) {
		this.getBendPointsList().set(index, point);
		firePropertyChange("bendpoint", null, null);//$NON-NLS-1$
	}
	
	public void setWireId(String wireId)
	{
		this.wireId = wireId;
		this.firePropertyChange(WIREID,null,wireId);
	}
	
	public String getWireId()
	{
		return this.wireId;
	}

	public Long getConditionType() {
		return this.conditionType;
	}

	public void setConditionType(Long conditionType) {
		this.conditionType = conditionType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		this.firePropertyChange(ID_NAME, null, name);
	}

}
