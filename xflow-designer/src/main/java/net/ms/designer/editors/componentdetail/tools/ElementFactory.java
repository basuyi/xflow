/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.componentdetail.tools;

import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;

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
        if(template == ComponentTable.class){
        	return new ComponentTable();
        }
        if(template == ChildTable.class)
        {
        	return new ChildTable();
        }
        if(template == CommonField.class)
        {
        	return new CommonField();
        }
        return null;
    }

    public Object getObjectType() 
    {
        return template;
    }
}