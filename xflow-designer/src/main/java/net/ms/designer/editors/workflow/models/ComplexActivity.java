/**
 * @author liuchunxia
 * 
 *  the complexactivity node 
 * the parent of ApplicationActivity
 * 
 * extends WorkflowBaseActivity
 */
package net.ms.designer.editors.workflow.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.ms.designer.editors.workflow.Messages;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


public class ComplexActivity extends WorkflowBaseActivity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	/**
	 * all finish type of the task
	 */
	public static String[] getSingletaskflag =new String[]{Messages.getString("ComplexActivity.SingletaskflagAllSideBySide"),Messages.getString("ComplexActivity.SingletaskflagOne"),Messages.getString("ComplexActivity.SingletaskflagAllOrader")};


	/**
	 * get propertyValue by propName
	 * @param propName: the property's name
	 */
	public Object getPropertyValue(Object propName) 
	{
		if(FINISHTYPE.equals(propName))
			if( ifEditAble())
				return getActivity_finish_type();
			else
				return getSingletaskflag[(new Integer(super.getActivity_finish_type())).intValue()];
		else if(propName.equals(PARTICIPANT))
			return super.getActivity_participant();
		else
			return super.getPropertyValue(propName);
   }
	
	/**
	 * set property whose name is propName with value of 'value'
	 * @param propName: the property name
	 * @param value: the property value
	 */
	public void setPropertyValue(Object propName, Object value) 
	{
	    if(PARTICIPANT.equals(propName))
	        super.setActivity_participant((String)value);
		else if(FINISHTYPE.equals(propName))
			super.setActivity_finish_type((String)value);
		else		
			super.setPropertyValue(propName, value);
	}

	

	/**
	 * get image
	 */
	public Image getIconImage() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * this kind of node 's count
	 */
	private static int count=0;
	
	/**
	 * get a new id
	 */
	public String getNewID() 
	{
		return Integer.toString(count++);
	}

	

}
