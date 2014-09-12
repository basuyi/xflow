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
//			name�����
			Element name = doc.createElement("name");
			Text name_model = doc.createTextNode(""+this.cproject.getProjectName());
			name.appendChild(name_model);
			project.appendChild(name);
		
		//iname����ʻ�����
			Element iname = doc.createElement("iname");
			CDATASection iname_model = doc.createCDATASection(""+this.cproject.getIname());
			iname.appendChild(iname_model);
			project.appendChild(iname);
		
//		desc���Ŀ����
			Element desc = doc.createElement("desc");
			CDATASection desc_model = doc.createCDATASection(""+this.cproject.getDesc());
			desc.appendChild(desc_model);
			project.appendChild(desc);
		
		//locale���������
			Element locale = doc.createElement("locale");
			CDATASection locale_model = doc.createCDATASection(""+this.cproject.getLocale());
			locale.appendChild(locale_model);
			project.appendChild(locale);
		
		//packagename���Ŀ�İ���
			
				
			Element packagename = doc.createElement("packagename");
			CDATASection packagename_model = doc.createCDATASection("com."+this.cproject.getCmpy_short()+"."+this.cproject.getProjectName());
			packagename.appendChild(packagename_model);
			project.appendChild(packagename);
		
		//company��
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
//	 * ������
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
//		��Ԫ��������ĵ� 
		doc.appendChild(root); 
		
		Element project = doc.createElement("project");
		
		root.appendChild(project);	
		
//		��XML�ĵ������ָ�����ļ� 
//		FileOutputStream outStream = new FileOutputStream(outFile); 
//		OutputStreamWriter outWriter = new OutputStreamWriter(outStream); 
////		((XmlDocument) doc).write(outWriter, "gb2312"); 
//		((XmlDocument) doc).write(outWriter, "UTF-8");  //Ӧ��utf-8���뷽ʽ
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
//            //name�����
//			Element p1name = doc.createElement("name");
//			Text p1name_model = doc.createTextNode("�����������:pack1");
//			p1name.appendChild(p1name_model);
//			package1.appendChild(p1name);
//			
//			//iname����ʻ�����
//			Element p1iname = doc.createElement("iname");
//			CDATASection p1iname_model = doc.createCDATASection("���ʻ�����,�磺�����1");
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
//			//			name�����
//			Element compname = doc.createElement("name");
//			Text compname_model = doc.createTextNode("�������,�磺����");
//			compname.appendChild(compname_model);
//			component.appendChild(compname);
//			
//			//			iname����ʻ�����
//			Element compiname = doc.createElement("iname");
//			CDATASection compiname_model = doc.createCDATASection("���ʻ�����,�磺����");
//			compiname.appendChild(compiname_model);
//			component.appendChild(compiname);
//			
//			//			desc��������
//			Element compdesc = doc.createElement("desc");
//			CDATASection compdesc_model = doc.createCDATASection("����������磺����һ�����Ե�����");
//			compdesc.appendChild(compdesc_model);
//			component.appendChild(compdesc);
//			
//			//			path�·��(xml�ļ���)
//			Element comppath = doc.createElement("path");
//			CDATASection comppath_model = doc.createCDATASection("·��(xml�ļ���)���磺test.pack1.comp1");
//			comppath.appendChild(comppath_model);
//			component.appendChild(comppath);
//			
//			//			hasWf��Ƿ������̣�0��1
//			Element comphasWf = doc.createElement("hasWf");
//			Text comphasWf_model = doc.createTextNode("�Ƿ�������,0��1");
//			comphasWf.appendChild(comphasWf_model);
//			component.appendChild(comphasWf);
//			
//			//			workflow������������˲�����
//			Element workflow = doc.createElement("workflow");
//			
//			Element wfName = doc.createElement("name");
//			Text wfName_model = doc.createTextNode("������EN");
//			wfName.appendChild(wfName_model);
//			workflow.appendChild(wfName);
//			
//			Element wfIname = doc.createElement("iname");
//			CDATASection wfIname_model = doc.createCDATASection("������CN�����ʻ�����");
//			wfIname.appendChild(wfIname_model);
//			workflow.appendChild(wfIname);
//			
//			Element wfPath = doc.createElement("path");
//			CDATASection wfPath_model = doc.createCDATASection("������̵�xml�ļ���");
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
//			//participant�һ������ߣ�����ж��ֻ��ѭ��participant�е����ݣ��кŷ�Χ�ǣ�152-178
//			Element participant = doc.createElement("participant");
//			
//			Element partiName = doc.createElement("name");
//			Text partiName_model = doc.createTextNode("ִ�������ƣ�Ҫ��Ӣ�ģ�");
//			partiName.appendChild(partiName_model);
//			participant.appendChild(partiName);
//			
////			desc�ִ��������
//			Element partidesc = doc.createElement("desc");
//			CDATASection partidesc_model = doc.createCDATASection("ִ�����������磺����");
//			partidesc.appendChild(partidesc_model);
//			participant.appendChild(partidesc);
//			
//			//type�ִ��������,1��2���ֱ����java��ͽű�
//			//����java�࣬Ҫ���ڽ�������path�����������������ƣ��ű�Ҫ��������ķ���
//			Element partiType = doc.createElement("type");
//			Text partiType_model = doc.createTextNode("ִ�������ͣ�1��2");
//			partiType.appendChild(partiType_model);
//			participant.appendChild(partiType);
//			
//			//����java�࣬Ҫ���������������ƣ��ű�Ҫ��������ķ���
//			Element partiPath = doc.createElement("path");
//			Text partiPath_model = doc.createTextNode("�����򷽷���,�磺net.ms.part.parti1.getuser(session)");
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
//			//wfapplications�СӦ�ÿ�
//			Element wfapplication = doc.createElement("wfapplication");
//			
//			//application�һ��Ӧ�ã�����ж��Ӧ��ֻ��ѭ�������application�е����ݣ��к��ǣ�186-247
//			Element application = doc.createElement("application");
//			
////			name�Ӧ������
//			Element appname = doc.createElement("name");
//			CDATASection appname_model = doc.createCDATASection("����,�磺����");
//			appname.appendChild(appname_model);
//			application.appendChild(appname);
//			
////			desc�Ӧ������
//			Element appdesc = doc.createElement("desc");
//			CDATASection appdesc_model = doc.createCDATASection("Ӧ���������磺����һ�����Ե�����");
//			appdesc.appendChild(appdesc_model);
//			application.appendChild(appdesc);
//			
//			//type�ִ��������,1��2���ֱ����ϵͳӦ�ú��û�Ӧ��
//			//����javaӦ�õĴ�java����Ҫ���������path�������������ƣ�WEBӦ�������漰���ݽ϶࣬����ҳ�棬�ʹ�����
//			Element appType = doc.createElement("type");
//			Text appType_model = doc.createTextNode("Ӧ�����ͣ�1��2");
//			appType.appendChild(appType_model);
//			application.appendChild(appType);
//			
////			����javaӦ�õĴ�java����Ҫ�������������ƣ�WEBӦ�������漰���ݽ϶࣬����ҳ�棬�ʹ�����
//			Element appPath = doc.createElement("path");
//			Text appPath_model = doc.createTextNode("����������,�磺app1.action)");
//			appPath.appendChild(appPath_model);
//			application.appendChild(appPath);
//			
//			//params�Ӧ�õĲ����б�
//			Element params = doc.createElement("params");
//			//param�һ������������,����Ƕ��������ѭ���������ǣ�217-243��
//			Element param = doc.createElement("param");
//			
////			name���������
//			Element paramName = doc.createElement("name");
//			Text paramName_model = doc.createTextNode("�������ƣ�ֻ����Ӣ�ĺ�����,�磺id");
//			paramName.appendChild(paramName_model);
//			param.appendChild(paramName);
//			
//			//type���������
//			Element paramType = doc.createElement("type");
//			Text paramType_model = doc.createTextNode("�������ͣ��磺2");
//			paramType.appendChild(paramType_model);
//			param.appendChild(paramType);
//			
//			//isinput��Ƿ����������
//			Element isinput = doc.createElement("isinput");
//			Text isinput_model = doc.createTextNode("�Ƿ������������0��1");
//			isinput.appendChild(isinput_model);
//			param.appendChild(isinput);
//			
//			//isoutput��Ƿ����������
//			Element isoutput = doc.createElement("isoutput");
//			Text isoutput_model = doc.createTextNode("�Ƿ������������0��1");
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
////		�����ǽ���XML�ĵ����ݵĹ��̣��Ƚ�����Ԫ��
//		Element root = doc.createElement("richweb"); 
////		��Ԫ��������ĵ� 
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
//		//wfparticipants��,�����߻�ִ����
//				
//		
//		root.appendChild(project);		
//		
////		��XML�ĵ������ָ�����ļ� 
//		FileOutputStream outStream = new FileOutputStream(outFile); 
//		OutputStreamWriter outWriter = new OutputStreamWriter(outStream); 
////		((XmlDocument) doc).write(outWriter, "gb2312"); 
//		((XmlDocument) doc).write(outWriter, "UTF-8");  //Ӧ��utf-8���뷽ʽ
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
