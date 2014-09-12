package org.basuyi.xflow.core.service;

import java.util.List;

import org.basuyi.xflow.model.ActionParameter;
import org.basuyi.xflow.model.Activity;
import org.basuyi.xflow.model.WfAction;
import org.basuyi.xflow.model.WfActivity;
import org.basuyi.xflow.model.WfActivityTran;
import org.basuyi.xflow.model.WfCondition;
import org.basuyi.xflow.model.WfDefination;

public interface DBWFService {
	public List getAllWfDefination();
	public Activity getActivity(String activityId);
	public List<WfActivity> getWfActivityByModel(WfActivity wfActivity);
	public WfActivity getWfActivity(Long wfActivityId);
	public List<WfActivityTran> getAllWfActivityTrans();
	public List<WfActivityTran> getWfActivityTrans(WfActivityTran wfTrans);
	public List<ActionParameter> getActionParams(String actionId);
	public WfCondition getCondition(String conditionId);
}
