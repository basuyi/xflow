package net.ms.designer.ui;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.ms.designer.core.MsProject;
import net.ms.designer.core.IOStreams;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;



public class WriteToProjectXML 
{
	
//	private String status;
//	String conName;
//	String dbType;
//	String port;
//	String username;
//	String password;
//	String server;
//	String dbName;
//	String url;
	Object type;
	MsProject cproject;

	
	public WriteToProjectXML(Object type,MsProject cproject)
	{
		this.type = type;
		this.cproject = cproject;
	}
//	public WriteToProjectXML()
//	{
//		
//	}
//	
	
	
	public void accessXMLFile(String file)throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		db = dbf.newDocumentBuilder();
		Document doc = null; 
		File f = new File(file);
		if(f.exists()){
			doc = db.parse(file);
			doc.normalize(); 
		}
		
//		if the element to insert is project ,new a "project.xml" file
//		if(status.equals("Project"))
//		{
//			this.writeXMLFile(file);

			NodeList list = doc.getElementsByTagName("project");
			
			Element project = (Element)list.item(0);
//			name项，名称
			Element name = doc.createElement("name");
			Text name_model = doc.createTextNode(""+this.cproject.getProjectName());
			name.appendChild(name_model);
			project.appendChild(name);
		
		//iname项，国际化名称
			Element iname = doc.createElement("iname");
			CDATASection iname_model = doc.createCDATASection(""+this.cproject.getIname());
			iname.appendChild(iname_model);
			project.appendChild(iname);
		
//		desc项，项目描述
			Element desc = doc.createElement("desc");
			CDATASection desc_model = doc.createCDATASection(""+this.cproject.getDesc());
			desc.appendChild(desc_model);
			project.appendChild(desc);
		
		//locale项，区域描述
			Element locale = doc.createElement("locale");
			CDATASection locale_model = doc.createCDATASection(""+this.cproject.getLocale());
			locale.appendChild(locale_model);
			project.appendChild(locale);
		
		//packagename项，项目的包名
			
				
			Element packagename = doc.createElement("packagename");
			CDATASection packagename_model = doc.createCDATASection("com."+this.cproject.getCmpy_short()+"."+this.cproject.getProjectName());
			packagename.appendChild(packagename_model);
			project.appendChild(packagename);
		
		//company项
			Element company = doc.createElement("company");
			CDATASection company_model = doc.createCDATASection(""+this.cproject.getCmpy_short());
			company.appendChild(company_model);
			project.appendChild(company);
//		}
	
	
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new java.io.File(file));
		transformer.transform(source, result); 
	}
//
//	/**
//	 * 测试用
//	 * @param outFile
//	 * @throws Exception
//	 */
	public void writeProjectElement(String outFile) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		try { 
			db = dbf.newDocumentBuilder(); 
		} catch (ParserConfigurationException pce) { 
			System.err.println(pce); 
			System.exit(1); 
			pce.printStackTrace();
		} 
		Document doc = null; 
		doc = db.newDocument(); 
		
		Element root = doc.createElement("richweb"); 
//		根元素添加上文档 
		doc.appendChild(root); 
		
		Element project = doc.createElement("project");
		
		root.appendChild(project);	
		
//		把XML文档输出到指定的文件 
//		FileOutputStream outStream = new FileOutputStream(outFile); 
//		OutputStreamWriter outWriter = new OutputStreamWriter(outStream); 
////		((XmlDocument) doc).write(outWriter, "gb2312"); 
//		((XmlDocument) doc).write(outWriter, "UTF-8");  //应用utf-8编码方式
//		outWriter.close(); 
//		outStream.close();
		IOStreams ioStreams = new IOStreams ();
		ioStreams.transformToFile(doc,outFile);

	}

	
	public static void main(String[] args){
		WriteToProjectXML write = new WriteToProjectXML(null,null);
		try{
			write.writeProjectElement("E:\\project.xml");
			//System.out.println("finished");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//		insert the datasource tag
//		if(status.equals("DataSource"))
//		{
//			writeXMLFile(file);
//			
//		}
////		insert the package tag
//		if(status.equals("Package"))
//		{
//			NodeList list = doc.getElementsByTagName("project");
//			Element package1 = doc.createElement("package");
//			
//            //name项，名称
//			Element p1name = doc.createElement("name");
//			Text p1name_model = doc.createTextNode("组件包名，如:pack1");
//			p1name.appendChild(p1name_model);
//			package1.appendChild(p1name);
//			
//			//iname项，国际化名称
//			Element p1iname = doc.createElement("iname");
//			CDATASection p1iname_model = doc.createCDATASection("国际化名称,如：组件包1");
//			p1iname.appendChild(p1iname_model);
//			package1.appendChild(p1iname);
//			
//			list.item(0).appendChild(package1);
//		}
////		
////		insert the component tag
////		not finished
//		if(status.equals("Component"))
//		{
//			NodeList list = doc.getElementsByTagName("package");
//			
//			Element component = doc.createElement("component");
//
//			//			name项，名称
//			Element compname = doc.createElement("name");
//			Text compname_model = doc.createTextNode("组件名称,如：测试");
//			compname.appendChild(compname_model);
//			component.appendChild(compname);
//			
//			//			iname项，国际化名称
//			Element compiname = doc.createElement("iname");
//			CDATASection compiname_model = doc.createCDATASection("国际化名称,如：测试");
//			compiname.appendChild(compiname_model);
//			component.appendChild(compiname);
//			
//			//			desc项，组件描述
//			Element compdesc = doc.createElement("desc");
//			CDATASection compdesc_model = doc.createCDATASection("组件描述，如：这是一个测试的例子");
//			compdesc.appendChild(compdesc_model);
//			component.appendChild(compdesc);
//			
//			//			path项，路径(xml文件名)
//			Element comppath = doc.createElement("path");
//			CDATASection comppath_model = doc.createCDATASection("路径(xml文件名)，如：test.pack1.comp1");
//			comppath.appendChild(comppath_model);
//			component.appendChild(comppath);
//			
//			//			hasWf项，是否有流程，0或1
//			Element comphasWf = doc.createElement("hasWf");
//			Text comphasWf_model = doc.createTextNode("是否有流程,0或1");
//			comphasWf.appendChild(comphasWf_model);
//			component.appendChild(comphasWf);
//			
//			//			workflow项，如果有流程则此部分有
//			Element workflow = doc.createElement("workflow");
//			
//			Element wfName = doc.createElement("name");
//			Text wfName_model = doc.createTextNode("流程名EN");
//			wfName.appendChild(wfName_model);
//			workflow.appendChild(wfName);
//			
//			Element wfIname = doc.createElement("iname");
//			CDATASection wfIname_model = doc.createCDATASection("流程名CN，国际化名字");
//			wfIname.appendChild(wfIname_model);
//			workflow.appendChild(wfIname);
//			
//			Element wfPath = doc.createElement("path");
//			CDATASection wfPath_model = doc.createCDATASection("相关流程的xml文件名");
//			wfPath.appendChild(wfPath_model);
//			workflow.appendChild(wfPath);
//			
//			component.appendChild(workflow);		
//			
//			for(int i = 0 ; i < list.getLength(); i++)
//			{
//				//select which package to insert by checking the package name
//				if(((Element)list.item(i)).getElementsByTagName("name")
//						.equals(""))
//				{
//					((Element)list.item(i)).appendChild(component);
//				}
//			}
//		}
//
////		insert the wfparticipants tag
//		if(status.equals("wfparticipants"))
//		{
//			Element wfparticipants = doc.createElement("wfparticipants");
//			
//			//participant项，一类参与者，如果有多个只需循环participant中的内容，行号范围是：152-178
//			Element participant = doc.createElement("participant");
//			
//			Element partiName = doc.createElement("name");
//			Text partiName_model = doc.createTextNode("执行者名称（要求英文）");
//			partiName.appendChild(partiName_model);
//			participant.appendChild(partiName);
//			
////			desc项，执行者描述
//			Element partidesc = doc.createElement("desc");
//			CDATASection partidesc_model = doc.createCDATASection("执行者描述，如：经理");
//			partidesc.appendChild(partidesc_model);
//			participant.appendChild(partidesc);
//			
//			//type项，执行者类型,1或2，分别代表java类和脚本
//			//其中java类，要求在接下来的path项中输入完整类名称；脚本要求输入类的方法
//			Element partiType = doc.createElement("type");
//			Text partiType_model = doc.createTextNode("执行者类型，1或2");
//			partiType.appendChild(partiType_model);
//			participant.appendChild(partiType);
//			
//			//其中java类，要求输入完整类名称；脚本要求输入类的方法
//			Element partiPath = doc.createElement("path");
//			Text partiPath_model = doc.createTextNode("类名或方法名,如：net.ms.part.parti1.getuser(session)");
//			partiPath.appendChild(partiPath_model);
//			participant.appendChild(partiPath);
//			
//			wfparticipants.appendChild(participant);
//			
////			project.appendChild(wfparticipants);	
//		}
////		insert wfapplication tag
//		if(status.equals("wfapplication"))
//		{
//			//wfapplications项，小应用库
//			Element wfapplication = doc.createElement("wfapplication");
//			
//			//application项，一个应用，如果有多个应用只需循环下面的application中的内容，行号是：186-247
//			Element application = doc.createElement("application");
//			
////			name项，应用名称
//			Element appname = doc.createElement("name");
//			CDATASection appname_model = doc.createCDATASection("名称,如：测试");
//			appname.appendChild(appname_model);
//			application.appendChild(appname);
//			
////			desc项，应用描述
//			Element appdesc = doc.createElement("desc");
//			CDATASection appdesc_model = doc.createCDATASection("应用描述，如：这是一个测试的例子");
//			appdesc.appendChild(appdesc_model);
//			application.appendChild(appdesc);
//			
//			//type项，执行者类型,1或2，分别代表系统应用和用户应用
//			//其中java应用的纯java程序，要求在下面的path项输入完整名称；WEB应有由于涉及内容较多，包括页面，和处理动作
//			Element appType = doc.createElement("type");
//			Text appType_model = doc.createTextNode("应用类型，1或2");
//			appType.appendChild(appType_model);
//			application.appendChild(appType);
//			
////			其中java应用的纯java程序，要求输入完整名称；WEB应有由于涉及内容较多，包括页面，和处理动作
//			Element appPath = doc.createElement("path");
//			Text appPath_model = doc.createTextNode("类名或动作名,如：app1.action)");
//			appPath.appendChild(appPath_model);
//			application.appendChild(appPath);
//			
//			//params项，应用的参数列表
//			Element params = doc.createElement("params");
//			//param项，一个参数的内容,如果是多个参数则循环的内容是：217-243行
//			Element param = doc.createElement("param");
//			
////			name项，参数名称
//			Element paramName = doc.createElement("name");
//			Text paramName_model = doc.createTextNode("参数名称，只能是英文和数字,如：id");
//			paramName.appendChild(paramName_model);
//			param.appendChild(paramName);
//			
//			//type项，参数类型
//			Element paramType = doc.createElement("type");
//			Text paramType_model = doc.createTextNode("参数类型，如：2");
//			paramType.appendChild(paramType_model);
//			param.appendChild(paramType);
//			
//			//isinput项，是否是输入参数
//			Element isinput = doc.createElement("isinput");
//			Text isinput_model = doc.createTextNode("是否是输入参数，0或1");
//			isinput.appendChild(isinput_model);
//			param.appendChild(isinput);
//			
//			//isoutput项，是否是输出参数
//			Element isoutput = doc.createElement("isoutput");
//			Text isoutput_model = doc.createTextNode("是否是输出参数，0或1");
//			isoutput.appendChild(isoutput_model);
//			param.appendChild(isoutput);		
//			
//			params.appendChild(param);		
//			
//			application.appendChild(params);
//			
//			wfapplication.appendChild(application);		
//			
////			project.appendChild(wfapplication);	
//		}
		
		

//	}
	
//	public  void writeXMLFile(String outFile) throws Exception
//	{
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
//		DocumentBuilder db = null; 
//		try 
//		{ 
//			db = dbf.newDocumentBuilder(); 
//		} 
//		catch (ParserConfigurationException pce) 
//		{ 
//			System.err.println(pce); 
//			System.exit(1); 
//		} 
//		Document doc = null; 
//		doc = db.newDocument();
//
//		
////		下面是建立XML文档内容的过程，先建立根元素
//		Element root = doc.createElement("richweb"); 
////		根元素添加上文档 
//		doc.appendChild(root); 
//	
//		Element project = doc.createElement("project");
//		
//		Element ds = doc.createElement("DataSource");
//		
//		Element name = doc.createElement("DataSourceName");
//		Text txtName = doc.createTextNode(getConName());
//		name.appendChild(txtName);
//		ds.appendChild(name);
//		
//		Element userName = doc.createElement("username");
//		Text txtUserName = doc.createTextNode(getUsername());
//		userName.appendChild(txtUserName);
//		ds.appendChild(userName);
//	
//		Element passWord = doc.createElement("password");
//		Text txtPassword = doc.createTextNode(getPassword());
//		passWord.appendChild(txtPassword);
//		ds.appendChild(passWord);
//		
//		Element type = doc.createElement("type");
//		Text txtType = doc.createTextNode(getDbType());
//		type.appendChild(txtType);
//		ds.appendChild(type);
//		
//		Element database = doc.createElement("database");
//		Text txtDB = doc.createTextNode(getDbName());
//		database.appendChild(txtDB);
//		ds.appendChild(database);
//		
//		Element ePort = doc.createElement("port");
//		Text txtPort = doc.createTextNode(getPort());
//		ePort.appendChild(txtPort);
//		ds.appendChild(ePort);
//		
//		Element eServer = doc.createElement("server");
//		Text txtServer = doc.createTextNode(getServer());
//		eServer.appendChild(txtServer);
//		ds.appendChild(eServer);
//		
//		Element max = doc.createElement("maxConnections");
//		Text txtMax = doc.createTextNode("10");
//		max.appendChild(txtMax);
//		ds.appendChild(max);
//		
//		project.appendChild(ds);
//		//wfparticipants项,参与者或执行者
//				
//		
//		root.appendChild(project);		
//		
////		把XML文档输出到指定的文件 
//		FileOutputStream outStream = new FileOutputStream(outFile); 
//		OutputStreamWriter outWriter = new OutputStreamWriter(outStream); 
////		((XmlDocument) doc).write(outWriter, "gb2312"); 
//		((XmlDocument) doc).write(outWriter, "UTF-8");  //应用utf-8编码方式
//		outWriter.close(); 
//		outStream.close();
//	
//	}
	
////	DBType
//	public String getDbType()
//	{
//		return this.dbType;
//	}
//	public void setDbType(String str)
//	{
//		this.dbType = str;
//	}
////	DBName
//	public String getDbName()
//	{
//		return this.dbName;
//	}
//	public void setDbName(String str)
//	{
//		this.dbName = str;
//	}
////	server
//	public String getServer()
//	{
//		return this.server;
//	}
//	public void setServer(String str)
//	{
//		this.server = str;
//	}
////	Port
//	public String getPort()
//	{
//		return this.port;
//	}
//	public void setPort(String str)
//	{
//		this.port = str;
//	}
////	userName
//	public String getUsername()
//	{
//		return this.username;
//	}
//	public void setUsername(String str)
//	{
//		this.username = str;
//	}
////	password
//	public String getPassword()
//	{
//		return this.password;
//	}
//	public void setPassword(String str)
//	{
//		this.password = str;
//	}
////	url
//	public String getUrl()
//	{
//		return this.url;
//	}
//	public void setUrl(String str)
//	{
//		this.url = str;
//	}
////	connect name
//	public String getConName()
//	{
//		return this.conName;
//	}
//	public void setConName(String str)
//	{
//		this.conName = str;
//	}

}
