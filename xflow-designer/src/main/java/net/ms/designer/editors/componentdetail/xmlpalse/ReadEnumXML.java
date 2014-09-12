package net.ms.designer.editors.componentdetail.xmlpalse;


import java.io.*;
import java.util.*; 

import javax.xml.parsers.*;

import net.ms.designer.core.MsProject;

import org.w3c.dom.*; 

public class ReadEnumXML {
	
	List Enum_Vector = new ArrayList();
	String enumName;
	List CompList = new ArrayList();

	int type;
	public List readXMLFile(String inFile,int type) throws Exception { 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		try { 
			db = dbf.newDocumentBuilder(); 
		} catch (ParserConfigurationException pce) { 
			System.err.println(pce); 
			System.exit(1); 
		} 
		Document doc = null; 
		try { 
			doc = db.parse(inFile); 
		} catch (DOMException dom) { 
			System.err.println(dom.getMessage()); 
			System.exit(1); 
		} catch (IOException ioe) { 
			System.err.println(ioe); 
			System.exit(1); 
		} 
		
		Element root = doc.getDocumentElement(); 


		this.type = type;
		if(type == 0)
		{
			NodeList enumsName = root.getElementsByTagName("enumeration");
			if(enumsName.getLength() >0)
			{
				
				for(int i = 0 ; i < enumsName.getLength(); i++)
				{
					MsProject ceecProject = new MsProject();
		//			Enmu enum = new Enmu();
					Element enumName = (Element)enumsName.item(i);
					NodeList one = enumName.getElementsByTagName("name");
					Element contant = (Element)one.item(0);
					Text t = (Text) contant.getFirstChild();
		//			enum.setFullName(t.getNodeValue());
					ceecProject.setEnumCompName(t.getNodeValue());
					//System.out.println(t.getNodeValue());
					
					NodeList two = enumName.getElementsByTagName("desc");
					if(two.getLength()!=0)
					{
						Element contant1 = (Element)two.item(0);
						Text t1 = (Text)contant1.getFirstChild();
			//			enum.setDecs(t1.getNodeValue());
						ceecProject.setDesc(t1.getNodeValue());
						//System.out.println(t1.getNodeValue());
					}
					Enum_Vector.add(ceecProject);
				}
			}
			
			return Enum_Vector;
		}
		//如果type为1，则为组件
		if(type == 1)
		{
			NodeList CompName = root.getElementsByTagName("component");
			if(CompName.getLength()>0)
			{
				for(int j=0;j<CompName.getLength();j++)
				{
					MsProject ceecProject1 = new MsProject();
					Element id = (Element)CompName.item(j);
					NodeList name = id.getElementsByTagName("name");
					Element contant3 = (Element)name.item(0);
					Text compt = (Text) contant3.getFirstChild();
					ceecProject1.setComponentName(compt.getNodeValue());
					
					NodeList idxml = id.getElementsByTagName("path");
					Element contant4 = (Element)idxml.item(0);
					Text compt1 = (Text)contant4.getFirstChild();
					ceecProject1.setComponentID(compt1.getNodeValue());
					
					Enum_Vector.add(ceecProject1);		
				}
				return Enum_Vector;
			}
		}
		//如果type为2，则为字段信息
		if(type == 2)
		{
			NodeList CompAll = root.getElementsByTagName("fields");
			
			if(CompAll.getLength()>0)
			{
				for(int k = 0;k<CompAll.getLength();k++)
				{
					
					Element fields = (Element)CompAll.item(k);
					NodeList field = fields.getElementsByTagName("field");
					if(field.getLength()>0)
					{
						for(int k1=0;k1<field.getLength();k1++)
						{
							MsProject ceecProject2 = new MsProject();
							Element fieldelement = (Element)field.item(k1);
							NodeList subfield = fieldelement.getElementsByTagName("isuserfield");
							Element contant5 = (Element)subfield.item(0);
							Text isuserField = (Text)contant5.getFirstChild();
							if(isuserField.getNodeValue().equals("1"))
							{
								NodeList subfield1 = fieldelement.getElementsByTagName("name");
								Element contant6 = (Element)subfield1.item(0);
								Text fieldname = (Text)contant6.getFirstChild();
								ceecProject2.setFieldName(fieldname.getNodeValue());
								
								NodeList subfield1_fieldtype = fieldelement.getElementsByTagName("type");
								Element contant7 = (Element)subfield1_fieldtype.item(0);
								Text fieldtype = (Text)contant7.getFirstChild();
								ceecProject2.setFieldType(fieldtype.getNodeValue());
								
								NodeList subfield1_fieldListable = fieldelement.getElementsByTagName("listable");
								Element contant8 = (Element)subfield1_fieldListable.item(0);
								Text fieldListable = (Text)contant8.getFirstChild();
								ceecProject2.setFieldListable(fieldListable.getNodeValue());
								
								NodeList subfield1_fieldSearchable = fieldelement.getElementsByTagName("searchable");
								Element contant9 = (Element)subfield1_fieldSearchable.item(0);
								Text fieldSearchable = (Text)contant9.getFirstChild();
								ceecProject2.setFieldSearchable(fieldSearchable.getNodeValue());
								
								CompList.add(ceecProject2);
								
							}
								
							
						}
						return CompList;
					}
				}
			}
		}
		return Enum_Vector;
	}


}
