package net.ms.designer.editors.componentdetail.figures;

import net.ms.designer.editors.enumcomponentdetail.tools.GraphicsUtil;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

;

public class ContentFigure extends Figure
{

	public static Color classColor = new Color(null, 255, 255, 206);

	public ContentFigure() 
	{
		
	          //g.setBackgroundColor(ColorConstants.red);
	          //g.fillRectangle(getBounds());
	      
		FillLayout layout = new FillLayout();
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		layout.setStretchMinorAxis(false);
		setLayoutManager(layout);
//		setBorder(new CompartmentFigureBorder());
	     
	}

	public class CompartmentFigureBorder extends AbstractBorder 
	{
		public Insets getInsets(IFigure figure) {
			return new Insets(1, 0, 0, 0);
		}

		public void paint(IFigure figure, Graphics graphics, Insets insets) 
		{
			graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(),
					tempRect.getTopRight());
		}
	}

}
