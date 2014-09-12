package net.ms.designer.editors.packages;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;


public class PackageImages {

	private static ImageRegistry imageRegistry;
	private final static String ICONS_PATH = "icons/";
	
	public final static String ENDNODE = "ENDNODE";
	public final static String ENDNODE_16 = "ENDNODE_16";
	
//	public final static String STARTNODE="STARTNODE";
//	public final static String STARTNODE_16="STARTNODE_16";
	
	public synchronized static ImageRegistry getImageRegistry() 
	{
		if (imageRegistry == null)
		{
			imageRegistry = new ImageRegistry();
			declareImages();// 将图像注册
		}
		return imageRegistry;
	}

	/**
	 * 在此处进行图象注册
	 */
	private final static void declareImages() 
	{
		declareRegistryImage(ENDNODE, ICONS_PATH + "package.gif");
		declareRegistryImage(ENDNODE_16, ICONS_PATH + "endnode_16.gif");
		
//		declareRegistryImage(STARTNODE_16, ICONS_PATH + "startnode_16.gif");
//		declareRegistryImage(STARTNODE, ICONS_PATH + "startnode.gif");
	}
	private final static void declareRegistryImage(String key, String path) {
		ImageDescriptor desc = ImageDescriptor.createFromFile(PackageImages.class, path);
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
