package net.ms.designer.editors.componentdetail.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

public class LabelFigure extends Figure
{

	protected TextFlow showText;

	private Label label;

	protected static int DEFAULT_CORNER_SIZE = 10;

	private int cornerSize;
	
	public void setShowText(String text) 
	{
		showText.setText(text);
		// repaint();
	}

	public String getShowText() 
	{
		return showText.getText();
	}

	/**
	 * Creates a new KCGLabelFigure with a default MarginBorder size of
	 * DEFAULT_CORNER_SIZE - 3 and a FlowPage containing a TextFlow with the
	 * style WORD_WRAP_SOFT.
	 */
	public LabelFigure() 
	{
		this(DEFAULT_CORNER_SIZE - 3);
	}

	public LabelFigure(int borderSize)
	{
		
	}
	protected void paintFigure(Graphics graphics) 
	{

		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		// fill the note
		PointList outline = new PointList();

		outline.addPoint(0, 0);
		outline.addPoint(rect.width - cornerSize, 0);
		outline.addPoint(rect.width - 1, cornerSize);
		outline.addPoint(rect.width - 1, rect.height - 1);
		outline.addPoint(0, rect.height - 1);

		graphics.fillPolygon(outline);

		// draw the inner outline
		PointList innerLine = new PointList();

		innerLine.addPoint(rect.width - cornerSize - 1, 0);
		innerLine.addPoint(rect.width - cornerSize - 1, cornerSize);
		innerLine.addPoint(rect.width - 1, cornerSize);
		innerLine.addPoint(rect.width - cornerSize - 1, 0);
		innerLine.addPoint(0, 0);
		innerLine.addPoint(0, rect.height - 1);
		innerLine.addPoint(rect.width - 1, rect.height - 1);
		innerLine.addPoint(rect.width - 1, cornerSize);

		graphics.drawPolygon(innerLine);

		graphics.translate(getLocation().getNegated());
	}

	

	/**
	 * @return 返回 cornerSize。
	 */
	public int getCornerSize() 
	{
		return cornerSize;
	}

	/**
	 * @param cornerSize
	 *            要设置的 cornerSize。
	 */
	public void setCornerSize(int cornerSize) 
	{
		this.cornerSize = cornerSize;
	}

}
