/**
 * @author liuchunxia
 * 
 * workflow property dialog
 */

package net.ms.designer.editors.workflow.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.ParameterPartial;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.UIPlugin;

public class WorkflowPropertyDialog extends TitleAreaDialog {

	private Shell shell;
	private WorkflowSubPart subpart;
	public List paraList;
	private MsProject project;
	WorkflowPropertyDialog myself = this;
	private Container container;
	private String inf1, inf2, inf3;

	// Text;
	// properties about workflow
	private Text txtVersion;
	private Text txtWorkflowParas;
	private Text txtCreateTime;
	private Text txtFinishtTime;
	private Text txtStartTime;
	private Text txtWorkflowPriority;
	private Text txtWorkflowStatus;
	private Text txtWorkflowName;
	private Text txtWorkflowDesc;
	private Text txtWorkflowAllInfor; // 自定义
	private Text txtWorkflowComp;
	private Text txtWorkflowIname;

	// button about workflow
	private Button btnWorkflowCus;
	private Button btnWorkflowParaCreate;
	private Button btnWorkflowParaEdit;
	private Button btnWorkflowParaDelete;

	// Combo;
	// properties about workflow
	// private Combo comboWorkflowComp;
	private Combo comboWorkflowEnt;
	private Combo comboWorkflowInf1;
	private Combo comboWorkflowInf2;

	// Tableview
	private TableViewer tvWfPara;

	public WorkflowPropertyDialog(Shell shell, WorkflowSubPart subpart,
			MsProject project) {
		super(shell);
		this.shell = shell;
		this.subpart = subpart;
		this.project = ((WorkflowDiagram) this.subpart).getProject();
		// this.container =
		// (Container)((PackageEditor)UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getContainer();
		paraList = new ArrayList();
	}

	protected Control createDialogArea(Composite parent) {

		// workflow property
		if (this.subpart instanceof WorkflowDiagram) {
			paraList = ((WorkflowDiagram) subpart).getParaList();

			this.createWorkflowArea(parent);
		}
		return parent;

	}

	protected Control createWorkflowArea(Composite parent) {
		setTitle("工作流信息");
		setMessage(" 设定流程相关属性");

		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);

		Composite composite1 = new Composite(tabFolder, SWT.NULL);
		tabItem_1.setText("常规");
		//
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 20;
		layout.verticalSpacing = 8;
		layout.numColumns = 2;
		composite1.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite1.setLayout(layout);

		tabItem_1.setControl(composite1);

		GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		GridData txtGridData = new GridData(GridData.FILL_HORIZONTAL);
		GridData comboGridData = new GridData(GridData.FILL_HORIZONTAL);

		Label labelWorkflowName = new Label(composite1, SWT.NONE);
		labelWorkflowName.setLayoutData(labelGridData);
		labelWorkflowName.setText("流程名称");

		this.txtWorkflowName = new Text(composite1, SWT.BORDER);
		txtWorkflowName.setLayoutData(txtGridData);
		txtWorkflowName.setEditable(false);

		// Label labelWorkflowIname = new Label(composite1,SWT.NONE);
		// labelWorkflowIname .setLayoutData(labelGridData);
		// labelWorkflowIname .setText("国际化名称");
		//
		// this.txtWorkflowIname = new Text(composite1,SWT.BORDER);
		// txtWorkflowIname.setLayoutData(txtGridData);

		Label labelWorkflowDesc = new Label(composite1, SWT.NONE);
		labelWorkflowDesc.setLayoutData(labelGridData);
		labelWorkflowDesc.setText("流程描述");

		this.txtWorkflowDesc = new Text(composite1, SWT.BORDER);
		txtWorkflowDesc.setLayoutData(txtGridData);
		txtWorkflowDesc.setEditable(false);

		// Label labelWorkflowComp = new Label(composite1,SWT.NONE);
		// labelWorkflowComp.setLayoutData(labelGridData);
		// labelWorkflowComp.setText("关联组件");

		// this.comboWorkflowComp = new Combo(composite1,SWT.DROP_DOWN |
		// SWT.READ_ONLY);
		// comboWorkflowComp.setLayoutData();
		// this.txtWorkflowComp = new Text(composite1,SWT.BORDER);
		// txtWorkflowComp.setLayoutData(txtGridData);
		// txtWorkflowComp.setEditable(false);
		//
		// Label labelWorkflowEnt = new Label(composite1,SWT.NONE);
		// labelWorkflowEnt.setLayoutData(labelGridData);
		// labelWorkflowEnt.setText("业务实体");
		//
		// this.comboWorkflowEnt = new Combo(composite1,SWT.DROP_DOWN |
		// SWT.READ_ONLY);
		// comboWorkflowEnt.setLayoutData(comboGridData);
		//
		// Label labelWorkflowInfor1= new Label(composite1,SWT.NONE);
		// labelWorkflowInfor1.setLayoutData(labelGridData);
		// labelWorkflowInfor1.setText("提示信息");
		//
		// this.comboWorkflowInf1 = new Combo(composite1,SWT.NONE);
		// comboWorkflowInf1.setLayoutData(comboGridData);
		// comboWorkflowInf1.addModifyListener(new ModifyListener()
		// {
		//
		// public void modifyText(ModifyEvent e) {
		// if(comboWorkflowInf1.getText() != null &&
		// comboWorkflowInf1.getText().length()>0)
		// {
		// inf1 = comboWorkflowInf1.getText();
		// StringBuffer sb = new StringBuffer(inf1);
		// if(inf2 != null && inf2.length()>0)
		// {
		// sb.append(",");
		// sb.append(inf2);
		// }
		// // if(inf3 != null && inf3.length()>0)
		// // {
		// // sb.append(",");
		// // sb.append(inf3);
		// // }
		// txtWorkflowAllInfor.setText(sb.toString());
		// }
		// }
		//
		// });
		//
		// Label labelWorkflowInfor2= new Label(composite1,SWT.NONE);
		// labelWorkflowInfor2.setLayoutData(labelGridData);
		// labelWorkflowInfor2.setText("");
		//
		// this.comboWorkflowInf2 = new Combo(composite1,SWT.NONE);
		// comboWorkflowInf2.setLayoutData(comboGridData);
		// comboWorkflowInf2.addModifyListener(new ModifyListener()
		// {
		//
		// public void modifyText(ModifyEvent e) {
		// if(comboWorkflowInf2.getText() != null &&
		// comboWorkflowInf2.getText().length()>0)
		// {
		// inf2 = comboWorkflowInf2.getText();
		// StringBuffer sb = new StringBuffer();
		// if(inf1 != null && inf1.length()>0)
		// {
		// sb.append(inf1);
		// sb.append(",");
		// sb.append(inf2);
		// }
		// // if(inf3 != null && inf3.length()>0)
		// // {
		// // sb.append(",");
		// // sb.append(inf3);
		// // }
		// txtWorkflowAllInfor.setText(sb.toString());
		// }
		// }
		//
		// });
		//
		// this.btnWorkflowCus = new Button(composite1, SWT.CHECK);
		// btnWorkflowCus.setText("自定义");
		// btnWorkflowCus.setSelection(false);
		// btnWorkflowCus.setLayoutData(labelGridData);
		// btnWorkflowCus.addSelectionListener(new SelectionListener(){
		//
		// public void widgetSelected(SelectionEvent e) {
		// // TODO Auto-generated method stub
		// if(btnWorkflowCus.getSelection() == true)
		// {
		// txtWorkflowAllInfor.setEditable(true);
		// }
		// if(btnWorkflowCus.getSelection() == false)
		// {
		// txtWorkflowAllInfor.setEditable(false);
		// }
		// }
		//
		// public void widgetDefaultSelected(SelectionEvent e) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// });

		// this.txtWorkflowAllInfor = new Text(composite1,SWT.BORDER);
		// txtWorkflowAllInfor.setLayoutData(txtGridData);
		// txtWorkflowAllInfor.setEditable(false);

		TabItem tabItem_2 = new TabItem(tabFolder, SWT.NONE);

		Composite composite2 = new Composite(tabFolder, SWT.NONE);
		GridLayout layout2 = new GridLayout();
		layout2.marginHeight = 5;
		layout2.marginWidth = 20;
		layout2.verticalSpacing = 8;
		layout2.numColumns = 2;
		composite2.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite2.setLayout(layout2);
		tabItem_2.setText("参数");
		tabItem_2.setControl(composite2);

		tvWfPara = new TableViewer(composite2, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.BORDER | SWT.FULL_SELECTION);
		tvWfPara.getTable().setHeaderVisible(true);
		tvWfPara.getTable().setLinesVisible(true);

		TableLayout tLayout = new TableLayout();
		tvWfPara.getTable().setLayout(tLayout);
		tvWfPara.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		TableColumn column1 = new TableColumn(tvWfPara.getTable(), SWT.SINGLE);
		column1.setText("参数名称");
		column1.setWidth(150);

		TableColumn column2 = new TableColumn(tvWfPara.getTable(), SWT.SINGLE);
		column2.setText("参数类型");
		column2.setWidth(150);

		TableColumn column3 = new TableColumn(tvWfPara.getTable(), SWT.SINGLE);
		column3.setText("输入/输出");
		column3.setWidth(120);

		tvWfPara.setContentProvider(new TableViewerContentProvider());
		tvWfPara.setLabelProvider(new TableViewerLabelProvider());
		tvWfPara.setInput(paraList);

		tvWfPara.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				ParameterEntire para = (ParameterEntire) tvWfPara
						.getElementAt(tvWfPara.getTable().getSelectionIndex());
				if (para.getParaName().equals("bean")
						|| para.getParaName().equals("entityid")
						|| para.getParaName().equals("infor")) {
					btnWorkflowParaEdit.setEnabled(true);
					btnWorkflowParaDelete.setEnabled(true);
				} else {
					btnWorkflowParaEdit.setEnabled(true);
					btnWorkflowParaDelete.setEnabled(true);
				}
			}

		});

		Composite parent2 = new Composite(composite2, SWT.NONE);

		GridLayout layoutpa = new GridLayout();
		layoutpa.marginHeight = 5;
		layoutpa.marginWidth = 20;
		layoutpa.verticalSpacing = 8;

		parent2.setLayout(layoutpa);
		parent2.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridData btnGridData = new GridData();
		btnGridData.horizontalAlignment = GridData.FILL;
		//
		this.btnWorkflowParaCreate = new Button(parent2, SWT.NONE);
		btnWorkflowParaCreate.setLayoutData(btnGridData);
		btnWorkflowParaCreate.setText("新  建");
		btnWorkflowParaCreate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				WorkflowParameterDialog paraDialog = new WorkflowParameterDialog(
						UIPlugin.getDefault().getWorkbench()
								.getActiveWorkbenchWindow().getShell(), null,
						myself, null, null);
				paraDialog.open();
			}
		});

		this.btnWorkflowParaEdit = new Button(parent2, SWT.NONE);
		btnWorkflowParaEdit.setLayoutData(btnGridData);
		btnWorkflowParaEdit.setText("编  辑");
		btnWorkflowParaEdit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (tvWfPara.getTable().getSelectionIndex() < 0) {
					{
						MessageDialog.openConfirm(UIPlugin.getDefault()
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), "请选择一个参数", "请选择一个参数");
						return;
					}
				}
				WorkflowParameterDialog paraDialog = new WorkflowParameterDialog(
						UIPlugin.getDefault().getWorkbench()
								.getActiveWorkbenchWindow().getShell(),
						(ParameterEntire) (tvWfPara.getElementAt(tvWfPara
								.getTable().getSelectionIndex())), myself,
						null, null);
				paraDialog.open();
			}
		});

		this.btnWorkflowParaDelete = new Button(parent2, SWT.NONE);
		btnWorkflowParaDelete.setLayoutData(btnGridData);
		btnWorkflowParaDelete.setText("删  除");
		btnWorkflowParaDelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (tvWfPara.getTable().getSelectionIndex() < 0) {
					{
						MessageDialog.openConfirm(UIPlugin.getDefault()
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), "请选择一个参数", "请选择一个参数");
						return;
					}
				}
				Iterator it = paraList.iterator();
				while (it.hasNext()) {
					ParameterEntire para = (ParameterEntire) it.next();
					if (para.getParaName().equals(
							((ParameterEntire) tvWfPara.getElementAt(tvWfPara
									.getTable().getSelectionIndex()))
									.getParaName())) {
						paraList.remove(para);
						break;
					}
				}
				tvWfPara.getTable().deselectAll();
				refresh();

			}
		});

		setPrioTextContent();
		return parent;

	}

	// set text or combo or tableView value
	private void setPrioTextContent() {
		if (this.subpart instanceof WorkflowDiagram) {
			WorkflowDiagram diagram = (WorkflowDiagram) subpart;
			this.txtWorkflowName.setText(diagram.getName());
			if (diagram.getWfDesc() != null && diagram.getWfDesc().length() > 0) {
				this.txtWorkflowDesc.setText("" + diagram.getWfDesc());
			}
			this.paraList = diagram.getParaList();
		}
	}

	/**
	 * it defind the order when press the OK button
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			WorkflowDiagram diagram = (WorkflowDiagram) subpart;
			
			diagram.setParaList(paraList);
			((MsEditorInput) (UIPlugin.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor().getEditorInput())).setProject(project);
		} else if (buttonId == IDialogConstants.CANCEL_ID) {
			super.cancelPressed();
		}
		super.buttonPressed(buttonId);
	}

	protected GridData setCustomButtonLayoutData(Button button, int hSapn) {
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.heightHint = convertVerticalDLUsToPixels(IDialogConstants.BUTTON_HEIGHT);
		int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint,
				button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		data.horizontalSpan = hSapn;
		button.setLayoutData(data);
		return data;
	}

	public void refresh() {
		tvWfPara.setContentProvider(new TableViewerContentProvider());
		tvWfPara.setLabelProvider(new TableViewerLabelProvider());
		tvWfPara.setInput(paraList);
		tvWfPara.refresh();
	}

}
