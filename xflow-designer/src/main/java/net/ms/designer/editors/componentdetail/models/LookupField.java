package net.ms.designer.editors.componentdetail.models;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public class LookupField extends CommonField
{

	private final String field_Type = "Lookup";
	private static Image LED_ICON = new Image(null, TemplateConstants.class.getResourceAsStream("icons/Lookup16.gif"));
	private List list = new ArrayList();
	private List fieldlist = new ArrayList();
	private Object selectedfield = "";
	private String selectedcomp = "";
	private int k;
	private String mainlookup = "";
	private String fieldListable = "";
	private String fieldSearchable = "";

	
	public String getFieldSearchable()
	{
		return this.fieldSearchable;
	}
	public void setFieldSearchable(String fieldSearchable)
	{
		this.fieldSearchable = fieldSearchable;
	}
	public String getFieldListable()
	{
		return this.fieldListable;
	}
	public void setFieldListable(String fieldListable)
	{
		this.fieldListable = fieldListable;
	}
	public int getK()
	{
		return this.k;
	}
	public void setK(int k)
	{
		this.k = k;
	}
	public String getMainlookup()
	{
		return this.mainlookup;
	}
	public void setMainlookup(String mainlookup)
	{
		this.mainlookup = mainlookup;
	}
	public List getFieldlist()
	{
		return this.fieldlist;
	}
	public String getSelectedcomp()
	{
		return this.selectedcomp;
	}
	public void setSelectedcomp(String selectedcomp)
	{
		this.selectedcomp = selectedcomp;
	}
	
	public Object getSelectedfield()
	{
		return this.selectedfield;
	}
	public void setSelectedfield(Object selectedfield)
	{
		this.selectedfield = selectedfield;
	}
	public Image getIconImage() 
	{
		return LED_ICON;
	}
	public String getField_Type() 
	{
		return field_Type;
	}
	public List getList()
	{
		return this.list;
	}
	
}
