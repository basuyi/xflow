
package net.ms.designer.core;

import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author lili
 *  
 */
public class CoreClasspathContainerPage extends WizardPage implements
		IClasspathContainerPage {

	private IClasspathEntry fSelection;
	/**
	 * @param pageName
	 */
	public CoreClasspathContainerPage() {
		super(null); //$NON-NLS-1$
		// TODO
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public CoreClasspathContainerPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		// TODO
	}

	/**
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#finish()
	 */
	public boolean finish() {
		fSelection = JavaCore.newContainerEntry(new Path("CEEC_CORELIB")); //$NON-NLS-1$
		return true;
	}

	/**
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#getSelection()
	 */
	public IClasspathEntry getSelection() {
		//TODO
		return fSelection;
	}

	/**
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#setSelection(org.eclipse.jdt.core.IClasspathEntry)
	 */
	public void setSelection(IClasspathEntry containerEntry) {
		//TODO

	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.verticalSpacing = 7;
		layout.horizontalSpacing = 6;
		container.setLayout(layout);

		Label label = new Label(container, SWT.NULL);

		label.setText(null); //$NON-NLS-1$

		setControl(container);

		setTitle(null); //$NON-NLS-1$
		setMessage(null); //$NON-NLS-1$
	}

}
