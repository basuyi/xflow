package org.basuyi.xflow.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.basuyi.xflow.core.actions.SubflowAction;
import org.basuyi.xflow.core.actions.WorkflowAction;
import org.basuyi.xflow.core.actions.WorkflowCondition;
import org.basuyi.xflow.core.config.model.WfConfigAction;
import org.basuyi.xflow.core.config.model.WfConfigActivity;
import org.basuyi.xflow.core.config.model.WfConfigArgument;
import org.basuyi.xflow.core.config.model.WfConfigCondition;
import org.basuyi.xflow.core.config.model.WfConfigResult;
import org.basuyi.xflow.core.config.model.WfConfigSubflowActivity;
import org.basuyi.xflow.core.config.model.WfConfigUserActivity;
import org.basuyi.xflow.core.config.model.WfConfigUserPolicy;
import org.basuyi.xflow.core.config.model.Workflow;
import org.basuyi.xflow.core.config.model.WorkflowContext;
import org.basuyi.xflow.core.config.model.WorkflowDefination;
import org.basuyi.xflow.core.config.model.WorkflowException;
import org.basuyi.xflow.core.utils.StringUtils;
import org.basuyi.xflow.core.utils.beanshell.BeanShellAction;
import org.basuyi.xflow.core.utils.beanshell.BeanShellCondition;

/**
 * ����������ӿ�
 * @author mashuai
 * @version 0.1
 * @date 2009-10-19
 */
public abstract class AbstractWorkflowEngine implements WorflowEngine {
	
	/*�����������Ķ���*/
	protected WorkflowContext context = null;
	/*����������*/
	protected Workflow workflow = null;
	/*�λ��ڶ���*/
	protected WfConfigActivity nextActivity = null;
	/*�����б����*/
	protected Map<String, WfConfigActivity> wfConfigActivities = null;
	/*���ö���*/
	protected Properties properties = null;
	/*��־����*/
	private static final Logger log = Logger.getLogger(AbstractWorkflowEngine.class.getSimpleName());
	
	public AbstractWorkflowEngine() {
		/*��ʼ�����������������ļ�*/
		initialize();
		
		/*���û����������û��ӿڲ�������������ӻ����м��ع��������������Ķ���*/
		loadWorkflowContext();
	}

	/**
	 * ȡ�õ�ǰ������������
	 */
	protected abstract void initWorkflowContext(String workflowId);
	
	/**
	 * ��ʼ������ȡ�����ļ�
	 */
	private void initialize() {
		properties = new Properties();
		FileInputStream fis = null;
		try {
			URL url = Thread.currentThread().getContextClassLoader().getResource("msworkflow.properties");
			String file = url.getFile();
			fis = new FileInputStream(new File(file));
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					fis = null;
				}
			}
		}
	}
	
	/**
	 * ���������淽��
	 */
	protected void execute() throws Exception {
		try {
			WfConfigActivity currentActivity = context.getCurrentActivity();
			if (currentActivity == null) {
				WfConfigActivity start_activity = getStartActivity();
				context.setCurrentActivity(start_activity);
				executeActivity(start_activity);
			} else {
				executeActivity(currentActivity);
				/*�ж��Ƿ���ֹ*/
				if (WorkflowStatus.TERMINAL == context.getEngineStatus()) {
					return;
				}
				/*�ж��Ƿ����û���������*/
				if (WorkflowStatus.SUSPEND == context.getEngineStatus()) {
					/*���湤����״̬*/
					cacheWorkflowContext();
					
					/*ת�û��ӿڲ���*/
					lanchUserInterface(context);
					return;
				}
			}
			/*�ݹ鴦����*/
			execute();
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * ȡ��ʼ������Ϣ
	 * @return
	 */
	private WfConfigActivity getStartActivity() {
		for (WfConfigActivity activity : wfConfigActivities.values()) {
			if (WorkflowDefination.START_ACTIVITY.equals(activity.getActivityType())) {
				return activity;
			}
		}
		return null;
	}

	/**
	 * �����û��ӿڲ���
	 * @param context
	 */
	protected abstract void lanchUserInterface(WorkflowContext context);

	/**
	 * ���湤���������Ķ���
	 */
	protected abstract void cacheWorkflowContext();
	
	/**
	 * �ָ������������Ķ���
	 */
	protected abstract void loadWorkflowContext();
	
	/**
	 * ͨ�����ڱ�ʾȡ�û��ڶ���
	 * @param activityId
	 * @return
	 */
	private WfConfigActivity getActivityById(String activityId) {
		WfConfigActivity nextActivity1 = wfConfigActivities.get(activityId);
		return nextActivity1;
	}

	/**
	 * ִ�л���
	 * @param activityId
	 * @throws WorkflowException 
	 */
	private void executeActivity(WfConfigActivity wfConfigActivity) throws WorkflowException {
		try {
			System.out.println(wfConfigActivity);
			log.error("��ǰ���ڣ�" + context.getWorkflow().getWorkflowId() + "." + wfConfigActivity.getActivityId());
			/*Ԥ����*/
			beforeExecuteActivity(wfConfigActivity);
			/*������ִ��ʵ��*/
			if (WorkflowDefination.SUBFLOW_ACTIVITY.equals(wfConfigActivity.getActivityType())) {
				executeSubflowActivity(wfConfigActivity);
			} else if (WorkflowDefination.USER_ACTIVITY.equals(wfConfigActivity.getActivityType())) {
				executeUserActivity(wfConfigActivity);
				
				/*������״̬��Ϊ��������ͣ����*/
				if (WorkflowStatus.SUSPEND == context.getEngineStatus()) {
					return;
				}
			} else {
				/*ִ�л���ʵ�嶯������*/
				List<WfConfigAction> wfConfigActions = wfConfigActivity.getActions();
				executeActions(wfConfigActions);
			}
			
			/*���ڽ������*/
			Map<String, WfConfigResult> wfConfigResults = wfConfigActivity.getResults();
			WfConfigResult wfConfigResult = executeResults(wfConfigResults);
		
			/*���ں���*/
			String nextActivityId = wfConfigResult.getActivityId();
			nextActivity = getActivityById(nextActivityId);
			context.setCurrentActivity(nextActivity);
			if (nextActivity == null
					&& WorkflowDefination.WORKFLOW_FINISH_ACTIVITY_ID.equals(nextActivityId)
					&& WorkflowDefination.WORKFLOW_FINISH.equals(wfConfigResult.getStatus())) {
				context.setEngineStatus(WorkflowStatus.TERMINAL);
			} 
//			else {
//				if (WorkflowDefination.USER_ACTIVITY.equals(nextActivity.getActivityType())) {
//					context.setEngineStatus(WorkflowStatus.SUSPEND);
//				}
//			}
	
			/*����*/
			log.error("�������ڣ�" + context.getWorkflow().getWorkflowId() + "." + nextActivityId);
			afterExecuteActivity(wfConfigActivity);
		} catch (WorkflowException e) {
			/*�쳣����*/
			errorExecuteActivity(wfConfigActivity);
			throw e;
	    } catch (Exception e) {
	    	/*�쳣����*/
	    	e.printStackTrace();
			errorExecuteActivity(wfConfigActivity);
			throw new WorkflowException();
	    }
	}

	/**
	 * �˹�������չ�������
	 * @param wfConfigActivity
	 */
	protected abstract void extendUserActivity(WfConfigActivity wfConfigActivity);

	/**
	 * ִ�л����쳣�������
	 * @param wfConfigActivity
	 */
	protected abstract void errorExecuteActivity(WfConfigActivity wfConfigActivity);

	/**
	 * ִ�л��ں������
	 * @param wfConfigActivity
	 */
	protected abstract void afterExecuteActivity(WfConfigActivity wfConfigActivity);

	/**
	 * ִ�л���Ԥ�������
	 * @param wfConfigActivity
	 */
	protected abstract void beforeExecuteActivity(WfConfigActivity wfConfigActivity);

	/**
	 * �����û�����
	 * @param wfConfigActivity
	 * @throws Exception 
	 * @throws WorkflowException
	 */
	private void executeUserActivity(WfConfigActivity wfConfigActivity) throws WorkflowException {
		WfConfigUserActivity wfConfigUserActivity = (WfConfigUserActivity)wfConfigActivity;
		WfConfigUserPolicy wfConfigUserPolicy = wfConfigUserActivity.getUserPolicy();
	    /*�����û�������Ϣ*/
		if (WorkflowStatus.NORMAL == context.getEngineStatus()) {
			
			/*�˹�������չ����*/
			context.putPrivate(WorkflowDefination.CONTEXT_KEY_POST_ID, wfConfigUserPolicy.getRoleId());
			context.putPrivate(WorkflowDefination.CONTEXT_KEY_USER_ID, wfConfigUserPolicy.getUserId());
			
			/*������״̬��Ϊ����*/
			context.setEngineStatus(WorkflowStatus.SUSPEND);
			
			/*�û��ӿ���չ����*/
			extendUserActivity(wfConfigActivity);
		}
		
		/*�û��ӿڷ���Ĺ���������ָ�����*/
		else if (WorkflowStatus.SUSPEND == context.getEngineStatus()) {
			Map<String, Object> in_params = (Map<String, Object>)context.getPrivate(WorkflowDefination.IN_PARAMS);
			if (StringUtils.trimToString(in_params.get(WorkflowDefination.USER_ENTRY)).equals(wfConfigUserPolicy.getEntry())) {
				/*ִ�л��ں�ʵ�嶯������*/
				List<WfConfigAction> postActions = wfConfigUserActivity.getPostActions();
				executeActions(postActions);
				
				/*������״̬�ָ�Ϊ����*/
				context.setEngineStatus(WorkflowStatus.NORMAL);
				
				/*�û��ӿ���չ����*/
				extendUserActivity(wfConfigActivity);
			} else {
				/*ִ�л���ǰʵ�嶯������*/
				List<WfConfigAction> preActions = wfConfigUserActivity.getPreActions();
				executeActions(preActions);
			}
		}
	}

	/**
	 * ���������̻���
	 * @param wfConfigActivity
	 * @throws Exception 
	 */
	private void executeSubflowActivity(WfConfigActivity wfConfigActivity) throws WorkflowException {
		/*ִ�л���ǰʵ�嶯������*/
		WfConfigSubflowActivity wfConfigSubflowActivity = (WfConfigSubflowActivity)wfConfigActivity;
		List<WfConfigAction> preActions = wfConfigSubflowActivity.getPreActions();
		executeActions(preActions);
		
		/*����������*/
		String subflowId = wfConfigSubflowActivity.getSubflowId();
		context.put(WorkflowDefination.CONTEXT_KEY_SUBFLOW_ID, subflowId);
		WorkflowAction subflowAction = new SubflowAction();
		subflowAction.execute(context);
		context.remove(WorkflowDefination.CONTEXT_KEY_SUBFLOW_ID);
		
		/*ִ�л��ں�ʵ�嶯������*/
		List<WfConfigAction> postActions = wfConfigSubflowActivity.getPostActions();
		executeActions(postActions);
	}

	/**
	 * ִ�л���ʵ�嶯������
	 * @param wfConfigActions
	 * @throws Exception 
	 */
	private void executeActions(List<WfConfigAction> wfConfigActions) throws WorkflowException {
		try {
			for (WfConfigAction wfConfigAction : wfConfigActions) {
				log.error("ִ�д���ʼ��" + wfConfigAction.getActionId());
				String type = wfConfigAction.getType();
				if ("".equals(type) || WorkflowDefination.DEFAULT_ACTION_TYPE.equals(type)) {
					String argName = wfConfigAction.getArgument().getArgName();
					if (WorkflowDefination.ARG_VALUE_CLASS.equals(argName)) {
						String className = wfConfigAction.getArgument().getArgValue();
						Class clsAction = Class.forName(className);
						WorkflowAction workflowAction = (WorkflowAction)clsAction.newInstance();
						//log.error("ִ�д���ʼ��" + wfConfigAction.getActionId() + "-" + className);
						workflowAction.execute(context);
						//log.error("ִ�д��������" + wfConfigAction.getActionId() + "-" + className);
					}
				} else if (WorkflowDefination.BEANSHELL_ACTION_TYPE.equals(type)) {
					context.put(WorkflowDefination.CONTEXT_KEY_ARGUMENT, wfConfigAction.getArgument());
					WorkflowAction workflowAction = new BeanShellAction();
					workflowAction.execute(context);
					context.remove(WorkflowDefination.CONTEXT_KEY_ARGUMENT);
				}
				log.error("ִ�д��������" + wfConfigAction.getActionId());
			}
		} catch (WorkflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WorkflowException();
		}
	}
	
	/**
	 * ���ڽ������
	 * @param wfConfigResults
	 */
	private WfConfigResult executeResults(Map<String, WfConfigResult> wfConfigResults) {
		WfConfigResult wfConfigResult = null;
		Set<String> resultSet = wfConfigResults.keySet();
		String [] resultKeys = resultSet.toArray(new String[0]);
		for (String resultKey : resultKeys) {
			wfConfigResult = wfConfigResults.get(resultKey);
//			if (WorkflowDefination.DEFAULT_RESULT_TYPE.equals(wfConfigResult.getResultType())) {
//				break;
//			}
			List<WfConfigCondition> wfConfigConditions = wfConfigResult.getConditions();
			if (wfConfigConditions == null) {
				break;
			}
			String policy = StringUtils.trimToString(wfConfigResult.getPolicy());
			boolean match = false;
			for (WfConfigCondition wfConfigCondition : wfConfigConditions) {
				boolean _match = executeCondition(wfConfigCondition);
				if ("or".equals(policy)) {
					if (_match) {
						match = true;
						break;
					}
				} else if ("and".equals(policy)) {
					if (_match) {
						match = true;
						continue;
					} else {
						match = false;
						break;
					}
				} else {
					if (_match) {
						match = true;
						break;
					}
				}
			}
			if (match) {
				break;
			}
		}
		//����������Ϊ�գ���Ϊ������ִ�н������ֶ��������״̬
		if (wfConfigResult == null) {
			wfConfigResult = new WfConfigResult();
			wfConfigResult.setActivityId(WorkflowDefination.WORKFLOW_FINISH_ACTIVITY_ID);
			wfConfigResult.setStatus(WorkflowDefination.WORKFLOW_FINISH);
		}
		return wfConfigResult;
	}
	
	/**
	 * ִ�������ж�
	 * @param wfConfigCondition
	 * @return
	 */
	private boolean executeCondition(WfConfigCondition wfConfigCondition) {
		boolean match = false;
		try {
			WorkflowCondition workflowCondition = getCondition(wfConfigCondition);
			
			if (WorkflowDefination.BEANSHELL_CONDITION_TYPE.equals(wfConfigCondition.getConditionType()))
				context.put(WorkflowDefination.CONTEXT_KEY_ARGUMENT, wfConfigCondition.getArgument());
			
			match = workflowCondition.processCondition(context);
			
			if (WorkflowDefination.BEANSHELL_CONDITION_TYPE.equals(wfConfigCondition.getConditionType()))
				context.remove(WorkflowDefination.CONTEXT_KEY_ARGUMENT);
		} catch (WorkflowException e) {
			e.printStackTrace();
		}
		return match;
	}
	
	/**
	 * ȡ����������
	 * @param wfConfigCondition
	 * @return
	 * @throws WorkflowException
	 */
	private WorkflowCondition getCondition(WfConfigCondition wfConfigCondition) throws WorkflowException {
		String conditionType = wfConfigCondition.getConditionType();
		WfConfigArgument arg = wfConfigCondition.getArgument();
		String argValue = arg.getArgValue();
		WorkflowCondition workflowCondition = null;
		if ("".equals(conditionType) || WorkflowDefination.DEFAULT_CONDITION_TYPE.equals(conditionType)) {
			try {
				workflowCondition = (WorkflowCondition)Class.forName(argValue).newInstance();
			} catch (Exception e) {
				if (e instanceof WorkflowException) {
					throw (WorkflowException)e;
				} else {
					throw new WorkflowException("", e);
				}
			}
		} else if (WorkflowDefination.BEANSHELL_CONDITION_TYPE.equals(conditionType)) {
			workflowCondition = new BeanShellCondition();
		} else {
			throw new WorkflowException();
		}
		return workflowCondition;
	}

	/**
	 * @return �����������Ķ���
	 */
	public WorkflowContext getContext() {
		return context;
	}

	/**
	 * @param context �����������Ķ���
	 */
	public void setContext(WorkflowContext context) {
		this.context = context;
	}

	/**
	 * @return ����������
	 */
	public Workflow getWorkflow() {
		return workflow;
	}

	/**
	 * @param workflow ����������
	 */
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	protected Properties getProperties() {
		return properties;
	}

	protected void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	/**
	 * ��������������
	 */
	public void start() throws Exception {
		this.execute();
	}
	
	/**
	 * ������������ǰ����
	 */
	protected abstract Map<String, Object> preStartWorkflow(Map<String, Object> params);
	
	/**
	 * �����������̺���
	 */
	protected abstract Map<String, Object> postStartWorkflow(Map<String, Object> params);
	
	/**
	 * ����������
	 * @throws WorkflowException 
	 */
	public Map<String, Object> startWorkflow(Map<String, Object> in_params) throws WorkflowException {
		Map<String, Object> out_params = new HashMap<String, Object>();
		String workflowId = StringUtils.trimToString(in_params.get(WorkflowDefination.WORKFLOW_ID));
		
		/*��ʼ��������������*/
		if (context == null) {
			initWorkflowContext(workflowId);
		}
		
		/*���������������*/
		context.putPrivate(WorkflowDefination.IN_PARAMS, in_params);
		context.put(WorkflowDefination.IN_PARAMS, in_params);
		workflow = context.getWorkflow();
		wfConfigActivities = workflow.getActivities();
		
		/*�����������ǰ����*/
		this.preStartWorkflow(in_params);
		
		/*��������*/
		try {
			this.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			if (e instanceof WorkflowException) {
				throw (WorkflowException)e;
			} else {
				throw new WorkflowException("", e);
			}
		}
		
		/*�����������̺���*/
		this.postStartWorkflow(in_params);
		
		out_params.put(WorkflowDefination.WORKFLOW_CONTEXT, context);
		return out_params;
	}
}
