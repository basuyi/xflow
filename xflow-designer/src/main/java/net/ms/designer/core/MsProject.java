package net.ms.designer.core;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import net.ms.designer.projectbuilder.service.ProjectBuilder;



public class MsProject implements Serializable
{
	//xml文件里面的变量

	private String projectName = ""; //名称
	private String iname = "";
	private String desc = "";
	private String locale = "";
    private String company = "";
    private String enumPackageName = "";
    private String bizPackageName = "";
    private String enumCompName = "";
    private String bizCompName = "";
    private String componentID = "";
    private String fieldName = "";
    private String fieldType = "";
    private String fieldListable = "";
    private String fieldSearchable = "";
    //used for the package prefix of generated java file 
    private String genPackageName = "";
    //2006.09.27 updating 
    private String packageName = "";
    private String componentName = "";
    private String type = "";
    private String name = "";
    //updated by liuchunxia
    private String workflowName;
    private String workflowIname;
    private String workflowId;
    private String subflowName;
//    private String subflowIname;
//    private String subflowPath;
    private String subflowId;
//    private String subflowDesc;
//    private boolean isSubflow = false;
//    private List subflowParaList;
    private ProjectBuilder projectBuilder;
    
    //xml文件里的列表
    private List packages;
    private List participant;
    private List applicationList;
    private List subflowList;
    transient private DBTool dbtool;
    
    //page 里面的变量
    private String directory;   //目录
    private String workplace;   //工作目录
    private String cmpy_short;  //公司简称
    private String author = "";//作者
    private String version = "";//版本
    private String dblink;//数据库连接
    private String configPath;
   // private DBTool dbtool; //数据库配置
    
    
	//get set 方法
	
    //xml文件里面的变量
	
    public String getProjectName(){
	return this.projectName;	
	} //名称
    public void setProjectName(String projectName){
    	this.projectName = projectName;
    }
    
    public String getFieldName()
    {
    	return this.fieldName;
    }
    public void setFieldName(String fieldName)
    {
    	this.fieldName = fieldName;
    }
    public String getFieldType()
    {
    	return this.fieldType;
    }
    public void setFieldType(String fieldType)
    {
    	this.fieldType = fieldType;
    }
    public String getFieldListable()
    {
    	return this.fieldListable;
    }
    public void setFieldListable(String fieldListable)
    {
    	this.fieldListable = fieldListable;
    }
    public String getFieldSearchable()
    {
    	return this.fieldSearchable;
    }
    public void setFieldSearchable(String fieldSearchable)
    {
    	this.fieldSearchable = fieldSearchable;
    }
	public  String getIname(){
		return this.iname;
	}
	public void setIname(String iname){
		this.iname = iname;
	}
	
	
	public String getDesc(){
		return this.desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public String getLocale(){
	    return this.locale;	
	}
	public void setLocale(String locale){
		this.locale = locale;
	}
	
	public String getPackageName()
	{
		return this.packageName;
	}
	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}
	
	public String getComponentName()
	{
		return this.componentName;
	}
	public void setComponentName(String componentName)
	{
		this.componentName = componentName;
	}
	
	public String getType()
	{
		return this.type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	
    public  String getEnumPackageName(){
    	return this.enumPackageName;
    }
    public void setEnumPackageName(String enumPackageName){
    	this.enumPackageName = enumPackageName;
    }
    
    public String getEnumCompName()
    {
    	return this.enumCompName;
    }
    public void setEnumCompName(String enumCompName)
    {
    	this.enumCompName = enumCompName;
    }
    
    public String getBizCompName()
    {
    	return this.bizCompName;
    }
    public void setBizCompName(String bizCompName)
    {
    	this.bizCompName = bizCompName;
    }
    
    
    public String getCompany(){
    	return this.company;
    }
    public void setCompany(String company){
    	this.company = company;
    }
    
    
    //xml文件里的列表
    
    public List getPackages(){
    	return this.packages;
    }
    public void setPackages(List packages){
    	this.packages=packages;
    }
    
    
    public  List getParticipant(){
    	return this.participant;
    }
    public void setParticipant(List participant){
    	this.participant=participant;
    }
    
    //update by lcx
    public List getApplicationList(){
    	return this.applicationList;
    }
    public void setApplicationList(List applicationList){
    	this.applicationList=applicationList;
    }
    
    public String getComponentID()
    {
    	return this.componentID;
    }
    public void setComponentID(String componentID)
    {
    	this.componentID = componentID;
    }
    //page 里面的变量
   
    public String getDirectory(){
    	return this.directory;
    }   //目录
    public void setDirectory(String directory){
    	this.directory=directory;
    }
    
    
    public String getWorkplace(){
    	return this.workplace;
    }   //工作目录
    public void setWorkplace(String workplace){
    	this.workplace=workplace;
    }
    
    
    public String getCmpy_short(){
    	return this.cmpy_short;
    }  //公司简称
    public void setCmpy_short(String cmpy_short){
    	this.cmpy_short=cmpy_short;
    }
    
    
    public String getAuthor(){
    	return this.author;
    }//作者
    public void setAuthor(String author){
    	this.author=author;
    }
    
    
    public String getVersion(){
    	return this.version;
    }//版本
    public void setVersion(String version){
    	this.version=version;
    }
    
    
    
    public  String getDblink(){
    	return this.dblink;
    }//数据库连接
    public  void setDblink(String dblink){
    	this.dblink=dblink;
    }
//    get the path of "Configure" directory
    public String getConfigPath()
    {
    	StringBuffer sb = new StringBuffer(this.getDirectory());
		sb.append(File.separator);
		sb.append(".configure\\");
		return sb.toString();
    }
    public void setConfigPath(String path)
    {
    	this.configPath = path;
    }
    
    public String getGenPackageName()
    {
    	return this.genPackageName;
    }
    public void setGenPackageName(String genPackageName)
    {
    	this.genPackageName = genPackageName;
    }
    
    
    public  Connection getConnection(){
    return this.dbtool.getConnection();
    } //数据库配置
    
    public void setDbtool(DBTool dbtool){
     this.dbtool=dbtool;
    }
    
    public void setWorkflowName(String workflowName)
    {
    	this.workflowName = workflowName;
    }
    
    public String getWorkflowName()
    {
    	return this.workflowName;
    }
    
    public void setWorkflowIname(String workflowIname)
    {
    	this.workflowIname = workflowIname;
    }
    
    public String getWorkflowIname()
    {
    	return this.workflowIname;
    }
    
    public void setWorkflowId(String workflowId)
    {
    	this.workflowId = workflowId;
    }
    
    public String getWorkflowId()
    {
    	return this.workflowId;
    }
    
    public void setSubflowName(String subflowName)
    {
    	this.subflowName = subflowName;
    }
    
    public String getSubflowName()
    {
    	return this.subflowName;
    }
//    
//    public void setSubflowPath(String subflowPath)
//    {
//    	this.subflowPath = subflowPath;
//    }
//    
//    public String getSubflowPath()
//    {
//    	return this.subflowPath;
//    }
//    
    public void setSubflowId(String subflowId)
    {
    	this.subflowId = subflowId;
    }
    
    public String getSubflowId()
    {
    	return this.subflowId;
    }
//    
//    public void setSubflowParaList(List subflowParaList)
//    {
//    	this.subflowParaList = subflowParaList;
//    }
//    
//    public List getSubflowParaList()
//    {
//    	return this.subflowParaList;
//    }
//    
    public void setSubflowList(List subflowList)
    {
    	this.subflowList = subflowList;
    }
    
    public List getSubflowList()
    {
    	return this.subflowList;
    }
    
    public String getName()
    {
    	return this.name;
    }
    public void setName(String name)
    {
    	this.name = name;
    }
//    
//    public void setIsSubflow(boolean isSubflow)
//    {
//    	this.isSubflow = isSubflow;
//    }
//    
//    public boolean getIsSubflow()
//    {
//    	return this.isSubflow;
//    }
//    
//    public void setSubflowIname(String subflowIname)
//    {
//    	this.subflowIname = subflowIname;
//    }
//    
//    public String getSubflowIname()
//    {
//    	return this.subflowIname;
//    }
//    
//    public void setSubflowDesc(String subflowDesc)
//    {
//    	this.subflowDesc = subflowDesc;
//    }
//    
//    public String getSubflowDesc()
//    {
//    	return this.subflowDesc;
//    }
	/**
	 * @return the appContext
	 */
	public ProjectBuilder getProjectBuilder() {
		return projectBuilder;
	}
	/**
	 * @param appContext the appContext to set
	 */
	public void setProjectBuilder(ProjectBuilder projectBuilder) {
		this.projectBuilder = projectBuilder;
	}
    
//	private String proName;
//	
//	public void setProName(String proName){
//		this.proName = proName;
//	}
//	
//	public String getProName(){
//		return this.proName;
//	}
	
//	public CEECProject(String proName){
//		this.proName = proName;
//	}
//	
	
}
