package net.ms.designer.core;

import java.io.Serializable;

import org.eclipse.gef.palette.PaletteRoot;

public class PaletteRootFactory implements Serializable
{
	public PaletteRoot getPaletteFactory(String type)
	{
		if(type.equals("package"))
		{
			return net.ms.designer.editors.packages.tools.PaletteFactory.createPalette();
		}
		else if(type.equals("component"))
		{
			return net.ms.designer.editors.component.tools.PaletteFactory.createPalette();
		}
		else if(type.equals("componentdetail"))
		{
			return net.ms.designer.editors.componentdetail.tools.PaletteFactory.createPalette();
		}
		else if(type.equals("enumdetail"))
		{
			return net.ms.designer.editors.enumcomponentdetail.palette.PaletteFactory.INSTANCE().createPaletteRoot();
		}
		else if(type.equals("workflow"))
		{
			return net.ms.designer.editors.workflow.tools.WorkflowPaletteFactory.createPalette();
		}
		else if(type.equals("subflow"))
		{
			return net.ms.designer.editors.workflow.tools.WorkflowPaletteFactory.createPalette();
		}
		else
			return null;
	}
}