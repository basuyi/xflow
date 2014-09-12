package createtable.actions;

import net.ms.designer.core.MsElement;
import net.ms.designer.editors.packages.ui.PackageEditor;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.UIPlugin;

import com.ceec.generator.Generator;
import java.lang.reflect.InvocationTargetException;



public class DetailEditorRunable implements Runnable{
	
	/**
	 * The constructor.
	 */
	
	
//	project.xml中读取项目工作目录以及项目名称
	private String workplace=new String();  //工作目录
	private String projectName=new String();	 //项目名称	
	private String projectPath=new String();   //项目路径
	private String componentName=new String();   //组建名称
	private String componentPath=new String();   //组建路径
	private String packageName=new String();

	private String componentName1 = new String();
    private Monitor rwp;
    private IEditorPart editor;
	private CommFunction commfun;
	
    public DetailEditorRunable(CommFunction commfun,IEditorPart editor,Monitor rwp){
	this.editor=editor;
	this.rwp=rwp;
    this.commfun=commfun;
    }
		public void run() {

			IWorkspace myWorkspace = ResourcesPlugin.getWorkspace();
			workplace = myWorkspace.getRoot().getLocation().toOSString();
			
			Object obj;
			try {
				obj = PropertyUtils.getProperty(editor.getEditorInput(),"project");
				this.projectName = (String)PropertyUtils.getProperty(obj,"projectName");
				this.componentName = (String)PropertyUtils.getProperty(obj,"componentName");
//				this.genPackageName = (String)PropertyUtils.getProperty(obj,"genPackageName");
				this.packageName = (String)PropertyUtils.getProperty(obj,"packageName");
//				this.companyName =(String)PropertyUtils.getProperty(obj,"company");
//				this.componentName1 = this.componentName;
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				 commfun.openMessage();
				rwp.monitorCancel();
				 
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				 commfun.openMessage();
				rwp.monitorCancel();
				
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				 commfun.openMessage();
				rwp.monitorCancel();
			
			}
		
			// added by mashuai at 2006/11/17
			//*************Begin****************
			PackageEditor packageEditor = (PackageEditor)editor;
			MsElement element = packageEditor.getCurrentElement();
			//**************End*****************
		
		 //-------------
	

	       //组件保存地址
			this.projectPath=workplace+"\\"+this.projectName+"\\";
	    
			this.componentName1 = this.componentName;
			//组建名称
//			this.componentName=this.projectName+"."+this.packageName+"."+this.componentName+".xml";
			this.componentName = element.getId() + ".xml";
		    //组建地址
			this.componentPath=workplace+"\\"+this.projectName+"\\.configure\\";
				
		
				
//			////	调用生成器方法	

			Generator ganerator=new Generator();
			
			ganerator.setComponentXMLName(this.componentName);
			ganerator.setComponentXMLPath(this.componentPath);
			ganerator.setGenerationPath(this.projectPath);
			
			
			try {
				ganerator.generate();
				 
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				 commfun.openMessage();
				//  rwp.monitorCancel();
				
				
			}
			
//			try{
//
//				//updaTED by lili
//				UpdateComponent upcom=new UpdateComponent(this.projectName,this.componentName1,this.packageName);
//				upcom.updateProject(true,null);
//				//------------
//		        }catch(Exception e)
//		      {
//			  e.printStackTrace();
//			//  commfun.openMessage();
//			 // rwp.monitorCancel();
//			
//		      }
		
		        rwp.monitorCancel();
			// TODO Auto-generated method stub
			
		}
	}
	


