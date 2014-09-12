package net.ms.designer.editors.workflow.editparts;

import net.ms.designer.editors.workflow.figures.NodeFigure;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Text;




public class NodeDirectEditManager extends DirectEditManager{

	Font scaledFont;

    protected VerifyListener verifyListener;

    protected NodeFigure nodeFigure;
	
	public NodeDirectEditManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator) {
		super(source, editorType, locator);
		this.nodeFigure = (NodeFigure)source.getFigure();
		// TODO Auto-generated constructor stub
	}

	protected void initCellEditor() {
		// TODO Auto-generated method stub
		 Text text = (Text) getCellEditor().getControl();
	        getCellEditor().setValue(((WorkflowSubPart) getEditPart().getModel()).getName());
	        IFigure figure = ((GraphicalEditPart) getEditPart()).getFigure();
	        scaledFont = figure.getFont();
	        FontData data = scaledFont.getFontData()[0];
	        Dimension fontSize = new Dimension(0, data.getHeight());
	        nodeFigure.translateToAbsolute(fontSize);
	        data.setHeight(fontSize.height);
	        scaledFont = new Font(null, data);

	        text.setFont(scaledFont);
	        text.selectAll();
	}

}
