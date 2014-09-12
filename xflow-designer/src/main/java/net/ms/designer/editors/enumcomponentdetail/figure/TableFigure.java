package net.ms.designer.editors.enumcomponentdetail.figure;

import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.tools.GraphicsUtil;
import net.ms.designer.editors.enumcomponentdetail.tools.ImageProvider;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;


public class TableFigure extends Figure 
{
    private Table model;

    Figure contentFigure = null;

    private Label tableNameLabel = null;

    public TableFigure(Table model) 
    {
        super();
        this.model = model;

        tableNameLabel = new Label();
        tableNameLabel.setText(model.getTableName());
        FontData fd = new FontData();

        fd.setHeight(10);
        fd.setName("Arial");
        fd.setStyle(SWT.BOLD);

        tableNameLabel.setFont(new Font(null, fd));
        tableNameLabel.setIcon(ImageProvider.TABLE_ICON.createImage());
        tableNameLabel.setLabelAlignment(PositionConstants.MIDDLE);

        // ValueField Container
        contentFigure = new Figure() 
        {
            protected void paintFigure(Graphics g) 
            {
                super.paintFigure(g);
                //g.setBackgroundColor(ColorConstants.red);
                //g.fillRectangle(getBounds());
            }

            public Dimension getPreferredSize(int wHint, int hHint) 
            {
                Dimension dimension = super.getPreferredSize(wHint, hHint);

                int w = Math.max(dimension.width, wHint);
                int h = Math.max(dimension.height, 30);

                return new Dimension(w, h);
            }
        };

        ToolbarLayout tableLayout = new ToolbarLayout();
        //the distance between the label and the content container
        tableLayout.setSpacing(4);
        tableLayout.setVertical(true);
        this.setBorder(new MarginBorder(8, 8, 8, 8));
        // use ToolbarLayout
        this.setLayoutManager(tableLayout);
        ToolbarLayout containerLayout = new ToolbarLayout();
        containerLayout.setMinorAlignment(ToolbarLayout.ALIGN_BOTTOMRIGHT);
        contentFigure.setLayoutManager(containerLayout);
        this.add(tableNameLabel);
        this.add(contentFigure);
        this.setOpaque(true);
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
                + this.tableNameLabel.getSize().height;
        graphics.drawLine(getBounds().x, y, getBounds().x + getBounds().width,
                y);
    }

    public void paint(Graphics graphics) 
    {
        // set the table name before painting
        this.tableNameLabel.setText(model.getTableName());
        super.paint(graphics);
    }

    public Dimension getPreferredSize(int wHint, int hHint) 
    {
        return super.getPreferredSize(wHint, hHint);
    }

    public Figure getContentFigure()
    {
        return contentFigure;
    }

    public void setContentFigure(Figure containerFigure) 
    {
        this.contentFigure = containerFigure;
    }
}