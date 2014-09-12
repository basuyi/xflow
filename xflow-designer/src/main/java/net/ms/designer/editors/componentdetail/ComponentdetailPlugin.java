package net.ms.designer.editors.componentdetail;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ComponentdetailPlugin extends AbstractUIPlugin 
{

	//The shared instance.
	private static ComponentdetailPlugin plugin;
	private ResourceBundle resourceBundle;
	public static final String PLUGIN_ID = "net.ms.designer.editors.componentdetail";
	/**
	 * The constructor.
	 */
	public ComponentdetailPlugin() 
	{
		super();
		//System.out.println("ComponentdetailPlugin");
		plugin = this;
		 try {
	            resourceBundle = ResourceBundle
	                    .getBundle("net.ms.designer.editors.componentdetail.Messages");
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
		//System.out.println("statr");
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception 
	{
		super.stop(context);
		
	}

	/**
	 * Returns the shared instance.
	 */
	public static ComponentdetailPlugin getDefault() 
	{
		return plugin;
	}
	
	
	    public static String getResourceString(String key) {
	        ResourceBundle bundle = ComponentdetailPlugin.getDefault()
	                .getResourceBundle();
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
