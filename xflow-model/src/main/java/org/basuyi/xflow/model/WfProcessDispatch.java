package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfProcessDispatch implements Serializable {

    /** identifier field */
    private Long dispatchSeq;

    /** persistent field */
    private java.lang.Long wfProcessSeq;

    /** persistent field */
    private java.lang.Long wfInstId;

    /** nullable persistent field */
    private Long dispatchType;

    /** nullable persistent field */
    private Long postId;

    /** nullable persistent field */
    private Long userId;

    /** nullable persistent field */
    private Long orgId;

    /** nullable persistent field */
    private Date optDate;

    /** nullable persistent field */
    private Long dispatchStatus;

    /** nullable persistent field */
    private Long tagPolicy;

    /** full constructor */
    public WfProcessDispatch(Long dispatchSeq, long wfProcessSeq, long wfInstId, Long dispatchType, Long postId, Long userId, Long orgId, Date optDate, Long dispatchStatus, Long tagPolicy) {
        this.dispatchSeq = dispatchSeq;
        this.wfProcessSeq = wfProcessSeq;
        this.wfInstId = wfInstId;
        this.dispatchType = dispatchType;
        this.postId = postId;
        this.userId = userId;
        this.orgId = orgId;
        this.optDate = optDate;
        this.dispatchStatus = dispatchStatus;
        this.tagPolicy = tagPolicy;
    }

    /** default constructor */
    public WfProcessDispatch() {
    }

    /** minimal constructor */
    public WfProcessDispatch(Long dispatchSeq, long wfProcessSeq, long wfInstId) {
        this.dispatchSeq = dispatchSeq;
        this.wfProcessSeq = wfProcessSeq;
        this.wfInstId = wfInstId;
    }

    public Long getDispatchSeq() {
        return this.dispatchSeq;
    }

    public void setDispatchSeq(Long dispatchSeq) {
        this.dispatchSeq = dispatchSeq;
    }

    public Long getWfProcessSeq() {
        return this.wfProcessSeq;
    }

    public void setWfProcessSeq(Long wfProcessSeq) {
        this.wfProcessSeq = wfProcessSeq;
    }

    public Long getWfInstId() {
        return this.wfInstId;
    }

    public void setWfInstId(Long wfInstId) {
        this.wfInstId = wfInstId;
    }

    public Long getDispatchType() {
        return this.dispatchType;
    }

    public void setDispatchType(Long dispatchType) {
        this.dispatchType = dispatchType;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Date getOptDate() {
        return this.optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public Long getDispatchStatus() {
        return this.dispatchStatus;
    }

    public void setDispatchStatus(Long dispatchStatus) {
        this.dispatchStatus = dispatchStatus;
    }

    public Long getTagPolicy() {
        return this.tagPolicy;
    }

    public void setTagPolicy(Long tagPolicy) {
        this.tagPolicy = tagPolicy;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("dispatchSeq", getDispatchSeq())
            .toString();
    }

}
