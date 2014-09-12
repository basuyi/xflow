package net.ms.designer.editors.componentdetail.models;

public class IknDataType 
{

	public static  int BOOLEAN = 0;

	static int ENUMERATION=0;
	
	public static  int FLOAT = 0;
	
	public static int INTEGER =0;
	
	public static int DATA = 0;
	
	public static int STRING = 0;
	
	public int getString()
	{
		return STRING;
	}
	public void getString(int String)
	{
		this.STRING = STRING;
	}
	 
	public int getData()
	{
		return DATA;
	}
	public void setData(int Data)
	{
		this.DATA = DATA;
	}
	
	public int getInteger()
	{
		return INTEGER;
	}
	public void setInteger(int Integer)
	{
		this.INTEGER = INTEGER;
	}
	
	public int getFloat()
	{
		return FLOAT;
	}
	public void setFloat(int Float)
	{
		this.FLOAT = FLOAT;
	}
	public int getBoolean()
	{
		return BOOLEAN;
	}
	public void setBoolean(int Boolean)
	{
		this.BOOLEAN = BOOLEAN;
	}
	
	public int getEnumerTion()
	{
	 return ENUMERATION; 
	}
	
	public void setEnumerTion(int EnumerTion)
	{
		this.ENUMERATION = ENUMERATION;
	}
	

}
