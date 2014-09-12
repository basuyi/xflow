package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfControl implements Serializable {

    /** identifier field */
    private Long wfInstId;

    /** nullable persistent field */
    private String workflowId;

    /** nullable persistent field */
    private Long creator;

    /** nullable persistent field */
    private Date createDate;

    /** nullable persistent field */
    private Long status;

    /** nullable persistent field */
    private Date startDate;

    /** nullable persistent field */
    private Date finishDate;

    /** nullable persistent field */
    private String currentActivityId;

    /** nullable persistent field */
    private String remark;

    /** full constructor */
    public WfControl(Long wfInstId, String workflowId, Long creator, Date createDate, Long status, Date startDate, Date finishDate, String currentActivityId, String remark) {
        this.wfInstId = wfInstId;
        this.workflowId = workflowId;
        this.creator = creator;
        this.createDate = createDate;
        this.status = status;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.currentActivityId = currentActivityId;
        this.remark = remark;
    }

    /** default constructor */
    public WfControl() {
    }

    /** minimal constructor */
    public WfControl(Long wfInstId) {
        this.wfInstId = wfInstId;
    }

    public Long getWfInstId() {
        return this.wfInstId;
    }

    public void setWfInstId(Long wfInstId) {
        this.wfInstId = wfInstId;
    }

    public String getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public Long getCreator() {
        return this.creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getStatus() {
        return this.status;
    }

    public void setStatus(Long status) {
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

    public String getCurrentActivityId() {
        return this.currentActivityId;
    }

    public void setCurrentActivityId(String currentActivityId) {
        this.currentActivityId = currentActivityId;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("wfInstId", getWfInstId())
            .toString();
    }

}
