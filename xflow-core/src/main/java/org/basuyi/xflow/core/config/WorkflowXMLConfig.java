package org.basuyi.xflow.core.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.basuyi.xflow.core.WorkflowStatus;
import org.basuyi.xflow.core.config.model.ActivityFactory;
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
import org.basuyi.xflow.core.config.model.WorkflowContext;
import org.basuyi.xflow.core.config.model.WorkflowDefination;
import org.basuyi.xflow.core.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * �����������ļ���ȡ��
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class WorkflowXMLConfig extends AbstractWorkflowConfig {
	
	/**
	 * ��ʼ�������������Ķ���
	 * @return
	 */
	public WorkflowContext initContext(String workflowId) {
		/*�����������ļ�·��*/
		String config_path = WorkflowDefination.WF_CONFIG_DIR + WorkflowDefination.DIR_DELIMITER + workflowId + WorkflowDefination.WF_CONFIG_EXTENTION;
		
		if (context != null) {
			return context;
		}
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			File file = new File(config_path);
			doc = reader.read(file);
			setContext(doc, context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return context;
	}

	/**
	 * ���������������ļ������ù����������Ķ���
	 * @param doc
	 * @param context2
	 */
	private void setContext(Document doc, WorkflowContext context2) throws Exception {
		Element root = doc.getRootElement();
		
		/*ȡ�ù�����������Ϣ*/
		String workflowId = StringUtils.getElementValue(root, "id");
		String workflowName = StringUtils.getElementValue(root, "name");
		String description = StringUtils.getElementValue(root, "description");
		Workflow workflow = new Workflow();
		
		/*���������������в����ڵ�*/
		List parameters = root.element("parameters").elements("parameter");
		Map<String, WfConfigParameter> workflowParameters = new HashMap<String, WfConfigParameter>();
		for (int i = 0; parameters != null && i < parameters.size(); i++) {
			Element parameter = (Element)parameters.get(i);
			
			/*���ò�����Ϣ*/
			WfConfigParameter workflowParameter = new WfConfigParameter();
			workflowParameter.setType(StringUtils.getAttributeValue(parameter, "type"));
			workflowParameter.setKey(StringUtils.getElementValue(parameter));
			workflowParameters.put(workflowParameter.getKey(), workflowParameter);
		}
		
		/*���������������л��ڽڵ�*/
		List activities = root.element("activities").elements("activity");
		Map<String, WfConfigActivity> workflowActivities = new HashMap<String, WfConfigActivity>();
		for (int i = 0; activities != null && i < activities.size(); i++) {
			Element activity = (Element)activities.get(i);
			
			/*���û��ڻ�����Ϣ*/
			WfConfigActivity workflowActivity = ActivityFactory.getActivity(StringUtils.getAttributeValue(activity, "type"));
			workflowActivity.setActivityId(StringUtils.getAttributeValue(activity, "id"));
			workflowActivity.setActivityName(StringUtils.getAttributeValue(activity, "name"));
			workflowActivity.setActivityType(StringUtils.getAttributeValue(activity, "type"));
			workflowActivity.setDecription(StringUtils.getElementValue(activity, "description"));
			
			/*���ó�ʼ����*/
			if ("start".equals(StringUtils.getAttributeValue(activity, "type")))
				workflow.setStartActivity(workflowActivity);
			workflowActivities.put(workflowActivity.getActivityId(), workflowActivity);

			/*���û���ִ����Ϣ*/
			if ("subflow".equals(workflowActivity.getActivityType())) {/*�û���Ϊ������*/
				fetchSubflowctivity(activity, workflowActivity);
			} else if ("user".equals(workflowActivity.getActivityType())) {/*�û���Ϊ�û��ӿ�*/
				fetchUserActivity(activity, workflowActivity);
			} else {
				fetchAppActivity(activity, workflowActivity);
			}
			
			/*��ȡ�û��������н��������Ϣ*/
			fetchResultInfo(activity, workflowActivity);
		}
		
		/*���ù���������*/
		workflow.setActivities(workflowActivities);
		workflow.setDescription(description);
		workflow.setWorkflowId(workflowId);
		workflow.setWorkflowName(workflowName);
		workflow.setWfConfigParameters(workflowParameters);
		context = new WorkflowContext();
		context.setWorkflow(workflow);
		context.setEngineStatus(WorkflowStatus.NORMAL);
	}

	/**
	 * *��ȡ�û��������н��������Ϣ
	 * @param activity
	 * @param workflowActivity
	 */
	private void fetchResultInfo(Element activity, WfConfigActivity workflowActivity) {
		/*�����û��������н���ڵ�*/
		List results = activity.element("results").elements("result");
		Map<String, WfConfigResult> workflowResults = new HashMap<String, WfConfigResult>();
		workflowActivity.setResults(workflowResults);
		for (int j = 0; results != null && j < results.size(); j++) {
			Element result = (Element)results.get(j);
			
			/*���ý������*/
			WfConfigResult workflowResult = new WfConfigResult();
			workflowResult.setResultId(StringUtils.getAttributeValue(result, "id"));
			workflowResult.setResultName(StringUtils.getAttributeValue(result, "name"));
			workflowResult.setResultType(StringUtils.getAttributeValue(result, "type"));
			workflowResult.setActivityId(StringUtils.getAttributeValue(result, "activity"));
			workflowResult.setStatus(StringUtils.getAttributeValue(result, "status"));
			workflowResult.setNextStatus(StringUtils.getAttributeValue(result, "next-status"));
			workflowResult.setPolicy(StringUtils.getAttributeValue(result, "policy"));
			if (result.elements("condition") != null) {
				List<WfConfigCondition> workflowConditions = new ArrayList<WfConfigCondition>();
				for (Element condition: (List<Element>)result.elements("condition")) {
					WfConfigCondition workflowCondition = new WfConfigCondition();
					workflowCondition.setConditionId(StringUtils.getAttributeValue(condition, "id"));
					workflowCondition.setConditionName(StringUtils.getAttributeValue(condition, "name"));
					workflowCondition.setConditionType(StringUtils.getAttributeValue(condition, "type"));
					WfConfigArgument arg = new WfConfigArgument();
					arg.setArgValue(StringUtils.getElementValue(condition, "arg"));
					arg.setArgName(StringUtils.getAttributeValue(condition.element("arg"), "name"));
					workflowCondition.setArgument(arg);
					workflowConditions.add(workflowCondition);
				}
				workflowResult.setConditions(workflowConditions);
			}
			workflowResults.put(workflowResult.getActivityId(), workflowResult);
		}
	}

	/**
	 * ��ȡӦ�û�����Ϣ
	 * @param activity
	 * @param workflowActivity
	 */
	private void fetchAppActivity(Element activity, WfConfigActivity workflowActivity) {
		/*�����û��������ж����ڵ�*/
		List actions = activity.element("actions").elements("action");
		List<WfConfigAction> workflowActions = new ArrayList<WfConfigAction>();
		workflowActivity.setActions(workflowActions);
		for (int j = 0; actions != null && j < actions.size(); j++) {
			Element action = (Element)actions.get(j);
			
			/*���ö�������*/
			WfConfigAction workflowAction = new WfConfigAction();
			workflowActions.add(workflowAction);
			workflowAction.setActionId(StringUtils.getAttributeValue(action, "id"));
			workflowAction.setActionName(StringUtils.getAttributeValue(action, "name"));
			workflowAction.setType(StringUtils.getAttributeValue(action, "type"));
			WfConfigArgument arg = new WfConfigArgument();
			arg.setArgValue(StringUtils.getElementValue(action, "arg"));
			arg.setArgName(StringUtils.getAttributeValue(action.element("arg"), "name"));
			workflowAction.setArgument(arg);
		}
	}

	/**
	 * ��ȡ�û�������Ϣ
	 * @param activity
	 * @param workflowActivity
	 */
	private void fetchUserActivity(Element activity, WfConfigActivity workflowActivity) {
		WfConfigUserActivity wfConfigUserActivity = (WfConfigUserActivity)workflowActivity;
		/*�����û���������ִ��ǰ�����ڵ�*/
		if (activity.element("pre-actions") != null) {
			List preActions = activity.element("pre-actions").elements("action");
			List<WfConfigAction> workflowPreActions = new ArrayList<WfConfigAction>();
			wfConfigUserActivity.setPreActions(workflowPreActions);
			for (int j = 0; preActions != null && j < preActions.size(); j++) {
				Element action = (Element)preActions.get(j);
				
				/*���ö�������*/
				WfConfigAction workflowAction = new WfConfigAction();
				workflowPreActions.add(workflowAction);
				workflowAction.setActionId(StringUtils.getAttributeValue(action, "id"));
				workflowAction.setActionName(StringUtils.getAttributeValue(action, "name"));
				workflowAction.setType(StringUtils.getAttributeValue(action, "type"));
				WfConfigArgument arg = new WfConfigArgument();
				arg.setArgValue(StringUtils.getElementValue(action, "arg"));
				arg.setArgName(StringUtils.getAttributeValue(action.element("arg"), "name"));
				workflowAction.setArgument(arg);
			}
		}
		/*�����û���������ִ�к����ڵ�*/
		if (activity.element("post-actions") != null) {
			List postActions = activity.element("post-actions").elements("action");
			List<WfConfigAction> workflowPostActions = new ArrayList<WfConfigAction>();
			wfConfigUserActivity.setPostActions(workflowPostActions);
			for (int j = 0; postActions != null && j < postActions.size(); j++) {
				Element action = (Element)postActions.get(j);
				
				/*���ö�������*/
				WfConfigAction workflowAction = new WfConfigAction();
				workflowPostActions.add(workflowAction);
				workflowAction.setActionId(StringUtils.getAttributeValue(action, "id"));
				workflowAction.setActionName(StringUtils.getAttributeValue(action, "name"));
				workflowAction.setType(StringUtils.getAttributeValue(action, "type"));
				WfConfigArgument arg = new WfConfigArgument();
				arg.setArgValue(StringUtils.getElementValue(action, "arg"));
				arg.setArgName(StringUtils.getAttributeValue(action.element("arg"), "name"));
				workflowAction.setArgument(arg);
			}
		}
		/*ȡ���û�����*/
		Element user_policy = activity.element("user-policy");
		if (user_policy != null) {
			Element entry = user_policy.element("entry");
			String entryType = StringUtils.getAttributeValue(entry, "type");
			WfConfigUserPolicy wfConfigUserPolicy = new WfConfigUserPolicy();
			wfConfigUserPolicy.setEntryType(entryType);
			wfConfigUserPolicy.setEntry(StringUtils.getElementValue(user_policy, "entry"));
			String type = StringUtils.getAttributeValue(user_policy, "type");
			wfConfigUserPolicy.setType(type);
			if ("rbac".equals(type)) {
				wfConfigUserPolicy.setRoleId(StringUtils.getElementValue(user_policy, "role"));
			} else if ("user-input".equals(type)) {
				wfConfigUserPolicy.setUserId(StringUtils.getElementValue(user_policy, "user-input"));
			}
			wfConfigUserActivity.setUserPolicy(wfConfigUserPolicy);
		}
	}

	/**
	 * ��ȡ�����̻�����Ϣ
	 * @param activity
	 * @param workflowActivity
	 */
	private void fetchSubflowctivity(Element activity, WfConfigActivity workflowActivity) {
		WfConfigSubflowActivity wfConfigSubflowActivity = (WfConfigSubflowActivity)workflowActivity;
		/*�����û���������ִ��ǰ�����ڵ�*/
		if (activity.element("pre-actions") != null) {
			List preActions = activity.element("pre-actions").elements("action");
			List<WfConfigAction> workflowPreActions = new ArrayList<WfConfigAction>();
			wfConfigSubflowActivity.setPreActions(workflowPreActions);
			for (int j = 0; preActions != null && j < preActions.size(); j++) {
				Element action = (Element)preActions.get(j);
				
				/*���ö�������*/
				WfConfigAction workflowAction = new WfConfigAction();
				workflowPreActions.add(workflowAction);
				workflowAction.setActionId(StringUtils.getAttributeValue(action, "id"));
				workflowAction.setActionName(StringUtils.getAttributeValue(action, "name"));
				workflowAction.setType(StringUtils.getAttributeValue(action, "type"));
				WfConfigArgument arg = new WfConfigArgument();
				arg.setArgValue(StringUtils.getElementValue(action, "arg"));
				arg.setArgName(StringUtils.getAttributeValue(action.element("arg"), "name"));
				workflowAction.setArgument(arg);
			}
		}
		/*�����û���������ִ�к����ڵ�*/
		if (activity.element("post-actions") != null) {
			List postActions = activity.element("post-actions").elements("action");
			List<WfConfigAction> workflowPostActions = new ArrayList<WfConfigAction>();
			wfConfigSubflowActivity.setPostActions(workflowPostActions);
			for (int j = 0; postActions != null && j < postActions.size(); j++) {
				Element action = (Element)postActions.get(j);
				
				/*���ö�������*/
				WfConfigAction workflowAction = new WfConfigAction();
				workflowPostActions.add(workflowAction);
				workflowAction.setActionId(StringUtils.getAttributeValue(action, "id"));
				workflowAction.setActionName(StringUtils.getAttributeValue(action, "name"));
				workflowAction.setType(StringUtils.getAttributeValue(action, "type"));
				WfConfigArgument arg = new WfConfigArgument();
				arg.setArgValue(StringUtils.getElementValue(action, "arg"));
				arg.setArgName(StringUtils.getAttributeValue(action.element("arg"), "name"));
				workflowAction.setArgument(arg);
			}
		}
		/*ȡ�������̱�ʶ*/
		wfConfigSubflowActivity.setSubflowId(StringUtils.getElementValue(activity, "subflow"));
	}
}
