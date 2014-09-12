package net.ms.designer.editors.componentdetail.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 
 * @author lili
 * TODO defind the node's figure of WorkFlow
 *
 */
public class NodeFigure extends RectangleFigure 
{

    private String name;

    private RectangleFigure rectangleFigure;

    private Label label;
    
    ContentFigure fields = new ContentFigure();
    
    /**
     * TODO construct the NodeFigure()
     *
     */
    public NodeFigure() 
    {
        this.rectangleFigure = new RectangleFigure();
        this.rectangleFigure.setBackgroundColor(ContentFigure.classColor);
        this.label = new Label();
        this.add(rectangleFigure);
        this.add(label);
        this.add(fields);
    	
    }
   
    /**
     * 
     * @return return the Text contant of Lable
     */
    public String getText() 
    {
        return this.label.getText();
    }

    public Rectangle getTextBounds() 
    {
        return this.label.getTextBounds();
    }

    /**
     * 
     * @param name
     *        name to be set   
     */
    public void setName(String name) 
    {
        this.name = name;
        this.label.setText(name);
        this.repaint();
    }

    public void setBounds(Rectangle rect) 
    {
        super.setBounds(rect);
        this.rectangleFigure.setBounds(rect);
        this.label.setBounds(rect);
    }

}
