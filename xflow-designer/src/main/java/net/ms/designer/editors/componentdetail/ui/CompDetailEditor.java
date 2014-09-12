package net.ms.designer.editors.componentdetail.ui;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.EventObject;
import java.util.List;

import net.ms.designer.core.MsProject;
import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.component.ui.ComponentEditor;
import net.ms.designer.editors.componentdetail.dnd.LogicTemplateTransferDropTargetListener;
import net.ms.designer.editors.componentdetail.dnd.TextTransferDropTargetListener;
import net.ms.designer.editors.componentdetail.editparts.GraphicalPartFactory;
import net.ms.designer.editors.componentdetail.editparts.TreePartFactory;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.tools.MyContextMenuProvider;
import net.ms.designer.editors.componentdetail.tools.PaletteFactory;
import net.ms.designer.editors.componentdetail.ui.PropertyUtils;
import net.ms.designer.editors.componentdetail.xmlpalse.WriteToComponentXML;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;




public class CompDetailEditor extends GraphicalEditorWithPalette implements ISelectionChangedListener{

	    private PaletteRoot paletteRoot;
	    
	    Container cot = new Container();
		IOStreams stream;
		String filePath;
		
		private MsProject project;
		
	    private boolean savePreviouslyNeeded = false;
	    
	    //--------------------jia
	    public Container getContainer()
	    {
	    	return this.cot;
	    }
	    public CompDetailEditor()
	    {
	    	//System.out.println("net.ms.designer.editors.componentdetail.ui.PackageEditor.PackageEditor()");
	    	this.setEditDomain(new DefaultEditDomain(this));
	    	stream = new IOStreams();
	    }
	    
	    protected void configureGraphicalViewer() {
	        super.configureGraphicalViewer();
	        GraphicalViewer viewer = this.getGraphicalViewer();
	        getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
	        getGraphicalViewer().setEditPartFactory(new GraphicalPartFactory());
	        ContextMenuProvider provider = new MyContextMenuProvider(viewer, getActionRegistry());
	        viewer.setContextMenu(provider);
	        viewer.addSelectionChangedListener(this);
	        getSite().registerContextMenu(provider, viewer);
	        updateActions(getSelectionActions());
	        //System.out.println("initcontextMenu");
	    }

	    protected void initializeGraphicalViewer() {
	    	
	    	
//	    	cot = new Container();
//	    	 generate the diagram
	    	try
	    	{
//	    		get configure directory path
	    		String path = project.getConfigPath();
	    		StringBuffer sb = new StringBuffer(path);
	    		sb.append(project.getComponentName());
	    		sb.append(".cde");
	    		filePath = sb.toString();
	    		File file = new File(filePath);
	    		
	    		if(file.exists() && file.length() != 0)
	    		{
	    			cot = (Container)stream.inputs(filePath);
	    			if(cot != null)
	    			{
	    				this.getGraphicalViewer().setContents(cot);
	    			}
	    		}
	    		else
	    		{
	    			cot = new Container();
	    			cot.setId(project.getComponentID());
	    			cot.setName(project.getComponentName());
	    	        this.getGraphicalViewer().setContents(cot);
	    		}
//	    		this.getGraphicalViewer().addDropTargetListener(new LogicTemplateTransferDropTargetListener(getGraphicalViewer()));
//		        this.getGraphicalViewer().addDropTargetListener(new TextTransferDropTargetListener(getGraphicalViewer(), TextTransfer.getInstance()));
	    		 getGraphicalViewer().addDropTargetListener(new LogicTemplateTransferDropTargetListener(getGraphicalViewer()));
	    	        getGraphicalViewer().addDropTargetListener(
	    	                new TextTransferDropTargetListener(getGraphicalViewer(), TextTransfer.getInstance()));
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    }

	    
	    // ------------保存
	    public void doSave(IProgressMonitor monitor) {
//	    	WriteToComponentXML write = new WriteToComponentXML(cot , project);
//	    	String path = project.getConfigPath();
//	    	StringBuffer sb = new StringBuffer(path);
//	    	StringBuffer sb1 = new StringBuffer(project.getProjectName());
//	    	sb1.append(".");
//	    	sb1.append(project.getPackageName());
//	    	sb1.append(".");
//	    	sb1.append(project.getComponentName());
//	    	sb1.append(".xml");
//	    	try{
//				write.writeXMLFile(sb.append(sb1.toString()).toString());
//				stream.outputs(cot,filePath);
//	    	}
//	    	catch(IOException ioe)
//	    	{
//	    		ioe.printStackTrace();
//	    	}
//	    	catch(Exception e)
//	    	{
//				e.printStackTrace();
//			}
//	    	
//	    	getCommandStack().markSaveLocation();
//	    	IEditorPart [] editors = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
//	    								.getActivePage().getEditors();
//	    	for(int i = 0; i<editors.length;i++)
//	    	{
//	    		if(editors[i].getTitle().equals("ComponentEditor"))
//	    		{
//	    			if(editors[i].getEditorInput().getName().equals(project.getComponentName()))
//	    			{
//	    				List nodes = ((ComponentEditor)editors[i]).getDiagram().getNodes();
//	    				for(int j = 0 ; j <= nodes.size(); j++)
//	    				{
//	    					
//	    				}
//	    			}
//	    		}
//	    	}
	    }

	    public void doSaveAs() {
	    }

	    
	    public void setInput(IEditorInput input)
	    {
	    	superSetInput(input);
	    	project = ((net.ms.designer.core.MsEditorInput)getEditorInput()).getProject();
//	    	try 
//	    	{
//				Object tmpproject = PropertyUtils.getProperty(getEditorInput(),"project");
//				project=new net.ms.designer.core.MsCProject();
//				
//				project.setDirectory((String )PropertyUtils.getProperty(tmpproject,"directory"));
//				project.setProjectName((String )PropertyUtils.getProperty(tmpproject,"projectName"));
//				project.setGenPackageName((String)PropertyUtils.getProperty(tmpproject, "genPackageName"));
//				project.setPackageName((String )PropertyUtils.getProperty(tmpproject,"packageName"));
//				project.setComponentName((String )PropertyUtils.getProperty(tmpproject,"componentName"));
//			} 
//	    	catch (IllegalAccessException e)
//	    	{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//	    	catch (InvocationTargetException e) 
//	    	{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//	    	catch (NoSuchMethodException e)
//	    	{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    }
	    
	    protected void superSetInput(IEditorInput input) 
	    {
	        super.setInput(input);
	    }
	    
	    
		public boolean isSaveAsAllowed() {
			// TODO 自动生成方法存根
			return false;
		}
		 public Object getAdapter(Class type) {
		        if (type == IContentOutlinePage.class)
		            return new OutlinePage();
		        return super.getAdapter(type);
		    }

		    PaletteFactory paletteFactory=new PaletteFactory();
		    protected PaletteRoot getPaletteRoot() {
		        if (this.paletteRoot == null) {
		            this.paletteRoot = paletteFactory.createPalette();
		        }
		        return this.paletteRoot;
		    }

		    protected void initializePaletteViewer() {
		        super.initializePaletteViewer();
		        //System.out.println("componentdetail.ui.PackageEditor.initializePaletteViewer()");
		        getPaletteViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getPaletteViewer()));
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

//		        public void createControl(Composite parent) {
//		            outline = getViewer().createControl(parent);
//		            getSelectionSynchronizer().addViewer(getViewer());
//		            getViewer().setEditDomain(getEditDomain());
//		            getViewer().setEditPartFactory(new TreePartFactory());
//		            getViewer().setContents(getContainer());
//		        }
//
//		        public Control getControl() {
//		            //            return pageBook;
//		            return outline;
//		        }

		        public void dispose() {
		            getSelectionSynchronizer().removeViewer(getViewer());
		            super.dispose();
		        }
		    }

			public void selectionChanged(SelectionChangedEvent event) {
				// TODO 自动生成方法存根
				updateActions(getSelectionActions());
			}

			protected void createActions() 
		    {
		    	  super.createActions();
		    	  ActionRegistry registry = getActionRegistry();
		    	  IAction action;

		    	  action = new net.ms.designer.editors.componentdetail.actions.PropertyAction(this);
		    	  registry.registerAction(action);
		    	  getSelectionActions().add(action.getId());
		    }
			
//			 protected void initializeOutlineViewer() {
//		            setContents(getContainer());
//		        }
//			  public void setContents(Object contents) {
//		            getViewer().setContents(contents);
//		        }
			 public boolean isDirty() 
			    {
			        return isSaveOnCloseNeeded();
			    }

			    public boolean isSaveOnCloseNeeded() 
			    {
			        return getCommandStack().isDirty();
			    }
			
			   public void commandStackChanged(EventObject event) {
			        if (isDirty()) {
			            if (!savePreviouslyNeeded()) {
			                setSavePreviouslyNeeded(true);
			                firePropertyChange(IEditorPart.PROP_DIRTY);
			            }
			        } else {
			            setSavePreviouslyNeeded(false);
			            firePropertyChange(IEditorPart.PROP_DIRTY);
			        }
			        super.commandStackChanged(event);
			    }
			   
			   private void setSavePreviouslyNeeded(boolean value) {
			        savePreviouslyNeeded = value;
			    }
			    
			    private boolean savePreviouslyNeeded() {
			        return savePreviouslyNeeded;
			    }
			    
			    
			    public MsProject getProject()
			    {
			    	return this.project;
			    }
			    public void setProject(MsProject project)
			    {
			    	this.project = project;
			    }
}
