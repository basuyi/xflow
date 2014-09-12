package net.ms.designer.editors.componentdetail.editparts;

import java.util.List;
import java.util.Vector;

import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.componentdetail.models.AutoNumField;
import net.ms.designer.editors.componentdetail.models.AutoPField;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.Label;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.models.Wire;
import net.ms.designer.editors.componentdetail.ui.CompDetailEditor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.UIPlugin;


/**
 * @author lili
 * @version 1.1.0
 * defind the GraphicalPartFactory ,it can excute creatEditPart and do the auto connet wire.
 */
public class GraphicalPartFactory implements EditPartFactory 
{

	public EditPart createEditPart(EditPart context, Object model) 
	{
		EditPart part = null;
		if((model instanceof ChildTable) && !(model instanceof ComponentTable))
		{	
			part = new CompTableEditPart();
		}
		if (model instanceof net.ms.designer.editors.componentdetail.models.Table)
		{
			part = new CompTableEditPart();
		}
		else if(model instanceof FlowField)
		{
			part = new FlowEditPart();
		}
		else if (model instanceof Wire)
			part = new WireEditPart();
		else if (model instanceof Label)
			part = new LabelEditPart();
		else if (model instanceof CommonField)
		{
			part = new CommonEditPart();
		}
		else if (model instanceof net.ms.designer.editors.componentdetail.models.Container) 
		{
			net.ms.designer.editors.componentdetail.models.Container ct = (net.ms.designer.editors.componentdetail.models.Container) model;
			if (!ct.hasMainTable()) 
			{
				ComponentTable t = new ComponentTable();
				t.setId(ct.getId());
				t.setName(ct.getName());
				t.setIName(t.getName());
				AutoNumField at = new AutoNumField();
				at.setName(ct.getName().toLowerCase() + "id");
				at.setIName(at.getName());
				ChildTable cd = new ChildTable();
				cd.setMainTableName(ct.getName().toLowerCase() + "id");
				ct.addChild(t);	
				t.addChild(at);
							
				ct.setMainTable(true);
			}
			part = new net.ms.designer.editors.componentdetail.editparts.DiagramEditPart();
			part.setModel(ct);
			return part;
		}
		if (context instanceof net.ms.designer.editors.componentdetail.editparts.DiagramEditPart
				&& (model instanceof ChildTable )&& !(model instanceof ComponentTable))
		{
			ChildTable ct = (ChildTable) model;

			boolean hasConnToMainTable = false;	//look out it has connet to the mainTable or not
			
			ComponentTable mainTable = ((net.ms.designer.editors.componentdetail.editparts.DiagramEditPart) context).getDiagram().getMainTable();
			mainTable.setHasChildTable(1);
		
			
			Vector conns = ct.getSourceConnections();
			for (int i = 0; i < conns.size(); i++) 
			{
//				((ChildTable)child).setChildID(i);
				Wire w = (Wire) conns.get(i);
				net.ms.designer.editors.componentdetail.models.Element target = (net.ms.designer.editors.componentdetail.models.Element)w.getTarget();
				if ((target instanceof ComponentTable) ||
				        !target.getName().equals(mainTable.getName())) 
				{
					hasConnToMainTable = true;
					break;
				}				
			}
			if (!hasConnToMainTable) 
			{			    
				Wire wire = new Wire();
				wire.setSource(ct);
				wire.setTarget(((net.ms.designer.editors.componentdetail.editparts.DiagramEditPart) context).getDiagram()
						.getMainTable());
				wire.attachSource();
				wire.attachTarget();
			}	
		}
		if (context instanceof net.ms.designer.editors.componentdetail.editparts.DiagramEditPart
			&&(model instanceof FlowField))
		{
			//connet to the mainTable to the mainTable
			//System.out.println("GraphicalPartFactory.createEditPart().if(context)");
			FlowField ff = (FlowField)model;

			boolean hasConnToMainTable1 = false;	//look out it has connet to the mainTable or not
			ComponentTable mainTable = ((net.ms.designer.editors.componentdetail.editparts.DiagramEditPart) context).getDiagram().getMainTable();
			mainTable.setFlowAssociated(1);
			Vector conns = ff.getSourceConnections();
			for (int i = 0; i < conns.size(); i++) 
			{
				Wire w = (Wire) conns.get(i);
				net.ms.designer.editors.componentdetail.models.Element target = (net.ms.designer.editors.componentdetail.models.Element)w.getTarget();
				if ((target instanceof ComponentTable) ||
				        !target.getName().equals(mainTable.getName())) 
				{
					hasConnToMainTable1 = true;
					break;
				}				
			}
			if (!hasConnToMainTable1) 
			{			    
				//System.out.println("flowField ffffffffffffffffff");
				Wire wire = new Wire();
				wire.setSource(ff);
				wire.setTarget(((net.ms.designer.editors.componentdetail.editparts.DiagramEditPart) context).getDiagram()
						.getMainTable());
				wire.attachSource();
				wire.attachTarget();
			}
		}
		part.setModel(model);
		return part;
	}
}
