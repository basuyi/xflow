package net.ms.designer.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import msdesigner.MsdesignerPlugin;
import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.DBTool;
import net.ms.designer.projectbuilder.model.Company;
import net.ms.designer.projectbuilder.model.Project;
import net.ms.designer.projectbuilder.service.ProjectBuilder;
import net.ms.designer.ui.WriteToProjectXML;
import net.ms.designer.ui.preference.DBPreferencePage;
import net.ms.designer.ui.view.MsTreeView;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.core.internal.resources.Container;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import net.ms.designer.editors.packages.ui.PackageEditor;
//import net.ms.designer.ui.MsProject;

//import net.ms.designer.editors.packages.ui.PackageEditor;

public class NewProjectWizard extends Wizard implements INewWizard {
	private IWorkbench _workbench;
	NewAppWizard page;
	IWorkbenchPage workbenchpage;
	MsProject project = new MsProject();
	IWorkspace workspace = ResourcesPlugin.getWorkspace();

	NewAppSecondPage javaPage;

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this._workbench = workbench;
		setNeedsProgressMonitor(true);
		setWindowTitle("Create a new project");
	}

	/** */
	/**
	 * @throws IOException
	 * @throws SAXException
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		//System.out.println("NewAppWizard.performFinish");
		this.project = new MsProject();
		this.project.setProjectName(page.txtProject.getText());
		this.project.setCmpy_short(page.companyText.getText());
		this.project.setAuthor(page.authorText.getText());
		this.project.setVersion(page.versionText.getText());

		//
		this.project.setDirectory(page.pathText.getText());
		// this.project.setDirectory(workspace.getRoot().getLocation().toOSString()+"\\"+page.txtProject.getText());
		this.project.setWorkplace(workspace.getRoot().getLocation()
				.toOSString());
		this.project.setGenPackageName(page.genPackageNameText.getText());
		this.createProject();

		net.ms.designer.ui.WriteToProjectXML write = new net.ms.designer.ui.WriteToProjectXML(
				null, this.project);
		MsEditorInput editorInput = new MsEditorInput();
		editorInput.setName("PackageEditor");
		IEditorInput ieditorInput = editorInput;
		String editorID = "net.ms.designer.editors.packages.ui.PackageEditor";
		String viewID = "net.ms.designer.ui.view.MsTreeView";
		String perspectiveID = "net.ms.designer.ui.wizard.CPerspective";
		workbenchpage = UIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editor = workbenchpage.findEditor(editorInput);
		if (editor != null) {
			workbenchpage.bringToTop(editor);
		} else {
			try {

				// String xml =
				// workspace.getRoot().getProject(page.txtProject.getText()).getFolder("configure").getLocation().toOSString()+"\\"+"project.xml";
				String xml = this.project.getDirectory()
						+ "\\.configure\\project.xml";
				if (page.cbConn.getSelectionIndex() > -1) {
					net.ms.designer.ui.preference.WriteToProjectXML writeDB = new net.ms.designer.ui.preference.WriteToProjectXML(
							"DataSource", null, page.txtProject.getText());
					Iterator it = page.dbList.iterator();
					DBTool tool = new DBTool();
					while (it.hasNext()) {
						tool = (DBTool) it.next();
						if (tool.getConName()
								.toLowerCase()
								.equals(page.cbConn.getItem(
										page.cbConn.getSelectionIndex())
										.toLowerCase()))
							break;
					}

					this.project.setDbtool(tool);

					writeDB.setConName(tool.getConName());
					writeDB.setDbName(tool.getDbName());
					writeDB.setDbType(tool.getDbType());
					writeDB.setPassword(tool.getPassword());
					writeDB.setPort(tool.getPort());
					writeDB.setServer(tool.getServer());
					writeDB.setUsername(tool.getUsername());

					try {
						//writeDB.accessXMLFile(xml);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					write.accessXMLFile(xml);
				}

				else {
					write.writeProjectElement(xml);
					write.accessXMLFile(xml);
				}

				Project newProject = new Project();
				Company com = new Company();
				com.setCompanyCode(this.project.getCmpy_short());
				com.setCompanyName(this.project.getCompany());
				newProject.setCompany(com);
				newProject.setProjectCode(this.project.getProjectName());
				MsdesignerPlugin.projectBuilder.saveProject(newProject);
				((MsEditorInput) ieditorInput).setProject(project);
				editor = workbenchpage.openEditor(ieditorInput,
						"net.ms.designer.editors.packages.ui.PackageEditor");
				MsElement element = ((net.ms.designer.editors.packages.ui.PackageEditor) editor)
						.getCurrentElement();
				if (element == null) {
					element = new MsElement();
					element.setNodeName(project.getProjectName());
					element.setNodeType("package");
					element.setContainer(((net.ms.designer.editors.packages.ui.PackageEditor) editor)
							.getViewer().getContents().getModel());
					((net.ms.designer.editors.packages.ui.PackageEditor) editor)
							.setCurrentElement(element);
				}
				workbenchpage.bringToTop(editor);
				if (workbenchpage.getPerspective().getId().toLowerCase()
						.equals((perspectiveID).toLowerCase())) {
					MsTreeView view = (MsTreeView) workbenchpage
							.findView(viewID);
					view.setProject(project);
					view.refresh();

				} else {
					boolean temp = MessageDialog.openQuestion(workbenchpage
							.getWorkbenchWindow().getShell(), "是否转换透视图",
							"是否转换透视图");
					if (temp == true) {
						_workbench.showPerspective(perspectiveID,
								workbenchpage.getWorkbenchWindow());
						MsTreeView view = (MsTreeView) workbenchpage
								.findView(viewID);
						view.setProject(project);
						view.refresh();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// createProject();

		return true;
	}

	public boolean performCancel() {
		removeProject();
		super.performCancel();
		return true;
	}

	private void removeProject() {
		if (page.txtProject.getText() == null
				|| page.txtProject.getText().length() < 1) {
			return;
		}
		final IProject ipro = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(page.txtProject.getText());
		if (ipro == null) {
			return;
		}
		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				boolean noProgressMonitor = Platform.getLocation().equals(
						ResourcesPlugin.getWorkspace().getRoot().getLocation());
				if (monitor == null || noProgressMonitor)
					monitor = new NullProgressMonitor();
				monitor.beginTask(
						NewWizardMessages.NewJavaProjectWizardPageTwo_operation_remove, //$NON-NLS-1$
						3);
				try {
					ipro.delete(true, false, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
					project = null;
					// fCanRemoveContent = false;
				}
			}

		};
		try {
			IRunnableContext context = (IRunnableContext) getContainer();
			if (context == null) {
				context = (IRunnableContext) WizardPlugin.getDefault()
						.getActivePage();
			}
			context.run(false, true, op);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException _ex) {
		}

	}

	/** */
	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		page = new NewAppWizard();

		page.setTitle("创建一个代码生成项目框架");
		page.setDescription("创建一个代码生成项目框架");
		addPage(page);
		javaPage = new NewAppSecondPage(page, this.project);
		addPage(javaPage);
		javaPage.setWizard(this);
		//System.out.println("addPages");

	}

	private void createProject() {

		workspace = ResourcesPlugin.getWorkspace(); // ！！！

		// //System.out.println("workspace:"+workspace.getRoot().getLocation().toOSString());
		IWorkspaceRoot root = workspace.getRoot();
		// String path = workspace.getRoot().getLocation().toOSString();
		// StringBuffer sb = new StringBuffer(path);

		IProject newProjectHandle = root.getProject(page.txtProject.getText());

		// sb.append("\\");
		// //System.out.println(sb.toString());
		// IPath targetPath = new
		// Path(workspace.getRoot().getLocation().toOSString()
		// +newProjectHandle.getName());
		// IPath targetPath = new Path(workspace + newProjectHandle.getName());
		// IPath targetPath = new Path(newProjectHandle.getName());
		// sb.append(targetPath.toString());
		// ------------------lichunxia
		// final IProjectDescription description =
		// workspace.newProjectDescription(newProjectHandle.getName());
		// if(page.initButton.getSelection() == false)
		// {
		// // IPath newPath= new Path(page.pathText.getText());
		// // description.setLocation(newPath);
		// }
		// else
		// {
		// description.setLocation(null);
		// }
		try {
			// -------------new
			// newProjectHandle.create(description, null);
			// newProjectHandle.open(null);
			// ___________new
			javaPage.updateProject(true, null);
			javaPage.configureJavaProject(null);
			this.createLibFolder();
			this.createOutputFolder(page.txtProject.getText().trim());
			this.createConfigureFolder();
			// createSrcFolder();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private IFolder createOutputFolder(String folderName) {
		IFolder web = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(page.txtProject.getText().trim()).getFolder("web"); //$NON-NLS-1$
		if (!web.exists()) {
			try {
				web.create(true, true, null);
			} catch (CoreException e) {
				// TODO
				// e.printStackTrace();
			}
		}
		//		IFolder inf = createFolder(web,"WEB-INF"); //$NON-NLS-1$

		return createFolder(web, "WEB-INF"); //$NON-NLS-1$
	}

	private IFolder createFolder(IFolder parentFolder, String folderName) {
		IFolder out = parentFolder.getFolder(folderName);
		if (!out.exists()) {
			try {
				out.create(true, true, null);
			} catch (CoreException e) {
				// TODO
				// e.printStackTrace();
			}
		}

		return out;
	}

	private void createLibFolder() {
		IFolder srcFolder = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(page.txtProject.getText().trim()).getFolder("lib"); //$NON-NLS-1$
		if (!srcFolder.exists())
			try {
				srcFolder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
	}

	// private void createSrcFolder() {
	//		IFolder srcFolder = ResourcesPlugin.getWorkspace().getRoot().getProject(page.txtProject.getText().trim()).getFolder("temp"); //$NON-NLS-1$
	// if (!srcFolder.exists())
	// try {
	// srcFolder.create(true, true, null);
	// } catch (CoreException e) {
	// // e.printStackTrace();
	// }
	// }

	private void createConfigureFolder() {
		IFolder srcFolder = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(page.txtProject.getText().trim())
				.getFolder(".configure"); //$NON-NLS-1$
		if (!srcFolder.exists())
			try {
				srcFolder.create(true, true, null);
			} catch (CoreException e) {
				// e.printStackTrace();
			}
	}

}