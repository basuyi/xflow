package net.ms.designer.ui.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.DBTool;
import net.ms.designer.ui.view.MsTreeView;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.ui.util.CoreUtility;
import org.eclipse.jdt.internal.ui.wizards.ClassPathDetector;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathSupport;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jdt.ui.wizards.JavaCapabilityConfigurationPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.UIPlugin;


public class NewAppSecondPage extends JavaCapabilityConfigurationPage{

	
	private static final String FILENAME_PROJECT= ".project"; //$NON-NLS-1$
    private static final String FILENAME_CLASSPATH= ".classpath";
	private NewAppWizard fMainPage;

	private IWorkbench _workbench;

	private IProject fCurrProject;
	
	private MsProject project;
	
	IWorkbenchPage workbenchpage;
	
	private File fDotProjectBackup;
	private File fDotClasspathBackup;
    private Boolean fIsAutobuild;
	
	/**
	 * @return Returns the fCurrProject.
	 */
	public IProject getFCurrProject() {
		return fCurrProject;
	}
	/**
	 * @param currProject The fCurrProject to set.
	 */
	public void setFCurrProject(IProject currProject) {
		fCurrProject = currProject;
	}
	private IPath fCurrProjectLocation;

	private boolean fCanRemoveContent;

//	private static IClasspathEntry CEEC_CORE = JavaCore.newContainerEntry(
//			new Path("CEEC_CORELIB"), false); //$NON-NLS-1$

	/**
	 * @param mainPage
	 */
	public NewAppSecondPage(NewAppWizard mainPage,MsProject project) {
		super();
		fMainPage = mainPage;
		this.project = project;
		if(this.project == null){
			this.project = new MsProject();
		}
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		if (visible)
			createNewProject();
		else
			removeProject();
		super.setVisible(visible);
	}

	/**
	 *  
	 */
	private void removeProject() {
		if (fCurrProject == null || !fCurrProject.exists())
			return;
		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				boolean noProgressMonitor = Platform.getLocation().equals(
						fCurrProjectLocation);
				if (monitor == null || noProgressMonitor)
					monitor = new NullProgressMonitor();
				monitor
						.beginTask(
								//NewWizardMessages.JavaProjectWizardSecondPage_operation_remove, //$NON-NLS-1$
								NewWizardMessages.NewJavaProjectWizardPageTwo_operation_remove,
								3);
				try {
					fCurrProject.delete(true, false, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
					fCurrProject = null;
					//fCanRemoveContent = false;
				}
			}

		};
		try {
			IRunnableContext context = (IRunnableContext) getContainer();
			if (context == null) {
				context = (IRunnableContext) WizardPlugin.getDefault()
						.getActivePage();
			}
			context.run(false, true, op);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException _ex) {
		}

	}

	/**
	 *  
	 */
	public void createNewProject() {
		//TODO
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(fMainPage.txtProject.getText().trim());
//		IPath path = fMainPage.getLocationPath();
		IPath path = ResourcesPlugin.getWorkspace().getRoot().getLocation();

//		//System.out.println("1111111111111");
		if (true)
			fCanRemoveContent = !path.append(fMainPage.txtProject.getText().trim())
					.toFile().exists();
		else
			fCanRemoveContent = !path.toFile().exists();
		final boolean initialize = !proj.equals(fCurrProject)
				|| !proj.equals(fCurrProjectLocation);

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				try {
					updateProject(initialize, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				}
			}
		};

		try {
			IRunnableContext context = (IRunnableContext) getContainer();
			if (context == null)
				context = (IRunnableContext) WizardPlugin.getDefault()
						.getActivePage();
			context.run(false, true, op);
		} catch (Exception e) {

		}
	}

	protected void updateProject(boolean initialize, IProgressMonitor monitor)
			throws CoreException, InterruptedException 
			{
				fCurrProject = ResourcesPlugin.getWorkspace().getRoot().getProject(fMainPage.txtProject.getText().trim());
				fCurrProjectLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation();
				boolean noProgressMonitor = !initialize;// && fCanRemoveContent;
				if (monitor == null || noProgressMonitor)
					monitor = new NullProgressMonitor();
				try 
				{
					monitor
							.beginTask(
									NewWizardMessages.NewJavaProjectWizardPageTwo_operation_create, //$NON-NLS-1$
									7);
					IPath realLocation= fCurrProjectLocation;
					JavaCapabilityConfigurationPage.createProject(fCurrProject,
							fCurrProjectLocation, new SubProgressMonitor(monitor, 1));
					if (Platform.getLocation().equals(fCurrProjectLocation)) 
					{
						realLocation= fCurrProjectLocation.append(fCurrProject.getName()); 
					}
						
					rememberExistingFiles(realLocation);
		
					if (initialize) 
					{
						IClasspathEntry entries[] = (IClasspathEntry[]) null;
						IPath outputLocation = null;
						if(!fCurrProject.getFile(".classpath").exists())
						{
							ClassPathDetector detector = new ClassPathDetector(fCurrProject,monitor);
							entries = detector.getClasspath();
							outputLocation = detector.getOutputLocation();
						}
						else
						{
							  monitor.worked(2);
						}
//						if (outputLocation == null) 
//						{
//							fCurrProject.open(null);
//							outputLocation = createOutputFolder(fCurrProject).getFullPath();
//						}
						
		//				//----- modified by lcx
		//				IPreferenceStore store= PreferenceConstants.getPreferenceStore();
//						IPath srcPath= new Path("temp");
						
						
						IPath srcPath = new Path("src");
						
						IPath binPath= new Path("web\\WEB-INF\\classes");
				
		//				IPath srcPath= new Path(store.getString(PreferenceConstants.SRCBIN_SRCNAME));
		//				IPath binPath= new Path(store.getString(PreferenceConstants.SRCBIN_BINNAME));
		//				final IPath projectPath= fCurrProject.getFullPath();
						if (srcPath.segmentCount() > 0) 
						{
		                    IFolder folder= fCurrProject.getFolder(srcPath);
			                CoreUtility.createFolder(folder, true, true, new SubProgressMonitor(monitor, 1));
			            } 
						else 
			            {			            	
			            	monitor.worked(1);
			            }
					    if (binPath.segmentCount() > 0 && !binPath.equals(srcPath)) 
						
					    if (binPath.segmentCount() > 0 ) 
					    {
		                     IFolder folder= fCurrProject.getFolder(binPath);
		                     CoreUtility.createFolder(folder, true, true, new SubProgressMonitor(monitor, 1));
		                } 
					    else 
					    {
		                     monitor.worked(1);
		                }
					    final IPath projectPath= fCurrProject.getFullPath();
						List cpEntries= new ArrayList();

						cpEntries.add(JavaCore.newSourceEntry(projectPath.append(srcPath)));
//						try {
////							this.copyFile(new File("D:/lili/lib/"),new File("CEEC_CORELIB"));
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						cpEntries.add(CEEC_CORE);
						
						cpEntries.addAll(Arrays.asList(getDefaultClasspathEntry()));
						entries= (IClasspathEntry[]) cpEntries.toArray(new IClasspathEntry[cpEntries.size()]);
						outputLocation= projectPath.append(binPath);
//		            } 
					
//					else 
//		            {
//		               IPath projectPath= fCurrProject.getFullPath();
//		               List cpEntries= new ArrayList();
//		               cpEntries.add(JavaCore.newSourceEntry(projectPath));
//		               cpEntries.addAll(Arrays.asList(getDefaultClasspathEntry()));
//		               entries= (IClasspathEntry[]) cpEntries.toArray(new IClasspathEntry[cpEntries.size()]);
//		
//		               outputLocation= projectPath;
//		               monitor.worked(2);
//		            }
				
		            if (monitor.isCanceled()) 
		            {
		                 throw new OperationCanceledException();
		            }
//				}
		            init(JavaCore.create(fCurrProject), outputLocation, entries, false);
		            configureJavaProject(new SubProgressMonitor(monitor, 3));
					monitor.worked(1);
				}
				}
		
			
			finally 
			{
				monitor.done();
			}
			
				createConfigureFolder();
		//		writeXML();
	}
	 private IClasspathEntry[] getDefaultClasspathEntry() {
		 IClasspathEntry[] defaultJRELibrary= PreferenceConstants.getDefaultJRELibrary();
		 String compliance= null;
		 IPath jreContainerPath= new Path(JavaRuntime.JRE_CONTAINER);
		 if (compliance == null || defaultJRELibrary.length > 1 || !jreContainerPath.isPrefixOf(defaultJRELibrary[0].getPath())) {
		  // use default
		 return defaultJRELibrary;
		  }
		 // try to find a compatible JRE
//		 IVMInstall inst= BuildPathSupport..findMatchingJREInstall(compliance);
//		 if (inst != null) {
//		    IPath newPath= jreContainerPath.append(inst.getVMInstallType().getId()).append(inst.getName());
//		   return new IClasspathEntry[] { JavaCore.newContainerEntry(newPath) };
//		   }
		       return defaultJRELibrary;
		   } 
	 private void rememberExistingFiles(IPath currProjectLocation) throws CoreException 
	 {
		   fDotProjectBackup= null;
		   fDotClasspathBackup= null;
		         
		          File file= currProjectLocation.toFile();
		         if (file.exists()) 
		         {
		             File projectFile= new File(file, FILENAME_PROJECT);
		             if (projectFile.exists())
		             {
		                 fDotProjectBackup= createBackup(projectFile, "project-desc"); //$NON-NLS-1$ //$NON-NLS-2$
		             }
		             File classpathFile= new File(file, FILENAME_CLASSPATH);
		             if (classpathFile.exists()) 
		             {
		                fDotClasspathBackup= createBackup(classpathFile, "classpath-desc"); //$NON-NLS-1$ //$NON-NLS-2$
		             }
		          }
	  }

	 private File createBackup(File file, String name) throws CoreException 
	 {
		 try 
		 {
			 File bak= File.createTempFile("eclipse-" + name, "bak"); //$NON-NLS-1$//$NON-NLS-2$
		     copyFile(file, bak);
		     return bak;
		 } 
		 catch (IOException e)
		 {
//		             IStatus status= new Status(IStatus.ERROR, JavaUI.ID_PLUGIN, IStatus.ERROR, Messages.format(NewWizardMessages.JavaProjectWizardSecondPage_problem_backup, name), e); 
//		              throw new CoreException(status);
		 }
		return file; 
	 }

	 private void copyFile(File file, File target) throws IOException 
	 { 
		 FileInputStream is= new FileInputStream(file);
		 FileOutputStream os= new FileOutputStream(target);
		 try 
		 {
			 byte[] buffer = new byte[8192];
		     while (true) 
		     {
		    	 int bytesRead= is.read(buffer);
		         if (bytesRead == -1)
		         break;
		                 
		         os.write(buffer, 0, bytesRead);
		     }
		 } 
		 finally 
		 {
			 try 
			 	{
				   is.close();
		        } 
			 finally 
			 {
				 os.close();
		     }
		}
	 }

	private void writeXML(){
    	this.project.setProjectName(fMainPage.txtProject.getText());
    	this.project.setGenPackageName("com."+fMainPage.companyText.getText());
    	this.project.setCmpy_short(fMainPage.companyText.getText());
    	this.project.setAuthor(fMainPage.authorText.getText());
    	this.project.setVersion(fMainPage.versionText.getText());
    	//
    	this.project.setDirectory(fMainPage.pathText.getText());
    	this.project.setWorkplace(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
    	this.project.setGenPackageName(fMainPage.genPackageNameText.getText());
		
		net.ms.designer.ui.WriteToProjectXML write = new net.ms.designer.ui.WriteToProjectXML(null,this.project);
    	MsEditorInput editorInput = new MsEditorInput();
    	editorInput.setName("PackageEditor");
    	IEditorInput ieditorInput = editorInput;
    	String editorID = "net.ms.designer.editors.packages.ui.PackageEditor";
    	String viewID = "net.ms.designer.ui.view.MsTreeView";
    	String perspectiveID = "net.ms.designer.ui.wizard.CPerspective";
    	workbenchpage = UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	IEditorPart editor = workbenchpage.findEditor(editorInput);
    	if(editor!=null)
    	{
    		workbenchpage.bringToTop(editor);
    	}
    	else
    	{
	    	try
	    	{
	    		
	    		String xml = this.project.getDirectory()+"\\.configure\\project.xml";
	    		
	        	if(fMainPage.cbConn.getSelectionIndex()>-1)
	        	{
	        		net.ms.designer.ui.preference.WriteToProjectXML writeDB = new net.ms.designer.ui.preference.WriteToProjectXML("DataSource",null,fMainPage.txtProject.getText());
		        	Iterator it = fMainPage.dbList.iterator();
		        	DBTool tool = new DBTool();
		        	while(it.hasNext()){
		        		tool = (DBTool) it.next();
		        		if(tool.getConName().toLowerCase().equals(fMainPage.cbConn.getItem(fMainPage.cbConn.getSelectionIndex()).toLowerCase()))
		        			break;
		        	}
	     
		        	this.project.setDbtool(tool);
		        	
	        	
	    			writeDB.setConName(tool.getConName());
	    			writeDB.setDbName(tool.getDbName());
	    			writeDB.setDbType(tool.getDbType());
	    			writeDB.setPassword(tool.getPassword());
	    			writeDB.setPort(tool.getPort());
	    			writeDB.setServer(tool.getServer());
	    			writeDB.setUsername(tool.getUsername());
	    	    	
	    			try {
	    				writeDB.accessXMLFile(xml);
	    			} catch (Exception e1) {
	    				e1.printStackTrace();
	    			}
	        			
	    			write.accessXMLFile(xml);
	        	}
	        	
	        	else
	        	{
	        		write.writeProjectElement(xml);
	        		write.accessXMLFile(xml);
	        	}
	    		
	    		if(workbenchpage.getPerspective().getId().toLowerCase().equals((perspectiveID).toLowerCase()))
	    		{
	    			MsTreeView view = (MsTreeView)workbenchpage.findView(viewID);
	    			view.setProject(project);
	    			view.refresh();
	    			
	    		}else{
	    			boolean temp = MessageDialog.openQuestion(
	    					workbenchpage.getWorkbenchWindow().getShell(),
	    					"是否转换透视图",
	    					"是否转换透视图");
	    			if(temp==true){
	    				_workbench.showPerspective(perspectiveID,workbenchpage.getWorkbenchWindow());
	    				MsTreeView view = (MsTreeView)workbenchpage.findView(viewID);
		    			view.setProject(project);
		    			view.refresh();
	    			}else{
	    				
	    			}
	    		}		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
    	}
	}

	/**
	 * @param currProject
	 * @return
	 */
	private IFolder createOutputFolder(IProject currProject) {
		IFolder web = currProject.getFolder("web");
		if (!web.exists()) {
			try {
				web.create(true, true, null);
			} catch (CoreException e) {
				// TODO
				e.printStackTrace();
			}
		}		
		return createFolder(web,"WEB-INF\\classes"); 
	}
	/**
	 * @return
	 */
	private IFolder createFolder(IFolder parentFolder,String folderName) {
		IFolder out = parentFolder.getFolder(folderName);
		if (!out.exists()) {
			try {
				out.create(true, true, null);
			} catch (CoreException e) {
				// TODO
				e.printStackTrace();
			}
		}

		return out;
	}

//	private IClasspathEntry[] checkEntries(IClasspathEntry entries[])
//			throws CoreException {
//
//		if (entries == null) {
//			createSrcFolder();
//			return (new IClasspathEntry[] { createSrcClasspathEntry(),
//					CEEC_CORE, JavaRuntime.getDefaultJREContainerEntry() });
//		}
//
//		boolean hasSrcEntry = false;
//		boolean hasTapestryEntry = false;
//		boolean hasDefaultJREEntry = false;
//		List allEntries = Arrays.asList(entries);
//		for (Iterator iter = allEntries.iterator(); iter.hasNext();) {
//			IClasspathEntry element = (IClasspathEntry) iter.next();
//			if (!hasSrcEntry && element.getEntryKind() == 3)
//				hasSrcEntry = true;
//			else if (element.getEntryKind() == 5) {
//				if (!hasTapestryEntry)
//					hasTapestryEntry = element.getPath().segment(0).equals(
//							"CEEC_CORELIB"); 
//				if (!hasDefaultJREEntry)
//					hasDefaultJREEntry = element.getPath().segment(0).equals(
//							JavaRuntime.JRE_CONTAINER);
//			}
//		}
//
//		if (!hasSrcEntry) {
//			createSrcFolder();
//			
//			allEntries.add(createSrcClasspathEntry());
//		}
//		if (!hasTapestryEntry)
//			allEntries.add(CEEC_CORE);
//		if (!hasDefaultJREEntry)
//			allEntries.add(JavaRuntime.getDefaultJREContainerEntry());
//		return (IClasspathEntry[]) allEntries
//				.toArray(new IClasspathEntry[allEntries.size()]);
//	}

//	private IClasspathEntry createSrcClasspathEntry() {
//		return JavaCore.newSourceEntry(new Path("/" + fCurrProject.getName() 
//				+ "/temp")); 
//	}

//	private void createSrcFolder() {
//		IFolder srcFolder = fCurrProject.getFolder("temp"); 
//		if (!srcFolder.exists())
//			try {
//				srcFolder.create(true, true, null);
//			} catch (CoreException e) {
//				e.printStackTrace();
//			}
//	}

	private void createConfigureFolder() {
		IFolder srcFolder = ResourcesPlugin.getWorkspace().getRoot().getProject(fMainPage.txtProject.getText().trim()).getFolder(".configure"); //$NON-NLS-1$
		if (!srcFolder.exists())
			try {
				srcFolder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
	}
	
	public void performFinish(IProgressMonitor monitor) throws CoreException,
			InterruptedException {
		try {
			monitor
					.beginTask(
							NewWizardMessages.NewJavaProjectWizardPageTwo_operation_create, 
							3);
			if (fCurrProject == null)
				updateProject(true, new SubProgressMonitor(monitor, 1));
			configureJavaProject(new SubProgressMonitor(monitor, 2));
			this.writeXML();
		} finally {
			monitor.done();
			fCurrProject = null;
		}
	}

	public void performCancel() {
		removeProject();
	}
}
