package net.ms.designer.editors.enumcomponentdetail.command;

import net.ms.designer.editors.enumcomponentdetail.dialog.PropertyDialog;
import net.ms.designer.editors.enumcomponentdetail.model.Element;
import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.model.ValueField;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;


public class CreateCommand extends Command 
{
    private Element parent;
    private Element child;
    private Shell shell;
    private int index = -1;

    public void execute() 
    {
    	PropertyDialog dialog = new PropertyDialog(shell,child);
    	dialog.status = 0 ;
    	int flag = dialog.open();
        if(Window.OK == flag)
        	parent.addChild(index,child);
        else if(Window.CANCEL == flag)
        	return;
    }

    public void redo() 
    {
        execute();
    }

    public void undo() 
    {
        parent.removeChild(child);
    }

    public Element getParent() 
    {
        return parent;
    }

    public void setParent(Element parent) 
    {
        this.parent = parent;
    }

    public Element getChild() 
    {
        return child;
    }

    public void setChild(Element child) 
    {
        this.child = child;
    }

    public int getIndex() 
    {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
