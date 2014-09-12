package net.ms.designer.projectbuilder.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.basuyi.xflow.model.ActionParameter;
import org.basuyi.xflow.model.Activity;
import org.basuyi.xflow.model.ActivityAction;
import org.basuyi.xflow.model.Parameter;
import org.basuyi.xflow.model.WfAction;
import org.basuyi.xflow.model.WfActivity;
import org.basuyi.xflow.model.WfActivityParam;
import org.basuyi.xflow.model.WfActivityTran;
import org.basuyi.xflow.model.WfCondition;
import org.basuyi.xflow.model.WfDefination;
import org.basuyi.xflow.model.WfTransCondition;

import net.ms.designer.projectbuilder.dao.ProjectBuilderDAO;
import net.ms.designer.projectbuilder.model.ComAttr;
import net.ms.designer.projectbuilder.model.ComDetail;
import net.ms.designer.projectbuilder.model.ComDetailPosition;
import net.ms.designer.projectbuilder.model.ComDetailRelation;
import net.ms.designer.projectbuilder.model.ComPosition;
import net.ms.designer.projectbuilder.model.ComWorkflow;
import net.ms.designer.projectbuilder.model.Company;
import net.ms.designer.projectbuilder.model.Component;
import net.ms.designer.projectbuilder.model.Package;
import net.ms.designer.projectbuilder.model.PackagePosition;
import net.ms.designer.projectbuilder.model.Position;
import net.ms.designer.projectbuilder.model.Project;
import net.ms.designer.projectbuilder.model.WfActivityPosition;
import net.ms.designer.projectbuilder.model.WorkflowPosition;

public class DBProjectBuilder implements ProjectBuilder,
		DBProjectBuilderDaoAware {

	private ProjectBuilderDAO dao;

	public void saveProject(Project newProject) {
		List companyList = dao.getEntityByModel(newProject.getCompany());
		if (companyList != null) {
			for (Company company : (List<Company>)companyList) {
				dao.removeEntity(company);
			}
		}
		dao.saveEntity(newProject.getCompany());
		List projectList = dao.getEntityByModel(newProject);
		if (projectList != null) {
			for (Project project : (List<Project>)projectList) {
				dao.removeEntity(project);
			}
		}
		dao.saveEntity(newProject);
	}

	public void savePackage(Package comPackage) {
		List comPackageList = dao.getEntityByModel(comPackage);
		if (comPackageList != null) {
			for (Package pack : (List<Package>)comPackageList) {
				dao.removeEntity(pack);
			}
		}
		dao.saveEntity(comPackage);
		PackagePosition position = comPackage.getPackagePosition();
		if (position != null) {
			position.setPackageId(comPackage.getPackageId());
			List positionList = dao.getEntityByModel(position);
			if (positionList != null) {
				for (Position pos : (List<Position>)positionList) {
					dao.removeEntity(pos);
				}
			}
			dao.saveEntity(position);
		}
	}

	public void saveComponent(Component component) {
		List comList = dao.getEntityByModel(component);
		if (comList != null) {
			for (Component com : (List<Component>)comList) {
				dao.removeEntity(com);
			}
		}
		dao.saveEntity(component);
		ComPosition position = component.getComPosition();
		if (position != null) {
			position.setComponentId(component.getComponentId());
			List positionList = dao.getEntityByModel(position);
			if (positionList != null) {
				for (Position pos : (List<Position>)positionList) {
					dao.removeEntity(pos);
				}
			}
			dao.saveEntity(position);
		}
	}

	public void saveComponentDetail(ComDetail comDetail) {
		List comDetailList = dao.getEntityByModel(comDetail);
		if (comDetailList != null) {
			for (ComDetail cd : (List<ComDetail>)comDetailList) {
				dao.removeEntity(cd);
			}
		}
		dao.saveEntity(comDetail);
		ComDetailPosition position = comDetail.getComDetailPosition();
		if (position != null) {
			position.setComDetailId(comDetail.getComDetailId());
			List positionList = dao.getEntityByModel(position);
			if (positionList != null) {
				for (Position pos : (List<Position>)positionList) {
					dao.removeEntity(pos);
				}
			}
			dao.saveEntity(position);
		}
	}

	public void saveComAttr(ComAttr comAttr) {
		List comAttrList = dao.getEntityByModel(comAttr);
		if (comAttrList != null) {
			for (ComAttr ca : (List<ComAttr>)comAttrList) {
				dao.removeEntity(ca);
			}
		}
		dao.saveEntity(comAttr);
	}

	public void saveComWorkflow(ComWorkflow comWorkflow) {
		List comWorkflowList = dao.getEntityByModel(comWorkflow);
		if (comWorkflowList != null) {
			for (ComWorkflow cw : (List<ComWorkflow>)comWorkflowList) {
				dao.removeEntity(cw);
			}
		}
		dao.saveEntity(comWorkflow);
		WorkflowPosition position = comWorkflow.getWorkflowPosition();
		if (position != null) {
			position.setComWorkflowId(comWorkflow.getComWorkflowId());
			List positionList = dao.getEntityByModel(position);
			if (positionList != null) {
				for (Position pos : (List<Position>)positionList) {
					dao.removeEntity(pos);
				}
			}
			dao.saveEntity(position);
		}
	}

	public WfDefination getWorkflow(WfDefination workflow) {
		WfDefination _workflow = (WfDefination)dao.getEntityById(WfDefination.class, workflow.getWorkflowId());
		return _workflow;
	}
	
	public void saveWorkflow(WfDefination workflow) {
		//ɾ����������
		clearWfDefination(workflow);
		
		Set<Parameter> wfParams = workflow.getWfParameters();
		if (wfParams != null) {
			for (Parameter param : wfParams) {
				dao.saveEntity(param);
			}
		}
		dao.saveEntity(workflow);
	}
	
	private void clearWfDefination(WfDefination workflow) {
		String workflowId = workflow.getWorkflowId();
		WfActivity wfAct = new WfActivity();
		wfAct.setWorkflowId(workflowId);
		//ɾ��workflow-activity��ϵ��
		dao.removeEntity(wfAct);
		List wfActList = dao.getEntityByModel(wfAct);
		if (wfActList != null) {
			for (WfActivity _wfAct : (List<WfActivity>)wfActList) {
				String activityId = _wfAct.getActivityId();
				Long wfActivityId = _wfAct.getWfActivityId();
				
				//ɾ��activity-action��ϵ��
				ActivityAction aa = new ActivityAction();
				aa.setActivityId(activityId);
				dao.removeEntity(aa);
				
				//ɾ��activity trans
				WfActivityTran wfTran = new WfActivityTran();
				wfTran.setFromWfActivityId(wfActivityId);
				//dao.removeEntity(wfTran);
				List wfTranList = dao.getEntityByModel(wfTran);
				if (wfTranList != null) {
					for (WfActivityTran _wfTran : (List<WfActivityTran>)wfTranList) {
						Long wfTransId = _wfTran.getActivityTransId();
						dao.removeEntity(_wfTran);
						
						//ɾ��wfTrans - condition
						WfTransCondition wfCondition = new WfTransCondition();
						wfCondition.setActivityTransId(wfTransId);
						dao.removeEntity(wfCondition);
					}
				}
				//ɾ��activity trans
				wfTran = new WfActivityTran();
				wfTran.setToWfActivityId(wfActivityId);
				//dao.removeEntity(wfTran);
				wfTranList = dao.getEntityByModel(wfTran);
				if (wfTranList != null) {
					for (WfActivityTran _wfTran : (List<WfActivityTran>)wfTranList) {
						Long wfTransId = _wfTran.getActivityTransId();
						dao.removeEntity(_wfTran);
						//ɾ��wfTrans - condition
						WfTransCondition wfCondition = new WfTransCondition();
						wfCondition.setActivityTransId(wfTransId);
						dao.removeEntity(wfCondition);
					}
				}
				
			}
		}
	}

	public void setServiceTx(ProjectBuilderDAO dao) {
		this.dao = dao;
	}

	public void saveCompany(Company company) {
		List companyList = dao.getEntityByModel(company);
		if (companyList != null) {
			for (Company comp : (List<Company>)companyList) {
				dao.removeEntity(comp);
			}
		}
		dao.saveEntity(company);
	}

	public List<Project> getProjectByModel(Project project) {
		return dao.getEntityByModel(project);
	}

	public List<Package> getPackageByModel(Package comPackage) {
		// TODO Auto-generated method stub
		return dao.getEntityByModel(comPackage);
	}

	public List<Component> getComponentByModel(Component com) {
		// TODO Auto-generated method stub
		return dao.getEntityByModel(com);
	}

	public List<ComDetail> getCompDetailByModel(ComDetail comDetail) {
		// TODO Auto-generated method stub
		return dao.getEntityByModel(comDetail);
	}
	
	public void saveComDetailRelation(ComDetailRelation comDetailRelation) {
		dao.saveEntity(comDetailRelation);
	}

	public void saveWfActivity(WfActivity wfActivity) {
		// TODO Auto-generated method stub
		dao.saveEntity(wfActivity);
		WfActivityPosition position = wfActivity.getWfActivityPosition();
		if (position != null) {
			position.setWfActivityId(wfActivity.getWfActivityId());
			List positionList = dao.getEntityByModel(position);
			if (positionList != null) {
				for (Position pos : (List<Position>)positionList) {
					dao.removeEntity(pos);
				}
			}
			dao.saveEntity(position);
		}
	}

	public void saveActivity(Activity activity) {
		// TODO Auto-generated method stub
		Set<WfAction> wfActions = activity.getActivityActions();
		Set<Parameter> wfParams = activity.getWfActivityParams();
//		Activity _activity = new Activity();
//		_activity.setActivityId(activity.getActivityId());
//		_activity.setActivityName(activity.getActivityName());
//		_activity.setActivityDesc(activity.getActivityDesc());
//		_activity.setActivityType(activity.getActivityType());
//		activity.setActivityActions(null);
//		activity.setWfActivityParams(null);
		Activity _activity = (Activity)dao.getEntityById(Activity.class, activity.getActivityId());
		if (_activity != null) {
			//activity = _activity;
			return;
		}
		dao.saveEntity(activity);
		if (wfParams != null) {
			for (Parameter param : wfParams) {
				dao.saveEntity(param);
				WfActivityParam act_param = new WfActivityParam();
				act_param.setActivityId(activity.getActivityId());
				act_param.setParamId(param.getParamId());	
				dao.saveEntity(act_param);
			}
		}
		if (wfActions != null) {
			int cnt = 0;
			for (WfAction action : wfActions) {
				WfAction _action = (WfAction)dao.getEntityById(WfAction.class, action.getActionId());
				if (_action == null) {
					Set<Parameter> action_arams = action.getActionParams();
					if (action_arams != null) {
						for (Parameter param : action_arams) {
							dao.saveEntity(param);
							ActionParameter action_param = new ActionParameter();
							action_param.setActionId(action.getActionId());
							action_param.setParamId(param.getParamId());
							dao.saveEntity(action_param);
						}
					}
					dao.saveEntity(action);
				}
				ActivityAction act_action = new ActivityAction();
				act_action.setActionId(action.getActionId());
				act_action.setActivityId(activity.getActivityId());
				act_action.setSortNo(new Long(cnt++));
				dao.saveEntity(act_action);			
			}
		}
	}

	public List<WfActivity> getWfActivityByModel(WfActivity wfActivity) {
		// TODO Auto-generated method stub
		return dao.getEntityByModel(wfActivity);
	}

	public void saveWfActivityTran(WfActivityTran wfActivityTran) {
		// TODO Auto-generated method stub
		WfActivityTran _wfActivityTran = new WfActivityTran();
		_wfActivityTran.setConditionPolicy(wfActivityTran.getConditionPolicy());
		_wfActivityTran.setFromWfActivityId(wfActivityTran.getFromWfActivityId());
		_wfActivityTran.setToWfActivityId(wfActivityTran.getToWfActivityId());
		dao.saveEntity(_wfActivityTran);
		Set<WfCondition> conditions = wfActivityTran.getWfTranConditions();
		if (conditions != null) {
			int cnt = 0;
			for (WfCondition condition : conditions) {
				WfCondition _condition = (WfCondition)dao.getEntityById(WfCondition.class, condition.getConditionId());
				if (_condition == null) {
					Set<Parameter> condition_arams = condition.getConditionParams();
					if (condition_arams != null) {
						for (Parameter param : condition_arams) {
							dao.saveEntity(param);			
						}
					}
					dao.saveEntity(condition);
				}
				WfTransCondition trans_con = new WfTransCondition();
				trans_con.setConditionId(condition.getConditionId());
				trans_con.setActivityTransId(_wfActivityTran.getActivityTransId());
				trans_con.setSortNo(new Long(cnt++));
				dao.saveEntity(trans_con);
			}
		}
	}

	public Set<WfAction> getActivityActions(Activity activity) {
		Activity _activity = (Activity)dao.getEntityById(Activity.class, activity.getActivityId());
		Set<WfAction> actions = (Set<WfAction>)_activity.getActivityActions();
		return actions;
	}

	public void saveEntity(Object entity) {
		dao.saveEntity(entity);
	}

	public List getEntityByModel(Object model) {
		// TODO Auto-generated method stub
		return dao.getEntityByModel(model);
	}

	public Object getEntityById(Class cls, Serializable id) {
		// TODO Auto-generated method stub
		return dao.getEntityById(cls, id);
	}
	
	public void removeEntity(Object entity) {
		dao.removeEntity(entity);
	}
}
