/**
 * @author liuchunxia
 * 
 * TODO the main plugin used in workflow
 */
package net.ms.designer.editors.workflow;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.ui.plugin.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;


/**
 * The main plugin class to be used in the desktop.
 */
public class WorkflowPlugin extends AbstractUIPlugin
{

	//The shared instance.
	private static WorkflowPlugin plugin;
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public WorkflowPlugin() 
	{
		super();
//		//System.out.println("workflowPlugin.java");
		
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("net.ms.designer.editors.workflow.Messages");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception 
	{
		super.start(context);
//		//System.out.println("plugin start");
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception 
	{
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static WorkflowPlugin getDefault() 
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) 
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin("workflow", path);
	}
	
	public static String getResourceString(String key) {
		ResourceBundle bundle = WorkflowPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

}
