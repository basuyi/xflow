package org.basuyi.xflow.core.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.basuyi.xflow.core.config.model.WfConfigAction;
import org.basuyi.xflow.core.config.model.WfConfigActivity;
import org.basuyi.xflow.core.config.model.WfConfigArgument;
import org.basuyi.xflow.core.config.model.WfConfigCondition;
import org.basuyi.xflow.core.config.model.WfConfigParameter;
import org.basuyi.xflow.core.config.model.WfConfigResult;
import org.basuyi.xflow.core.config.model.WfConfigSubflowActivity;
import org.basuyi.xflow.core.config.model.WfConfigUserActivity;
import org.basuyi.xflow.core.config.model.WfConfigUserPolicy;
import org.basuyi.xflow.core.config.model.Workflow;
import org.basuyi.xflow.core.config.model.WorkflowDefination;
import org.basuyi.xflow.model.ActionParameter;
import org.basuyi.xflow.model.Activity;
import org.basuyi.xflow.model.Parameter;
import org.basuyi.xflow.model.SubflowActivity;
import org.basuyi.xflow.model.UserActivity;
import org.basuyi.xflow.model.WfAction;
import org.basuyi.xflow.model.WfActivity;
import org.basuyi.xflow.model.WfActivityTran;
import org.basuyi.xflow.model.WfCondition;
import org.basuyi.xflow.model.WfDefination;
import org.basuyi.xflow.core.service.DBWFService;
import org.basuyi.xflow.core.utils.StringUtils;

public class DBWFConfig {
	
	private DBWFService serviceNoTx;
	
	public static final Map<String, Workflow> workflows = new HashMap<String, Workflow>();

	
	/**
	 * �������̱�ʶȡ�ù���������
	 * @param workflowId
	 * @return
	 */
	public static Workflow getWorkflowByID(String workflowId) {
		return workflows.get(workflowId);
	}
	
	/**
	 * ���ݿ⹤�������ó�ʼ��
	 */
	public void initialize() {
		this.loadAllWorkflows();
	}
	
	/**
	 * �������й�������������
	 */
	public void loadAllWorkflows() {
		/*��ѯ���еĹ�������Ϣ*/
		List result_list = serviceNoTx.getAllWfDefination();
		Collection<WfDefination> workflow_list = margeResutlSet(result_list);
		System.out.println();
		for (WfDefination workflow : (Collection<WfDefination>)workflow_list) {
			Workflow _workflow = new Workflow();
			_workflow.setWorkflowId(workflow.getWorkflowId());
			_workflow.setWorkflowName(workflow.getWorkflowName());
			_workflow.setDescription(workflow.getWorkflowDesc());
			Map<String, WfConfigActivity> wfConfigActivities = new HashMap<String, WfConfigActivity>();
			_workflow.setActivities(wfConfigActivities);
			Map<String, WfConfigParameter> wfConfigParameter = new HashMap<String, WfConfigParameter>();
			_workflow.setWfConfigParameters(wfConfigParameter);
			
			/*�������̻����б�*/
			Set<Activity> activity_list = workflow.getWfActivities();
			
			/*�������л�����Ϣ*/
			for (Activity activity : activity_list) {
				WfConfigActivity _activity = null;
				if (activity instanceof UserActivity) {
					_activity = new WfConfigUserActivity();
				} else if (activity instanceof SubflowActivity) {
					_activity = new WfConfigSubflowActivity();
				} else {
					_activity = new WfConfigActivity();
				}
				_activity.setActivityId(activity.getActivityId());
				_activity.setActivityName(activity.getActivityName());
				if (activity.getActivityType() == 0) {
					_activity.setActivityType("start");
				} else if (activity.getActivityType() == 1) {
					_activity.setActivityType("app");
				} else if (activity.getActivityType() == 2) {
					_activity.setActivityType("user");
				} else if (activity.getActivityType() == 3) {
					_activity.setActivityType("subflow");
				} else if (activity.getActivityType() == 9) {
					_activity.setActivityType("end");
				}
				_activity.setDecription(activity.getActivityDesc());
				
				Set<WfAction> activityActions = activity.getActivityActions();
				Set<WfAction> activityPreActions = activity.getActivityPreActions();
				Set<WfAction> activityPostActions = activity.getActivityPostActions();
				if (activity instanceof UserActivity) {
					WfConfigUserActivity _userActivity = (WfConfigUserActivity)_activity;
					/*�����û�����ǰ�������б�*/
					List<WfConfigAction> workflowPreActions = new ArrayList<WfConfigAction>();
					_userActivity.setPreActions(workflowPreActions);
					setActions(activityPreActions, workflowPreActions);
					/*�����û����ں������б�*/
					List<WfConfigAction> workflowPostActions = new ArrayList<WfConfigAction>();
					_userActivity.setPostActions(workflowPostActions);
					setActions(activityPostActions, workflowPostActions);
					/*�����û�����*/
					WfConfigUserPolicy wfConfigUserPolicy = new WfConfigUserPolicy();
					if (((UserActivity) activity).getPolicyType() == 0) {
						wfConfigUserPolicy.setType("rbac");
						wfConfigUserPolicy.setRoleId(StringUtils.trimToString(((UserActivity) activity).getPostId()));
					} else if (((UserActivity) activity).getPolicyType() == 1) {
						wfConfigUserPolicy.setType("user-input");
						wfConfigUserPolicy.setUserId(StringUtils.trimToString(((UserActivity) activity).getUserId()));
					}
					if (((UserActivity) activity).getEntryType() == 0) {
						wfConfigUserPolicy.setEntryType("page");
					} else if (((UserActivity) activity).getEntryType() == 1) {
						wfConfigUserPolicy.setEntryType("action");
					}
					wfConfigUserPolicy.setEntry(StringUtils.trimToString(((UserActivity) activity).getEntryValue()));
					_userActivity.setUserPolicy(wfConfigUserPolicy);
				} else if (activity instanceof SubflowActivity) {
					WfConfigSubflowActivity _subflowActivity = (WfConfigSubflowActivity)_activity;
					/*���������̻���ǰ�������б�*/
					List<WfConfigAction> workflowPreActions = new ArrayList<WfConfigAction>();
					_subflowActivity.setPreActions(workflowPreActions);
					setActions(activityPreActions, workflowPreActions);
					/*���������̻��ں������б�*/
					List<WfConfigAction> workflowPostActions = new ArrayList<WfConfigAction>();
					_subflowActivity.setPostActions(workflowPostActions);
					setActions(activityPostActions, workflowPostActions);
					/*���������̱�ʶ*/
					_subflowActivity.setSubflowId(((SubflowActivity)activity).getSubflowId());
				} else {
					/*���뻷�ڶ����б�*/
					List<WfConfigAction> wfConfigActions = new ArrayList<WfConfigAction>();
					_activity.setActions(wfConfigActions);
					setActions(activityActions, wfConfigActions);
				}
				
				/*���뻷�ڲ����б�*/
				Set<Parameter> wfActivityParams = activity.getWfActivityParams();
				Map<String, WfConfigParameter> activityParameter = new HashMap<String, WfConfigParameter>();
				_activity.setWfConfigParameters(activityParameter);
				for (Parameter parameter : wfActivityParams) {
					WfConfigParameter _parameter = new WfConfigParameter();
					_parameter.setKey(parameter.getParamCode());
					if (parameter.getParamType() == 0) {
						_parameter.setType("in");
					} else if (parameter.getParamType() == 1) {
						_parameter.setType("out");
					} else if (parameter.getParamType() == 2) {
						_parameter.setType("both");
					}
					_parameter.setParamClass(parameter.getParamClass());
					activityParameter.put(_parameter.getKey(), _parameter);
				}
				
				/*���뻷��ת���б�*/
				WfActivity wfActivity = new WfActivity();
				wfActivity.setWorkflowId(_workflow.getWorkflowId());
				wfActivity.setActivityId(_activity.getActivityId());
				
				/*��ѯ���������ڱ�ʶ*/
				List<WfActivity> wfActivity_list = this.serviceNoTx.getWfActivityByModel(wfActivity);
				wfActivity = wfActivity_list.get(0);
				
				/*���ݹ��������ڱ�ʶ��ѯ����ת��Ŀ��*/
				WfActivityTran wfTrans = new WfActivityTran();
				wfTrans.setFromWfActivityId(wfActivity.getWfActivityId());
				List<WfActivityTran> wfTrans_list = this.serviceNoTx.getWfActivityTrans(wfTrans);
				Map<String, WfConfigResult> workflowResults = new HashMap<String, WfConfigResult>();
				_activity.setResults(workflowResults);
				for (WfActivityTran wf_trans : wfTrans_list) {
					WfConfigResult workflowResult = new WfConfigResult();
					WfActivity _wfActivity = this.serviceNoTx.getWfActivity(wf_trans.getToWfActivityId());
					if (wf_trans.getToWfActivityId() == -1) {//���̽����������⴦��nextActivityId��Ϊ-1
						workflowResult.setActivityId(WorkflowDefination.WORKFLOW_FINISH_ACTIVITY_ID);
						workflowResult.setStatus(WorkflowDefination.WORKFLOW_FINISH);
					} else {
						workflowResult.setActivityId(_wfActivity.getActivityId());
					}
					workflowResult.setResultId(StringUtils.trimToString(wf_trans.getActivityTransId()));
					if (StringUtils.transToLong(wf_trans.getConditionPolicy()) == null || wf_trans.getConditionPolicy() == 0) {
						workflowResult.setResultType("default");
					} else if (wf_trans.getConditionPolicy() == 1) {
						workflowResult.setPolicy("and");
						workflowResult.setResultType("condition");
					} else if (wf_trans.getConditionPolicy() == 2) {
						workflowResult.setPolicy("or");
						workflowResult.setResultType("condition");
					}
					
					/*����ת�������б�*/
					Set<WfCondition> condition_list = wf_trans.getWfTranConditions();
					List<WfConfigCondition> workflowConditions = new ArrayList<WfConfigCondition>();
					workflowResult.setConditions(workflowConditions);
					for (WfCondition condition : condition_list) {
						WfConfigCondition workflowCondition = new WfConfigCondition();
						workflowCondition.setConditionId(condition.getConditionId());
						workflowCondition.setConditionName(condition.getConditionName());
						
						WfConfigArgument arg = new WfConfigArgument();
						if (condition.getConditionType() == 0) {
							workflowCondition.setConditionType("default");
							arg.setArgName("class");
						} else if (condition.getConditionType() == 1) {
							workflowCondition.setConditionType("beanshell");
							arg.setArgName("script");
						}
						arg.setArgValue(condition.getConditionValue());
						workflowCondition.setArgument(arg);
						
						/*��ѯ���������б�*/
						Set<Parameter> actionParameters = condition.getConditionParams();
						/*�������������б�*/
						Map<String, WfConfigParameter> wfConfigParameters = new HashMap<String, WfConfigParameter>();
						workflowCondition.setWfConfigParameters(wfConfigParameters);
						for (Parameter parameter : actionParameters) {
							WfConfigParameter _parameter = new WfConfigParameter();
							_parameter.setKey(parameter.getParamCode());
							if (parameter.getParamType() == 0) {
								_parameter.setType("in");
							} else if (parameter.getParamType() == 1) {
								_parameter.setType("out");
							} else if (parameter.getParamType() == 2) {
								_parameter.setType("both");
							}
							_parameter.setParamClass(parameter.getParamClass());
							wfConfigParameters.put(_parameter.getKey(), _parameter);
						}
						
						workflowConditions.add(workflowCondition);
					}
					
					/*����ת�������б�*/
					Set<WfAction> action_list = wf_trans.getWfTranActions();
					List<WfConfigAction> workflowActions = new ArrayList<WfConfigAction>();
					workflowResult.setActions(workflowActions);
					setActions(action_list, workflowActions);
					
					workflowResults.put(workflowResult.getResultId(), workflowResult);
				}
				
				
				
				wfConfigActivities.put(_activity.getActivityId(), _activity);
			}
			
			/*�������̲����б�*/
			Set<Parameter> parameter_list = workflow.getWfParameters();
			for (Parameter parameter : parameter_list) {
				WfConfigParameter _parameter = new WfConfigParameter();
				_parameter.setKey(parameter.getParamCode());
				if (parameter.getParamType() == 0) {
					_parameter.setType("in");
				} else if (parameter.getParamType() == 1) {
					_parameter.setType("out");
				} else if (parameter.getParamType() == 2) {
					_parameter.setType("both");
				}
				_parameter.setParamClass(parameter.getParamClass());
				wfConfigParameter.put(_parameter.getKey(), _parameter);
			}
			workflows.put(_workflow.getWorkflowId(), _workflow);
		}
	}

	private Collection<WfDefination> margeResutlSet(List result_list) {
		Map<String, WfDefination> hash = new HashMap<String, WfDefination>();
		for (WfDefination workflow : (List<WfDefination>)result_list) {
			String workflowId = workflow.getWorkflowId();
			WfDefination _workflow = new WfDefination();
			if (hash.containsKey(workflowId)) {
				_workflow = hash.get(workflowId);
			} else {
				hash.put(workflowId, workflow);
			}
			Set<Activity> _acitivity_set = _workflow.getWfActivities();
			_acitivity_set = _acitivity_set != null ? _acitivity_set : new HashSet<Activity>();
			for (Activity activity : (Set<Activity>)workflow.getWfActivities()) {
				if (!_acitivity_set.contains(activity)) {
					_acitivity_set.add(activity);
				} else {
					Activity _activity = getMatchedActivity(_acitivity_set, activity);
					Set<WfAction> action_set = activity.getActivityActions();
					Set<WfAction> _action_set = _activity.getActivityActions();
					_action_set = _action_set != null ? _action_set : new HashSet<WfAction>();
					mergeActivityActions(action_set, _action_set);
					Set<WfAction> preaction_set = activity.getActivityPreActions();
					Set<WfAction> _preaction_set = _activity.getActivityActions();
					_preaction_set = _preaction_set != null ? _preaction_set : new HashSet<WfAction>();
					mergeActivityActions(preaction_set, _preaction_set);
					Set<WfAction> postaction_set = activity.getActivityPostActions();
					Set<WfAction> _postaction_set = _activity.getActivityPostActions();
					_postaction_set = _postaction_set != null ? _postaction_set : new HashSet<WfAction>();
					mergeActivityActions(postaction_set, _postaction_set);
				}
			}
		}
		return (Collection<WfDefination>)hash.values();
	}

	private void mergeActivityActions(Set<WfAction> action_set,
			Set<WfAction> _action_set) {
		for (WfAction action : action_set) {
			if (!_action_set.contains(action)) {
				_action_set.add(action);
			} else {
				WfAction _action = getMatchedAction(_action_set, action);
			}
		}
	}

	private Activity getMatchedActivity(Set<Activity> _acitivity_set,
			Activity activity) {
		Activity ret_activity = null;
		for (Activity _activity : _acitivity_set) {
			if (_activity.getActivityId().equals(activity.getActivityId())) {
				ret_activity = _activity;
				break;
			}
		}
		return ret_activity;
	}

	private WfAction getMatchedAction(Set<WfAction> _acition_set,
			WfAction action) {
		WfAction ret_action = null;
		for (WfAction _action : _acition_set) {
			if (_action.getActionId().equals(action.getActionId())) {
				ret_action = _action;
				break;
			}
		}
		return ret_action;
	}

	/**
	 * ���û��ڵĶ����б�
	 * @param activityActions
	 * @param workflowActions
	 */
	private void setActions(Set<WfAction> activityActions,
			List<WfConfigAction> workflowActions) {
		for (WfAction action : activityActions) {
			WfConfigAction _action = new WfConfigAction();
			workflowActions.add(_action);
			_action.setActionId(action.getActionId());
			_action.setActionName(action.getActionName());
			_action.setDescription(action.getActionDesc());
			WfConfigArgument _arg = new WfConfigArgument();
			_arg.setArgValue(action.getActionValue());
			if (action.getActionType() == 0) {
				_action.setType("default");
				_arg.setArgName("class");
			} else if (action.getActionType() == 1) {
				_action.setType("beanshell");
				_arg.setArgName("script");
			}
			_action.setArgument(_arg);
			
			/*��ѯ���������б�*/
			List<ActionParameter> actionParams = this.serviceNoTx.getActionParams(_action.getActionId());
			actionParams = actionParams != null ? actionParams : new ArrayList<ActionParameter>();
			
			/*���붯�������б�*/
			Map<String, WfConfigParameter> wfConfigParameters = new HashMap<String, WfConfigParameter>();
			_action.setWfConfigParameters(wfConfigParameters);
			for (ActionParameter actionParam : actionParams) {
				Set<Parameter> params = actionParam.getParameters();
				if (params != null && params.size() > 0) {
					Parameter parameter = params.toArray(new Parameter[0])[0];
					WfConfigParameter _parameter = new WfConfigParameter();
					_parameter.setKey(parameter.getParamCode());
					if (parameter.getParamType() == 0) {
						_parameter.setType("in");
					} else if (parameter.getParamType() == 1) {
						_parameter.setType("out");
					} else if (parameter.getParamType() == 2) {
						_parameter.setType("both");
					}
					_parameter.setParamClass(parameter.getParamClass());
					wfConfigParameters.put(_parameter.getKey(), _parameter);
				}
			}
		}
	}
	
	public void setServiceNoTx(DBWFService serviceNoTx) {
		this.serviceNoTx = serviceNoTx;
	}
}
