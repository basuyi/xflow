package net.ms.designer.editors.componentdetail.commands;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.models.Wire;
import net.ms.designer.editors.componentdetail.tools.MyContextMenuProvider;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.ActionRegistry;


/**
 * @author lili
 * @version 1.1.0
 *defind the DeleteCommand
 */
public class DeleteCommand extends Command 
{

    private Element child;

    private Container parent;

    private int index = -1;

    private List sourceConnections = new ArrayList();

    private List targetConnections = new ArrayList();

    public void setChild(Element base) 
    {
        child = base;
    }

    public void execute() 
    {
    	this.primExecute();
    }

    private void deleteConnections(Element part) 
    {
        if (part instanceof Container) 
        {
            List children = ((Container) part).getChildren();
            for (int i = 0; i < children.size(); i++)
  
                deleteConnections((Element) children.get(i));
            
        }
        sourceConnections.addAll(part.getSourceConnections());
        for (int i = 0; i < sourceConnections.size(); i++) 
        {
            Wire wire = (Wire) sourceConnections.get(i);
            wire.detachSource();
            wire.detachTarget();
        }
        targetConnections.addAll(part.getTargetConnections());
        for (int i = 0; i < targetConnections.size(); i++) 
        {
            Wire wire = (Wire) targetConnections.get(i);
            wire.detachSource();
            wire.detachTarget();
        }
    }

    private void restoreConnections() 
    {
        for (int i = 0; i < sourceConnections.size(); i++) 
        {
            Wire wire = (Wire) sourceConnections.get(i);
            wire.attachSource();
            wire.attachTarget();
        }
        sourceConnections.clear();
        for (int i = 0; i < targetConnections.size(); i++) 
        {
            Wire wire = (Wire) targetConnections.get(i);
            wire.attachSource();
            wire.attachTarget();
        }
        targetConnections.clear();
    }

    private void primExecute() 
    {
//    	deleteConnections(child);
//        index = parent.getChildren().indexOf(child);
//        if (child instanceof ComponentTable) 
//        {
//        	
//        	return;
//    
//        }
//        if(child instanceof ChildTable && !(child instanceof ComponentTable))
//        {
////        	child.getParent().getMainTable().setHasChildTable(0);
//        	if(parent.getChildTableCount() > 0 && parent.getChildTableCount()!=1)
//        	{
//        		parent.getMainTable().setHasChildTable(1);
//        		int newCount = parent.getChildTableCount()-1;
//        		parent.setChildTableCount(newCount);
//        	}
//        	else
//        	{
//        		parent.getMainTable().setHasChildTable(0);
//        		parent.setChildTableCount(0);
//        	}
//        	
//        }
//        if(child instanceof FlowField)
//        {
////        	child.getParent().getMainTable().setFlowAssociated(0);
//        	parent.getMainTable().setFlowAssociated(0);
//        }
//        parent.removeChild(child);
    	
    	deleteConnections(child);
        index = parent.getChildren().indexOf(child);
        if (child instanceof ComponentTable) 
        {
        	
        	return;
    
        }
        if(child instanceof ChildTable && !(child instanceof ComponentTable))
        {
        	//判断字表是否有字表，有则不可删除，无则删除----------lili 1116 start
        	if(((Table)child).getSubTabel().size()>0)
        	{
        		return;
        		
        	}
        	else
        	{
        		for(int i = 0;i< parent.getChildren().size();i++)
        		{
        			if(parent.getChildren().get(i) instanceof ChildTable)
        			{
	        			Table table1 = (Table)(parent.getChildren().get(i));
	        			for(int j=0;j<table1.getSubTabel().size();j++)
	        			{
	        				if(((Table)(table1.getSubTabel().get(j))).getName().equals(child.getName()))
	        				{
	        					table1.getSubTabel().remove(j);
	        				}
	        			}
        			}
        		}
        	
        		((Table)child).getSubTabel().remove(child);
        		
        	}
        	//---------------lili 1116 end
//        	child.getParent().getMainTable().setHasChildTable(0);
        	if(parent.getChildTableCount() > 0 && parent.getChildTableCount()!=1)
        	{
        		parent.getMainTable().setHasChildTable(1);
        		int newCount = parent.getChildTableCount()-1;
        		parent.setChildTableCount(newCount);
        	}
        	else
        	{
        		parent.getMainTable().setHasChildTable(0);
        		parent.setChildTableCount(0);
        	}
        	
        }
        if(child instanceof FlowField)
        {
//        	child.getParent().getMainTable().setFlowAssociated(0);
        	parent.getMainTable().setFlowAssociated(0);
        }
        parent.removeChild(child);
    }

    public void setParent(Container parent) 
    {
        this.parent = parent;
    }

    public void redo() 
    {
        primExecute();
    }

    public void undo() 
    {
        restoreConnections();
        parent.addChild(child, index);

    }
    
   

}
    