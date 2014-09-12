package net.ms.designer.editors.componentdetail.models;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CecInputDialog extends InputDialog{
	
//	private Label type;  
	private Text name;
//	private Text onName;
	private Label location;
	private Label defaultValue;
	private Combo readOnly;
	private Combo isFilled;
	private Text maxValue;
	private Text minValue;
//	private Element child;
//	private String t;

	public CecInputDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue, IInputValidator validator) {
		  super(parentShell, dialogTitle, dialogMessage, initialValue, validator);
      
		// TODO Auto-generated constructor stub
	}
	 protected Control createDialogArea(Composite parent) {
		 Composite comp= (Composite) super.createDialogArea(parent);
			Composite composite = new Composite(comp, SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.marginHeight = 5;
			layout.marginWidth = 20;
			layout.verticalSpacing = 8;
			layout.numColumns = 2;
			
			composite.setLayoutData(new GridData(GridData.FILL_BOTH));
			composite.setLayout(layout);
			
			Label lblName = new Label(composite,SWT.NONE);
			lblName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblName.setText("名称");
			
			name = new Text(composite,SWT.BORDER);
			name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			name.setText("");
			
			Label lblLocation = new Label(composite, SWT.NONE);
			lblLocation.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblLocation.setText("坐标"); 

			location = new Label(composite, SWT.BORDER);
			location.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			location.setText("");
			
			Label lblDefaultValue = new Label(composite, SWT.NONE);
			lblDefaultValue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblDefaultValue.setText("默认值"); 
			
			defaultValue = new Label(composite, SWT.BORDER);
			defaultValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			defaultValue.setText("");
			
			Label lblIsFilled = new Label(composite, SWT.NONE);
			lblIsFilled.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblIsFilled.setText("必须填写");

			isFilled = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.DefaultSelection);
			isFilled.add("是");
			isFilled.add("否");
			GridData ifd = new GridData(GridData.HORIZONTAL_ALIGN_END);
			GridData ifd1 = new GridData(SWT.CHECK);
			ifd.widthHint = 100;
			isFilled.setLayoutData(ifd);
			isFilled.setLayoutData(ifd1);
//			isFilled.addSelectionListener(new SelectionListener() {
//				public void widgetSelected(SelectionEvent e) {
//					
//				}
//
//				public void widgetDefaultSelected(SelectionEvent e) {
////					isFilled.add("是");
//					isFilled.setEnabled(true);
//				}
//			});
			
			Label lblReadOnly = new Label(composite, SWT.NONE);
			lblReadOnly.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblReadOnly.setText("只读"); 

			readOnly = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
			readOnly.setText("shi");
			readOnly.add("是");
			readOnly.add("否");
			GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
			gd.widthHint = 100;
			readOnly.setLayoutData(gd);
//			readOnly.setItems("io");
//			readOnly.addSelectionListener(new SelectionListener() {
//				public void widgetSelected(SelectionEvent e) {
//					
//				}
//
//				public void widgetDefaultSelected(SelectionEvent e) {
//				}
//			});
		
			Label lblMaxValue = new Label(composite, SWT.NONE);
			lblMaxValue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblMaxValue.setText("最大值"); 

			maxValue = new Text(composite, SWT.BORDER);
			maxValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//			maxValue.setText(child.get);
			
			Label lblMinValue = new Label(composite, SWT.NONE);
			lblMinValue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblMinValue.setText("最小值"); 

			minValue = new Text(composite, SWT.BORDER);
			minValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			return parent;
	 }

}
