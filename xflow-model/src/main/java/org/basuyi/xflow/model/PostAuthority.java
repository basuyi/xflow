package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PostAuthority implements Serializable {

    /** identifier field */
    private Long postAuthId;

    /** nullable persistent field */
    private Long creator;

    /** nullable persistent field */
    private Date createDate;

    /** nullable persistent field */
    private Date startDate;

    /** nullable persistent field */
    private Date endDate;

    /** nullable persistent field */
    private Date updateDate;

    /** nullable persistent field */
    private Long updater;

    /** persistent field */
    private org.basuyi.xflow.model.Post post;

    /** persistent field */
    private org.basuyi.xflow.model.Authority authority;

    /** full constructor */
    public PostAuthority(Long postAuthId, Long creator, Date createDate, Date startDate, Date endDate, Date updateDate, Long updater, org.basuyi.xflow.model.Post post, org.basuyi.xflow.model.Authority authority) {
        this.postAuthId = postAuthId;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
        this.updater = updater;
        this.post = post;
        this.authority = authority;
    }

    /** default constructor */
    public PostAuthority() {
    }

    /** minimal constructor */
    public PostAuthority(Long postAuthId, org.basuyi.xflow.model.Post post, org.basuyi.xflow.model.Authority authority) {
        this.postAuthId = postAuthId;
        this.post = post;
        this.authority = authority;
    }

    public Long getPostAuthId() {
        return this.postAuthId;
    }

    public void setPostAuthId(Long postAuthId) {
        this.postAuthId = postAuthId;
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

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdater() {
        return this.updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public org.basuyi.xflow.model.Post getPost() {
        return this.post;
    }

    public void setPost(org.basuyi.xflow.model.Post post) {
        this.post = post;
    }

    public org.basuyi.xflow.model.Authority getAuthority() {
        return this.authority;
    }

    public void setAuthority(org.basuyi.xflow.model.Authority authority) {
        this.authority = authority;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("postAuthId", getPostAuthId())
            .toString();
    }

}
