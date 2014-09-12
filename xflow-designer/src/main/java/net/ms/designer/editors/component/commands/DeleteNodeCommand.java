/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.component.models.BizComponent;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;
import net.ms.designer.editors.component.models.EnumComponent;
import net.ms.designer.editors.component.tools.WriteToProjectXML;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.internal.UIPlugin;


/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteNodeCommand extends Command {
    private CompDiagram diagram;

    private Component node;
    
    private int index;

    public void setDiagram(CompDiagram diagram) {
        this.diagram = diagram;
    }

    public void setNode(Component node) {
        this.node = node;
    }

    //------------------------------------------------------------------------
    // Overridden from Command

    public void execute() 
    {
    	PackageEditor editor = (PackageEditor)UIPlugin.getDefault()
								.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage().getActiveEditor();
    	MsProject project = editor.getProject();
    	MsContext context = editor.getContext();
    	MsElement currentElement = editor.getCurrentElement();
    	
        String type = "";
        String tag = "";
        
        if(node instanceof BizComponent)
    	{
    		type = "componentdetail";
    		tag = "component";
    	}
    	if(node instanceof EnumComponent)
    	{
    		type = "enumdetail";
    		tag = "enumeration";
    	}
        String path = project.getConfigPath();
    	path = path + "project.xml";
    	WriteToProjectXML writor = new WriteToProjectXML();
    	try
    	{
    		writor.doDelete(path , tag , node.getName() , currentElement.getNodeName());
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	MsElement child = context.getElement(type + "_" + node.getName());

        index=diagram.getNodes().indexOf(node);
        diagram.removeNode(node);
            
	    MsElement parent = child.getParent();
	    parent.removeChild(child);
	    context.removeElement(type + "_" + node.getName());
	    // delete the xml file
	    File file = new File(project.getConfigPath() + node.getComponentID() + ".xml");
	    file.delete();
    }

    public String getLabel() {
        return "Delete Node";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        diagram.addNode(node);
    }
}