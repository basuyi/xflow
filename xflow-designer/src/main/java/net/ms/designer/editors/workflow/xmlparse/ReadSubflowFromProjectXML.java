package net.ms.designer.editors.workflow.xmlparse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.ms.designer.core.MsProject;
import net.ms.designer.editors.workflow.models.SubFlowActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class ReadSubflowFromProjectXML
{
	MsProject project;
	
	public ReadSubflowFromProjectXML(MsProject project)
	{
		this.project = project;
	}
	
	public List readSubflowFromProjectXML(String filePath) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(filePath);
		document.normalize();
		
		List subflowList = new ArrayList();
		
		if(document.getDocumentElement()==null)
		{
			return null;
		}
		
		if(document.getDocumentElement()!=null)
		{
			Element richweb = document.getDocumentElement();
			if(richweb.getElementsByTagName("project")!= null && richweb.getElementsByTagName("project").getLength()>0)
			{
				Element project0 = (Element)richweb.getElementsByTagName("project").item(0);
				
				if(project0.getElementsByTagName("package")!= null &&project0.getElementsByTagName("package").getLength()>0)	
				{
					NodeList packageList = project0.getElementsByTagName("package");
					
					for(int i = 0; i <packageList.getLength(); i++)
					{
						Element onePack = (Element) packageList.item(i);
						
						if(onePack.getElementsByTagName("name")!=null && onePack.getElementsByTagName("name").getLength()>0)
						{
							if(onePack.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(this.project.getPackageName()))
							{
								NodeList componentList = onePack.getElementsByTagName("component");
								if(componentList!= null)
								{
									for(int j = 0 ; j<componentList.getLength(); j++)
									{
										Element oneComp = (Element)componentList.item(j);
										if(oneComp.getElementsByTagName("name")!= null && oneComp.getElementsByTagName("name").getLength()>0)
										{
											if(oneComp.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(this.project.getComponentName()))
											{
												NodeList workflowList = oneComp.getElementsByTagName("workflow");
												if(workflowList!= null && workflowList.getLength()>0)
												{
													Element workflow = (Element)workflowList.item(0);
													if(workflow.getElementsByTagName("name")!= null && workflow.getElementsByTagName("name").getLength()>0)
													{
														if(workflow.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(this.project.getWorkflowName()))
														{
															NodeList subList = workflow.getElementsByTagName("subflow");
															if(subList!= null && subList.getLength()>0)
															{
																for(int k = 0 ; k<subList.getLength(); k++)
																{
																	Element subflow = (Element)subList.item(k);
																	SubFlowActivity subflowActivity = new SubFlowActivity();
																	
																	subflowActivity.setSubflowId(subflow.getElementsByTagName("id").item(0).getFirstChild().getNodeValue());
																	subflowActivity.setSubflowName(subflow.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
																	subflowActivity.setSubflowPath(subflow.getElementsByTagName("path").item(0).getFirstChild().getNodeValue());
																	subflowList.add(subflowActivity);
																}
																break;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return subflowList;
		
	}
}
