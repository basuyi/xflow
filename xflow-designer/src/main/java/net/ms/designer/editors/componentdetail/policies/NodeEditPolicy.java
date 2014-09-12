
package net.ms.designer.editors.componentdetail.policies;

import net.ms.designer.editors.componentdetail.commands.ConnectionCommand;
import net.ms.designer.editors.componentdetail.editparts.BaseEditPart;
import net.ms.designer.editors.componentdetail.figures.FigureFactory;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.models.Wire;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;


/**
 * @author lili
 * @version 1.1.0
 *defind the NodeEditPolicy, it defind how to request to the command
 */
public class NodeEditPolicy extends GraphicalNodeEditPolicy {

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#createDummyConnection(org.eclipse.gef.Request)
	 */
	protected Connection createDummyConnection(Request req) {
		return FigureFactory.createNewWire(null);
	}
	
	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
	 */
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		if(getTable() == null)
			return null;
		ConnectionCommand command = (ConnectionCommand) request
				.getStartCommand();
		command.setTarget(getTable());
		return command;
	}

	/**
	 * 
	 * @return return the model of table
	 */
	protected Table getTable() {
		////System.out.println("KCGNodeEditPolicy.getTable() = "+getHost().getModel());
		if(getHost().getModel() instanceof Table)
			return (Table) getHost().getModel();
		return null;
	}

	/**
	 * 
	 * @return
	 */
	protected BaseEditPart getEditPart() {
		return (BaseEditPart) getHost();
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCreateCommand(org.eclipse.gef.requests.CreateConnectionRequest)
	 */
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		
		if(getTable() == null || getTable() instanceof ComponentTable)
			return null;
		
		ConnectionCommand command = new ConnectionCommand();
		command.setWire(new Wire());
		command.setSource(getTable());

		request.setStartCommand(command);
		return command;
	}

	/*
	 *  （非 Javadoc）
	 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
	 */
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		if(getTable() == null)
			return null;
		
		ConnectionCommand cmd = new ConnectionCommand();
		cmd.setWire((Wire) request.getConnectionEditPart().getModel());
		cmd.setTarget(getTable());
		return cmd;
	}

//	protected IFigure getFeedbackLayer() {
//		return getLayer(LayerConstants.SCALED_FEEDBACK_LAYER);
//	}

	/**
	 * fire the request to command to do the connection
	 */
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		if(getTable() == null)
			return null;
		
		ConnectionCommand cmd = new ConnectionCommand();
		cmd.setWire((Wire) request.getConnectionEditPart().getModel());

		cmd.setSource(getTable());
		return cmd;
	}
}