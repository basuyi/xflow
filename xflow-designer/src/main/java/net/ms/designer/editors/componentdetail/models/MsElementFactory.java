package net.ms.designer.editors.componentdetail.models;

import org.eclipse.gef.requests.CreationFactory;

/**
 * 
 * @author lili
 *
 *TODO identify the different Elements
 */
public class MsElementFactory implements CreationFactory 
{

	private Class template;
	
	/**
	 * TODO construct of the CEECElementFactory()
	 * @param str
	 */
	public MsElementFactory(Class str) 
	{
		template = str;
	}

	/**
	 * TODO identify the different Elements
	 */
	public Object getNewObject() 
	{
		
	
		if(template.equals(ComponentTable.class))
		{
			return new Table();
		}
		else if(template.equals(StringField.class))
		{
			return new StringField();
		}
		else if(template.equals(IntegerField.class))
		{
			return new IntegerField();
		}
		else if(template.equals(DateField.class))
		{
			return new DateField();
		}
		else if(template.equals(FloatField.class))
		{
			return new FloatField();
		}
		else if(template.equals(EnumField.class))
		{
			return new EnumField();
		}
		else if(template.equals(ChildTable.class))
		{
			return new ChildTable();
		}
		else if(template.equals(FlowField.class))
		{
			return new FlowField();
		}
		return null;
	
	}

	/**
	 * return the Object template
	 */
	public Object getObjectType() 
	{
		return template;
	}

}
