/*
 * 创建日期 2006-9-4
 *
 * @author lili
 */
package net.ms.designer.editors.componentdetail.figures;

import net.ms.designer.editors.enumcomponentdetail.tools.ImageProvider;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;


public class CommonFigure extends Figure
{
	
	protected Label label = new Label();
	boolean selected;
	boolean hasFocus;

	public CommonFigure(String fieldLabel, Image typeIcon) 
	{
		
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);
//		setBorder(new LineBorder());

		
		label.setTextAlignment(PositionConstants.LEFT);
		label.setIcon(typeIcon);
//		label.setVisible(false);
		setLayoutManager(new FlowLayout());
		add(label);
		
		setLabelText(fieldLabel);
	}
	
	public void setLabelText(String newLabel)
	{
		label.setText(newLabel);
	}
	/**
     * Sets the selection state of this SimpleActivityLabel
     * @param b true will cause the label to appear selected
     */
    public void setSelected(boolean b) 
    {
    	selected = b;
    	repaint();
    }

    /**
     * Sets the focus state of this SimpleActivityLabel
     * @param b true will cause a focus rectangle to be drawn around the text of the Label
     */
    public void setFocus(boolean b) 
    {
    	hasFocus = b;
    	repaint();
    }
    
    protected void paintFigure(Graphics graphics) 
    {
//        this.setText(model.getFieldName());
    	if (selected) 
    	{
    		graphics.pushState();
    		graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
    		graphics.fillRectangle(getSelectionRectangle());
    		graphics.popState();
    		graphics.setForegroundColor(ColorConstants.white);
    	}
    	if (hasFocus) 
    	{
    		graphics.pushState();
    		graphics.setXORMode(true);
    		graphics.setForegroundColor(ColorConstants.menuBackgroundSelected);
    		graphics.setBackgroundColor(ColorConstants.white);
    		graphics.drawFocus(getSelectionRectangle().resize(-1, -1));
    		graphics.popState();
    	}
        super.paintFigure(graphics);
    }
    
    private Rectangle getSelectionRectangle() 
    {
    	Rectangle bounds = label.getTextBounds();
    	bounds.expand(new Insets(2,2,0,0));
    	translateToParent(bounds);
    	bounds.intersect(getBounds());
    	return bounds;
    }

}
