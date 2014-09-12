/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.component.figures;

import net.ms.designer.editors.component.models.BizComponent;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;


//public class NodeFigure extends RectangleFigure 
//{
////	color variable
//    public static Color bizColor = new Color(null,255,255,206);
//    public static Color enumColor = new Color(null,255,206,0);
//    
//    private String name;
//    private Label label;
//
//    CompartmentFigure contentFigure = new CompartmentFigure();
//    
//    public NodeFigure(Object type) 
//    {
//    	ToolbarLayout layout = new ToolbarLayout();//the layout in the Figure
//    	setLayoutManager(layout);
//    	setBorder(new LineBorder(ColorConstants.black,1));
//    	if(type instanceof BizComponent)
//    		setBackgroundColor(bizColor);
//    	else
//    		setBackgroundColor(enumColor);
//        setOpaque(true);
////        
//        this.label = new Label();
//        label.setText("Node1");
////      add the label and the CompartmentFigure to this main figure
//        this.add(label);
//        this.add(contentFigure);
//    }
//    
//    public String getText() 
//    {
//        return this.label.getText();
//    }
//
//    public Rectangle getTextBounds() 
//    {
//        return this.label.getTextBounds();
//    }
//
//    public void setName(String name) 
//    {
//        this.name = name;
//        this.label.setText(name);        
//        this.repaint();
//    }
//
//    public CompartmentFigure getContentFigure() 
//    {
//        return contentFigure;
//    }  
//}

//---------------lili  start

public class NodeFigure extends Figure
{
	protected Label label = new Label();
	protected String name = "name";
//	private RectangleFigure rectangleFigure;
	public void setText(String text) 
	{
		label.setText(text);
		this.repaint();
	}
	
	public String getText()
	{
		return label.getText();
	}
	
	public Rectangle getTextBounds() 
	{
		return this.label.getTextBounds();
	}
	
	public void setBounds(Rectangle rect) 
	{
		super.setBounds(rect);
//		this.label.setBounds(rect);
	}
	
//	public Rectangle getBounds(){
//		return this.label.getBounds();
//	}
	
	public void setName(String name) 
	{
		this.name = name;
		this.label.setText(name);
		this.repaint();
	}
	
	public NodeFigure() {
//    	//System.out.println("com.example.figures.NodeFigure.NodeFigure()");
//        this.rectangleFigure = new RectangleFigure();
		
		
        this.label = new Label();
//        this.add(rectangleFigure);
        this.add(label);
    }

}

//--------------lili end