package net.ms.designer.editors.workflow.dialog;

import net.ms.designer.editors.workflow.models.Wire;

import org.eclipse.jface.dialogs.IDialogConstants;
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


public class WirePropertyDialog extends TitleAreaDialog{

	private Shell shell;
	private Wire wire;

	private Label labelName;
	private Label labelDesc;
	private Label labelCondition;
	private Label labelConditionType;

	private Text txtName;
	private Text txtDesc;
	private Text txtCondition;
	
	private Combo comboNodePar;
	
	public WirePropertyDialog(Shell parentShell,Wire wire) {
		super(parentShell);
		this.shell = parentShell;
		this.wire = wire;
	}

	protected Control createDialogArea(Composite parent)
	{
		if(this.wire!=null){
			setTitle("传输条件");
			setMessage(" 设定传输条件");
			
			GridLayout layout = new GridLayout();
	        layout.numColumns = 2;
	        layout.marginHeight = 5;
			layout.marginWidth = 20;
			layout.verticalSpacing = 8;

	        Composite composite = new Composite(parent, SWT.NONE);
	        composite.setLayout(layout);
	        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
	        
	        GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
	        GridData txtGridData = new GridData(GridData.FILL_HORIZONTAL);
	        
	        this.labelName = new Label(composite,SWT.NONE);
	        labelName.setLayoutData(labelGridData);
	        labelName.setText("名称");
	        
	        this.txtName = new Text(composite,SWT.BORDER);
	        txtName.setLayoutData(txtGridData);
	        if (wire.getName() != null)
	        	txtName.setText(wire.getName());
	        
	        this.labelDesc = new Label(composite,SWT.NONE);
	        labelDesc.setLayoutData(labelGridData);
	        labelDesc.setText("描述");
	        
	        this.txtDesc = new Text(composite,SWT.BORDER);
	        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
					| GridData.FILL_VERTICAL);
	        data.verticalSpan = 6;
	        txtDesc.setLayoutData(data);
	        txtDesc.setText(wire.getDescription());
	        
	        this.labelConditionType = new Label(composite,SWT.NONE);
	        labelConditionType.setLayoutData(labelGridData);
	        labelConditionType.setText("条件类型");
	        
	        this.comboNodePar = new Combo(composite,SWT.DROP_DOWN | SWT.READ_ONLY);
			comboNodePar.setLayoutData(txtGridData);
			comboNodePar.add("条件处理类",0);
			comboNodePar.add("条件表达式",1);
			if (wire.getConditionType() != null)
				comboNodePar.select(Integer.parseInt(wire.getConditionType()+""));
	        
	        this.labelCondition = new Label(composite,SWT.NONE);
	        labelCondition.setLayoutData(labelGridData);
	        labelCondition.setText("传输条件");
	        
	        this.txtCondition = new Text(composite,SWT.BORDER);
	        txtCondition.setLayoutData(data);
	        txtCondition.setText(wire.getTransitionCondition());
		
		}
		
		
		return parent;
		
	}
	
	protected void buttonPressed(int buttonId) 
	{
	    //如果是点了OK按钮，则将值设置回类变量
	    if (buttonId == IDialogConstants.OK_ID)
	    {
	    	wire.setName(txtName.getText());
	    	wire.setDescription(txtDesc.getText());
	    	wire.setTransitionCondition(txtCondition.getText());
	    	wire.setConditionType(new Long(comboNodePar.getSelectionIndex()));
	    }
	    else if(buttonId == IDialogConstants.CANCEL_ID)
	    {
	    	super.cancelPressed();
	    }
	    super.buttonPressed(buttonId);
	}
	
	
	
	
	
}
