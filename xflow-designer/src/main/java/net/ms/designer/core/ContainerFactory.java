package net.ms.designer.core;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.componentdetail.models.AutoNumField;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.packages.models.PackageDiagram;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.internal.UIPlugin;


public class ContainerFactory implements Serializable
{
	String filePath;
	Object container;
	IOStreams stream;
	MsProject project;
	private PackageEditor editor;
	
	public ContainerFactory(MsProject project)
	{
		this.project = project;
		stream = new IOStreams();
		this.editor = (PackageEditor)UIPlugin.getDefault()
						.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().getActiveEditor();
	}
	
	public Object getContainer(String type , String name)
	{
		if(type.equals("package"))
		{
    		container  = new PackageDiagram();
		}
		if(type.equals("component"))
		{
    		container  = new CompDiagram();
		}
		if(type.equals("componentdetail"))
		{
			MsElement element = editor.getCurrentElement();
			container  = new Container();
			Container ct = (Container)container;
			ct.setName(name);
			ct.setId(element.getId());
			ComponentTable table = new ComponentTable();
			table.setId(ct.getId());
			table.setName(ct.getName());
			table.setIName(table.getName());
			AutoNumField at = new AutoNumField();
			at.setName(ct.getName().toLowerCase() + "id");
			at.setIName(at.getName());
			ChildTable cd = new ChildTable();
			cd.setMainTableName(ct.getName().toLowerCase() + "id");
			ct.addChild(table);	
			table.addChild(at);
						
			ct.setMainTable(true);
		}
		if(type.equals("enumdetail"))
		{
    		container  = new net.ms.designer.editors.enumcomponentdetail.model.Container();
    		Table table = new Table();
    	    Point p = new Point(88,88);
    	    table.setTableName(name);
    	    table.setLocation(p);
    	    ((net.ms.designer.editors.enumcomponentdetail.model.Container)container).addChild(table);
		}
		if(type.equals("workflow"))
		{
			container = new net.ms.designer.editors.workflow.models.WorkflowDiagram();
			((net.ms.designer.editors.workflow.models.WorkflowDiagram)container).setName(name);
		}
		if(type.equals("subflow"))
		{
			container = new net.ms.designer.editors.workflow.models.WorkflowDiagram();
			((net.ms.designer.editors.workflow.models.WorkflowDiagram)container).setIsSubflow(true);
			((net.ms.designer.editors.workflow.models.WorkflowDiagram)container).setName(name);
		}
		return container;
	}
}