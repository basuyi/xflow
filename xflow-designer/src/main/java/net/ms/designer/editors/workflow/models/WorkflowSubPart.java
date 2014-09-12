/**
 * @author liuchunxia
 * 
 * the WorkflowSubPart
 * extends WorkflowElement
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import net.ms.designer.editors.workflow.Messages;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.PropertyDescriptor;


abstract public class WorkflowSubPart extends WorkflowElement 
{
	protected Vector inputs = new Vector();      //all inputs line into nodes
	protected Vector outputs = new Vector();      //all outputs line from nodes
	private WorkflowDiagram parent;             //diagram's editor
	protected Dimension size = new Dimension(-1, -1);    //the element's size
	protected String name = "";               //the element's name
	protected String iname ="";            //the internationalize name
	private String nodeId;
	private List rightList;
	
	public static String ID_SIZE = "size";      
	public static String ID_LOCATION = "location"; 
	public static String PRO_NAME = "name"; 
	public static String PRO_INAME = "iname";
	public static final String INPUTS = "inputs";
	public static final String OUTPUTS = "outputs";
	public static final String PARENTS = "patent";
	public static final String NODE_ID = "node id";
	public static final String RIGHTLIST = "right list";
		
	abstract public Image getIconImage();
	abstract public Point getLocation() ;
	public abstract String getNewID();
	
	public static List WorkflowSubpartDescriptors = new ArrayList();
	static 
	{
		WorkflowSubpartDescriptors
				.add(new PropertyDescriptor(ID_LOCATION, Messages.getString("WorkflowSubpart.location"))); //$NON-NLS-1$
	}
	
	public void setParent(WorkflowDiagram p)
	{
	    this.parent = p;
//	    doAfterSetParent();
	}
//	protected void doAfterSetParent() 
//	{
//	}
	
	public WorkflowDiagram getParent()
	{
		return this.parent;
	}
	
	public void setNodeStatus(String nodeStatus) 
	{
		this.processDefStatus= nodeStatus;
		this.firePropertyChange("status",null,(new Long(nodeStatus)));
	}
	
	public String getNodeStatus()
	{
		return this.processDefStatus;
	}
	
	public void setInputs(Vector inputs)
	{
		this.inputs = inputs;
		this.firePropertyChange(INPUTS,null,inputs);
	}
	
	public Vector getInputs()
	{
		return this.inputs;
	}

	public void setOutputs(Vector outputs)
	{
		this.outputs = outputs;
		this.firePropertyChange(OUTPUTS,null,outputs);
	}
	
	public Vector getOutputs()
	{
		return this.outputs;
	}
	
	public void setName(String name)
	{
		this.name = name;
		this.firePropertyChange(PRO_NAME,null,name);
	}

	public abstract void setLocation(Point p);
	
	public String getName()
	{
		return this.name;
	}
	
	public void setIname(String iname){
		this.iname = iname;
		this.firePropertyChange(PRO_INAME,null,iname);
	}
	
	public String getIname(){
		return this.iname;
	}
	
	public void addInput(Wire wire)
	{
		this.inputs.add(wire);
		this.firePropertyChange(INPUTS,null,wire);
	}
	
	public void addOutput(Wire wire)
	{
		this.outputs.add(wire);
		this.firePropertyChange(OUTPUTS,null,wire);
	}
	
	public void removeInput(Wire wire)
	{
		if(this.inputs.contains(wire))
		{
		    this.inputs.remove(wire);
		}
		this.firePropertyChange(INPUTS,null,wire);
	}
	
	public void removeOutput(Wire wire)
	{
		if(this.outputs.contains(wire))
		{
			this.outputs.remove(wire);
		}
		this.firePropertyChange(OUTPUTS,null,wire);
	}
	
	public void setSize(Dimension size)
	{
		this.size = size;
		this.firePropertyChange(ID_SIZE,null,size);
	}
	
	public Dimension getSize()
	{
		return this.size;
	}

	public Vector getConnections() {
		Vector v = (Vector) outputs.clone();
		Enumeration ins = inputs.elements();
		while (ins.hasMoreElements())
			v.addElement(ins.nextElement());
		return v;
	}
	
	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
		this.firePropertyChange(NODE_ID,null,nodeId);
	}
	
	public String getNodeId()
	{
		return this.nodeId;
	}
	
	
	public void setRightLight(List rightList)
	{
		this.rightList = rightList;
		this.firePropertyChange(RIGHTLIST,null,rightList);
	}
	
	public List getRightList()
	{
		return this.rightList;
	}
	
}
