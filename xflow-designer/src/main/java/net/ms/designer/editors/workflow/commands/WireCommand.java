package net.ms.designer.editors.workflow.commands;

import java.util.List;

import net.ms.designer.editors.workflow.dialog.WirePropertyDialog;
import net.ms.designer.editors.workflow.dialog.WorkflowPropertyDialog;
import net.ms.designer.editors.workflow.models.EndNode;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;


public class WireCommand extends Command 
{

	protected WorkflowSubPart oldSource;
	protected WorkflowSubPart oldTarget;
	protected WorkflowSubPart source;
	protected WorkflowSubPart target;
	protected Wire wire;
	protected Shell shell;
	
	protected String flag;

	public boolean canExecute() 
	{
		if (source!=null) 
		{
		    if(source instanceof EndNode)
		        return false;
			if(target!=null)
			{
				if(source.equals(target))
					return false;
			}
			if (source.equals(oldTarget))
				return false;			
			List connections = this.source.getInputs();
			for (int i = 0; i < connections.size(); i++) 
			{
				WorkflowSubPart t = ((Wire) connections.get(i)).getTarget();
				if (t.equals(target)||t.equals(oldTarget))
					return false;
			}
		}
		if(target !=null )
		{
			if (target.equals(oldSource))
				return false;			
			List connections = this.target.getOutputs();
			for (int i = 0; i < connections.size(); i++) 
			{
				WorkflowSubPart t = ((Wire) connections.get(i)).getSource();
				if (t.equals(source)||t.equals(oldSource))
					return false;
			}			
		}
		if(wire!=null){
		    if(!wire.ifEditAble())
		        return false;
		}
		return true;
	}
	public void execute() 
	{ 
		// open property dialog
		if(this.wire != null && this.flag.equals("WIRE_PROPERTY"))
		{
			WirePropertyDialog dialog = new WirePropertyDialog(shell,wire);
	    	dialog.open();
		}
		// delete wire
		if(this.wire !=null && this.flag.equals("delete"))
		{
			this.wire.getSource().removeOutput(wire);
			this.wire.getTarget().removeInput(wire);
		}
		//draw wire
		else
		{
			if(wire == null)
			{
				wire = new Wire(this.source,this.target);
			}
		}
	}

	public WorkflowSubPart getSource() 
	{
		return source;
	}

	public WorkflowSubPart getTarget() 
	{
		return target;
	}

	public Wire getWire() 
	{
		return wire;
	}
	
	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public void redo()
	{
		 this.source.addOutput(this.wire);
	     this.target.addInput(this.wire);
	}

	public void setSource(WorkflowSubPart newSource) 
	{
		source = newSource;
	}

	public void setTarget(WorkflowSubPart newTarget) 
	{
		target = newTarget;
	}

	public void setWire(Wire w) 
	{
		wire = w;
		oldSource = w.getSource();
		oldTarget = w.getTarget();
		this.source = w.getSource();
		this.target = w.getTarget();
	}

	public void undo() 
	{
		this.source.removeOutput(this.wire);
        this.target.removeInput(this.wire);
	}

}