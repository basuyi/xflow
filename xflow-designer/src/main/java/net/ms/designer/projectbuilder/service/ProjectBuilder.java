package net.ms.designer.projectbuilder.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.basuyi.xflow.model.Activity;
import org.basuyi.xflow.model.WfAction;
import org.basuyi.xflow.model.WfActivity;
import org.basuyi.xflow.model.WfActivityTran;
import org.basuyi.xflow.model.WfDefination;

import net.ms.designer.projectbuilder.model.ComAttr;
import net.ms.designer.projectbuilder.model.ComDetail;
import net.ms.designer.projectbuilder.model.ComDetailRelation;
import net.ms.designer.projectbuilder.model.ComWorkflow;
import net.ms.designer.projectbuilder.model.Company;
import net.ms.designer.projectbuilder.model.Component;
import net.ms.designer.projectbuilder.model.Package;
import net.ms.designer.projectbuilder.model.Project;

public interface ProjectBuilder {
	
	public void saveCompany(Company company);
	
	public void saveProject(Project newProject);
	
	public void savePackage(Package comPackage);
	
	public void saveComponent(Component component);
	
	public void saveComponentDetail(ComDetail comDetail);
	
	public void saveComAttr(ComAttr comAttr);
	
	public void saveComWorkflow(ComWorkflow comWorkflow);
	
	public void saveWorkflow(WfDefination workflow);
	
	public WfDefination getWorkflow(WfDefination workflow);
	
	public void saveWfActivity(WfActivity wfActivity);
	
	public void saveActivity(Activity Activity);
	
	public List<Project> getProjectByModel(Project project);
	
	public List<Package> getPackageByModel(Package comPackage);
	
	public List<Component> getComponentByModel(Component comPackage);
	
	public List<WfActivity> getWfActivityByModel(WfActivity wfActivity);
	
	public void saveWfActivityTran(WfActivityTran wfActivityTran);
	
	public Set<WfAction> getActivityActions(Activity activity);
	
	public List<ComDetail> getCompDetailByModel(ComDetail comDetail) ;
	
	public void saveComDetailRelation(ComDetailRelation comDetailRelation);
	
	public void saveEntity(Object entity);

	public List getEntityByModel(Object model);

	public Object getEntityById(Class cls, Serializable id);
	
	public void removeEntity(Object entity);
}
