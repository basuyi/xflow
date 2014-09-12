/**
 * @author mashuai
 * 2006.09.06/09:41
 *Abstract base class of the node,reride some firePropertyChange()
 *methods
 *
 */
package net.ms.designer.editors.packages.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public abstract class Element implements Cloneable,Serializable
{
//	define PropertyChangelistener
	PropertyChangeSupport listeners = new PropertyChangeSupport(this);
//	register listener method
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		listeners.addPropertyChangeListener(listener);
	}
//	fire the property changing
	protected void firePropertyChange(String prop, Object oldValue, Object newValue) {
        listeners.firePropertyChange(prop, oldValue, newValue);
    }
//	fire the property changing
    protected void fireStructureChange(String prop, Object child) {
        listeners.firePropertyChange(prop, null, child);
    }
//  sign out the listener
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.removePropertyChangeListener(listener);
    }
}