package net.ms.designer.ui.preference;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ms.designer.core.IOStreams;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public class WriteToProjectXML 
{
	
	private String status;
	String conName;
	String dbType;
	String port;
	String username;
	String password;
	String server;
	String dbName;
	String url;
	Object type;
	
	private String projectName;

	
	public WriteToProjectXML(String status,Object type,String projectName)
	{
		this.status = status;
		this.projectName = projectName;
	}
	
	public void accessXMLFile(String file)throws Exception
	{
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
//		DocumentBuilder db = null; 
//		db = dbf.newDocumentBuilder();
//		Document doc = null; 
//		doc = db.parse(file);
//		doc.normalize(); 
//		if the element to insert is project ,new a "project.xml" file
		if(status.equals("Project"))
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = null; 
			db = dbf.newDocumentBuilder();
			Document doc = null; 
			doc = db.parse(file);
			doc.normalize(); 

			NodeList list = doc.getElementsByTagName("project");
//			name�����
			Element name = doc.createElement("name");
			Text name_model = doc.createTextNode("����,�磺����");
			name.appendChild(name_model);
			list.item(0).appendChild(name);
		
		//iname����ʻ�����
			Element iname = doc.createElement("iname");
			CDATASection iname_model = doc.createCDATASection("���ʻ�����,�磺����");
			iname.appendChild(iname_model);
			list.item(0).appendChild(iname);
		
//		desc���Ŀ����
			Element desc = doc.createElement("desc");
			CDATASection desc_model = doc.createCDATASection("��Ŀ�������磺����һ�����Ե�����");
			desc.appendChild(desc_model);
			list.item(0).appendChild(desc);
		
		//locale���������
			Element locale = doc.createElement("locale");
			CDATASection locale_model = doc.createCDATASection("��Ŀ����ʱ��,�磺zh_CN");
			locale.appendChild(locale_model);
			list.item(0).appendChild(locale);
		
		//packagename���Ŀ�İ���
			
				
			Element packagename = doc.createElement("packagename");
			CDATASection packagename_model = doc.createCDATASection("��Ŀ�İ������磺net.ms.test");
			packagename.appendChild(packagename_model);
			list.item(0).appendChild(packagename);
		
		//company��
			Element company = doc.createElement("company");
			CDATASection company_model = doc.createCDATASection("��˾��");
			company.appendChild(company_model);
			list.item(0).appendChild(company);
		}
//		insert the datasource tag
		if(status.equals("DataSource"))
		{
			writeXMLFile(file);
			
		}
//		insert the package tag
		if(status.equals("Package"))
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = null; 
			db = dbf.newDocumentBuilder();
			Document doc = null; 
			doc = db.parse(file);
			doc.normalize(); 
			
			NodeList list = doc.getElementsByTagName("project");
			Element package1 = doc.createElement("package");
			
            //name�����
			Element p1name = doc.createElement("name");
			Text p1name_model = doc.createTextNode("�����������:pack1");
			p1name.appendChild(p1name_model);
			package1.appendChild(p1name);
			
			//iname����ʻ�����
			Element p1iname = doc.createElement("iname");
			CDATASection p1iname_model = doc.createCDATASection("���ʻ�����,�磺�����1");
			p1iname.appendChild(p1iname_model);
			package1.appendChild(p1iname);
			
			list.item(0).appendChild(package1);
		}
//		
//		insert the component tag
//		not finished
		if(status.equals("Component"))
		{
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = null; 
			db = dbf.newDocumentBuilder();
			Document doc = null; 
			doc = db.parse(file);
			doc.normalize(); 
			NodeList list = doc.getElementsByTagName("package");
			
			Element component = doc.createElement("component");

			//			name�����
			Element compname = doc.createElement("name");
			Text compname_model = doc.createTextNode("�������,�磺����");
			compname.appendChild(compname_model);
			component.appendChild(compname);
			
			//			iname����ʻ�����
			Element compiname = doc.createElement("iname");
			CDATASection compiname_model = doc.createCDATASection("���ʻ�����,�磺����");
			compiname.appendChild(compiname_model);
			component.appendChild(compiname);
			
			//			desc��������
			Element compdesc = doc.createElement("desc");
			CDATASection compdesc_model = doc.createCDATASection("����������磺����һ�����Ե�����");
			compdesc.appendChild(compdesc_model);
			component.appendChild(compdesc);
			
			//			path�·��(xml�ļ���)
			Element comppath = doc.createElement("path");
			CDATASection comppath_model = doc.createCDATASection("·��(xml�ļ���)���磺test.pack1.comp1");
			comppath.appendChild(comppath_model);
			component.appendChild(comppath);
			
			//			hasWf��Ƿ������̣�0��1
			Element comphasWf = doc.createElement("hasWf");
			Text comphasWf_model = doc.createTextNode("�Ƿ�������,0��1");
			comphasWf.appendChild(comphasWf_model);
			component.appendChild(comphasWf);
			
			//			workflow������������˲�����
			Element workflow = doc.createElement("workflow");
			
			Element wfName = doc.createElement("name");
			Text wfName_model = doc.createTextNode("������EN");
			wfName.appendChild(wfName_model);
			workflow.appendChild(wfName);
			
			Element wfIname = doc.createElement("iname");
			CDATASection wfIname_model = doc.createCDATASection("������CN�����ʻ�����");
			wfIname.appendChild(wfIname_model);
			workflow.appendChild(wfIname);
			
			Element wfPath = doc.createElement("path");
			CDATASection wfPath_model = doc.createCDATASection("������̵�xml�ļ���");
			wfPath.appendChild(wfPath_model);
			workflow.appendChild(wfPath);
			
			component.appendChild(workflow);		
			
			for(int i = 0 ; i < list.getLength(); i++)
			{
				//select which package to insert by checking the package name
				if(((Element)list.item(i)).getElementsByTagName("name")
						.equals(""))
				{
					((Element)list.item(i)).appendChild(component);
				}
			}
		}

//		insert the wfparticipants tag
		if(status.equals("wfparticipants"))
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = null; 
			db = dbf.newDocumentBuilder();
			Document doc = null; 
			doc = db.parse(file);
			doc.normalize(); 
			Element wfparticipants = doc.createElement("wfparticipants");
			
			//participant�һ������ߣ�����ж��ֻ��ѭ��participant�е����ݣ��кŷ�Χ�ǣ�152-178
			Element participant = doc.createElement("participant");
			
			Element partiName = doc.createElement("name");
			Text partiName_model = doc.createTextNode("ִ�������ƣ�Ҫ��Ӣ�ģ�");
			partiName.appendChild(partiName_model);
			participant.appendChild(partiName);
			
//			desc�ִ��������
			Element partidesc = doc.createElement("desc");
			CDATASection partidesc_model = doc.createCDATASection("ִ�����������磺����");
			partidesc.appendChild(partidesc_model);
			participant.appendChild(partidesc);
			
			//type�ִ��������,1��2���ֱ����java��ͽű�
			//����java�࣬Ҫ���ڽ�������path�����������������ƣ��ű�Ҫ��������ķ���
			Element partiType = doc.createElement("type");
			Text partiType_model = doc.createTextNode("ִ�������ͣ�1��2");
			partiType.appendChild(partiType_model);
			participant.appendChild(partiType);
			
			//����java�࣬Ҫ���������������ƣ��ű�Ҫ��������ķ���
			Element partiPath = doc.createElement("path");
			Text partiPath_model = doc.createTextNode("�����򷽷���,�磺net.ms.part.parti1.getuser(session)");
			partiPath.appendChild(partiPath_model);
			participant.appendChild(partiPath);
			
			wfparticipants.appendChild(participant);
			
//			project.appendChild(wfparticipants);	
		}
//		insert wfapplication tag
		if(status.equals("wfapplication"))
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = null; 
			db = dbf.newDocumentBuilder();
			Document doc = null; 
			doc = db.parse(file);
			doc.normalize(); 
			//wfapplications�СӦ�ÿ�
			Element wfapplication = doc.createElement("wfapplication");
			
			//application�һ��Ӧ�ã�����ж��Ӧ��ֻ��ѭ�������application�е����ݣ��к��ǣ�186-247
			Element application = doc.createElement("application");
			
//			name�Ӧ������
			Element appname = doc.createElement("name");
			CDATASection appname_model = doc.createCDATASection("����,�磺����");
			appname.appendChild(appname_model);
			application.appendChild(appname);
			
//			desc�Ӧ������
			Element appdesc = doc.createElement("desc");
			CDATASection appdesc_model = doc.createCDATASection("Ӧ���������磺����һ�����Ե�����");
			appdesc.appendChild(appdesc_model);
			application.appendChild(appdesc);
			
			//type�ִ��������,1��2���ֱ����ϵͳӦ�ú��û�Ӧ��
			//����javaӦ�õĴ�java����Ҫ���������path�������������ƣ�WEBӦ�������漰���ݽ϶࣬����ҳ�棬�ʹ�����
			Element appType = doc.createElement("type");
			Text appType_model = doc.createTextNode("Ӧ�����ͣ�1��2");
			appType.appendChild(appType_model);
			application.appendChild(appType);
			
//			����javaӦ�õĴ�java����Ҫ�������������ƣ�WEBӦ�������漰���ݽ϶࣬����ҳ�棬�ʹ�����
			Element appPath = doc.createElement("path");
			Text appPath_model = doc.createTextNode("����������,�磺app1.action)");
			appPath.appendChild(appPath_model);
			application.appendChild(appPath);
			
			//params�Ӧ�õĲ����б�
			Element params = doc.createElement("params");
			//param�һ������������,����Ƕ��������ѭ���������ǣ�217-243��
			Element param = doc.createElement("param");
			
//			name���������
			Element paramName = doc.createElement("name");
			Text paramName_model = doc.createTextNode("�������ƣ�ֻ����Ӣ�ĺ�����,�磺id");
			paramName.appendChild(paramName_model);
			param.appendChild(paramName);
			
			//type���������
			Element paramType = doc.createElement("type");
			Text paramType_model = doc.createTextNode("�������ͣ��磺2");
			paramType.appendChild(paramType_model);
			param.appendChild(paramType);
			
			//isinput��Ƿ����������
			Element isinput = doc.createElement("isinput");
			Text isinput_model = doc.createTextNode("�Ƿ������������0��1");
			isinput.appendChild(isinput_model);
			param.appendChild(isinput);
			
			//isoutput��Ƿ����������
			Element isoutput = doc.createElement("isoutput");
			Text isoutput_model = doc.createTextNode("�Ƿ������������0��1");
			isoutput.appendChild(isoutput_model);
			param.appendChild(isoutput);		
			
			params.appendChild(param);		
			
			application.appendChild(params);
			
			wfapplication.appendChild(application);		
			
//			project.appendChild(wfapplication);	
		}
		
//		TransformerFactory tFactory =TransformerFactory.newInstance();
//		Transformer transformer = tFactory.newTransformer();
//		DOMSource source = new DOMSource(doc);
//		StreamResult result = new StreamResult(new java.io.File(file));
//		transformer.transform(source, result); 

	}
	
	private void writeXMLFile(String outFile) throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		try 
		{ 
			db = dbf.newDocumentBuilder(); 
		} 
		catch (ParserConfigurationException pce) 
		{ 
			System.err.println(pce); 
			System.exit(1); 
		} 
		Document doc = null; 
		doc = db.newDocument();

		
//		�����ǽ���XML�ĵ����ݵĹ��̣��Ƚ�����Ԫ��
		Element root = doc.createElement("richweb"); 
//		��Ԫ��������ĵ� 
		doc.appendChild(root); 
	
		Element project = doc.createElement("project");
		
//		Text projectName = doc.createTextNode(this.projectName);
//		project.appendChild(projectName);
		
		Element ds = doc.createElement("DataSource");
		
		Element name = doc.createElement("DataSourceName");
		Text txtName = doc.createTextNode(getConName());
		name.appendChild(txtName);
		ds.appendChild(name);
		
		Element userName = doc.createElement("username");
		Text txtUserName = doc.createTextNode(getUsername());
		userName.appendChild(txtUserName);
		ds.appendChild(userName);
	
		Element passWord = doc.createElement("password");
		Text txtPassword = doc.createTextNode(getPassword());
		passWord.appendChild(txtPassword);
		ds.appendChild(passWord);
		
		Element type = doc.createElement("type");
		Text txtType = doc.createTextNode(getDbType());
		type.appendChild(txtType);
		ds.appendChild(type);
		
		Element database = doc.createElement("database");
		Text txtDB = doc.createTextNode(getDbName());
		database.appendChild(txtDB);
		ds.appendChild(database);
		
		Element ePort = doc.createElement("port");
		Text txtPort = doc.createTextNode(getPort());
		ePort.appendChild(txtPort);
		ds.appendChild(ePort);
		
		Element eServer = doc.createElement("server");
		Text txtServer = doc.createTextNode(getServer());
		eServer.appendChild(txtServer);
		ds.appendChild(eServer);
		
		Element max = doc.createElement("maxConnections");
		Text txtMax = doc.createTextNode("10");
		max.appendChild(txtMax);
		ds.appendChild(max);
		
		project.appendChild(ds);
		//wfparticipants��,�����߻�ִ����
				
		
		root.appendChild(project);		
		
//		��XML�ĵ������ָ�����ļ� 
//		this.createProject();
//		FileOutputStream outStream = new FileOutputStream(outFile); 
//		OutputStreamWriter outWriter = new OutputStreamWriter(outStream); 
//		((XmlDocument) doc).write(outWriter, "gb2312"); 
//		((XmlDocument) doc).write(outWriter, "UTF-8");  //Ӧ��utf-8���뷽ʽ
		IOStreams ioStreams = new IOStreams ();
		ioStreams.transformToFile(doc,outFile);
		
//		outWriter.close(); 
//		outStream.close();
	
	}
	

	
	private void createProject()
	{
       
        String path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
        StringBuffer sb = new StringBuffer(path);
        
        IProject newProjectHandle = ResourcesPlugin.getWorkspace().getRoot().getProject(this.projectName); 
        
        sb.append("\\");
        //System.out.println(sb.toString());
//        IPath targetPath = new Path(workspace.getRoot().getLocation().toOSString() +newProjectHandle.getName()); 
//        IPath targetPath = new Path(workspace + newProjectHandle.getName()); 
        IPath targetPath = new Path(newProjectHandle.getName());
        sb.append(targetPath.toString());
        final IProjectDescription description = ResourcesPlugin.getWorkspace().newProjectDescription(newProjectHandle.getName()); 
        description.setLocation(null); 
        try
        { 
          newProjectHandle.create(description, null); 
          newProjectHandle.open(null); 

          this.createConfigureFolder();
          
          
//          project = new CEECProject();
//          project.ssetProjectName(workspace.getRoot().getProject(page.txtProject.getText().trim()));
//          
//          
        }
        catch (CoreException e)
        { 
          e.printStackTrace();
        }
   }
	
	private void createConfigureFolder() {
		IFolder srcFolder = ResourcesPlugin.getWorkspace().getRoot().getProject(this.projectName).getFolder("configure"); //$NON-NLS-1$
		if (!srcFolder.exists())
			try {
				srcFolder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
	}
	
//	DBType
	public String getDbType()
	{
		return this.dbType;
	}
	public void setDbType(String str)
	{
		this.dbType = str;
	}
//	DBName
	public String getDbName()
	{
		return this.dbName;
	}
	public void setDbName(String str)
	{
		this.dbName = str;
	}
//	server
	public String getServer()
	{
		return this.server;
	}
	public void setServer(String str)
	{
		this.server = str;
	}
//	Port
	public String getPort()
	{
		return this.port;
	}
	public void setPort(String str)
	{
		this.port = str;
	}
//	userName
	public String getUsername()
	{
		return this.username;
	}
	public void setUsername(String str)
	{
		this.username = str;
	}
//	password
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String str)
	{
		this.password = str;
	}
//	url
	public String getUrl()
	{
		return this.url;
	}
	public void setUrl(String str)
	{
		this.url = str;
	}
//	connect name
	public String getConName()
	{
		return this.conName;
	}
	public void setConName(String str)
	{
		this.conName = str;
	}

}
