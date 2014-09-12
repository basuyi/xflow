package net.ms.designer.editors.componentdetail.figures;

import net.ms.designer.editors.enumcomponentdetail.tools.GraphicsUtil;
import net.ms.designer.editors.enumcomponentdetail.tools.ImageProvider;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


public class ComponentTableFigure extends Figure 
{

//	ContentFigure fields = (ContentFigure) new Figure();
	ContentFigure fields = new ContentFigure();

	Label label = new Label();

	public ComponentTableFigure(String tableLabel) 
	{
		ToolbarLayout layout = new ToolbarLayout();
		layout.setSpacing(4);
		setLayoutManager(layout);

		setBorder(new LineBorder(ColorConstants.black, 1));
		setBackgroundColor(ColorConstants.white);
		
		setOpaque(true);

		label.setTextAlignment(PositionConstants.CENTER);
		label.setIcon(ImageProvider.TABLE_ICON.createImage());
		label.setText(tableLabel);

		
		add(label);
		add(fields);
	}

	public ComponentTableFigure() 
	{

	}

	/**
	 * @return
	 */
	public IFigure getFieldsPane() 
	{
		return fields;
	}

	/**
	 * @return 返回 label。
	 */
	public Label getLabel() 
	{
		return label;
	}
	
	protected void paintFigure(Graphics graphics) 
    {
        super.paintFigure(graphics);
        graphics.setForegroundColor(ColorConstants.gray);
        // 通过GraphicsUtil绘制渐变区域
        Point plusPoint = new Point(getLocation().x ,getLocation().y );
        Dimension plusDimension = new Dimension(getBounds().getSize().width,20);
        GraphicsUtil.paintPlusArea(graphics,plusPoint,plusDimension);
        Rectangle bounds = getBounds();
        graphics.drawRectangle(new Rectangle(bounds.x, bounds.y,
                bounds.width - 1, bounds.height - 1));
        int y = getBounds().y + getBorder().getInsets(this).bottom
                + this.label.getSize().height;
        graphics.drawLine(getBounds().x, y, getBounds().x + getBounds().width,
                y);
    }

}
