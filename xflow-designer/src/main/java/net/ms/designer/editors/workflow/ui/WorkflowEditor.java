package net.ms.designer.editors.workflow.ui;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.actions.WorkflowPropertyAction;
import net.ms.designer.editors.workflow.dnd.WorkflowTemplateTransferDropTargetListener;
import net.ms.designer.editors.workflow.editparts.PartFactory;
import net.ms.designer.editors.workflow.editparts.TreePartFactory;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.tools.WorkflowContextMenuProvider;
import net.ms.designer.editors.workflow.tools.WorkflowPaletteFactory;
import net.ms.designer.editors.workflow.xmlparse.ReadFromApplicationXML;
import net.ms.designer.editors.workflow.xmlparse.ReadSubflowFromProjectXML;
import net.ms.designer.editors.workflow.xmlparse.WriteToWorkflowXML;
import net.ms.designer.ui.view.MsTreeView;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;




public class WorkflowEditor extends GraphicalEditorWithPalette  implements ISelectionChangedListener
{
	 private WorkflowDiagram diagram = null;
	 private WorkflowDiagram sfDiagram = null;
	 private MsProject project ;
	 private IOStreams stream ,compStream,sfStream;
	 private String filePath;
	 private Container container;
	 private List allAppList,allSubflowList;

	  private MsEditorInput input;
	 private PaletteRoot paletteRoot;
	 
	 public WorkflowDiagram getDiagram()
	 {	        
	        return this.diagram;
     }

	    public WorkflowEditor() 
	    {
	        setEditDomain(new DefaultEditDomain(this));
	        stream = new IOStreams();
	        compStream = new IOStreams();
	        sfStream = new IOStreams();
	    }

	    protected void configureGraphicalViewer() 
	    {
	        super.configureGraphicalViewer();
	        getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
	        getGraphicalViewer().setEditPartFactory(new PartFactory());
	        ContextMenuProvider provider = new WorkflowContextMenuProvider(getGraphicalViewer(), getActionRegistry());
	        getGraphicalViewer().setContextMenu(provider);
	        getGraphicalViewer().addSelectionChangedListener(this);
	        getSite().registerContextMenu(provider, getGraphicalViewer());
	        updateActions(getSelectionActions());
	    }
	    
	    public Object getAdapter(Class type) 
	    {
	        if (type == IContentOutlinePage.class)
	            return new OutlinePage();
	        return super.getAdapter(type);
	    }
	    
	protected PaletteRoot getPaletteRoot()
	{
		// TODO Auto-generated method stub
		if (this.paletteRoot == null) {
            this.paletteRoot = WorkflowPaletteFactory.createPalette();
        }
        return this.paletteRoot;
	}
	

	public void setInput(IEditorInput input) 
    {
    	superSetInput(input);
    	project = ((net.ms.designer.core.MsEditorInput)getEditorInput()).getProject();
    	this.allSubflowList = project.getSubflowList();
    	if(project.getApplicationList() == null || project.getApplicationList().size()<1)
    	{
    		allAppList = new ArrayList();
    		ReadFromApplicationXML read = new ReadFromApplicationXML();
    		try {
				allAppList = read.readFromApplicationXML(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"\\applications.xml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			project.setApplicationList(allAppList);
    	}
    	if(project.getSubflowList() == null || project.getSubflowList().size()<1)
    	{
    		ReadSubflowFromProjectXML read2 = new ReadSubflowFromProjectXML(project);
    		this.allSubflowList = new ArrayList();
    		try {
				allSubflowList = read2.readSubflowFromProjectXML(project.getConfigPath()+"\\project.xml");
				project.setSubflowList(allSubflowList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	container = (((net.ms.designer.core.MsEditorInput)getEditorInput())).getContainer();
    	this.input = (net.ms.designer.core.MsEditorInput)getEditorInput();
    	if(this.input.getIsSubflow() == false)
    	{
    		this.diagram = new WorkflowDiagram();
    		this.diagram.setIsSubflow(this.input.getIsSubflow());
    	}
    	else
    	{
    		this.sfDiagram = new WorkflowDiagram();
    		this.sfDiagram.setIsSubflow(this.input.getIsSubflow());
    	}
//    	this.diagram.setProject(project);
//    	this.diagram.setContainer(container);
//        this.diagram.setWfName(project.getWorkflowName());
//        this.diagram.setWfDesc("");
//        this.diagram.setWorkflowId(project.getWorkflowId());
//        this.diagram.setStatus(Constants.DRAFT);
    }
	  protected void superSetInput(IEditorInput input) 
	    {
	        super.setInput(input);
	    } 
	protected void initializeGraphicalViewer() {
		try
		{
			input.setName(project.getWorkflowName());
	//			this.setPartName(input.getName());
	//    		get configure directory path
	    	String path = project.getConfigPath();
	    	StringBuffer sb = new StringBuffer(path);
	    	sb.append(project.getWorkflowName());
	    	sb.append(".wf");
	    	filePath = sb.toString();
	    	File file = new File(filePath);
	    	StringBuffer comp = new StringBuffer(path);
	    	comp.append(project.getComponentName());
	    	comp.append(".cde");
	    	String compPath = comp.toString();
	    	File compFile = new File(compPath);
	    	if(file.exists() && compFile.exists())  //存在工作流，打开已有的
	    	{
	    		diagram = (WorkflowDiagram)stream.inputs(filePath);
	    		container = (Container)compStream.inputs(compPath);
	    		if(this.input.getIsSubflow() == false)  //打开的是主流
	    		{
		    		this.project = diagram.getProject();
		    		if(this.project.getApplicationList() == null || this.project.getApplicationList().size()<1)
		    		{
			    		this.project.setApplicationList(this.allAppList);
			    		diagram.setProject(this.project);
		    		}
		    		if(this.project.getSubflowList() == null || this.project.getSubflowList().size()<1)
		    		{
			    		this.project.setSubflowList(this.allSubflowList);
			    		diagram.setProject(this.project);
		    		}
	    		
	    			if(diagram != null)
	    			{
	    				getGraphicalViewer().setContents(this.diagram);
	    			}
	    		}
	    		else   //打开的是子流
	        	{
	    			StringBuffer subflowBuffer = new StringBuffer(path);
	    			subflowBuffer.append(this.project.getSubflowId());
	    			subflowBuffer.append(".sf");
	    			File subflowFile = new File(subflowBuffer.toString());
	    			if(subflowFile.exists())  //打开已有子流
	    			{
	    				sfDiagram = (WorkflowDiagram)sfStream.inputs(subflowBuffer.toString());
	    				this.project = sfDiagram.getProject();
	    				if(sfDiagram != null)
	    				{
	    					getGraphicalViewer().setContents(this.sfDiagram);
	    				}
	    			}
	    			else  //新建子流
	    			{
	    				
	    			}
//	    			diagram = new WorkflowDiagram();
//	    			
//	    			List paraList = new ArrayList();
//					ParameterEntire bean = new ParameterEntire();
//					bean.setParaName("bean");
//					bean.setParaType(Constants.WF_PARA_TYPE_BUSINESS_BEAN);
//					ParameterEntire entityid = new ParameterEntire();
//					entityid.setParaName("entityid");
//					entityid.setParaType(Constants.WF_PARA_TYPE_INT);
//					ParameterEntire infor = new ParameterEntire();
//					infor.setParaName("infor");
//					infor.setParaType(Constants.WF_PARA_TYPE_STRING);
//					paraList.add(bean);
//					paraList.add(entityid);
//					paraList.add(infor);
//					diagram.setParaList(paraList);
//					
//	    			diagram.setProject(this.project);
//	    			diagram.setWfId(this.project.getWorkflowId());
//	    			diagram.setIsSubflow(false);
//	    			
//	                getGraphicalViewer().setContents(this.diagram);
	        	}
			}
			else  //创建新的工作流
			{
				diagram = new WorkflowDiagram();
    			
//    			List paraList = new ArrayList();
//				ParameterEntire bean = new ParameterEntire();
//				bean.setParaName("bean");
//				bean.setParaType(Constants.WF_PARA_TYPE_BUSINESS_BEAN);
//				ParameterEntire entityid = new ParameterEntire();
//				entityid.setParaName("entityid");
//				entityid.setParaType(Constants.WF_PARA_TYPE_INT);
//				ParameterEntire infor = new ParameterEntire();
//				infor.setParaName("infor");
//				infor.setParaType(Constants.WF_PARA_TYPE_STRING);
//				paraList.add(bean);
//				paraList.add(entityid);
//				paraList.add(infor);
//				diagram.setParaList(paraList);
				
    			diagram.setProject(this.project);
    			diagram.setWfId(this.project.getWorkflowId());
    			diagram.setIsSubflow(false);
    			
                getGraphicalViewer().setContents(this.diagram);
			}
//				String subflowName = null;
//				SubFlowActivity subflow = null;
//				//			this.setPartName(input.getName());
//				String path = project.getConfigPath();
//				
//	    		StringBuffer comp = new StringBuffer(path);
//	    		comp.append(project.getComponentName());
//	    		comp.append(".cde");
//	    		StringBuffer wf = new StringBuffer(path);
//	    		wf.append(project.getWorkflowName());
//	    		wf.append(".wf");
//	    		File wfFile = new File(wf.toString());
//	    		String compPath = comp.toString();
//	    		File compFile = new File(compPath);
//	    		if(compFile.exists() && wfFile.exists())
//	    		{    			
//	    			StringBuffer sb = new StringBuffer(path);
//					sb.append(project.getSubflowId());
//		    		sb.append(".sf");
//		    		filePath = sb.toString();
//		    		File file = new File(filePath);
//		    		
//	    			if(file.exists())
//	    			{
//	    			
//		    			sfDiagram = (WorkflowDiagram)sfStream.inputs(filePath);
////		    			this.project.setSubflowList(this.allSubflowList);
////		    			sfDiagram.setProject(this.project);
//	    			
//		    			diagram = (WorkflowDiagram)stream.inputs(filePath);
//		    			container = (Container)compStream.inputs(compPath);
//		    			
//		    			this.project.setSubflowId(diagram.getProject().getSubflowId());
//		    			this.project.setSubflowList(diagram.getProject().getSubflowList());
//		    			this.project.setSubflowName(diagram.getProject().getSubflowName());
//		    			sfDiagram.setProject(this.project);
//		    			
//		    			if(project.getSubflowList() != null && project.getSubflowList().size()>0)
//						{
//							for(int j = 0; j<project.getSubflowList().size();j++)
//							{
//								if(((SubFlowActivity)project.getSubflowList().get(j)).getSubflowId().equals(project.getSubflowId()))
//								{
//									subflow = ((SubFlowActivity)project.getSubflowList().get(j));
//								}
//							}
//						}
//						input.setName(project.getSubflowName());
//	    			
//		    			if(sfDiagram != null)
//		    			{
//		    				getGraphicalViewer().setContents(this.sfDiagram);
//		    			}
//			    	}
//			    	else
//			        {				    			
//			    		sfDiagram = new WorkflowDiagram();
//			    			
//			    		List paraList = new ArrayList();
//						ParameterEntire bean = new ParameterEntire();
//						bean.setParaName("bean");
//						bean.setParaType(Constants.WF_PARA_TYPE_BUSINESS_BEAN);
//						ParameterEntire entityid = new ParameterEntire();
//						entityid.setParaName("entityid");
//						entityid.setParaType(Constants.WF_PARA_TYPE_INT);
//						ParameterEntire infor = new ParameterEntire();
//						infor.setParaName("infor");
//						infor.setParaType(Constants.WF_PARA_TYPE_STRING);
//						paraList.add(bean);
//						paraList.add(entityid);
//						paraList.add(infor);
//						sfDiagram.setParaList(paraList);
//												
//						sfDiagram.setProject(this.project);
//						sfDiagram.setWfDesc(""+subflow.getActivity_desc());
//						sfDiagram.setWfName(subflow.getName());
//						sfDiagram.setIsSubflow(true);
//						sfDiagram.setWfId(this.project.getSubflowId());
//			    			
//			    			
//			    		subflow.setSubflowDiagram(sfDiagram);
//			    			
//			            getGraphicalViewer().setContents(this.sfDiagram);
//			        }
//	        	}
//			}
		}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        getGraphicalViewer().addDropTargetListener(new WorkflowTemplateTransferDropTargetListener(getGraphicalViewer()));
      
	}

	public void doSave(IProgressMonitor monitor) 
	{
		// TODO Auto-generated method stub
		List children;
		if(this.input.getIsSubflow() == false)
		{
			children = diagram.getChildren();
		}
		else
		{
			children = sfDiagram.getChildren();
		}
		for(int i = 0; i<children.size(); i++)
		{
			WorkflowBaseActivity node = (WorkflowBaseActivity)children.get(i);
			for(int j = i+1; j<children.size(); j++)
			{
				if(((WorkflowBaseActivity)children.get(j)).getName().equals(node.getName()))
				{
					MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),"结点中重名","结点中有重名");
					return;
				}
			}
		}
		try
    	{
			if(this.input.getIsSubflow() == false)
			{
				String path0 = project.getConfigPath();
	    		StringBuffer sb0 = new StringBuffer(path0);
	//    		sb.append(project.getProjectName());
	//    		sb.append("\\configure");
	    		sb0.append(project.getWorkflowName());
	    		sb0.append(".wf");
	    		filePath = sb0.toString();
	    		stream.outputs(diagram,filePath);
	//    		project = diagram.getProject();
	    		WriteToWorkflowXML writer = new WriteToWorkflowXML(project, diagram);
	//    		get directory of the xml file to save 
	        	String path = project.getConfigPath();
	        	//get the xml file name
	        	StringBuffer sb = new StringBuffer(path);
	        	//get full file path
	        	sb.append("project.xml");
	    		writer.writeProjectXMLFile(sb.toString());
	    		StringBuffer sb1 = new StringBuffer(path);
	    		sb1.append(project.getProjectName()+"."+project.getPackageName()+"."+project.getComponentName()+"."+project.getWorkflowId());
	    		sb1.append(".xml");
	    		writer.writeWorkflowXMLFile(sb1.toString());
			}
			else
			{
				Iterator itSub = project.getSubflowList().iterator();
				List partSubflowList = new ArrayList();
	    		while(itSub.hasNext())
				{
					SubFlowActivity sub = (SubFlowActivity)itSub.next();
					if(!sub.getSubflowId().equals(project.getSubflowId()))
					{
						partSubflowList.add(sub);
					}
				}
	    		if(partSubflowList != null && partSubflowList.size()>0)
	    		{
	    			for(int i = 0; i<partSubflowList.size(); i++)
	    			{
	    				if(((SubFlowActivity)partSubflowList.get(i)).getName().equals(project.getSubflowName()))
						{	
							MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
			        					"流程名称重复",
			        					"流程名称重复");
							return;
						}
	    			}
	    		}
	    		
				String path0 = project.getConfigPath();
	    		StringBuffer sb0 = new StringBuffer(path0);
	//    		sb.append(project.getProjectName());
	//    		sb.append("\\configure");
	    		sb0.append(project.getSubflowId());
	    		sb0.append(".sf");
	    		filePath = sb0.toString();
	    		stream.outputs(sfDiagram,filePath);
	//    		project = diagram.getProject();
	    		WriteToWorkflowXML writer = new WriteToWorkflowXML(project, sfDiagram);
	//    		get directory of the xml file to save 
	        	String path = project.getConfigPath();
	        	//get the xml file name
	        	StringBuffer sb = new StringBuffer(path);
	        	//get full file path
	        	sb.append("project.xml");
	    		writer.writeProjectXMLFile(sb.toString());
	    		StringBuffer sb1 = new StringBuffer(path);
	    		sb1.append(project.getProjectName()+"."+project.getPackageName()+"."+project.getComponentName()+"."+project.getSubflowId());
	    		sb1.append(".xml");
	    		writer.writeWorkflowXMLFile(sb1.toString());
			}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
   	
    	((MsTreeView)UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
    	    	.getActivePage().findView("net.ms.designer.ui.view.MsTreeView")).refresh();
    	    	
    	getCommandStack().markSaveLocation();
	}

	 public void commandStackChanged(EventObject event) 
	 {
	        if (isDirty()) 
	        {
	                firePropertyChange(IEditorPart.PROP_DIRTY);
	        } 
	        else 
	        {
	            firePropertyChange(IEditorPart.PROP_DIRTY);
	        }
	        super.commandStackChanged(event);
	    }
	    
	 public void doSaveAs() 
	    {
	    }

	 public boolean isDirty() 
	    {
	        return getCommandStack().isDirty();
	    }

	    public boolean isSaveAsAllowed() 
	    {
	        return true;
	    }
	
	protected void initializePaletteViewer() {
        super.initializePaletteViewer();
        getPaletteViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getPaletteViewer()));
    }
	
	 protected void closeEditor(boolean save) {
			getSite().getPage().closeEditor(WorkflowEditor.this, save);
		}
	 
	   public void createActions()
	    {
	    	super.createActions();
	  	  	ActionRegistry registry = getActionRegistry();
	  	  	IAction action;

	  	  	action = new WorkflowPropertyAction(this);
	  	  	registry.registerAction(action);
	  	  	getSelectionActions().add(action.getId());
	    }

	 class OutlinePage extends ContentOutlinePage {
	        //        private PageBook pageBook;

	        private Control outline;

	        public OutlinePage() {
	            super(new TreeViewer());
	        }

	        public void init(IPageSite pageSite) {
	            super.init(pageSite);
	            ActionRegistry registry = getActionRegistry();
	            IActionBars bars = pageSite.getActionBars();
	            String id = IWorkbenchActionConstants.UNDO;
	            bars.setGlobalActionHandler(id, registry.getAction(id));
	            id = IWorkbenchActionConstants.REDO;
	            bars.setGlobalActionHandler(id, registry.getAction(id));
	            id = IWorkbenchActionConstants.DELETE;
	            bars.setGlobalActionHandler(id, registry.getAction(id));
	            bars.updateActionBars();
	        }

	        public void createControl(Composite parent) {
	            //            pageBook = new PageBook(parent, SWT.NONE);
	            //            outline = getViewer().createControl(pageBook);
	            //            pageBook.showPage(outline);
	            outline = getViewer().createControl(parent);
	            getSelectionSynchronizer().addViewer(getViewer());
	            getViewer().setEditDomain(getEditDomain());
	            getViewer().setEditPartFactory(new TreePartFactory());
	            //            getViewer().setKeyHandler(getCommonKeyHandler());
	            getViewer().setContents(getDiagram());
//	            //System.out.println("diagramOutline:"+getDiagram().getChildren().size());
	        }

	        public Control getControl() {
	            //            return pageBook;
	            return outline;
	        }

	        public void dispose() {
	            getSelectionSynchronizer().removeViewer(getViewer());
	            super.dispose();
	        }
	    }

	public void selectionChanged(SelectionChangedEvent event) 
	{
		// TODO Auto-generated method stub
		updateActions(getSelectionActions());
	}

	 public MsProject getProject()
	    {
	    	return this.project;
	    }
	    public void setProject(MsProject project)
	    {
	    	this.project = project;
	    }
	
	 public void setContainer(Container container)
	 {
		 this.container = container;
	 }
	 
	 public Container getContainer()
	 {
		 return this.container;
	 }
	

}
