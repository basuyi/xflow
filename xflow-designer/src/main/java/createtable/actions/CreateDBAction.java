package createtable.actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ms.designer.core.MsElement;
import net.ms.designer.core.DBTool;
import net.ms.designer.editors.component.ui.ComponentEditor;
import net.ms.designer.editors.componentdetail.ui.CompDetailEditor;
import net.ms.designer.editors.enumcomponentdetail.ui.EnumDetailEditor;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.ceec.generator.DBGenerator;


/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class CreateDBAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	 private Shell shell=new Shell();
		
//		project.xml中读取项目工作目录以及项目名称
		private String workplace=new String();  //工作目录
		private String projectName=new String();	 //项目名称	
		private String componentName=new String();   //组建名称
		private String componentname=new String();
		private String componentPath=new String();   //组建路径
		private Connection con;
		private String packageName=new String();
	    private DBTool tool=null;
		private Monitor rwp;
		private CommFunction commfun=new CommFunction();
		

	public CreateDBAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		IEditorPart editor = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	
		// added at 2006/11/21
		//*************Begin****************
		PackageEditor packageEditor = (PackageEditor)editor;
		MsElement element = packageEditor.getCurrentElement();
		//**************End*****************
		
		if(editor==null){
			MessageDialog.openInformation(
					window.getShell(),
					"Attention",
					"editors is NULL!You must push this bottom in CEEC Editor!");
		
		}else 
			if((editor.getClass().getName().equals(CompDetailEditor.class.getName())
			   ||editor.getClass().getName().equals(EnumDetailEditor.class.getName())
			   ||editor.getClass().getName().equals(ComponentEditor.class.getName())
			   ||editor.getClass().getName().equals(PackageEditor.class.getName())
			   )==false){
				MessageDialog.openInformation(
						window.getShell(),
						"Attention",
						"不可识别的Editor!!");
			
			}else{
	boolean createOrNot = MessageDialog.openQuestion(
			window.getShell(),
			"生成数据库表",
			"是否确定把当前编辑器内容生成为数据表？");
		

//检查是否保存
	if(createOrNot==true){	
		
		IWorkspace myWorkspace = ResourcesPlugin.getWorkspace();
		workplace = myWorkspace.getRoot().getLocation().toOSString();
		
		Object obj ;
		
		try {
			obj = PropertyUtils.getProperty(editor.getEditorInput(),"project");

		this.projectName = (String)PropertyUtils.getProperty(obj,"projectName");
		this.packageName = (String)PropertyUtils.getProperty(obj,"packageName");
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			commfun.openMessage();	
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			commfun.openMessage();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			commfun.openMessage();
		}
		 //组建地址
		this.componentPath=workplace+"\\"+this.projectName+"\\.configure\\";
		
//		数据库连接取得
		
		tool=commfun.getDBTool(this.workplace,this.projectName);
		if(tool==null||tool.getConnection()==null){
			MessageDialog.openError(
					window.getShell(),
					"建表错误",
					"请检查数据库连接！");
			this.con=null;
		return;	 
		}else{
				try{
		this.con=tool.getConnection();
				}catch(Exception ex){
					ex.printStackTrace();	
				}
			
			 if(packageEditor.getCurrentElement().getNodeType().equals("enumdetail") || packageEditor.getCurrentElement().getNodeType().equals("componentdetail")){	

		
					try {
						obj = PropertyUtils.getProperty(editor.getEditorInput(),"project");
						this.componentName = (String)PropertyUtils.getProperty(obj,"componentName");
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						commfun.openMessage();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						commfun.openMessage();
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						commfun.openMessage();
					}
					
					// modified at 2006/11/21
					// aimed to change the path and name of the component
					//*************Begin************
//			        this.componentname=this.componentName;
					this.componentname = element.getNodeName();
					//组建名称
//					this.componentName=this.projectName+"."+this.packageName+"."+this.componentName+".xml";
					this.componentName = element.getId() + ".xml";
					//*************End***************
				
					//是否存在
					if(commfun.ifExists(this.con,this.componentname)){
						boolean OverWrite = MessageDialog.openQuestion(
								window.getShell(),
								"数据库表已经存在",
								"是否根据现有数据覆盖原来的数据表？");
						
						if(OverWrite==false){

							return;
						}
						
					}

				 try {
                     ProgressMonitorDialog pmd=new ProgressMonitorDialog(shell);
				        Monitor rwp=new Monitor();
				         
				        DetailEditorDBRunable run=new DetailEditorDBRunable(this.componentname,commfun,rwp,this.con,this.componentName,this.componentPath);
						
						
						Thread th=new Thread(run);
						th.start();
	
						pmd.run(false,false,(IRunnableWithProgress)rwp);
							
						}
						catch(Exception e){	
							e.printStackTrace();
							commfun.openMessage();
						}	

		
						}
			 
	
			 else if(packageEditor.getCurrentElement().getNodeType().equals("component")){	
				 boolean Exist=false;
					try {
						Exist=tableExist();
					} catch (ParserConfigurationException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						commfun.openMessage();
					} catch (SAXException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						commfun.openMessage();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						commfun.openMessage();
					}
						if(Exist==true){
						
							
							boolean OverWrite = MessageDialog.openQuestion(
								window.getShell(),
								"数据库表已经存在",
								"是否根据现有数据覆盖原来的数据表？");
						
						if(OverWrite==false){
							return;
						}
						}
				    
				 try {
                     ProgressMonitorDialog pmd=new ProgressMonitorDialog(shell);
				        Monitor rwp=new Monitor();
				        
						PackageEditorDBRunable run=new PackageEditorDBRunable(commfun,rwp,
								                                              this.con,this.projectName,this.componentPath,this.packageName
								                                              );
						
						
						Thread th=new Thread(run);
						th.start();
	
						pmd.run(false,false,(IRunnableWithProgress)rwp);
							
						}
						catch(Exception e){	
							e.printStackTrace();
							commfun.openMessage();
				         }	

		
			 }
			
				

			 
//	   else	if(editor.getClass().getName().equals(EnumDetailEditor.class.getName())){

//		}
	
			 
		else if(packageEditor.getCurrentElement().getNodeType().equals("package")){	
			boolean Exist=false;
			try {
				Exist=tableExist();
			} catch (ParserConfigurationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				commfun.openMessage();
			} catch (SAXException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				commfun.openMessage();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				commfun.openMessage();
			}
				if(Exist==true){
				
					
					boolean OverWrite = MessageDialog.openQuestion(
						window.getShell(),
						"数据库表已经存在",
						"是否根据现有数据覆盖原来的数据表？");
				
				if(OverWrite==false){
					return;
				}
				}

			 try {
                 ProgressMonitorDialog pmd=new ProgressMonitorDialog(shell);
			        Monitor rwp=new Monitor();
			        
			        PackageEditorDBRunable run=new PackageEditorDBRunable(commfun,rwp,
			        		                             this.con,this.packageName,this.componentPath,this.packageName);
					
					
					Thread th=new Thread(run);
					th.start();

					//pmd.run(false,false,(IRunnableWithProgress)rwp);
						
					}
					catch(Exception e){	
						e.printStackTrace();
						commfun.openMessage();
					}	

					
				} 
				
	
			}
		

			
	}
			}
	
		if(commfun.getMessageStat()){
		MessageDialog.openInformation(
				window.getShell(),
				"失败！！",
				"生成失败！");
		}
		
		
		
		
		}	
	
	
public boolean tableExist() throws ParserConfigurationException, SAXException, IOException{
	
	
    //		开始读取xml
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder builder;
				
					builder = factory.newDocumentBuilder();
			
				Document document = builder.parse(this.componentPath+"\\project.xml");
	  //      	root=richweb 取得根元素		
				Element root = document.getDocumentElement();
	
	//	root第一层project		
				if(root.getElementsByTagName("project")!=null){
			Element prot = (Element) root.getElementsByTagName("project").item(0);
								
	//	get package
			if(prot.getElementsByTagName("package")!=null){
				NodeList packages = prot.getElementsByTagName("package");
		
				for(int i=0;i<packages.getLength();i++)
				
				{
					Element packagelement = (Element)packages.item(i);
				if(packagelement.getElementsByTagName("name")!=null){
					Element packageElement=(Element)packagelement.getElementsByTagName("name").item(0);
					Text packagetext = (Text)packageElement.getFirstChild();
					
				         if(packagetext.getNodeValue().equalsIgnoreCase(this.packageName)||this.packageName.equals(null)||this.packageName.equals("")){
				 
				        	 this.packageName=packagetext.getNodeValue();
				        	 
				        	 
				        	 //get component
				        	 if(packagelement.getElementsByTagName("component")!=null){
				        	 NodeList component =packagelement.getElementsByTagName("component");
				        	
				        	 for(int j=0;j<component.getLength();j++){
				        		 Element componentelement = (Element)component.item(j);
				        		 if(componentelement.getElementsByTagName("name")!=null){
									Element comElement=(Element)componentelement.getElementsByTagName("name").item(0);
				        		 Text componentext=(Text)comElement.getFirstChild();
				        		 
				        		 //	组建名称
				        		 this.componentName=componentext.getNodeValue();
									
				        		 if (commfun.ifExists(this.con,this.componentName)){
								        return true;	
									}
		                     	}
				        	 }
				         }
				        	 //get Enum
				        	 if(packagelement.getElementsByTagName("enumeration")!=null){
				        	 NodeList enums =packagelement.getElementsByTagName("enumeration");
				        	 for(int j=0;j<enums.getLength();j++){
				        		 Element enumsElement = (Element)enums.item(i);
				        		 if(enumsElement.getElementsByTagName("name")!=null){
									Element comElement=(Element)enumsElement.getElementsByTagName("name").item(0);
				        		 Text enumtext=(Text)comElement.getFirstChild();
				        		 
								 //	组建名称								 		
				        		 this.componentName=enumtext.getNodeValue();
											if(commfun.ifExists(this.con,this.componentName)){
												
													return true;
												
											}

				        		 }
				        	 }
				         }
				        	 
				
				
				         }
				         }
				}
				}
			}

	return false;
}
	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
	public int getProgress() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getActivity() {
		// TODO Auto-generated method stub
		return null;
	}
	public void stopMask() {
		// TODO Auto-generated method stub
		
	}
}