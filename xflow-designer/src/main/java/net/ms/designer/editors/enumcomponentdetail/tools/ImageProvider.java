package net.ms.designer.editors.enumcomponentdetail.tools;

import net.ms.designer.editors.enumcomponentdetail.EnumcomponentdetailPlugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class ImageProvider 
{
    
    private ImageProvider()
    {
    	//default
    }
    
    public static final ImageDescriptor TABLE_ICON = createImage("/icons/full/table.gif");
    
    public static final ImageDescriptor FIELD_ICON = createImage("/icons/full/column.gif");
    
    public static ImageDescriptor createImage(String url)
    {
       try
       {
           return AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner",url);
       }
       catch(Exception e)
       {
           return null;
       }
    }
}
