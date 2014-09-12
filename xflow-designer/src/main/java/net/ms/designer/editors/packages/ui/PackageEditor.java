package net.ms.designer.editors.packages.ui;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import msdesigner.MsdesignerPlugin;
import net.ms.designer.core.MsContext;
import net.ms.designer.core.MsEditorInput;
import net.ms.designer.core.MsElement;
import net.ms.designer.core.MsProject;
import net.ms.designer.core.ContainerFactory;
import net.ms.designer.core.IOStreams;
import net.ms.designer.core.PaletteRootFactory;
import net.ms.designer.editors.component.models.BizComponent;
import net.ms.designer.editors.component.models.CompDiagram;
import net.ms.designer.editors.component.models.Component;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.DateField;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.EnumField;
import net.ms.designer.editors.componentdetail.models.FloatField;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.LookupField;
import net.ms.designer.editors.componentdetail.models.StringField;
import net.ms.designer.editors.componentdetail.xmlpalse.WriteToComponentXML;
import net.ms.designer.editors.enumcomponentdetail.tools.WriteToEnumXML;
import net.ms.designer.editors.packages.actions.PropertyAction;
import net.ms.designer.editors.packages.dnd.DiagramTemplateTransferDropTargetListener;
import net.ms.designer.editors.packages.editparts.PartFactory;
import net.ms.designer.editors.packages.editparts.TreePartFactory;
import net.ms.designer.editors.packages.models.Package;
import net.ms.designer.editors.packages.models.PackageDiagram;
import net.ms.designer.editors.packages.tools.MyContextMenuProvider;
import net.ms.designer.editors.packages.tools.PaletteFactory;
import net.ms.designer.editors.packages.ui.WriteToProjectXML;
import net.ms.designer.editors.workflow.Constants;
import net.ms.designer.editors.workflow.models.ApplicationActivity;
import net.ms.designer.editors.workflow.models.EndNode;
import net.ms.designer.editors.workflow.models.ParameterEntire;
import net.ms.designer.editors.workflow.models.ParameterPartial;
import net.ms.designer.editors.workflow.models.RouteOnlyActivity;
import net.ms.designer.editors.workflow.models.SubFlowActivity;
import net.ms.designer.editors.workflow.models.SystemAppActivity;
import net.ms.designer.editors.workflow.models.UserAppActivity;
import net.ms.designer.editors.workflow.models.Wire;
import net.ms.designer.editors.workflow.models.WorkflowBaseActivity;
import net.ms.designer.editors.workflow.models.WorkflowDiagram;
import net.ms.designer.editors.workflow.models.WorkflowSubPart;
import net.ms.designer.editors.workflow.xmlparse.WriteToWorkflowXML;
import net.ms.designer.projectbuilder.model.ComAttr;
import net.ms.designer.projectbuilder.model.ComDetail;
import net.ms.designer.projectbuilder.model.ComDetailPosition;
import net.ms.designer.projectbuilder.model.ComDetailRelation;
import net.ms.designer.projectbuilder.model.ComPosition;
import net.ms.designer.projectbuilder.model.ComWorkflow;
import net.ms.designer.projectbuilder.model.PackagePosition;
import net.ms.designer.projectbuilder.model.Project;
import net.ms.designer.projectbuilder.model.WfActivityPosition;
import net.ms.designer.projectbuilder.model.WorkflowPosition;
import net.ms.designer.projectbuilder.service.ProjectBuilder;
import net.ms.designer.ui.view.MsTreeView;
import net.ms.designer.ui.view.ITreeEntry;

import org.basuyi.xflow.model.Activity;
import org.basuyi.xflow.model.Parameter;
import org.basuyi.xflow.model.SubflowActivity;
import org.basuyi.xflow.model.UserActivity;
import org.basuyi.xflow.model.WfAction;
import org.basuyi.xflow.model.WfActivity;
import org.basuyi.xflow.model.WfActivityTran;
import org.basuyi.xflow.model.WfCondition;
import org.basuyi.xflow.model.WfDefination;
import org.basuyi.xflow.model.WfParameter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.CDATASection;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author mashuai
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PackageEditor extends GraphicalEditorWithPalette implements
		ISelectionChangedListener {
	private MsProject project;
	private PackageDiagram diagram;
	private IOStreams stream;
	private MsContext context = new MsContext();
	private Object container;
	private String nodeType;
	private String nodeName;
	private MsElement currentElement;

	private PaletteRoot paletteRoot;

	public PackageDiagram getDiagram() {
		return this.diagram;
	}

	public PackageEditor() {
		setEditDomain(new DefaultEditDomain(this));
		stream = new IOStreams();

	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer()
				.setRootEditPart(new ScalableFreeformRootEditPart());
		getGraphicalViewer().setEditPartFactory(new PartFactory());
		ContextMenuProvider provider = new MyContextMenuProvider(
				getGraphicalViewer(), getActionRegistry());
		getGraphicalViewer().setContextMenu(provider);
		getGraphicalViewer().addSelectionChangedListener(this);
		getSite().registerContextMenu(provider, getGraphicalViewer());
		updateActions(getSelectionActions());
	}

	protected void initializeGraphicalViewer() {
		context.setProject(project);
		this.setNodeName(project.getName());
		this.setNodeType(project.getType());
		if (context.getElements().size() == 0) {
			diagram = new PackageDiagram();
			getGraphicalViewer().setContents(this.diagram);
		} else {
			PaletteRootFactory factory = new PaletteRootFactory();
			PaletteRoot root = factory.getPaletteFactory(getInput()
					.getEditorType());
			this.getEditDomain()
					.getPaletteViewer()
					.getEditPartRegistry()
					.put(root.getDefaultEntry(),
							new org.eclipse.gef.internal.ui.palette.editparts.ToolEntryEditPart(
									root.getDefaultEntry()));
			this.getEditDomain().setPaletteRoot(root);

			MsElement element = context.getElement(getInput().getEditorType()
					+ "_" + getInput().getEditorName());
			Object container = element.getContainer();
			getGraphicalViewer().setContents(container);

			this.setCurrentElement(element);
		}
		getGraphicalViewer().addDropTargetListener(
				new DiagramTemplateTransferDropTargetListener(
						getGraphicalViewer()));
	}

	public void doSave(IProgressMonitor monitor) {
		context.preOperation();

		MsProject msProject;
		msProject = context.getProject();
		String path = msProject.getConfigPath();

		this.saveContext(context, path);

		String file = null;

		if (context.getCount() != 0) {
			List packs = context.getPackages();
			for (int i = 0; i < packs.size(); i++) {
				MsElement elementPk = (MsElement) packs.get(i);
				this.savePackages(elementPk, msProject, path);

				List comps = elementPk.getChildren();
				for (int j = 0; j < comps.size(); j++) {
					MsElement elementCp = (MsElement) comps.get(j);
					this.saveComponents(elementCp, msProject, path);
					List details = elementCp.getChildren();
					for (int k = 0; k < details.size(); k++) {
						MsElement elementCd = (MsElement) details.get(k);
						this.saveDetails(elementCd, msProject, path);
						List flows = elementCd.getChildren();
						for (int l = 0; l < flows.size(); l++) {
							MsElement elementWf = (MsElement) flows.get(l);
							this.saveWorkflow(elementWf, msProject, path);
						}
					}
				}
			}
		}
		// refresh the tree
		((MsTreeView) UIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.findView("net.ms.designer.ui.view.MsTreeView")).refresh();
		// refresh the project directory
		try {
			ResourcesPlugin.getWorkspace().getRoot()
					.getProject(project.getProjectName())
					.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getCommandStack().markSaveLocation();
	}

	public void commandStackChanged(EventObject event) {
		if (isDirty()) {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} else {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
		super.commandStackChanged(event);
	}

	public void doSaveAs() {
	}

	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	public boolean isSaveAsAllowed() {
		return true;
	}

	protected void setInput(IEditorInput input) {
		superSetInput(input);
		project = ((net.ms.designer.core.MsEditorInput) getEditorInput())
				.getProject();

		if (((MsEditorInput) getEditorInput()).getContext() != null)
			context = ((MsEditorInput) getEditorInput()).getContext();
	}

	protected void superSetInput(IEditorInput input) {
		super.setInput(input);
	}

	public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class)
			return new OutlinePage();
		return super.getAdapter(type);
	}

	// net.ms.designer.editors.component.tools.PaletteFactory paletteFactory=new
	// net.ms.designer.editors.component.tools.PaletteFactory();
	protected PaletteRoot getPaletteRoot() {
		if (this.paletteRoot == null) {
			this.paletteRoot = PaletteFactory.createPalette();
		}
		return this.paletteRoot;
	}

	protected void initializePaletteViewer() {
		super.initializePaletteViewer();
		getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(getPaletteViewer()));
	}

	public void selectionChanged(SelectionChangedEvent event) {
		updateActions(getSelectionActions());
	}

	public void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		IAction action;

		action = new PropertyAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	public MsProject getProject() {
		return this.project;
	}

	public void setProject(MsProject project) {
		this.project = project;
	}

	class OutlinePage extends ContentOutlinePage {
		// private PageBook pageBook;

		private Control outline;

		public OutlinePage() {
			super(new TreeViewer());
		}

		public void init(IPageSite pageSite) {
			super.init(pageSite);
			ActionRegistry registry = getActionRegistry();
			IActionBars bars = pageSite.getActionBars();
			String id = IWorkbenchActionConstants.UNDO;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = IWorkbenchActionConstants.REDO;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = IWorkbenchActionConstants.DELETE;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			bars.updateActionBars();
		}

		public void createControl(Composite parent) {
			// pageBook = new PageBook(parent, SWT.NONE);
			// outline = getViewer().createControl(pageBook);
			// pageBook.showPage(outline);
			outline = getViewer().createControl(parent);
			getSelectionSynchronizer().addViewer(getViewer());
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new TreePartFactory());
			// getViewer().setKeyHandler(getCommonKeyHandler());
			getViewer().setContents(getDiagram());
		}

		public Control getControl() {
			// return pageBook;
			return outline;
		}

		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			super.dispose();
		}
	}

	public GraphicalViewer getViewer() {
		return this.getGraphicalViewer();
	}

	public MsContext getContext() {
		return this.context;
	}

	public void setContext(MsContext context) {
		this.context = context;
	}

	// current cotainer
	public Object getContainer() {
		return this.container;
	}

	public void setContainer(Object container) {
		this.container = container;
	}

	// current msElement
	public MsElement getCurrentElement() {
		return this.currentElement;
	}

	public void setCurrentElement(MsElement element) {
		this.currentElement = element;
	}

	public String getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	// save the packages
	public void savePackages(MsElement element, MsProject msProject, String path) {
		Object tmp = element.getContainer();

		if (tmp instanceof net.ms.designer.editors.packages.models.PackageDiagram) {
			if (MsdesignerPlugin.PROJECT_CONFIG_POLICY.equals("DB")) {
				savePackagesToDB(msProject, tmp);
			} else {
				savePackagesToXML(msProject, path, tmp);
			}
		}
	}

	private void savePackagesToDB(MsProject msProject, Object tmp) {
		PackageDiagram diagram = (PackageDiagram) tmp;
		Project _project = new Project();
		_project.setProjectCode(msProject.getProjectName());
		List<Project> projects = MsdesignerPlugin.projectBuilder
				.getProjectByModel(_project);
		_project = projects.get(0);
		List nodes = diagram.getNodes();
		for (int i = 0; i < nodes.size(); i++) {
			Package node = (Package) nodes.get(i);
			net.ms.designer.projectbuilder.model.Package comPackage = new net.ms.designer.projectbuilder.model.Package();
			comPackage.setPackageName(node.getName());
			comPackage.setPackageCode(node.getName());
			comPackage.setProject(_project);
			PackagePosition position = new PackagePosition();
			position.setPositionX(new Long(node.getLocation().x));
			position.setPositionY(new Long(node.getLocation().y));
			comPackage.setPackagePosition(position);
			MsdesignerPlugin.projectBuilder.savePackage(comPackage);

		}
	}

	private void savePackagesToXML(MsProject msProject, String path, Object tmp) {
		try {
			WriteToProjectXML writer = new WriteToProjectXML(msProject, tmp);
			StringBuffer sb1 = new StringBuffer(path);
			// get full file path
			sb1.append("project.xml");
			writer.accessXMLFile(sb1.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// save components
	public void saveComponents(MsElement element, MsProject msProject,
			String path) {
		Object tmp = element.getContainer();

		if (tmp instanceof net.ms.designer.editors.component.models.CompDiagram) {
			if (MsdesignerPlugin.PROJECT_CONFIG_POLICY.equals("DB")) {
				saveComponentsToDB(element);
			} else {
				saveComponentsToXML(element, msProject, path);
			}
		}
	}

	private void saveComponentsToDB(MsElement element) {
		Object tmp = element.getContainer();
		CompDiagram diagram = (CompDiagram) tmp;
		List nodes = diagram.getNodes();

		net.ms.designer.projectbuilder.model.Package comPackage = new net.ms.designer.projectbuilder.model.Package();
		comPackage.setPackageCode(element.getNodeName());
		List<net.ms.designer.projectbuilder.model.Package> packages = MsdesignerPlugin.projectBuilder
				.getPackageByModel(comPackage);
		comPackage = packages.get(0);
		for (int k = 0; k < nodes.size(); k++) {
			if (nodes.get(k) instanceof BizComponent) {
				net.ms.designer.projectbuilder.model.Component com = new net.ms.designer.projectbuilder.model.Component();
				Component node = (Component) nodes.get(k);
				// name�����
				com.setComponentCode(node.getName());
				com.setComponentName(node.getName());

				// desc��������
				com.setComponentDesc(node.getDesc());

				com.setComPackage(comPackage);
				ComPosition position = new ComPosition();
				position.setPositionX(new Long(node.getLocation().x));
				position.setPositionY(new Long(node.getLocation().y));
				com.setComPosition(position);
				MsdesignerPlugin.projectBuilder.saveComponent(com);
			}
		}
	}

	private void saveComponentsToXML(MsElement element, MsProject msProject,
			String path) {
		net.ms.designer.editors.component.tools.WriteToProjectXML write = new net.ms.designer.editors.component.tools.WriteToProjectXML(
				msProject, element);
		// get the xml file name
		StringBuffer sb1 = new StringBuffer(path);
		// get full file path
		sb1.append("project.xml");
		try {
			write.accessXMLFile(sb1.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// save the details
	public void saveDetails(MsElement element, MsProject msProject, String path) {
		Object tmp = element.getContainer();

		if (tmp instanceof net.ms.designer.editors.componentdetail.models.Container) {
			if (MsdesignerPlugin.PROJECT_CONFIG_POLICY.equals("DB")) {
				saveComDetailToDB(element);
			} else {
				saveComDetailToXML(element, msProject, path);
			}
		}
		if (tmp instanceof net.ms.designer.editors.enumcomponentdetail.model.Container) {
			WriteToEnumXML write = new WriteToEnumXML(element, msProject);
			StringBuffer sb2 = new StringBuffer(path);
			sb2.append(element.getId());
			sb2.append(".xml");
			try {
				// save component information to xml file
				write.writeXMLFile(sb2.toString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void saveComDetailToDB(MsElement element) {
		Container container = (Container) element.getContainer();

		net.ms.designer.projectbuilder.model.Component com = new net.ms.designer.projectbuilder.model.Component();
		com.setComponentCode(container.getMainTable().getName());
		List<net.ms.designer.projectbuilder.model.Component> coms = MsdesignerPlugin.projectBuilder
				.getComponentByModel(com);
		com = coms.get(0);

		if (container.getMainTable().getFlowAssociated() == 1) {
			// workflow���е����ݣ���û�����̾�û��workflow�е�����
			FlowField flow1 = (FlowField) container.getFlowField();

			ComWorkflow comWorkflow = new ComWorkflow();
			comWorkflow.setWorkflowId(flow1.getName());
			comWorkflow.setComponent(com);
			WorkflowPosition position = new WorkflowPosition();
			position.setPositionX(new Long(flow1.getLocation().x));
			position.setPositionY(new Long(flow1.getLocation().y));
			comWorkflow.setWorkflowPosition(position);
			MsdesignerPlugin.projectBuilder.saveComWorkflow(comWorkflow);
		}
		List children = container.getChildren();
		if (children != null) {
			for (int i = 0; i < children.size(); i++) {
				Element child = (Element) children.get(i);
				if (child instanceof FlowField) {
					continue;
				}
				ComDetail comDetail = new ComDetail();
				comDetail.setComDetailCode(child.getName());
				if (child instanceof ComponentTable) {
					comDetail.setComDetailType(0L);
				} else {
					comDetail.setComDetailType(1L);
				}
				comDetail.setComponent(com);
				ComDetailPosition position = new ComDetailPosition();
				position.setPositionX(new Long(child.getLocation().x));
				position.setPositionY(new Long(child.getLocation().y));
				comDetail.setComDetailPosition(position);
				MsdesignerPlugin.projectBuilder.saveComponentDetail(comDetail);

				// fields���е����ݣ���¼������а������ֶ���
				Iterator itField = ((ChildTable) child).getChildren()
						.iterator();
				if (itField != null) {
					while (itField.hasNext()) {
						ComAttr comAttr = new ComAttr();
						comAttr.setComDetail(comDetail);
						// ����5�������ֶβ�ͬ������ֱ��г������屣��ʱҪ�Լ��޸ģ����飺ѭ���кŷ�Χ��133-352����forѭ����ѭ��������componentTable�е�Ԫ�ظ�����ѭ���ڲ���swith-case
						CommonField commonField = (CommonField) itField.next();
						comAttr.setComAttrCode(commonField.getName());

						if (commonField.getField_Type().equals("Autonum")) {
							comAttr.setComAttrType(0L);
							comAttr.setIsKey(0L);
						}
						if (commonField.getField_Type().equals("String")) {
							comAttr.setComAttrType(1L);
							comAttr.setComAttrLength(new Long(
									((StringField) commonField).getStrLength()));
						}

						if (commonField.getField_Type().equals("Integer")) {
							comAttr.setComAttrType(2L);
						}
						if (commonField.getField_Type().equals("Date")) {
							comAttr.setComAttrType(3L);
						}
						if (commonField.getField_Type().equals("Float")) {
							comAttr.setComAttrType(4L);
						}
						if (commonField.getField_Type().equals("Enum")) {
							comAttr.setComAttrType(5L);
						}

						// Element f1_searchable =
						// doc.createElement("searchable");
						// Text f1_searchable_model = doc.createTextNode(""
						// + commonField.getCanBeQuery());
						// f1_searchable.appendChild(f1_searchable_model);
						// field1.appendChild(f1_searchable);
						//
						// Element f1_isrequired =
						// doc.createElement("isrequired");
						// Text f1_isrequired_model = doc.createTextNode(""
						// + commonField.getMustBeFilled());
						// f1_isrequired.appendChild(f1_isrequired_model);
						// field1.appendChild(f1_isrequired);
						//
						// Element f1_listable = doc.createElement("listable");
						// Text f1_listable_model = doc.createTextNode(""
						// + commonField.getListable());
						// f1_listable.appendChild(f1_listable_model);
						// field1.appendChild(f1_listable);
						//
						// Element f1_Autonum =
						// doc.createElement("isuserfield");
						// Text f1_Autonum_model = doc.createTextNode(""
						// + commonField.getIsuserfield());
						// f1_Autonum.appendChild(f1_Autonum_model);
						// field1.appendChild(f1_Autonum);
						//
						// if (commonField.getField_Type().equals("Date")) {
						// if (!(((DateField)
						// commonField).getDateFormatPattern() ==
						// null)) {
						// Element f1_DateTimeDisplay = doc
						// .createElement("DateTimeDisplay");
						// Text f1_DateTimeDisplay_model = doc.createTextNode(""
						// + ((DateField) commonField)
						// .getDateFormatPattern());
						// f1_DateTimeDisplay
						// .appendChild(f1_DateTimeDisplay_model);
						// field1.appendChild(f1_DateTimeDisplay);
						// }
						//
						// }
						//
						// if (commonField.getField_Type().equals("Float")) {
						// if (!(((FloatField)
						// commonField).getFractionDigitals()
						// .equals(""))) {
						// Element f1_maxFractionDigits = doc
						// .createElement("maxFractionDigits");
						// Text f1_maxFractionDigits_model =
						// doc.createTextNode(""
						// + ((FloatField) commonField)
						// .getFractionDigitals());
						// f1_maxFractionDigits
						// .appendChild(f1_maxFractionDigits_model);
						// field1.appendChild(f1_maxFractionDigits);
						// }
						// }
						MsdesignerPlugin.projectBuilder.saveComAttr(comAttr);
					}
				}
			}
		}
		if (children != null) {
			for (int i = 0; i < children.size(); i++) {
				Element child = (Element) children.get(i);
				if (child instanceof FlowField) {
					continue;
				}
				Vector inputs = child.getInputs();
				if (inputs != null) {
					for (net.ms.designer.editors.componentdetail.models.Wire input : (Vector<net.ms.designer.editors.componentdetail.models.Wire>) inputs) {
						Element from = input.getSource();
						if (from instanceof FlowField) {
							continue;
						}
						ComDetail fromCd = new ComDetail();
						fromCd.setComDetailCode(from.getName());
						List<ComDetail> fromComDetials = MsdesignerPlugin.projectBuilder.getCompDetailByModel(fromCd);
						fromCd = fromComDetials.get(0);
						Element to = input.getTarget();
						ComDetail toCd = new ComDetail();
						toCd.setComDetailCode(to.getName());
						List<ComDetail> toComDetials = MsdesignerPlugin.projectBuilder.getCompDetailByModel(toCd);
						toCd = toComDetials.get(0);
						
						ComDetailRelation cdr = new ComDetailRelation();
						cdr.setFromComDetailId(fromCd.getComDetailId());
						cdr.setToComDetailId(toCd.getComDetailId());
						cdr.setRelationType(0L);
						MsdesignerPlugin.projectBuilder.saveComDetailRelation(cdr);
					}
				}
			}
		}
	}

	private void saveComDetailToXML(MsElement element, MsProject msProject,
			String path) {
		WriteToComponentXML write = new WriteToComponentXML(element, msProject);
		StringBuffer sb = new StringBuffer(path);
		sb.append(element.getId());
		sb.append(".xml");
		try {
			write.writeXMLFile(sb.toString());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveWorkflow(MsElement element, MsProject msProject, String path) {
		if (MsdesignerPlugin.PROJECT_CONFIG_POLICY.equals("DB")) {
			saveWorkflowToDB(element, msProject);
		} else {
			saveWorkflwoToXML(element, msProject, path);
		}
		List subflows = element.getChildren();
		if (subflows != null) {
			for (MsElement subflow : (List<MsElement>) subflows) {
				WorkflowDiagram diagram = (WorkflowDiagram) subflow.getContainer();
				String workflowId = diagram.getName();
				WfDefination workflow = new WfDefination();
				workflow.setWorkflowId(workflowId);
				WfDefination _workflow = MsdesignerPlugin.projectBuilder.getWorkflow(workflow);
				if (_workflow != null) {
					continue;
				}
				saveWorkflow(subflow, msProject, path);
			}
		}
	}

	private void saveWorkflowToDB(MsElement element, MsProject project) {
		WorkflowDiagram diagram = (WorkflowDiagram) element.getContainer();
		String workflowId = diagram.getName();
		WfDefination workflow = new WfDefination();
		workflow.setWorkflowId(workflowId);
		workflow.setWorkflowName(workflowId);
		Set<Parameter> wfParams = new HashSet<Parameter>();
		if (diagram.getParaList() != null && diagram.getParaList().size() > 0) {
			// wfparams��
			Iterator it = diagram.getParaList().iterator();
			while (it.hasNext()) {
				ParameterEntire parameter = (ParameterEntire) it.next();
				Parameter param = new Parameter();
				// name��
				if (parameter.getParaName() != null) {
					param.setParamCode(parameter.getParaName());
				}

				// type��
				if (parameter.getParaType() != null) {
					param.setParamType(transParamType(parameter.getParaType()));
				}

				if (parameter.getIsInput()) {
					param.setInOutType(0L);
				}
				if (parameter.getIsOutput()) {
					param.setInOutType(1L);
				}
				if (parameter.getIsInput() && parameter.getIsOutput()) {
					param.setInOutType(2L);
				}
				wfParams.add(param);
			}
			workflow.setWfParameters(wfParams);
		}

		// ����WfDefination
		MsdesignerPlugin.projectBuilder.saveWorkflow(workflow);

		Set<Activity> wfAcitivities = new HashSet<Activity>();
		if (diagram.getChildren() != null && diagram.getChildren().size() > 0) {
			// wfactivity��
			Iterator itActivity = diagram.getChildren().iterator();
			while (itActivity.hasNext()) {
				WorkflowBaseActivity node = (WorkflowBaseActivity) itActivity
						.next();
				Activity activity = null;
				if (node.getActivity_type_constant().equals(
						Constants.WF_ACTIVITY_TYPE_USER_APP)) {
					activity = new UserActivity();
				} else if (node.getActivity_type_constant().equals(
						Constants.WF_ACTIVITY_TYPE_SUBFLOW)) {
					activity = new SubflowActivity();
				} else {
					activity = new Activity();
				}

				// nodeid
				activity.setActivityId(node.getName());

				// name
				activity.setActivityName(node.getName());

				// desc
				if (node.getActivity_desc() != null
						&& node.getActivity_desc().length() > 0) {
					activity.setActivityDesc(node.getActivity_desc());
				}

				// type
				activity.setActivityType(transActivityType(node
						.getActivity_type_constant()));

				// params
				if (node.getActivity_param() != null
						&& node.getActivity_param().size() > 0) {
					Set params = new HashSet();
					Iterator activityParams = node.getActivity_param()
							.iterator();
					while (activityParams.hasNext()) {
						ParameterEntire paraPartial = (ParameterEntire) activityParams
								.next();
						Parameter param = new Parameter();
						param.setParamCode(paraPartial.getParaName());
						param.setParamDesc(paraPartial.getDescription());
						param.setParamType(transParamType(paraPartial
								.getParaType()));
						if (paraPartial.getIsInput()) {
							param.setInOutType(0L);
						}
						if (paraPartial.getIsOutput()) {
							param.setInOutType(1L);
						}
						if (paraPartial.getIsInput()
								&& paraPartial.getIsOutput()) {
							param.setInOutType(2L);
						}
						params.add(param);
					}
					activity.setWfActivityParams(params);
				}

				// app
				Set<WfAction> activityActions = new HashSet<WfAction>();
				if (node.getActivity_actions() != null) {
					for (ApplicationActivity app : (List<ApplicationActivity>) node
							.getActivity_actions()) {
						WfAction action = new WfAction();
						action.setActionId(app.getApplicationName());
						action.setActionName(app.getApplicationName());
						action.setActionValue(app.getApplicationPath());
						action.setActionType(new Long(app.getApplicationType()));

						Set<Parameter> actionParams = new HashSet<Parameter>();
						if (app.getWfApplicationParam() != null) {
							for (ParameterEntire entry : (List<ParameterEntire>) app
									.getWfApplicationParam()) {
								Parameter param = new Parameter();
								param.setParamCode(entry.getParaName());
								param.setParamDesc(entry.getDescription());
								param.setParamName(entry.getParaName());
								param.setParamType(transParamType(entry
										.getParaType()));
								if (entry.getIsInput()) {
									param.setInOutType(0L);
								}
								if (entry.getIsOutput()) {
									param.setInOutType(1L);
								}
								if (entry.getIsInput() && entry.getIsOutput()) {
									param.setInOutType(2L);
								}
								actionParams.add(param);
							}
						}
						action.setActionParams(actionParams);
						activityActions.add(action);
					}
					activity.setActivityActions(activityActions);
				}

				// start node
				if (node.getActivity_type_constant().equals(
						Constants.WF_ACTIVITY_TYPE_START)) {

				}

				// �û�Ӧ��
				else if (node.getActivity_type_constant().equals(
						Constants.WF_ACTIVITY_TYPE_USER_APP)) {

					// executer,��
					((UserActivity) activity)
							.setPolicyType(((UserAppActivity) node)
									.getPolicyType());
					((UserActivity) activity)
							.setEntryType(((UserAppActivity) node)
									.getEntryType());
					((UserActivity) activity)
							.setEntryValue(((UserAppActivity) node)
									.getEntryValue());
					if (((UserAppActivity) node).getPolicyType() == 0) {
						((UserActivity) activity).setPostId(new Long(
								((UserAppActivity) node).getPolicyValue()));
					} else if (((UserAppActivity) node).getPolicyType() == 1) {
						((UserActivity) activity).setUserId(new Long(
								((UserAppActivity) node).getPolicyValue()));
					}
					((UserActivity) activity).setOrgId(null);

				}

				// �����ڵ�
				else if (node.getActivity_type_constant().equals(
						Constants.WF_ACTIVITY_TYPE_SUBFLOW)) {

					// subflow
					if (((SubFlowActivity) node).getSubflowName() != null
							&& ((SubFlowActivity) node).getSubflowName()
									.length() > 0) {
						((SubflowActivity) activity)
								.setSubflowId(((SubFlowActivity) node)
										.getSubflowName());
					}
				}
				MsdesignerPlugin.projectBuilder.saveActivity(activity);
				WfActivity wfActivity = new WfActivity();
				wfActivity.setWorkflowId(workflow.getWorkflowId());
				wfActivity.setActivityId(activity.getActivityId());

				WfActivityPosition position = new WfActivityPosition();
				position.setPositionX(new Long(node.getLocation().x));
				position.setPositionY(new Long(node.getLocation().y));
				wfActivity.setWfActivityPosition(position);
				MsdesignerPlugin.projectBuilder.saveWfActivity(wfActivity);

			}
		}
		if (diagram.getChildren() != null && diagram.getChildren().size() > 0) {
			// wfactivity��
			Iterator itActivity = diagram.getChildren().iterator();
			while (itActivity.hasNext()) {
				WorkflowBaseActivity node = (WorkflowBaseActivity) itActivity
						.next();
				// Transitions
				if (node.getInputs() != null && node.getInputs().size() > 0) {

					Iterator itTransitions = node.getInputs().iterator();
					while (itTransitions.hasNext()) {
						WfActivityTran tran = new WfActivityTran();

						Wire transitionsWire = (Wire) itTransitions.next();
						WorkflowSubPart source = transitionsWire.getSource();
						WorkflowSubPart target = transitionsWire.getTarget();

						// fromactivity
						WfActivity _wfActivity = new WfActivity();
						_wfActivity.setActivityId(source.getName());
						_wfActivity.setWorkflowId(workflowId);
						List<WfActivity> wfActivities = MsdesignerPlugin.projectBuilder
								.getWfActivityByModel(_wfActivity);
						WfActivity fromWfActivity = wfActivities.get(0);
						tran.setFromWfActivityId(fromWfActivity
								.getWfActivityId());
						_wfActivity.setActivityId(target.getName());
						wfActivities = MsdesignerPlugin.projectBuilder
								.getWfActivityByModel(_wfActivity);
						WfActivity toWfActivity = wfActivities.get(0);
						tran.setToWfActivityId(toWfActivity.getWfActivityId());
						Set wfTranConditions = new HashSet();
						// condition
						if (transitionsWire.getTransitionCondition() != null
								&& transitionsWire.getTransitionCondition()
										.length() > 0) {
							WfCondition condition = new WfCondition();
							condition.setConditionId(transitionsWire.getName());
							condition.setConditionName(transitionsWire
									.getName());
							condition.setConditionValue(transitionsWire
									.getTransitionCondition());
							condition.setConditionDesc(transitionsWire
									.getDescription());
							condition.setConditionType(transitionsWire
									.getConditionType());
							wfTranConditions.add(condition);
						}
						tran.setWfTranConditions(wfTranConditions);
						MsdesignerPlugin.projectBuilder.saveWfActivityTran(tran);
					}
				}
			}
		}
	}

	private Long transParamType(String paraType) {
		// TODO Auto-generated method stub
		return new Long(paraType);
	}

	private Long transActivityType(String activity_type_constant) {
		// TODO Auto-generated method stub
		return new Long(activity_type_constant);
	}

	private void saveWorkflwoToXML(MsElement element, MsProject msProject,
			String path) {
		WriteToWorkflowXML wfWritor = new WriteToWorkflowXML(element, msProject);
		StringBuffer sb2 = new StringBuffer(path);
		sb2.append(element.getId());
		sb2.append(".xml");
		try {
			// save component information to xml file
			wfWritor.writeWorkflowXMLFile(sb2.toString());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// save the context
	public void saveContext(MsContext context, String path) {
		String file = path + "context";
		try {
			stream.outputs(context, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MsEditorInput getInput() {
		return (MsEditorInput) getEditorInput();
	}
}
