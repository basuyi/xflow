package net.ms.designer.editors.workflow.ui;

import net.ms.designer.editors.workflow.Constants;

public class WfUtil 
{
	private String processStatus = Constants.DRAFT ;      //the status,   1:finished  , 0:editing
	

	public void setProcessStatus(String status)
	{
		this.processStatus = status;
	}
	
	public String getProcessStatus()
	{
		return this.processStatus;
	}
	
	public String getOppositionStatus(String status)
	{
		if(status.equals(Constants.DRAFT))
		{
			return Constants.COMPLETE;
		}
		else 
			return Constants.DRAFT;
	}
	
	public boolean ifEditAble()
	{
		if(this.getProcessStatus().equals(Constants.COMPLETE))
		{
			return false;
		}
		else
			return true;
	}
	
	public static String getCompleteStatus()
	{
		return Constants.COMPLETE;
	}
	
	public static String getDraftStatus()
	{
		return Constants.DRAFT;
	}
	
	
}
