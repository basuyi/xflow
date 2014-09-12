/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.commands;

import net.ms.designer.editors.component.models.Component;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;



public class MoveNodeCommand extends Command {
    private Component node;

    private Point oldPos;

    private Point newPos;

    public void setLocation(Point p) {
        this.newPos = p;
    }

    public void setNode(Component node) {
        this.node = node;
    }

    public void execute() {
        oldPos = this.node.getLocation();
        node.setLocation(newPos);
    }

    public String getLabel() {
        return "Move Node";
    }

    public void redo() {
        this.node.setLocation(newPos);
    }

    public void undo() {
        this.node.setLocation(oldPos);
    }
}