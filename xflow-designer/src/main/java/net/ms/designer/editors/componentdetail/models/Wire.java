package net.ms.designer.editors.componentdetail.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.Bendpoint;
public class Wire extends AbstractElement
{
	
	static final long serialVersionUID = 1;
	protected boolean value;

	protected Table target;
	protected Element source;
	
	protected List bendpoints = new ArrayList();

	public void attachSource() 
	{
		if (getSource() == null
				|| getSource().getSourceConnections().contains(this))
			return;
		getSource().connectOutput(this);
	}

	public void attachTarget()
   {
		if (getTarget() == null
				|| getTarget().getTargetConnections().contains(this))
			return;
		getTarget().connectInput(this);
	}

	public void detachSource()
	{
		if (getSource() == null)
			return;
		getSource().disconnectOutput(this);
	}

	public void detachTarget() 
	{
		if (getTarget() == null)
			return;
		getTarget().disconnectInput(this);
	}

	public List getBendpoints() 
	{
		return bendpoints;
	}

	public Element getSource() 
	{
		return source;
	}

	public Table getTarget() 
	{
		return target;
	}

	public boolean getValue() 
	{
		return value;
	}

	public void insertBendpoint(int index, Bendpoint point) 
	{
		getBendpoints().add(index, point);
		firePropertyChange("bendpoint", null, null);//$NON-NLS-1$
	}

	public void removeBendpoint(int index) 
	{
		getBendpoints().remove(index);
		firePropertyChange("bendpoint", null, null);//$NON-NLS-1$
	}

	public void setBendpoint(int index, Bendpoint point) 
	{
		getBendpoints().set(index, point);
		firePropertyChange("bendpoint", null, null);//$NON-NLS-1$
	}

	public void setBendpoints(Vector points) 
	{
		bendpoints = points;
		firePropertyChange("bendpoint", null, null);//$NON-NLS-1$
	}

	public void setSource(Element e) 
	{
		Object old = source;
		source = e;
		firePropertyChange("source", old, source);//$NON-NLS-1$
	}

	public void setTarget(Table e) 
	{
		Object old = target;
		target = e;
		firePropertyChange("target", old, source);//$NON-NLS-1$
	}

	public void setValue(boolean value) 
	{
		if (value == this.value)
			return;
		this.value = value;
//		if (target != null)
//			target.update();
		firePropertyChange("value", null, null);//$NON-NLS-1$
	}

	public String toString() 
	{
		return null;
//		return "Connection(" + getSource() + "->" + getTarget() + ")";//$NON-NLS-5$//$NON-NLS-4$//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO 自动生成方法存根

	}

	

}
