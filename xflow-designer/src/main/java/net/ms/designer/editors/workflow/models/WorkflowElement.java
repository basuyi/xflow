package net.ms.designer.editors.workflow.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;


import net.ms.designer.editors.workflow.ui.*;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


/**
 * add propertyChangeListener to the element
 * @author liuchunxia
 *
 */
abstract public class WorkflowElement implements IPropertySource,
	Cloneable,Serializable
{
	
	protected String processDefStatus = WfUtil.getDraftStatus();    //the default process status
	PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	 /**
	  * add a propertyChangeListener to the listener list
	  * @param l : the property change listener
	  */  
	 public void addPropertyChangeListener(PropertyChangeListener l)
	 {
		 listeners.addPropertyChangeListener(l);
	 }
	 
	 /**
	  * Report a bound property update to any registered listeners
	  * @param prop : property Name
	  * @param old : old value
	  * @param newValue: new value
	  */
	 protected void firePropertyChange(String prop, Object old, Object newValue) 
	 {
	    listeners.firePropertyChange(prop, old, newValue);
	 }

	 /**
	  * report a structureChange
	  * @param prop
	  * @param child
	  */
	 protected void fireStructureChange(String prop, Object child)
	 {
	    listeners.firePropertyChange(prop, null, child);
	 }

	 /**
	  * remove a propertyChangeListener for a specific property
	  * @param l
	  */
	 public void removePropertyChangeListener(PropertyChangeListener l) 
	 {
	    listeners.removePropertyChangeListener(l);
	 }
	 
	 public boolean ifEditAble()
	 {
		 WfUtil util = new WfUtil();
		 if(util.ifEditAble() == true)
		 {
			 return true;
		 }
		 else
			 return false;
	 }
	 
		//------------------------------------
		//------Abstract methods from IPropertySource

	public Object getEditableValue() 
	{
		// TODO Auto-generated method stub
		return this;
	}

	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		// TODO Auto-generated method stub
		return new IPropertyDescriptor[0];
	}

	public Object getPropertyValue(Object arg0) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPropertySet(Object propName) 
	{
		// TODO Auto-generated method stub
		return true;
	}

	public void resetPropertyValue(Object arg0) 
	{
		// TODO Auto-generated method stub
	}

	public void setPropertyValue(Object arg0, Object arg1) 
	{
		// TODO Auto-generated method stub
			
	}
		
	public void update() 
	{
	}
	public abstract void setNodeStatus(String nodeStatus);

	public abstract String getNodeStatus();
}
