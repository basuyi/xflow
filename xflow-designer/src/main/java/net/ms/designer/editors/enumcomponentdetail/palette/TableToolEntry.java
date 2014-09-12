package net.ms.designer.editors.enumcomponentdetail.palette;

import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.tools.ImageProvider;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;


public class TableToolEntry extends CombinedTemplateCreationEntry 
{
    public TableToolEntry() 
    {
    	super("EnumTable", "Create a EnumTable", Table.class,new EnumCreationFactory(Table.class), ImageProvider.TABLE_ICON, null);
    }
}
