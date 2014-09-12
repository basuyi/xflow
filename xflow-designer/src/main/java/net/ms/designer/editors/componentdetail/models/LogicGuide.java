package net.ms.designer.editors.componentdetail.models;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import net.ms.designer.editors.componentdetail.models.Element;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;




public class LogicGuide implements Serializable 
{
	
	public static final String PROPERTY_CHILDREN = "subparts changed"; //$NON-NLS-1$
	
	public static final String PROPERTY_POSITION = "position changed"; //$NON-NLS-1$

	static final long serialVersionUID = 1;

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	private Map map;

	private int position;

	private boolean horizontal;
	
	public LogicGuide() 
	{
		// empty constructor
	}
	
	public LogicGuide(boolean isHorizontal) 
	{
		setHorizontal(isHorizontal);
	}

	
	public void addPropertyChangeListener(PropertyChangeListener listener) 
	{
		listeners.addPropertyChangeListener(listener);
	}

	
	public void attachPart(Element part, int alignment) 
	{
		if (getMap().containsKey(part) && getAlignment(part) == alignment)
			return;

		getMap().put(part, new Integer(alignment));
		LogicGuide parent = isHorizontal() ? part.getHorizontalGuide() : part
				.getVerticalGuide();
		if (parent != null && parent != this) 
		{
			parent.detachPart(part);
		}
		if (isHorizontal()) 
		{
			part.setHorizontalGuide(this);
		} 
		else 
		{
			part.setVerticalGuide(this);
		}
		listeners.firePropertyChange(PROPERTY_CHILDREN, null, part);
	}

	
	public void detachPart(Element part) 
	{
		if (getMap().containsKey(part)) 
		{
			getMap().remove(part);
			if (isHorizontal()) 
			{
				part.setHorizontalGuide(null);
			} 
			else 
			{
				part.setVerticalGuide(null);
			}
			listeners.firePropertyChange(PROPERTY_CHILDREN, null, part);
		}
	}

	
	public int getAlignment(Element part) 
	{
		if (getMap().get(part) != null)
			return ((Integer) getMap().get(part)).intValue();
		return -2;
	}

	
	public Map getMap() 
	{
		if (map == null) 
		{
			map = new Hashtable();
		}
		return map;
	}

	
	public Set getParts() 
	{
		return getMap().keySet();
	}

	
	public int getPosition() 
	{
		return position;
	}

	
	public boolean isHorizontal() 
	{
		return horizontal;
	}


	public void removePropertyChangeListener(PropertyChangeListener listener) 
	{
		listeners.removePropertyChangeListener(listener);
	}

	
	public void setHorizontal(boolean isHorizontal) 
	{
		horizontal = isHorizontal;
	}

	
	public void setPosition(int offset) 
	{
		if (position != offset) 
		{
			int oldValue = position;
			position = offset;
			listeners.firePropertyChange(PROPERTY_POSITION, new Integer(oldValue), new Integer(position));
		}
	}

	
}
