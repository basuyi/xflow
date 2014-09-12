package net.ms.designer.editors.component.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.PropertyUtils;
import net.ms.designer.editors.component.models.BizComponent;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;
import net.ms.designer.editors.component.models.EnumComponent;
import net.ms.designer.editors.component.tools.WriteToProjectXML;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.UIPlugin;



public class PropertyDialog extends TitleAreaDialog
{
	public int status;
	private Object type;
	private Shell shell;
	private Object container;
	
	private Text txtName;
	private Text txtIname;
	private Text txtDesc;
	
	private Label lName;
	private Label lIname;
	private Label lDesc;
	
	public PropertyDialog(Shell parent, Object type , Object container)
	{
		super(parent);
		this.shell = parent;
		this.type = type;
		this.container = container;
	}

	protected Control createDialogArea(Composite parent)
	{
		if (type instanceof BizComponent)
			setTitle("业务组件信息");
		else
			setTitle("枚举组件信息");
		
		// config the composite
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 20;
		layout.verticalSpacing = 8;
		layout.numColumns = 2;
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(layout);
		
		lName = new Label(composite,SWT.NONE);
		lName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lName.setText("名称");
		
		txtName = new Text(composite,SWT.BORDER);
		txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
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
		
		if (type instanceof BizComponent)
		{
			if( ((BizComponent)type).getName()!= null)
				txtName.setText(((BizComponent)type).getName());
			else
				txtName.setText("");
			if(((BizComponent)type).getIname() != null)
				txtIname.setText(((BizComponent)type).getIname());
			else
				txtIname.setText("");
			if(((BizComponent)type).getDesc() != null)
				txtDesc.setText(((BizComponent)type).getDesc());
			else
				txtDesc.setText("");
		}
		if (type instanceof EnumComponent)
		{
			if( ((EnumComponent)type).getName()!= null)
				txtName.setText(((EnumComponent)type).getName());
			else
				txtName.setText("");
			if(((EnumComponent)type).getIname() != null)
				txtIname.setText(((EnumComponent)type).getIname());
			else
				txtIname.setText("");
			if(((EnumComponent)type).getDesc() != null)
				txtDesc.setText(((EnumComponent)type).getDesc());
			else
				txtDesc.setText("");
		}
	
		return composite;
	}
	public void okPressed()
	{
		if (type instanceof BizComponent)
		{
			
			if(txtName.getText() != "")
			{
				char ii = txtName.getText().toString().charAt(0);
				if(!((ii >='A') && (ii <='Z')))
				{
					MessageBox box = new MessageBox(shell);
					box.setMessage("The first charater must be upcase");
					box.open();
					return;
				}
				
				boolean flag = true;
				List packs = ((CompDiagram)container).getNodes();
				for(int i = 0;i < packs.size(); i++)
				{
					if(((Component)packs.get(i)).getName().equals(txtName.getText()))
					{
						flag = false;
						break;
					}
				}
				if(!flag)
				{
					MessageBox box = new MessageBox(shell);
					box.setMessage("Invailed name!");
					box.open();
					return;
				}
//				 implements the changing
				// begin
				String oldName = ((BizComponent)type).getName();
				String newName = txtName.getText();
				if(!oldName.equals(newName) && status == 1)
				{
					this.dealChanging("component" , "name" , oldName , newName);
				}
				// end
				((BizComponent)type).setName(txtName.getText());
				((BizComponent)type).setIname(txtIname.getText());
				((BizComponent)type).setDesc(txtDesc.getText());
				super.okPressed();
				return;
			}
			else
				return;
		}
		if (type instanceof EnumComponent)
		{
			if(txtName.getText() != "")
			{
				char ii = txtName.getText().toString().charAt(0);
				if(!((ii >='A') && (ii <= 'Z')))
				{
					MessageBox box = new MessageBox(shell);
					box.setMessage("The first charater must be upcase");
					box.open();
					return;
				}
				boolean flag = true;
				List packs = ((CompDiagram)container).getNodes();
				for(int i = 0;i < packs.size(); i++)
				{
					if(((Component)packs.get(i)).getName().equals(txtName.getText()))
					{
						flag = false;
						break;
					}
				}
				if(!flag)
				{
					MessageBox box = new MessageBox(shell);
					box.setMessage("Invailed name!");
					box.open();
					return;
				}
				// implements the changing
				// begin
				String oldName = ((EnumComponent)type).getName();
				String newName = txtName.getText();
				if(!oldName.equals(newName) && status == 1)
				{
					this.dealChanging("enumeration" , "name" , oldName , newName);
				}
				// end
				((EnumComponent)type).setName(txtName.getText());
				((EnumComponent)type).setIname(txtIname.getText());
				((EnumComponent)type).setDesc(txtDesc.getText());
				super.okPressed();
				return;
			}
			else
				return;
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
		}
		if(parentTag.equals("component"))
		{
			type = "componentdetail";
//			 get the old element to modify
			MsElement element = context.getElement(type + "_" + oldText);
			// changing
			element.setNodeName(newText);
			Object container = element.getContainer();
			((net.ms.designer.editors.componentdetail.models.Container)container)
					.getMainTable().setName(newText);
			
			MsElement newElement = element;
			context.addElement(newElement);
			context.removeElement(type + "_" + oldText);
		}
	}
}

