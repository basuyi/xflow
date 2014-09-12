package net.ms.designer.editors.enumcomponentdetail.palette;

import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.model.ValueField;

import org.eclipse.gef.requests.CreationFactory;


public class EnumCreationFactory implements CreationFactory 
{
    private Class type;
    public EnumCreationFactory(Class type)
    {
        setType(type);
    }

    public Object getNewObject() 
    {
        if(type == Table.class)
        {
            return new Table();
        }
        if(type == ValueField.class) 
        	return new ValueField();
        return null;
    }

    public Object getObjectType() 
    {
        return getType();
    }

    /**
     * @return type.
     */
    public Class getType() 
    {
        return type;
    }
    /**
     * @param type
     */
    public void setType(Class type) 
    {
        this.type = type;
    }
}
