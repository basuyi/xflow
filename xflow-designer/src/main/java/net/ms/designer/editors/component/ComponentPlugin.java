package net.ms.designer.editors.component;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.ui.plugin.*;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ComponentPlugin extends AbstractUIPlugin {

	//The shared instance.
	private static ComponentPlugin plugin;
	//the resource bundle
	private ResourceBundle resourceBundle;
	/**
	 * The constructor.
	 */
	public ComponentPlugin()
	{
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("net.ms.designer.editors.component.ComponentPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}
	public ComponentPlugin(IPluginDescriptor descriptor) {
        super(descriptor);
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
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ComponentPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("net.ms.designer.editors.component", path);
	}
	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = ComponentPlugin.getDefault().getResourceBundle();
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
}
