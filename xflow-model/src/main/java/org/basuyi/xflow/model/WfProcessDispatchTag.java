package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class WfProcessDispatchTag implements Serializable {

    /** identifier field */
    private Long dispatchTagSeq;

    /** nullable persistent field */
    private Long dispatchSeq;

    /** nullable persistent field */
    private Long optOrder;

    /** nullable persistent field */
    private Long status;

    /** nullable persistent field */
    private Long postId;

    /** nullable persistent field */
    private Long userId;

    /** nullable persistent field */
    private Long orgId;

    /** persistent field */
    private Date startDate;

    /** nullable persistent field */
    private Date finishDate;

    /** nullable persistent field */
    private Date operateDate;

    /** nullable persistent field */
    private String resultCode;

    /** nullable persistent field */
    private String remark;

    /** full constructor */
    public WfProcessDispatchTag(Long dispatchTagSeq, Long dispatchSeq, Long optOrder, Long status, Long postId, Long userId, Long orgId, Date startDate, Date finishDate, Date operateDate, String resultCode, String remark) {
        this.dispatchTagSeq = dispatchTagSeq;
        this.dispatchSeq = dispatchSeq;
        this.optOrder = optOrder;
        this.status = status;
        this.postId = postId;
        this.userId = userId;
        this.orgId = orgId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.operateDate = operateDate;
        this.resultCode = resultCode;
        this.remark = remark;
    }

    /** default constructor */
    public WfProcessDispatchTag() {
    }

    /** minimal constructor */
    public WfProcessDispatchTag(Long dispatchTagSeq, Date startDate) {
        this.dispatchTagSeq = dispatchTagSeq;
        this.startDate = startDate;
    }

    public Long getDispatchTagSeq() {
        return this.dispatchTagSeq;
    }

    public void setDispatchTagSeq(Long dispatchTagSeq) {
        this.dispatchTagSeq = dispatchTagSeq;
    }

    public Long getDispatchSeq() {
        return this.dispatchSeq;
    }

    public void setDispatchSeq(Long dispatchSeq) {
        this.dispatchSeq = dispatchSeq;
    }

    public Long getOptOrder() {
        return this.optOrder;
    }

    public void setOptOrder(Long optOrder) {
        this.optOrder = optOrder;
    }

    public Long getStatus() {
        return this.status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("dispatchTagSeq", getDispatchTagSeq())
            .toString();
    }

}
