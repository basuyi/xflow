package net.ms.designer.editors.component.ui;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventObject;
import java.util.List;

import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.component.actions.PropertyAction;
import net.ms.designer.editors.component.dnd.DiagramTemplateTransferDropTargetListener;
import net.ms.designer.editors.component.editparts.PartFactory;
import net.ms.designer.editors.component.editparts.TreePartFactory;
import net.ms.designer.editors.component.models.BizComponent;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.EnumComponent;
import net.ms.designer.editors.component.tools.MyContextMenuProvider;
import net.ms.designer.editors.component.tools.PaletteFactory;
import net.ms.designer.editors.component.tools.WriteToProjectXML;
import net.ms.designer.ui.view.MsTreeView;
import net.ms.designer.ui.view.ITreeEntry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;


/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComponentEditor extends GraphicalEditorWithPalette implements ISelectionChangedListener 
{

    private CompDiagram diagram = new CompDiagram();

    private PaletteRoot paletteRoot;
    private MsProject project;
    private IOStreams stream ;
    private String filePath;
    private MsEditorInput input;


    public CompDiagram getDiagram() 
    {
        return this.diagram;
    }

    public ComponentEditor() 
    {
        setEditDomain(new DefaultEditDomain(this));
        stream = new IOStreams();
    }

    protected void configureGraphicalViewer() 
    {
        super.configureGraphicalViewer();
        getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
        getGraphicalViewer().setEditPartFactory(new PartFactory());
        ContextMenuProvider provider = new MyContextMenuProvider(getGraphicalViewer(), getActionRegistry());
        getGraphicalViewer().setContextMenu(provider);
        getGraphicalViewer().addSelectionChangedListener(this);
        getSite().registerContextMenu(provider, getGraphicalViewer());
        updateActions(getSelectionActions());
    }

    protected void initializeGraphicalViewer() 
    {
    	try
		{
	//    	get configure directory path
			String path = project.getConfigPath();
			StringBuffer sb = new StringBuffer(path);
			sb.append(project.getPackageName());
			sb.append(".ce");
			filePath = sb.toString();
			File file = new File(filePath);
			if(file.exists())
			{
				diagram = (CompDiagram)stream.inputs(filePath);
				if(diagram != null)
				{
					getGraphicalViewer().setContents(this.diagram);
				}
			}
			else
	    	{
	    		diagram  = new CompDiagram();
	            getGraphicalViewer().setContents(this.diagram);
	    	}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    getGraphicalViewer().addDropTargetListener(new DiagramTemplateTransferDropTargetListener(getGraphicalViewer()));
    }

    public void doSave(IProgressMonitor monitor) 
    {
    	WriteToProjectXML write = new WriteToProjectXML(project,diagram);
    	//get directory of the xml file to save 
    	String path = project.getConfigPath();
    	//get the xml file name
    	StringBuffer sb = new StringBuffer(path);
    	//get full file path
    	sb.append("project.xml");
    	try
        {
    		write.accessXMLFile(sb.toString());
    		stream.outputs(diagram,filePath);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		((MsTreeView)UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("net.ms.designer.ui.view.MsTreeView")).refresh();
		
//    	try {
//			Object tmp = PropertyUtils.getProperty(getEditorInput(),"treeView");
//			Method [] methods = tmp.getClass().getMethods();
//			for(int i = 0; i < methods.length;i++)
//			{
//				if(methods[i].getName().equals("refresh"))
//					methods[i].invoke(tmp , null);
//			}
//			
//		} 
//    	catch (IllegalAccessException e) 
//    	{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//    	catch (InvocationTargetException e) 
//    	{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//    	catch (NoSuchMethodException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
    	getCommandStack().markSaveLocation();
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

    public void setInput(IEditorInput input) 
    {
    	superSetInput(input);
    	project = ((net.ms.designer.core.MsEditorInput)getEditorInput()).getProject();
//    	try 
//    	{
//			Object tmpproject = PropertyUtils.getProperty(getEditorInput(),"project");
//			project=new net.ms.designer.core.MsProject();
//			
//			project.setDirectory((String )PropertyUtils.getProperty(tmpproject , "directory"));
//			project.setProjectName((String )PropertyUtils.getProperty(tmpproject , "projectName"));
//			project.setGenPackageName((String)PropertyUtils.getProperty(tmpproject, "genPackageName"));
//			project.setPackageName((String )PropertyUtils.getProperty(tmpproject,"packageName"));
//		} 
//    	catch (IllegalAccessException e)
//    	{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//    	catch (InvocationTargetException e) 
//    	{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//    	catch (NoSuchMethodException e)
//    	{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    

    public Object getAdapter(Class type) 
    {
        if (type == IContentOutlinePage.class)
            return new OutlinePage();
        return super.getAdapter(type);
    }

    public MsProject getProject()
    {
    	return this.project;
    }
    public void setProject(MsProject project)
    {
    	this.project = project;
    }
    
    PaletteFactory paletteFactory=new PaletteFactory();
    protected PaletteRoot getPaletteRoot() {
        if (this.paletteRoot == null) {
            this.paletteRoot = PaletteFactory.createPalette();
        }
        return this.paletteRoot;
    }

    protected void initializePaletteViewer() 
    {
        super.initializePaletteViewer();
        getPaletteViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getPaletteViewer()));
    }
    
    public void selectionChanged(SelectionChangedEvent event) 
    {
        updateActions(getSelectionActions());
    }
    
    public void createActions()
    {
    	super.createActions();
  	  	ActionRegistry registry = getActionRegistry();
  	  	IAction action;

  	  	action = new PropertyAction(this);
  	  	registry.registerAction(action);
  	  	getSelectionActions().add(action.getId());
    }

    class OutlinePage extends ContentOutlinePage 
    {
        //        private PageBook pageBook;

        private Control outline;

        public OutlinePage() 
        {
            super(new TreeViewer());
        }

        public void init(IPageSite pageSite) 
        {
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

        public void createControl(Composite parent) 
        {
            //            pageBook = new PageBook(parent, SWT.NONE);
            //            outline = getViewer().createControl(pageBook);
            //            pageBook.showPage(outline);
            outline = getViewer().createControl(parent);
            getSelectionSynchronizer().addViewer(getViewer());
            getViewer().setEditDomain(getEditDomain());
            getViewer().setEditPartFactory(new TreePartFactory());
            //            getViewer().setKeyHandler(getCommonKeyHandler());
            getViewer().setContents(getDiagram());
        }

        public Control getControl() 
        {
            //            return pageBook;
            return outline;
        }

        public void dispose() 
        {
            getSelectionSynchronizer().removeViewer(getViewer());
            super.dispose();
        }
    } 
    
    protected void superSetInput(IEditorInput input) 
    {
        super.setInput(input);
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
    
}

