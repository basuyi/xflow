package net.ms.designer.editors.enumcomponentdetail.palette;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;

public class PaletteFactory 
{
    private PaletteRoot root;
    
    private PaletteDrawer defaultTools;
    
    private PaletteDrawer CompTools;
    
    private static PaletteFactory instance = null;
    private PaletteFactory(){}
    
    public static PaletteFactory INSTANCE()
    {
        if(instance == null) instance = new PaletteFactory();
        return instance;
    }
    
    public PaletteRoot createPaletteRoot()
    {
//        if(root != null) return root;
        
        root = new PaletteRoot();
        root.add(createDefaultToolBox());
        root.add(createComponentBox());
        return root;
    }
    
    private PaletteDrawer createDefaultToolBox()
    {
        defaultTools = new PaletteDrawer("Default tools");
        
        defaultTools.add(new SelectionToolEntry());
        
        return defaultTools;
    }
    
    private PaletteDrawer createComponentBox()
    {
    	CompTools = new PaletteDrawer("Field");
        
//    	CompTools.add(new TableToolEntry());
    	CompTools.add(new ValueFieldToolEntry());
        
        return CompTools;
    }
}
