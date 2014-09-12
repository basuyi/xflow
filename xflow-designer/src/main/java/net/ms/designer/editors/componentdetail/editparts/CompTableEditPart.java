
package net.ms.designer.editors.componentdetail.editparts;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.core.ITableContentProvider;
import net.ms.designer.editors.componentdetail.figures.ComponentTableFigure;
import net.ms.designer.editors.componentdetail.models.AutoNumField;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.ConfigDialog;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.Messages;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.policies.LogicFlowEditPolicy;
import net.ms.designer.editors.componentdetail.policies.PropertyEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.ui.internal.UIPlugin;


/**
 * @author lili
 * @version 1.1.0
 * @explain defind the CompTableEditPart,it can do something of componentTable
 */
public class CompTableEditPart extends ContainerEditPart implements ITableContentProvider{

	protected Element obj;
	protected CommonField comf;
	protected Container cot;
	protected String change;
	
	public void performRequest(Request req) 
    {
        if(req.getType().equals(RequestConstants.REQ_OPEN))
        {
        	obj = (Element)getModel();
        	cot = ((Container)this.getParent().getModel());
        	change = "1";
        	ConfigDialog dialog = new ConfigDialog(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), obj, cot, comf,change);
//			dialog.create(obj);
			dialog.open();
        }
    }
	
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		ComponentTableFigure f = null;
		if (getModel() instanceof Table) {
			//新建如果未改名时，获得它的类型
//			f = new ComponentTableFigure(getCompTable().getField_Type());
	
			f = new ComponentTableFigure(getCompTable().getName());
		}
		
		return f;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.gef.GraphicalEditPart#getContentPane()
	 */
	public IFigure getContentPane() {
		return ((ComponentTableFigure) getFigure()).getFieldsPane();
	}

	/**
	 * 
	 * @return componentTableFigure's Label
	 */
	public Label getTableLabel() {
		return ((ComponentTableFigure) getFigure()).getLabel();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
//		this.installEditPolicy(EditPolicy.LAYOUT_ROLE,new PropertyEditPolicy());
		super.createEditPolicies();
		//installEditPolicy(EditPolicy.NODE_ROLE, null);
		//installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
//		this.installEditPolicy(EditPolicy.LAYOUT_ROLE,new PropertyEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LogicFlowEditPolicy());
//		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
//				null);
	}

	/**
	 * @return the Table model
	 */
	public Table getCompTable() {
		return (Table) getModel();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		//改名时，（有问题）
		ComponentTableFigure g = null;
		if (getModel() instanceof ComponentTable) {
			getTableLabel().setText(getCompTable().getName());	
//		getTableLabel().setText(getCompTable().getField_Type());
		}else
			
		getTableLabel().setText(getCompTable().getName());
		super.refreshVisuals();
	}

	/*
	 *  (non-Javadoc)
	 * @see net.ms.designer.editors.componentdetail.core.ITableContentProvider#getTableContents()
	 */
	public String[][] getTableContents() {
		List result = new ArrayList();
		List childs = getCompTable().getChildren();
		for(int i=0;i<childs.size();i++){		    
			List objInfo = new ArrayList();
			Element obj = (Element)childs.get(i);
			objInfo.add(obj.getName());
			objInfo.add(obj.getField_Type());
	
//			objInfo.add(obj.getFieldLabel());
			int canQuery = 0;
			if(obj instanceof CommonField){
			    canQuery = ((CommonField)obj).getCanBeQuery();
			}
			if(canQuery==0)
				objInfo.add(Messages.getString("KDEEditors.Constant.False")); //$NON-NLS-1$
			else
				objInfo.add(Messages.getString("KDEEditors.Constant.True")); //$NON-NLS-1$
						

			result.add((String[])objInfo.toArray(new String[5]));
		}
		return (String[][])result.toArray(new String[result.size()][5]);
	}
}
