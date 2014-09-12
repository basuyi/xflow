package net.ms.designer.editors.componentdetail.models;

/**
 * @author lili
 * @version 1.1.0
 * @explain defind the ComponentTable's properties,it extends ChildTable.
 */
public class ComponentTable extends ChildTable 
{

	public static String COMPONENT_NEED_2DUPDATE = "Need 2D Update"; 

	public static String COMPONENT_2DUPDATE = "2D Update"; 

	public static String COMPONENT_SECUREBASE = "Security Base"; 

	public static String COMPONENT_NEED_FLOWASSOCIATED = "Need Flow Associated"; 
	
	public static String COMPONENT_NEED_FLOWASSOCIATED_DISABLE = "Need Flow Associated Disable"; 

	public static String COMPONENT_FLOWASSOCIATED = "Flow Associated"; 

	public static String COMPONENT_NEED_DYNAMIC_QUERY = "Need Dynamic Query"; //¶¯×÷Ñ¯ÎÊ£¿

	public static String COMPONENT_DYNAMIC_QUERY = "Dynamic Query"; 

	public static String COMPONENT_ID_AUTO = "Id auto count"; 
	
	public static String COMPONENT_ID_AUTO_DISABLE = "Id auto count disable"; 
	
	public static String COMPONENT_PROTLET = "portlet"; //À¦°ó£¿
	
	public static String COMPONENT_WS = "webservice"; 
	
	public static String COMPONENT_RICHCLIENT = "richclient"; 
	
	public static String COMPONENT_JSP = "commonjsp"; 
	
	public static String HASECHILDTABLE ="hasChildTable";
	
	
	private int isPortlet;
	
	private int isWebService;
	
	private int isRichClient;
	
	private int isJSP;

	private int idAuto = 1;

	private String securityFieldName = "";

	private int ownerId = 0;
	
	private String packageName="";
	
	private String startNumber="";
	
	private String customerView = "";
	
	private String customerSP = "";
	
	private int flowAssociated = 0;
	
	private int hasChildTable = 0 ;
	
	private String oldName;

	 /**
     * @return Returns the PropertyValue.
     */
	public Object getPropertyValue(Object propName) 
	{
		return propName;
		
		
	}

	 /**
     * @param null
     *            The PropertyValue to reset.
     */
	public void resetPropertyValue(Object propName) 
	{
		
	}

	/**
	 * 
	 * @param oldName
	 *         oldName to set
	 */
	public void setOldName(String oldName)
	{
		this.oldName = oldName;
	}
	/**
	 * 
	 * @return return the oldName
	 */
	public String getOldName()
	{
		return this.oldName;
	}
	 /**
     * @param null
     *            The PropertyValue to set.
     */
	public void setPropertyValue(Object propName, Object value) 
	{


	}

	/**
	 *  @explain: construction of ComponentTable()
	 */
	public ComponentTable() 
	{
		super();

	}

	 /**
     * @return Returns the Field_Type is Component
     */
	public String getField_Type() 
	{
		return "Component"; 
	}

	/**
	 * 
	 * @return return the flowAssociated
	 */
	public int getFlowAssociated()
	{
		return flowAssociated;
	}
	
	public int getHasChildTable()
	{
		return this.hasChildTable;
	}
	
	
	 /**
     * @return Returns the ownerId.
     */
	public int getOwnerId() 
	{
		return ownerId;
	}

	 /**
     * @param owerId
     *            The ownerId to set.
     */
	public void setOwnerId(int ownerId) 
	{
		this.ownerId = ownerId;
	}

	/**
	 * 
	 * @param flowAssociated
	 *         flowAssociated to set
	 */
	public void setFlowAssociated(int flowAssociated)
	{
		this.flowAssociated = flowAssociated;
		this.firePropertyChange(COMPONENT_FLOWASSOCIATED,null,""+flowAssociated);
	}
	
	public void setHasChildTable(int hasChildTable)
	{
		this.hasChildTable = hasChildTable;
		this.firePropertyChange(HASECHILDTABLE,null,""+hasChildTable);
	
	}
	

	 /**
     * @return Returns the idAuto.
     */
	public int getIdAuto() 
	{
		return idAuto;
	}

	 /**
     * @param idAuto
     *            The idAuto to set.
     */
	public void setIdAuto(int idAuto) 
	{
		this.idAuto = idAuto;
	}

	 /**
     * @return Returns the securityFieldName.
     */
	public String getSecurityFieldName() 
	{
		return securityFieldName;
	}

	 /**
     * @param securityFieldName
     *            The securityFieldName to set.
     */
	public void setSecurityFieldName(String securityFieldName) 
	{
		this.securityFieldName = securityFieldName;
	}

	 /**
     * @return Returns the PackageName.
     */
    public String getPackageName() 
    {
        return packageName;
    }
    
    /**
     * @param PackageName
     *            The PackageName to set.
     */
    public void setPackageName(String packageName) 
    {
        this.packageName = packageName;
    }
    
    /**
     * @return Returns the startNumber.
     */
    public String getStartNumber() 
    {
        return startNumber;
    }
    
    /**
     * @param startNumber
     *            The startNumber to set.
     */
    public void setStartNumber(String startNumber) 
    {
        this.startNumber = startNumber;
    }
    
    /**
     * @return Returns the customerSP.
     */
    public String getCustomerSP() 
    {
        return customerSP;
    }
    
    /**
     * @param customerSP
     *            The customerSP to set.
     */
    public void setCustomerSP(String customerSP) 
    {
        this.customerSP = customerSP;
    }
    
    /**
     * @return Returns the customerView.
     */
    public String getCustomerView() 
    {
        return customerView;
    }
    
    /**
     * @param customerView
     *            The customerView to set.
     */
    public void setCustomerView(String customerView) 
    {
        this.customerView = customerView;
    }
    
    /**
     * @return Returns the isPortlet.
     */
    public int getIsPortlet() 
    {
        return isPortlet;
    }
    
    /**
     * @param isPortlet
     *            The isPortlet to set.
     */
    public void setIsPortlet(int isPortlet) 
    {
        this.isPortlet = isPortlet;
    }
    
    /**
     * @return Returns the isRichClient.
     */
    public int getIsRichClient() 
    {
        return isRichClient;
    }
    
    /**
     * @param isRichClient
     *            The isRichClient to set.
     */
    public void setIsRichClient(int isRichClient) 
    {
        this.isRichClient = isRichClient;
    }
    
    /**
     * @return Returns the isWebService.
     */
    public int getIsWebService() 
    {
        return isWebService;
    }
    
    /**
     * @param isWebService
     *            isWebService to set.
     */
    public void setIsWebService(int isWebService) 
    {
        this.isWebService = isWebService;
    }
    
    /**
     * @return Returns the isJSP.
     */
    public int getIsJSP() 
    {
        return isJSP;
    }
    
    /**
     * @param isJSP
     *            The isJSP to set.
     */
    public void setIsJSP(int isJSP) 
    {
        this.isJSP = isJSP;
    }

}
