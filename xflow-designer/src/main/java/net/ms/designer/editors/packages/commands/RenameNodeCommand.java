/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.commands;

import net.ms.designer.editors.packages.models.Package;

import org.eclipse.gef.commands.Command;


/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RenameNodeCommand extends Command {

    private Package node;

    private String newName;

    private String oldName;

    public void setName(String name) {
        this.newName = name;
    }

    public void setNode(Package node) {
        this.node = node;
    }

    public void execute() {
        oldName = this.node.getName();
        this.node.setName(newName);
    }

    public void redo() {
        node.setName(newName);
    }

    public void undo() {
        node.setName(oldName);
    }

    public String getLabel() {
        return "Rename Node";
    }
}