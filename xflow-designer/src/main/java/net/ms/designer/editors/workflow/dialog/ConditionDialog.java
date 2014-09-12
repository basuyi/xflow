package net.ms.designer.editors.workflow.dialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.models.ApplicationActivity;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.SystemAppActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.UIPlugin;



public class ConditionDialog extends TitleAreaDialog
{
	private Shell shell;
	private ApplicationActivity application;
	private Text txtAppName,txtAppDesc,txtAppPath;
	private String sysContentTxt = "",userContentTxt= "";
	private Combo comboAppType;
	private Label labelApp,labelAppName,labelAppDesc,labelAppType;
	private TableViewer tvAppPara;
	private Button btnAppParaCreate,btnAppParaEdit,btnAppParaDelete;
	public List appParaList = new ArrayList();
	ConditionDialog myself = this;
	ApplicationListDialog applicationListDialog ;
	
	public ConditionDialog(Shell parentShell, ApplicationActivity application, ApplicationListDialog applicationListDialog) {
		super(parentShell);
		this.shell = parentShell;
		this.application = application;
		
		if(applicationListDialog.activityPropDialog.allApplicationList == null || applicationListDialog.activityPropDialog.allApplicationList.size()<1)
		{
			applicationListDialog.activityPropDialog.allApplicationList = new ArrayList();
		}
		this.applicationListDialog = applicationListDialog;
		if(this.application != null && this.application.getApplicationId().length()>0)
		{
			if(this.application.getApplicationType().equals("0"))
			{
				this.sysContentTxt = this.application.getApplicationPath();
			}
			else
				this.userContentTxt = this.application.getApplicationPath();
		}
	}
	
	protected Control createDialogArea(Composite parent) 
	{
		setTitle("自定义应用");
		setMessage(" 设定自定义应用属性");
		
		TabFolder tabFolder = new TabFolder(parent,SWT.NONE);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		TabItem tabItem_1 = new TabItem(tabFolder,SWT.NONE);
		Composite mainComp = new Composite(tabFolder,SWT.NULL);
		tabItem_1.setText("常规");
		
		GridLayout layout0 = new GridLayout();
		layout0.marginHeight = 5;
		layout0.marginWidth = 20;
		layout0.verticalSpacing = 8;
		mainComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		mainComp.setLayout(layout0);
		
		tabItem_1.setControl(mainComp);
		
		GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		GridData txtGridData = new GridData(GridData.FILL_HORIZONTAL);
		GridData comboGridData = new GridData(GridData.FILL_HORIZONTAL);
		Composite composite1 = new Composite(mainComp,SWT.NULL);
		GridLayout layout1 = new GridLayout();
		layout1.verticalSpacing = 8;
		layout1.numColumns = 2;
		composite1.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite1.setLayout(layout1);
		
		labelAppName = new Label(composite1,SWT.NONE);
		labelAppName.setLayoutData(labelGridData);
		labelAppName.setText("应用名称");
		
		this.txtAppName= new Text(composite1,SWT.BORDER);
		txtAppName.setLayoutData(txtGridData);
		
		labelAppDesc = new Label(composite1,SWT.NONE);
		labelAppDesc.setLayoutData(labelGridData);
		labelAppDesc.setText("应用描述");
		
		this.txtAppDesc= new Text(composite1,SWT.BORDER);
		txtAppDesc.setLayoutData(txtGridData);
		
		labelAppType = new Label(composite1,SWT.NONE);
		labelAppType.setLayoutData(labelGridData);
		labelAppType.setText("应用类型");
		
		this.comboAppType = new Combo(composite1,SWT.DROP_DOWN | SWT.READ_ONLY);
		comboAppType.setLayoutData(comboGridData);
		comboAppType.add("Action类",0);
		comboAppType.add("表达式",1);
		comboAppType.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e) 
			{
				if(comboAppType.getSelectionIndex() == 0)
				{
					labelApp.setText("Java类名");
					txtAppPath.setText(sysContentTxt);
				}
				if(comboAppType.getSelectionIndex() == 1)
				{
					labelApp.setText("执行URL");
					txtAppPath.setText(userContentTxt);
				}
			}

			
		});
		labelApp = new Label(composite1,SWT.NONE);
		labelApp.setLayoutData(labelGridData);
			
		this.txtAppPath = new Text(composite1,SWT.BORDER);
		txtAppPath.setLayoutData(txtGridData);
		txtAppPath.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e) 
			{
				if(comboAppType.getSelectionIndex() == 0)
				{
					sysContentTxt = txtAppPath.getText();
				}
				if(comboAppType.getSelectionIndex() == 1)
				{
					userContentTxt = txtAppPath.getText();
				}
			}
			
		});
		
		if(this.comboAppType.getSelectionIndex() == 0)
		{
			labelApp.setText("Action类");
			txtAppPath.setText(this.sysContentTxt);
		}
		if(this.comboAppType.getSelectionIndex() == 1)
		{
			labelApp.setText("表达式");
			txtAppPath.setText(this.userContentTxt);
		}
		

		TabItem tabItem_2 = new TabItem(tabFolder,SWT.NONE);
		
		Composite composite2 = new Composite(tabFolder,SWT.NONE);
		GridLayout layout2 = new GridLayout();
		layout2.marginHeight = 5;
		layout2.marginWidth = 20;
		layout2.verticalSpacing = 8;
		layout2.numColumns = 1;
		composite2.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite2.setLayout(layout2);
		tabItem_2.setText("参数");
		tabItem_2.setControl(composite2);
		
		tvAppPara = new TableViewer(composite2, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		tvAppPara.getTable().setHeaderVisible(true);
		tvAppPara.getTable().setLinesVisible(true);
        
        TableLayout tLayout = new TableLayout();
        tvAppPara.getTable().setLayout(tLayout);
        GridData tvAppParaGridData = new GridData(GridData.FILL_BOTH);
        tvAppParaGridData.verticalSpan = 15;
        tvAppPara.getTable().setLayoutData(tvAppParaGridData);

        TableColumn column1 = new TableColumn(tvAppPara.getTable(), SWT.SINGLE);
        column1.setText("参数名称"); 
        column1.setWidth(120);

        TableColumn column2 = new TableColumn(tvAppPara.getTable(), SWT.SINGLE);
        column2.setText("参数类型"); 
        column2.setWidth(120);
        
        TableColumn column3 = new TableColumn(tvAppPara.getTable(), SWT.SINGLE);
        column3.setText("输入/输出"); 
        column3.setWidth(120);
        
        tvAppPara.setContentProvider(new TableViewerContentProvider());
        tvAppPara.setLabelProvider(new TableViewerLabelProvider());
        tvAppPara.setInput(appParaList);
        
        Composite btnComposite = new Composite(composite2,SWT.NONE);
        btnComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout btnComLayout = new GridLayout();
        btnComLayout.numColumns = 3;
        btnComLayout.makeColumnsEqualWidth = true;
        
	    btnComposite.setLayout(btnComLayout);
        
        this.btnAppParaCreate = new Button(btnComposite,SWT.NONE);
//	    btnAppCreate.setLayoutData(btnGridData);
	    btnAppParaCreate.setText(" 新    建 ");
	    btnAppParaCreate.addSelectionListener(new SelectionAdapter()
	    		{
	    			public void widgetSelected(SelectionEvent e)
	            	{
	    				WorkflowParameterDialog paraDialog = new WorkflowParameterDialog(UIPlugin.getDefault()
	            				.getWorkbench().getActiveWorkbenchWindow().getShell(),null,null,null,null,myself);
	    				paraDialog.open();
	            	}
	    		});
	    
      
	    this.btnAppParaEdit = new Button(btnComposite,SWT.NONE);
//	    btnAppEdit.setLayoutData(btnGridData);
	    btnAppParaEdit.setText(" 编    辑 ");
	    btnAppParaEdit.addSelectionListener(new SelectionAdapter()
	    		{
	    			public void widgetSelected(SelectionEvent e)
	    		    {
	    				if(tvAppPara.getTable().getSelectionIndex()<0)
	    				{
	    					{
	    	        			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
	    	        					"请选择一个参数",
	    	        					"请选择一个参数");
	    	        			return;
	    	        		}
	    				}
	    				WorkflowParameterDialog paraDialog = new WorkflowParameterDialog(UIPlugin.getDefault()
	    		        		.getWorkbench().getActiveWorkbenchWindow().getShell(),
	    		        		(ParameterEntire)(tvAppPara.getElementAt(tvAppPara.getTable().getSelectionIndex())),null,null,null,myself);
	    				paraDialog.open();
	    		    }
	    		});
	    
	    this.btnAppParaDelete = new Button(btnComposite,SWT.NONE);
	    btnAppParaDelete.setText(" 删    除 ");
	    btnAppParaDelete.addSelectionListener(new SelectionAdapter()
	    		{
	    			public void widgetSelected(SelectionEvent e)
	    			{
	    				if(tvAppPara.getTable().getSelectionIndex()<0)
	    				{
	    					{
	    	        			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
	    	        					"请选择一个参数",
	    	        					"请选择一个参数");
	    	        			return;
	    	        		}
	    				}
	    				Iterator it = appParaList.iterator();
	    				while(it.hasNext())
	    				{
	    					ParameterEntire para = (ParameterEntire)it.next();
	    					if(para.getParaName().equals(((ParameterEntire)tvAppPara.getElementAt(tvAppPara.getTable().getSelectionIndex())).getParaName()))
	    					{
	    						tvAppPara.remove(para);
	    						break;
	    					}
	    				}
	    				refresh();
	    			}
	    		}
	    		);

		this.setPrioTextContent();
		
		return tabFolder;
	}
	
	private void setPrioTextContent()
	{
		labelAppName.setText("应用名称");
		labelAppDesc.setText("应用描述");
		labelAppType.setText("应用类型");
		if(this.application != null && this.application.getApplicationId().length()>0)
		{
			this.txtAppName.setText(application.getApplicationName());
			this.txtAppDesc.setText(application.getApplicationDesc());
			this.comboAppType.select(new Integer(application.getApplicationType()).intValue());
			this.appParaList = application.getWfApplicationParam();
			this.refresh();
		}
	}
	
	 protected void buttonPressed(int buttonId) 
	    {
	    	if (buttonId == IDialogConstants.OK_ID)
	    	{
	    		if(this.txtAppName.getText() == null && this.txtAppName.getText().length()<1)
	    		{
	    			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
	    					"应用名称不能为空",
	    					"应用名称不能为空");
	    			return;
	    		}
	    		if(this.comboAppType.getSelectionIndex()<0)
	    		{
	    			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
	    					"应用类型不能为空",
	    					"应用类型不能为空");
	    			return;
	    		}
	    		if(this.application!=null && this.application.getApplicationId().length()>0)
	    		{
		    		this.application.setApplicationName(this.txtAppName.getText());
		    		this.application.setApplicationDesc(this.txtAppDesc.getText());
		    		this.application.setApplicationPath(this.txtAppPath.getText());
		    		this.application.setApplicationType((new Integer(this.comboAppType.getSelectionIndex())).toString());
		    		this.application.setWfApplicationParam(this.appParaList);
	    		}
	    		else if(this.application == null)
	    		{
	    			this.application = new ApplicationActivity();
	    			Date da = new Date();
    				long time =  da.getTime();
    				application.setApplicationId(Long.toString(time));
	    			this.application.setApplicationName(this.txtAppName.getText());
		    		this.application.setApplicationDesc(this.txtAppDesc.getText());
		    		this.application.setApplicationPath(this.txtAppPath.getText());
		    		if(this.comboAppType.getSelectionIndex()>-1)
		    		{
		    			this.application.setApplicationType((new Integer(this.comboAppType.getSelectionIndex())).toString());
		    		}
		    		this.application.setWfApplicationParam(this.appParaList);
	    		}
//	    		for(int i = 0;i<this.applicationListDialog.allApplicationList.size();i++)
//	    		{
//	    			if(this.txtAppName.getText().equals(((ApplicationActivity)this.applicationListDialog.allApplicationList.get(i)).getApplicationName()))
//	    			{
//	    				MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
//		    					"应用名称重复",
//		    					"应用名称重复");
//		    			return;
//	    			}
//	    		}
	    		if(!this.applicationListDialog.activityPropDialog.allApplicationList.contains(this.application))
	    		{
	    			this.applicationListDialog.activityPropDialog.allApplicationList.add(this.application);
	    		}
	    		this.applicationListDialog.refresh();
	    		
	    	}
	    	else if(buttonId == IDialogConstants.CANCEL_ID)
	    	{
	    		super.cancelPressed();
	    	}
	    	super.buttonPressed(buttonId);
	    }
	
	public void refresh()
	{
		 tvAppPara.setContentProvider(new TableViewerContentProvider());
	     tvAppPara.setLabelProvider(new TableViewerLabelProvider());
	     tvAppPara.setInput(appParaList);
	     tvAppPara.refresh();
	}
}
