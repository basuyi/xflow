/**
 * @author liuchunxia
 *  
 * define the entire parameters
 */
package net.ms.designer.editors.workflow.models;

import java.io.Serializable;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;


public class ParameterEntire implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the parameter's name
	 */
	private String paraName = "";
	
	/**
	 * the parameter's value
	 */
	private String paraValue = "";
	
	/**
	 * the parameter's description
	 */
	private String description = "";
	
	/**
	 * is the parameter input value?
	 */
	private boolean isInput  = true;
	
	/**
	 * is the parameter output value?
	 */
	private boolean isOutput = true;
	
	/**
	 * the parameter's type
	 */
	private String paraType = "";
	
	/**
	 * the parameter's all type
	 */
	private String[] paraAllType = {
			Messages.getString("WorkflowParameterType.string"),
			Messages.getString("WorkflowParameterType.long"),
			Messages.getString("WorkflowParameterType.double"),
			Messages.getString("WorkflowParameterType.date"),
			Messages.getString("WorkflowParameterType.object")};
	
//	private static final String PARANAME = "paraName";
//	private static final String PARAVALUE = "paraValue";
//	private static final String DESCRIPTION = "description";
//	private static final String ISINPUT = "isInput";
//	private static final String ISOUTPUT = "isOutput";
//	private static final String PARATYPE = "paraType";

	/**
	 * set parameter's name
	 */
	public void setParaName(String paraName)
	{
		this.paraName = paraName;
	}
	
	/**
	 * get parameter's name
	 * @return parameter's name
	 */
	public String getParaName()
	{
		return this.paraName;
	}
	
	/**
	 * set parameter's value
	 * @param paraValue
	 */
	public void setParaValue(String paraValue)
	{
		this.paraValue = paraValue;
	}
	
	/**
	 * get parameter's value
	 * @return parameter's value
	 */
	public String getParaValue()
	{
		return this.paraValue;
	}
	
	/**
	 * set description
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * get description
	 * @return description
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * set isInput
	 * @param isInput
	 */
	public void setIsInput(boolean isInput)
	{
		this.isInput = isInput;
	}
	
	/**
	 * get isInput
	 * @return
	 */
	public boolean getIsInput()
	{
		return this.isInput;
	}
	
	/**
	 * set isOutput
	 * @param isOutput
	 */
	public void setIsOutput(boolean isOutput)
	{
		this.isOutput = isOutput;
	}
	
	/**
	 * get isOutput
	 * @return isOutput
	 */
	public boolean getIsOutput(){
		return this.isOutput;
	}

	/**
	 * set parameter's type
	 * @param paraType
	 */
	public void setParaType(String paraType)
	{
		this.paraType = paraType;
		
	}
	
	/**
	 * get parameter's type
	 * @return parameter's type
	 */
	public String getParaType()
	{
		return this.paraType;
	}
	
	/**
	 * set all type of parameters
	 * @param paraAllType
	 */
	public void setParaAllType(String[] paraAllType)
	{
		this.paraAllType = paraAllType;
	}
	
	/**
	 * get all parameter's type
	 * @return paraAllType
	 */
	public String[] getParaAllType()
	{
		return this.paraAllType;
	}
}
