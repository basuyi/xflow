package net.ms.designer.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ms.designer.core.DBTool;
import net.ms.designer.ui.preference.DBPreferencePage;

import org.eclipse.core.internal.resources.WorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.UIPlugin; 
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


//import net.ms.designer.ui.preference.DBPreferencePage;

//import net.ms.designer.ui.preference.DBPreferencePage;;
//let's begin
public class NewAppWizard extends WizardPage
{
	private Composite composite;
	Text authorText, versionText,companyText,pathText,txtProject;
	Button initButton;
	static Combo cbConn;
	boolean needInitDB ;
	boolean defaultV = false;
	int flag = 0;
	Button btnConfig;
	Text genPackageNameText;
	static List dbList ;
	String path;
	
	public NewAppWizard()
	{
		super("Platform Project Wizard");
		setTitle("Platform Project Wizard");
		setDescription("Platform Project Wizard");
		setPageComplete(false);
//		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
	}
	public void createControl(Composite parent)
	{
		initializeDialogUnits(parent);
		composite = createClientArea(parent);
		createProject(composite);
		createAppInfoGroup(composite);
		createProjInfoGroup(composite);
		createConfigGroup(composite);
		setControl(composite);
		Dialog.applyDialogFont(composite);
	}
	private Composite createClientArea(Composite composite) {
		Composite composite1 = new Composite(composite, SWT.NONE);
		GridLayout gridlayout = new GridLayout();
		gridlayout.marginWidth = 0;
		gridlayout.marginHeight = 0;
		gridlayout.numColumns = 1;
		composite1.setLayout(gridlayout);
//		composite1.setLayout(new RowLayout());
		return composite1;
	}
	private void createProject(Composite parent)
	{
		Font font = parent.getFont();
		Label companyLabel = new Label(parent, SWT.NONE | SWT.TRAIL);
        companyLabel.setFont(font);
        companyLabel.setText("项目名称"); //$NON-NLS-1$

//        //System.out.println("aaaaaaaaaaaaaaa");
        txtProject = new Text(parent, SWT.BORDER);
        GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
//      gd3.widthHint = 120;
        txtProject.setLayoutData(gd3);
        txtProject.setFont(font);
        txtProject.setText(""); 
        txtProject.setFocus();
        
        txtProject.addModifyListener(new ModifyListener(){
        	
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				if(txtProject.getText().length()>0){
					setPageComplete(true);
				}
				else 
					setPageComplete(false);
				genPackageNameText.setText("com."+ companyText.getText()+"."+txtProject.getText() );
				pathText.setText(path+"\\"+txtProject.getText());
			}
        	
        });
	}
	private void createAppInfoGroup(Composite parent) {
		Font font = parent.getFont();

		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		group.setLayout(gl);
		group.setFont(font);
		group.setText("项目内容"); //$NON-NLS-1$

		initButton = new Button(group, SWT.CHECK);
		initButton.setText("使用缺省值（D）"); //$NON-NLS-1$
		setCustomButtonLayoutData(initButton, 3);
		initButton.setSelection(true);
		initButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				
//				defaultV = initButton.getSelection();
				if(initButton.getSelection() == true){
		        	pathText.setText(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()); 
					pathText.setEnabled(false);
					btnConfig.setEnabled(false);
					path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
		        }
		        else{
		        	pathText.setText(""); 
		        	pathText.setEnabled(true);
		        	btnConfig.setEnabled(true);
		        }
//				//System.out.println("initButton.getSelection()"+initButton.getSelection());
//				//System.out.println("defaultV"+defaultV);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});
		
        //path 
        Label companyLabel = new Label(group, SWT.NONE | SWT.TRAIL);
        companyLabel.setFont(font);
        companyLabel.setText("目录"); //$NON-NLS-1$

        pathText = new Text(group, SWT.BORDER);
        GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
//      gd3.widthHint = 120;
        pathText.setLayoutData(gd3);
        pathText.setFont(font);
        pathText.setEnabled(false);
        pathText.setText(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
        path = pathText.getText();
//        if(defaultV==true){
////        	pathText.setText(""); //$NON-NLS-1$
//        }
//        else{
//        	pathText.setText(""); 
//        }
//        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
//		gd.widthHint = 120;
//		pathText.setLayoutData(gd);
//		pathText.setFont(font);
        
		// 
        btnConfig = new Button(group, SWT.PUSH);
		btnConfig.setText("浏览"); //$NON-NLS-1$
		btnConfig.setEnabled(false);
		btnConfig.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new DirectoryDialog(new Shell());
				dd.open();
				pathText.setText(dd.getFilterPath()+"\\"+txtProject.getText());
				path = dd.getFilterPath();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		setCustomButtonLayoutData(btnConfig, 1);
		
	}
	private void createProjInfoGroup(Composite parent) {
		Font font = parent.getFont();

		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		group.setLayout(gl);
		group.setFont(font);
		group.setText("MS Application"); //$NON-NLS-1$

        //company 
        Label companyLabel = new Label(group, SWT.NONE | SWT.TRAIL);
        companyLabel.setFont(font);
        companyLabel.setText("公司简称"); //$NON-NLS-1$

        companyText = new Text(group, SWT.BORDER);
        GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
//      gd3.widthHint = 120;
        companyText.setLayoutData(gd3);
        companyText.setFont(font);
        companyText.setText("basuyi"); 
//        companyText.setTextLimit(4);    
        companyText.addModifyListener(new ModifyListener(){

			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				genPackageNameText.setText("com."+ companyText.getText()+"."+txtProject.getText() );
			}
        	
        });
        
		// Author
		Label authorLabel = new Label(group, SWT.NONE | SWT.TRAIL);
		authorLabel.setFont(font);
		authorLabel.setText("作者"); //$NON-NLS-1$

		authorText = new Text(group, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 120;
		authorText.setLayoutData(gd);
		authorText.setFont(font);
		authorText.setText(System.getProperty("user.name")); //$NON-NLS-1$

		// Version
		Label versionLabel = new Label(group, SWT.NONE | SWT.TRAIL);
		versionLabel.setFont(font);
		versionLabel.setText("版本"); //$NON-NLS-1$

		versionText = new Text(group, SWT.BORDER);
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 120;
		versionText.setLayoutData(gd2);
		versionText.setFont(font);
		versionText.setText("1.0"); //$NON-NLS-1$
		
		
//		 GenPackageName
		Label genPackageNameLabel = new Label(group, SWT.NONE | SWT.TRAIL);
		genPackageNameLabel.setFont(font);
		genPackageNameLabel.setText("生成包路径"); //$NON-NLS-1$

		genPackageNameText = new Text(group, SWT.BORDER);
		genPackageNameText.setLayoutData(gd);
		genPackageNameText.setFont(font);
		genPackageNameText.setText("com."+ companyText.getText()+"."+txtProject.getText() );
//		genPackageNameText.addModifyListener(new ModifyListener(){
//
//			public void modifyText(ModifyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		
//		});
		
		
	}
	private void createConfigGroup(Composite parent) {
		Font font = parent.getFont();
		Group group = new Group(parent, SWT.NONE);

		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		group.setLayout(gl);
		group.setFont(font);
		group
				.setText("数据库配置"); //$NON-NLS-1$

		Label connLabel = new Label(group, SWT.NONE | SWT.TRAIL);
		connLabel.setFont(font);
		connLabel.setText("数据库连接"); //$NON-NLS-1$

		cbConn = new Combo(group, SWT.DOWN | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 120;
		cbConn.setLayoutData(gd);
		cbConn.setFont(font);
//		dbList = DBPreferencePage.dbToolList;
		
		//-------------lili
		//读取DataSourceXML中的所有数据
		String dataSource;
		File file =  new File("D:\\runtime-workspace\\DataSourceXML.xml");
		if(file.exists()){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		try { 
			db = dbf.newDocumentBuilder(); 
		} catch (ParserConfigurationException pce) { 
			System.err.println(pce); 
			System.exit(1); 
		} 
		Document doc = null; 
		try { 
			try {
				doc = db.parse(file);
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		} catch (DOMException dom) { 
			System.err.println(dom.getMessage()); 
			System.exit(1); 
		} catch (IOException ioe) { 
			System.err.println(ioe); 
			System.exit(1); 
		} 
		dbList = new ArrayList();
		Element root = doc.getDocumentElement(); 
		NodeList dataSources = root.getElementsByTagName("DataSource");
		if(dataSources.getLength() > 0)
		{
			for(int i = 0; i < dataSources.getLength(); i++)
			{
				DBTool dbTool2 = new DBTool();
				
				Element data = (Element)dataSources.item(i);
				NodeList one = data.getElementsByTagName("conName");
				Element contant = (Element)one.item(0);
				org.w3c.dom.Text t1 = (org.w3c.dom.Text) contant.getFirstChild();
				dbTool2.setConName(t1.getNodeValue());
				
				NodeList two = data.getElementsByTagName("dataBase");
				Element contant2 = (Element)two.item(0);
				org.w3c.dom.Text t2 = (org.w3c.dom.Text) contant2.getFirstChild();
				dbTool2.setDbName(t2.getNodeValue());
				
				NodeList three = data.getElementsByTagName("dbType");
				Element contant3 = (Element)three.item(0);
				org.w3c.dom.Text t3 = (org.w3c.dom.Text) contant3.getFirstChild();
				dbTool2.setDbType(t3.getNodeValue());
				
				NodeList fore = data.getElementsByTagName("password");
				Element contant4 = (Element)fore.item(0);
				org.w3c.dom.Text t4 = (org.w3c.dom.Text) contant4.getFirstChild();
				dbTool2.setPassword(t4.getNodeValue());
				
				NodeList five = data.getElementsByTagName("port");
				Element contant5 = (Element)five.item(0);
				org.w3c.dom.Text t5 = (org.w3c.dom.Text) contant5.getFirstChild();
				dbTool2.setPort(t5.getNodeValue());
				
				NodeList six = data.getElementsByTagName("server");
				Element contant6 = (Element)six.item(0);
				org.w3c.dom.Text t6 = (org.w3c.dom.Text) contant6.getFirstChild();
				dbTool2.setServer(t6.getNodeValue());
				
				NodeList seven = data.getElementsByTagName("userName");
				Element contant7 = (Element)seven.item(0);
				org.w3c.dom.Text t7 = (org.w3c.dom.Text) contant7.getFirstChild();
				dbTool2.setUsername(t7.getNodeValue());
				
				dbList.add(dbTool2);
			}
			
		}
		}

		//-----------
//		if(DBPreferencePage.dbToolList ==null ||DBPreferencePage.dbToolList.size()>0 ||dbList != null || dbList.size()>0){
//			
//		}
		if(dbList != null && dbList.size() > 0 )
		{
			Iterator it = dbList.iterator();
			List dbNameList = new ArrayList();
			while(it.hasNext()){
				DBTool tool = (DBTool) it.next();
				dbNameList.add(tool.getConName());
			}
			cbConn.setItems((String[]) dbNameList.toArray(new String[dbNameList.size()]));
			if (cbConn.getItemCount() > 0) {
				cbConn.select(-1);
			}
			DBPreferencePage.dbToolList = dbList;
		}
		if(DBPreferencePage.dbToolList !=null && DBPreferencePage.dbToolList.size()>0){
			dbList = DBPreferencePage.dbToolList;
//		if(dbList != null && dbList.size() > 0)
//		{
			Iterator it = dbList.iterator();
			List dbNameList = new ArrayList();
			while(it.hasNext()){
				DBTool tool = (DBTool) it.next();
				dbNameList.add(tool.getConName());
			}
			cbConn.setItems((String[]) dbNameList.toArray(new String[dbNameList.size()]));
			if (cbConn.getItemCount() > 0) {
				cbConn.select(-1);
			}
		}
//		cbConn.addSelectionListener(new SelectionListener() {
//			public void widgetSelected(SelectionEvent e) {
//				setPageComplete(validatePage());
//			}
//
//			public void widgetDefaultSelected(SelectionEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});

		Button btnConfig = new Button(group, SWT.PUSH);
		btnConfig.setText("配置"); //$NON-NLS-1$
		setCustomButtonLayoutData(btnConfig, 1);
		btnConfig.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if(txtProject.getText()==null||txtProject.getText().length()<1){
					MessageDialog.openInformation(
	    					UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
	    					"请先输入项目名称",
	    					"请先输入项目名称");
				}else{
					DBPreferencePage.projectName = txtProject.getText();
					DBPreferencePage.dbToolList = dbList;
					DBPreferencePage.Show();
				}
				
//				java.util.List names = DBConfigTools.getConnectionNames();
//				cbConn.setItems((String[]) names.toArray(new String[names
//						.size()]));
//				if (conn.trim().equals("")) //$NON-NLS-1$
//					cbConn.select(0);
//				else
//					cbConn.setText(conn);
//				setPageComplete(validatePage());
			}
		});

		final Button initButton = new Button(group, SWT.CHECK);
		initButton.setText("初始化"); //$NON-NLS-1$
		setCustomButtonLayoutData(initButton, 3);
		initButton.setSelection(needInitDB);
		initButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				needInitDB = initButton.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});
	}
	
	public static void refresh(){
		if(DBPreferencePage.dbToolList !=null && DBPreferencePage.dbToolList.size()>0){
			dbList = DBPreferencePage.dbToolList;;
			Iterator it = dbList.iterator();
			List dbNameList = new ArrayList();
			while(it.hasNext()){
				DBTool tool = (DBTool) it.next();
				dbNameList.add(tool.getConName());
			}
			cbConn.setItems((String[]) dbNameList.toArray(new String[dbNameList.size()]));
			if (cbConn.getItemCount() > 0) {
				cbConn.select(0);
			}
		}
	}
	
	protected GridData setCustomButtonLayoutData(Button button, int hSapn) {
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.heightHint = convertVerticalDLUsToPixels(IDialogConstants.BUTTON_HEIGHT);
		int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT,
				SWT.DEFAULT, true).x);
		data.horizontalSpan = hSapn;
		button.setLayoutData(data);
		return data;
	}
	



}