package net.ms.designer.editors.enumcomponentdetail.ui;

import java.io.IOException;
import java.util.EventObject;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.ms.designer.core.MsProject;
import net.ms.designer.core.IOStreams;
import net.ms.designer.editors.enumcomponentdetail.action.PropertyAction;
import net.ms.designer.editors.enumcomponentdetail.dnd.LogicTemplateTransferDropTargetListener;
import net.ms.designer.editors.enumcomponentdetail.dnd.TextTransferDropTargetListener;
import net.ms.designer.editors.enumcomponentdetail.editpart.PartFactory;
import net.ms.designer.editors.enumcomponentdetail.model.Container;
import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.palette.PaletteFactory;
import net.ms.designer.editors.enumcomponentdetail.tools.MyContextMenuProvider;
import net.ms.designer.editors.enumcomponentdetail.tools.WriteToEnumXML;
import net.ms.designer.ui.view.MsTreeView;
import net.ms.designer.ui.view.ITreeEntry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.UIPlugin;


public class EnumDetailEditor extends GraphicalEditorWithPalette implements ISelectionChangedListener 
{
	private boolean savePreviouslyNeeded = false;
	Container diagram;
	IOStreams stream;
	MsProject project;
	private String filePath;
	
	public MsProject getProject()
	{
		return this.project;
	}
	public void setProject(MsProject project)
	{
		this.project = project;
	}
	
    public EnumDetailEditor()
    {
        this.setEditDomain(new DefaultEditDomain(this));
        stream = new IOStreams();
    }

    protected PaletteRoot getPaletteRoot() 
    {
        return PaletteFactory.INSTANCE().createPaletteRoot();
    }

    protected void initializePaletteViewer() 
    {
        super.initializePaletteViewer();
        getPaletteViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getPaletteViewer()));
    }
    
    protected void initializeGraphicalViewer()
    {
        // generate the diagram
    	try
    	{
//    		get configure directory path
    		String path = project.getConfigPath();
    		StringBuffer sb = new StringBuffer(path);
    		sb.append(project.getComponentName());
    		sb.append(".ede");
    		filePath = sb.toString();
    		File file = new File(filePath);
    		if(file.exists() && file.length() != 0)
    		{
    			diagram = (Container)stream.inputs(file.getAbsoluteFile().toString());
    			if(diagram != null)
    			{
    				this.getGraphicalViewer().setContents(diagram);
    			}
    		}
    		else
    		{
    			diagram = new Container();
    	        Table table = new Table();
    	        Point p = new Point(88,88);
    	        table.setTableName(project.getComponentName());
    	        table.setLocation(p);
    	        diagram.addChild(table);
    	        this.getGraphicalViewer().setContents(diagram);
    		}
    		this.getGraphicalViewer().addDropTargetListener(new LogicTemplateTransferDropTargetListener(getGraphicalViewer()));
	        this.getGraphicalViewer().addDropTargetListener(new TextTransferDropTargetListener(getGraphicalViewer(), TextTransfer.getInstance()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        
    }

    protected void configureGraphicalViewer() 
    {
        super.configureGraphicalViewer();
        GraphicalViewer viewer = this.getGraphicalViewer();
        // set teh RootEditPart
        viewer.setRootEditPart(new FreeformGraphicalRootEditPart()); 
        // set the factory
        viewer.setEditPartFactory(new PartFactory());
        ContextMenuProvider provider = new MyContextMenuProvider(viewer, getActionRegistry());
        viewer.setContextMenu(provider);
        viewer.addSelectionChangedListener(this);
        getSite().registerContextMenu(provider, viewer);
        updateActions(getSelectionActions());
    }

    public void doSave(IProgressMonitor monitor) 
    {
        // TODO Auto-generated method stub
//    	WriteToEnumXML write = new WriteToEnumXML(diagram , project);
//    	String path = project.getConfigPath();
//    	StringBuffer sb = new StringBuffer(path);
//    	StringBuffer sb1 = new StringBuffer(project.getProjectName());
//    	sb1.append(".");
//    	sb1.append(project.getPackageName());
//    	sb1.append(".");
//    	sb1.append(project.getComponentName());
//    	sb1.append(".xml");
//    	try{
//    		//save component information to xml file
//			write.writeXMLFile(sb.append(sb1.toString()).toString());
//			//save component to file
//			sb = new StringBuffer(path);
//			stream.outputs(diagram , filePath);
//    	}
//    	catch(IOException ioe)
//    	{
//    		ioe.printStackTrace();
//    	}
//    	catch(Exception e)
//    	{
//			e.printStackTrace();
//		}
////    	try {
////			Object tmp = PropertyUtils.getProperty(getEditorInput(),"treeView");
////			Method [] methods = tmp.getClass().getMethods();
////			for(int i = 0; i < methods.length;i++)
////			{
////				if(methods[i].getName().equals("refresh"))
////					methods[i].invoke(tmp , null);
////			}
////		} 
////    	catch (IllegalAccessException e) 
////    	{
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} 
////    	catch (InvocationTargetException e) 
////    	{
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} 
////    	catch (NoSuchMethodException e) 
////		{
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//    	((CEECTreeView)UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
//    			.getActivePage().findView("net.ms.designer.ui.view.CEECTreeView")).refresh();
//    	
//    	getCommandStack().markSaveLocation();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#doSaveAs()
     */
    public void doSaveAs() 
    {
    }

    public boolean isDirty() 
    {
        return isSaveOnCloseNeeded();
    }

    public boolean isSaveOnCloseNeeded() 
    {
        return getCommandStack().isDirty();
    }
    
    public boolean isSaveAsAllowed() 
    {
        return false;
    }
    
    protected void createActions() 
    {
    	  super.createActions();
    	  ActionRegistry registry = getActionRegistry();
    	  IAction action;

    	  action = new PropertyAction(this);
    	  registry.registerAction(action);
    	  getSelectionActions().add(action.getId());
    }
    
    public void selectionChanged(SelectionChangedEvent event) 
    {
        updateActions(getSelectionActions());
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
//			project.setDirectory((String )PropertyUtils.getProperty(tmpproject,"directory"));
//			project.setProjectName((String )PropertyUtils.getProperty(tmpproject,"projectName"));
//			project.setGenPackageName((String)PropertyUtils.getProperty(tmpproject, "genPackageName"));
//			project.setPackageName((String )PropertyUtils.getProperty(tmpproject,"packageName"));
//			project.setComponentName((String )PropertyUtils.getProperty(tmpproject,"componentName"));
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
    
    protected void superSetInput(IEditorInput input) 
    {
        super.setInput(input);
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
}
