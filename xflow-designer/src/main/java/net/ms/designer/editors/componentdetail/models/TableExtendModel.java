package net.ms.designer.editors.componentdetail.models;

import java.io.Serializable;

public class TableExtendModel implements Cloneable,Serializable 
{

	private int extendModel = 0; // 0 = Inherit £»1 = Replace

	private String baseComName = ""; //$NON-NLS-1$
	
	public String getBaseComName() 
	{

		return baseComName;
	}

	public void setBaseComName(String baseComName) 
	{
		this.baseComName = baseComName;
		 
	}
	

	public int getExtendModel() 
	{
		return extendModel;
	}

	public void setExtendModel(int extendModel) 
	{
		this.extendModel = extendModel;
	}

	public TableExtendModel getCopy() 
	{
		TableExtendModel tem = new TableExtendModel();

		tem.setBaseComName(getBaseComName());
		tem.setExtendModel(getExtendModel());
//		tem.setBizObject(this.getBizObject());

		return tem;
	}

	public String toString() 
	{
		String str = ""; //$NON-NLS-1$
		if (getExtendModel() == 0)
			str = Messages.getString("KCGTableExtendModel.ModelLabel.Inherit"); //$NON-NLS-1$
		else
			str = Messages.getString("KCGTableExtendModel.ModelLabel.Replace"); //$NON-NLS-1$

		str += " " + getBaseComName(); //$NON-NLS-1$
		return str;
	}

}
