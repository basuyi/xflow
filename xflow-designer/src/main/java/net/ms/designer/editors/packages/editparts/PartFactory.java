/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.editparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.ms.designer.editors.componentdetail.editparts.CommonEditPart;
import net.ms.designer.editors.componentdetail.editparts.CompTableEditPart;
import net.ms.designer.editors.componentdetail.editparts.DiagramEditPart;
import net.ms.designer.editors.componentdetail.editparts.FlowEditPart;
import net.ms.designer.editors.componentdetail.editparts.LabelEditPart;
import net.ms.designer.editors.componentdetail.editparts.WireEditPart;
import net.ms.designer.editors.componentdetail.models.AutoNumField;
import net.ms.designer.editors.componentdetail.models.AutoPField;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.Label;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.models.Wire;
import net.ms.designer.editors.packages.models.PackageDiagram;
import net.ms.designer.editors.workflow.editparts.WorkflowBaseActivityEditPart;
import net.ms.designer.editors.workflow.editparts.WorkflowDiagramEditPart;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

public class PartFactory implements EditPartFactory 
{
    public EditPart createEditPart(EditPart context, Object model) 
    {
        EditPart part = null;
        if (model instanceof PackageDiagram)
            part = new DiagramPart();
        else if (model instanceof net.ms.designer.editors.packages.models.Package)
        	part = new net.ms.designer.editors.packages.editparts.NodePart();
        else if (model instanceof net.ms.designer.editors.component.models.CompDiagram)
        	part = new net.ms.designer.editors.component.editparts.DiagramPart();
        else if (model instanceof net.ms.designer.editors.component.models.Component)
        	part = new net.ms.designer.editors.component.editparts.NodePart();
        else if (model instanceof net.ms.designer.editors.enumcomponentdetail.model.Container)
        	part = new net.ms.designer.editors.enumcomponentdetail.editpart.ContainerEditPart();
        else if (model instanceof net.ms.designer.editors.enumcomponentdetail.model.Table)
        	part = new net.ms.designer.editors.enumcomponentdetail.editpart.TableEditPart();
        else if (model instanceof net.ms.designer.editors.enumcomponentdetail.model.ValueField)
        	part = new net.ms.designer.editors.enumcomponentdetail.editpart.ValueFieldEditPart();
        else if((model instanceof ChildTable) && !(model instanceof ComponentTable))
		{	
			part = new CompTableEditPart();
		}
        else if (model instanceof net.ms.designer.editors.componentdetail.models.Table)
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
			
			part = new net.ms.designer.editors.componentdetail.editparts.DiagramEditPart();
			part.setModel(ct);
			return part;
		}

        if (context instanceof DiagramEditPart
				&& (model instanceof ChildTable ))
		{
			//-------------lili 1115 start
			List list1 = new ArrayList();
			List list = context.getChildren();
			ChildTable ct = (ChildTable) model;
			for(int j = 0 ; j < list.size() ; j++)
			{
			
					if(((EditPart)list.get(j)).getSelected() == 2 )
					{
						if(!((Element)((EditPart)list.get(j)).getModel() instanceof FlowField))
						{
							list1.add((Table)((CompTableEditPart)list.get(j)).getModel());
							String newName = ((Table)((CompTableEditPart)list.get(j)).getModel()).getName();
							//System.out.println(((Table)((CompTableEditPart)list.get(j)).getModel()).getName());
							 Table conctedTable = ((Table)((CompTableEditPart)list.get(j)).getModel());
							
	//						----------lili 1120 start
								AutoPField ap = new AutoPField();
								AutoNumField au = new AutoNumField();
								ap.setName(newName.toLowerCase()+"id");
								au.setName(ct.getName().toLowerCase()+"id");
								ct.addChild(au);
								//ct.addChild(ap);
								
								//-----------lili 1120 end -you wen ti
							 if(conctedTable instanceof ComponentTable)
							 {
								 ComponentTable mainTable = (ComponentTable)conctedTable;
									mainTable.setHasChildTable(1);
							 }
							 conctedTable.getSubTabel().add(ct);
							boolean hasConnToTable = false;
							
							Vector conns = ct.getSourceConnections();
							for(int i = 0; i < conns.size(); i++)
							{
								Wire w = (Wire) conns.get(i);
								Element target = (Element )w.getTarget();
								
								if ((target instanceof ChildTable) &&
								        target.getName().equals(((Table)((CompTableEditPart)list.get(j)).getModel()).getName()))
								        {
									hasConnToTable = true;
									break;
								}	
							}
//								if (!hasConnToTable)
//								{			    
//									Wire wire = new Wire();
//									wire.setSource(ct);
//									wire.setTarget(((Table)((CompTableEditPart)list.get(j)).getModel()));
//									wire.attachSource();
//									wire.attachTarget();
//								
//									
//								}	
								((net.ms.designer.editors.componentdetail.models.Container)context.getModel()).flag = 0;
							}
			
					}
					}
			if(list1.size()==0 && !(model instanceof ComponentTable))
			{
				boolean hasConnToMainTable = false;	//look out it has connet to the mainTable or not		
				ComponentTable mainTable = ((DiagramEditPart) context).getDiagram().getMainTable();
				mainTable.setHasChildTable(1);

//				----------lili 1120 start
				if(((net.ms.designer.editors.componentdetail.models.Container)context.getModel()).flag == 1)
				{
						AutoPField ap = new AutoPField();
						AutoNumField au = new AutoNumField();
						ap.setName(mainTable.getName().toLowerCase()+"id");
						au.setName(ct.getName().toLowerCase()+"id");
						ct.addChild(au);
						//ct.addChild(ap);
						mainTable.getSubTabel().add(ct);
					((net.ms.designer.editors.componentdetail.models.Container)context.getModel()).flag = 0;
				}

				//-----------lili 1120 end
						Vector conns = ct.getSourceConnections();
						for (int i = 0; i < conns.size(); i++) {
							Wire w = (Wire) conns.get(i);
							Element target = (Element )w.getTarget();
							if ((target instanceof ComponentTable) ||
							        !target.getName().equals(mainTable.getName())) {
								hasConnToMainTable = true;
								break;
							}				
						}
//						if (!hasConnToMainTable) {			    
//							Wire wire = new Wire();
//							wire.setSource(ct);
//							wire.setTarget(((DiagramEditPart) context).getDiagram()
//									.getMainTable());
//							wire.attachSource();
//							wire.attachTarget();
//						}	
				}			
			}

		if (context instanceof net.ms.designer.editors.componentdetail.editparts.DiagramEditPart
			&&(model instanceof FlowField))
		{
			//connet to the mainTable to the mainTable
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
				Wire wire = new Wire();
				wire.setSource(ff);
				wire.setTarget(((net.ms.designer.editors.componentdetail.editparts.DiagramEditPart) context).getDiagram()
						.getMainTable());
				wire.attachSource();
				wire.attachTarget();
			}
		}
        //
		//end
		//
		if (model instanceof WorkflowDiagram)
		{
			part = new WorkflowDiagramEditPart();
        }
        if (model instanceof net.ms.designer.editors.workflow.models.Wire) 
        {
            part = new net.ms.designer.editors.workflow.editparts.WireEditPart();
        }
        if (model instanceof WorkflowBaseActivity)
        {
            part = new WorkflowBaseActivityEditPart();
        }
		
        part.setModel(model);
        return part;
    }
}