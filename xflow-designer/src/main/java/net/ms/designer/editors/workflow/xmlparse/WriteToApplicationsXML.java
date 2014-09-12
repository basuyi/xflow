package net.ms.designer.editors.workflow.xmlparse;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.workflow.models.ApplicationActivity;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class WriteToApplicationsXML 
{
	private List applicationList;
	
	public WriteToApplicationsXML(List applicationList)
	{
		this.applicationList = applicationList;
	}
	
	public void writeApplicationsXML(String outFile) throws Exception
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
		Element root = doc.createElement("wfapplications"); 
//		根元素添加上文档 
		doc.appendChild(root); 
		
		Iterator it = this.applicationList.iterator();
		
		while(it.hasNext())
		{
			ApplicationActivity applicationActivity = (ApplicationActivity)it.next();
			
			Element application = doc.createElement("application");
			
			if(applicationActivity.getApplicationId() != null && applicationActivity.getApplicationId().length()>0)
			{
				Element id = doc.createElement("id");
				Text id_model = doc.createTextNode(applicationActivity.getApplicationId());
				id.appendChild(id_model);
				application.appendChild(id);
			}
			if(applicationActivity.getApplicationName() != null && applicationActivity.getApplicationName().length()>0)
			{
				Element name = doc.createElement("name");
				Text name_model = doc.createTextNode(applicationActivity.getApplicationName());
				name.appendChild(name_model);
				application.appendChild(name);
			}
			if(applicationActivity.getApplicationDesc() !=null && applicationActivity.getApplicationDesc().length()>0)
			{
				Element desc = doc.createElement("desc");
				Text desc_model = doc.createTextNode(applicationActivity.getApplicationDesc());
				desc.appendChild(desc_model);
				application.appendChild(desc);
			}
			if(applicationActivity.getApplicationType() != null && applicationActivity.getApplicationType().length()>0)
			{
				Element type = doc.createElement("type");
				Text type_model = doc.createTextNode(applicationActivity.getApplicationType());
				type.appendChild(type_model);
				application.appendChild(type);
			}
			if(applicationActivity.getApplicationPath() != null && applicationActivity.getApplicationPath().length()>0)
			{
				Element path = doc.createElement("path");
				Text path_model = doc.createTextNode(applicationActivity.getApplicationPath());
				path.appendChild(path_model);
				application.appendChild(path);
			}
			if(applicationActivity.getWfApplicationParam() != null && applicationActivity.getWfApplicationParam().size()>0)
			{
				Element params = doc.createElement("params");
				
				Iterator itPara = applicationActivity.getWfApplicationParam().iterator();
				
				while(itPara.hasNext())
				{
					ParameterEntire parameter = (ParameterEntire)itPara.next();
					
					Element param = doc.createElement("param");
					
					if(parameter.getParaName() != null && parameter.getParaName().length()>0)
					{
						Element paramName = doc.createElement("name");
						Text paramName_model = doc.createTextNode(parameter.getParaName());
						paramName.appendChild(paramName_model);
						param.appendChild(paramName);
					}
					if(parameter.getParaType() != null && parameter.getParaType().length()>0)
					{
						Element paramType = doc.createElement("type");
						Text paramType_model = doc.createTextNode(parameter.getParaType());
						paramType.appendChild(paramType_model);
						param.appendChild(paramType);
					}
					
					Element paramIsInput = doc.createElement("isinput");
					int isInputTemp;
					if(parameter.getIsInput() == true)
						isInputTemp = 1;
					else
						isInputTemp = 0;
					Text paramIsInput_model = doc.createTextNode(isInputTemp+"");
					paramIsInput.appendChild(paramIsInput_model);
					param.appendChild(paramIsInput);
					
					Element paramIsOutput = doc.createElement("isoutput");
					int isOutputTemp;
					if(parameter.getIsOutput() == true)
						isOutputTemp = 1;
					else
						isOutputTemp = 0;
					Text paramIsOutput_model = doc.createTextNode(isOutputTemp+"");
					paramIsOutput.appendChild(paramIsOutput_model);
					param.appendChild(paramIsOutput);
					
					params.appendChild(param);
				}
				
				application.appendChild(params);
				
			}
			
			root.appendChild(application);
		}
		
//		FileOutputStream outStream = new FileOutputStream(outFile); 
//		OutputStreamWriter outWriter = new OutputStreamWriter(outStream,"UTF-8"); 
//		((XmlDocument) doc).write(outWriter, "UTF-8"); 
//		outWriter.close(); 
//		outStream.close();
		IOStreams ioStreams = new IOStreams ();
		ioStreams.transformToFile(doc,outFile);
	}
}
