package net.ms.designer.editors.enumcomponentdetail.palette;

import net.ms.designer.editors.enumcomponentdetail.model.ValueField;
import net.ms.designer.editors.enumcomponentdetail.tools.ImageProvider;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;


public class ValueFieldToolEntry extends CombinedTemplateCreationEntry 
{
    public ValueFieldToolEntry()
    {
        super("Value", "Create a ValueField",ValueField.class, new EnumCreationFactory(ValueField.class), ImageProvider.FIELD_ICON, null);
    }
}