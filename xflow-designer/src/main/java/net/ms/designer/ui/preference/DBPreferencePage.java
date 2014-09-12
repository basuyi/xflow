package net.ms.designer.ui.preference;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import msdesigner.MsdesignerPlugin;
import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.DBTool;
import net.ms.designer.ui.wizard.NewAppWizard;

import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.*;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.Platform;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.internal.dialogs.ViewContentProvider;
import org.eclipse.ui.internal.dialogs.ViewLabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.core.resources.ResourcesPlugin;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class DBPreferencePage extends PreferencePage implements IWorkbenchPreferencePage ,Serializable
{
	public static final String DBCONFIGPREF_ID = "net.ms.designer.ui.preference.DBPreferencePage";
	static TableViewer viewer;
	static Text txtPort;
	static Text txtDBName;
	static Text txtUserName;
	Composite parent2;
	public static List dbToolList ; //包含所有参数的列表
	List dbTreeViewList;   //只包含treeView中显示的参数
	public static String projectName;
	
	public static void Show() {
		
		DBPreferencePage dbConf = new DBPreferencePage();
        IPreferenceNode targetNode = new PreferenceNode(DBPreferencePage.DBCONFIGPREF_ID, dbConf);

        PreferenceManager manager = new PreferenceManager();
        manager.addToRoot(targetNode);
        PreferenceDialog dialog = new PreferenceDialog(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
                .getShell(), manager);
        dialog.create();
        dialog.setMessage(targetNode.getLabelText());
        dialog.open();  
    }
	
	public void init(IWorkbench workbench)
	{
//		读取XML文件中的所有数据信息    lili
		if(dbToolList == null)
		{
			String dataSource;
			File file =  new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"\\DataSourceXML.xml");
			if(file.exists())
			{
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
			dbToolList = new ArrayList();
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
					
					dbToolList.add(dbTool2);
				}
				
			}
			}
			
		}
		setPreferenceStore(MsdesignerPlugin.getDefault().getPreferenceStore());
	}
	public Control createContents(Composite parent)
	{
		GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginHeight = 5;
        layout.marginWidth = 0;

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));

        Label caption = new Label(composite, SWT.NONE);
        GridData gd = new GridData();
        gd.horizontalSpan = 2;
        caption.setLayoutData(gd);
        caption.setText("管理数据库配置"); //$NON-NLS-1$

        createDBInfoGroup(composite);

        createDBInfoDetailGroup(composite);

        return composite;
	}
	private void createDBInfoDetailGroup(Composite composite) 
	{
        Group group = new Group(composite, SWT.NONE);

        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        group.setLayoutData(gd);

        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        gl.makeColumnsEqualWidth = false;
        group.setLayout(gl);
        group.setFont(composite.getFont());
        group.setText("详细信息"); //$NON-NLS-1$

        Label lblConnPort = new Label(group, SWT.NONE);
        lblConnPort.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        lblConnPort.setText("端口"); //$NON-NLS-1$

        txtPort = new Text(group, SWT.BORDER | SWT.READ_ONLY);
        txtPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
       

        Label lblConnDB = new Label(group, SWT.NONE);
        lblConnDB.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        lblConnDB.setText("数据库"); //$NON-NLS-1$

        txtDBName = new Text(group, SWT.BORDER | SWT.READ_ONLY);
        txtDBName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label lblUserName = new Label(group, SWT.NONE);
        lblUserName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        lblUserName.setText("用户名"); //$NON-NLS-1$

        txtUserName = new Text(group, SWT.BORDER | SWT.READ_ONLY);
        txtUserName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      
	}

    protected int getTableStyle() 
    {
        return SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION;
    }

    /**
     * @param composite
     */
    private void createDBInfoGroup(Composite composite) 
    {
        Composite parent = new Composite(composite, SWT.NONE);
        parent.setLayout(new FillLayout());
        parent.setLayoutData(new GridData(GridData.FILL_BOTH));

        viewer = new TableViewer(parent, getTableStyle());
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setLinesVisible(true);

        TableColumn column1 = new TableColumn(viewer.getTable(), SWT.SINGLE);
        column1.setText("连接名称"); //$NON-NLS-1$
        column1.setWidth(100);

        TableColumn column2 = new TableColumn(viewer.getTable(), SWT.SINGLE);
        column2.setText("连接类型"); //$NON-NLS-1$
        column2.setWidth(100);

        TableColumn column3 = new TableColumn(viewer.getTable(), SWT.NONE);
        column3.setText("服务器"); //$NON-NLS-1$
        column3.setWidth(200);

        viewer.setContentProvider(new TableViewerContentProvider());
        viewer.setLabelProvider(new TableViewerLabelProvider());
        viewer.setSorter(new NameSorter());
        viewer.setInput(dbToolList);

        createButtons(composite);
    }
    private void createButtons(Composite composite) 
    {
        parent2 = new Composite(composite, SWT.NONE);
        parent2.setLayout(new GridLayout());
        parent2.setLayoutData(new GridData(GridData.FILL_BOTH));

        Button btn = new Button(parent2, SWT.PUSH);
        setButtonLayoutData(btn);
        btn.setText("新建..."); 
        btn.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent e)
        	{
        		DBConfigDialog dialog = new DBConfigDialog(UIPlugin.getDefault()
        				.getWorkbench().getActiveWorkbenchWindow().getShell(),dbToolList,null);
        		dialog.open();
        		
        	}
        });

        Button btn2 = new Button(parent2, SWT.PUSH);
        setButtonLayoutData(btn2);
        btn2.setText("配置..."); 
        btn2.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent e)
        	{
        		if(viewer.getTable().getSelectionIndex()<0)
        		{
        			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
        					"请选择数据源",
        					"请选择数据源");
        			return;
        		}
        		else
        		{
	        		DBConfigDialog dialog = new DBConfigDialog(UIPlugin.getDefault()
	        				.getWorkbench().getActiveWorkbenchWindow().getShell(),dbToolList,((DBTool)viewer.getElementAt(viewer.getTable().getSelectionIndex())).getConName());
	        		dialog.open();
        		}
        	}
        });

        Button btn3 = new Button(parent2, SWT.PUSH);
        setButtonLayoutData(btn3);
        btn3.setText("删除..."); 
        btn3.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent e)
        	{
        		if(viewer.getTable().getSelectionIndex()<0)
        		{
        			MessageDialog.openConfirm(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
        					"请选择数据源",
        					"请选择数据源");
        			return;
        		}
        		Iterator it = dbToolList.iterator();
    			while(it.hasNext()){
    				DBTool dbTool = (DBTool)it.next();
    				if(dbTool.getConName().toLowerCase().equals(((DBTool)viewer.getElementAt(viewer.getTable().getSelectionIndex())).getConName().toLowerCase())){
    					dbToolList.remove(dbTool);
    					break;
    				}
    			}
        		refresh();
        	}
        });


    }
  
    protected GridData setButtonLayoutData(Button button) 
    {
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        data.heightHint = convertVerticalDLUsToPixels(IDialogConstants.BUTTON_HEIGHT);
        int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
        data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
        button.setLayoutData(data);
        return data;
    }


    class NameSorter extends ViewerSorter 
    {
    }
    
    
    public boolean performOk(){
    	//System.out.println("dbToolList:"+dbToolList.size());
//    	this.Show();
    	
//    	if(!((CEECEditorInput)(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput())).exists())
//    	{
//    		return super.performOk();
//    	}
    	if(this.projectName != null)
    	{
    		NewAppWizard.refresh();
    		this.projectName = null;
    		return super.performOk();
    	}
//    	String projectName1;
//    	projectName1 = ((CEECEditorInput)(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput())).getProject().getProjectName();
//    	if(this.projectName == null && !(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName1)).exists())
//    	{
//    		return super.performOk();
//    	}
//    	if((this.projectName != null) && !(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName1)).exists())
//    	{
//    		NewAppWizard.refresh();
//    	}
    	else
    	{ 
    		String projectName1;
        	projectName1 = ((MsEditorInput)(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput())).getProject().getProjectName();
    		if(viewer.getTable().getSelectionIndex()<0)
    		{
    			return super.performOk();
    		}
//    		projectName1 = ((CEECEditorInput)(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput())).getProject().getProjectName();
    		String xml = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName1).getFolder(".configure").getFile("project.xml").getLocation().toOSString();
    	
    		net.ms.designer.ui.preference.WriteToProjectXML writeDB = new net.ms.designer.ui.preference.WriteToProjectXML("DataSource",null,projectName1);
    
    		 DBTool tool = ((DBTool)viewer.getElementAt(viewer.getTable().getSelectionIndex()));
    	
    		 ((MsEditorInput)(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput())).getProject().setDbtool(tool);
        	
    	
//			writeDB.setConName(tool.getConName());
//			writeDB.setDbName(tool.getDbName());
//			writeDB.setDbType(tool.getDbType());
//			writeDB.setPassword(tool.getPassword());
//			writeDB.setPort(tool.getPort());
//			writeDB.setServer(tool.getServer());
//			writeDB.setUsername(tool.getUsername());
	    	
		File file = new File(xml);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null; 
		try {
			db = dbf.newDocumentBuilder();
			Document doc = null; 
			try {
				doc = db.parse(file);
				Element root = doc.getDocumentElement();
				NodeList name = root.getElementsByTagName("project");
				//向DataSourceXML文件里继续写数据项
			
				Element ds = doc.createElement("DataSource");
				
//				Element projectName = doc.createElement("projectName");
//				Text txtName = doc.createTextNode(getProjectName());
//				projectName.appendChild(txtName);
//				ds.appendChild(projectName);
				
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
				
//				project.appendChild(ds);
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
    	return super.performOk();
    }
    
    DBPreferencePage getInstance(){
    	return this;
    }
 

   public static void refresh(){
	   viewer.setContentProvider(new TableViewerContentProvider());
       viewer.setLabelProvider(new TableViewerLabelProvider());
//       viewer.setInput(getdbTreeViewList());
       viewer.setInput(dbToolList);
       if(dbToolList != null && dbToolList.size()==1)
       {
	       txtPort.setText(((DBTool)dbToolList.get(0)).getPort());
	       txtDBName.setText(((DBTool)dbToolList.get(0)).getDbName());
	       txtUserName.setText(((DBTool)dbToolList.get(0)).getUsername());
       }
       if(dbToolList != null && dbToolList.size()>1)
       {
    	   if(viewer.getTable().getSelectionIndex()<0)
    	   {
		       txtPort.setText(((DBTool)dbToolList.get(viewer.getTable().getItemCount()-1)).getPort());
		       txtDBName.setText(((DBTool)dbToolList.get(viewer.getTable().getItemCount()-1)).getDbName());
		       txtUserName.setText(((DBTool)dbToolList.get(viewer.getTable().getItemCount()-1)).getUsername());
    	   }
    	   else
    	   {
    		   txtPort.setText(((DBTool)dbToolList.get(viewer.getTable().getSelectionIndex())).getPort());
		       txtDBName.setText(((DBTool)dbToolList.get(viewer.getTable().getSelectionIndex())).getDbName());
		       txtUserName.setText(((DBTool)dbToolList.get(viewer.getTable().getSelectionIndex())).getUsername());
    	   }
       }
	   viewer.refresh();
   }

}
