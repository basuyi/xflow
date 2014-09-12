/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.editparts;
 
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.packages.PackageImages;
import net.ms.designer.editors.packages.editpolicies.NodeDirectEditPolicy;
import net.ms.designer.editors.packages.editpolicies.NodeEditPolicy;
import net.ms.designer.editors.packages.figures.CompartmentFigure;
import net.ms.designer.editors.packages.figures.NodeFigure;
import net.ms.designer.editors.packages.models.Package;
import net.ms.designer.editors.packages.ui.PackageEditor;
import net.ms.designer.ui.view.MsTreeView;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.UIPlugin;


/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodePart extends BaseEditPart implements PropertyChangeListener, NodeEditPart 
{
	PackageEditor editor;
    protected DirectEditManager manager;
    IWorkbenchPage page = null;
    MsProject project;
    private static Image LOGIC_ICON = PackageImages.getImage(PackageImages.ENDNODE);
    
    public void performRequest(Request req) 
    {
    	page = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
    	editor = (PackageEditor)page.getActiveEditor();
    	
        if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) 
        {
            if (manager == null) 
            {
                NodeFigure figure = (NodeFigure) getFigure();
                manager = new NodeDirectEditManager(this, TextCellEditor.class, new NodeCellEditorLocator(figure));
            }
            manager.show();
        }
        if(req.getType().equals(RequestConstants.REQ_OPEN))
        {
        	MsContext context;
        	if(editor.getContext() != null)
        		context = editor.getContext();
        	else
        	{
        		context = new MsContext();
        		editor.setContext(context);
        	}
        	context.change(this , "package");
        }
    }

    public void propertyChange(PropertyChangeEvent evt) 
    {
        if (evt.getPropertyName().equals(Package.prop_Location))
            refreshVisuals();
        else if (evt.getPropertyName().equals(Package.prop_Name))
            refreshVisuals();
    }

//    protected IFigure createFigure() 
//    {
//        return new NodeFigure();
//    }
    
    //-------------lili start
    protected IFigure createFigure()
    {
    	return CompartmentFigure.createCPFigure(((Package) getModel())
				.getName(), LOGIC_ICON);
    	
    }
    //------------lili end

    protected void createEditPolicies() 
    {
    	super.createEditPolicies();
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new NodeDirectEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeEditPolicy());
        
//        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
    }

    public void activate() 
    {
        if (isActive()) 
        {
            return;
        }
        super.activate();
        ((Package) getModel()).addPropertyChangeListener(this);
    }

    public void deactivate() 
    {
        if (!isActive()) 
        {
            return;
        }
        super.deactivate();
        ((Package) getModel()).removePropertyChangeListener(this);
    }

    protected void refreshVisuals() 
    {
    	Package node = (Package) getModel();
        Point loc = node.getLocation();
//        Dimension size = new Dimension(100, 100);
        Dimension size = node.getSize();
        Rectangle rectangle = new Rectangle(loc, size);
        ((NodeFigure) this.getFigure()).setName(((Package) this.getModel()).getName());
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
        super.refreshVisuals();
    }

    //------------------------------------------------------------------------
    // Abstract methods from NodeEditPart

    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) 
    {
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request)
    {
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) 
    {
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) 
    {
        return new ChopboxAnchor(getFigure());
    }

//    protected List getModelSourceConnections() {
//        return ((Node) this.getModel()).getOutgoingConnections();
//    }
//
//    protected List getModelTargetConnections() {
//        return ((Node) this.getModel()).getIncomingConnections();
//    }

}