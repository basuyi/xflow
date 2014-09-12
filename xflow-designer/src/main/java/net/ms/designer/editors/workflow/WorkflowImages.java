/**
 * @author liuchunxia
 * 
 * TODO registry of images needed in workflow
 */
package net.ms.designer.editors.workflow;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * 图像注册表。系统所有图像在此注册
 */
public class WorkflowImages {
	private static ImageRegistry imageRegistry;
	private final static String ICONS_PATH = "icons/";
	
	public final static String ENDNODE = "ENDNODE";
	public final static String ENDNODE_16 = "ENDNODE_16";
	
	
	public final static String EXTERNALAPPLICATION="EXTERNALAPPLICATION";
	public final static String EXTERNALAPPLICATION_16="EXTERNALAPPLICATION_16";
	
	public final static String JAVAAPPLICATION="JAVAAPPLICATION";
	public final static String JAVAAPPLICATION_16="JAVAAPPLICATION_16";
	
	public final static String LINKNODE="LINKNODE";
	public final static String LINKNODE_16="LINKNODE_16";
	
	public final static String PROCESSMONITOR="PROCESSMONITOR";
	public final static String PROCESSMONITOR_16="PROCESSMONITOR_16";
	
	public final static String ROUTE="ROUTE";
	public final static String ROUTE_16="ROUTE_16";
	
	public final static String SCRIPTAPPLICATION="SCRIPTAPPLICATION";
	public final static String SCRIPTAPPLICATION_16="SCRIPTAPPLICATION_16";
	
	public final static String STARTNODE="STARTNODE";
	public final static String STARTNODE_16="STARTNODE_16";
	
	public final static String SUBFLOW="SUBFLOW";
	public final static String SUBFLOW_16="SUBFLOW_16";
	
	public final static String WEBBAPPLICATION="WEBBAPPLICATION";
	public final static String WEBBAPPLICATION_16="WEBBAPPLICATION_16";
	
	public final static String WEBSERVICEAPPLICATION="WEBSERVICEAPPLICATION";
	public final static String WEBSERVICEAPPLICATION_16="WEBSERVICEAPPLICATION_16";
	
	public final static String CONNECTION = "connection";
	public final static String CONNECTION_16 = "connection_16";
	
	
	//for cut ,copy and paste
	public final static String CUT="cut";
	public final static String COPY="copy";
	public final static String PASTE="paste";
	
	//workflowCreationPage
	public final static String WORKFLOWWIZARD="WORKFLOWWIZARD";
	public final static String TESTNODE="TESTNODE";
	
	public final static String OUTLINE="OUTLINE";
	public final static String OVERVIEW="OVERVIEW";
	
	public final static String WORKFLOWVIEW="WORKFLOWVIEW";
	
	
	/**
	 * 单例模式
	 */
	public synchronized static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
			declareImages();// 将图像注册
		}
		return imageRegistry;
	}

	/**
	 * 在此处进行图象注册
	 */
	private final static void declareImages() {
		declareRegistryImage(ENDNODE, ICONS_PATH + "endnode.gif");
		declareRegistryImage(ENDNODE_16, ICONS_PATH + "endnode_16.gif");
		
		
		declareRegistryImage(EXTERNALAPPLICATION, ICONS_PATH + "externalapplication.gif");
		declareRegistryImage(EXTERNALAPPLICATION_16, ICONS_PATH + "externalapplication_16.gif");
		declareRegistryImage(JAVAAPPLICATION_16, ICONS_PATH + "javaapplication_16.gif");
		declareRegistryImage(JAVAAPPLICATION, ICONS_PATH + "javaapplication.gif");
		declareRegistryImage(LINKNODE_16, ICONS_PATH + "linknode_16.gif");
		declareRegistryImage(LINKNODE, ICONS_PATH + "linknode.gif");
		
		declareRegistryImage(PROCESSMONITOR_16, ICONS_PATH + "processmonitor_16.gif");
		declareRegistryImage(PROCESSMONITOR, ICONS_PATH + "processmonitor.gif");
		
		declareRegistryImage(ROUTE_16, ICONS_PATH + "route_16.gif");
		declareRegistryImage(ROUTE, ICONS_PATH + "route.gif");
		declareRegistryImage(SCRIPTAPPLICATION_16, ICONS_PATH + "scriptapplication_16.gif");
		declareRegistryImage(SCRIPTAPPLICATION, ICONS_PATH + "scriptapplication.gif");
		declareRegistryImage(STARTNODE_16, ICONS_PATH + "startnode_16.gif");
		declareRegistryImage(STARTNODE, ICONS_PATH + "startnode.gif");
		declareRegistryImage(SUBFLOW_16, ICONS_PATH + "subflow_16.gif");
		declareRegistryImage(SUBFLOW, ICONS_PATH + "subflow.gif");
		declareRegistryImage(WEBBAPPLICATION_16, ICONS_PATH + "webbapplication_16.gif");
		declareRegistryImage(WEBBAPPLICATION, ICONS_PATH + "webbapplication.gif");
		declareRegistryImage(WEBSERVICEAPPLICATION_16, ICONS_PATH + "webserviceapplication_16.gif");
		declareRegistryImage(WEBSERVICEAPPLICATION, ICONS_PATH + "webserviceapplication.gif");
		
		declareRegistryImage(CONNECTION_16, ICONS_PATH + "connection16.gif");
		declareRegistryImage(CONNECTION, ICONS_PATH + "connection.gif");
		
		
		
		
		declareRegistryImage(CUT, ICONS_PATH + "subflow_16.gif");
		declareRegistryImage(COPY, ICONS_PATH + "subflow_16.gif");
		declareRegistryImage(PASTE, ICONS_PATH + "subflow_16.gif");

		
		declareRegistryImage(WORKFLOWWIZARD, ICONS_PATH +"webserviceapplication.gif"); 
		declareRegistryImage(TESTNODE, ICONS_PATH +"webserviceapplication.gif");
		
		declareRegistryImage(OUTLINE, ICONS_PATH +"outline.gif");
		declareRegistryImage(OVERVIEW, ICONS_PATH +"overview.gif");
		
		
		declareRegistryImage(WORKFLOWVIEW, ICONS_PATH +"WorkflowView.gif");
	}

	private final static void declareRegistryImage(String key, String path) {
		ImageDescriptor desc = ImageDescriptor.createFromFile(WorkflowImages.class, path);
		imageRegistry.put(key, desc);
	}

	/**
	 * 取得一个图片资源
	 * @param key 图片注册名
	 */
	public static Image getImage(String key) {
		return getImageRegistry().get(key);
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		return getImageRegistry().getDescriptor(key);
	}
}