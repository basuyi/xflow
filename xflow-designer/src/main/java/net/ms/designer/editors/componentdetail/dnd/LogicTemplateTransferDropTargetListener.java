package net.ms.designer.editors.componentdetail.dnd;

import net.ms.designer.editors.componentdetail.models.MsElementFactory;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.Table;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.dnd.DND;


public class LogicTemplateTransferDropTargetListener extends
		TemplateTransferDropTargetListener 
{
	protected void handleDragOver() 
	{
		org.eclipse.swt.graphics.Point p = getViewer().getControl().toControl(getCurrentEvent().x,
				getCurrentEvent().y);
	    Point point = new Point();
		point.x = p.x;
		point.y = p.y;

		EditPart ep = this.getViewer().findObjectAt(point);
		Object model = getCreateRequest().getNewObject();
		if (ep.getModel() instanceof Table && model instanceof ChildTable && model instanceof FlowField ) 
		{
			//表不能嵌套
			getCurrentEvent().detail = DND.DROP_NONE;
		} 
		else if (!(ep.getModel() instanceof Table) && !(model instanceof Table)
				&& ((model instanceof CommonField) )) 
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

//	protected CreationFactory getFactory(Object template) 
//	{
//		return new ElementFactory((Class)template);
//	}
	
	protected CreationFactory getFactory(Object template) {
//		if (template instanceof String)
//			return new ElementFactory((String) template);
			return new MsElementFactory((Class)template);
//		return null;
	}

}