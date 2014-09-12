/**
 * @author liuchunxia
 * 
 * workflowBaseActivioty property dialog
 */
package net.ms.designer.editors.workflow.dialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.models.ApplicationActivity;
import net.ms.designer.editors.workflow.models.ComplexActivity;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.ParameterPartial;
import net.ms.designer.editors.workflow.models.StartNode;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.SystemAppActivity;
import net.ms.designer.editors.workflow.models.UserAppActivity;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.projectbuilder.service.ProjectBuilder;
import net.ms.designer.ui.view.MsTreeView;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.UIPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WorkflowBaseActivityPropertyDialog extends TitleAreaDialog {
	private Shell shell;
	private WorkflowDiagram diagram;
	private WorkflowSubPart subpart;
	public List nodeParaList = new ArrayList();
	private List wfParaList;
	public List allApplicationList;
	private String[] wfParaNames, allAppName, allSysAppName, allUserAppName;
	private ApplicationActivity oldApplicationActivity;
	private SubFlowActivity oldSubflowActivity;
	private WorkflowBaseActivityPropertyDialog myself = this;

	// properties about node
	private Text txtNodeDesc;
	private Text txtNodeName;
	private Text txtNodeLocation;
	private Text txtNodeLocationX;
	private Text txtNodeLocationY;
	private Text txtNodeParticipant;
	private Text txtNodeSubFlow;
	private Text txtUserEntryValue;

	// button about node
	private Button btnNodeUser;
	private Button btnNodeBusiness;
	private Button btnNodeRole;
	private Button btnNodeCus;
	private Button btnSubflowCreate;
	private Button btnSetActions;
	private TableViewer tvAppPara;
	private Button btnAppParaCreate, btnAppParaEdit, btnAppParaDelete;
	public List appParaList = new ArrayList();

	// properties about node
	// private Combo comboNodeApplication;
	private Combo comboNodeJoinType; // 通路方式
	private Combo comboNodePar; // 参与方式
	private Combo comboNodePaticipant;
	private Combo comboSubflow;
	private Combo comboExecMode; // 运行模式
	private Combo comboUserEntryType;

	// Tableview
	private TableViewer tvNodePara;

	public WorkflowBaseActivityPropertyDialog(Shell shell,
			WorkflowDiagram diagram, WorkflowSubPart subpart) {
		super(shell);
		this.shell = shell;
		this.diagram = diagram;
		this.subpart = subpart;
		if (((WorkflowBaseActivity)this.subpart).getActivity_actions() != null) 
			this.allApplicationList = ((WorkflowBaseActivity)this.subpart).getActivity_actions();
		if (((WorkflowBaseActivity)this.subpart).getActivity_param() != null) 
			this.nodeParaList = ((WorkflowBaseActivity)this.subpart).getActivity_param();
		// wfParaNames = new String[this.diagram.getParaListbtApplicationList();
		// if(this.subpart instanceof ApplicationActivity)
		// {
		// this.oldApplicationActivity = (ApplicationActivity)subpart;
		// }
		// if(this.subpart instanceof SubFlowActivity)
		// {
		// this.oldSubflowActivity = (SubFlowActivity)subpart;
		// }
	}

	protected Control createDialogArea(Composite parent) {
		if (this.subpart instanceof WorkflowBaseActivity) {
			this.wfParaList = this.diagram.getParaList();
			// 取出全局变量的名字
			Iterator it = wfParaList.iterator();
			int i = 0;
			wfParaNames = new String[wfParaList.size()];
			while (it.hasNext()) {
				ParameterEntire para = (ParameterEntire) it.next();
				wfParaNames[i] = para.getParaName();
				i++;
			}
			this.createNodeArea(parent);
		}

		return parent;
	}

	protected Control createNodeArea(Composite parent) {
		setTitle("节点属性");
		setMessage("设定节点相关属性");

		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);

		Composite mainComp = new Composite(tabFolder, SWT.NULL);

		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 20;
		layout.verticalSpacing = 8;
		mainComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		mainComp.setLayout(layout);

		tabItem_1.setText("常规");
		tabItem_1.setControl(mainComp);
		Composite composite1 = new Composite(mainComp, SWT.NULL);

		GridLayout layout1 = new GridLayout();
		layout1.verticalSpacing = 8;
		layout1.numColumns = 2;
		composite1.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite1.setLayout(layout1);

		GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		GridData txtGridData = new GridData(GridData.FILL_HORIZONTAL);
		GridData btnGridData = new GridData(GridData.FILL_HORIZONTAL);
		GridData comboGridData = new GridData(GridData.FILL_HORIZONTAL);

		Label labelNodeName = new Label(composite1, SWT.NONE);
		labelNodeName
				.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		labelNodeName.setText("节点名称");

		this.txtNodeName = new Text(composite1, SWT.BORDER);
		txtNodeName.setLayoutData(txtGridData);
		Label labelNodeDesc = new Label(composite1, SWT.NONE);
		GridData labelGridData1 = new GridData(GridData.HORIZONTAL_ALIGN_END);
		labelGridData1.verticalSpan = 6;
		labelNodeDesc.setLayoutData(labelGridData1);
		labelNodeDesc.setText("节点描述");

		this.txtNodeDesc = new Text(composite1, SWT.BORDER);
		GridData date = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.FILL_VERTICAL);
		date.verticalSpan = 6;
		txtNodeDesc.setLayoutData(date);

		if (this.subpart instanceof ApplicationActivity) {

			// Label labelNodeApplication = new Label(composite1, SWT.NONE);
			// labelNodeApplication.setLayoutData(new GridData(
			// GridData.HORIZONTAL_ALIGN_END));
			// labelNodeApplication.setText("节点应用");
			//
			// this.comboNodeApplication = new Combo(composite1, SWT.NONE);
			// comboNodeApplication.setLayoutData(comboGridData);
			// comboNodeApplication.addModifyListener(new ModifyListener() {
			//
			// public void modifyText(ModifyEvent e) {
			// if (comboNodeApplication.getSelectionIndex() > -1) {
			// ApplicationActivity appActivityTemp = new ApplicationActivity();
			// Iterator itAllApp = allApplicationList.iterator();
			// while (itAllApp.hasNext()) {
			// ApplicationActivity appActivity = (ApplicationActivity) itAllApp
			// .next();
			// if (appActivity.getApplicationName().equals(
			// comboNodeApplication.getText())) {
			// appActivityTemp = appActivity;
			// break;
			// }
			// }
			// if (!comboNodeApplication.getText().equals(
			// oldApplicationActivity.getApplicationName())) {
			// nodeParaList = new ArrayList();
			//
			// for (int m = 0; m < appActivityTemp
			// .getWfApplicationParam().size(); m++) {
			// ParameterPartial paraPartial = new ParameterPartial();
			// paraPartial
			// .setFormalPara((ParameterEntire) appActivityTemp
			// .getWfApplicationParam().get(m));
			// nodeParaList.add(paraPartial);
			// }
			// } else {
			// nodeParaList = oldApplicationActivity
			// .getActivity_param();
			// }
			// refresh();
			// }
			// }
			//
			// });
		}

		if (this.subpart instanceof UserAppActivity) {
//			Label labelNodePar = new Label(composite1, SWT.NONE);
//			labelNodePar.setLayoutData(new GridData(
//					GridData.HORIZONTAL_ALIGN_END));
//			labelNodePar.setText("参与方式");
//
//			this.comboNodePar = new Combo(composite1, SWT.NONE);
//			comboNodePar.setLayoutData(comboGridData);
//			comboNodePar.add("单人完成", 0);
//			comboNodePar.add("多人完成", 1);
			
			Label labelUserEntryType = new Label(composite1, SWT.NONE);
			labelUserEntryType.setLayoutData(new GridData(
					GridData.HORIZONTAL_ALIGN_END));
			labelUserEntryType.setText("用户界面类型");

			this.comboUserEntryType = new Combo(composite1, SWT.DROP_DOWN | SWT.READ_ONLY);
			comboUserEntryType.setLayoutData(comboGridData);
			comboUserEntryType.add("页面标识", 0);
			comboUserEntryType.add("URL路径", 1);
			if (((UserAppActivity)subpart).getEntryType() != null)
				comboUserEntryType.select(new Integer(((UserAppActivity)subpart).getEntryType()+"").intValue());
			
			Label labelUserEntryValue = new Label(composite1, SWT.NONE);
			labelUserEntryValue
					.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			labelUserEntryValue.setText("用户界面");

			this.txtUserEntryValue = new Text(composite1, SWT.BORDER);
			txtUserEntryValue.setLayoutData(txtGridData);
			if (((UserAppActivity)subpart).getEntryValue() != null)
				txtUserEntryValue.setText(((UserAppActivity)subpart).getEntryValue());
		}

		// if(!(this.subpart instanceof StartNode))
		// {
		// Label labelNodeJoinType = new Label(composite1,SWT.NONE);
		// labelNodeJoinType.setLayoutData(new
		// GridData(GridData.HORIZONTAL_ALIGN_END));
		// labelNodeJoinType.setText("通路方式");
		//
		// this.comboNodeJoinType = new Combo(composite1,SWT.NONE);
		// comboNodeJoinType.setLayoutData(comboGridData);
		// comboNodeJoinType.add("单通",0);
		// comboNodeJoinType.add("全通",1);
		// }

		if (this.subpart instanceof SubFlowActivity) {
			Label labelExecMode = new Label(composite1, SWT.NONE);
			labelExecMode.setLayoutData(new GridData(
					GridData.HORIZONTAL_ALIGN_END));
			labelExecMode.setText("运行模式");

			this.comboExecMode = new Combo(composite1, SWT.DROP_DOWN | SWT.READ_ONLY);
			comboExecMode.setLayoutData(comboGridData);
			comboExecMode.add("同步", 0);
			comboExecMode.add("异步", 1);

			Composite subComp = new Composite(mainComp, SWT.NULL);

			GridLayout sublayout = new GridLayout();
			sublayout.verticalSpacing = 8;
			sublayout.numColumns = 3;
			subComp.setLayoutData(new GridData(GridData.FILL_BOTH));
			subComp.setLayout(sublayout);

			Label labelSubflow = new Label(subComp, SWT.NONE);
			labelSubflow.setLayoutData(new GridData(
					GridData.HORIZONTAL_ALIGN_END));
			labelSubflow.setText("  子流程  ");

			this.comboSubflow = new Combo(subComp, SWT.NONE);
			comboSubflow.setLayoutData(comboGridData);
//			comboSubflow.addModifyListener(new ModifyListener() {
//
//				public void modifyText(ModifyEvent e) {
//					if (comboSubflow.getSelectionIndex() > -1) {
//						SubFlowActivity subflow = new SubFlowActivity();
//						for (int i = 0; i < diagram.getProject()
//								.getSubflowList().size(); i++) {
//							if (comboSubflow.getText().equals(
//									((SubFlowActivity) diagram.getProject()
//											.getSubflowList().get(i))
//											.getSubflowName())) {
//								subflow = (SubFlowActivity) diagram
//										.getProject().getSubflowList().get(i);
//								break;
//							}
//						}
//						if (!comboSubflow.getText().equals(
//								oldSubflowActivity.getSubflowName())) {
//							nodeParaList = new ArrayList();
//							for (int j = 0; j < subflow.getSubflowParam()
//									.size(); j++) {
//								ParameterPartial paraPartial = new ParameterPartial();
//								paraPartial
//										.setFormalPara((ParameterEntire) subflow
//												.getSubflowParam().get(j));
//								nodeParaList.add(paraPartial);
//							}
//						} else {
//							nodeParaList = oldSubflowActivity.getSubflowParam();
//						}
//					}
//				}
//
//			});
			if (((SubFlowActivity)this.subpart).getSubflowName() != null) {
				this.comboSubflow.setText(((SubFlowActivity)this.subpart).getSubflowName());
			}

//			this.btnSubflowCreate = new Button(subComp, SWT.NONE);
//			btnSubflowCreate.setText("创建");
//			btnSubflowCreate.setLayoutData(new GridData(
//					GridData.HORIZONTAL_ALIGN_END));
//			btnSubflowCreate.addSelectionListener(new SelectionListener() {
//
//				public void widgetSelected(SelectionEvent e) {
//					MsEditorInput input = new MsEditorInput();
//					Date da = new Date();
//					long time = da.getTime();
//					SubFlowActivity subflow = (SubFlowActivity) subpart;
//
//					MsProject subProject = diagram.getProject();
//					subProject.setSubflowId(Long.toString(time));
//					subProject.setSubflowName(subflow.getName());
//					subflow.setSubflowId(Long.toString(time));
//					subflow.setSubflowName(subflow.getName());
//
//					if (subProject.getSubflowList() == null
//							|| subProject.getSubflowList().size() < 1) {
//						List subflowList = new ArrayList();
//						subProject.setSubflowList(subflowList);
//					}
//
//					subProject.getSubflowList().add(subflow);
//
//					diagram.setProject(subProject);
//					input.setName(subflow.getName());
//					input.setIsSubflow(true);
//					input.setContainer(((MsEditorInput) UIPlugin.getDefault()
//							.getWorkbench().getActiveWorkbenchWindow()
//							.getActivePage().getActiveEditor().getEditorInput())
//							.getContainer());
//					input.setProject(subProject);
//
//					((MsTreeView) UIPlugin.getDefault().getWorkbench()
//							.getActiveWorkbenchWindow().getActivePage()
//							.findView("net.ms.designer.ui.view.CEECTreeView")).projectAndEditorInput
//							.put(subProject.getProjectName() + "."
//									+ subProject.getPackageName() + "."
//									+ subProject.getComponentName() + "."
//									+ subProject.getWorkflowName() + "."
//									+ subProject.getSubflowName(), input);
//					IWorkbenchPage page = UIPlugin.getDefault().getWorkbench()
//							.getActiveWorkbenchWindow().getActivePage();
//					try {
//						myself.close();
//						page.openEditor(input,
//								"net.ms.designer.editors.workflow.ui.WorkflowEditor");
//					} catch (PartInitException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//
//				public void widgetDefaultSelected(SelectionEvent e) {
//					// TODO Auto-generated method stub
//
//				}
//
//			});
		}

		if (this.subpart instanceof ApplicationActivity
				|| this.subpart instanceof SubFlowActivity) {
			TabItem tabItem_2 = new TabItem(tabFolder, SWT.NONE);

			Composite composite2 = new Composite(tabFolder, SWT.NONE);
			GridLayout layout2 = new GridLayout();
			layout2.marginHeight = 5;
			layout2.marginWidth = 20;
			layout2.verticalSpacing = 8;
			layout2.numColumns = 1;
			composite2.setLayoutData(new GridData(GridData.FILL_BOTH));
			composite2.setLayout(layout2);
			tabItem_2.setText("参数");
			tabItem_2.setControl(composite2);

			tvNodePara = new TableViewer(composite2, SWT.H_SCROLL | SWT.V_SCROLL
					| SWT.BORDER | SWT.FULL_SELECTION);
			tvNodePara.getTable().setHeaderVisible(true);
			tvNodePara.getTable().setLinesVisible(true);

			TableLayout tLayout = new TableLayout();
			tvNodePara.getTable().setLayout(tLayout);
			GridData tvAppParaGridData = new GridData(GridData.FILL_BOTH);
			tvAppParaGridData.verticalSpan = 15;
			tvNodePara.getTable().setLayoutData(tvAppParaGridData);

			TableColumn column1 = new TableColumn(tvNodePara.getTable(),
					SWT.SINGLE);
			column1.setText("参数名称");
			column1.setWidth(120);

			TableColumn column2 = new TableColumn(tvNodePara.getTable(),
					SWT.SINGLE);
			column2.setText("参数类型");
			column2.setWidth(120);

			TableColumn column3 = new TableColumn(tvNodePara.getTable(),
					SWT.SINGLE);
			column3.setText("输入/输出");
			column3.setWidth(120);

			tvNodePara.setContentProvider(new TableViewerContentProvider());
			tvNodePara.setLabelProvider(new TableViewerLabelProvider());
			tvNodePara.setInput(nodeParaList);

			Composite btnComposite = new Composite(composite2, SWT.NONE);
			btnComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			GridLayout btnComLayout = new GridLayout();
			btnComLayout.numColumns = 3;
			btnComLayout.makeColumnsEqualWidth = true;

			btnComposite.setLayout(btnComLayout);

			this.btnAppParaCreate = new Button(btnComposite, SWT.NONE);
			// btnAppCreate.setLayoutData(btnGridData);
			btnAppParaCreate.setText(" 新    建 ");
			btnAppParaCreate.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					WorkflowParameterDialog paraDialog = new WorkflowParameterDialog(
							UIPlugin.getDefault().getWorkbench()
									.getActiveWorkbenchWindow().getShell(),
							null, null, null, myself);
					paraDialog.open();
				}
			});

			this.btnAppParaEdit = new Button(btnComposite, SWT.NONE);
			// btnAppEdit.setLayoutData(btnGridData);
			btnAppParaEdit.setText(" 编    辑 ");
			btnAppParaEdit.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (tvNodePara.getTable().getSelectionIndex() < 0) {
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
							(ParameterEntire) (tvNodePara.getElementAt(tvNodePara
									.getTable().getSelectionIndex())), null,
							null, myself);
					paraDialog.open();
				}
			});

			this.btnAppParaDelete = new Button(btnComposite, SWT.NONE);
			btnAppParaDelete.setText(" 删    除 ");
			btnAppParaDelete.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (tvNodePara.getTable().getSelectionIndex() < 0) {
						{
							MessageDialog.openConfirm(UIPlugin.getDefault()
									.getWorkbench().getActiveWorkbenchWindow()
									.getShell(), "请选择一个参数", "请选择一个参数");
							return;
						}
					}
					Iterator it = appParaList.iterator();
					while (it.hasNext()) {
						ParameterEntire para = (ParameterEntire) it.next();
						if (para.getParaName().equals(
								((ParameterEntire) tvNodePara
										.getElementAt(tvNodePara.getTable()
												.getSelectionIndex()))
										.getParaName())) {
							tvNodePara.remove(para);
							break;
						}
					}
					refresh();
				}
			});

			if (this.subpart instanceof UserAppActivity) {
				TabItem tabItem_3 = new TabItem(tabFolder, SWT.NONE);

				Composite composite3 = new Composite(tabFolder, SWT.NULL);
				GridLayout layout3 = new GridLayout();
				layout3.marginHeight = 5;
				layout3.marginWidth = 20;
				layout3.verticalSpacing = 8;
				layout3.numColumns = 1;
				composite3.setLayoutData(new GridData(GridData.FILL_BOTH));
				composite3.setLayout(layout3);
				tabItem_3.setText("执行者");
				tabItem_3.setControl(composite3);

				this.btnNodeUser = new Button(composite3, SWT.RADIO | SWT.NONE);
				btnNodeUser.setLayoutData(btnGridData);
				btnNodeUser.setText("指定某个用户");
				if (((UserAppActivity)subpart).getPolicyType() != null && ((UserAppActivity)subpart).getPolicyType() == 1L) {
					btnNodeUser.setSelection(true);
				}

				this.btnNodeBusiness = new Button(composite3, SWT.RADIO
						| SWT.NONE);
				btnNodeBusiness.setLayoutData(btnGridData);
				btnNodeBusiness.setText("指定某种职位");

				this.btnNodeRole = new Button(composite3, SWT.RADIO | SWT.NONE);
				btnNodeRole.setLayoutData(btnGridData);
				btnNodeRole.setText("指定某种角色");
				if (((UserAppActivity)subpart).getPolicyType() != null && ((UserAppActivity)subpart).getPolicyType() == 0L) {
					btnNodeRole.setSelection(true);
				}

				this.btnNodeCus = new Button(composite3, SWT.RADIO | SWT.NONE);
				btnNodeCus.setLayoutData(btnGridData);
				btnNodeCus.setText("自定义");

				Composite subComposite = new Composite(composite3, SWT.NULL);
				GridLayout sublayout = new GridLayout();
				sublayout.marginHeight = 5;
				sublayout.marginWidth = 0;
				sublayout.verticalSpacing = 8;
				sublayout.numColumns = 2;
				subComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
				subComposite.setLayout(sublayout);

				Label labelPaticipant = new Label(subComposite, SWT.NONE);
				labelPaticipant.setLayoutData(labelGridData);
				labelPaticipant.setText("执行者");

				this.comboNodePaticipant = new Combo(subComposite, SWT.NONE);
				comboNodePaticipant.setLayoutData(comboGridData);
				if (((UserAppActivity)subpart).getPolicyValue() != null)
					comboNodePaticipant.setText(((UserAppActivity)subpart).getPolicyValue());

//				TabItem tabItem_4 = new TabItem(tabFolder, SWT.NONE);
//
//				Composite composite4 = new Composite(tabFolder, SWT.NULL);
//				GridLayout layout4 = new GridLayout();
//				layout4.marginHeight = 5;
//				layout4.marginWidth = 20;
//				layout4.verticalSpacing = 8;
//				layout4.numColumns = 2;
//				composite4.setLayoutData(new GridData(GridData.FILL_BOTH));
//				composite4.setLayout(layout4);
//				tabItem_4.setText("字段权限");
//				tabItem_4.setControl(composite4);

			}

		}

		btnSetActions = new Button(composite1, SWT.NONE);
		btnSetActions.setText(" 设置环节应用 ");
		btnSetActions.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ApplicationListDialog dialog = new ApplicationListDialog(shell,
						subpart, myself);
				dialog.open();
			}
		});
		this.setPrioTextContent();
		return parent;

	}

	// set text or combo or tableView value
	private void setPrioTextContent() {
		if (this.subpart instanceof WorkflowBaseActivity) {
			WorkflowBaseActivity allNode = (WorkflowBaseActivity) this.subpart;
			this.txtNodeName.setText(allNode.getName());
			this.txtNodeDesc.setText("" + allNode.getActivity_desc());
			// 连接类型初值
			if (!(this.subpart instanceof StartNode)) {
				if (allNode.getActivity_join_type() != null
						&& allNode.getActivity_join_type().length() > 0) {
					int joinType = new Integer(allNode.getActivity_join_type())
							.intValue();
					this.comboNodeJoinType.select(joinType);
				}
			}
			// 用户节点参与方式(即完成方式)初值
			if (allNode instanceof UserAppActivity) {
				UserAppActivity userApp = (UserAppActivity) allNode;
				if (userApp.getActivity_finish_type() != null
						&& userApp.getActivity_finish_type().length() > 0) {
					int finishType = new Integer(
							userApp.getActivity_finish_type()).intValue();
					this.comboNodePar.select(finishType);
				}
			}
			// 子流执行模式 ,子流程
			if (this.subpart instanceof SubFlowActivity) {
				SubFlowActivity subFlow = (SubFlowActivity) this.subpart;
				int execMode = new Integer(subFlow.getExecMode()).intValue();
				this.comboExecMode.select(execMode);

//				if (this.diagram.getProject().getSubflowList() != null
//						&& this.diagram.getProject().getSubflowList().size() > 0) {
//					List allSubflow = this.diagram.getProject()
//							.getSubflowList();
//					String[] allSubflowName = new String[allSubflow.size()];
//
//					for (int i = 0; i < allSubflow.size(); i++) {
//						allSubflowName[i] = ((SubFlowActivity) allSubflow
//								.get(i)).getName();
//
//					}
//					this.comboSubflow.setItems(allSubflowName);
//				}
//				if (subFlow.getSubflowId() != null) {
//					this.comboSubflow.select(this.comboSubflow.indexOf(subFlow
//							.getSubflowName()));
//					for (int i = 0; i < this.diagram.getProject()
//							.getSubflowList().size(); i++) {
//						if (subFlow.getSubflowId().equals(
//								((SubFlowActivity) this.diagram.getProject()
//										.getSubflowList().get(i))
//										.getSubflowId())) {
//							this.nodeParaList = ((SubFlowActivity) this.diagram
//									.getProject().getSubflowList().get(i))
//									.getSubflowParam();
//						}
//
//					}
//				}
			}
			// 应用节点
			if (this.subpart instanceof ApplicationActivity) {
				this.allSysAppName = new String[0];
				this.allUserAppName = new String[0];
				// 系统中有已经定义好的应用,应用列表非空
//				if (allApplicationList != null && allApplicationList.size() > 0) {
//					this.allSysApplicationList = new ArrayList();
//					this.allUserApplicationList = new ArrayList();
//					this.allAppName = new String[allApplicationList.size()];
//					// 取出所有应用的名称
//					for (int i = 0; i < allApplicationList.size(); i++) {
//						ApplicationActivity app = (ApplicationActivity) allApplicationList
//								.get(i);
//						if (app.getApplicationType() != null
//								&& app.getApplicationType().equals(
//										Constants.WF_APPLICATION_TYPE_USER))
//							this.allUserApplicationList.add(app);
//						if (app.getApplicationType() != null
//								&& app.getApplicationType().equals(
//										Constants.WF_APPLICATION_TYPE_SYS))
//							this.allSysApplicationList.add(app);
//						allAppName[i] = app.getApplicationName();
//					}
//
//					// 取出所有用户应用名称
//					this.allUserAppName = new String[allUserApplicationList
//							.size()];
//					for (int j = 0; j < allUserApplicationList.size(); j++) {
//						ApplicationActivity userAppActivity = (ApplicationActivity) allUserApplicationList
//								.get(j);
//						allUserAppName[j] = userAppActivity
//								.getApplicationName();
//					}
//					// 取出所有系统应用名称
//					this.allSysAppName = new String[allSysApplicationList
//							.size()];
//					for (int k = 0; k < allSysApplicationList.size(); k++) {
//						ApplicationActivity sysAppActivity = (ApplicationActivity) allSysApplicationList
//								.get(k);
//						allSysAppName[k] = sysAppActivity.getApplicationName();
//					}
//				}

				// 系统应用节点
				ApplicationActivity applicationActivity = (ApplicationActivity) this.subpart;
				if (applicationActivity instanceof SystemAppActivity) {
					// this.comboNodeApplication.setItems(this.allSysAppName);
					// 有初值
					if (applicationActivity.getApplicationType() != null
							&& applicationActivity.getApplicationType().equals(
									Constants.WF_APPLICATION_TYPE_SYS)
							&& applicationActivity.getApplicationName() != null
							&& applicationActivity.getApplicationName()
									.length() > 0) {
						// 选择相应应用
						// this.comboNodeApplication
						// .select(this.comboNodeApplication
						// .indexOf(applicationActivity
						// .getApplicationName()));
						// 给tableviewer赋值
						if (applicationActivity.getActivity_param() != null
								&& applicationActivity.getActivity_param()
										.size() > 0) {
							this.nodeParaList = applicationActivity
									.getActivity_param();
						}
					}
				}
				// 用户应用节点
				if (applicationActivity instanceof UserAppActivity) {
					// this.comboNodeApplication.setItems(this.allUserAppName);
					// 有初值
					if (applicationActivity.getApplicationType() != null
							&& applicationActivity.getApplicationType().equals(
									Constants.WF_APPLICATION_TYPE_USER)
							&& applicationActivity.getApplicationName() != null
							&& applicationActivity.getApplicationName()
									.length() > 0) {
						// this.comboNodeApplication
						// .select(this.comboNodeApplication
						// .indexOf(applicationActivity
						// .getApplicationName()));
						if (applicationActivity.getActivity_param() != null
								&& applicationActivity.getActivity_param()
										.size() > 0) {
							this.nodeParaList = applicationActivity
									.getActivity_param();
						}
					}
				}
				this.refresh();

			}
		}
	}

	/**
	 * it defind the order when press the OK button
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			WorkflowBaseActivity base = (WorkflowBaseActivity) this.subpart;
			base.setName(this.txtNodeName.getText());
			base.setActivity_desc(this.txtNodeDesc.getText());
			if (nodeParaList != null && nodeParaList.size() > 0)
				base.setActivity_param((ArrayList) nodeParaList);
			if (!(this.subpart instanceof StartNode)) {
				// if (this.comboNodeJoinType.getSelectionIndex() > -1)
				// base.setActivity_join_type((new Integer(
				// this.comboNodeJoinType.getSelectionIndex()))
				// .toString());
			}
			if (this.subpart instanceof UserAppActivity) {
//				if (this.comboNodePar.getSelectionIndex() > -1)
//					base.setActivity_finish_type((new Integer(this.comboNodePar
//							.getSelectionIndex())).toString());
				if (this.btnNodeRole.getSelection()) {
					((UserAppActivity)base).setPolicyType(0L);
				}
				if (this.btnNodeUser.getSelection()) {
					((UserAppActivity)base).setPolicyType(1L);
				}
				((UserAppActivity)base).setPolicyValue(this.comboNodePaticipant.getText());
				((UserAppActivity)base).setEntryType(new Long(this.comboUserEntryType.getSelectionIndex()));
				((UserAppActivity)base).setEntryValue(this.txtUserEntryValue.getText());
			}
			if (this.subpart instanceof SubFlowActivity) {
				SubFlowActivity subFlowActivity = (SubFlowActivity) this.subpart;
//				if (this.comboExecMode.getSelectionIndex() > -1) {
//					subFlowActivity.setExecMode((new Integer(this.comboExecMode
//							.getSelectionIndex())).toString());
//				}
//				SubFlowActivity subTemp = new SubFlowActivity();
//				if (this.comboSubflow.getSelectionIndex() > -1) {
//					for (int k = 0; k < this.diagram.getProject()
//							.getSubflowList().size(); k++) {
//						if (this.comboSubflow.getText().equals(
//								((SubFlowActivity) this.diagram.getProject()
//										.getSubflowList().get(k))
//										.getSubflowName())) {
//							subTemp = (SubFlowActivity) this.diagram
//									.getProject().getSubflowList().get(k);
//							break;
//						}
//					}
//				}
//				subFlowActivity.setSubflowDiagram(subTemp.getSubflowDiagram());
//				subFlowActivity.setSubflowId(subTemp.getSubflowId());
//				subFlowActivity.setSubflowName(subTemp.getSubflowName());
//				subFlowActivity.setSubflowParam(subTemp.getSubflowParam());
//				subFlowActivity.setSubflowPath(subTemp.getSubflowPath());
				subFlowActivity.setSubflowName(this.comboSubflow.getText());
			}
			base.setActivity_actions(allApplicationList);
			base.setActivity_param(nodeParaList);
		} else if (buttonId == IDialogConstants.CANCEL_ID) {
			super.cancelPressed();
		}
		super.buttonPressed(buttonId);
	}

	// private class CEECCellModifier implements ICellModifier {
	//
	// public boolean canModify(Object element, String property) {
	// return true;
	// }
	//
	// public Object getValue(Object element, String property) {
	// ParameterPartial o = (ParameterPartial) element;
	// if (property.equals("0")) {
	// return null;
	// } else if (property.equals("1")) {
	// return new Integer(0);
	// }
	// return null;
	// }
	//
	// public void modify(Object element, String property, Object value) {
	// TableItem tableItem = (TableItem) element;
	// ParameterPartial o = (ParameterPartial) tableItem.getData();
	// if (property.equals("1")) {
	// Integer newValue = (Integer) value;
	// if (newValue.intValue() > -1) {
	// String name = wfParaNames[newValue.intValue()];
	// // ParameterEntire para = o.getRealPara();
	// // if(para == null)
	// // {
	// // para = new ParameterEntire();
	// // }
	// Iterator it = wfParaList.iterator();
	// ParameterEntire paraDiagram = new ParameterEntire();
	// while (it.hasNext()) {
	// ParameterEntire para = (ParameterEntire) it.next();
	// if (para.getParaName().equals(name)) {
	// paraDiagram = para;
	// break;
	// }
	// }
	// o.setRealPara(paraDiagram);
	// }
	// }
	// refresh();
	// }
	//
	// }

	public void refresh() {
		if (tvNodePara != null) {
			tvNodePara.setContentProvider(new TableViewerContentProvider());
			tvNodePara.setLabelProvider(new TableViewerLabelProvider());
			nodeParaList = nodeParaList != null ? nodeParaList
					: new ArrayList();
			tvNodePara.setInput(nodeParaList);
			tvNodePara.refresh();
		}
	}

}
