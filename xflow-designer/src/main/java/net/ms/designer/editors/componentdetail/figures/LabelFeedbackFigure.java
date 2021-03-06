package net.ms.designer.editors.componentdetail.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

public class LabelFeedbackFigure extends LabelFigure
{

	protected void paintFigure(Graphics graphics) 
	{
		Rectangle rect = getBounds().getCopy();

		graphics.setXORMode(true);
		graphics.setForegroundColor(ColorConstants.white);
		graphics.setBackgroundColor(ColorConstants.black);

		graphics.translate(getLocation());

		PointList outline = new PointList();

		outline.addPoint(0, 0);
		outline.addPoint(rect.width - getCornerSize(), 0);
		outline.addPoint(rect.width - 1, getCornerSize());
		outline.addPoint(rect.width - 1, rect.height - 1);
		outline.addPoint(0, rect.height - 1);

		graphics.fillPolygon(outline);

		// draw the inner outline
		PointList innerLine = new PointList();

		innerLine.addPoint(rect.width - getCornerSize() - 1, 0);
		innerLine.addPoint(rect.width - getCornerSize() - 1, getCornerSize());
		innerLine.addPoint(rect.width - 1, getCornerSize());
		innerLine.addPoint(rect.width - getCornerSize() - 1, 0);
		innerLine.addPoint(0, 0);
		innerLine.addPoint(0, rect.height - 1);
		innerLine.addPoint(rect.width - 1, rect.height - 1);
		innerLine.addPoint(rect.width - 1, getCornerSize());

		graphics.drawPolygon(innerLine);

		graphics.drawLine(rect.width - getCornerSize() - 1, 0, rect.width - 1,
				getCornerSize());

		graphics.translate(getLocation().getNegated());
	}

}
