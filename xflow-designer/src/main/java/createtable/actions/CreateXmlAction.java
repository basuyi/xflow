package createtable.actions;

import net.ms.designer.editors.component.ui.ComponentEditor;
import net.ms.designer.editors.componentdetail.ui.CompDetailEditor;
import net.ms.designer.editors.enumcomponentdetail.ui.EnumDetailEditor;
import net.ms.designer.editors.packages.ui.PackageEditor;

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



/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class CreateXmlAction  implements IWorkbenchWindowActionDelegate{
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */

    private Shell shell=new Shell();
    private CommFunction commfun=new CommFunction();
//    private Monitor rwp;
   
 
   public CreateXmlAction() {
	}

  
   
	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		
		IEditorPart editor = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

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
					"编辑器不可识别！");
		
		}
	else{		
	boolean createOrNot = MessageDialog.openQuestion(
			window.getShell(),
			"生成文档",
			"是否把当前编辑器中的组件保存为文档形式？");
		

   //检查是否保存
	if(createOrNot==true){	

		PackageEditor pe = (PackageEditor)editor;
			 if(pe.getCurrentElement().getNodeType().equals("enumdetail")||pe.getCurrentElement().getNodeType().equals("componentdetail")){	
			
                	
					try {
                        ProgressMonitorDialog pmd=new ProgressMonitorDialog(shell);
				        Monitor rwp=new Monitor();
				        
						DetailEditorRunable run=new DetailEditorRunable(commfun,editor,rwp);
						
						
						Thread th=new Thread(run);
						th.start();
	
						pmd.run(false,false,(IRunnableWithProgress)rwp);
							
						}
						catch(Exception e){	
							e.printStackTrace();
				         }	

		}
	
			 else if(pe.getCurrentElement().getNodeType().equals("component")){	
					 
				 try {
                     ProgressMonitorDialog pmd=new ProgressMonitorDialog(shell);
				        Monitor rwp=new Monitor();
				        
				        PackageEditorRunable run=new PackageEditorRunable(commfun,editor,rwp);
						
						
						Thread th=new Thread(run);
						th.start();
	
						pmd.run(false,false,(IRunnableWithProgress)rwp);
							
						}
						catch(Exception e){	
							e.printStackTrace();
				         }	

				
		}
			 
			 
			 
//			 else	if(editor.getClass().getName().equals(EnumDetailEditor.class.getName())){
//

//		}

		else if(pe.getCurrentElement().getNodeType().equals("package")){	 

			 try {
                 ProgressMonitorDialog pmd=new ProgressMonitorDialog(shell);
			        Monitor rwp=new Monitor();
			        
			        PackageEditorRunable run=new PackageEditorRunable(commfun,editor,rwp);
					
					
					Thread th=new Thread(run);
					th.start();

					pmd.run(false,false,(IRunnableWithProgress)rwp);
						
					}
					catch(Exception e){	
						e.printStackTrace();
			         }	
		}
			 }
	}
	
	
	if(commfun.getMessageStat()){
		MessageDialog.openInformation(
				window.getShell(),
				"失败！！",
				"生成失败！");
		commfun.closeMessage();
		}
		
		
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
}