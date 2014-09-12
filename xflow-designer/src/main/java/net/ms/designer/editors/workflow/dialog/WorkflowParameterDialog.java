package net.ms.designer.editors.workflow.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.Messages;
import net.ms.designer.editors.workflow.models.ParameterEntire;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.UIPlugin;

public class WorkflowParameterDialog extends TitleAreaDialog {

	Shell shell;
	Text txtName;
	Combo comboType, comboInputOrOutput;
	String[] strInputOrOutput = new String[] { "输入", "输出", "输入+输出" };
	WorkflowPropertyDialog workflowPropertyDialog;
	ApplicationDialog applicationDialog;
	WorkflowBaseActivityPropertyDialog activityPropDialog;
//	ConditionDialog conditionDialog;

	private ParameterEntire para;

	public WorkflowParameterDialog(Shell parentShell, ParameterEntire para,
			WorkflowPropertyDialog workflowPropertyDialog,
			ApplicationDialog applicationDialog,
			WorkflowBaseActivityPropertyDialog activityPropDialog) {
		super(parentShell);
		this.shell = parentShell;
		this.para = para;
		this.workflowPropertyDialog = workflowPropertyDialog;
		this.applicationDialog = applicationDialog;
		this.activityPropDialog = activityPropDialog;
//		this.conditionDialog = conditionDialog;
	}

	protected Control createDialogArea(Composite parent) {
		setTitle("参数属性");
		setMessage("设置参数属性");

		Composite composite = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 20;
		layout.verticalSpacing = 8;
		layout.numColumns = 2;
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(layout);

		GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		GridData txtGridData = new GridData(GridData.FILL_HORIZONTAL);

		Label labelName = new Label(composite, SWT.NONE);
		labelName.setLayoutData(labelGridData);
		labelName.setText("参数名称");

		this.txtName = new Text(composite, SWT.BORDER);
		txtName.setLayoutData(txtGridData);

		Label labelType = new Label(composite, SWT.NONE);
		labelType.setLayoutData(labelGridData);
		labelType.setText("参数类型");

		this.comboType = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		comboType.setLayoutData(txtGridData);

		Label labelInputOrOutput = new Label(composite, SWT.NONE);
		labelInputOrOutput.setLayoutData(labelGridData);
		labelInputOrOutput.setText("输入/输出");

		this.comboInputOrOutput = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		comboInputOrOutput.setLayoutData(txtGridData);
		comboInputOrOutput.setItems(this.strInputOrOutput);
		if (this.para != null) {
			if (this.para.getIsInput() == true
					&& this.para.getIsOutput() == false)
				comboInputOrOutput.select(comboInputOrOutput.indexOf("输入"));
			if (this.para.getIsInput() == false
					&& this.para.getIsOutput() == true)
				comboInputOrOutput.select(comboInputOrOutput.indexOf("输出"));
			if (this.para.getIsInput() == true
					&& this.para.getIsOutput() == true)
				comboInputOrOutput.select(comboInputOrOutput.indexOf("输入+输出"));
		}

		if (this.para != null) {
			txtName.setText(this.para.getParaName());
			comboType.setItems(this.para.getParaAllType());
			// if(para.getParaType().equals(Constants.WF_PARA_TYPE_BOOLEAN))
			// comboType.select(comboType.indexOf(Messages.getString("WorkflowParameterType.boolean")));
			if (para.getParaType().equals(Constants.WF_PARA_TYPE_DATE))
				comboType.select(comboType.indexOf(Messages
						.getString("WorkflowParameterType.date")));
			if (para.getParaType().equals(Constants.WF_PARA_TYPE_NONE))
				comboType.select(comboType.indexOf(Messages
						.getString("WorkflowParameterType.none")));
			if (para.getParaType().equals(Constants.WF_PARA_TYPE_DOUBLE))
				comboType.select(comboType.indexOf(Messages
						.getString("WorkflowParameterType.double")));
			if (para.getParaType().equals(Constants.WF_PARA_TYPE_LONG))
				comboType.select(comboType.indexOf(Messages
						.getString("WorkflowParameterType.long")));
			if (para.getParaType().equals(Constants.WF_PARA_TYPE_STRING))
				comboType.select(comboType.indexOf(Messages
						.getString("WorkflowParameterType.string")));
			if (para.getParaType().equals(Constants.WF_PARA_TYPE_OBJECT))
				comboType.select(comboType.indexOf(Messages
						.getString("WorkflowParameterType.object")));
		}
		if (this.para == null) {
			txtName.setText("");
			this.para = new ParameterEntire();
			comboType.setItems(this.para.getParaAllType());
		}

		return parent;
	}

	public void okPressed() {
		if (this.txtName.getText() == null
				|| this.txtName.getText().length() < 1) {
			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getShell(), "参数名不能为空",
					"参数名不能为空");
			return;
		}

		if (this.txtName.getText() != null
				&& this.txtName.getText().length() > 0
				&& this.workflowPropertyDialog != null) {
			Iterator itWfParaList = workflowPropertyDialog.paraList.iterator();
			List paraList = new ArrayList();
			while (itWfParaList.hasNext()) {
				ParameterEntire wfParaTemp = (ParameterEntire) itWfParaList
						.next();
				if (!wfParaTemp.getParaName().equals(this.para.getParaName())) {
					paraList.add(wfParaTemp);
				}
			}
			if (paraList.size() > 0) {
				for (int j = 0; j < paraList.size(); j++) {
					if (((ParameterEntire) paraList.get(j)).getParaName()
							.equals(this.txtName.getText())) {
						MessageDialog.openConfirm(UIPlugin.getDefault()
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), "参数名重复", "参数名重复");
						return;
					}
				}
			}
		}

		this.para.setParaName(this.txtName.getText());
		if (this.comboType.getText().equals(
				Messages.getString("WorkflowParameterType.string"))) {
			this.para.setParaType(Constants.WF_PARA_TYPE_STRING);
		}
		// else
		// if(this.comboType.getText().equals(Messages.getString("WorkflowParameterType.boolean")))
		// {
		// this.para.setParaType(Constants.WF_PARA_TYPE_BOOLEAN);
		// }
		else if (this.comboType.getText().equals(
				Messages.getString("WorkflowParameterType.long"))) {
			this.para.setParaType(Constants.WF_PARA_TYPE_LONG);
		} else if (this.comboType.getText().equals(
				Messages.getString("WorkflowParameterType.double"))) {
			this.para.setParaType(Constants.WF_PARA_TYPE_DOUBLE);
		} else if (this.comboType.getText().equals(
				Messages.getString("WorkflowParameterType.date"))) {
			this.para.setParaType(Constants.WF_PARA_TYPE_DATE);
		} else if (this.comboType.getText().equals(
				Messages.getString("WorkflowParameterType.object"))) {
			this.para.setParaType(Constants.WF_PARA_TYPE_OBJECT);
		} else {
			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getShell(), "参数类型不能为空",
					"参数类型不能为空");
			return;
		}
		if (this.workflowPropertyDialog != null) {
			if (!workflowPropertyDialog.paraList.contains(this.para)) {
				workflowPropertyDialog.paraList.add(this.para);
			}
			workflowPropertyDialog.refresh();
		}
		if (this.comboInputOrOutput.getText().equals("输入")) {
			this.para.setIsInput(true);
			this.para.setIsOutput(false);
		} else if (this.comboInputOrOutput.getText().equals("输出")) {
			this.para.setIsOutput(true);
			this.para.setIsInput(false);
		} else if (this.comboInputOrOutput.getText().equals("输入+输出")) {
			this.para.setIsInput(true);
			this.para.setIsOutput(true);
		} else {
			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getShell(), "输入/输出项不能为空",
					"输入/输出项不能为空");
			return;
		}
		if (this.activityPropDialog != null) {
			if (!this.activityPropDialog.nodeParaList.contains(this.para)) {
				this.activityPropDialog.nodeParaList.add(this.para);
			}
			this.activityPropDialog.refresh();
		}
		if (this.applicationDialog != null) {
			if (!this.applicationDialog.appParaList.contains(this.para)) {
				this.applicationDialog.appParaList.add(this.para);
			}
			this.applicationDialog.refresh();
		}
		super.okPressed();
	}

}
