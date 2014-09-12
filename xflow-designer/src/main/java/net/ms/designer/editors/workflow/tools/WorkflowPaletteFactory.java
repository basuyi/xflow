package net.ms.designer.editors.workflow.tools;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.WorkflowImages;
import net.ms.designer.editors.workflow.models.EndNode;
import net.ms.designer.editors.workflow.models.RouteOnlyActivity;
import net.ms.designer.editors.workflow.models.StartNode;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.SystemAppActivity;
import net.ms.designer.editors.workflow.models.UserAppActivity;
import net.ms.designer.editors.workflow.models.Wire;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;



public class WorkflowPaletteFactory {
	public static PaletteRoot createPalette() {
        PaletteRoot paletteRoot = new PaletteRoot();
        paletteRoot.addAll(createCategories(paletteRoot));
        return paletteRoot;
    }

    private static List createCategories(PaletteRoot root) {
        List categories = new ArrayList();

        categories.add(createControlGroup(root));
        categories.add(createComponentsDrawer());

        return categories;
    }

    private static PaletteContainer createControlGroup(PaletteRoot root) {
        PaletteGroup controlGroup = new PaletteGroup("Control Group");

        List entries = new ArrayList();
        ToolEntry tool = new SelectionToolEntry();
        entries.add(tool);
        root.setDefaultEntry(tool);

        tool = new ConnectionCreationToolEntry(Messages.getString("WorkflowPaletteFactory.wire"), Messages.getString("WorkflowPaletteFactory.wire_desc"),null, null, null);
        entries.add(tool);

        controlGroup.addAll(entries);
        return controlGroup;
    }

    private static PaletteContainer createComponentsDrawer() {

        PaletteDrawer drawer = new PaletteDrawer("Components");

        List entries = new ArrayList();
        ToolEntry startNode = new CombinedTemplateCreationEntry(Messages.getString("WorkflowPaletteFactory.startnode"), Messages.getString("WorkflowPaletteFactory.startnode_desc"), StartNode.class, new SimpleFactory( 
        		StartNode.class), WorkflowImages.getImageDescriptor(WorkflowImages.STARTNODE_16), null);       
        entries.add(startNode); 

        ToolEntry endNode = new CombinedTemplateCreationEntry(Messages.getString("WorkflowPaletteFactory.endnode"), Messages.getString("WorkflowPaletteFactory.endnode_desc"), EndNode.class, new SimpleFactory(
        		EndNode.class), WorkflowImages.getImageDescriptor(WorkflowImages.ENDNODE_16), null);       
        entries.add(endNode); 

//        ToolEntry routeOnly = new CombinedTemplateCreationEntry(Messages.getString("WorkflowPaletteFactory.route"), Messages.getString("WorkflowPaletteFactory.route_desc"), RouteOnlyActivity.class, new SimpleFactory( 
//        		RouteOnlyActivity.class), WorkflowImages.getImageDescriptor(WorkflowImages.ROUTE_16), null);
//        entries.add(routeOnly);
        ToolEntry javaApp = new CombinedTemplateCreationEntry(Messages.getString("WorkflowPaletteFactory.javaapp"), Messages.getString("WorkflowPaletteFactory.javaapp_desc"), SystemAppActivity.class, new SimpleFactory( 
        		SystemAppActivity.class), WorkflowImages.getImageDescriptor(WorkflowImages.JAVAAPPLICATION_16), null);
        entries.add(javaApp);
        ToolEntry subFlowNode = new CombinedTemplateCreationEntry(Messages.getString("WorkflowPaletteFactory.subflownode"), Messages.getString("WorkflowPaletteFactory.subflownode_desc"), SubFlowActivity .class, new SimpleFactory( 
        		SubFlowActivity.class), WorkflowImages.getImageDescriptor(WorkflowImages.SUBFLOW_16), null);
        entries.add(subFlowNode);
        ToolEntry webAppNode = new CombinedTemplateCreationEntry(Messages.getString("WorkflowPaletteFactory.webapp"), Messages.getString("WorkflowPaletteFactory.webapp_desc"), UserAppActivity.class, new SimpleFactory( 
        		UserAppActivity.class), WorkflowImages.getImageDescriptor(WorkflowImages.WEBBAPPLICATION_16), null);
        entries.add(webAppNode); 

        drawer.addAll(entries);
        return drawer;
    }
}