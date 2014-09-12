package net.ms.designer.editors.componentdetail.commands;

import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ConfigDialog;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.Element;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.UIPlugin;


public class PropertyCommand extends Command{

		Shell shell;
		protected Element obj;
		protected CommonField comf;
		protected Container cot;
		protected String change;	
		public void execute()
		{
			change = "1";
			ConfigDialog dialog = new ConfigDialog(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), obj, cot, comf,change);
//			dialog.create(obj);
			dialog.open();
		}
		
		public Element getCopyObject()
		{
			return obj;
		}
		public void setCopyObject(Element t)
		{
			this.obj = t;
		}
		public CommonField getCopyObject1()
		{
			return comf;
		}
		public void setCopyObject(CommonField c)
		{
			this.comf = c;
		}
		public Container getContainer()
		{
			return cot;
		}
		public void setContainer(Container co)
		{
			this.cot = co;
		}
		public String getChange()
		{
			return this.change;
		}
		public void setChange(String change)
		{
			this.change = change;
		}

}
