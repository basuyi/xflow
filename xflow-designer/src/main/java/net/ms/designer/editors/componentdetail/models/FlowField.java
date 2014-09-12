package net.ms.designer.editors.componentdetail.models;


import org.eclipse.swt.graphics.Image;

public class FlowField extends Element
{

    public FlowField()
    {
    	super();
    }
    
//    static final long serialVersionUID = 1;

    private String workflowID;
    
    private final String field_Type = "Flow"; 

    public static final String FIELD_PATH = "path"; 

    private static Image LED_ICON = new Image(null, TemplateConstants.class
			.getResourceAsStream("icons/Boolean16.gif"));  //$NON-NLS-1$

    public Image getIconImage() 
    {
        return LED_ICON;
    }

    public String getField_Type() 
    {
        return field_Type;
    }
    public int getFieldTypeLength() 
    {
        return 10;
    }
    public int getFieldTypeScale() 
    {
        return 0;
    }

    public void setFieldName(String fieldName) 
    {

        super.setName(fieldName);
    }

	public int getFieldTypeID() 
	{
		// TODO 自动生成方法存根
		return 0;
	}

	protected String getNewID() {
		// TODO 自动生成方法存根
		return null;
	}

	public String getWorkflowID()
	{
		return this.workflowID;
	}
	public void setWorkflowID(String id)
	{
		this.workflowID = id;
	}
}
