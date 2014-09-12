/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.tools;

import org.eclipse.gef.requests.CreationFactory;

/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ElementFactory implements CreationFactory 
{

    private Object template;

    public ElementFactory(Object template) 
    {
        this.template = template;
    }

    public Object getNewObject() 
    {
        try 
        {
            return ((Class) template).newInstance();
         }
         catch (Exception e) 
         {
            return null;
         }
    }

    public Object getObjectType() 
    {
        return template;
    }
}