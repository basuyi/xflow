package net.ms.designer.editors.enumcomponentdetail.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Element implements Serializable
{ 
    public static final String PRO_FIGURE = "__figure__property";  
    public static final String PRO_CHILD = "__child__property";
    
     protected PropertyChangeSupport support = new PropertyChangeSupport(this);
     protected Element parent;
     protected List children;

    public Element getParent() 
    {
        return parent;
    }

    public void setParent(Element parent) 
    {
        this.parent = parent;
    }

    public List getChildren() 
    {
        if(children == null) children = new ArrayList();
        return children;
    }

    public void setChildren(List children) 
    {
        this.children = children;
    }
    
    public void addChild(Element child)
    {
       addChild(-1,child);
    }
    
    public void addChild(int index , Element child)
    {
        if(index == -1)
        {
        	getChildren().add(child);
        }
        else
        {
            getChildren().add(index,child);
        }
        child.setParent(this);
        this.fireChildenChange(child);
    }
    
    public void removeChild(Element child)
    {
        child.setParent(null);
        getChildren().remove(child);
        this.fireChildenChange(child);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l)
    {
        support.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l)
    {
        support.removePropertyChangeListener(l);
    }
    
    public void fireFigurePropertyChange(Object old,Object now)
    {
        support.firePropertyChange(PRO_FIGURE,old,now);
    }
    
    public void fireChildenChange(Element child)
    {
        support.firePropertyChange(PRO_CHILD,null,child);
    }
}
