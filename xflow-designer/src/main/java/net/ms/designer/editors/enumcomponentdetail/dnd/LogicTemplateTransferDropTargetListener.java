package net.ms.designer.editors.enumcomponentdetail.dnd;

import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.model.ValueField;
import net.ms.designer.editors.enumcomponentdetail.palette.EnumCreationFactory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.graphics.Point;


public class LogicTemplateTransferDropTargetListener extends
		TemplateTransferDropTargetListener 
{
	protected void handleDragOver() 
	{
		Point p = getViewer().getControl().toControl(getCurrentEvent().x,
				getCurrentEvent().y);
		org.eclipse.draw2d.geometry.Point point = new org.eclipse.draw2d.geometry.Point();
		point.x = p.x;
		point.y = p.y;

		EditPart ep = this.getViewer().findObjectAt(point);
		Object model = getCreateRequest().getNewObject();
		if (ep.getModel() instanceof Table && model instanceof Table) 
		{
			//表不能嵌套
			getCurrentEvent().detail = DND.DROP_NONE;
		} 
		else if (!(ep.getModel() instanceof Table) && !(model instanceof Table)
				&& ((model instanceof ValueField) )) 
		{
			//字段必须放置在表中
			getCurrentEvent().detail = DND.DROP_NONE;			
		}
		else
			super.handleDragOver();
	}

	public LogicTemplateTransferDropTargetListener(EditPartViewer viewer) 
	{
		super(viewer);
	}

	protected CreationFactory getFactory(Object template) 
	{
		return new EnumCreationFactory((Class)template);
	}

}