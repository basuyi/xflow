package net.ms.designer.editors.workflow.xmlparse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class ReadFromRightXML
{
	
	public ReadFromRightXML()
	{
		
	}
	
	public List readFromRightXML(String filePath) throws Exception
	{
//		String filePath = ResourcesPlugin.getWorkspace().getRoot().getFullPath().toOSString();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(filePath);
		document.normalize();
		
		List rightList = new ArrayList();
		{
		
			if(document.getDocumentElement()==null)
			{
				return null;
			}
			
			if(document.getDocumentElement()!=null)
			{
				Element rights = document.getDocumentElement();
							
				if(rights.getElementsByTagName("field") != null || rights.getElementsByTagName("field").getLength()>0)
				{
					NodeList fieldList = rights.getElementsByTagName("field");
					
					if(fieldList == null || fieldList.getLength()<1)
					{
						return null;
					}
					else
					{
						for(int i = 0; i<fieldList.getLength(); i++)
						{
							Element fieldNode = (Element) fieldList.item(i);
							
							Right right = new Right();
							
							
							if(fieldNode.getElementsByTagName("name")!=null || fieldNode.getElementsByTagName("name").getLength()>0)
							{
								right.setName(fieldNode.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
							}
							if(fieldNode.getElementsByTagName("value")!=null || fieldNode.getElementsByTagName("value").getLength()>0)
							{
								right.setValue(fieldNode.getElementsByTagName("value").item(0).getFirstChild().getNodeValue());
							}
							rightList.add(right);
						}					
					}
				}
			}		
		}
	    return rightList;
		
		
	}
	
}	
	
	
	
	
	
	
	

