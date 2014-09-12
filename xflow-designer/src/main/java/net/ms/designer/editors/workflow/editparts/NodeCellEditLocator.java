package net.ms.designer.editors.workflow.editparts;

import net.ms.designer.editors.workflow.figures.NodeFigure;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

;

public class NodeCellEditLocator implements CellEditorLocator{
	
	private NodeFigure nodeFigure;

	public void relocate(CellEditor celleditor) {
		// TODO Auto-generated method stub
		Text text = (Text) celleditor.getControl();
        Point pref = text.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        Rectangle rect = this.nodeFigure.getTextBounds();
        text.setBounds(rect.x - 1, rect.y - 1, pref.x + 1, pref.y + 1);
	}
	
	public NodeCellEditLocator(NodeFigure nodeFigure) {
	    this.nodeFigure = nodeFigure;
	}

}
