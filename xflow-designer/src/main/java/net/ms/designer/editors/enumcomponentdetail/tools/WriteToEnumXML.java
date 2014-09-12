package net.ms.designer.editors.enumcomponentdetail.tools;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.enumcomponentdetail.model.Container;
import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.model.ValueField;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class WriteToEnumXML 
{
	
	private Container container;
	private Table table;
	private List fields = new ArrayList();
	private MsProject project;
	private MsElement element;
	
	public WriteToEnumXML(MsElement obj, MsProject project)
	{
		this.element = obj;
		this.container = (Container)obj.getContainer();
		if(container.getChildren().size() > 0)
		{
			Iterator i = container.getChildren().iterator();
			table = (Table)i.next();
		}
		if(table.getChildren().size() > 0)
		{
			for(Iterator i = table.getChildren().iterator(); i.hasNext();)
			{
				fields.add(i.next());
			}
		}
		this.project = project;
	}
	
	public void writeXMLFile(String outFile) throws Exception{
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
		Element root = doc.createElement("enumeraion"); 
//		根元素添加上文档 
//		doc.appendChild(root); 
		
//		fullname项，组件全名（实际调用的路径）
		Element fullname = doc.createElement("fullname");
		StringBuffer sb = new StringBuffer(project.getGenPackageName());
//		sb.append(".");
//		sb.append(project.getProjectName());
		sb.append(".");
		sb.append(element.getParent().getNodeName());
		sb.append(".");
		sb.append(element.getNodeName());
		Text fullname_model = doc.createTextNode(sb.toString());
		fullname.appendChild(fullname_model);
		root.appendChild(fullname);
		
//		path项，路径(xml文件名)
		Element path = doc.createElement("path");
		sb = new StringBuffer(project.getProjectName());
		sb.append(".");
		sb.append(element.getParent().getNodeName());
		sb.append(".");
		sb.append(element.getNodeName());
		Text path_model = doc.createTextNode(sb.toString());
		path.appendChild(path_model);
		root.appendChild(path);
		
//		name项，名称
		Element name = doc.createElement("name");
		Text name_model = doc.createTextNode(table.getTableName());
		name.appendChild(name_model);
		root.appendChild(name);
		
		//iname项，国际化名称
		if(!table.getIname().equals(""))
		{
			Element iname = doc.createElement("iname");
			CDATASection iname_model = doc.createCDATASection(table.getIname());
			iname.appendChild(iname_model);
			root.appendChild(iname);
		}
		
//		desc项，组件描述
		if(!table.getDesc().equals(""))
		{
			Element desc = doc.createElement("desc");
			CDATASection desc_model = doc.createCDATASection(table.getDesc());
			desc.appendChild(desc_model);
			root.appendChild(desc);
		}
		
		//positionx项，组件坐标的x值
		Element positionx = doc.createElement("positionx");
		int x = table.getLocation().x;
		String sx = String.valueOf(x);
		Text positionx_model = doc.createTextNode(sx);
		positionx.appendChild(positionx_model);
		root.appendChild(positionx);
		
		//positiony项，组件坐标的y值
		Element positiony = doc.createElement("positiony");
		int y = table.getLocation().y;
		String sy = String.valueOf(y);
		Text positiony_model = doc.createTextNode(sy);
		positiony.appendChild(positiony_model);
		root.appendChild(positiony);
		
		//values项，枚举组件的内容
		Element values = doc.createElement("values");
		ValueField field;
		//枚举组件的内容，如果有多个值就有多个value，格式相同,用for循环"---"间的内容即可，行号：82-99
		//--------------
		for(int i = 0; i< fields.size(); i++)
		{
			field = (ValueField)fields.get(i);
			
			Element value = doc.createElement("value");
		
			Element id = doc.createElement("id");
			
			Text id_model = doc.createTextNode(String.valueOf(i+1));
			id.appendChild(id_model);
			value.appendChild(id);
		
			Element residstr = doc.createElement("residstr");
			Text residstr_model = doc.createTextNode(field.getFieldName());
			residstr.appendChild(residstr_model);
			value.appendChild(residstr);
		
			if(!field.getIname().equals(""))
			{
				Element viname = doc.createElement("iname");
				CDATASection viname_model = doc.createCDATASection(field.getIname());
				viname.appendChild(viname_model);
				value.appendChild(viname);
			}
		
			values.appendChild(value);	
		}
		//---------------
		root.appendChild(values);
		doc.appendChild(root); 
//		把XML文档输出到指定的文件 
		try
		{
//			FileOutputStream outStream = new FileOutputStream(outFile); 
//			OutputStreamWriter outWriter = new OutputStreamWriter(outStream,"UTF-8");  
//			((XmlDocument) doc).write(outWriter, "UTF-8");  //应用utf-8编码方式
//			outWriter.close(); 
//			outStream.close();
			IOStreams ioStreams = new IOStreams ();
			ioStreams.transformToFile(doc,outFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
