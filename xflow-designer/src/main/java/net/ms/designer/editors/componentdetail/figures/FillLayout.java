package net.ms.designer.editors.componentdetail.figures;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class FillLayout extends ToolbarLayout
{
	public void layout(IFigure parent) 
	{
		List children = parent.getChildren();
		int numChildren = children.size();
		Rectangle clientArea = transposer.t(parent.getClientArea());
		int x = clientArea.x;
		int y = clientArea.y;
		for (int i = 0; i < numChildren; i++) 
		{
			IFigure child = (IFigure) children.get(i);
			
			Dimension prefdim = child.getPreferredSize(-1,-1);
			
			Rectangle newBounds = new Rectangle(x, y, clientArea.width, prefdim.height);

			child.setBounds(transposer.t(newBounds));
			y += newBounds.height + spacing;
		}

	}
}
