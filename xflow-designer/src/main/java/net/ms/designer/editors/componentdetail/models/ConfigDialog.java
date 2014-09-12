package net.ms.designer.editors.componentdetail.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.editors.componentdetail.commands.CreateCommand;
import net.ms.designer.editors.componentdetail.ui.CompDetailEditor;
import net.ms.designer.editors.componentdetail.xmlpalse.ReadEnumXML;
import net.ms.designer.editors.componentdetail.xmlpalse.TableViewerContentProvider;
import net.ms.designer.editors.componentdetail.xmlpalse.TableViewerLabelProvider;
import net.ms.designer.editors.componentdetail.xmlpalse.TableViewerLabelProvider1;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
//import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.Model;
import org.eclipse.ui.internal.UIPlugin;
import org.w3c.dom.Document;


/**
 * 
 * @author lili
 * @explain: defind the Dialog that can config the properties of the table and the field
 */
public class ConfigDialog extends TitleAreaDialog  
{

	private Label type;  
	private Text name;
	private Text iName;
	private Text decs;
	private Combo ischearchable;
	private Combo readOnly;
	private Combo isFilled;
	private Combo isList;
	private Combo isMKey;
	private Combo isPKey;
	private Combo isuserfield;
	private Text selectFrom;
	private Text maxValue;
	private Text minValue;
	private Text length;
	private Combo date;
	private Combo lookup;
	private Combo mainlookup;
	private Text fracDigits;
	private Element child;
	private Container container;
	private CommonField commonField;
	private String textValue ="";
	private String maxTextValue="";
	private String minTextValue="";
	private CheckboxTableViewer ctv;
	private CheckboxTableViewer ctv1;
	private Table table1;
	private Object checkObj;
	private int t;
	private Shell shell;
	private String nameid;
	private String change;
	 List list1 = null;
	/**
	 * Contruct ths ConfigDialog.
	 * @param parentShell
	 * @param child
	 * @param container
	 * @param commonField
	 */
	public ConfigDialog(Shell parentShell,Element child,Container container,CommonField commonField,String change) 
	{
		super(parentShell);
	
		this.shell = parentShell;
		this.child = child;
		this.container = container;
		this.commonField = commonField;
		this.change = change;
//		//System.out.println(this.commonField.ge);
		// TODO 自动生成构造函数存根
	}
	
	/**
	 * config the porperties of the different fields
	 */
	protected Control createDialogArea(Composite parent) 
	{
//		nameid = container.getName();
//		((ChildTable)child).setMainTableName(nameid);
		setTitle("属性信息");
		
		Composite comp = (Composite) super.createDialogArea(parent);
		
		final Composite composite = new Composite(comp, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 20;
		layout.verticalSpacing = 8;
		layout.numColumns = 2;
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(layout);		
		
		//-------------名称
		Label lblName = new Label(composite,SWT.NONE);
		lblName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblName.setText("名称");
		
		name = new Text(composite,SWT.BORDER);
		name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		if(child instanceof AutoNumField)
		{
			String fild1 = container.getName();
			name.setText(fild1);
		}
		if(((Element)child).getName() == "")
		name.setText("");	
				
		else 
			name.setText(child.getName());
			
		//-------------国际化名称
		Label lblIName = new Label(composite,SWT.NONE);
		lblIName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblIName.setText("国际化名称");
		
		iName = new Text(composite,SWT.BORDER);
		iName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if(((Element)child).getIName() == "")
			iName.setText(child.getName());	
		else 
			iName.setText(child.getIName());
		
		//-----------------描述
		Label lblDesc = new Label(composite, SWT.NONE);
		lblDesc.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblDesc.setText("描述"); 

		decs = new Text(composite, SWT.BORDER);
		decs.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if(((Element)child).getDesc() == null)
			decs.setText("");	
		else 
			decs.setText(child.getDesc());
		if ((child instanceof CommonField))   //----判断是否是字段
		{
		Label lblIschearchable = new Label(composite, SWT.NONE);
		lblIschearchable.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblIschearchable.setText("查询条件"); 
		
		ischearchable = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		ischearchable.setText("shi");
		ischearchable.add("是");
		ischearchable.add("否");
		if(((CommonField)child).getCanBeQuery() == 0)
		{
			ischearchable.select(1);
		}
		else
		{
			ischearchable.select(0);
		}
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 100;
		ischearchable.setLayoutData(gd);
		ischearchable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {

			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		//-------------用户定义
		
	
//		Label lblIsUserField = new Label(composite, SWT.NONE);
//		lblIsUserField.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
//		lblIsUserField.setText("用户定义"); 
//		lblIsUserField.setVisible(false);
//		
//		isuserfield = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
//		isuserfield.setText("shi");
//		isuserfield.add("是");
//		isuserfield.add("否");
//		isuserfield.setVisible(false);
////		if(((CommonField)child).getIsuserfield() == 0)
////		{
//		if((child instanceof AutoNumField) || (child instanceof AutoPField))
//		{
//			isuserfield.select(1);
//		}
//		else
//		{
//			isuserfield.select(0);
//		}
//		GridData gd13= new GridData(GridData.FILL_HORIZONTAL);
//		gd13.widthHint = 100;
//		isuserfield.setLayoutData(gd13);
//		isuserfield.addSelectionListener(new SelectionListener() 
//				{
//			public void widgetSelected(SelectionEvent e) 
//			{
//
//			}
//
//			public void widgetDefaultSelected(SelectionEvent e)
//			{
//			}
//		});
		//-----------必填
		Label lblIsFilled = new Label(composite, SWT.NONE);
		lblIsFilled.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblIsFilled.setText("必须"); 
		
		isFilled = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		isFilled.setText("shi");
		isFilled.add("是");
		isFilled.add("否");
		if(((CommonField)child).getMustBeFilled() == 0)
		{
			isFilled.select(1);
		}
		else
		{
			isFilled.select(0);
		}
		GridData gd3= new GridData(GridData.FILL_HORIZONTAL);
		gd3.widthHint = 100;
		isFilled.setLayoutData(gd3);
		isFilled.addSelectionListener(new SelectionListener() 
				{
			public void widgetSelected(SelectionEvent e) 
			{

			}

			public void widgetDefaultSelected(SelectionEvent e)
			{
			}
		});
		//--------------可列表
		Label lblIsList= new Label(composite, SWT.NONE);
		lblIsList.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblIsList.setText("可列表"); 
		
		isList = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		isList.setText("shi");
		isList.add("是");
		isList.add("否");
		if(((CommonField)child).getListable() == 0)
		{
			isList.select(1);
		}
		else
		{
			isList.select(0);
		}
		GridData gd4= new GridData(GridData.FILL_HORIZONTAL);
		gd4.widthHint = 100;
		isList.setLayoutData(gd4);
		isList.addSelectionListener(new SelectionListener() 
				{
			public void widgetSelected(SelectionEvent e) 
			{

			}

			public void widgetDefaultSelected(SelectionEvent e) 
			{
			}
		});
		
		//-------------是主键
		Label lblIsMKey= new Label(composite, SWT.NONE);
		lblIsMKey.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblIsMKey.setText("主键"); 
		
		isMKey = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
//		isList.setText("shi");
		isMKey.add("是");
		isMKey.add("否");
		if(((CommonField)child).getIsBizKey() == 0)
		{
			isMKey.select(1);
		}
		else
		{
			isMKey.select(0);
		}
		GridData gd5= new GridData(GridData.FILL_HORIZONTAL);
		gd5.widthHint = 100;
		isMKey.setLayoutData(gd5);
		isMKey.addSelectionListener(new SelectionListener() 
				{
			public void widgetSelected(SelectionEvent e) 
			{

			}

			public void widgetDefaultSelected(SelectionEvent e) 
			{
			}
		});
		//-------------字符串长度
		if(child instanceof StringField)
		{
		Label lblLength = new Label(composite,SWT.NONE);
		lblLength.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblLength.setText("长度");
		
		length = new Text(composite,SWT.BORDER);
		length.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if(((StringField)child).getStrLength() == null)
			length.setText("");	
		else 
			length.setText(((StringField)child).getStrLength());
		}
		//--------------lili 1122 start
		//---查找带回
		if(child instanceof LookupField)
		{
			Label lblLookup= new Label(composite, SWT.NONE);
			lblLookup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblLookup.setText("组件"); 
			
//			lookup = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
			lookup = new Combo(composite,SWT.NONE);
			lookup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lookup.setText(((LookupField)child).getSelectedcomp());
			
			PackageEditor editor = (PackageEditor)UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	
			MsContext context = editor.getContext();
		    final MsProject project = context.getProject();
			String path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"\\"+project.getProjectName()+"\\.configure"+"\\project.xml";
		
			
			try 
			{
					list1 = new ReadEnumXML().readXMLFile(path,1);
				
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			if(list1.size() != 0)
			{
				for(int i = 0; i<list1.size();i++)
				{
					lookup.add(((MsProject)(list1.get(i))).getComponentName());	
				}
			}
			//--------
			if(!((LookupField)child).getSelectedcomp().equals(""))
			{
				lookup.select(((LookupField)child).getK());
			}
			//---------
			Label lblLookup2= new Label(composite, SWT.NONE);
			lblLookup2.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblLookup2.setText("主带回"); 
			
			mainlookup = new Combo(composite,SWT.NONE);
			mainlookup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			if(!((LookupField)child).getMainlookup().equals(""))
			{
				mainlookup.setText(((LookupField)child).getMainlookup());
			}
//			final String hh = null;
//			mainlookup.add(hh);

			Label lblLookup1= new Label(composite, SWT.NONE);
			lblLookup1.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblLookup1.setText("带回字段"); 
			
			TableViewer tv1 = new TableViewer(composite,SWT.MULTI | SWT.FULL_SELECTION |SWT.HORIZONTAL |SWT.CHECK );
			TableLayout tLayout1 = new TableLayout();
		    table1 = tv1.getTable();

			table1.setHeaderVisible(true);
			table1.setLinesVisible(true);
			GridData tvGridData = new GridData(GridData.FILL_BOTH);
		    tvGridData.verticalSpan = 8;
		    table1.setLayoutData(tvGridData);
			table1.setLayout(tLayout1);
		
			tLayout1.addColumnData(new ColumnWeightData(50));
			new TableColumn(table1,SWT.NONE).setText("字段名称");
			
			ctv1 = new CheckboxTableViewer(table1);
			ctv1.setContentProvider(new TableViewerContentProvider());
			ctv1.setLabelProvider(new TableViewerLabelProvider1());
			
			String compName = this.lookup.getText();
			if(!compName.equals(""))
			{
				MsElement element = context.getElement("componentdetail_"+compName);
				String comPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"\\"+project.getProjectName()+"\\.configure"+"\\"+element.getId()+".xml";
				try 
				{
					List list2 = new ReadEnumXML().readXMLFile(comPath,2);
					ctv1.setInput(list2);
					
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
			
			if(((LookupField)child).getList().size()!=0)
			{
				for(int j = 0;j<((LookupField)child).getList().size();j++)
				{
					int index = -1;
					index = Integer.parseInt((String)((LookupField)child).getList().get(j));
					ctv1.setChecked(ctv1.getElementAt(index),true);
				}
			}


					lookup.addModifyListener(new ModifyListener()
							{
								public void modifyText(ModifyEvent e) {
									if(lookup.getSelectionIndex()>-1)
									{
										int kk = lookup.getSelectionIndex();					
										
									
										MsProject project1 = (MsProject) list1.get(kk);
										((LookupField)child).setK(kk);
										((LookupField)child).setSelectedcomp(project1.getComponentName());

										String comPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"\\"+project.getProjectName()+"\\.configure"+"\\"+project1.getComponentID()+".xml";
										try {
										
										
											List list2 = new ReadEnumXML().readXMLFile(comPath,2);
											
											ctv1.setInput(list2);	
											
											
										} 
										catch (Exception e1) 
										{
											e1.printStackTrace();
										}
										
									}
									
								}

							
							});
					ctv1.addCheckStateListener(new ICheckStateListener()
							{
								public void checkStateChanged(CheckStateChangedEvent event) {	
									Object[] obj1 =  ctv1.getCheckedElements();
									 if(obj1.length != 0)
									 {
										 for( int u = 0; u<obj1.length;u++)
										 {
											 String hh1 = ((MsProject)obj1[u]).getFieldName();	
												((LookupField)child).setFieldListable(((MsProject)obj1[u]).getFieldListable());
												((LookupField)child).setFieldSearchable(((MsProject)obj1[u]).getFieldSearchable());
//											 mainlookup.remove(hh1);
											 
											 mainlookup.add(hh1,u);		
											 
										 }
										 
									 }	
								
								}
							});

			
	
		
			
		}
		//-------------lili 1122 end

		//----------lili 1114 start
		if(child instanceof DateField)
		{
			Label lblDate= new Label(composite, SWT.NONE);
			lblDate.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblDate.setText("日期"); 
			
			date = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
	//		isList.setText("shi");
			date.add("yyyy-MM-dd hh:mm");
			date.add("yyyy-MM-dd hh");
			date.add("yyyy-MM-dd");
			date.add("yyyy-MM");
			date.add("yyyy");
//			if(((DateField)child).getDateFormatPattern().equals())
//			{
//				date.select(0);
//			}
			if(((DateField)child).getDateFormatPattern().equals("1"))
			{
				date.select(0);
			}
			if(((DateField)child).getDateFormatPattern().equals("2"))
			{
				date.select(1);
			}
			if(((DateField)child).getDateFormatPattern().equals("3"))
			{
				date.select(2);
			}
			if(((DateField)child).getDateFormatPattern().equals("4"))
			{
				date.select(3);
			}
			if(((DateField)child).getDateFormatPattern().equals("5"))
			{
				date.select(4);
			}
			GridData gd23= new GridData(GridData.FILL_HORIZONTAL);
			gd23.widthHint = 100;
			date.setLayoutData(gd23);
			date.addSelectionListener(new SelectionListener() 
					{
				public void widgetSelected(SelectionEvent e) 
				{
	
				}
	
				public void widgetDefaultSelected(SelectionEvent e) 
				{
				}
			});
		}

		//----------lili 1114 end
			
		//----------------精度
		if(child instanceof FloatField)
		{
		Label lblFatDigits = new Label(composite, SWT.NONE);
		lblFatDigits.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblFatDigits.setText("精度"); 
		
		fracDigits = new Text(composite, SWT.BORDER);
		fracDigits.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if(child instanceof CommonField)
		{
		if(((FloatField)child).getFractionDigitals() != null)
			fracDigits.setText(((FloatField)child).getFractionDigitals());
		else
			fracDigits.setText("");
		}
		}
		
		//-------------------------枚举字段选择
		if(child instanceof EnumField)
		{
			
			Label lblEnum = new Label(composite, SWT.NONE);
			lblEnum.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			lblEnum.setText("枚举选择"); 
			
		TableViewer tv = new TableViewer(composite,SWT.MULTI | SWT.FULL_SELECTION |SWT.HORIZONTAL |SWT.CHECK );
		
		
		Table table = tv.getTable();
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableLayout tLayout = new TableLayout();
		table.setLayout(tLayout);
		tLayout.addColumnData(new ColumnWeightData(50));
		new TableColumn(table,SWT.NONE).setText("全名");
		tLayout.addColumnData(new ColumnWeightData(10));
		new TableColumn(table,SWT.NONE).setText("描述");
		
		ctv = new CheckboxTableViewer(table);
		ctv.setContentProvider(new TableViewerContentProvider());
		ctv.setLabelProvider(new TableViewerLabelProvider());
				
			try {
				String filePath = null;
				MsProject project = ((CompDetailEditor)UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getProject();
		    	String path = project.getConfigPath();
		    	StringBuffer sb= new StringBuffer(path);
		    	sb.append("project.xml");
		    	filePath = sb.toString();
		    	if(filePath != null)
		    	{
				ctv.setInput(new ReadEnumXML().readXMLFile(filePath,0));
				String selectedEnum = ((EnumField)child).getSelectedEnum();
				if(!selectedEnum.equals(""))
				{
					int index = -1;
					for(int i = 0; i< table.getItemCount(); i++)
					{
						String tmp = ((MsProject)ctv.getElementAt(i)).getEnumCompName();
						if(tmp.equals(selectedEnum))
						{
							index = i;
						}
					}
					ctv.setChecked(ctv.getElementAt(index),true);
				}	
		    	}
			} catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		}
		
		return parent;		
	
	}
		
	
	/**
	 * it defind the order when press the OK button
	 */
    protected void buttonPressed(int buttonId) 
    {
        //如果是点了OK按钮，则将值设置回类变量
        if (buttonId == IDialogConstants.OK_ID)
        {
        
        	 textValue = name.getText();
        	if((child instanceof ChildTable))
        	{
        		container.flag =1;
        		nameid=container.getMainTable().getName().toLowerCase();
        		List ta = container.getChildren();
        		for(int k = 0;k<ta.size();k++)
        		{
        			boolean flag = true;
        			if(ta.get(k) instanceof ChildTable)
	        			{
	        			if(((ChildTable)ta.get(k)).getName().equals(textValue))
	        			{
	        				flag = false;
	        			}	
	        
	        			if(((ChildTable)ta.get(k)).getField_Type().equals("Autonum"))
	        			{
	        				((ChildTable)ta.get(k)).setName(nameid);
	        			}
	        			if(!flag && change.equals("0"))
	            		{
	            			MessageBox box = new MessageBox(shell);
	            			box.setMessage("Invailed name!");
	            			box.open();
	            			return;
	            		}  
        			}
        		}
        		((ChildTable)child).setMainTableName(nameid);
        	}
       
           

           if(child instanceof CommonField)
           {
            if(textValue != "")
            {
            	boolean flag = true;
            		List nodes =container.getChildren();
            		for(int i = 0;i<nodes.size();i++)
            		{         		
            			if(((CommonField)nodes.get(i)).getName().equals(textValue))
            			{
            				flag = false;
            				break;
            			}	
            		}
            		if(!flag && change.equals("0"))
            		{
            			MessageBox box = new MessageBox(shell);
            			box.setMessage("Invailed name!");
            			box.open();
            			return;
            		}             
            }
           }
            if((child instanceof ComponentTable))
            {
            	
            	char ii = textValue.charAt(0);
            	if(!((ii >='A') && (ii <= 'Z')))
				{
					MessageBox box = new MessageBox(shell);
					box.setMessage("The first charater must be upcase");
					box.open();
					return;
				}
            }
            if((child instanceof ChildTable))
            {
            	
            	char ii = textValue.charAt(0);
            	if(!((ii >='A') && (ii <= 'Z')))
				{
					MessageBox box = new MessageBox(shell);
					box.setMessage("The first charater must be upcase");
					change = "1";
					box.open();
					return;
				}
            }
        	   if(textValue.equals(""))
        	   {
        		   MessageBox box = new MessageBox(shell);
        		   box.setMessage("Name is not null");
        		   box.open();
        		   return;
        	   }
            child.setName(textValue);    
            if(iName.getText().equals(""))
            {
            	child.setIName(textValue);
            }
            else
            {
            	child.setIName(iName.getText());
            }
            child.setDesc(decs.getText());
        
            if((child instanceof CommonField))
            {
            	
            	//----------lili 1114 start
            	if(child instanceof DateField)
            	{
            		if(date.getText().equals("yyyy-MM-dd hh:mm"))
            		{
            			((DateField)child).setDateFormatPattern("1");
            		}
            		if(date.getText().equals("yyyy-MM-dd hh"))
            		{
            			((DateField)child).setDateFormatPattern("2");
            		}
            		if(date.getText().equals("yyyy-MM-dd"))
            		{
            			((DateField)child).setDateFormatPattern("3");
            		}
            		if(date.getText().equals("yyyy-MM"))
            		{
            			((DateField)child).setDateFormatPattern("4");
            		}
            		if(date.getText().equals("yyyy"))
            		{
            			((DateField)child).setDateFormatPattern("5");
            		}
            	}
            	//----------lili 1114 end
            	if(child instanceof StringField)
        		{
            		 ((StringField)child).setStrLength(length.getText());
        		}
            	if(child instanceof FloatField)
            	{
            		((FloatField)child).setFractionDigitals(fracDigits.getText());
            	}
            	if(child instanceof AutoPField)
            	{
            		((CommonField)child).setIsPreKey(1);
            	}
            	else
            	{
            		((CommonField)child).setIsPreKey(0);
            	}

            ischearchable.getText();
            {
            if(ischearchable.getText().equals("是"))
            {
            
            	((CommonField)child).setCanBeQuery(1);
            }
            else
            {
            	((CommonField)child).setCanBeQuery(0);
            }
            }
            isFilled.getText();
            {
            if(isFilled.getText().equals("是"))
            	((CommonField)child).setMustBeFilled(1);
            else
            	((CommonField)child).setMustBeFilled(0);
            }

            isList.getText();
            {
            	if(isList.getText().equals("是"))
            		((CommonField)child).setListable(1);
            	else
            		((CommonField)child).setListable(0);
            }
            isMKey.getText();
            {
            	if(isMKey.getText().equals("是"))
            		((CommonField)child).setIsBizKey(1);
            	else
            		((CommonField)child).setIsBizKey(0);
            }
            if((child instanceof AutoNumField) || (child instanceof AutoPField))
            {
            	((CommonField)child).setIsuserfield(0);
            }
            else
            {
            	((CommonField)child).setIsuserfield(1);
            }
            //----------lili 1124
            if(child instanceof LookupField)
            {
            	Object[] obj3 = ctv1.getCheckedElements();
//            	List list3 = new ArrayList();
            	if(obj3.length != 0)
            	{
            		for(int i = 0; i<obj3.length; i++)
            		{
            			MsProject pro3;
            			pro3 = (MsProject)obj3[i];
//            			list3.add(pro3.getFieldName());
            				
            			for(int j = 0; j< table1.getItemCount(); j++)
    					{
    						MsProject tmp = ((MsProject)ctv1.getElementAt(j));
    						((LookupField)child).getFieldlist().add(tmp);
    						if(tmp.getFieldName().equals(pro3.getFieldName()))
    						{
    							((LookupField)child).getList().add(Integer.toString(j));
    						}
    					}
            			
            		}
            		
            	}
            	((LookupField)child).setSelectedfield(((LookupField)child).getList());
            	String kk = mainlookup.getText();
            	((LookupField)child).setMainlookup(kk);
            
            }
            //----------lili 1124 end
            if(child instanceof EnumField)
        	{
            Object[] obj =  ctv.getCheckedElements();
            
            //System.out.println("obl"+obj.length);
            MsProject pro = null;
          
            if(obj.length != 0)
            {
            	
            	pro = (MsProject)obj[0];
            	//System.out.println("ConfigDialog,CEECProject");
            	
            ((EnumField)child).setSelectedEnum(pro.getEnumCompName());
            }
            else 
            	((EnumField)child).setSelectedEnum(null);
            	}
        
            }
      
        }
            else if (buttonId == IDialogConstants.CANCEL_ID){
            	//System.out.println("buttonId == IDialogConstants.CANCEL_ID");

            	super.cancelPressed();
            	
            	
            }
            super.buttonPressed(buttonId); 
    }

    

    
   
    }

