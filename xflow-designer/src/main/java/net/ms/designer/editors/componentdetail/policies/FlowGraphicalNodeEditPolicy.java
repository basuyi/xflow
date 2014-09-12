package net.ms.designer.editors.componentdetail.policies;

import net.ms.designer.editors.componentdetail.commands.ConnectionCommand;
import net.ms.designer.editors.componentdetail.models.FlowField;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;




public class FlowGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	protected Command getConnectionCompleteCommand(CreateConnectionRequest arg0) {
		// TODO 自动生成方法存根
		return null;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		// TODO 自动生成方法存根
		//System.out.println("getConnectionCreateCommand,flowFild");
		ConnectionCommand command = new ConnectionCommand();
		command.setSource((FlowField)getHost().getModel());
		request.setStartCommand(command);
		return command;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest arg0) {
		// TODO 自动生成方法存根
		return null;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest arg0) {
		// TODO 自动生成方法存根
		return null;
	}
	

	

}
