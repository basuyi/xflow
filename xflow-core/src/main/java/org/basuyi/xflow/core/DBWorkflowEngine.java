package org.basuyi.xflow.core;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.basuyi.xflow.core.config.WorkflowConfig;
import org.basuyi.xflow.core.config.WorkflowConfigFactory;
import org.basuyi.xflow.core.config.model.WfConfigActivity;
import org.basuyi.xflow.core.config.model.WfConfigUserActivity;
import org.basuyi.xflow.core.config.model.WfConfigUserPolicy;
import org.basuyi.xflow.core.config.model.Workflow;
import org.basuyi.xflow.core.config.model.WorkflowContext;
import org.basuyi.xflow.core.config.model.WorkflowDefination;
import org.basuyi.xflow.model.WfControl;
import org.basuyi.xflow.model.WfProcess;
import org.basuyi.xflow.model.WfProcessDispatch;
import org.basuyi.xflow.core.service.WorkflowService;
import org.basuyi.xflow.core.utils.StringUtils;

/**
 * �������ݿ����õĹ���������ʵ��
 * @author mashuai
 * @version 0.1
 * @date 2009-10-21
 */
public class DBWorkflowEngine extends AbstractWorkflowEngine {
	
	private WorkflowService wfServiceNoTx;
	private WorkflowService wfServiceTx;
	
	public Map<String, Object> preStartWorkflow(Map<String, Object> in_params) {
		String workflowId = StringUtils.trimToString(in_params.get(WorkflowDefination.WORKFLOW_ID));
		String userId = StringUtils.trimToString(in_params.get(WorkflowDefination.USER_ID));
		String postId = StringUtils.trimToString(in_params.get(WorkflowDefination.POST_ID));
		String orgId = StringUtils.trimToString(in_params.get(WorkflowDefination.ORG_ID));
		String dispatcher = StringUtils.trimToString(in_params.get(WorkflowDefination.DISPATCHER));
		String opt_user_id = StringUtils.trimToString(in_params.get(WorkflowDefination.OPT_USER_ID));
		String is_subflow = StringUtils.trimToString(in_params.get(WorkflowDefination.IS_SUBFLOW));
		Workflow upper_workflow = (Workflow)in_params.get(WorkflowDefination.UPPER_WORKFLOW);
		String upper_activity_id = StringUtils.trimToString(in_params.get(WorkflowDefination.UPPER_ACTIVITY_ID));
		String wf_inst_id = StringUtils.trimToString(in_params.get(WorkflowDefination.WF_INST_ID));
		String wf_process_seq = StringUtils.trimToString(in_params.get(WorkflowDefination.WF_PROCESS_SEQ));
		String page_id = StringUtils.trimToString(in_params.get(WorkflowDefination.USER_ENTRY));
		Map<String, Object> upper_wf_params = (Map<String, Object>)in_params.get(WorkflowDefination.UPPER_WF_PARAMS);
		
		/*������û�������룬�淶�����������������еı�Ҫ����*/
		if (!"".equals(wf_process_seq)) {
			WfProcess i_wf_process = new WfProcess();
			i_wf_process.setWfProcessSeq(StringUtils.transToLong(wf_process_seq));
			WfProcess wf_process = wfServiceNoTx.getWfProcess(i_wf_process);
			if (wf_process != null) {
				String currentActivityId = wf_process.getActivityId();
				WfControl i_wf_control = new WfControl();
				i_wf_control.setWfInstId(StringUtils.transToLong(wf_inst_id));
				WfControl wf_control = wfServiceNoTx.getWfControl(i_wf_control);
				currentActivityId = wf_control.getCurrentActivityId();
				WfConfigActivity currentActivity = workflow.getActivityById(currentActivityId);
				context.setCurrentActivity(currentActivity);
				context.setEngineStatus(WorkflowStatus.SUSPEND);
				this.context.put(WorkflowDefination.CONTEXT_KEY_WF_CONTROL, wf_control);
				this.context.putPrivate(WorkflowDefination.CONTEXT_KEY_WF_PROCESS, wf_process);
			}
		}
		
		context.setSubflow("1".equals(is_subflow) ? true : false);
		if (context.isSubflow()) {
			context.setUpperWorkflow(upper_workflow);
			context.setParams(upper_wf_params);
		} else if ("".equals(wf_process_seq)) {
			WfControl wf_control = new WfControl();
			wf_control.setWorkflowId(workflowId);
			wf_control.setCreateDate(new Date());
			wf_control.setStartDate(new Date());
			wf_control.setStatus(0L);
			wf_control.setCreator(StringUtils.transToLong(userId));
			this.wfServiceNoTx.saveWfControl(wf_control);
			this.context.put(WorkflowDefination.CONTEXT_KEY_WF_CONTROL, wf_control);
		}
		context.put(WorkflowDefination.CONTEXT_KEY_WORKFLOW_ID, workflowId);
		context.put(WorkflowDefination.CONTEXT_KEY_ORG_ID, orgId);
		context.put(WorkflowDefination.CONTEXT_KEY_POST_ID, postId);
		context.put(WorkflowDefination.CONTEXT_KEY_USER_ID, userId);
		context.put(WorkflowDefination.CONTEXT_KEY_DISPATCHER, dispatcher);
		context.put(WorkflowDefination.CONTEXT_KEY_OPT_USER_ID, opt_user_id);
		context.put(WorkflowDefination.CONTEXT_KEY_UPPER_ACTIVITY_ID, upper_activity_id);
		context.put(WorkflowDefination.CONTEXT_KEY_WF_SERVICE_NOTX, wfServiceNoTx);
		context.put(WorkflowDefination.CONTEXT_KEY_WF_SERVICE_TX, wfServiceTx);
		
		return null;
	}

	@Override
	protected void initWorkflowContext(String i_workflowId) {
		String [] workflowId_array = i_workflowId.split("\\.");
		String workflowId = workflowId_array[workflowId_array.length - 1];
		String workflow_config_policy = StringUtils.trimToString(properties.getProperty(WorkflowDefination.WF_CONFIG_POLICY));
		WorkflowConfig configure = new WorkflowConfigFactory().getWorkflowConfig(workflow_config_policy);
		context = configure.initContext(workflowId);
	}
	
	@Override
	protected void cacheWorkflowContext() {
//		String cache_file = (String)this.getProperties().get("cache_dir");
//		ContextUtils.outputs(this.getContext(), cache_file);
	}
	
	@Override
	protected void loadWorkflowContext() {
//		String cache_file = (String)this.getProperties().get("cache_dir");
//		this.setContext((WorkflowContext)ContextUtils.inputs(cache_file));
	}

	@Override
	protected void lanchUserInterface(WorkflowContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void afterExecuteActivity(WfConfigActivity wfConfigActivity) {
		WfProcess wf_process = (WfProcess)this.context.getPrivate(WorkflowDefination.CONTEXT_KEY_WF_PROCESS);
		wf_process.setFinishDate(new Date());
		wf_process.setStatus(WorkflowDefination.WF_PROCESS_STATUS_S);//�ɹ�
		wf_process.setOperater(StringUtils.transToLong((String)this.context.get(WorkflowDefination.CONTEXT_KEY_USER_ID)));
		this.wfServiceNoTx.saveWfProcess(wf_process);
		this.context.removePrivate(WorkflowDefination.CONTEXT_KEY_WF_PROCESS);
	}

	@Override
	protected void beforeExecuteActivity(WfConfigActivity wfConfigActivity) {
		WfControl wf_control = (WfControl)this.context.get(WorkflowDefination.CONTEXT_KEY_WF_CONTROL);
		WfProcess wf_process = (WfProcess)this.context.getPrivate(WorkflowDefination.CONTEXT_KEY_WF_PROCESS);
		if (wf_process == null) {
			wf_process = new WfProcess();
		}
		wf_process.setWorkflowId(StringUtils.trimToString(context.get(WorkflowDefination.CONTEXT_KEY_WORKFLOW_ID)));
		if (!"".equals(StringUtils.trimToString(context.get(WorkflowDefination.CONTEXT_KEY_UPPER_ACTIVITY_ID)))) {
			wf_process.setActivityId(StringUtils.trimToString(context.get(WorkflowDefination.CONTEXT_KEY_UPPER_ACTIVITY_ID)) + "." + wfConfigActivity.getActivityId());
		} else {
			wf_process.setActivityId(wfConfigActivity.getActivityId());
		}
		wf_process.setStartDate(new Date());
		wf_process.setWfControl(wf_control);
		wf_process.setStatus(WorkflowDefination.WF_PROCESS_STATUS_I);//��ʼ
		wf_process.setStartDate(new Date());
		wf_process.setOperateDate(new Date());
		wf_process.setOperater(StringUtils.transToLong((String)this.context.get(WorkflowDefination.CONTEXT_KEY_USER_ID)));
		this.wfServiceNoTx.saveWfProcess(wf_process);
		this.context.putPrivate(WorkflowDefination.CONTEXT_KEY_WF_PROCESS, wf_process);
		wf_control.setCurrentActivityId(wfConfigActivity.getActivityId());
		this.wfServiceNoTx.saveWfControl(wf_control);
	}
	
	public void setWfServiceNoTx(WorkflowService wfServiceNoTx) {
		this.wfServiceNoTx = wfServiceNoTx;
	}
	
	public void setWfServiceTx(WorkflowService wfServiceTx) {
		this.wfServiceTx = wfServiceTx;
	}

	@Override
	protected void errorExecuteActivity(WfConfigActivity wfConfigActivity) {
		WfProcess wf_process = (WfProcess)this.context.getPrivate(WorkflowDefination.CONTEXT_KEY_WF_PROCESS);
		wf_process.setFinishDate(new Date());
		wf_process.setStatus(WorkflowDefination.WF_PROCESS_STATUS_F);//ʧ��
		wf_process.setOperater(StringUtils.transToLong((String)this.context.get(WorkflowDefination.CONTEXT_KEY_USER_ID)));
		this.wfServiceNoTx.saveWfProcess(wf_process);
		this.context.removePrivate(WorkflowDefination.CONTEXT_KEY_WF_PROCESS);
	}

	@Override
	protected void extendUserActivity(WfConfigActivity wfConfigActivity) {
		WfProcessDispatch wf_dispatch = new WfProcessDispatch();
		Map<String, Object> in_params = (Map<String, Object>)context.getPrivate(WorkflowDefination.IN_PARAMS);
		String wf_process_seq = StringUtils.trimToString(in_params.get(WorkflowDefination.WF_PROCESS_SEQ));
		if (!"".equals(wf_process_seq)) {
			WfProcessDispatch i_wf_dispatch = new WfProcessDispatch();
			i_wf_dispatch.setWfProcessSeq(StringUtils.transToLong(wf_process_seq));
			List<WfProcessDispatch> wf_dispatch_list = wfServiceNoTx.getWfProcessDispatchByModel(i_wf_dispatch);
			if (wf_dispatch_list != null && wf_dispatch_list.size() > 0) {
				wf_dispatch = wf_dispatch_list.get(0);
			}
		} else {
			WfProcess wf_process = (WfProcess)this.context.getPrivate(WorkflowDefination.CONTEXT_KEY_WF_PROCESS);
			WfControl wf_control = (WfControl)this.context.get(WorkflowDefination.CONTEXT_KEY_WF_CONTROL);
			wf_dispatch = new WfProcessDispatch();
			wf_dispatch.setWfInstId(wf_control.getWfInstId());
			wf_dispatch.setWfProcessSeq(wf_process.getWfProcessSeq());
		}
		WfConfigUserActivity wfConfigUserActivity = (WfConfigUserActivity)wfConfigActivity;
		WfConfigUserPolicy wfConfigUserPolicy = wfConfigUserActivity.getUserPolicy();
		if (WorkflowDefination.USER_POLICY_RBAC.equals(wfConfigUserPolicy.getType())) {
			wf_dispatch.setDispatchType(1L);
		} else if (WorkflowDefination.USER_POLICY_UESR_INPUT.equals(wfConfigUserPolicy.getType())) {
			wf_dispatch.setDispatchType(0L);
		}
		if (WorkflowStatus.SUSPEND == context.getEngineStatus()) {
			wf_dispatch.setDispatchStatus(1L);//�ȴ�����
		} else if (WorkflowStatus.NORMAL == context.getEngineStatus()) {
			wf_dispatch.setDispatchStatus(5L);//�������
		}
		wf_dispatch.setUserId(StringUtils.transToLong(this.context.getPrivate(WorkflowDefination.CONTEXT_KEY_USER_ID)));
		wf_dispatch.setPostId(StringUtils.transToLong(this.context.getPrivate(WorkflowDefination.CONTEXT_KEY_POST_ID)));
		wf_dispatch.setOrgId(StringUtils.transToLong(this.context.getPrivate(WorkflowDefination.CONTEXT_KEY_ORG_ID)));
		wf_dispatch.setOptDate(new Date());
		this.wfServiceNoTx.saveWfProcessDispatch(wf_dispatch);
	}

	@Override
	protected Map<String, Object> postStartWorkflow(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
}
