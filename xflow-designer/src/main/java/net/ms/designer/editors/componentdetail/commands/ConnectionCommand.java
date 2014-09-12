package net.ms.designer.editors.componentdetail.commands;

import java.util.Iterator;
import java.util.Vector;

import net.ms.designer.editors.componentdetail.models.AutoPField;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.Messages;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.models.Wire;

import org.eclipse.gef.commands.Command;

/**
 * @author lili
 * @version 1.1.0 defind the ConnectionCommand, it deal with the source,target
 *          and how to connect them
 */
public class ConnectionCommand extends Command {

	// protected Table oldSource;
	protected Element oldSource;

	protected Table oldTarget;

	protected Element source;

	protected Table target;

	protected Wire wire;

	/**
	 * construct of ConnectionCommand()
	 * 
	 */
	public ConnectionCommand() {
		super(Messages.getString("ConnectionCommand.Label"));
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (target != null) {
			Vector conns = target.getConnections();
			Iterator i = conns.iterator();
			while (i.hasNext()) {
				Wire conn = (Wire) i.next();
				if (conn.getTarget().equals(oldSource))
					return false;
			}
		}
		return true;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {

		if (target != null) {
			if (target.equals(wire.getSource()))
				return;
			wire.detachTarget();
			wire.setTarget(target);
			wire.attachTarget();
		}
		if (source != null) {
			if (source.equals(wire.getTarget()))
				return;
			wire.detachSource();
			wire.setSource(source);
			wire.attachSource();
			if (source instanceof ChildTable) {
				AutoPField ap = new AutoPField();
				ap.setName(target.getName().toLowerCase() + "id");
				((ChildTable) source).addChild(ap);
			}
		}
		if (source == null && target == null) {
			wire.detachSource();
			wire.detachTarget();
			wire.setTarget(null);
			wire.setSource(null);

			if (oldSource instanceof ChildTable) {
				if (((ChildTable) oldSource).getChildren() != null) {
					for (int i = 0; i < ((ChildTable) oldSource).getChildren()
							.size(); i++) {
						Element field = (Element) ((ChildTable) oldSource)
								.getChildren().get(i);
						if (field.getName().equals(
								oldTarget.getName().toLowerCase() + "id")) {
							((ChildTable) oldSource).removeChild(field);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @return return the source
	 */
	public Element getSource() {
		return source;
	}

	/**
	 * 
	 * @return return the target
	 */
	public Element getTarget() {
		return target;
	}

	/**
	 * 
	 * @return return the wire
	 */
	public Wire getWire() {
		return wire;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		execute();
	}

	/**
	 * 
	 * @param newSource
	 *            Table newSource to set
	 */
	public void setSource(Element newSource) {
		source = newSource;
	}

	/**
	 * 
	 * @param newTarget
	 *            Talbe newTarget to set
	 */
	public void setTarget(Table newTarget) {
		target = newTarget;
	}

	/**
	 * 
	 * @param w
	 *            Wire w to set
	 */
	public void setWire(Wire w) {
		wire = w;
		oldSource = w.getSource();
		oldTarget = w.getTarget();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		source = wire.getSource();
		target = wire.getTarget();

		wire.detachSource();
		wire.detachTarget();

		wire.setSource(oldSource);
		wire.setTarget(oldTarget);

		wire.attachSource();
		wire.attachTarget();
	}

}
