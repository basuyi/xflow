package net.ms.designer.editors.enumcomponentdetail.editpart;

import net.ms.designer.editors.enumcomponentdetail.editpolicy.ContainerLayoutEditPolicy;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;


public class ContainerEditPart extends BaseEditPart 
{
    protected IFigure createFigure() 
    {
        IFigure figure = new FreeformLayer()
        {
			public void paintFigure(Graphics g)
			{
				super.paintFigure(g);
				g.setBackgroundColor(new Color(null,253,250,240));
				g.fillRectangle(getBounds());
			}
        };
        figure.setLayoutManager(new FreeformLayout());
        return figure;
    }

    protected void createEditPolicies() 
    {
        this.installEditPolicy(EditPolicy.LAYOUT_ROLE,new ContainerLayoutEditPolicy());
    }
}
