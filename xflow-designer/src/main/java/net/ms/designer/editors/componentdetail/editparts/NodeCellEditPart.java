package net.ms.designer.editors.componentdetail.editparts;

import net.ms.designer.editors.componentdetail.figures.NodeFigure;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;



public class NodeCellEditPart implements CellEditorLocator {


	 private NodeFigure nodeFigure;

	    public NodeCellEditPart(NodeFigure nodeFigure) {
	        this.nodeFigure = nodeFigure;
	    }

	    /**
	     * @see CellEditorLocator#relocate(org.eclipse.jface.viewers.CellEditor)
	     */
	    public void relocate(CellEditor celleditor) {
	        Text text = (Text) celleditor.getControl();
	        Point pref = text.computeSize(SWT.DEFAULT, SWT.DEFAULT);
	        Rectangle rect = this.nodeFigure.getTextBounds();
	        text.setBounds(rect.x - 1, rect.y - 1, pref.x + 1, pref.y + 1);
	    }

}
