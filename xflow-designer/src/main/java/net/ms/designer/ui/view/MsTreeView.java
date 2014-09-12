package net.ms.designer.ui.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.DBTool;
import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class MsTreeView extends ViewPart
{
	
	private MsEditorInput editorInput = new MsEditorInput();
	private MsContext context;
	private List projectList;
	public static TreeViewer tv;
//	private List editorList = new ArrayList();
	// 2006/10/08 update by mashuai
	private MsProject project;

//	static IWorkbenchPage workbenchpage = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
	static String packageEditorID = "net.ms.designer.editors.packages.ui.PackageEditor";
	static String workflowEditorID = "net.ms.designer.editors.workflow.ui.WorkflowEditor";
	static String componentEditorID = "net.ms.designer.editors.component.ui.ComponentEditor";
	static String enumDetailEditorID = "net.ms.designer.editors.enumcomponentdetail.ui.EnumDetailEditor";
	static String componentDetailEditorID = "net.ms.designer.editors.componentdetail.ui.CompDetailEditor";
	String viewID = "net.ms.designer.ui.view.MsTreeView";

	public HashMap projectAndEditorInput = new HashMap();
	
//	 2006/10/08 update by mashuai
	public MsProject getProject()
	{
		return this.project;
	}
	public void setProject(MsProject project)
	{
		this.project = project;
	}
	
	public List getProjectList()
	{
		this.setProjectList();
		return this.projectList;
	}
	
	public void setProjectList()
	{
		IProject[] project =  ResourcesPlugin.getWorkspace().getRoot().getProjects();
		projectList = new ArrayList();
		for(int i =0 ; i<project.length ; i++)
		{
			projectList.add(project[i]);
		}
	}

	public Composite openShell(Composite parent)
	{
        Composite c = new Composite(parent, SWT.NONE);
        c.setLayout(new FillLayout());
        tv = new TreeViewer(c, SWT.BORDER);
        tv.setContentProvider(new TreeViewerContentProvider());
        tv.setLabelProvider(new TreeViewerLableProvider());
        //和TableViewer一样，数据的入口也是setInput方法
        this.setProjectList();
        this.getProjectList();
        if(this.projectList == null){
        	//System.out.println("null");
        }
        //System.out.println("not null");
        DataFactory.tv = this.tv;
        Object inputObj = DataFactory.createTreeData(this.projectList);
        tv.setInput(inputObj);
        
        return c;
	}
	
	/**
     * 自定义方法：取得当前选择的结点
     */
    public  ITreeEntry getSelTreeEntry() 
    {
        IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
        ITreeEntry entry = (ITreeEntry) (selection.getFirstElement());
        return entry;
    }

	public void createPartControl(Composite parent) 
	{
		this.openShell(parent);
		
		tv.addDoubleClickListener(new IDoubleClickListener() 
		{

			public void doubleClick(DoubleClickEvent event) 
			{
				ITreeEntry obj = getSelTreeEntry();
				String editorType = obj.getType();
				String projectName = obj.getProjectName();
				String editorID = null;
			
				IWorkbenchPage page = getViewSite().getPage();
				try
				{
					if(editorType.toLowerCase().equals(("package").toLowerCase()))
					{
						// if project obj is null,the the project is exist;
						if(project == null)
						{
							project = new MsProject();
							project.setProjectName(projectName);
							project.setDbtool(this.getDBTool(projectName));
							project.setDirectory(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toOSString());
							project.setGenPackageName(this.getGenPackageName(projectName));
						}	
						
					}
					if(editorType.toLowerCase().equals(("component").toLowerCase()))
					{
						PackageEntity packageEntity = (PackageEntity) obj;
//						if(project == null){
							if(project == null)
							{
								project = new MsProject();
							}
							project.setProjectName(projectName);
							project.setPackageName(packageEntity.getName());
							project.setDbtool(this.getDBTool(projectName));
							project.setDirectory(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toOSString());
							project.setGenPackageName(this.getGenPackageName(projectName));
//						}	
						
						
					}
					if(editorType.toLowerCase().equals(("componentdetail").toLowerCase()))
					{
						ComponentEntity componentEntity = (ComponentEntity) obj;

						if(project == null)
						{
							project = new MsProject();
						}
						project.setProjectName(projectName);
						project.setPackageName(componentEntity.getPackageName());
						project.setComponentName(componentEntity.getName());
						project.setDbtool(this.getDBTool(projectName));
						project.setDirectory(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toOSString());
						project.setGenPackageName(this.getGenPackageName(projectName));
						
						
					}
					if(editorType.toLowerCase().equals(("enumdetail").toLowerCase()))
					{
						EnumEntity enumEntity = (EnumEntity) obj;

						if(project == null)
						{
							project = new MsProject();
						}
						project.setProjectName(projectName);
						project.setPackageName(enumEntity.getPackageName());
						project.setComponentName(enumEntity.getName());
						project.setDbtool(this.getDBTool(projectName));
						project.setDirectory(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toOSString());
						project.setGenPackageName(this.getGenPackageName(projectName));
						
					}
					if(editorType.toLowerCase().equals(("workflow").toLowerCase()))
					{
						WorkflowEntity workflowEntity = (WorkflowEntity) obj;
						if(project == null){
							project = new MsProject();
							project.setProjectName(projectName);
							project.setPackageName(workflowEntity.getPackageName());
							project.setComponentName(workflowEntity.getComponentName());
							project.setWorkflowName(workflowEntity.getWorkflowName());
							project.setDbtool(this.getDBTool(projectName));
							project.setDirectory(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toOSString());
							project.setGenPackageName(this.getGenPackageName(projectName));
						}
					}
					
					project.setName(projectName);
					project.setType(obj.getType());
					
					PackageEditor editor = (PackageEditor)UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().getActiveEditor();
					
//					IEditorPart [] editors = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
//					.getActivePage().getEditors();
					
//					if(editors.length != 0)
//					{
//						PackageEditor editor = null;
//						for(int i = 0 ; i < editors.length ; i++)
//						{
//							if(editors[i].getEditorInput().getName().equals(obj.getName()))
//							{
//								editor = (PackageEditor)editors[i];
//							}
//						}
						if(editor!=null)
						{
							MsContext context = editor.getContext();
							MsElement element = context.getElement(obj.getType() + "_" + obj.getName());
							if(element == null)
							{
								element = new MsElement();
								element.setNodeName(editor.getNodeName());
								element.setNodeType(editor.getNodeType());
								element.setContainer(editor.getViewer().getContents().getModel());
								context.addElement(element);
							}
//							editor.setCurrentElement(element);
//							if(element.getParent() != null)
//				        	{
//								element.getParent().addChild(element);
//				        	}
							context.changeContext(obj.getType() , obj.getName());
							page.bringToTop(editor);
						}
//					}
					else
					{
						MsEditorInput input = new MsEditorInput();	
			    		input.setName(obj.getName());
						input.setTreeView((MsTreeView)(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage()).findView(viewID));
						
						input.setEditorName(obj.getName());
						input.setEditorType(obj.getType());
						
//						 2006/10/08 update by mashuai					
						input.setProject(project);
						IOStreams stream = new IOStreams();
						String contextPath = project.getConfigPath() + "context";
						File file = new File(contextPath);
						if(file.exists() && file.length() != 0)
						{
							try 
							{
								context = (MsContext)stream.inputs(contextPath);
							} 
							catch (IOException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							catch (ClassNotFoundException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						input.setContext(context);
						page.openEditor(input,"net.ms.designer.editors.packages.ui.PackageEditor");
					}
					
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
				}
			}
		
		
		private String getGenPackageName(String projectName)
		{

				String filePath = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getFolder(".configure").getFile("project.xml").getLocation().toOSString();
				try
				{
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(filePath);
					document.normalize();
					if(document.getDocumentElement()==null)
					{
						return null;
					}			
					Element genPackageName = (Element)document.getElementsByTagName("packagename").item(0);
					String result = genPackageName.getFirstChild().getNodeValue();
					return result;
//					if(document.getDocumentElement()!=null)
//					{
//						Element richweb = document.getDocumentElement();
//						
//						if(richweb.getElementsByTagName("project")!=null||richweb.getElementsByTagName("project").getLength()>0)
//						{
//							Element projectXML = (Element) richweb.getElementsByTagName("project").item(0);
//							NodeList companyList = projectXML.getElementsByTagName("company");
//							if(companyList == null||companyList.getLength()<1)
//							{
//								return null;
//							}
//							else
//							{
//								String company = companyList.item(0).getFirstChild().getNodeValue().toString();
//								String genPackageName = "com."+company;
//								return genPackageName;
//							}
//						}
//					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			return null;
		}
		
		private DBTool getDBTool(String projectName){

				String filePath = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getFolder(".configure").getFile("project.xml").getLocation().toOSString();
				try{
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(filePath);
					document.normalize();
					if(document.getDocumentElement()==null){
						return null;
					}					
					if(document.getDocumentElement()!=null){
						Element richweb = document.getDocumentElement();
						
						if(richweb.getElementsByTagName("project")!=null||richweb.getElementsByTagName("project").getLength()>0){
							Element projectXML = (Element) richweb.getElementsByTagName("project").item(0);
							NodeList DataSourceList = projectXML.getElementsByTagName("DataSource");
							if(DataSourceList == null||DataSourceList.getLength()<1){
								return null;
							}
							else{
								DBTool tool = new DBTool();
								Element dataSource = (Element) DataSourceList.item(0);
								if(dataSource.getElementsByTagName("DataSourceName") != null 
										|| dataSource.getElementsByTagName("DataSourceName").getLength()>0)
									tool.setConName(dataSource.getElementsByTagName("DataSourceName").item(0).getFirstChild().getNodeValue());
								if(dataSource.getElementsByTagName("username") != null 
										|| dataSource.getElementsByTagName("username").getLength()>0)
									tool.setUsername(dataSource.getElementsByTagName("username").item(0).getFirstChild().getNodeValue());
								if(dataSource.getElementsByTagName("password") != null 
										|| dataSource.getElementsByTagName("password").getLength()>0)
									tool.setPassword(dataSource.getElementsByTagName("password").item(0).getFirstChild().getNodeValue());
								if(dataSource.getElementsByTagName("type") != null 
										|| dataSource.getElementsByTagName("type").getLength()>0)
									tool.setDbType(dataSource.getElementsByTagName("type").item(0).getFirstChild().getNodeValue());
								if(dataSource.getElementsByTagName("database") != null 
										|| dataSource.getElementsByTagName("database").getLength()>0)
									tool.setDbName(dataSource.getElementsByTagName("database").item(0).getFirstChild().getNodeValue());
								if(dataSource.getElementsByTagName("port") != null 
										|| dataSource.getElementsByTagName("port").getLength()>0)
									tool.setPort(dataSource.getElementsByTagName("port").item(0).getFirstChild().getNodeValue());
								if(dataSource.getElementsByTagName("server") != null 
										|| dataSource.getElementsByTagName("server").getLength()>0)
									tool.setServer(dataSource.getElementsByTagName("server").item(0).getFirstChild().getNodeValue());
//								if(dataSource.getElementsByTagName("maxConnections") != null 
//										|| dataSource.getElementsByTagName("maxConnections").getLength()>0)
								return tool;
							}
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			return null;	
		
		}		
        });
	}

	public void setFocus() 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void refresh()
	{
		tv.setContentProvider(new TreeViewerContentProvider());
        tv.setLabelProvider(new TreeViewerLableProvider());
		this.setProjectList();
	    this.getProjectList();
	    DataFactory.tv = this.tv;
	    Object inputObj = DataFactory.createTreeData(this.projectList);
	    tv.setInput(inputObj);
		tv.refresh();
	}
	
	public Document getDocument(String projectName)
	{
		String filePath = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getFolder("configure").getFile("project.xml").getLocation().toOSString();
		Document document = null;
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(filePath);
			document.normalize();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return document;
	}

}
