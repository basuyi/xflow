package createtable.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import org.eclipse.jdt.ui.wizards.JavaCapabilityConfigurationPage;


public class UpdateComponent extends JavaCapabilityConfigurationPage{
	
	private String projectName=new String();	 //ÏîÄ¿Ãû³Æ	
	private String componentName = new String();
	private String packageName=new String();
	
	private File fDotProjectBackup;
    private File fDotClasspathBackup;
	private IProject fCurrProject;
	private static final String FILENAME_PROJECT= ".project"; //$NON-NLS-1$
	private static final String FILENAME_CLASSPATH= ".classpath";

	    
  public UpdateComponent(String proname,String compname,String pakname){
	  this.projectName=proname;
	  this.componentName=compname;
	  this.packageName=pakname;
  }
  
  protected void updateProject(boolean initialize, IProgressMonitor monitor)
	throws CoreException, InterruptedException, IOException 
	{
		fCurrProject = ResourcesPlugin.getWorkspace().getRoot().getProject(this.projectName);
		
		
//		(2006-11-7 sqh add) begin
	HashSet paths = new HashSet();
	ArrayList result = new ArrayList();
	IClasspathEntry[] ientries = JavaCore.create(fCurrProject).getRawClasspath();
	for (int i = 0; i < ientries.length; i++) {
		IClasspathEntry entry = ientries[i];
//		if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE && entry.getPath().segmentCount() > 1) {
//			if (paths.add(entry.getPath()))
//		paths.add(entry.getPath());
//				result.add(entry);
//		}
				
				if(!entry.getPath().toOSString().equals("\\"+ this.projectName + "\\" +this.projectName + "\\" + this.packageName + "\\" + this.componentName))
				{
					
						paths.add(entry.getPath());
						result.add(entry);
					
				}
	}
	
	//(2006-11-7 sqh add) end 
		IPath fCurrProjectLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		boolean noProgressMonitor = !initialize;// && fCanRemoveContent;
		if (monitor == null || noProgressMonitor)
			monitor = new NullProgressMonitor();
		try 
		{
			monitor
					.beginTask(
							//NewWizardMessages.JavaProjectWizardSecondPage_operation_create, //$NON-NLS-1$
							NewWizardMessages.NewJavaProjectWizardPageTwo_operation_create,
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

					ClassPathDetector detector = new ClassPathDetector(fCurrProject,monitor);
					entries = detector.getClasspath();
					outputLocation = detector.getOutputLocation();

					  monitor.worked(2);

					  
			    IPath srcPath= new Path(this.projectName + "\\" + this.packageName + "\\" + this.componentName);
				IPath binPath= new Path("web\\WEB-INF\\classes");

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

				for(int j = 0;j<result.size();j++)
				{
					cpEntries.add(result.get(j));
				}

				
				entries= (IClasspathEntry[]) cpEntries.toArray(new IClasspathEntry[cpEntries.size()]);
				outputLocation= projectPath.append(binPath);

		
          if (monitor.isCanceled()) 
          {
               throw new OperationCanceledException();
          }

          super.init(JavaCore.create(fCurrProject), outputLocation, entries, true);
          
          configureJavaProject(new SubProgressMonitor(monitor, 3));
			monitor.worked(1);
		}
		}

	
	finally 
	{
		monitor.done();
	}
	
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

}
