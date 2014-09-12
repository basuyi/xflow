/*
 * qing.yang : 2005-6-25
 */
package net.ms.designer.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * @author qing.yang
 *  
 */
public class CoreClasspathContainerInitializer extends
		org.eclipse.jdt.core.ClasspathContainerInitializer {

	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#initialize(org.eclipse.core.runtime.IPath,
	 *      org.eclipse.jdt.core.IJavaProject)
	 */
	public void initialize(IPath containerPath, IJavaProject project)
			throws CoreException {
		int size = containerPath.segmentCount();
		if (size > 0) {
			if (containerPath.segment(0).equals("KENOAH_CORELIB")) {
				CoreClasspathContainer container = new CoreClasspathContainer(
						containerPath);
				JavaCore.setClasspathContainer(containerPath,
						new IJavaProject[] { project },
						new IClasspathContainer[] { container }, null);
			}
		}
	}

	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#canUpdateClasspathContainer(org.eclipse.core.runtime.IPath,
	 *      org.eclipse.jdt.core.IJavaProject)
	 */
	public boolean canUpdateClasspathContainer(IPath containerPath,
			IJavaProject project) {
		return false;
	}

	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#requestClasspathContainerUpdate(org.eclipse.core.runtime.IPath,
	 *      org.eclipse.jdt.core.IJavaProject,
	 *      org.eclipse.jdt.core.IClasspathContainer)
	 */
	public void requestClasspathContainerUpdate(IPath containerPath,
			IJavaProject project, IClasspathContainer containerSuggestion)
			throws CoreException {
	}

	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getDescription(org.eclipse.core.runtime.IPath,
	 *      org.eclipse.jdt.core.IJavaProject)
	 */
	public String getDescription(IPath containerPath, IJavaProject project) {
		return "Kenoah Library";
	}
}
