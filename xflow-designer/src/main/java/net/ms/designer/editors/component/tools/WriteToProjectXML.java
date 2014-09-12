package net.ms.designer.editors.component.tools;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.component.models.BizComponent;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;
import net.ms.designer.editors.component.models.EnumComponent;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;



public class WriteToProjectXML 
{
	Object type;
	MsProject project;
	MsElement element;
	
	public WriteToProjectXML()
	{
		
	}
	
	public WriteToProjectXML(MsProject project,Object type)
	{
		this.project = project;
		this.type = ((MsElement)type).getContainer();
		this.element = (MsElement)type;
	}
	
	public void accessXMLFile(String file)throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		db = dbf.newDocumentBuilder();
		Document doc = null; 
		File f = new File(file);
		if(f.exists())
		{
			doc = db.parse(file);
			doc.normalize(); 
		}

		CompDiagram diagram = (CompDiagram)type;
		List nodes = diagram.getNodes();
			
		NodeList list = doc.getElementsByTagName("package");

		for(int k = 0; k < nodes.size(); k++)
		{
			if(nodes.get(k) instanceof BizComponent)
			{
				Element component = doc.createElement("component");
				
				
				Component node = (Component)nodes.get(k);			
				//			name项，名称
				Element compname = doc.createElement("name");
				Text compname_model = doc.createTextNode(node.getName());
				compname.appendChild(compname_model);
				component.appendChild(compname);	
						
				//			iname项，国际化名称
				if(!node.getIname().equals(""))
				{
					Element compiname = doc.createElement("iname");
					CDATASection compiname_model = doc.createCDATASection(node.getIname());
					compiname.appendChild(compiname_model);
					component.appendChild(compiname);
				}
					
				//			desc项，组件描述
				if(!node.getDesc().equals(""))
				{
					Element compdesc = doc.createElement("desc");
					CDATASection compdesc_model = doc.createCDATASection(node.getDesc());
					compdesc.appendChild(compdesc_model);
					component.appendChild(compdesc);
				}
					
				StringBuffer sb = new StringBuffer(project.getProjectName());
				sb.append(".");
				sb.append(((MsElement)element).getNodeName());
				sb.append(".");
				sb.append(node.getName());
					
				//			path项，路径(xml文件名)
				Element comppath = doc.createElement("path");
//				CDATASection comppath_model = doc.createCDATASection(sb.toString());
				CDATASection comppath_model = doc.createCDATASection(node.getComponentID());
				comppath.appendChild(comppath_model);
				component.appendChild(comppath);
					
				for(int i = 0 ; i < list.getLength(); i++)
				{
					//select which package to insert by checking the package name
					if(((Element)((Element)list.item(i)).getElementsByTagName("name").item(0))
							.getFirstChild().getNodeValue().equals(((MsElement)element).getNodeName()))
					{
						NodeList comps = ((Element)list.item(i)).getElementsByTagName("component");
						boolean flag = true;
						for(int j = 0; j < comps.getLength() ; j++ )
						{
							boolean canDo = ((Element)(comps.item(j))).getElementsByTagName("name").item(0).getFirstChild()
							.getNodeValue().equals(node.getName());
							if(canDo)
							{
								flag = false;
								break;
							}
							else
							{
								flag = true;
							}
						}
						if(flag)
						{
							((Element)list.item(i)).appendChild(component);
						}
					}
				}
			}
			else if(nodes.get(k) instanceof EnumComponent)
			{
				Element component = doc.createElement("enumeration");
				
				Component node = (Component)nodes.get(k);			
				//			name项，名称
				Element compname = doc.createElement("name");
				Text compname_model = doc.createTextNode(node.getName());
				compname.appendChild(compname_model);
				component.appendChild(compname);
			
				//			iname项，国际化名称
				if(!node.getIname().equals(""))
				{
					Element compiname = doc.createElement("iname");
					CDATASection compiname_model = doc.createCDATASection(node.getIname());
					compiname.appendChild(compiname_model);
					component.appendChild(compiname);
				}
					
				//			desc项，组件描述
				if(!node.getDesc().equals(""))
				{
					Element compdesc = doc.createElement("desc");
					CDATASection compdesc_model = doc.createCDATASection(node.getDesc());
					compdesc.appendChild(compdesc_model);
					component.appendChild(compdesc);
				}
					
				StringBuffer sb = new StringBuffer(project.getProjectName());
				sb.append(".");
				sb.append(((MsElement)element).getNodeName());
				sb.append(".");
				sb.append(node.getName());
					
				//			path项，路径(xml文件名)
				Element comppath = doc.createElement("path");
//				CDATASection comppath_model = doc.createCDATASection(sb.toString());
				CDATASection comppath_model = doc.createCDATASection(node.getComponentID());
				comppath.appendChild(comppath_model);
				component.appendChild(comppath);
					
				for(int i = 0 ; i < list.getLength(); i++)
				{
					//select which package to insert by checking the package name
					if(((Element)((Element)list.item(i)).getElementsByTagName("name").item(0))
							.getFirstChild().getNodeValue().equals(((MsElement)element).getNodeName()))
					{
						NodeList enums = ((Element)list.item(i)).getElementsByTagName("enumeration");
						boolean flag = true;
						for(int j = 0; j < enums.getLength() ; j++ )
						{
							boolean canDo = ((Element)(enums.item(j))).getElementsByTagName("name").item(0).getFirstChild()
							.getNodeValue().equals(node.getName());
							if(canDo)
							{
								flag = false;
								break;
							}
							else
							{
								flag = true;
							}
						}
						if(flag)
						{
							((Element)list.item(i)).appendChild(component);
						}
					}
				}
			}			
		}
		
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new java.io.File(file));
		transformer.transform(source, result); 
	}
	/**
	 * 
	 * @param file
	 * @param type
	 * @param name
	 * @throws Exception
	 */
	public void doDelete(String file , String type , String name , String packageName) throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		db = dbf.newDocumentBuilder();
		Document doc = null; 
		File f = new File(file);
		if(f.exists())
		{
			doc = db.parse(file);
			doc.normalize(); 
		}
		
		NodeList list = doc.getElementsByTagName(type);
		for(int i = 0 ; i < list.getLength() ; i++)
		{
			if(((Element)list.item(i)).getElementsByTagName("name").item(0)
					.getFirstChild().getNodeValue().equals(name))
			{
				NodeList packs = doc.getElementsByTagName("package");
				for(int j = 0 ; j < packs.getLength() ; j++)
				{
					if(((Element)packs.item(j)).getElementsByTagName("name").item(0)
							.getFirstChild().getNodeValue().equals(packageName))
					{
						packs.item(j).removeChild(list.item(i));
					}
				}
			}
		}
		
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new java.io.File(file));
		transformer.transform(source, result); 
	}
	/**
	 * 
	 * @param file
	 * @param type
	 * @param tag
	 * @param oldText
	 * @param newText
	 * @throws Exception
	 */
	public void changeText(String file , String type , String tag , String oldText , String newText) throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		db = dbf.newDocumentBuilder();
		Document doc = null; 
		File f = new File(file);
		if(f.exists())
		{
			doc = db.parse(file);
			doc.normalize(); 
		}
		
		NodeList list = doc.getElementsByTagName(type);
		for(int i = 0 ; i < list.getLength() ; i++)
		{
			if(((Element)list.item(i)).getElementsByTagName(tag).item(0)
					.getFirstChild().getNodeValue().equals(oldText))
			{
				((Element)list.item(i)).getElementsByTagName(tag).item(0)
					.getFirstChild().setNodeValue(newText);
			}
		}
		
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new java.io.File(file));
		transformer.transform(source, result);
	}
}
