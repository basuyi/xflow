package net.ms.designer.editors.enumcomponentdetail.figure;

import net.ms.designer.editors.enumcomponentdetail.model.ValueField;
import net.ms.designer.editors.enumcomponentdetail.tools.ImageProvider;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;


public class ValueFieldFigure extends Label 
{
    private ValueField model;
    private boolean selected;
    private boolean hasFocus;
    
    public ValueFieldFigure(ValueField model)
    {
    	super();
        this.model = model;
        model.setFieldName(model.getFieldName());
          
        FontData fd = new FontData();
          
	  	fd.setHeight(8);
		fd.setName("Arial");
		fd.setStyle(SWT.BOLD);
        this.setIcon(ImageProvider.FIELD_ICON.createImage());
        this.setLabelAlignment(PositionConstants.LEFT);
        this.setFont(new Font(null,fd));
    }

    protected void paintFigure(Graphics graphics) 
    {
        this.setText(model.getFieldName());
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
    	Rectangle bounds = getTextBounds();
    	bounds.expand(new Insets(2,2,0,0));
    	translateToParent(bounds);
    	bounds.intersect(getBounds());
    	return bounds;
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
}