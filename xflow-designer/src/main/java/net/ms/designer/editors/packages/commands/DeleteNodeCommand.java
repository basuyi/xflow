/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.commands;

import java.io.File;
import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.packages.models.Package;
import net.ms.designer.editors.packages.models.PackageDiagram;
import net.ms.designer.editors.packages.ui.PackageEditor;
import net.ms.designer.editors.packages.ui.WriteToProjectXML;

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
    private PackageDiagram diagram;

    private Package node;
    
    private int index;

    public void setDiagram(PackageDiagram diagram) {
        this.diagram = diagram;
    }

    public void setNode(Package node) {
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
    	if(context.getElement("component" + "_" + node.getName()).getChildren().size() != 0)
        {
	        boolean flag = MessageDialog.openQuestion(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
	        							.getShell() , 
	        							"Information" , 
	        							"There are components in this package . Do you want to continue ?"
	        							);  
	        if(!flag)
	        {
	        	return;
	        }
        }
    	index=diagram.getNodes().indexOf(node);
	    diagram.removeNode(node);
	        
	    String path = project.getConfigPath();
	    path = path + "project.xml";
	    WriteToProjectXML writor = new WriteToProjectXML();
	    try 
	    {
			writor.doDelete(path,"package",node.getName());
		} 
	    catch (Exception e)
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    MsElement child = context.getElement("component" + "_" + node.getName());
	    // first , deleting the children elements , and the xml files
	    List children = child.getChildren();
	    for(int i = 0 ; i < children.size() ; i++)
	    {
	    	MsElement tmp = (MsElement)children.get(i);
	    	File file = new File(project.getConfigPath() + tmp.getId() + ".xml");
	    	file.delete();
	    	child.removeChild(tmp);
	    }
        MsElement parent = child.getParent();
        parent.removeChild(child);
        context.removeElement("component" + "_" + node.getName());
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