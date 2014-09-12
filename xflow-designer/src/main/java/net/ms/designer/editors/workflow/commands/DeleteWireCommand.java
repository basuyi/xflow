package net.ms.designer.editors.workflow.commands;

import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;


public class DeleteWireCommand extends Command 
{

	WorkflowSubPart source;
	WorkflowSubPart target;
	Wire connection;

    //Setters
    public void setConnection(Wire connection) 
    {
        this.connection = connection;
    }

    public void setSource(WorkflowSubPart source) 
    {
        this.source = source;
    }

    public void setTarget(WorkflowSubPart target) 
    {
        this.target = target;
    }

    public void execute() 
    {
    	if(source !=null)
    		source.removeInput(connection);
    	if(target !=null)
    		target.removeOutput(connection);   		        
        connection.setSource(null);
        connection.setTarget(null);
    }
    
    public String getLabel() 
    {
        return "Delete Connection";
    }

    public void redo() 
    {
        execute();
    }

    public void undo() 
    {
        connection.setSource((WorkflowBaseActivity)source);
        connection.setTarget((WorkflowBaseActivity)target);
        source.addOutput(connection);
//        source.addOutput(connection);
        target.addInput(connection);
    }
}