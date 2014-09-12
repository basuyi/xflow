package net.ms.designer.editors.workflow.policies;

import net.ms.designer.editors.workflow.commands.WireCommand;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;



public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy{

	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		// TODO Auto-generated method stub
		WireCommand command = (WireCommand) request.getStartCommand();
        command.setTarget((WorkflowBaseActivity) getHost().getModel());
        return command;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		// TODO Auto-generated method stub
		WireCommand command = new WireCommand();
        command.setSource((WorkflowBaseActivity) getHost().getModel());
        request.setStartCommand(command);
        return command;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		WireCommand cmd = new WireCommand();
		cmd.setWire((Wire)request.getConnectionEditPart().getModel());

		cmd.setTarget((WorkflowBaseActivity)getHost().getModel());
		return cmd;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		WireCommand cmd = new WireCommand();
		cmd.setWire((Wire)request.getConnectionEditPart().getModel());
		cmd.setSource((WorkflowBaseActivity)getHost().getModel());
		return cmd;
	}

}
