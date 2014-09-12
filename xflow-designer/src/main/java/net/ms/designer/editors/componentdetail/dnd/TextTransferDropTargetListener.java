package net.ms.designer.editors.componentdetail.dnd;

import net.ms.designer.editors.componentdetail.models.MsElementFactory;
import net.ms.designer.editors.componentdetail.models.ComponentTable;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;




public class TextTransferDropTargetListener extends
		AbstractTransferDropTargetListener 
{
//	private ElementFactory factory = new ElementFactory(Table.class);

	private MsElementFactory factory = new MsElementFactory(ComponentTable.class);
	public TextTransferDropTargetListener(EditPartViewer viewer, Transfer xfer) {
		super(viewer, xfer);
	}

	protected void handleDragOperationChanged() 
	{
		getCurrentEvent().detail = DND.DROP_COPY;
		super.handleDragOperationChanged();
	}

	protected Request createTargetRequest() 
	{
		CreateRequest request = new CreateRequest();
		request.setFactory(factory);
		return request;
	}

	protected void updateTargetRequest() 
	{
		((CreateRequest) getTargetRequest()).setLocation(getDropLocation());
	}

	protected final CreateRequest getCreateRequest() 
	{
		return ((CreateRequest) getTargetRequest());
	}

	protected void handleDragOver() 
	{
		getCurrentEvent().detail = DND.DROP_COPY | DND.DROP_MOVE;
		getCurrentEvent().feedback = DND.FEEDBACK_SCROLL | DND.FEEDBACK_EXPAND;
		super.handleDragOver();
	}

	protected void handleDrop() 
	{
		super.handleDrop();
		selectAddedObject();
	}

	private void selectAddedObject() 
	{
		Object model = getCreateRequest().getNewObject();
		if (model == null)
			return;
		EditPartViewer viewer = getViewer();
		viewer.getControl().forceFocus();
		Object editpart = viewer.getEditPartRegistry().get(model);
		if (editpart instanceof EditPart) 
		{
			//Force a layout first.
			getViewer().flush();
			viewer.select((EditPart) editpart);
		}
	}
}