/*
 * Created on 2006-09-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.ms.designer.editors.packages.tools;

import java.util.ArrayList;
import java.util.List;

import net.ms.designer.editors.packages.PackageImages;
import net.ms.designer.editors.packages.models.Package;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;


/**
 * @author mashuai
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PaletteFactory 
{

	public static PaletteRoot createPalette() 
	{
		PaletteRoot paletteRoot = new PaletteRoot();
		paletteRoot.addAll(createCategories(paletteRoot));
		return paletteRoot;
	}

	private static List createCategories(PaletteRoot root) 
	{
		List categories = new ArrayList();

		categories.add(createControlGroup(root));
		categories.add(createComponentsDrawer());

		return categories;
	}

	private static PaletteContainer createControlGroup(PaletteRoot root) 
	{
		PaletteGroup controlGroup = new PaletteGroup("Control Group");

		List entries = new ArrayList();
		ToolEntry tool = new SelectionToolEntry();
		entries.add(tool);
		root.setDefaultEntry(tool);

//		tool = new ConnectionCreationToolEntry("Connection", "Create a connection", null, null, null);
//		entries.add(tool);

		controlGroup.addAll(entries);
		return controlGroup;
	}

	private static PaletteContainer createComponentsDrawer() 
	{

		PaletteDrawer drawer = new PaletteDrawer("Packages");

		List entries = new ArrayList();
		ToolEntry tool = new CombinedTemplateCreationEntry("new Package", null, Package.class,
				new SimpleFactory(Package.class), PackageImages.getImageDescriptor(PackageImages.ENDNODE_16), null);
		entries.add(tool);

		drawer.addAll(entries);
		return drawer;
	}
}