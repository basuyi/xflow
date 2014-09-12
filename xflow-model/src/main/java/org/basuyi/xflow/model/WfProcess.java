package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfProcess implements Serializable {

    /** identifier field */
    private Long wfProcessSeq;

    /** nullable persistent field */
    private String workflowId;

    /** persistent field */
    private String activityId;

    /** nullable persistent field */
    private String activityType;

    /** persistent field */
    private String status;

    /** persistent field */
    private Date startDate;

    /** nullable persistent field */
    private Date finishDate;

    /** nullable persistent field */
    private Date operateDate;

    /** nullable persistent field */
    private Integer repeatCount;

    /** nullable persistent field */
    private Long operater;

    /** nullable persistent field */
    private String remark;

    /** persistent field */
    private org.basuyi.xflow.model.WfControl wfControl;

    /** full constructor */
    public WfProcess(Long wfProcessSeq, String activityId, String activityType, String status, Date startDate, Date finishDate, Date operateDate, Integer repeatCount, Long operater, String remark, org.basuyi.xflow.model.WfControl wfControl) {
        this.wfProcessSeq = wfProcessSeq;
        this.activityId = activityId;
        this.activityType = activityType;
        this.status = status;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.operateDate = operateDate;
        this.repeatCount = repeatCount;
        this.operater = operater;
        this.remark = remark;
        this.wfControl = wfControl;
    }

    /** default constructor */
    public WfProcess() {
    }

    /** minimal constructor */
    public WfProcess(Long wfProcessSeq, String activityId, String status, Date startDate, org.basuyi.xflow.model.WfControl wfControl) {
        this.wfProcessSeq = wfProcessSeq;
        this.activityId = activityId;
        this.status = status;
        this.startDate = startDate;
        this.wfControl = wfControl;
    }

    public Long getWfProcessSeq() {
        return this.wfProcessSeq;
    }

    public void setWfProcessSeq(Long wfProcessSeq) {
        this.wfProcessSeq = wfProcessSeq;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return this.finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getOperateDate() {
        return this.operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public Integer getRepeatCount() {
        return this.repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    public Long getOperater() {
        return this.operater;
    }

    public void setOperater(Long operater) {
        this.operater = operater;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public org.basuyi.xflow.model.WfControl getWfControl() {
        return this.wfControl;
    }

    public void setWfControl(org.basuyi.xflow.model.WfControl wfControl) {
        this.wfControl = wfControl;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("wfProcessSeq", getWfProcessSeq())
            .toString();
    }


    public String getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
}
