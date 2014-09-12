package net.ms.designer.editors.workflow.commands;

import java.util.List;

import net.ms.designer.core.MsProject;
import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.dialog.WorkflowBaseActivityPropertyDialog;
import net.ms.designer.editors.workflow.dialog.WorkflowPropertyDialog;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.UIPlugin;



public class WorkflowPropertyCommand extends Command
{
	Shell shell;
//	private Package obj;
	private WorkflowDiagram diagram;
	private WorkflowSubPart subpart;
	List paraList;
	
    
	public void execute()
	{
		if(this.subpart != null && this.subpart instanceof WorkflowDiagram)
		{
			WorkflowPropertyDialog wfDialog = new WorkflowPropertyDialog(shell,subpart,null);
	    	wfDialog.open();
		}
		if(this.subpart != null && this.subpart instanceof WorkflowBaseActivity)
		{
			paraList = diagram.getParaList();
//			if(paraList == null || paraList.size()<1)
//			{
//				ParameterEntire bean = new ParameterEntire();
//				bean.setParaName("bean");
//				//bean.setParaType(Constants.WF_PARA_TYPE_BEAN);
//				ParameterEntire entityid = new ParameterEntire();
//				entityid.setParaName("entityid");
//				//entityid.setParaType(Constants.WF_PARA_TYPE_ENTITYID);
//				ParameterEntire infor = new ParameterEntire();
//				infor.setParaName("infor");
//				//infor.setParaType(Constants.WF_PARA_TYPE_INFOR);
//				paraList.add(bean);
//				paraList.add(entityid);
//				paraList.add(infor);
//			}
			
			WorkflowBaseActivityPropertyDialog nodeDialog = new WorkflowBaseActivityPropertyDialog(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),diagram,subpart);
			nodeDialog.open();
		}
	}
	
	public WorkflowSubPart getSubpart()
	{
		return this.subpart;
	}
	public void setSubpart(WorkflowSubPart t)
	{
		this.subpart = t;
	}
	public WorkflowDiagram getDiagram()
	{
		return this.diagram;
	}
	public void setDiagram(WorkflowDiagram diagram)
	{
		this.diagram = diagram;
	}
}