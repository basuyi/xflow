package net.ms.designer.ui.view;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ms.designer.core.IOStreams;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class WriteToDataSourceXML {

	private String projectName;
	private String conName;
	private String server;
	private String userName;
	private String password;
	private String dbType;
	private String dataBase;
	private String port;
	private List dbToolList;
	
	public WriteToDataSourceXML(String status,Object type,List dbToolList)
	{
		this.dbToolList = dbToolList;
	}
	public void writeXMLFile(String outFile) throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		try { 
			db = dbf.newDocumentBuilder(); 
		} catch (ParserConfigurationException pce) { 
			System.err.println(pce); 
			System.exit(1); 
		} 
		Document doc = null; 
		doc = db.newDocument(); 
		
//		下面是建立XML文档内容的过程，先建立根元素
		Element root = doc.createElement("DataSources"); 
//		根元素添加上文档 
		doc.appendChild(root);
		
//        Element project = doc.createElement("project");	
		Element ds = doc.createElement("DataSource");
		
//		Element projectName = doc.createElement("projectName");
//		Text txtName = doc.createTextNode(getProjectName());
//		projectName.appendChild(txtName);
//		ds.appendChild(projectName);
		
		Element conName = doc.createElement("conName");
		Text conName_model = doc.createTextNode(getConName());
		conName.appendChild(conName_model);
		ds.appendChild(conName);
		
		Element server = doc.createElement("server");
		Text server_model = doc.createTextNode(getServer());
		server.appendChild(server_model);
		ds.appendChild(server);
		
		Element userName = doc.createElement("userName");
		Text userName_model = doc.createTextNode(getUserName());
		userName.appendChild(userName_model);
		ds.appendChild(userName);
		
		Element password = doc.createElement("password");
		Text password_model = doc.createTextNode(getPassWord());
		password.appendChild(password_model);
		ds.appendChild(password);
		
		Element dbType = doc.createElement("dbType");
		Text dbType_model = doc.createTextNode(getDbType());
		dbType.appendChild(dbType_model);
		ds.appendChild(dbType);
		
		Element dataBase = doc.createElement("dataBase");
		Text dataBase_model = doc.createTextNode(getDataBase());
		dataBase.appendChild(dataBase_model);
		ds.appendChild(dataBase);
		
		Element port = doc.createElement("port");
		Text port_model = doc.createTextNode(getPort());
		port.appendChild(port_model);
		ds.appendChild(port);
		
//		project.appendChild(ds);
		root.appendChild(ds);
		
		
		//		把XML文档输出到指定的文件 
//		FileOutputStream outStream = new FileOutputStream(outFile); 
//		OutputStreamWriter outWriter = new OutputStreamWriter(outStream); 
//		((XmlDocument) doc).write(outWriter, "UTF-8");  //应用utf-8编码方式
//		outWriter.close(); 
//		outStream.close();
		IOStreams ioStreams = new IOStreams ();
		ioStreams.transformToFile(doc,outFile);

		
		
	}
	
	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}
	public String getProjectName()
	{
		return this.projectName;
	}
	public void setConName(String conName)
	{
		this.conName = conName;
	}
	public String getConName()
	{
		return this.conName;
	}
	public void setServer(String server)
	{
		this.server = server;
	}
	public String getServer()
	{
		return this.server;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getUserName()
	{
		return this.userName;
	}
	public  void setPassWord(String passWord)
	{
		this.password = passWord;
	}
	public String getPassWord()
	{
		return this.password;
	}
	public void setDbType(String dbType)
	{
		this.dbType = dbType;
	}
	public String getDbType()
	{
		return this.dbType;
	}
	public void setDataBase(String dataBase)
	{
		this.dataBase = dataBase;
	}
	public String getDataBase()
	{
		return this.dataBase;
	}
	public void setPort(String port)
	{
		this.port = port;
	}
	public String getPort()
	{
		return this.port;
	}
}
