
package net.ms.designer.editors.componentdetail.models;

import java.io.IOException;

import net.ms.designer.editors.componentdetail.models.Messages;
import net.ms.designer.editors.componentdetail.models.TemplateConstants;

import org.eclipse.swt.graphics.Image;


/**
 * 标签
 * 
 * @author lili
 */
public class Label extends Element
{

	static final long serialVersionUID = 1;

	private static Image LOGIC_LABEL_ICON = new Image(null, TemplateConstants.class
			.getResourceAsStream("icons/Label16.gif")); //$NON-NLS-1$

	private static int count;

	public Label() 
	{
		super();
		setName("comments");
//		setFieldLabel("Comment");
	}

	public String getLabelContents() 
	{
		return null;
//		return this.getFieldLabel();
	}

	public Image getIconImage() 
	{
		return LOGIC_LABEL_ICON;
	}

	protected String getNewID() 
	{
		return Integer.toString(count++);
	}

	private void readObject(java.io.ObjectInputStream s) throws IOException,ClassNotFoundException 
	{
		s.defaultReadObject();
	}

//	public void setLabelContents(String s) 
//	{
//		setFieldLabel(s);
//		firePropertyChange("labelContents", null, s); //$NON-NLS-2$//$NON-NLS-1$ //$NON-NLS-1$
//	}

	public String toString() 
	{
		return getLabelContents();
	}

	/**
	 * @see com.kenoah.kde.editors.kcg.model.common.KCGElement#getField_Type()
	 */
	public String getField_Type() 
	{
		return Messages.getString("KCGLabel.Field.Type"); //$NON-NLS-1$
	}

	/* （非 Javadoc）
	 * @see com.kenoah.kde.editors.kcg.model.common.KCGField#getFieldTypeID()
	 */
	public int getFieldTypeID()
	{
		// TODO 
		return 0;
	}

	/* （非 Javadoc）
	 * @see com.kenoah.kde.editors.kcg.model.common.KCGField#getFieldTypeLength()
	 */
	public int getFieldTypeLength() 
	{
		// TODO 
		return 0;
	}

	/* （非 Javadoc）
	 * @see com.kenoah.kde.editors.kcg.model.common.KCGField#getFieldTypeScale()
	 */
	public int getFieldTypeScale() 
	{
		// TODO 
		return 0;
	}

}
