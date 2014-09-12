package net.ms.designer.editors.workflow.xmlparse;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.workflow.models.UserAppActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class WriteToRightXML 
{
	WorkflowDiagram diagram;
	
	public WriteToRightXML(WorkflowDiagram diagram)
	{
		this.diagram = diagram;
	}
	
	public void writeToXML(String outFile) throws Exception
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
		Element root = doc.createElement("rights"); 
//		根元素添加上文档 
		doc.appendChild(root); 
		
		List allChildren = this.diagram.getChildren();
		List allUserActivity = new ArrayList();
		
		Iterator itChildren = allChildren.iterator();
		
		while(itChildren.hasNext())
		{
			WorkflowSubPart subPart = (WorkflowSubPart)itChildren.next();
			
			if(subPart instanceof UserAppActivity)
			{
				allUserActivity.add(subPart);
			}			
		}
		
		if(allUserActivity != null && allUserActivity.size()>0)
		{
			Iterator itUserActivity = allUserActivity.iterator();
			while(itUserActivity.hasNext())
			{
				UserAppActivity userApp = (UserAppActivity)itUserActivity.next();
				
				List rightList = userApp.getRightList();
				Iterator itRight = rightList.iterator();
				while(itRight.hasNext())
				{
					Right right = (Right)itRight.next();
					
					Element field = doc.createElement("field");
					
					if(right.getName() != null && right.getName().length()>0)
					{
						Element name = doc.createElement("name");
						Text name_model = doc.createTextNode(right.getName());
						name.appendChild(name_model);
						field.appendChild(name);
					}
					
					if(right.getValue() != null && right.getValue().length()>0)
					{
						Element value = doc.createElement("value");
						Text value_model = doc.createTextNode(right.getValue());
						value.appendChild(value_model);
						field.appendChild(value);
					}
					root.appendChild(field);
				}
			}
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
