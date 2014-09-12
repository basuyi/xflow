package net.ms.designer.core;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.ms.designer.editors.component.models.BizComponent;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.tools.PaletteFactory;
import net.ms.designer.editors.packages.models.Package;
import net.ms.designer.editors.packages.models.PackageDiagram;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.UIPlugin;


public class MsContext implements Serializable
{
	private static final long serialVersionUID = 1162255464;
	
	MsProject project;
	
	transient IWorkbenchPage page;
	transient DefaultEditDomain domain;
	transient PaletteViewer view;
	transient PaletteRoot root;
	transient PackageEditor editor;
	
	PaletteRootFactory factory = new PaletteRootFactory();
	transient ContainerFactory containerFactory;
	
	Map elements = new HashMap();

	public MsContext()
	{
		
	}
	
	public void addElement(MsElement element)
	{
		elements.put(element.getKey() , element);
	}
	
	public MsElement getElement(String key)
	{
		return (MsElement)elements.get(key);
	}
	
	public void removeElement(String key)
	{
		elements.remove(key);
	}
	
	public Map getElements()
	{
		return this.elements;
	}
	
	// project info
	public MsProject getProject()
	{
		return this.project;
	}
	public void setProject(MsProject project)
	{
		this.project = project;
	}
	/**
	 * 
	 * @param element
	 */
	public void changeContext(MsElement element)
	{
		if(element != null)
			this.changeContext(element.nodeType , element.nodeName);
		else
			return;
	}
	/**
	 * 
	 * @param nodeType
	 * @param nodeName
	 */
	public void changeContext(String nodeType , String nodeName)
	{
		page = this.getActivePage();
		editor = this.getEditor();
		domain = this.getEditDomain();
		view = this.getPaletteViewer();
		root = factory.getPaletteFactory(nodeType);
		//change the PaletteRoot
    	view.getEditPartRegistry().put(root.getDefaultEntry() , 
    			new org.eclipse.gef.internal.ui.palette.editparts.ToolEntryEditPart(root.getDefaultEntry()));
    	domain.setPaletteRoot(root);
    	
    	MsElement oldElement = editor.getCurrentElement();
		MsElement newElement = new MsElement();
    	
    	newElement.setNodeType(nodeType);
    	newElement.setNodeName(nodeName);
    	
    	if(getElement(newElement.getKey()) != null)
    	{
    		editor.setCurrentElement(getElement(newElement.getKey()));
    		Object container = getElement(newElement.getKey()).getContainer();
    		editor.setContainer(container);
    		((PackageEditor)page.getActiveEditor()).getViewer().setContents(container);
    	}
    	else
    	{
    		containerFactory = new ContainerFactory(this.getProject());
    		((PackageEditor)page.getActiveEditor()).getViewer().setContents(containerFactory.getContainer(nodeType,nodeName));
    		editor.setContainer(containerFactory.getContainer(nodeType,nodeName));	
    		newElement.setContainer(containerFactory.getContainer(nodeType,nodeName));
    		newElement.setParent(oldElement);
    		editor.setCurrentElement(newElement);
    	}
		editor.setNodeName(nodeName);
		editor.setNodeType(nodeType);
		
	}
	//get all the packages
	public List getPackages()
	{
		List list = new ArrayList();
		Iterator iterator = this.getIterator();
		MsElement element;
		while(iterator.hasNext())
		{
			element = (MsElement)iterator.next();
			if(element.getNodeType().equals("package"))
			{
				list.add(element);
			}
		}
		return list;
	}
	//get all the components
	public List getComponents()
	{
		List list = new ArrayList();
		Iterator iterator = this.getIterator();
		MsElement element;
		while(iterator.hasNext())
		{
			element = (MsElement)iterator.next();
			if(element.getNodeType().equals("component"))
			{
				list.add(element);
			}
		}
		return list;
	}
	//get all the details
	public List getDetails()
	{
		List list = new ArrayList();
		Iterator iterator = this.getIterator();
		MsElement element;
		while(iterator.hasNext())
		{
			element = (MsElement)iterator.next();
			if(!element.getNodeType().equals("package") && !element.getNodeType().equals("component"))
			{
				list.add(element);
			}
		}
		return list;
	}
	//get the num of the elements
	public int getCount()
	{
		return this.getElements().size();
	}
	//get iterator
	public Iterator getIterator()
	{
		Map map = this.getElements();
		Collection col = map.values();
		Iterator iterator = col.iterator();
		return iterator;
	}
	
	public IWorkbenchPage getActivePage()
	{
		return UIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
	}
	public PackageEditor getEditor()
	{
		return (PackageEditor)getActivePage().getActiveEditor();
	}
	public DefaultEditDomain getEditDomain()
	{
		return (DefaultEditDomain)getEditor().getViewer().getEditDomain();
	}
	public PaletteViewer getPaletteViewer()
	{
		return getEditDomain().getPaletteViewer();
	}
	/**
	 * 
	 * @param obj
	 * @param type
	 */
	public void updateContext(Object obj , String type)
	{
		String name = "";
		String id = "";
		String idType = "";
		
		if(type.equals("component"))
		{
			idType = "packageID";
		}
		if(type.equals("componentdetail") || type.equals("enumdetail"))
		{
			idType = "componentID";
		}
		if(type.equals("workflow"))
		{
			idType = "id";
		}
		if(type.equals("subflow"))
		{
			idType = "subflowId";
		}
		
		try 
		{
			name = (String)PropertyUtils.getProperty(obj, "name");
			id = (String)PropertyUtils.getProperty(obj, idType);
		} 
		catch (IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (NoSuchMethodException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		containerFactory = new ContainerFactory(this.getProject());
		editor = this.getEditor();
		MsElement parent = editor.getCurrentElement();
		MsElement newElement = new MsElement();
		newElement.setContainer(containerFactory.getContainer(type , name));
		newElement.setNodeType(type);
		newElement.setId(id);
		
		newElement.setNodeName(name);
		if(parent == null)
		{
			parent = new MsElement();
			parent.setNodeName(editor.getInput().getEditorName());
			parent.setNodeType(editor.getInput().getEditorType());
			parent.setContainer(editor.getViewer().getContents().getModel());
		}
		parent.addChild(newElement);
		newElement.setParent(parent);
		editor.setCurrentElement(parent);
		this.addElement(newElement);
	}
	/**
	 * 
	 *
	 */
	public void preOperation()
	{
		PackageEditor editor = this.getEditor(); 
		MsElement element = editor.getCurrentElement();
    	if(element == null)
    	{
    		element = new MsElement();
    		element.setNodeName(editor.getInput().getEditorName());
    		element.setNodeType(editor.getInput().getEditorType());
    	}
    	element.setContainer(editor.getViewer().getContents().getModel());
    	this.addElement(element);
//    	if(element.getParent() != null)
//    	{
//    		element.getParent().addChild(element);
//    	}
	}
	/**
	 * 
	 * @param editPart
	 * @param type
	 */
	public void change(EditPart editPart , String type)
	{
		PackageEditor editor = this.getEditor();
		Object container = editPart.getParent().getModel();
		Object node = editPart.getModel();
		String name = editor.getNodeName();
		MsElement current = editor.getCurrentElement();
		if(current == null)
    	{
        	current = new MsElement();
        	current.setContainer(container);
        	current.setNodeName(name);
        	current.setNodeType(type);
    	}
    	editor.setCurrentElement(current);
    	
    	project = editor.getProject();
    	
    	String nodeName = "";
    	try 
		{
			nodeName = (String)PropertyUtils.getProperty(node, "name");
		} 
		catch (IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (NoSuchMethodException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
//    	if(current.getParent() != null)
//    	{
//    		current.getParent().addChild(current);
//    	}
    	if(type.equals("package"))
    	{
    		project.setPackageName(nodeName);
        	editor.setProject(project);
        	this.setProject(project);
        	this.addElement(current);
        	this.changeContext("component",nodeName);
    	}
    	if(type.equals("component"))
    	{
    		if(node instanceof net.ms.designer.editors.component.models.EnumComponent)
    		{
    			project.setComponentName(nodeName);
	        	editor.setProject(project);
	        	this.setProject(project);
	        	this.addElement(current);
	        	this.changeContext("enumdetail",nodeName);
    		}
    		if(node instanceof net.ms.designer.editors.component.models.BizComponent)
    		{
    			project.setComponentName(nodeName);
	        	editor.setProject(project);
	        	this.setProject(project);
	        	this.addElement(current);
	        	this.changeContext("componentdetail",nodeName);
    		}
    	}
    	if(type.equals("componentdetail"))
    	{
    		project.setWorkflowName(nodeName);
    		editor.setProject(project);
    		this.setProject(project);
    		this.addElement(current);
    		this.changeContext("workflow",nodeName);
    	}
    	if(type.equals("workflow"))
    	{
    		project.setWorkflowName(nodeName);
    		editor.setProject(project);
    		this.setProject(project);
    		this.addElement(current);
    		this.changeContext("subflow",nodeName);
    	}
	}
}