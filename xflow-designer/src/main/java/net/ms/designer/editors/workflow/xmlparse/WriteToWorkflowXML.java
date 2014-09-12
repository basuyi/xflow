package net.ms.designer.editors.workflow.xmlparse;



import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.models.EndNode;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.ParameterPartial;
import net.ms.designer.editors.workflow.models.RouteOnlyActivity;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.SystemAppActivity;
import net.ms.designer.editors.workflow.models.UserAppActivity;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;



public class WriteToWorkflowXML {
	
	private WorkflowDiagram diagram;
	private MsProject myProject;
	private MsElement element;
	
	public WriteToWorkflowXML(MsProject project1, WorkflowDiagram diagram)
	{
		this.diagram = diagram;
//		project1.setWorkflowName(diagram.getProject().getWorkflowName());
//		project1.setWorkflowIname(diagram.getProject().getWorkflowIname());
		this.myProject = project1;
	}
	/**
	 * 
	 * @param element
	 * @param project1
	 */
	public WriteToWorkflowXML(MsElement element , MsProject project)
	{
		this.element = element;
		this.diagram = (WorkflowDiagram)element.getContainer();
		this.myProject = project;
	}
	public void writeProjectXMLFile(String file) throws Exception{
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
		
		MsProject project  =  this.myProject;
		
		Element richweb = doc.getDocumentElement();
		
		if(richweb.getElementsByTagName("project")!=null||richweb.getElementsByTagName("project").getLength()>0)
		{
			Element projectXML = (Element) richweb.getElementsByTagName("project").item(0);		
		
			NodeList packageList = projectXML.getElementsByTagName("package");
			for(int i=0; i<packageList.getLength(); i++)
			{
				String packageName = ((Element)packageList.item(i)).getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
				if(packageName.equals(project.getPackageName()))
				{
					NodeList componentList = ((Element)packageList.item(i)).getElementsByTagName("component");
	
					for(int j=0; j<componentList.getLength(); j++)
					{
						String componentName = ((Element)componentList.item(j)).getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
						if(componentName.equals(project.getComponentName()))
						{							
							boolean flag = true;
							NodeList workflowList = ((Element)componentList.item(j)).getElementsByTagName("workflow");
							
							if(workflowList == null ||workflowList.getLength()<1)
							{
								flag = true;
							}
//							if(workflowList != null&&workflowList.getLength()>0)
//							{
//								for(int m = 0; m<workflowList.getLength(); m++)
//								{
//									
//										Element workflowTemp = (Element) workflowList.item(0);
//										if(workflowTemp.getElementsByTagName("id").item(0).getFirstChild().getNodeValue().equals(project.getWorkflowId()))
//										{
//											flag = false;
//										}
//										if(workflowTemp.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(project.getWorkflowName()))
//										{
//											flag = false;
////											break;
//										}
//										if(!workflowTemp.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(project.getWorkflowName()))
//										{
//											flag = true;
//										}
//										if(workflowTemp.getElementsByTagName("iname")!=null && workflowTemp.getElementsByTagName("iname").getLength()>0 && !workflowTemp.getElementsByTagName("iname").item(0).getFirstChild().getNodeValue().equals(project.getWorkflowIname()))
//										{
//											flag = true;
//										}
//									
//									if(this.diagram.getIsSubflow() == true)
//									{
//										Element workflowTemp0 = (Element) workflowList.item(0);
//										NodeList subflowList = workflowTemp0.getElementsByTagName("subflow"); 
//										if(subflowList != null && subflowList.getLength()>0)
//										{
//											for(int n = 0; n<subflowList.getLength(); n++)
//											{
//												Element subflowTemp = (Element)subflowList.item(n);
//												if(subflowTemp.getElementsByTagName("id").item(0).getFirstChild().getNodeValue().equals(project.getSubflowId()))
//												{
//													flag = false;
//												}
//												Iterator itSubflow = this.myProject.getSubflowList().iterator();
//												SubFlowActivity subflowActivity = new SubFlowActivity();
//												while(itSubflow.hasNext())
//												{
//													SubFlowActivity subflow = (SubFlowActivity)itSubflow.next();
//													if(subflow.getSubflowId().equals(subflowTemp.getElementsByTagName("id").item(0).getFirstChild().getNodeValue()))
//													{
//														subflowActivity = subflow;
//														break;
//													}
//												}
//												if(subflowTemp.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(subflowActivity.getName()))
//												{
//													flag = false;
//												}
//											}
//										}
//									}
//								}
//							}							
							if(flag)
							{
								Element component = (Element)componentList.item(j);
		//						component.getElementsByTagName("hasWf").item(0).getFirstChild().setNodeValue("1");
								if(this.diagram.getIsSubflow() == false)
								{
//									if(component.getElementsByTagName("workflow") == null || component.getElementsByTagName("workflow").getLength()<1)
//									{
//										Element workflow = doc.createElement("workflow");
//										
//										Element wfId = doc.createElement("id");
//										Text wfId_model = doc.createTextNode(project.getWorkflowId());
//										wfId.appendChild(wfId_model);
//										workflow.appendChild(wfId);
//										
//										Element wfName = doc.createElement("name");
//										Text wfName_model = doc.createTextNode(project.getWorkflowName());
//										wfName.appendChild(wfName_model);
//										workflow.appendChild(wfName);
//										
//										if(project.getWorkflowIname() != null && project.getWorkflowIname().length()>0)
//										{
//											Element wfIname = doc.createElement("iname");
//											Text  wfIname_model = doc.createTextNode(project.getWorkflowIname());
//											wfIname.appendChild(wfIname_model);
//											workflow.appendChild(wfIname);
//										}
//																
//										Element wfPath = doc.createElement("path");
//										Text wfPath_model = doc.createTextNode(project.getProjectName()+"."+project.getPackageName()+"."+project.getComponentName()+"."+project.getWorkflowId());
//										wfPath.appendChild(wfPath_model);
//										workflow.appendChild(wfPath);
//										
//										Element hasWf = doc.createElement("hasWf");
//										Text hasWf_model = doc.createTextNode("1");
//										hasWf.appendChild(hasWf_model);
//										component.appendChild(hasWf);
//										
//										component.appendChild(workflow);
//									}
//									if(component.getElementsByTagName("workflow") != null && component.getElementsByTagName("workflow").getLength()>0)
//									{
//										Element workflow = (Element) component.getElementsByTagName("workflow").item(0);
//																			
//										workflow.getElementsByTagName("name").item(0).getFirstChild().setNodeValue(project.getWorkflowName());
//										
//										if(project.getWorkflowIname() != null && project.getWorkflowIname().length()>0)
//										{
//											if(workflow.getElementsByTagName("iname")!=null && workflow.getElementsByTagName("iname").getLength()>0)
//											{
//												workflow.getElementsByTagName("iname").item(0).getFirstChild().setNodeValue(project.getWorkflowIname());
//											}
//											else
//											{
//												Element wfIname = doc.createElement("iname");
//												Text  wfIname_model = doc.createTextNode(project.getWorkflowIname());
//												wfIname.appendChild(wfIname_model);
//												workflow.appendChild(wfIname);
//											}
//										}
//										
//										workflow.getElementsByTagName("path").item(0).getFirstChild().setNodeValue(project.getProjectName()+"."+project.getPackageName()+"."+project.getComponentName()+"."+project.getWorkflowId());
										
										if(component.getElementsByTagName("workflow") != null && component.getElementsByTagName("workflow").getLength()>0)
										{
											component.removeChild(component.getElementsByTagName("workflow").item(0));
										}
									
										Element workflow = doc.createElement("workflow");
										
										Element wfId = doc.createElement("id");
										Text wfId_model = doc.createTextNode(project.getWorkflowId());
										wfId.appendChild(wfId_model);
										workflow.appendChild(wfId);
										
										Element wfName = doc.createElement("name");
										Text wfName_model = doc.createTextNode(project.getWorkflowName());
										wfName.appendChild(wfName_model);
										workflow.appendChild(wfName);
										
										if(project.getWorkflowIname() != null && project.getWorkflowIname().length()>0)
										{
											Element wfIname = doc.createElement("iname");
											Text  wfIname_model = doc.createTextNode(project.getWorkflowIname());
											wfIname.appendChild(wfIname_model);
											workflow.appendChild(wfIname);
										}
																
										Element wfPath = doc.createElement("path");
										Text wfPath_model = doc.createTextNode(project.getProjectName()+"."+project.getPackageName()+"."+project.getComponentName()+"."+project.getWorkflowId());
										wfPath.appendChild(wfPath_model);
										workflow.appendChild(wfPath);
										
										if(component.getElementsByTagName("haswf")!= null && component.getElementsByTagName("haswf").getLength()>0)
										{
											component.removeChild(component.getElementsByTagName("haswf").item(0));
										}
										Element hasWf = doc.createElement("hasWf");
										Text hasWf_model = doc.createTextNode("1");
										hasWf.appendChild(hasWf_model);
										component.appendChild(hasWf);
										
										component.appendChild(workflow);
									
								}
								else
								{
									if(this.diagram.getIsSubflow() == true)
									{
										Element workflow = (Element)component.getElementsByTagName("workflow").item(0);
										NodeList subflowList = workflow.getElementsByTagName("subflow");
										if(subflowList != null)
										{
											for(int a = 0; a<workflow.getElementsByTagName("subflow").getLength(); )
											{
//												//System.out.println("subflowList.getLength():"+workflow.getElementsByTagName("subflow").getLength());
												workflow.removeChild(workflow.getElementsByTagName("subflow").item(0));
											}
										}
										
										for(int b = 0; b<project.getSubflowList().size(); b++)
										{
											MsProject subProject = ((SubFlowActivity)project.getSubflowList().get(b)).getSubflowDiagram().getProject();
											Element subflow = doc.createElement("subflow");
											
											Element subflowId = doc.createElement("id");
											Text subflowId_model = doc.createTextNode(((SubFlowActivity)project.getSubflowList().get(b)).getSubflowId());
											subflowId.appendChild(subflowId_model);
											subflow.appendChild(subflowId);
											
											if(((SubFlowActivity)project.getSubflowList().get(b)).getName() != null && ((SubFlowActivity)project.getSubflowList().get(b)).getName().length()>0)
											{
												Element subflowName = doc.createElement("name");
												Text subflowName_model = doc.createTextNode(((SubFlowActivity)project.getSubflowList().get(b)).getName());
												subflowName.appendChild(subflowName_model);
												subflow.appendChild(subflowName);
											}
											
											Element subflowPath = doc.createElement("path");
											Text subflowPath_model = doc.createTextNode(subProject.getProjectName()+"."+subProject.getPackageName()+"."+subProject.getComponentName()+"."+((SubFlowActivity)project.getSubflowList().get(b)).getSubflowId());
											subflowPath.appendChild(subflowPath_model);
											subflow.appendChild(subflowPath);
											
											workflow.appendChild(subflow);
										}
									}
									
								}
							}
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
	
	public void writeWorkflowXMLFile(String outFile) throws Exception{
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
		Element root = doc.createElement("workflow"); 
//		根元素添加上文档 
		doc.appendChild(root); 

		MsProject project = this.myProject;
		
		SubFlowActivity subflowTemp = null;
		if(this.diagram.getIsSubflow() == true)
		{
			Iterator itSub = project.getSubflowList().iterator();
			while(itSub.hasNext())
			{
				SubFlowActivity sub = (SubFlowActivity)itSub.next();
				if(sub.getSubflowId().equals(project.getSubflowId()))
				{
					subflowTemp = sub;
					break;
				}
			}
		}
		
		Element id = doc.createElement("id");
		String workflowId;
		if(this.diagram.getIsSubflow() == false)
		{
			workflowId = project.getWorkflowId();
		}
		else
		{
			workflowId = project.getSubflowId();
		}
		Text id_model = doc.createTextNode(workflowId);
		id.appendChild(id_model);
		root.appendChild(id);
		
		//name项
		Element name = doc.createElement("name");		
		WorkflowDiagram wfDiagram = this.diagram;
		Text name_model;
		if(this.diagram.getIsSubflow() == false)
		{
			name_model = doc.createTextNode(""+project.getWorkflowName());
		}
		else
		{
			name_model = doc.createTextNode(""+subflowTemp.getName());
		}
		name.appendChild(name_model);
		root.appendChild(name);
		
		//iname项
		if(diagram.getIsSubflow() == false &&project.getWorkflowIname() != null && project.getWorkflowIname().length()>0)
		{
			Element iname = doc.createElement("iname");
			CDATASection iname_model = doc.createCDATASection(""+project.getWorkflowIname());
			iname.appendChild(iname_model);
			root.appendChild(iname);
		}
		
//		iname项
		if(diagram.getIsSubflow() == true && subflowTemp.getIname()!= null && subflowTemp.getIname().length()>0)
		{
			Element iname = doc.createElement("iname");
			CDATASection iname_model = doc.createCDATASection(""+ subflowTemp.getIname());
			iname.appendChild(iname_model);
			root.appendChild(iname);
		}
		
		//path项
		Element path = doc.createElement("path");
		String path_temp;
		if(diagram.getIsSubflow() == false)
		{
			path_temp = project.getProjectName()+"."+project.getPackageName()+"."+project.getComponentName()+"."+project.getWorkflowId();
		}
		else
		{
			path_temp = project.getProjectName()+"."+project.getPackageName()+"."+project.getComponentName()+"."+project.getSubflowId();
		}
		Text path_model = doc.createTextNode(path_temp);
		path.appendChild(path_model);
		root.appendChild(path);
		
		//opentity
		if(wfDiagram.getOpEntity() !=null && wfDiagram.getOpEntity().length()>0)
		{
			Element opentity = doc.createElement("opentity");
			Text opentity_model = doc.createTextNode(""+wfDiagram.getOpEntity());
			opentity.appendChild(opentity_model);
			root.appendChild(opentity);
		}
		
		//information
		if(wfDiagram.getAllInfor() !=null && wfDiagram.getAllInfor().length()>0)
		{
			Element information = doc.createElement("information");
			Text information_model = doc.createTextNode(""+wfDiagram.getAllInfor());
			information.appendChild(information_model);
			root.appendChild(information);
		}
		
		//status
		Element status = doc.createElement("status");
		Text status_model = doc.createTextNode(""+wfDiagram.getStatus());
		status.appendChild(status_model);
		root.appendChild(status);
		
		if(wfDiagram.getParaList()!= null && wfDiagram.getParaList().size()>0){
			//wfparams项
			Element wfparams = doc.createElement("wfparams");
			
			Iterator it = wfDiagram.getParaList().iterator();
			while(it.hasNext()){
				//param项
				Element param = doc.createElement("param");
				ParameterEntire parameter = (ParameterEntire)it.next();
				
				//name项
				if(parameter.getParaName()!=null)
				{
					Element paraName = doc.createElement("name");	
					Text paraName_model = doc.createTextNode(""+parameter.getParaName());
					paraName.appendChild(paraName_model);
					param.appendChild(paraName);
				}
				
				//type项
				if(parameter.getParaType()!=null)
				{
					Element paraType = doc.createElement("type");
					Text paraType_model = doc.createTextNode(""+parameter.getParaType());
					paraType.appendChild(paraType_model);
					param.appendChild(paraType);
				}
//				
//				//isinput项
//				Element paraIsinput = doc.createElement("isinput");
//				boolean temp = parameter.getIsInput();
//				Text paraIsinput_model;
//				if(temp == true)
//					paraIsinput_model = doc.createTextNode("1");
//				else 
//					paraIsinput_model = doc.createTextNode("0");
//				paraIsinput.appendChild(paraIsinput_model);
//				param.appendChild(paraIsinput);
//				
//				//isoutput项
//				Element paraIsoutput = doc.createElement("isoutput");
//				Text paraIsoutput_model;
//				if(parameter.getIsOutput() == true)
//					paraIsoutput_model = doc.createTextNode("1");
//				else
//					paraIsoutput_model = doc.createTextNode("0");
//				paraIsoutput.appendChild(paraIsoutput_model);
//				param.appendChild(paraIsoutput);			
				
				wfparams.appendChild(param);	
			}
			root.appendChild(wfparams);
		}
		
		if(wfDiagram.getChildren()!=null && wfDiagram.getChildren().size()>0){
			//wfactivity项
			Iterator itActivity = wfDiagram.getChildren().iterator();
			while(itActivity.hasNext()){
				WorkflowBaseActivity node = (WorkflowBaseActivity)itActivity.next();
				
				//start node
				if(node.getActivity_type_constant().equals(Constants.WF_ACTIVITY_TYPE_START)){
					Element start = doc.createElement("wfactivity");
					
					//nodeid
					Element startId = doc.createElement("id");
					Text startId_model = doc.createTextNode(""+node.getNodeId());
					startId.appendChild(startId_model);
					start.appendChild(startId);
					
					
					//name
					Element startName = doc.createElement("name");
					Text startName_model = doc.createTextNode(""+node.getName());
					startName.appendChild(startName_model);
					start.appendChild(startName);
					
					//desc
					if(node.getActivity_desc()!=null && node.getActivity_desc().length()>0)
					{
						Element startDesc = doc.createElement("desc");
						Text startDesc_model = doc.createTextNode(""+node.getActivity_desc());
						startDesc.appendChild(startDesc_model);
						start.appendChild(startDesc);
					}
					
					//type
					Element startType = doc.createElement("type");
					Text startType_model = doc.createTextNode(""+node.getActivity_type_constant());
					startType.appendChild(startType_model);
					start.appendChild(startType);
					
					//positionx
					Element startPositionx = doc.createElement("positionx");
					Text startPositionx_model = doc.createTextNode(""+(new Integer(node.getLocation().x)).toString());
					startPositionx.appendChild(startPositionx_model);
					start.appendChild(startPositionx);
					
					//positiony
					Element startPositiony = doc.createElement("positiony");
					Text startPositiony_model = doc.createTextNode(""+(new Integer(node.getLocation().y)).toString());
					startPositiony.appendChild(startPositiony_model);
					start.appendChild(startPositiony);					
					
					root.appendChild(start);
				}
				
				//用户应用
				if(node.getActivity_type_constant().equals( Constants.WF_ACTIVITY_TYPE_USER_APP)){
					Element userApp = doc.createElement("wfactivity");
					
//					nodeid
					Element userAppId = doc.createElement("id");
					Text userAppId_model = doc.createTextNode(""+node.getNodeId());
					userAppId.appendChild(userAppId_model);
					userApp.appendChild(userAppId);
					
//					name
					Element userAppName = doc.createElement("name");
					Text userAppName_model = doc.createTextNode(""+node.getName());
					userAppName.appendChild(userAppName_model);
					userApp.appendChild(userAppName);
					
					//desc
					if(node.getActivity_desc()!=null && node.getActivity_desc().length()>0)
					{
						Element userAppDesc = doc.createElement("desc");
						Text userAppDesc_model = doc.createTextNode(""+node.getActivity_desc());
						userAppDesc.appendChild(userAppDesc_model);
						userApp.appendChild(userAppDesc);
					}
					
					//type
					Element userAppType = doc.createElement("type");
					Text userAppType_model = doc.createTextNode(""+node.getActivity_type_constant());
					userAppType.appendChild(userAppType_model);
					userApp.appendChild(userAppType);
					
					//positionx
					Element userAppPositionx = doc.createElement("positionx");
					Text userAppPositionx_model = doc.createTextNode(""+(new Integer(node.getLocation().x)).toString());
					userAppPositionx.appendChild(userAppPositionx_model);
					userApp.appendChild(userAppPositionx);
					
					//positiony
					Element userAppPositiony = doc.createElement("positiony");
					Text userAppPositiony_model = doc.createTextNode(""+(new Integer(node.getLocation().y)).toString());
					userAppPositiony.appendChild(userAppPositiony_model);
					userApp.appendChild(userAppPositiony);	
					
					//jointype
					if(node.getActivity_join_type()!=null && node.getActivity_join_type().length()>0)
					{
						Element userAppJoinType = doc.createElement("jointype");
						Text userAppJoinType_model = doc.createTextNode(""+node.getActivity_join_type());
						userAppJoinType.appendChild(userAppJoinType_model);
						userApp.appendChild(userAppJoinType);
					}
					
					//completetype
					if(node.getActivity_finish_type()!=null && node.getActivity_finish_type().length()>0)
					{
						Element userAppCompletetype = doc.createElement("completetype");
						Text userAppCompletetype_model = doc.createTextNode(""+node.getActivity_finish_type());
						userAppCompletetype.appendChild(userAppCompletetype_model);
						userApp.appendChild(userAppCompletetype);
					}
					
					//app
					if(((UserAppActivity)node).getApplicationName()!=null && ((UserAppActivity)node).getApplicationName().length()>0)
					{
						Element userAppApplication = doc.createElement("app");
						
						//application id
						if(((UserAppActivity)node).getApplicationId() != null && ((UserAppActivity)node).getApplicationId().length()>0)
						{
							Element userAppApplicationId = doc.createElement("id");
							Text userAppApplicationId_model = doc.createTextNode(((UserAppActivity)node).getApplicationId());
							userAppApplicationId.appendChild(userAppApplicationId_model);
							userAppApplication.appendChild(userAppApplicationId);
						}
						
						//name
						Element userAppApplicationName = doc.createElement("name");
						Text userAppApplicationName_model = doc.createTextNode(""+((UserAppActivity)node).getApplicationName());
						userAppApplicationName.appendChild(userAppApplicationName_model);
						userAppApplication.appendChild(userAppApplicationName);
						
						//params
						if(((UserAppActivity)node).getActivity_param()!=null && ((UserAppActivity)node).getActivity_param().size()>0){
							Element userAppApplicationParams = doc.createElement("params");
							
							Iterator itUserAppParam = ((UserAppActivity)node).getActivity_param().iterator();
							while(itUserAppParam.hasNext()){
								ParameterPartial paraPartial = (ParameterPartial)itUserAppParam.next();
								
								Element  userAppApplicationParam = doc.createElement("param");
								
								//formalpara
								Element userAppApplicationParamFormalPara = doc.createElement("formalpara");
								Text userAppApplicationParamFormalPara_model = doc.createTextNode(""+paraPartial.getFormalPara().getParaName());
								userAppApplicationParamFormalPara.appendChild(userAppApplicationParamFormalPara_model);
								userAppApplicationParam.appendChild(userAppApplicationParamFormalPara);
								
								//realpara
								Element userAppApplicationParamRealPara = doc.createElement("realpara");
								Text userAppApplicationParamRealPara_model = doc.createTextNode(""+paraPartial.getRealPara().getParaName());
								userAppApplicationParamRealPara.appendChild(userAppApplicationParamRealPara_model);
								userAppApplicationParam.appendChild(userAppApplicationParamRealPara);
								
								//isexpression
								Element userAppApplicationParamIsExpression = doc.createElement("isexpression");
								Text userAppApplicationParamIsExpression_model;
								if(paraPartial.getIsExpression() == true)
									userAppApplicationParamIsExpression_model = doc.createTextNode("1");
								else 
									userAppApplicationParamIsExpression_model = doc.createTextNode("0");
								userAppApplicationParamIsExpression.appendChild(userAppApplicationParamIsExpression_model);
								userAppApplicationParam.appendChild(userAppApplicationParamIsExpression);
															
								userAppApplicationParams.appendChild(userAppApplicationParam);
							}
							userAppApplication.appendChild(userAppApplicationParams);				
						}
						userApp.appendChild(userAppApplication);
					}
					
					//executer,空
					Element userAppExecuter = doc.createElement("executer");
					
					Element userAppExecuterType = doc.createElement("type");
					Text userAppExecuterType_model = doc.createTextNode("1");
					userAppExecuterType.appendChild(userAppExecuterType_model);
					userAppExecuter.appendChild(userAppExecuterType);
					
					Element userAppExecuterName = doc.createElement("name");
					Text userAppExecuterName_model = doc.createTextNode("job_leader");
					userAppExecuterName.appendChild(userAppExecuterName_model);
					userAppExecuter.appendChild(userAppExecuterName);					
					
					userApp.appendChild(userAppExecuter);
					
					//Transitions
					if(((UserAppActivity)node).getInputs()!=null && ((UserAppActivity)node).getInputs().size()>0)
					{
						Element userAppTransitions = doc.createElement("transitions");
						
						Iterator ituserAppTransitions = ((UserAppActivity)node).getInputs().iterator();
						while(ituserAppTransitions.hasNext()){
							Element userAppTransition = doc.createElement("transition");
							
							Wire userAppTransitionsWire = (Wire)ituserAppTransitions.next();
							WorkflowSubPart userAppTransitionsource = userAppTransitionsWire.getSource();
							
							//id
							Element userAppTransitionId = doc.createElement("id");
							Text userAppTransitionId_model = doc.createTextNode(""+userAppTransitionsWire.getWireId());
							userAppTransitionId.appendChild(userAppTransitionId_model);
							userAppTransition.appendChild(userAppTransitionId);
							
							//fromactivity
							Element userAppTransitionFromActivity = doc.createElement("fromactivity");
							Text userAppTransitionFromActivity_model = doc.createTextNode(""+userAppTransitionsource.getNodeId());
							userAppTransitionFromActivity.appendChild(userAppTransitionFromActivity_model);
							userAppTransition.appendChild(userAppTransitionFromActivity);
							
							//positionx
							Element userAppTransitionPositionx = doc.createElement("positionx");
							Text userAppTransitionPositionx_model = doc.createTextNode(""+(new Integer(userAppTransitionsource.getLocation().x)).toString());
							userAppTransitionPositionx.appendChild(userAppTransitionPositionx_model);
							userAppTransition.appendChild(userAppTransitionPositionx);
							
							//positiony
							Element userAppTransitionPositiony = doc.createElement("positiony");
							Text userAppTransitionPositiony_model = doc.createTextNode(""+(new Integer(userAppTransitionsource.getLocation().y)).toString());
							userAppTransitionPositiony.appendChild(userAppTransitionPositiony_model);
							userAppTransition.appendChild(userAppTransitionPositiony);
							
							//desc
							if(userAppTransitionsWire.getDescription()!=null && userAppTransitionsWire.getDescription().length()>0)
							{
								Element userAppTransitionDesc = doc.createElement("desc");
								Text userAppTransitionDesc_model = doc.createTextNode(""+userAppTransitionsWire.getDescription());
								userAppTransitionDesc.appendChild(userAppTransitionDesc_model);
								userAppTransition.appendChild(userAppTransitionDesc);
							}
							
							//condition
							if(userAppTransitionsWire.getTransitionCondition()!=null && userAppTransitionsWire.getTransitionCondition().length()>0)
							{
								Element userAppTransitionCondition = doc.createElement("condition");
								CDATASection userAppTransitionCondition_model = doc.createCDATASection(""+userAppTransitionsWire.getTransitionCondition());
								userAppTransitionCondition.appendChild(userAppTransitionCondition_model);
								userAppTransition.appendChild(userAppTransitionCondition);
							}
							
							userAppTransitions.appendChild(userAppTransition);	
							
						}
						userApp.appendChild(userAppTransitions);
					}
					root.appendChild(userApp);					
				}
				
				
				//系统应用
				if(node.getActivity_type_constant().equals(Constants.WF_ACTIVITY_TYPE_SYS_APP)){
					Element sysApp = doc.createElement("wfactivity");
					
//					nodeid
					Element sysAppId = doc.createElement("id");
					Text sysAppId_model = doc.createTextNode(""+node.getNodeId());
					sysAppId.appendChild(sysAppId_model);
					sysApp.appendChild(sysAppId);
					
//					name
					Element sysAppName = doc.createElement("name");
					Text sysAppName_model = doc.createTextNode(""+node.getName());
					sysAppName.appendChild(sysAppName_model);
					sysApp.appendChild(sysAppName);
					
					//desc
					if(node.getActivity_desc()!=null && node.getActivity_desc().length()>0)
					{
						Element sysAppDesc = doc.createElement("desc");
						Text sysAppDesc_model = doc.createTextNode(""+node.getActivity_desc());
						sysAppDesc.appendChild(sysAppDesc_model);
						sysApp.appendChild(sysAppDesc);
					}
					
					//type
					Element sysAppType = doc.createElement("type");
					Text sysAppType_model = doc.createTextNode(""+node.getActivity_type_constant());
					sysAppType.appendChild(sysAppType_model);
					sysApp.appendChild(sysAppType);
					
					//positionx
					Element sysAppPositionx = doc.createElement("positionx");
					Text sysAppPositionx_model = doc.createTextNode(""+(new Integer(node.getLocation().x)).toString());
					sysAppPositionx.appendChild(sysAppPositionx_model);
					sysApp.appendChild(sysAppPositionx);
					
					//positiony
					Element sysAppPositiony = doc.createElement("positiony");
					Text sysAppPositiony_model = doc.createTextNode(""+(new Integer(node.getLocation().y)).toString());
					sysAppPositiony.appendChild(sysAppPositiony_model);
					sysApp.appendChild(sysAppPositiony);	
					
					//jointype
					if(node.getActivity_join_type()!=null && node.getActivity_join_type().length()>0)
					{
						Element sysAppJoinType = doc.createElement("jointype");
						Text sysAppJoinType_model = doc.createTextNode(""+node.getActivity_join_type());
						sysAppJoinType.appendChild(sysAppJoinType_model);
						sysApp.appendChild(sysAppJoinType);
					}
					
					//app
					if(((SystemAppActivity)node).getApplicationName()!=null && ((SystemAppActivity)node).getApplicationName().length()>0)
					{
						Element sysAppApplication = doc.createElement("app");
						
						//application id
						if(((SystemAppActivity)node).getApplicationId() != null && ((SystemAppActivity)node).getApplicationId().length()>0)
						{
							Element sysAppApplicationId = doc.createElement("id");
							Text sysAppApplicationId_model = doc.createTextNode(((SystemAppActivity)node).getApplicationId());
							sysAppApplicationId.appendChild(sysAppApplicationId_model);
							sysAppApplication.appendChild(sysAppApplicationId);
						}
						
						//name
						Element sysAppApplicationName = doc.createElement("name");
						Text sysAppApplicationName_model = doc.createTextNode(""+((SystemAppActivity)node).getApplicationName());
						sysAppApplicationName.appendChild(sysAppApplicationName_model);
						sysAppApplication.appendChild(sysAppApplicationName);
						
						//params
						if(((SystemAppActivity)node).getActivity_param()!=null && ((SystemAppActivity)node).getActivity_param().size()>0){
							Element sysAppApplicationParams = doc.createElement("params");
							
							Iterator itSysAppParam = ((SystemAppActivity)node).getActivity_param().iterator();
							while(itSysAppParam.hasNext()){
								ParameterPartial paraPartial = (ParameterPartial)itSysAppParam.next();
								
								Element  sysAppApplicationParam = doc.createElement("param");
								
								//formalpara
								Element sysAppApplicationParamFormalPara = doc.createElement("formalpara");
								Text sysAppApplicationParamFormalPara_model = doc.createTextNode(""+paraPartial.getFormalPara().getParaName());
								sysAppApplicationParamFormalPara.appendChild(sysAppApplicationParamFormalPara_model);
								sysAppApplicationParam.appendChild(sysAppApplicationParamFormalPara);
								
								//realpara
								Element sysAppApplicationParamRealPara = doc.createElement("realpara");
								Text sysAppApplicationParamRealPara_model = doc.createTextNode(""+paraPartial.getRealPara().getParaName());
								sysAppApplicationParamRealPara.appendChild(sysAppApplicationParamRealPara_model);
								sysAppApplicationParam.appendChild(sysAppApplicationParamRealPara);
								
								//isexpression
								Element sysAppApplicationParamIsExpression = doc.createElement("isexpression");
								Text sysAppApplicationParamIsExpression_model;
								if(paraPartial.getIsExpression() == true)
									sysAppApplicationParamIsExpression_model = doc.createTextNode("1");
								else 
									sysAppApplicationParamIsExpression_model = doc.createTextNode("0");
								sysAppApplicationParamIsExpression.appendChild(sysAppApplicationParamIsExpression_model);
								sysAppApplicationParam.appendChild(sysAppApplicationParamIsExpression);
															
								sysAppApplicationParams.appendChild(sysAppApplicationParam);
							}
							sysAppApplication.appendChild(sysAppApplicationParams);				
						}
						sysApp.appendChild(sysAppApplication);
					}
					
//					transitions
					if(((SystemAppActivity)node).getInputs()!=null && ((SystemAppActivity)node).getInputs().size()>0)
					{
						Element sysAppTransitions = doc.createElement("transitions");
						
						Iterator itsysAppTransitions = ((SystemAppActivity)node).getInputs().iterator();
						while(itsysAppTransitions.hasNext()){
							Element sysAppTransition = doc.createElement("transition");
							
							Wire sysAppTransitionsWire = (Wire)itsysAppTransitions.next();
							WorkflowSubPart sysAppTransitionsource = sysAppTransitionsWire.getSource();
							
//							id
							Element sysAppTransitionId = doc.createElement("id");
							Text sysAppTransitionId_model = doc.createTextNode(""+sysAppTransitionsWire.getWireId());
							sysAppTransitionId.appendChild(sysAppTransitionId_model);
							sysAppTransition.appendChild(sysAppTransitionId);
							
							//fromactivity
							Element sysAppTransitionFromActivity = doc.createElement("fromactivity");
							Text sysAppTransitionFromActivity_model = doc.createTextNode(""+sysAppTransitionsource.getNodeId());
							sysAppTransitionFromActivity.appendChild(sysAppTransitionFromActivity_model);
							sysAppTransition.appendChild(sysAppTransitionFromActivity);
							
							//positionx
							Element sysAppTransitionPositionx = doc.createElement("positionx");
							Text sysAppTransitionPositionx_model = doc.createTextNode(""+(new Integer(sysAppTransitionsource.getLocation().x)).toString());
							sysAppTransitionPositionx.appendChild(sysAppTransitionPositionx_model);
							sysAppTransition.appendChild(sysAppTransitionPositionx);
							
							//positiony
							Element sysAppTransitionPositiony = doc.createElement("positiony");
							Text sysAppTransitionPositiony_model = doc.createTextNode(""+(new Integer(sysAppTransitionsource.getLocation().y)).toString());
							sysAppTransitionPositiony.appendChild(sysAppTransitionPositiony_model);
							sysAppTransition.appendChild(sysAppTransitionPositiony);
							
							//desc
							if(sysAppTransitionsWire.getDescription()!=null && sysAppTransitionsWire.getDescription().length()>0)
							{
								Element sysAppTransitionDesc = doc.createElement("desc");
								Text sysAppTransitionDesc_model = doc.createTextNode(""+sysAppTransitionsWire.getDescription());
								sysAppTransitionDesc.appendChild(sysAppTransitionDesc_model);
								sysAppTransition.appendChild(sysAppTransitionDesc);
							}
							
							//condition
							if(sysAppTransitionsWire.getTransitionCondition()!=null && sysAppTransitionsWire.getDescription().length()>0)
							{
								Element sysAppTransitionCondition = doc.createElement("condition");
								CDATASection sysAppTransitionCondition_model = doc.createCDATASection(""+sysAppTransitionsWire.getTransitionCondition());
								sysAppTransitionCondition.appendChild(sysAppTransitionCondition_model);
								sysAppTransition.appendChild(sysAppTransitionCondition);
							}
							
							sysAppTransitions.appendChild(sysAppTransition);	
							
						}
						sysApp.appendChild(sysAppTransitions);
					}
					
					root.appendChild(sysApp);
				}
				
				//子流节点
				if(node.getActivity_type_constant().equals(Constants.WF_ACTIVITY_TYPE_SUBFLOW)){
					Element subflow = doc.createElement("wfactivity");
					
//					nodeid
					Element subflowId = doc.createElement("id");
					Text subflowId_model = doc.createTextNode(""+node.getNodeId());
					subflowId.appendChild(subflowId_model);
					subflow.appendChild(subflowId);
					
//					name
					Element subflowName = doc.createElement("name");
					Text subflowName_model = doc.createTextNode(""+node.getName());
					subflowName.appendChild(subflowName_model);
					subflow.appendChild(subflowName);
					
					//desc
					if(node.getActivity_desc()!=null && node.getActivity_desc().length()>0)
					{
						Element subflowDesc = doc.createElement("desc");
						Text subflowDesc_model = doc.createTextNode(""+node.getActivity_desc());
						subflowDesc.appendChild(subflowDesc_model);
						subflow.appendChild(subflowDesc);
					}
					
					//type
					Element subflowType = doc.createElement("type");
					Text subflowType_model = doc.createTextNode(""+node.getActivity_type_constant());
					subflowType.appendChild(subflowType_model);
					subflow.appendChild(subflowType);
					
					//positionx
					Element subflowPositionx = doc.createElement("positionx");
					Text subflowPositionx_model = doc.createTextNode(""+(new Integer(node.getLocation().x)).toString());
					subflowPositionx.appendChild(subflowPositionx_model);
					subflow.appendChild(subflowPositionx);
					
					//positiony
					Element subflowPositiony = doc.createElement("positiony");
					Text subflowPositiony_model = doc.createTextNode(""+(new Integer(node.getLocation().y)).toString());
					subflowPositiony.appendChild(subflowPositiony_model);
					subflow.appendChild(subflowPositiony);	
					
					//jointype
					if(node.getActivity_join_type()!=null && node.getActivity_join_type().length()>0)
					{
						Element subflowJoinType = doc.createElement("jointype");
						Text subflowJoinType_model = doc.createTextNode(""+node.getActivity_join_type());
						subflowJoinType.appendChild(subflowJoinType_model);
						subflow.appendChild(subflowJoinType);
					}
					
					//execmode
					if(((SubFlowActivity)node).getExecMode() != null && ((SubFlowActivity)node).getExecMode().length()>0)
					{
						Element subflowExecMode = doc.createElement("execmode");
						Text subflowExecMode_model = doc.createTextNode(((SubFlowActivity)node).getExecMode());
						subflowExecMode.appendChild(subflowExecMode_model);
						subflow.appendChild(subflowExecMode);
					}
					
					//subflow
					if(((SubFlowActivity)node).getSubflowId()!=null && ((SubFlowActivity)node).getSubflowId().length()>0)
					{
						Element subflowSubflow = doc.createElement("subflow");
						
						//id
						Element subflowSubflowId = doc.createElement("id");
						Text subflowSubflowId_model = doc.createTextNode(((SubFlowActivity)node).getSubflowId());
						subflowSubflowId.appendChild(subflowSubflowId_model);
						subflowSubflow.appendChild(subflowSubflowId);
						
						//name
						if(((SubFlowActivity)node).getSubflowName()!=null && ((SubFlowActivity)node).getSubflowName().length()>0)
						{
							Element subflowSubflowName = doc.createElement("name");
							Text subflowSubflowName_model = doc.createTextNode(""+((SubFlowActivity)node).getSubflowName());
							subflowSubflowName.appendChild(subflowSubflowName_model);
							subflowSubflow.appendChild(subflowSubflowName);
						}
						
						//path
						if(((SubFlowActivity)node).getSubflowPath()!=null && ((SubFlowActivity)node).getSubflowPath().length()>0)
						{
							Element subflowSubflowPath = doc.createElement("path");
							Text subflowSubflowPath_model = doc.createTextNode(""+((SubFlowActivity)node).getSubflowPath());
							subflowSubflowPath.appendChild(subflowSubflowPath_model);
							subflowSubflow.appendChild(subflowSubflowPath);
						}
						
	//					params
						if(((SubFlowActivity)node).getActivity_param()!=null && ((SubFlowActivity)node).getActivity_param().size()>0){
							Element subflowSubflowParams = doc.createElement("params");
							
							Iterator itSubflowParam = ((SubFlowActivity)node).getActivity_param().iterator();
							while(itSubflowParam.hasNext()){
								ParameterPartial paraPartial = (ParameterPartial)itSubflowParam.next();
								
								Element  subflowSubflowParam = doc.createElement("param");
								
								//formalpara
								Element subflowSubflowParamFormalPara = doc.createElement("formalpara");
								Text subflowSubflowParamFormalPara_model = doc.createTextNode(""+paraPartial.getFormalPara().getParaName());
								subflowSubflowParamFormalPara.appendChild(subflowSubflowParamFormalPara_model);
								subflowSubflowParam.appendChild(subflowSubflowParamFormalPara);
								
								//realpara
								Element subflowSubflowParamRealPara = doc.createElement("realpara");
								Text subflowSubflowParamRealPara_model = doc.createTextNode(""+paraPartial.getRealPara().getParaName());
								subflowSubflowParamRealPara.appendChild(subflowSubflowParamRealPara_model);
								subflowSubflowParam.appendChild(subflowSubflowParamRealPara);
								
								//isexpression
								Element subflowSubflowParamIsExpression = doc.createElement("isexpression");
								Text subflowSubflowParamIsExpression_model;
								if(paraPartial.getIsExpression() == true)
									subflowSubflowParamIsExpression_model = doc.createTextNode("1");
								else 
									subflowSubflowParamIsExpression_model = doc.createTextNode("0");
								subflowSubflowParamIsExpression.appendChild(subflowSubflowParamIsExpression_model);
								subflowSubflowParam.appendChild(subflowSubflowParamIsExpression);
															
								subflowSubflowParams.appendChild(subflowSubflowParam);
							}
							subflowSubflow.appendChild(subflowSubflowParams);				
						}
						
						subflow.appendChild(subflowSubflow);
					}
					
					//Transitions
					if(((SubFlowActivity)node).getInputs()!=null && ((SubFlowActivity)node).getInputs().size()>0)
					{
						Element subflowTransitions = doc.createElement("transitions");
						
						Iterator itsubflowTransitions = ((SubFlowActivity)node).getInputs().iterator();
						while(itsubflowTransitions.hasNext()){
							Element subflowTransition = doc.createElement("transition");
							
							Wire subflowTransitionsWire = (Wire)itsubflowTransitions.next();
							WorkflowSubPart subflowTransitionSource = subflowTransitionsWire.getSource();
							
//							id
							Element subflowTransitionId = doc.createElement("id");
							Text subflowTransitionId_model = doc.createTextNode(""+subflowTransitionsWire.getWireId());
							subflowTransitionId.appendChild(subflowTransitionId_model);
							subflowTransition.appendChild(subflowTransitionId);
							
							//fromactivity
							Element subflowTransitionFromActivity = doc.createElement("fromactivity");
							Text subflowTransitionFromActivity_model = doc.createTextNode(""+subflowTransitionSource.getNodeId());
							subflowTransitionFromActivity.appendChild(subflowTransitionFromActivity_model);
							subflowTransition.appendChild(subflowTransitionFromActivity);
							
							//positionx
							Element subflowTransitionPositionx = doc.createElement("positionx");
							Text subflowTransitionPositionx_model = doc.createTextNode(""+(new Integer(subflowTransitionSource.getLocation().x)).toString());
							subflowTransitionPositionx.appendChild(subflowTransitionPositionx_model);
							subflowTransition.appendChild(subflowTransitionPositionx);
							
							//positiony
							Element subflowTransitionPositiony = doc.createElement("positiony");
							Text subflowTransitionPositiony_model = doc.createTextNode(""+(new Integer(subflowTransitionSource.getLocation().y)).toString());
							subflowTransitionPositiony.appendChild(subflowTransitionPositiony_model);
							subflowTransition.appendChild(subflowTransitionPositiony);
							
							//desc
							if(subflowTransitionsWire.getDescription()!=null && subflowTransitionsWire.getDescription().length()>0)
							{
								Element subflowTransitionDesc = doc.createElement("desc");
								Text subflowTransitionDesc_model = doc.createTextNode(""+subflowTransitionsWire.getDescription());
								subflowTransitionDesc.appendChild(subflowTransitionDesc_model);
								subflowTransition.appendChild(subflowTransitionDesc);
							}
							
							//condition
							if(subflowTransitionsWire.getTransitionCondition()!=null && subflowTransitionsWire.getTransitionCondition().length()>0)
							{
								Element subflowTransitionCondition = doc.createElement("condition");
								CDATASection subflowTransitionCondition_model = doc.createCDATASection(""+subflowTransitionsWire.getTransitionCondition());
								subflowTransitionCondition.appendChild(subflowTransitionCondition_model);
								subflowTransition.appendChild(subflowTransitionCondition);
							}
							
							subflowTransitions.appendChild(subflowTransition);	
							
						}
						subflow.appendChild(subflowTransitions);
					}
					
					root.appendChild(subflow);
				}
				
				
				//通路节点
//				if(node.getActivity_type_constant().equals(Constants.WF_ACTIVITY_TYPE_ROUTE_ONLY)){
//					Element routeOnly = doc.createElement("wfactivity");
//					
////					nodeid
//					Element routeOnlyId = doc.createElement("id");
//					Text routeOnlyId_model = doc.createTextNode(""+node.getNodeId());
//					routeOnlyId.appendChild(routeOnlyId_model);
//					routeOnly.appendChild(routeOnlyId);
//					
////					name
//					Element routeOnlyName = doc.createElement("name");
//					Text routeOnlyName_model = doc.createTextNode(""+node.getName());
//					routeOnlyName.appendChild(routeOnlyName_model);
//					routeOnly.appendChild(routeOnlyName);
//					
//					//desc
//					if(node.getActivity_desc()!=null && node.getActivity_desc().length()>0)
//					{
//						Element routeOnlyDesc = doc.createElement("desc");
//						Text routeOnlyDesc_model = doc.createTextNode(""+node.getActivity_desc());
//						routeOnlyDesc.appendChild(routeOnlyDesc_model);
//						routeOnly.appendChild(routeOnlyDesc);
//					}
//					
//					//type
//					Element routeOnlyType = doc.createElement("type");
//					Text routeOnlyType_model = doc.createTextNode(""+node.getActivity_type_constant());
//					routeOnlyType.appendChild(routeOnlyType_model);
//					routeOnly.appendChild(routeOnlyType);
//					
//					//positionx
//					Element routeOnlyPositionx = doc.createElement("positionx");
//					Text routeOnlyPositionx_model = doc.createTextNode(""+(new Integer(node.getLocation().x)).toString());
//					routeOnlyPositionx.appendChild(routeOnlyPositionx_model);
//					routeOnly.appendChild(routeOnlyPositionx);
//					
//					//positiony
//					Element routeOnlyPositiony = doc.createElement("positiony");
//					Text routeOnlyPositiony_model = doc.createTextNode(""+(new Integer(node.getLocation().y)).toString());
//					routeOnlyPositiony.appendChild(routeOnlyPositiony_model);
//					routeOnly.appendChild(routeOnlyPositiony);	
//					
//					//jointype
//					if(node.getActivity_join_type()!=null && node.getActivity_join_type().length()>0)
//					{
//						Element routeOnlyJoinType = doc.createElement("jointype");
//						Text routeOnlyJoinType_model = doc.createTextNode(""+node.getActivity_join_type());
//						routeOnlyJoinType.appendChild(routeOnlyJoinType_model);
//						routeOnly.appendChild(routeOnlyJoinType);
//					}
//					
////					Transitions
//					if(((RouteOnlyActivity)node).getInputs()!=null && ((RouteOnlyActivity)node).getInputs().size()>0)
//					{
//						Element routeOnlyTransitions = doc.createElement("transitions");
//						
//						Iterator itrouteOnlyTransitions = ((RouteOnlyActivity)node).getInputs().iterator();
//						while(itrouteOnlyTransitions.hasNext()){
//							Element routeOnlyTransition = doc.createElement("transition");
//							
//							Wire routeOnlyTransitionsWire = (Wire)itrouteOnlyTransitions.next();
//							WorkflowSubPart routeOnlyTransitionSource = routeOnlyTransitionsWire.getSource();
//							
////							id
//							Element routeOnlyTransitionId = doc.createElement("id");
//							Text routeOnlyTransitionId_model = doc.createTextNode(""+routeOnlyTransitionsWire.getWireId());
//							routeOnlyTransitionId.appendChild(routeOnlyTransitionId_model);
//							routeOnlyTransition.appendChild(routeOnlyTransitionId);
//							
//							//fromactivity
//							Element routeOnlyTransitionFromActivity = doc.createElement("fromactivity");
//							Text routeOnlyTransitionFromActivity_model = doc.createTextNode(""+routeOnlyTransitionSource.getNodeId());
//							routeOnlyTransitionFromActivity.appendChild(routeOnlyTransitionFromActivity_model);
//							routeOnlyTransition.appendChild(routeOnlyTransitionFromActivity);
//							
//							//positionx
//							Element routeOnlyTransitionPositionx = doc.createElement("positionx");
//							Text routeOnlyTransitionPositionx_model = doc.createTextNode(""+(new Integer(routeOnlyTransitionSource.getLocation().x)).toString());
//							routeOnlyTransitionPositionx.appendChild(routeOnlyTransitionPositionx_model);
//							routeOnlyTransition.appendChild(routeOnlyTransitionPositionx);
//							
//							//positiony
//							Element routeOnlyTransitionPositiony = doc.createElement("positiony");
//							Text routeOnlyTransitionPositiony_model = doc.createTextNode(""+(new Integer(routeOnlyTransitionSource.getLocation().y)).toString());
//							routeOnlyTransitionPositiony.appendChild(routeOnlyTransitionPositiony_model);
//							routeOnlyTransition.appendChild(routeOnlyTransitionPositiony);
//							
//							//desc
//							if(routeOnlyTransitionsWire.getDescription()!=null && routeOnlyTransitionsWire.getDescription().length()>0)
//							{
//								Element routeOnlyTransitionDesc = doc.createElement("desc");
//								Text routeOnlyTransitionDesc_model = doc.createTextNode(""+routeOnlyTransitionsWire.getDescription());
//								routeOnlyTransitionDesc.appendChild(routeOnlyTransitionDesc_model);
//								routeOnlyTransition.appendChild(routeOnlyTransitionDesc);
//							}
//							
//							//condition
//							if(routeOnlyTransitionsWire.getTransitionCondition()!=null && routeOnlyTransitionsWire.getTransitionCondition().length()>0)
//							{
//								Element routeOnlyTransitionCondition = doc.createElement("condition");
//								CDATASection routeOnlyTransitionCondition_model = doc.createCDATASection(""+routeOnlyTransitionsWire.getTransitionCondition());
//								routeOnlyTransitionCondition.appendChild(routeOnlyTransitionCondition_model);
//								routeOnlyTransition.appendChild(routeOnlyTransitionCondition);
//							}
//							
//							routeOnlyTransitions.appendChild(routeOnlyTransition);	
//							
//						}
//						routeOnly.appendChild(routeOnlyTransitions);
//					}
//					
//					root.appendChild(routeOnly);
//				}
				
				//end node
				if(node.getActivity_type_constant().equals( Constants.WF_ACTIVITY_TYPE_END)){
					Element end = doc.createElement("wfactivity");
					
//					nodeid
					Element endId = doc.createElement("id");
					Text endId_model = doc.createTextNode(""+node.getNodeId());
					endId.appendChild(endId_model);
					end.appendChild(endId);
					
//					name
					Element endName = doc.createElement("name");
					Text endName_model = doc.createTextNode(""+node.getName());
					endName.appendChild(endName_model);
					end.appendChild(endName);
					
					//desc
					if(node.getActivity_desc()!=null && node.getActivity_desc().length()>0)
					{
						Element endDesc = doc.createElement("desc");
						Text endDesc_model = doc.createTextNode(""+node.getActivity_desc());
						endDesc.appendChild(endDesc_model);
						end.appendChild(endDesc);
					}
					
					//type
					Element endType = doc.createElement("type");
					Text endType_model = doc.createTextNode(""+node.getActivity_type_constant());
					endType.appendChild(endType_model);
					end.appendChild(endType);
					
					//positionx
					Element endPositionx = doc.createElement("positionx");
					Text endPositionx_model = doc.createTextNode(""+(new Integer(node.getLocation().x)).toString());
					endPositionx.appendChild(endPositionx_model);
					end.appendChild(endPositionx);
					
					//positiony
					Element endPositiony = doc.createElement("positiony");
					Text endPositiony_model = doc.createTextNode(""+(new Integer(node.getLocation().y)).toString());
					endPositiony.appendChild(endPositiony_model);
					end.appendChild(endPositiony);	
					
					//jointype
					if(node.getActivity_join_type()!=null && node.getActivity_join_type().length()>0)
					{
						Element endJoinType = doc.createElement("jointype");
						Text endJoinType_model = doc.createTextNode(""+node.getActivity_join_type());
						endJoinType.appendChild(endJoinType_model);
						end.appendChild(endJoinType);
					}
					
//					Transitions
					if(((EndNode)node).getInputs()!=null && ((EndNode)node).getInputs().size()>0)
					{
						Element endTransitions = doc.createElement("transitions");
						
						Iterator itendTransitions = ((EndNode)node).getInputs().iterator();
						while(itendTransitions.hasNext()){
							Element endTransition = doc.createElement("transition");
							
							Wire endTransitionsWire = (Wire)itendTransitions.next();
							WorkflowSubPart endTransitionSource = endTransitionsWire.getSource();
							
//							id
							Element endTransitionId = doc.createElement("id");
							Text endTransitionId_model = doc.createTextNode(""+endTransitionsWire.getWireId());
							endTransitionId.appendChild(endTransitionId_model);
							endTransition.appendChild(endTransitionId);
							
							//fromactivity
							Element endTransitionFromActivity = doc.createElement("fromactivity");
							Text endTransitionFromActivity_model = doc.createTextNode(""+endTransitionSource.getNodeId());
							endTransitionFromActivity.appendChild(endTransitionFromActivity_model);
							endTransition.appendChild(endTransitionFromActivity);
							
							//positionx
							Element endTransitionPositionx = doc.createElement("positionx");
							Text endTransitionPositionx_model = doc.createTextNode(""+(new Integer(endTransitionSource.getLocation().x)).toString());
							endTransitionPositionx.appendChild(endTransitionPositionx_model);
							endTransition.appendChild(endTransitionPositionx);
							
							//positiony
							Element endTransitionPositiony = doc.createElement("positiony");
							Text endTransitionPositiony_model = doc.createTextNode(""+(new Integer(endTransitionSource.getLocation().y)).toString());
							endTransitionPositiony.appendChild(endTransitionPositiony_model);
							endTransition.appendChild(endTransitionPositiony);
							
							//desc
							if(endTransitionsWire.getDescription()!=null && endTransitionsWire.getDescription().length()>0)
							{
								Element endTransitionDesc = doc.createElement("desc");
								Text endTransitionDesc_model = doc.createTextNode(""+endTransitionsWire.getDescription());
								endTransitionDesc.appendChild(endTransitionDesc_model);
								endTransition.appendChild(endTransitionDesc);
							}
							
							//condition
							if(endTransitionsWire.getTransitionCondition()!=null && endTransitionsWire.getTransitionCondition().length()>0)
							{
								Element endTransitionCondition = doc.createElement("condition");
								CDATASection endTransitionCondition_model = doc.createCDATASection(""+endTransitionsWire.getTransitionCondition());
								endTransitionCondition.appendChild(endTransitionCondition_model);
								endTransition.appendChild(endTransitionCondition);
							}
							
							endTransitions.appendChild(endTransition);	
							
						}
						end.appendChild(endTransitions);
					}
					
					root.appendChild(end);
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
	
//	public static void main(String[] args){
//		  CEECProject myProject = new CEECProject();
//	        myProject.setDirectory("E:\\runtime-EclipseApplication\\rrr");
//	        myProject.setProjectName("rrr");
//	        myProject.setPackageName("gg");
//	        myProject.setGenPackageName("net.ms");
//	        myProject.setComponentName("Tr");
//	        myProject.setWorkflowName("workflowName");
//	        myProject.setWorkflowIname("workflowIname");
//	        WorkflowDiagram diagram = new WorkflowDiagram();
//	        StartNode node = new StartNode();
//	        node.setName("kfgfgkfgk");
//	        List child = new ArrayList();
//	        child.add(node);
//	        diagram.setChildren(child);
//		WriteToWorkflowXML write = new WriteToWorkflowXML(myProject, diagram);
//		try{
//			write.writeWorkflowXMLFile("E:\\workflow.xml");
//			//System.out.println("finished!");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}

}
