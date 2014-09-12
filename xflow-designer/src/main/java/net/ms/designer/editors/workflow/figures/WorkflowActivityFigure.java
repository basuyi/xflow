package net.ms.designer.editors.workflow.figures;

import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Image;

/**
 * @author liuchunxia
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//implements HandleBounds
public class WorkflowActivityFigure extends NodeFigure implements HandleBounds
{
	public WorkflowActivityFigure(String fieldLabel, Image typeIcon) 
	{
//	    setBorder(new CircuitBorder());
		ImageFigure im = new ImageFigure(typeIcon);
		ToolbarLayout layout=new ToolbarLayout();
		layout.setVertical(true);
		setLayoutManager(layout);
		add(im);
		add(label);
		setText(fieldLabel);	
	}
	
	public Rectangle getHandleBounds() 
	{
	    return getBounds().getCropped(new Insets(0,0,0,0));
	}
	
	public Label getLabel()
	{
		return label;
	}
//	protected void paintFigure(Graphics graphics) {
//	    super.paintFigure(graphics);
//		Rectangle rect = getBounds().getCopy();
//		rect.crop(new Insets(2,0,2,0));
//		graphics.fillRectangle(rect);
//	}
}