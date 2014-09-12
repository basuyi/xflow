package msdesigner;

import net.ms.designer.projectbuilder.service.ProjectBuilder;

import org.eclipse.ui.plugin.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class MsdesignerPlugin extends AbstractUIPlugin {

	// The shared instance.
	private static MsdesignerPlugin plugin;
	public static String PROJECT_CONFIG_POLICY = "DB";
	public static ProjectBuilder projectBuilder;

	/**
	 * The constructor.
	 */
	public MsdesignerPlugin() {
		plugin = this;
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"workflow_context.xml");
		projectBuilder = (ProjectBuilder) context
				.getBean("projectBuilderProxy");
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
	public static MsdesignerPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("msdesigner", path);
	}
}
