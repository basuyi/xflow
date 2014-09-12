/**
 * @author liuchunxia
 * 
 * set partial parameters
 * set realPara to formalPara
 */
package net.ms.designer.editors.workflow.models;

import java.io.Serializable;

public class ParameterPartial implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ParameterEntire  formalPara ;    //形参
	private ParameterEntire  realPara ;    //实参
	private boolean isExpression = false;	
	public void setFormalPara(ParameterEntire  formalPara)
	{
		this.formalPara = formalPara;
	}
	
	public ParameterEntire  getFormalPara()
	{
		return this.formalPara;
	}
	
	public void setRealPara(ParameterEntire  realPara)
	{
		this.realPara = realPara;
	}
	
	public ParameterEntire  getRealPara()
	{
		return this.realPara;
	}
	
	public void setIsExpression(boolean isExpression)
	{
		this.isExpression = isExpression;
	}
	
	public boolean getIsExpression()
	{
		return this.isExpression;
	}

}
