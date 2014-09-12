/*
 * qing.yang : 2005-6-25
 */
package net.ms.designer.core;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.framework.Bundle;

/**
 * @author qing.yang
 *  
 */
public class CoreClasspathContainer implements IClasspathContainer {

	private IPath fpath = null;

	private static IClasspathEntry[] fClasspathEntries = null;

	public CoreClasspathContainer(IPath path) {
		fpath = path;
	}

	/**
	 * @see org.eclipse.jdt.core.IClasspathContainer#getClasspathEntries()
	 */
	public IClasspathEntry[] getClasspathEntries(MsPlugin plugin) {
		if (fClasspathEntries == null)
			fClasspathEntries = computeClasspathEntries(plugin.getBundle());

		return fClasspathEntries;
	}

	/**
	 * @param bundle
	 * @return
	 */
	private IClasspathEntry[] computeClasspathEntries(Bundle bundle) {
		List entries = new ArrayList();

		URL installUrl = bundle.getEntry("/");

		try {
			IPath path = new Path(Platform.resolve(installUrl).getFile());

			IPath libpath = path.append("lib");

			File f = new File(libpath.toOSString());

			File[] fs = f.listFiles(new FileFilter(){

				public boolean accept(File arg0) {
					if(arg0.getName().endsWith(".jar"))
						return true;
					return false;
				}
				
			});

			IPath sourceAttachmentPath = null;
			IPath sourceAttachmentRootPath = null;

			for (int i = 0; i < fs.length; i++) {
				if (fs[i].isFile()) {
					String jarName = fs[i].getName();

					entries.add(JavaCore.newLibraryEntry(libpath
							.append(jarName), sourceAttachmentPath,
							sourceAttachmentRootPath, false));
				}
			}

			IPath weblibpath = path.append("web/WEB-INF/lib");

			File webf = new File(weblibpath.toOSString());

			File[] webfs = webf.listFiles(new FileFilter(){

				public boolean accept(File arg0) {
					if(arg0.getName().endsWith(".jar"))
						return true;
					return false;
				}
				
			});

			for (int i = 0; i < webfs.length; i++) {
				if (webfs[i].isFile()) {
					String jarName = webfs[i].getName();

					entries.add(JavaCore.newLibraryEntry(weblibpath
							.append(jarName), sourceAttachmentPath,
							sourceAttachmentRootPath, false));
				}
			}
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}

		return (IClasspathEntry[]) entries.toArray(new IClasspathEntry[entries
				.size()]);
	}

	/**
	 * @see org.eclipse.jdt.core.IClasspathContainer#getDescription()
	 */
	public String getDescription() {
		//TODO
		return "Kenoah Library";
	}

	/**
	 * @see org.eclipse.jdt.core.IClasspathContainer#getKind()
	 */
	public int getKind() {
		//TODO
		return IClasspathContainer.K_APPLICATION;
	}

	/**
	 * @see org.eclipse.jdt.core.IClasspathContainer#getPath()
	 */
	public IPath getPath() {
		//TODO
		return fpath;
	}

	/**
	 * @see org.eclipse.jdt.core.IClasspathContainer#getClasspathEntries()
	 */
	public IClasspathEntry[] getClasspathEntries() {
		return getClasspathEntries(MsPlugin.getDefault());
	}

}