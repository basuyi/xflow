package org.basuyi.xflow.core.service;

import java.util.List;

import org.basuyi.xflow.common.dao.EntityManager;
import org.basuyi.xflow.model.ActionParameter;
import org.basuyi.xflow.model.Activity;
import org.basuyi.xflow.model.WfAction;
import org.basuyi.xflow.model.WfActivity;
import org.basuyi.xflow.model.WfActivityTran;
import org.basuyi.xflow.model.WfCondition;
import org.basuyi.xflow.model.WfDefination;

public class DBWFServiceImpl implements DBWFService {
	
	public EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public List getAllWfDefination() {
		return this.entityManager.load(WfDefination.class);
//		return this.entityManager.find("from WfDefination a, WfActivity b, Activity c where a.workflowId=b.workflowId and b.activityId=c.activityId");
	}
	public Activity getActivity(String activityId) {
		return (Activity)this.entityManager.get(Activity.class, activityId);
	}
	public List<WfActivityTran> getAllWfActivityTrans() {
		// TODO Auto-generated method stub
		return this.entityManager.load(WfActivityTran.class);
	}
	public WfCondition getCondition(String conditionId) {
		// TODO Auto-generated method stub
		return (WfCondition)this.entityManager.get(WfCondition.class, conditionId);
	}
	public List<WfActivity> getWfActivityByModel(WfActivity wfActivity) {
		// TODO Auto-generated method stub
		return (List<WfActivity>)this.entityManager.find(wfActivity);
	}
	public List<WfActivityTran> getWfActivityTrans(WfActivityTran wfTrans) {
		// TODO Auto-generated method stub
		return (List<WfActivityTran>)this.entityManager.find(wfTrans);
	}
	public WfActivity getWfActivity(Long wfActivityId) {
		// TODO Auto-generated method stub
		return (WfActivity)this.entityManager.get(WfActivity.class, wfActivityId);
	}
	public List<ActionParameter> getActionParams(String actionId) {
		// TODO Auto-generated method stub
		ActionParameter actionParam = new ActionParameter();
		actionParam.setActionId(actionId);
		return (List<ActionParameter>)this.entityManager.find(actionParam);
	}
}
