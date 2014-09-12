/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.componentdetail.tools;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.componentdetail.ComponentdetailPlugin;
import net.ms.designer.editors.componentdetail.models.AutoNumField;
import net.ms.designer.editors.componentdetail.models.AutoPField;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.DateField;
import net.ms.designer.editors.componentdetail.models.EnumField;
import net.ms.designer.editors.componentdetail.models.FloatField;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.IntegerField;
import net.ms.designer.editors.componentdetail.models.LookupField;
import net.ms.designer.editors.componentdetail.models.StringField;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.WorkflowImages;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * @author lili
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PaletteFactory 
{
	public static PaletteRoot createPalette()
	{    
		PaletteRoot paletteRoot = new PaletteRoot();
		paletteRoot.add(createControlGroup(paletteRoot));
		paletteRoot.add(createComponentsDrawer());
		paletteRoot.add(createChildTableDrawer());
		paletteRoot.add(createFlowDrawer());
      return paletteRoot;
  } 
	
	private static PaletteContainer createControlGroup(PaletteRoot root) 
	{
		PaletteGroup controlGroup = new PaletteGroup("Control Group");

		List entries = new ArrayList();
		ToolEntry tool = new SelectionToolEntry();
		entries.add(tool);
		ToolEntry con_tool = new ConnectionCreationToolEntry(Messages.getString("WorkflowPaletteFactory.wire"), Messages.getString("WorkflowPaletteFactory.wire_desc"),null, null, null);
		entries.add(con_tool);
		root.setDefaultEntry(tool);
		controlGroup.addAll(entries);
		return controlGroup;
	}

	private static PaletteContainer createComponentsDrawer() 
	{

		PaletteDrawer drawer = new PaletteDrawer("Components");

		List entries = new ArrayList();
		ToolEntry tool = new CombinedTemplateCreationEntry("String", null, StringField.class,
				new SimpleFactory(StringField.class), AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner","icons/String16.gif"), null);
		entries.add(tool);
		ToolEntry tool1 = new CombinedTemplateCreationEntry("Float", null, FloatField.class,
				new SimpleFactory(FloatField.class), AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner","icons/Float16.gif"), null);
		entries.add(tool1);
		ToolEntry tool2 = new CombinedTemplateCreationEntry("Date", null, DateField.class,
				new SimpleFactory(DateField.class), AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner","icons/Date16.gif"), null);
		entries.add(tool2);
		ToolEntry tool4 = new CombinedTemplateCreationEntry("Integer", null, IntegerField.class,
				new SimpleFactory(IntegerField.class), AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner","icons/Integer16.gif"), null);
		entries.add(tool4);
		ToolEntry tool5 = new CombinedTemplateCreationEntry("Enum", null, EnumField.class,
				new SimpleFactory(EnumField.class), AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner","icons/Boolean16.gif"), null);
		entries.add(tool5);
		ToolEntry tool6 = new CombinedTemplateCreationEntry("Lookup", null, LookupField.class,
				new SimpleFactory(LookupField.class), AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner","icons/Lookup16.gif"), null);
		entries.add(tool6);
//		ToolEntry tool7 = new CombinedTemplateCreationEntry("AutoNum", null, AutoNumField.class,
//				new SimpleFactory(AutoNumField.class), AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner","icons/Boolean16.gif"), null);
//		entries.add(tool7);
		
		drawer.addAll(entries);
		return drawer;
	}
	
	private static PaletteContainer createFlowDrawer()
	{
		PaletteDrawer drawer = new PaletteDrawer("WorkFlow");
		List entries = new ArrayList();
		ToolEntry tool = new CombinedTemplateCreationEntry("WorkFlow", null, FlowField.class,
				new SimpleFactory(FlowField.class), WorkflowImages.getImageDescriptor(WorkflowImages.SUBFLOW_16), null); 
		entries.add(tool);
		
		drawer.addAll(entries);
		return drawer;
	}
	
	private static PaletteContainer createChildTableDrawer()
	{
		PaletteDrawer drawer = new PaletteDrawer("ChildTable");
		List entries = new ArrayList();
		ToolEntry tool = new CombinedTemplateCreationEntry("ChildTable", null, ChildTable.class,
				new SimpleFactory(ChildTable.class), AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner","icons/ChildTable16.gif"), null);
	
		entries.add(tool);
		drawer.addAll(entries);
		return drawer;
	}
	
}