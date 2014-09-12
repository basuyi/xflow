package net.ms.designer.editors.workflow.dialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.ms.designer.core.MsEditorInput;
import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.models.ApplicationActivity;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.editors.workflow.xmlparse.ReadFromApplicationXML;
import net.ms.designer.editors.workflow.xmlparse.WriteToApplicationsXML;
import net.ms.designer.projectbuilder.service.ProjectBuilder;

import org.basuyi.xflow.model.Activity;
import org.basuyi.xflow.model.WfAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConditionListDialog extends TitleAreaDialog {

	private Shell shell;

	// tableView
	public TableViewer tvApplication; // all applications

	// Button
	private Button btnSysApp;
	private Button btnUserApp;
	private Button btnAppCreate, btnAppEdit, btnAppDelete;
	public WorkflowBaseActivityPropertyDialog activityPropDialog;
	private ConditionListDialog appListDialog = this;

	public ConditionListDialog(Shell parentShell, WorkflowSubPart subpart, WorkflowBaseActivityPropertyDialog parent) {
		super(parentShell);
		this.shell = parentShell;
		this.activityPropDialog = parent;
		// ReadFromApplicationXML read = new ReadFromApplicationXML();
		// String path =
		// ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"\\applications.xml";
		try {
			// this.allApplicationList = read.readFromApplicationXML(path);
			if (parent.allApplicationList != null) {
				return;
			}
			parent.allApplicationList = getActivityActionsFromDB(subpart);
			if (parent.allApplicationList == null || parent.allApplicationList.size() < 1) {
				parent.allApplicationList = new ArrayList();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List getActivityActionsFromDB(WorkflowSubPart subpart) {
		List applicationList = new ArrayList();
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"workflow_context.xml");
		ProjectBuilder projectBuilder = (ProjectBuilder) context
				.getBean("projectBuilderProxy");
		Activity activity = new Activity();
		activity.setActivityId(subpart.getName());
		Set<WfAction> actions = projectBuilder.getActivityActions(activity);
		for (WfAction action : actions) {
			ApplicationActivity applicationActivity = new ApplicationActivity();
			applicationActivity.setApplicationId(action.getActionId());
			applicationActivity.setApplicationName(action.getActionName());
			applicationActivity.setApplicationDesc(action.getActionDesc());
			// applicationActivity.setApplicationType(action.getActionType());
			applicationList.add(applicationActivity);
		}
		return applicationList;
	}

	protected Control createDialogArea(Composite parent) {
		setTitle("����Ӧ���б�");
		setMessage(" ���û���Ӧ��");

		GridLayout listlayout = new GridLayout();
		listlayout.numColumns = 1;
		listlayout.marginHeight = 5;
		listlayout.marginWidth = 20;
		listlayout.verticalSpacing = 8;

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(listlayout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		// GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		// GridData txtGridData = new GridData(GridData.FILL_HORIZONTAL);
		GridData btnGridData = new GridData(GridData.FILL_HORIZONTAL);

		GridLayout groupLayout = new GridLayout();
		groupLayout.marginWidth = 20;
		groupLayout.marginHeight = 5;
		groupLayout.numColumns = 2;

		// Group group1 = new Group(composite,SWT.NONE);
		// group1.setText("Ӧ������");
		// group1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// group1.setLayout(groupLayout);
		//
		// this.btnSysApp = new Button(group1,SWT.RADIO);
		// btnSysApp.setLayoutData(btnGridData);
		// btnSysApp.setText("ϵͳӦ��");
		// btnSysApp.setSelection(false);
		// btnSysApp.addSelectionListener(new MyAppTypeSelectionListener());
		//
		// this.btnUserApp = new Button(group1,SWT.RADIO);
		// btnUserApp.setLayoutData(btnGridData);
		// btnUserApp.setText("�û�Ӧ��");
		// btnUserApp.setSelection(false);
		// btnUserApp.addSelectionListener(new MyAppTypeSelectionListener());

		Composite subComposite = new Composite(composite, SWT.NONE);
		GridLayout subLayout = new GridLayout();
		// subLayout.numColumns = 2;
		subLayout.marginHeight = 0;
		subLayout.marginWidth = 0;
		subLayout.verticalSpacing = 8;
		subComposite.setLayout(subLayout);
		subComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		tvApplication = new TableViewer(subComposite, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		tvApplication.getTable().setHeaderVisible(true);
		tvApplication.getTable().setLinesVisible(true);

		TableLayout tLayout = new TableLayout();
		tvApplication.getTable().setLayout(tLayout);
		GridData tvGridData = new GridData(GridData.FILL_BOTH);
		tvGridData.verticalSpan = 12;
		tvApplication.getTable().setLayoutData(tvGridData);

		TableColumn column1 = new TableColumn(tvApplication.getTable(),
				SWT.SINGLE);
		column1.setText("Ӧ������"); //$NON-NLS-1$
		column1.setWidth(190);

		TableColumn column2 = new TableColumn(tvApplication.getTable(),
				SWT.SINGLE);
		column2.setText("Ӧ������"); //$NON-NLS-1$
		column2.setWidth(190);

		tvApplication.setContentProvider(new TableViewerContentProvider());
		tvApplication
				.setLabelProvider(new ApplicationTableViewerLabelProvider());
		tvApplication.setInput(this.activityPropDialog.allApplicationList);

		Composite btnComposite = new Composite(composite, SWT.NONE);
		btnComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout btnComLayout = new GridLayout();
		btnComLayout.numColumns = 3;
		// btnComLayout.marginHeight = 5;
		// btnComLayout.marginWidth = 25;
		btnComLayout.makeColumnsEqualWidth = true;
		// btnComLayout.verticalSpacing = 50;

		btnComposite.setLayout(btnComLayout);

		this.btnAppCreate = new Button(btnComposite, SWT.NONE);
		// btnAppCreate.setLayoutData(btnGridData);
		btnAppCreate.setText(" ��    �� ");
		btnAppCreate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// ApplicationActivity application = new ApplicationActivity();
				// Date da = new Date();
				// long time = da.getTime();
				// application.setApplicationId(Long.toString(time));
				ConditionDialog appDialog = new ConditionDialog(UIPlugin
						.getDefault().getWorkbench().getActiveWorkbenchWindow()
						.getShell(), null, appListDialog);
				appDialog.open();
			}
		});

		this.btnAppEdit = new Button(btnComposite, SWT.NONE);
		// btnAppEdit.setLayoutData(btnGridData);
		btnAppEdit.setText(" ��    �� ");
		btnAppEdit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (tvApplication.getTable().getSelectionIndex() < 0) {
					{
						MessageDialog.openConfirm(UIPlugin.getDefault()
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), "��ѡ��һ��Ӧ��", "��ѡ��һ��Ӧ��");
						return;
					}
				}
				ConditionDialog appDialog = new ConditionDialog(UIPlugin
						.getDefault().getWorkbench().getActiveWorkbenchWindow()
						.getShell(), (ApplicationActivity) (tvApplication
						.getElementAt(tvApplication.getTable()
								.getSelectionIndex())), appListDialog);
				appDialog.open();
			}
		});

		this.btnAppDelete = new Button(btnComposite, SWT.NONE);
		btnAppDelete.setText(" ɾ    �� ");
		btnAppDelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (tvApplication.getTable().getSelectionIndex() < 0) {
					{
						MessageDialog.openConfirm(UIPlugin.getDefault()
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), "��ѡ��һ��Ӧ��", "��ѡ��һ��Ӧ��");
						return;
					}
				}
				Iterator it = appListDialog.activityPropDialog.allApplicationList.iterator();
				while (it.hasNext()) {
					ApplicationActivity app = (ApplicationActivity) it.next();
					if (app.getApplicationId().equals(
							((ApplicationActivity) tvApplication
									.getElementAt(tvApplication.getTable()
											.getSelectionIndex()))
									.getApplicationId())) {
						appListDialog.activityPropDialog.allApplicationList.remove(app);
						break;
					}
				}
				tvApplication.setInput(appListDialog.activityPropDialog.allApplicationList);
				refresh();
			}
		});

		return parent;

	}

	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			if (this.activityPropDialog.allApplicationList != null
					&& this.activityPropDialog.allApplicationList.size() > 0) {
				WriteToApplicationsXML write = new WriteToApplicationsXML(
						this.activityPropDialog.allApplicationList);
				try {
					write.writeApplicationsXML(ResourcesPlugin.getWorkspace()
							.getRoot().getLocation().toOSString()
							+ "\\applications.xml");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (UIPlugin.getDefault().getWorkbench()
						.getActiveWorkbenchWindow().getActivePage()
						.getActiveEditor() != null) {
					if ((UIPlugin.getDefault().getWorkbench()
							.getActiveWorkbenchWindow().getActivePage()
							.getActiveEditor().getEditorInput()) instanceof MsEditorInput)
						((MsEditorInput) (UIPlugin.getDefault().getWorkbench()
								.getActiveWorkbenchWindow().getActivePage()
								.getActiveEditor().getEditorInput()))
								.getProject().setApplicationList(
										activityPropDialog.allApplicationList);
				}
			}
		} else if (buttonId == IDialogConstants.CANCEL_ID) {
			super.cancelPressed();
		}
		super.buttonPressed(buttonId);
	}

	public void refresh() {
		tvApplication.setContentProvider(new TableViewerContentProvider());
		tvApplication
				.setLabelProvider(new ApplicationTableViewerLabelProvider());
		
		tvApplication.setInput(activityPropDialog.allApplicationList);

		tvApplication.refresh();
	}

	public class MyAppTypeSelectionListener implements SelectionListener {

		public void widgetSelected(SelectionEvent e) {
			Button button = (Button) e.getSource();
			if (button.getText().equals("ϵͳӦ��")
					&& button.getSelection() == true) {
				btnSysApp.setSelection(true);
				btnUserApp.setSelection(false);
				// sysApplicationList = new ArrayList();
				// Iterator itSys = allApplicationList.iterator();
				// while(itSys.hasNext())
				// {
				// ApplicationActivity applicationActivity =
				// (ApplicationActivity)itSys.next();
				// if(applicationActivity.getApplicationType() != null &&
				// applicationActivity.getApplicationType().equals(Constants.WF_APPLICATION_TYPE_SYS))
				// {
				// sysApplicationList.add(applicationActivity);
				// }
				// }
				// tvApplication.setInput(sysApplicationList);
				refresh();
			}
			if (button.getText().equals("�û�Ӧ��")
					&& button.getSelection() == true) {
				btnSysApp.setSelection(false);
				btnUserApp.setSelection(true);
				// userApplicationList = new ArrayList();
				// Iterator itUser = allApplicationList.iterator();
				// while(itUser.hasNext())
				// {
				// ApplicationActivity applicationActivity =
				// (ApplicationActivity)itUser.next();
				// if(applicationActivity.getApplicationType() != null &&
				// applicationActivity.getApplicationType().equals(Constants.WF_APPLICATION_TYPE_USER))
				// {
				// userApplicationList.add(applicationActivity);
				// }
				// }
				// tvApplication.setInput(userApplicationList);
				refresh();
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}

	}

}
