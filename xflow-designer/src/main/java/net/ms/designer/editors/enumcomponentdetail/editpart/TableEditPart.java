package net.ms.designer.editors.enumcomponentdetail.editpart;

import net.ms.designer.editors.enumcomponentdetail.editpolicy.TableFlowLayoutEditPolicy;
import net.ms.designer.editors.enumcomponentdetail.figure.TableFigure;
import net.ms.designer.editors.enumcomponentdetail.model.Table;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;


public class TableEditPart extends BaseEditPart 
{
    protected IFigure createFigure() 
    {
//    	IFigure f =new TableFigure((Table)this.getModel());
//    	f.set
        return new TableFigure((Table)this.getModel());
    }

    protected void createEditPolicies() 
    {
        super.createEditPolicies();
        this.installEditPolicy(EditPolicy.CONTAINER_ROLE,new TableFlowLayoutEditPolicy());
    }

    protected void refreshVisuals() 
    {
        super.refreshVisuals();
        //get the current size of th TableFigure,it can automatelly
        //compute under the toolbarlayout constraint
        Dimension size = this.getFigure().getPreferredSize();
        Point p = ((Table) getModel()).getLocation();
        ((GraphicalEditPart) this.getParent()).setLayoutConstraint(this, this
                .getFigure(),new Rectangle(p, size));
    }

    public IFigure getContentPane() 
    {
        return ((TableFigure)getFigure()).getContentFigure();
    }
}