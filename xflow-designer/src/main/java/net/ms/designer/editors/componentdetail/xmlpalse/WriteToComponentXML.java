package net.ms.designer.editors.componentdetail.xmlpalse;


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
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.DateField;
import net.ms.designer.editors.componentdetail.models.EnumField;
import net.ms.designer.editors.componentdetail.models.FloatField;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.LookupField;
import net.ms.designer.editors.componentdetail.models.StringField;
import net.ms.designer.editors.componentdetail.models.Table;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class WriteToComponentXML {
	
	private Container container;
	private Table table;
	private List fields = new ArrayList();
	static int i;
	private MsProject project;
	private MsElement element;
	private Document doc;
	
	public WriteToComponentXML(MsElement element , MsProject project)
	{
		this.element = element;
		this.container = (Container)element.getContainer();
		this.project = project;
		
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
		this.doc = db.newDocument(); 

//		下面是建立XML文档内容的过程，先建立根元素
		Element root = doc.createElement("component"); 
//		根元素添加上文档 
		doc.appendChild(root); 
		
		//id项，组件id
		Element id = doc.createElement("id");
		ComponentTable componentTable = new ComponentTable();
		Text id_model = doc.createTextNode(element.getId()); 
		id.appendChild(id_model);
		root.appendChild(id);
			
		//fullname项，组件全名（实际调用的路径）
		
		StringBuffer sb = new StringBuffer(project.getGenPackageName().toLowerCase());
		sb.append(".");
		sb.append(element.getParent().getNodeName());
		sb.append(".");
		sb.append(element.getNodeName());
		
		Element fullname = doc.createElement("fullname");
		Text fullname_model = doc.createTextNode(sb.toString());
		fullname.appendChild(fullname_model);
		root.appendChild(fullname);

		//path项，路径(xml文件名)
		
		sb = new StringBuffer(project.getProjectName());
		sb.append(".");
		sb.append(element.getParent().getNodeName());
		sb.append(".");
		sb.append(element.getNodeName());
		
		Element path = doc.createElement("path");
		Text path_model = doc.createTextNode(sb.toString());
		path.appendChild(path_model);
		root.appendChild(path);
		
        //name项，名称
		Element name = doc.createElement("name");
		Text name_model = doc.createTextNode(container.getMainTable().getName());
		name.appendChild(name_model);
		root.appendChild(name);
		
		//iname项，国际化名称
		Element iname = doc.createElement("iname");
		CDATASection iname_model = doc.createCDATASection(container.getMainTable().getIName());
		iname.appendChild(iname_model);
		root.appendChild(iname);
		
		//desc项，组件描述
		if(!(container.getMainTable().getDesc().equals("")))
		{
		Element desc = doc.createElement("desc");
		CDATASection desc_model = doc.createCDATASection(container.getMainTable().getDesc());
		desc.appendChild(desc_model);
		root.appendChild(desc);
		}
		
		//positionx项，组件坐标的x值
		Element positionx = doc.createElement("positionx");
		Text positionx_model = doc.createTextNode("" + container.getMainTable().getLocation().x);
		positionx.appendChild(positionx_model);
		root.appendChild(positionx);
		
		//positiony项，组件坐标的y值
		Element positiony = doc.createElement("positiony");
		Text positiony_model = doc.createTextNode("" + container.getMainTable().getLocation().y);
		positiony.appendChild(positiony_model);
		root.appendChild(positiony);
		
		//hasChild项，是否有明细表,0或1
		Element hasChild = doc.createElement("hasChild");
		Text hasChild_model = doc.createTextNode("" + container.getMainTable().getHasChildTable());
		hasChild.appendChild(hasChild_model);
		root.appendChild(hasChild);
		
		//hasWf项，是否有流程，0或1
		Element hasWf = doc.createElement("hasWf");
		Text hasWf_model = doc.createTextNode("" + container.getMainTable().getFlowAssociated());
		hasWf.appendChild(hasWf_model);
		root.appendChild(hasWf);
		
		if(container.getMainTable().getFlowAssociated() == 1)
		{
			//workflow项中的内容，若没有流程就没有workflow中的内容
			FlowField flow1 =  (FlowField)container.getFlowField();
			
			
				
//				FlowField flow1 = (FlowField)flow.next();
				Element workflow = doc.createElement("workflow");
				
				Element wfName = doc.createElement("name");
		//		CommonField commonField = new CommonField(); 
	//			FlowField flowField =new FlowField();
				Text wfName_model = doc.createTextNode(flow1.getName());
				wfName.appendChild(wfName_model);
				workflow.appendChild(wfName);
				
				Element wfIname = doc.createElement("iname");
				CDATASection wfIname_model = doc.createCDATASection(flow1.getIName());
				wfIname.appendChild(wfIname_model);
				workflow.appendChild(wfIname);
				
				Element wfPath = doc.createElement("path");
//				CDATASection wfPath_model = doc.createCDATASection(sb.toString()+ "."+flow1.getId());
				CDATASection wfPath_model = doc.createCDATASection(sb.toString()+ "."+flow1.getName());
				wfPath.appendChild(wfPath_model);
				workflow.appendChild(wfPath);
				
				Element wfPositionx = doc.createElement("positionx");
				Text wfPositionx_model = doc.createTextNode("" + flow1.getLocation().x);
				wfPositionx.appendChild(wfPositionx_model);
				workflow.appendChild(wfPositionx);
				
				Element wfPositiony = doc.createElement("positiony");
				Text wfPositiony_model = doc.createTextNode("" + flow1.getLocation().y);
				wfPositiony.appendChild(wfPositiony_model);
				workflow.appendChild(wfPositiony);
				
				root.appendChild(workflow);
		
		}
		
        //fields项中的内容，记录组件表中包含的字段项	
		Iterator itField = container.getMainTable().getChildren().iterator();
		if(itField != null)
		{
			Element fields  = doc.createElement("fields");
		
		while(itField.hasNext())
		{
			
//			Element fields  = doc.createElement("fields");
			
			//由于5种类型字段不同，下面分别列出，具体保存时要稍加修改，建议：循环行号范围：133-352，用for循环，循环次数是componentTable中的元素个数，循环内部用swith-case
			CommonField commonField = (CommonField)itField.next();

			
				Element field1 = doc.createElement("field");
				fields.appendChild(field1);
				
				Element f1_name = doc.createElement("name");
				Text f1_name_model = doc.createTextNode(commonField.getName());
				f1_name.appendChild(f1_name_model);
				field1.appendChild(f1_name);
				
				Element f1_type = doc.createElement("type");
				
				if(commonField.getField_Type().equals("Autonum"))
				{
					Text f1_type_model = doc.createTextNode("0");
					f1_type.appendChild(f1_type_model);
					field1.appendChild(f1_type);
				}
				if(commonField.getField_Type().equals("String"))
				{
					Text f1_type_model = doc.createTextNode("1");
					f1_type.appendChild(f1_type_model);
					field1.appendChild(f1_type);
				}
				
				if(commonField.getField_Type().equals("Integer"))
				{
					Text f1_type_model = doc.createTextNode("2");
					f1_type.appendChild(f1_type_model);
					field1.appendChild(f1_type);
				}
				if(commonField.getField_Type().equals("Date"))
				{
					Text f1_type_model = doc.createTextNode("3");
					f1_type.appendChild(f1_type_model);
					field1.appendChild(f1_type);
				}
				if(commonField.getField_Type().equals("Float"))
				{
					Text f1_type_model = doc.createTextNode("4");
					f1_type.appendChild(f1_type_model);
					field1.appendChild(f1_type);
				}
				if(commonField.getField_Type().equals("Enum"))
				{
					Text f1_type_model = doc.createTextNode("5");
					f1_type.appendChild(f1_type_model);
					field1.appendChild(f1_type);
				}
				if(commonField.getField_Type().equals("Lookup"))
				{
					Text f1_type_model = doc.createTextNode("6");
					f1_type.appendChild(f1_type_model);
					field1.appendChild(f1_type);
					
				}
				Element f1_iname = doc.createElement("iname");
				CDATASection f1_iname_model = doc.createCDATASection(commonField.getIName());
				f1_iname.appendChild(f1_iname_model);
				field1.appendChild(f1_iname);
				
				if(commonField.getField_Type().equals("Autonum"))
				{
					Element f1_isprimarykey = doc.createElement("isprimarykey");
					Text f1_isprimarykey_model = doc.createTextNode("1");
					f1_isprimarykey.appendChild(f1_isprimarykey_model);
					field1.appendChild(f1_isprimarykey);
				}
			
				else
				{
					Element f1_isprimarykey = doc.createElement("isprimarykey");
					Text f1_isprimarykey_model = doc.createTextNode("" + commonField.getIsBizKey());
					f1_isprimarykey.appendChild(f1_isprimarykey_model);
					field1.appendChild(f1_isprimarykey);
				}
				
				Element f1_searchable = doc.createElement("searchable");
				Text f1_searchable_model = doc.createTextNode("" + commonField.getCanBeQuery());
				f1_searchable.appendChild(f1_searchable_model);
				field1.appendChild(f1_searchable);
				
				Element f1_isrequired = doc.createElement("isrequired");
				Text f1_isrequired_model = doc.createTextNode("" + commonField.getMustBeFilled());
				f1_isrequired.appendChild(f1_isrequired_model);
				field1.appendChild(f1_isrequired);
				
				Element f1_listable = doc.createElement("listable");
				Text f1_listable_model = doc.createTextNode("" + commonField.getListable());
				f1_listable.appendChild(f1_listable_model);
				field1.appendChild(f1_listable);

			
				Element f1_Autonum = doc.createElement("isuserfield");
				Text f1_Autonum_model = doc.createTextNode("" + commonField.getIsuserfield());
				f1_Autonum.appendChild(f1_Autonum_model);
				field1.appendChild(f1_Autonum);
				
				if(commonField.getField_Type().equals("String"))
				{
					Element f1_length = doc.createElement("length");
					Text f1_length_model = doc.createTextNode("" + ((StringField)commonField).getStrLength());
					f1_length.appendChild(f1_length_model);
					field1.appendChild(f1_length);
				}
				
				if(commonField.getField_Type().equals("Date"))
				{
					if(!(((DateField)commonField).getDateFormatPattern() == null))
					{
						Element f1_DateTimeDisplay = doc.createElement("DateTimeDisplay");
						Text f1_DateTimeDisplay_model = doc.createTextNode(""+((DateField)commonField).getDateFormatPattern());
						f1_DateTimeDisplay.appendChild(f1_DateTimeDisplay_model);
						field1.appendChild(f1_DateTimeDisplay);
					}
					
				}
				
				if(commonField.getField_Type().equals("Float"))
				{
					if(!(((FloatField)commonField).getFractionDigitals().equals("")))
					{
						Element f1_maxFractionDigits = doc.createElement("maxFractionDigits");
						Text f1_maxFractionDigits_model = doc.createTextNode("" + ((FloatField)commonField).getFractionDigitals());
						f1_maxFractionDigits.appendChild(f1_maxFractionDigits_model);
						field1.appendChild(f1_maxFractionDigits);
					}
				}
				
				if(commonField.getField_Type().equals("Enum"))
				{
					if(((EnumField)commonField).getSelectedEnum()!= null)
					{
						Element f1_selectFrom = doc.createElement("selectFrom");
						Text f1_selectFrom_model = doc.createTextNode(((EnumField)commonField).getSelectedEnum());
						f1_selectFrom.appendChild(f1_selectFrom_model);
						field1.appendChild(f1_selectFrom);
					}
				}
				if(commonField.getField_Type().equals("Lookup"))
				{
					Element f1_lookupcomponent = doc.createElement("lookup");
					Text f1_lookupcomponent_model = doc.createTextNode(((LookupField)commonField).getSelectedcomp());
					f1_lookupcomponent.appendChild(f1_lookupcomponent_model);
					field1.appendChild(f1_lookupcomponent);
					
					Element f1_mainlookup = doc.createElement("mainlookupfield");
					Text f1_mainlookup_model = doc.createTextNode(((LookupField)commonField).getMainlookup());
					f1_mainlookup.appendChild(f1_mainlookup_model);
					field1.appendChild(f1_mainlookup);
					
					for(int i = 0;i<((LookupField)commonField).getList().size();i++)
					{
						Element f1_lookupfield = doc.createElement("lookupfield");
						Text f1_lookupfield_model = doc.createTextNode(((LookupField)commonField).getFieldlist().get(i).toString());
						f1_lookupfield.appendChild(f1_lookupfield_model);
						field1.appendChild(f1_lookupfield);
					}
					
				}
			

		}
				root.appendChild(fields);

		}		
	

		
        //subcomponents项，明细表的内容，如果没有明细表则没有该部分
		
		if(container.getMainTable().getHasChildTable() == 1)
		{
			Element subcomponents = doc.createElement("subcomponents");
			if(container.getMainTable().getSubTabel().size()>0)
			{
				List subList = container.getMainTable().getSubTabel();
				this.doWriting(subList , root
						);

				root.appendChild(subcomponents);
			}		
		}
		
//		把XML文档输出到指定的文件 
		try
		{
//			FileOutputStream outStream = new FileOutputStream(outFile); 
//			OutputStreamWriter outWriter = new OutputStreamWriter(outStream,"UTF-8"); 
//			((XmlDocument) doc).write(outWriter, "UTF-8");  //应用utf-8编码方式
			
			IOStreams ioStreams = new IOStreams ();
			ioStreams.transformToFile(doc,outFile);
	
//			outWriter.close(); 
//			outStream.close();
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doWriting(List list , Element parent)
	{
		if(list.size() !=0)
		{
			Element subcomponents = doc.createElement("subcomponents");
			for(int i= 0;i< list.size();i++)
			{
				Element subcomponent = doc.createElement("subcomponent");
				ChildTable subTable = (ChildTable)(list.get(i));
				
				Element subid = doc.createElement("id");
				Text subid_model = doc.createTextNode(subTable.getId());  
				subid.appendChild(subid_model);
				subcomponent.appendChild(subid);
				
				StringBuffer sb1 = new StringBuffer(project.getGenPackageName());
				sb1.append(".");
				sb1.append(project.getPackageName());
				sb1.append(".");
				sb1.append(subTable.getName());
				Element subfullname = doc.createElement("fullname");
				Text subfullname_model = doc.createTextNode(sb1.toString());
				subfullname.appendChild(subfullname_model);
				subcomponent.appendChild(subfullname);
				
				Element subname = doc.createElement("name");
				Text subname_model = doc.createTextNode(subTable.getName());
				subname.appendChild(subname_model);
				subcomponent.appendChild(subname);
				
				Element subiname = doc.createElement("iname");
				CDATASection subiname_model = doc.createCDATASection(subTable.getIName());
				subiname.appendChild(subiname_model);
				subcomponent.appendChild(subiname);
				
				if(!(subTable.getDesc().equals("")))
				{
					Element subdesc = doc.createElement("desc");
					CDATASection subdesc_model = doc.createCDATASection(subTable.getDesc());
					subdesc.appendChild(subdesc_model);
					subcomponent.appendChild(subdesc);
				}
				
				Element subpositionx = doc.createElement("positionx");
				Text subpositionx_model = doc.createTextNode("" + subTable.getLocation().x);
				subpositionx.appendChild(subpositionx_model);
				subcomponent.appendChild(subpositionx);
				
				Element subpositiony = doc.createElement("positiony");
				Text subpositiony_model = doc.createTextNode("" + subTable.getLocation().y);
				subpositiony.appendChild(subpositiony_model);
				subcomponent.appendChild(subpositiony);
				
				
				Element fields  = doc.createElement("fields");
				
				Iterator itSubField = subTable.getChildren().iterator();
				while(itSubField.hasNext())
				{
				
				                 
					
					
					//由于5种类型字段不同，下面分别列出，具体保存时要稍加修改，建议：循环行号范围：133-352，用for循环，循环次数是componentTable中的元素个数，循环内部用swith-case
					CommonField commonField = (CommonField)itSubField.next();
		
					
						Element subfield1 = doc.createElement("field");
						
						Element subf1_name = doc.createElement("name");
						Text subf1_name_model = doc.createTextNode(commonField.getName());
						subf1_name.appendChild(subf1_name_model);
						subfield1.appendChild(subf1_name);
						
						Element subf1_type = doc.createElement("type");
					
						if(commonField.getField_Type().equals("AutoPNum"))
						{
							Text subf1_type_model = doc.createTextNode("0");
							subf1_type.appendChild(subf1_type_model);
							subfield1.appendChild(subf1_type);
						}
						
						if(commonField.getField_Type().equals("Autonum"))
						{
							Text subf1_type_model = doc.createTextNode("0");
							subf1_type.appendChild(subf1_type_model);
							subfield1.appendChild(subf1_type);
						}
						if(commonField.getField_Type().equals("String"))
						{
							Text subf1_type_model = doc.createTextNode("1");
							subf1_type.appendChild(subf1_type_model);
							subfield1.appendChild(subf1_type);
						}
						
						if(commonField.getField_Type().equals("Integer"))
						{
							
							Text subf1_type_model = doc.createTextNode("2");
							subf1_type.appendChild(subf1_type_model);
							subfield1.appendChild(subf1_type);
						}
						if(commonField.getField_Type().equals("Date"))
						{
							Text subf1_type_model = doc.createTextNode("3");
							subf1_type.appendChild(subf1_type_model);
							subfield1.appendChild(subf1_type);
						}
						if(commonField.getField_Type().equals("Float"))
						{
							Text subf1_type_model = doc.createTextNode("4");
							subf1_type.appendChild(subf1_type_model);
							subfield1.appendChild(subf1_type);;
						}
						if(commonField.getField_Type().equals("Enum"))
						{
							Text subf1_type_model = doc.createTextNode("5");
							subf1_type.appendChild(subf1_type_model);
							subfield1.appendChild(subf1_type);
						}
						if(commonField.getField_Type().equals("Lookup"))
						{
							Text subf1_type_model = doc.createTextNode("6");
							subf1_type.appendChild(subf1_type_model);
							subfield1.appendChild(subf1_type);
							
						}
						Element subf1_iname = doc.createElement("iname");
						CDATASection subf1_iname_model = doc.createCDATASection(commonField.getIName());
						subf1_iname.appendChild(subf1_iname_model);
						subfield1.appendChild(subf1_iname);
						
						if(commonField.getField_Type().equals("AutoPNum"))
						{
							Element subf1_isprimarykey = doc.createElement("isprimarykey");
							Text subf1_isprimarykey_model = doc.createTextNode("1");
							subf1_isprimarykey.appendChild(subf1_isprimarykey_model);
							subfield1.appendChild(subf1_isprimarykey);
						}
						else
						{
							Element subf1_isprimarykey = doc.createElement("isprimarykey");
							Text subf1_isprimarykey_model = doc.createTextNode("" + commonField.getIsBizKey());
							subf1_isprimarykey.appendChild(subf1_isprimarykey_model);
							subfield1.appendChild(subf1_isprimarykey);
							
						}
						
						Element subf1_isrequired = doc.createElement("isrequired");
						Text subf1_isrequired_model = doc.createTextNode("" + commonField.getMustBeFilled());
						subf1_isrequired.appendChild(subf1_isrequired_model);
						subfield1.appendChild(subf1_isrequired);
						
						if(commonField.getField_Type().equals("Autonum"))
						{
							Element subf1_isforeignkey = doc.createElement("isforeignkey");
							Text subf1_isforeignkey_model = doc.createTextNode("1");
							subf1_isforeignkey.appendChild(subf1_isforeignkey_model);
							subfield1.appendChild(subf1_isforeignkey);
						}
						else
						{
							Element subf1_isforeignkey = doc.createElement("isforeignkey");
							Text subf1_isforeignkey_model = doc.createTextNode(""+commonField.getIsPreKey());
							subf1_isforeignkey.appendChild(subf1_isforeignkey_model);
							subfield1.appendChild(subf1_isforeignkey);
						}
						
						Element subf1_isuserfield = doc.createElement("isuserfield");
						Text subf1_isuserfield_model = doc.createTextNode(""+commonField.getIsuserfield());
						subf1_isuserfield.appendChild(subf1_isuserfield_model);
						subfield1.appendChild(subf1_isuserfield);
						
						if(commonField.getField_Type().equals("String"))
						{
							Element subf1_length = doc.createElement("length");
							Text subf1_length_model = doc.createTextNode("" + ((StringField)commonField).getStrLength());
							subf1_length.appendChild(subf1_length_model);
							subfield1.appendChild(subf1_length);
						}
						
						if(commonField.getField_Type().equals("Date"))
						{
		
							Element subf1_DateTimeDisplay = doc.createElement("DateTimeDisplay");
							Text subf1_DateTimeDisplay_model = doc.createTextNode(""+((DateField)commonField).getDateFormatPattern());
							subf1_DateTimeDisplay.appendChild(subf1_DateTimeDisplay_model);
							subfield1.appendChild(subf1_DateTimeDisplay);
							
						}
						
						if(commonField.getField_Type().equals("Float") )
						{
							if(!(((FloatField)commonField).getFractionDigitals().equals("")))
							{
								Element subf1_maxFractionDigits = doc.createElement("maxFractionDigits");
								Text subf1_maxFractionDigits_model = doc.createTextNode("" + ((FloatField)commonField).getFractionDigitals());
								subf1_maxFractionDigits.appendChild(subf1_maxFractionDigits_model);
								subfield1.appendChild(subf1_maxFractionDigits);
							}
						
						}
						
						if(commonField.getField_Type().equals("Enum"))
						{
							if((((EnumField)commonField).getSelectedEnum()!=null))
							{
								Element subf1_selectFrom = doc.createElement("selectFrom");
								Text subf1_selectFrom_model = doc.createTextNode(((EnumField)commonField).getSelectedEnum());
								subf1_selectFrom.appendChild(subf1_selectFrom_model);
								subfield1.appendChild(subf1_selectFrom);
							}
						
		
						}
						
						if(commonField.getField_Type().equals("Lookup"))
						{
							Element f1_lookupcomponent = doc.createElement("lookup");
							Text f1_lookupcomponent_model = doc.createTextNode(((LookupField)commonField).getSelectedcomp());
							f1_lookupcomponent.appendChild(f1_lookupcomponent_model);
							subfield1.appendChild(f1_lookupcomponent);
							
							Element f1_mainlookup = doc.createElement("mainlookupfield");
							Text f1_mainlookup_model = doc.createTextNode(((LookupField)commonField).getMainlookup());
							f1_mainlookup.appendChild(f1_mainlookup_model);
							subfield1.appendChild(f1_mainlookup);
							
							if(((LookupField)commonField).getList().size()!=0)
							{
								Element f1_lookupfields = doc.createElement("bringbacks");
								for(int j = 0;j<((LookupField)commonField).getFieldlist().size();j++)
								{
									Element f1_lookupfield0 = doc.createElement("bringback");
									
									Element f1_newName = doc.createElement("newName");
									Text f1_newName_model = doc.createTextNode(((MsProject)((LookupField)commonField).getFieldlist().get(j)).getFieldName());
									f1_newName.appendChild(f1_newName_model);
									f1_lookupfield0.appendChild(f1_newName);
									
									Element f1_oldName = doc.createElement("Name");
									Text f1_oldName_model = doc.createTextNode(((MsProject)((LookupField)commonField).getFieldlist().get(j)).getFieldName());
									f1_oldName.appendChild(f1_oldName_model);
									f1_lookupfield0.appendChild(f1_oldName);
									
									Element f1_type = doc.createElement("type");
									Text f1_type_model = doc.createTextNode(((MsProject)((LookupField)commonField).getFieldlist().get(j)).getFieldType());
									f1_type.appendChild(f1_type_model);
									f1_lookupfield0.appendChild(f1_type);
									
									Element f1_listable = doc.createElement("listable");
									Text f1_listable_model = doc.createTextNode(((MsProject)((LookupField)commonField).getFieldlist().get(j)).getFieldListable());
									f1_listable.appendChild(f1_listable_model);
									f1_lookupfield0.appendChild(f1_listable);
									
									Element f1_searchable = doc.createElement("searchable");
									Text f1_searchable_model = doc.createTextNode(((MsProject)((LookupField)commonField).getFieldlist().get(j)).getFieldSearchable());
									f1_searchable.appendChild(f1_searchable_model);
									f1_lookupfield0.appendChild(f1_searchable);
									
//									Element f1_lookupfield = doc.createElement("lookupfield");
//									Text f1_lookupfield_model = doc.createTextNode(((LookupField)commonField).getFieldlist().get(j).toString());
//									f1_lookupfield.appendChild(f1_lookupfield_model);
//									f1_lookupfield0.appendChild(f1_lookupfield);
									f1_lookupfields.appendChild(f1_lookupfield0);
								}
								subfield1.appendChild(f1_lookupfields);
							}
							
						}
						fields.appendChild(subfield1);		
			}
			subcomponent.appendChild(fields);
			subcomponents.appendChild(subcomponent);
			parent.appendChild(subcomponents);
			List subList = ((Table)list.get(i)).getSubTabel();
			// digui
			doWriting(subList , subcomponent);
		}
		}
	}
	
}