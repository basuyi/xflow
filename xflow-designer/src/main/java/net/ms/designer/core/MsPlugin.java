package net.ms.designer.core;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class MsPlugin extends AbstractUIPlugin {
    public static final String KDE_DEBUG_FLAG="-KDE_DEBUG_FLAG";
	//The shared instance.
	private static MsPlugin plugin;

	//Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 * 
	 * @throws Exception
	 */
	public MsPlugin() {
		super();
		plugin = this;
		try {		    
		    if(!isDebugMode()){
			    IPath rootpath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
			    File f = rootpath.append("KDE.log").toFile();
			    if(f.exists())
			        f.delete();
			    f.createNewFile();
			    System.setOut(new PrintStream(new FileOutputStream(f)));
			    File fe = rootpath.append("KDE.err").toFile();
			    if(fe.exists())
			        fe.delete();
			    fe.createNewFile();
			    System.setErr(new PrintStream(new FileOutputStream(fe)));
		    }
		    
			resourceBundle = ResourceBundle
					.getBundle("com.kenoah.kde.core.KDEPluginResources");
			
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}catch(Exception e){
		    e.printStackTrace();
		}
	}
	
	private boolean isDebugMode(){
	    try{
		    String commandlines = System.getProperty("eclipse.commands");
		    BufferedReader br = new BufferedReader(new StringReader(commandlines));
		    while(br.ready()){
		    	String s = br.readLine();
		    	if(s == null) break;
		        if(s.equals(KDE_DEBUG_FLAG))
		            return true;
		    }
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return false;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static MsPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = MsPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public IWorkbenchPage getActivePage() {
		return this.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}

	public static void setKenoahLibClasspath(IJavaProject jproject) {
		try {
			IPath libpath = new Path("KENOAH_CORELIB");

			List entrylist = Arrays.asList(jproject.getRawClasspath());

			for (int i = 0; i < entrylist.size(); i++) {
				IClasspathEntry ce = (IClasspathEntry) entrylist.get(i);
				if (ce.getPath() == libpath)
					return;
			}

			ArrayList al = new ArrayList(entrylist);

			al.add(JavaCore.newContainerEntry(libpath, false));
			jproject.setRawClasspath((IClasspathEntry[]) al
					.toArray(new IClasspathEntry[al.size()]), null);
		} catch (JavaModelException e) {
			// TODO
			e.printStackTrace();
		}
	}

	//获得.project文件的信息
	public static void addKenoahProjectNature(IJavaProject project)
			throws CoreException {
		IProjectDescription pd = project.getProject().getDescription();

		List idlist = Arrays.asList(pd.getNatureIds());
		List newidList = new ArrayList();

//		newidList.add(CEECClassapathProject.kenoahNatureId);

		newidList.addAll(idlist);

		pd.setNatureIds((String[]) newidList.toArray(new String[newidList
				.size()]));
		project.getProject().setDescription(pd, null);

		PackageExplorerPart pep = PackageExplorerPart
				.getFromActivePerspective();
		if (pep != null)
			pep.getTreeViewer().refresh();
		//setKenoahLibClasspath(project);
	}

	public static void saveToFile(IFile f, String content) {
		byte[] bytes = null;

		try {
			bytes = content.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO
			bytes = content.getBytes();
		}

		try {
			if (f.exists())
				f
						.setContents(new ByteArrayInputStream(bytes), true,
								true, null);
			else
				f.create(new ByteArrayInputStream(bytes), true, null);
		} catch (CoreException e) {
			// TODO
			e.printStackTrace();
		}
	}
}