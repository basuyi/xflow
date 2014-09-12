package net.ms.designer.editors.workflow.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;

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
