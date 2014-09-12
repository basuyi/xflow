package net.ms.designer.ui.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.UIPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class DataFactory extends MouseAdapter{

	public static TreeViewer tv;

	public static Object createTreeData(List project){

//		//System.out.println("project.getProject().size():"+projectList.size());
		if(project == null){
//			//System.out.println("project is null");
			return null;
			
		}
		ArrayList projectlist = new ArrayList();
		
//		ArrayList componentList = new ArrayList();
//		ArrayList wfList = new ArrayList();
		Iterator it = project.iterator();
		
		while(it.hasNext()){
//			//System.out.println("it.hasNext");
			IProject cp = (IProject)it.next();
			ProjectEntity pe = new ProjectEntity();
			pe.setName((cp.getProject()).getName());
			pe.setProjectName((cp.getProject()).getName());
//			pe.mouseDoubleClick(MouseEvent event);
			if(!cp.isOpen())
			{
				try 
				{
					cp.open(null);
				} 
				catch (CoreException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try 
			{
				ResourcesPlugin.getWorkspace().getRoot().getProject((cp.getProject()).getName()).getFolder(".configure").refreshLocal(IResource.DEPTH_INFINITE,null);
			} 
			catch (CoreException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(!ResourcesPlugin.getWorkspace().getRoot().getProject((cp.getProject()).getName()).getFolder(".configure").exists()||
					!ResourcesPlugin.getWorkspace().getRoot().getProject((cp.getProject()).getName()).getFolder(".configure").getFile("project.xml").exists()){
//				//System.out.println("file is not exist");
//				continue;
				
				
			}
			else if(ResourcesPlugin.getWorkspace().getRoot().getProject((cp.getProject()).getName()).getFolder(".configure").getFile("project.xml").exists()){				
//				//System.out.println("file exist");
				String filePath = ResourcesPlugin.getWorkspace().getRoot().getProject(pe.getName()).getFolder(".configure").getFile("project.xml").getLocation().toOSString();
//				//System.out.println("filePath:"+filePath);
				
				try{
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(filePath);
					document.normalize();
					if(document.getDocumentElement()==null){
						
					}
				
					
//					//System.out.println(richweb.getNodeType());
					if(document.getDocumentElement()!=null){
						Element richweb = document.getDocumentElement();
					
						if(richweb.getElementsByTagName("project")!=null||richweb.getElementsByTagName("project").getLength()>0){
							Element projectXML = (Element) richweb.getElementsByTagName("project").item(0);
							ArrayList packageList = new ArrayList();
							NodeList packageNodeList = projectXML.getElementsByTagName("package");
							if(packageNodeList == null||packageNodeList.getLength()<1){
		//						//System.out.println("have no package");
//								continue;
							}else{
		//						//System.out.println("packageNodeList.getLength()"+packageNodeList.getLength());
		//						PackageEntity packageEntity = new PackageEntity();
								for(int i = 0; i<packageNodeList.getLength(); i++){
									Element packageNode = (Element) packageNodeList.item(i);
									if(packageNode.getElementsByTagName("name").getLength()>0){
		//								//System.out.println("find package name");
										String packageName =  packageNode.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().toString();
		//								//System.out.println(packageName);
										PackageEntity packageEntity = new PackageEntity();
										packageEntity.setName(packageName);
										packageEntity.setProjectName(pe.getName());
										packageEntity.setPackageName(packageName);
										NodeList componentNodeList  = packageNode.getElementsByTagName("component");
										ArrayList componentList = new ArrayList();
										if(componentNodeList == null || componentNodeList.getLength()<1){
											
										}else{
		//									//System.out.println("componentNodeList:"+componentNodeList.getLength());
//											ArrayList componentList = new ArrayList();
											for(int j=0; j<componentNodeList.getLength(); j++){
												Element componentNode = (Element)componentNodeList.item(j);
												if(componentNode.getElementsByTagName("name").getLength()>0){
													String componentName = componentNode.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().toString();
													ComponentEntity componentEntity = new ComponentEntity();
													componentEntity.setName(componentName);
													componentEntity.setPackageName(packageEntity.getName());
													componentEntity.setProjectName(pe.getName());
													componentEntity.setComponentName(componentName);
													NodeList wfNodeList = componentNode.getElementsByTagName("workflow");
													
													if(wfNodeList == null||wfNodeList.getLength()<1){
														
													}else{
		//												//System.out.println("wfNodeList:"+wfNodeList.getLength());
														Element wfNode = (Element)wfNodeList.item(0);
														if(wfNode.getElementsByTagName("name").getLength()>0){
															String wfName = wfNode.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().toString();
														
															WorkflowEntity workflowEntity = new WorkflowEntity();
															workflowEntity.setName(wfName);
															workflowEntity.setProjectName(pe.getName());
															workflowEntity.setPackageName(packageEntity.getName());
															workflowEntity.setComponentName(componentEntity.getName());
															workflowEntity.setWorkflowName(wfName);
															
															//update by lcx 子流
															NodeList sfNodeList = wfNode.getElementsByTagName("subflow");
															if(sfNodeList == null || sfNodeList.getLength()<1)
															{
																
															}
															else
															{
																ArrayList subflowList = new ArrayList();
																for(int k = 0; k<sfNodeList.getLength(); k++)
																{
																	Element sfNode = (Element)sfNodeList.item(k);
																	if(sfNode.getElementsByTagName("name").getLength()>0)
																	{
																		String sfName = sfNode.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
																		SubflowEntity  subflowEntity = new SubflowEntity();
																		subflowEntity.setName(sfName);
																		subflowEntity.setProjectName(pe.getName());
																		subflowEntity.setPackageName(packageEntity.getName());
																		subflowEntity.setComponentName(componentEntity.getName());
																		subflowEntity.setWorkflowName(wfName);
																		subflowEntity.setSubflowName(sfName);
																		subflowList.add(subflowEntity);
																	}
																}
																workflowEntity.setChildren(subflowList);
															}
															
															
															//-----------------
															
															ArrayList wfList = new ArrayList();
															wfList.add(workflowEntity);
															componentEntity.setChildren(wfList);
														}														
													}
													componentList.add(componentEntity);												
												}	
											}
//											packageEntity.setChildren(componentList);
										}								
										
										NodeList enumNodeList  = packageNode.getElementsByTagName("enumeration");
										
										if(enumNodeList == null || enumNodeList.getLength()<1){
											
										}else{
//											ArrayList enumList = new ArrayList();
											for(int k=0;k<enumNodeList.getLength();k++){
												Element enumNode = (Element) enumNodeList.item(k);
												if(enumNode.getElementsByTagName("name").getLength()>0){
													String enumName = enumNode.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().toString();
													EnumEntity enumEntity = new EnumEntity();
													enumEntity.setName(enumName);
													enumEntity.setPackageName(packageEntity.getName());
													enumEntity.setProjectName(pe.getName());
													enumEntity.setComponentName(enumName);
													componentList.add(enumEntity);
												}	
											}	
										}
										packageEntity.setChildren(componentList);										
										packageList.add(packageEntity);										
									}
								}
		//						packageList.add(packageEntity);
								pe.setChildren(packageList);
		//						continue;
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				
//			continue;
			}
			projectlist.add(pe);
		}
		return projectlist;
		
		
	}
	
//	private static final class MyMouseDoubleClick extends MouseAdapter{
////		String packageEditorID = "net.ms.designer.editors.packages.ui.PackageEditor";
////		String workflowEditorID = "net.ms.designer.editors.workflow.ui.WorkflowEditor";
////		CEditorInput editorInput = new CEditorInput();
////		IWorkbenchPage workbenchpage = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
////		
//		public void mouseDoubleClick(MouseEvent e){
//			ITreeEntry obj = this.getSelTreeEntry();
//			String editorType = obj.getType();
//			String editorName;
//			String editorID;
//			try{
//				if(editorType.toLowerCase().equals(("PackageEntity").toLowerCase())){
//					editorName = "Package Editor";
//					editorID = packageEditorID;
//					this.openEditor(editorName,editorID);
//				}
//				
//				if(editorType.toLowerCase().equals(("PackageEntity").toLowerCase())){
//					editorName = "Workflow Editor";
//					editorID = workflowEditorID;
//					this.openEditor(editorName,editorID);
//				}
//			}catch(Exception ee){
//				ee.printStackTrace();
//			}
//	}
//	
//	
//	private void openEditor(String editorName,String editorID) throws PartInitException{
//		editorInput.setName(editorName);
//		IEditorPart editor = workbenchpage.findEditor(editorInput);
//		if(editor!=null)
//    	{
//    		workbenchpage.bringToTop(editor);
//    	}
//    	else
//    	{
//    		workbenchpage.openEditor(editorInput,editorID);
//    	}
//	}
//	
//	/**
//     * 自定义方法：取得当前选择的结点
//     */
//    private ITreeEntry getSelTreeEntry() {
//        IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
//        ITreeEntry entry = (ITreeEntry) (selection.getFirstElement());
//        return entry;
//    }
//    
//	}
	
}
