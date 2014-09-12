package net.ms.designer.editors.componentdetail.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ConfigDialog;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.Messages;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.gef.commands.Command;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.UIPlugin;



/**
 * @author lili
 * @version 1.1.0
 *defind the CreateCommand, 未完
 */
public class CreateCommand extends Command
{
	
	private Element child;

	private Rectangle rect;

	private Container parent;
	
	private CommonField com;
	
	private String change;

	private int index = -1;
	
	private int count = 0;
	
	private int Id = 0;
	
	private Shell shell;

	private List sourceConnections = new ArrayList();

	private List targetConnections = new ArrayList();
	
	private PackageEditor editor;

	/**
	 * 
	 * @author Administrator
	 *
	 */
	class validate implements IInputValidator 
	{

		public String isValid(String newText) 
		{
           
            IStatus val = JavaConventions.validateJavaTypeName(newText);
            if (val.getSeverity() == IStatus.ERROR) 
            {
                return val.getMessage(); 
            }

            return null;
		}
	}
	
	/**
	 * construct of CreatCommand()
	 *
	 */
	public CreateCommand() 
	{
		super(Messages.getString("CreateCommand.Label"));
		this.editor = (PackageEditor)UIPlugin.getDefault()
						.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().getActiveEditor();
	}
	
	/**
	 * delect the connections
	 * @param part
	 */
	private void deleteConnections(Element part) 
	{
		
	}
	
	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() 
	{

		change ="0";
		ConfigDialog d = new ConfigDialog(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), child, parent, com,change);
//		d.open();
		if(child instanceof Table)
    	{
    		if (Window.OK == d.open()) {
//    			index=parent.getChildTableCount();
//    			parent.flag = 1;
    			parent.addChild(child);
    			count = parent.getChildTableCount() + 1;
    			parent.setChildTableCount(count);
    			Id = parent.getChildTableCount1()+1;
    			parent.setChildTableCount1(Id);
    			child.setId(""+Id);
//    			child.setId("" + Id);
//    			parent.getChildTableCount();
//    			parent.setChildTableCountPlus();
    			
    		}
    	}
		if(child instanceof CommonField)
    	{
    		if (Window.OK == d.open()) {
    			parent.addChild(child, index);
    		}
    	}
//        parent.addChild(child,-1);
		if(child instanceof FlowField)
		{
			if(Window.OK == d.open())
			{
				parent.addChild(child,index);
				Date da = new Date();
				long time =  da.getTime();
				child.setId(Long.toString(time));
				MsContext context = editor.getContext();
				context.updateContext(child , "workflow");
			}
		}
        if (rect != null) 
		{
			Insets expansion = getInsets();
			if (!rect.isEmpty())
				rect.expand(expansion);
			else {
				rect.x -= expansion.left;
				rect.y -= expansion.top;
			}
			child.setLocation(rect.getLocation());
			if (!rect.isEmpty())
			{
			
				child.setSize(rect.getSize());
			}
		}
////		redo();
//		parent.addChild(child, index);
		
	}
	
	/**
	 * 
	 * @return return the location of the child
	 */
	private Insets getInsets() 
	{
		if (child instanceof Element)// || child instanceof Circuit)
			return new Insets(2, 0, 2, 0);
		return new Insets();
	}

	/**
	 * 
	 * @return return the parent
	 */
	public Container getParent() 
	{
		return parent;
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() 
	{
		execute();
//		parent.addChild(child, index);
	}

	/**
	 * 
	 * @param subpart
	 *         Element subpart to set
	 */
	public void setChild(Element subpart) 
	{
		child = subpart;
	}

	/**
	 * 
	 * @param index
	 *         index to set
	 */
	public void setIndex(int index) 
	{
		this.index = index;
	}

	public int getIndex()
	{
		return this.index;
	}

	/**
	 * 
	 * @param r
	 *         Rectangle r to set
	 */
	public void setLocation(Rectangle r) 
	{
		rect = r;
	}

	/**
	 * 
	 * @param newParent
	 *         Container newParent to set
	 */
	public void setParent(Container newParent) 
	{
		parent = newParent;
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() 
	{
		undoExecute();
		parent.removeChild(child);
	}

	/**
	 * remove the child
	 *
	 */
	private void undoExecute() 
	{
		deleteConnections(child);
		index = parent.getChildren().indexOf(child);
		parent.removeChild(child);
		
	}
	
	}


