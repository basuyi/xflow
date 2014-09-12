package net.ms.designer.editors.componentdetail.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.component.editparts.NodeCellEditorLocator;
import net.ms.designer.editors.component.editparts.NodeDirectEditManager;
import net.ms.designer.editors.component.figures.NodeFigure;
import net.ms.designer.editors.component.models.BizComponent;
import net.ms.designer.editors.component.models.EnumComponent;
import net.ms.designer.editors.component.models.WorkFlow;
import net.ms.designer.editors.component.ui.ComponentEditor;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.policies.PropertyEditPolicy;
import net.ms.designer.editors.workflow.ui.WorkflowEditor;
import net.ms.designer.ui.view.MsTreeView;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.UIPlugin;



/**
 * @author lili
 * @version 1.1.0
 * Provides support for EditPartBase.
 */
public class DBEditPartBase extends AbstractGraphicalEditPart implements PropertyChangeListener{

	
    /* (non-Javadoc)
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if(getModel() != null && getModel() instanceof Element){
            ((Element)getModel()).addPropertyChangeListener(this);
        }
        super.activate();
    }
    
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if(getModel() != null && getModel() instanceof Element){
            ((Element)getModel()).removePropertyChangeListener(this);
        }
        super.deactivate();
    }
    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        // TODO Auto-generated method stub
    	
        
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    protected List getModelChildren() {
        // TODO Auto-generated method stub
        if(getModel() instanceof Container){
            return ((Container)getModel()).getChildren();
        }
        return super.getModelChildren();
    }

    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent evt) {
//       String pName = evt.getPropertyName();
//       if(pName.equals(Container.ID_FIELD_LABEL)){
//           this.refreshVisuals();
//       }
//       if(pName.equals(DBBase.PRO_CHILD)){
//           this.refreshChildren();
//           this.refreshVisuals();
//       }
    }
}
