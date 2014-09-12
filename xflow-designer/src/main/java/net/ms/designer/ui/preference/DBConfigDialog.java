package net.ms.designer.ui.preference;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.TableView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.ms.designer.core.DBTool;
import net.ms.designer.ui.view.WriteToDataSourceXML;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TableViewer;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


//import net.ms.designer.editors.enumcomponentdetail.tools.WriteToProjectXML;


public class DBConfigDialog extends TitleAreaDialog implements Serializable
{
	Shell shell;

	public String[] connTypes;

	private Combo cbType;

	private Text txtName;

	private Text txtUserName;

	private Text txtUserPwd;

	private String connectionName;

	private Text txtServer;

	private Text txtPort;

	private Text txtDBName;

	private DBTool tool;
	
	private List dbToolList;
	
	Button testConnection ;

	private String conName;
	
	private int flag = 0;
	
	private String filePath;
	
	/**
	 * @param parentShell
	 */
	public DBConfigDialog(Shell parentShell,List dbToolList,String conName) 
	{
		super(parentShell);
		this.dbToolList = dbToolList;
		this.conName = conName;
		connTypes = new String[]{"DB2","MSSql","MySql","Oracle","Sybase"};
	}

	protected Control createDialogArea(Composite parent) 
	{
//		tool = new DBTool();
		
		setTitle("数据库信息"); //$NON-NLS-1$
		setMessage("管理数据库连接"); //$NON-NLS-1$

		Composite comp = (Composite) super.createDialogArea(parent);

		Composite composite = new Composite(comp, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 20;
		layout.verticalSpacing = 8;
		layout.numColumns = 2;
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(layout);

		Label lblName = new Label(composite, SWT.NONE);
		lblName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblName.setText("连接名称"); //$NON-NLS-1$

		txtName = new Text(composite, SWT.BORDER);
		txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lblType = new Label(composite, SWT.NONE);
		lblType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblType.setText("连接类型"); //$NON-NLS-1$

		cbType = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 100;
		cbType.setLayoutData(gd);
		cbType.setItems(connTypes);
		cbType.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				// refreshTableList();
				typeSelectChanged();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Label lblConnServer = new Label(composite, SWT.NONE);
		lblConnServer.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblConnServer.setText("服务器"); //$NON-NLS-1$

		txtServer = new Text(composite, SWT.BORDER);
		txtServer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lblConnPort = new Label(composite, SWT.NONE);
		lblConnPort.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblConnPort.setText("端口"); //$NON-NLS-1$

		txtPort = new Text(composite, SWT.BORDER);
		txtPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// txtPort.setText("1433");

		Label lblConnDB = new Label(composite, SWT.NONE);
		lblConnDB.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblConnDB.setText("数据库"); //$NON-NLS-1$

		txtDBName = new Text(composite, SWT.BORDER);
		txtDBName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lblUserName = new Label(composite, SWT.NONE);
		lblUserName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblUserName.setText("用户名"); //$NON-NLS-1$

		txtUserName = new Text(composite, SWT.BORDER);
		txtUserName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lblUserPwd = new Label(composite, SWT.NONE);
		lblUserPwd.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lblUserPwd.setText("密码"); //$NON-NLS-1$

		txtUserPwd = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		txtUserPwd.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		initData();

		Composite pan = new Composite(composite, SWT.NULL);
		pan.setLayout(new GridLayout());

		testConnection = new Button(pan, SWT.PUSH);
		testConnection.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		testConnection.setText("测试连接"); //$NON-NLS-1$
		setButtonLayoutData(testConnection);
		
		
		testConnection.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				tool.setDbName(txtDBName.getText());
				tool.setDbType(cbType.getText());
				tool.setUsername(txtUserName.getText());
				tool.setPassword(txtUserPwd.getText());
				tool.setPort(txtPort.getText());
				tool.setServer(txtServer.getText());
				tool.setConName(txtName.getText());
				
				
				if(tool.testConnection())
				{
					MessageBox mb = new MessageBox(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell());
					mb.setMessage("Succeed!");
					flag = 1;
					mb.open();
				}
				else
				{
					MessageBox mb = new MessageBox(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell());
					mb.setMessage("Failed!");
					mb.open();
				}
			}
		});
	
		
		return comp;
	}
	
	private void initData(){
		if(this.conName == null || this.conName.length()<1)
		{
			if(DBPreferencePage.dbToolList == null || DBPreferencePage.dbToolList.size()<1)
			{
				DBPreferencePage.dbToolList = new ArrayList();
			}
			this.dbToolList = DBPreferencePage.dbToolList ;
			tool = new DBTool();
			return;
		}
		else{
			Iterator it = dbToolList.iterator();
			while(it.hasNext()){
				DBTool dbTool = (DBTool)it.next();
				if(dbTool.getConName().toLowerCase().equals(this.conName.toLowerCase())){
					this.tool = dbTool;
					break;
				}
			}
			this.txtDBName.setText(tool.getDbName());
			this.cbType.setText(tool.getDbType());
			this.txtUserName.setText(tool.getUsername());
			this.txtUserPwd.setText(tool.getPassword());
			this.txtPort.setText(tool.getPort());
			this.txtServer.setText(tool.getServer());
			this.txtName.setText(tool.getConName());
		}
	}

	public void typeSelectChanged() {
		if (cbType.getText().equalsIgnoreCase("MSSQL")) { //$NON-NLS-1$
			txtPort.setText("1433"); //$NON-NLS-1$
		}
		if (cbType.getText().equalsIgnoreCase("Oracle")) { //$NON-NLS-1$
			txtPort.setText("1521"); //$NON-NLS-1$
		}
		if (cbType.getText().equalsIgnoreCase("DB2")) { //$NON-NLS-1$
			txtPort.setText("50000"); //$NON-NLS-1$
		}
		if (cbType.getText().equalsIgnoreCase("Informix")) { //$NON-NLS-1$
			txtPort.setText("1533"); //$NON-NLS-1$
		}
		if (cbType.getText().equalsIgnoreCase("Sybase")) { //$NON-NLS-1$
			txtPort.setText("2638"); //$NON-NLS-1$
		}
		if (cbType.getText().equalsIgnoreCase("MySql")) { //$NON-NLS-1$
			txtPort.setText("3306"); //$NON-NLS-1$
		}
	}
	
	public void okPressed()
	{
		if(tool == null){
			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
					"请测试连接",
					"请测试连接");
			return;
		
		}
		if(flag != 1){
			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
					"数据源连接错误",
					"数据源连接错误");
			return;
		}
		if(!DBPreferencePage.dbToolList.contains(tool))
		{
//			if(DBPreferencePage.dbToolList == null ||DBPreferencePage.dbToolList.size()<1)
//			{
//				DBPreferencePage.dbToolList.add(0,tool);
//			}
//			else
				DBPreferencePage.dbToolList.add(dbToolList.size(),tool);
		}
		WriteToDataSourceXML DataSourceXML = new WriteToDataSourceXML(null,null,dbToolList);
		DataSourceXML.setConName(tool.getConName());
		DataSourceXML.setDataBase(tool.getDbName());
		DataSourceXML.setDbType(tool.getDbType());
		DataSourceXML.setPassWord(tool.getPassword());
		DataSourceXML.setServer(tool.getServer());
		DataSourceXML.setPort(tool.getPort());
		DataSourceXML.setUserName(tool.getUsername());
		
		
		File file = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"\\DataSourceXML.xml");
		if(!file.exists())
		{
			String path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"\\DataSourceXML.xml";
			
			try {
				DataSourceXML.writeXMLFile(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			//读出DataSourceXML文件的DataSources元素
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = null; 
			try {
				db = dbf.newDocumentBuilder();
				Document doc = null; 
				try {
					doc = db.parse(file);
					Element root = doc.getDocumentElement();
					NodeList name = doc.getElementsByTagName("DataSources");
					//向DataSourceXML文件里继续写数据项
				
					Element ds = doc.createElement("DataSource");
					
//					Element projectName = doc.createElement("projectName");
//					Text txtName = doc.createTextNode(getProjectName());
//					projectName.appendChild(txtName);
//					ds.appendChild(projectName);
					
					Element conName = doc.createElement("conName");
					org.w3c.dom.Text conName_model =  (org.w3c.dom.Text) doc.createTextNode(tool.getConName());
					conName.appendChild(conName_model);
					ds.appendChild(conName);
					
					Element server = doc.createElement("server");
					org.w3c.dom.Text server_model = (org.w3c.dom.Text) doc.createTextNode(tool.getServer());
					server.appendChild(server_model);
					ds.appendChild(server);
					
					Element userName = doc.createElement("userName");
					org.w3c.dom.Text userName_model = (org.w3c.dom.Text) doc.createTextNode(tool.getUsername());
					userName.appendChild(userName_model);
					ds.appendChild(userName);
					
					Element password = doc.createElement("password");
					org.w3c.dom.Text password_model = (org.w3c.dom.Text)doc.createTextNode(tool.getPassword());
					password.appendChild(password_model);
					ds.appendChild(password);
					
					Element dbType = doc.createElement("dbType");
					org.w3c.dom.Text dbType_model = (org.w3c.dom.Text)doc.createTextNode(tool.getDbType());
					dbType.appendChild(dbType_model);
					ds.appendChild(dbType);
					
					Element dataBase = doc.createElement("dataBase");
					org.w3c.dom.Text dataBase_model =(org.w3c.dom.Text) doc.createTextNode(tool.getDbName());
					dataBase.appendChild(dataBase_model);
					ds.appendChild(dataBase);
					
					Element port = doc.createElement("port");
					org.w3c.dom.Text port_model = (org.w3c.dom.Text)doc.createTextNode(tool.getPort());
					port.appendChild(port_model);
					ds.appendChild(port);
					
//					project.appendChild(ds);
					name.item(0).appendChild(ds);
					
					TransformerFactory tFactory =TransformerFactory.newInstance();
					Transformer transformer = null;
					try {
						transformer = tFactory.newTransformer();
					} catch (TransformerConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(file);
					try {
						transformer.transform(source, result);
					} catch (TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DBPreferencePage.refresh();
		super.okPressed();
		
	}
	
	private boolean judge(Object c)
	{
		if(c instanceof Text)
		{
			if(((Text)c).getText().equals(""))
			{
				return false;
			}
			else
				return true;
		}
		else if(c instanceof Combo)
		{
			if(((Combo)c).getText().equals(""))
			{
				return false;
			}
			else
				return true;
		}
		else
			return false;
	}
}