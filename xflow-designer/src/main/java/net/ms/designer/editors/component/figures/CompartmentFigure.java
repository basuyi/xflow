package net.ms.designer.editors.component.figures;

import net.ms.designer.editors.workflow.figures.WorkflowActivityFigure;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Image;


//public class CompartmentFigure extends Figure 
//{
public class CompartmentFigure extends NodeFigure implements HandleBounds
{
	public static CompartmentFigure createCPFigure(String name, Image img) {
		return new CompartmentFigure(name, img);
	}
//	  public CompartmentFigure() 
//	  {
//	    ToolbarLayout layout = new ToolbarLayout();
//	    layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
//	    layout.setStretchMinorAxis(false);
//	    layout.setSpacing(2);
//	    setLayoutManager(layout);
//	    setBorder((Border) new CompartmentFigureBorder());
//	  }
//	  
//	  public class CompartmentFigureBorder extends AbstractBorder 
//	  {
//	    public Insets getInsets(IFigure figure) 
//	    {
//	      return new Insets(1,0,0,0);
//	    }
//	    public void paint(IFigure figure, Graphics graphics, Insets insets) 
//	    {
//	      graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(),
//	                        tempRect.getTopRight());
//	    }
//	  }
//	}

	//-----------lili start
	
	public CompartmentFigure(String fieldLabel, Image typeIcon) 
	{
//	    setBorder(new CircuitBorder());
		ImageFigure im = new ImageFigure(typeIcon);
		ToolbarLayout layout=new ToolbarLayout();
		layout.setVertical(true);
		setLayoutManager(layout);
		add(im);
		add(label);
		setText(fieldLabel);	
	}
	
	public Rectangle getHandleBounds() 
	{
	    return getBounds().getCropped(new Insets(0,0,0,0));
	}
	
	public Label getLabel()
	{
		return label;
	}
}
	//-----------lili end