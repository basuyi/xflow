package net.ms.designer.editors.packages.ui;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.ms.designer.core.MsProject;
import net.ms.designer.editors.packages.models.Package;
import net.ms.designer.editors.packages.models.PackageDiagram;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;



public class WriteToProjectXML 
{
	Object type;
	MsProject project;

	public WriteToProjectXML()
	{
		
	}
	
	public WriteToProjectXML(MsProject project,Object type)
	{
		this.project = project;
		this.type = type;
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

		PackageDiagram diagram = (PackageDiagram)type;
		List nodes = diagram.getNodes();
		for(int i = 0; i < nodes.size();i++)
		{
			Package node = (Package)nodes.get(i);
			NodeList list = doc.getElementsByTagName("project");
			Element package1 = doc.createElement("package");
			
			//name项，名称
			Element p1name = doc.createElement("name");
			Text p1name_model = doc.createTextNode(node.getName());
			p1name.appendChild(p1name_model);
			package1.appendChild(p1name);
			
			//iname项，国际化名称
			if(!node.getIname().equals(""))
			{
				Element p1iname = doc.createElement("iname");
				CDATASection p1iname_model = doc.createCDATASection(node.getIname());
				p1iname.appendChild(p1iname_model);
				package1.appendChild(p1iname);
			}

			boolean flag = true;
			NodeList packs = ((Element)list.item(0)).getElementsByTagName("package");
			for(int j = 0;j < packs.getLength(); j++)
			{
				String name;
				name = ((Element)packs.item(j)).getElementsByTagName("name").item(0).getFirstChild()
				.getNodeValue();
				if(name.equals(node.getName()))
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
				list.item(0).appendChild(package1);
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
	public void doDelete(String file , String type , String name) throws Exception
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
				doc.getElementsByTagName("project").item(0).removeChild(list.item(i));
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
