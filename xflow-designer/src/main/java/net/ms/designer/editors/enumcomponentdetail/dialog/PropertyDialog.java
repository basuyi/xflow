package net.ms.designer.editors.enumcomponentdetail.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.PropertyUtils;
import net.ms.designer.editors.component.models.EnumComponent;
import net.ms.designer.editors.component.tools.WriteToProjectXML;
import net.ms.designer.editors.enumcomponentdetail.model.Table;
import net.ms.designer.editors.enumcomponentdetail.model.ValueField;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.UIPlugin;


public class PropertyDialog extends TitleAreaDialog
{
	Object type;
	public int status;
	private Text txtTableName;
	private Text txtResidstr;
	private Text txtIname;
	private Text txtDesc;
	
	private Label lTableName;
	private Label lResidstr;
	private Label lIname;
	private Label lDesc;
	
	public PropertyDialog(Shell parent, Object type)
	{
		super(parent);
		this.type = type;
	}

	protected Control createDialogArea(Composite parent)
	{
		setTitle("属性信息");
		// config the composite
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 20;
		layout.verticalSpacing = 8;
		layout.numColumns = 2;
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(layout);
		
		lTableName = new Label(composite,SWT.NONE);
		lTableName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lTableName.setText("枚举组件名");
		
		txtTableName = new Text(composite,SWT.BORDER);
		txtTableName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		
		lResidstr = new Label(composite,SWT.NONE);
		lResidstr.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lResidstr.setText("名称");
		
		txtResidstr = new Text(composite,SWT.BORDER);
		txtResidstr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		lIname = new Label(composite,SWT.NONE);
		lIname.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lIname.setText("标题");
		
		txtIname = new Text(composite,SWT.BORDER);
		txtIname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		lDesc = new Label(composite,SWT.NONE);
		lDesc.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lDesc.setText("描述");
		
		txtDesc = new Text(composite,SWT.BORDER);
		txtDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		if(type instanceof ValueField)
		{
			lResidstr.setVisible(true);
			txtResidstr.setVisible(true);
			lTableName.setVisible(false);
			txtTableName.setVisible(false);
			if( ((ValueField)type).getFieldName()!= null)
				txtResidstr.setText(((ValueField)type).getFieldName());
			else
				txtResidstr.setText("");
			if(((ValueField)type).getIname() != null)
				txtIname.setText(((ValueField)type).getIname());
			else
				txtIname.setText("");
			if(((ValueField)type).getDesc() != null)
				txtDesc.setText(((ValueField)type).getDesc());
			else
				txtDesc.setText("");
		}
		else if(type instanceof Table)
		{
			lResidstr.setVisible(false);
			txtResidstr.setVisible(false);
			lTableName.setVisible(true);
			txtTableName.setVisible(true);	
			if(((Table)type).getTableName() != null)
				txtTableName.setText(((Table)type).getTableName());
			else
				txtTableName.setText("");
			if(((Table)type).getIname() != null)
				txtIname.setText(((Table)type).getIname());
			else
				txtIname.setText("");
			if(((Table)type).getDesc() != null)
				txtDesc.setText(((Table)type).getDesc());
			else
				txtDesc.setText("");
		}
		
		return composite;
	}
	public void okPressed()
	{
		if(type instanceof ValueField)
		{
			if(txtResidstr.getText() != "")
			{
				((ValueField)type).setFieldName(txtResidstr.getText());
				((ValueField)type).setIname(txtIname.getText());
				((ValueField)type).setDesc(txtDesc.getText());
				super.okPressed();
				return;
			}
		}
		else if(type instanceof Table)
		{	
			if(txtTableName.getText() != "")
			{
//				 implements the changing
				// begin
				String oldName = ((Table)type).getTableName();
				String newName = txtTableName.getText();
				if(!oldName.equals(newName) && status == 1)
				{
					this.dealChanging("enumeration" , "name" , oldName , newName);
				}
				// end
				((Table)type).setTableName(txtTableName.getText());
				((Table)type).setIname(txtIname.getText());
				((Table)type).setDesc(txtDesc.getText());
				super.okPressed();
				return;
			}
		}
		
	}
	/**
	 * 
	 * @param oldText
	 * @param newText
	 */
	public void dealChanging(String parentTag , String tag , String oldText , String newText)
	{
		PackageEditor editor = (PackageEditor)UIPlugin.getDefault()
									.getWorkbench().getActiveWorkbenchWindow()
									.getActivePage().getActiveEditor();
		MsProject project = editor.getProject();
		MsContext context = editor.getContext();
		//
		// modify the "project.xml" file
		//
		String filePath = project.getConfigPath() + "project.xml";
		WriteToProjectXML writor = new WriteToProjectXML();
		try 
		{
			writor.changeText(filePath , parentTag , tag , oldText , newText);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		// modify the context object
		//
		String type = "";
		if(parentTag.equals("enumeration"))
		{
			type = "enumdetail";
//			 get the old element to modify
			MsElement element = context.getElement(type + "_" + oldText);
			// changing
			element.setNodeName(newText);
			Object container = element.getContainer();
			try 
			{
				List list = (List)PropertyUtils.getProperty(container , "children");
				PropertyUtils.setProperty(list.get(0) , "tableName" , newText);
			} 
			catch (IllegalAccessException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (InvocationTargetException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (NoSuchMethodException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MsElement newElement = element;
			context.addElement(newElement);
			context.removeElement(type + "_" + oldText);
			//
			// change the parent element
			//
			MsElement parent = newElement.getParent();
			Object diagram = parent.getContainer();
			try 
			{
				List list = (List)PropertyUtils.getProperty(diagram , "nodes");
				for(int i = 0 ; i < list.size() ; i++)
				{
					String componentID = (String)PropertyUtils.getProperty(list.get(i) , "componentID");
					if( componentID.equals(newElement.getId()))
					{
						PropertyUtils.setProperty(list.get(i) , "name" , newText);
					}
				}
			} 
			catch (IllegalAccessException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (InvocationTargetException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (NoSuchMethodException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(parentTag.equals("component"))
		{
			type = "componentdetail";
		}
	}
}

