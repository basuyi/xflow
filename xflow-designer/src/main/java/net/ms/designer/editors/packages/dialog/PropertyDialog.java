package net.ms.designer.editors.packages.dialog;


import java.util.List;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.component.tools.WriteToProjectXML;
import net.ms.designer.editors.packages.models.Package;
import net.ms.designer.editors.packages.models.PackageDiagram;
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
	private Object container;
	private Shell shell;
	
	private Text txtName;
	private Text txtIname;
	private Text txtDesc;
	
	private Label lName;
	private Label lIname;
	private Label lDesc;
	
	public PropertyDialog(Shell parent, Object type ,Object container)
	{
		super(parent);
		shell = parent;
		this.type = type;
		this.container = container;
	}

	protected Control createDialogArea(Composite parent)
	{
		setTitle("组件包信息");
		
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
		lName.setText("包名");
		
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
		
		
		if( ((Package)type).getName()!= null)
			txtName.setText(((Package)type).getName());
		else
			txtName.setText("");
		if(((Package)type).getIname() != null)
			txtIname.setText(((Package)type).getIname());
		else
			txtIname.setText("");
		if(((Package)type).getDesc() != null)
			txtDesc.setText(((Package)type).getDesc());
		else
			txtDesc.setText("");
		
		return composite;
	}
	public void okPressed()
	{	
		if(txtName.getText() != "")
		{
			boolean flag = true;
			List packs = ((PackageDiagram)container).getNodes();
			for(int i = 0;i < packs.size(); i++)
			{
				if(((Package)packs.get(i)).getName().equals(txtName.getText()))
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
			String oldName = ((Package)type).getName();
			String newName = txtName.getText();
			if(!oldName.equals(newName) && status == 1)
			{
				this.dealChanging("package" , "name" , oldName , newName);
			}
			// end
			((Package)type).setName(txtName.getText());
			((Package)type).setIname(txtIname.getText());
			((Package)type).setDesc(txtDesc.getText());
			super.okPressed();
			return;
		}
	}
	/**
	 * 
	 * @param parentTag
	 * @param tag
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
		MsElement oldElement = context.getElement("component_" + oldText);
		oldElement.setNodeName(newText);
		MsElement newElement = oldElement;
		context.addElement(newElement);
		context.removeElement("component_" + oldText);
	}
}

