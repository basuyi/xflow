package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserPost implements Serializable {

    /** identifier field */
    private Long userPostId;

    /** nullable persistent field */
    private Date startDate;

    /** nullable persistent field */
    private Date endDate;

    /** persistent field */
    private Date createDate;

    /** persistent field */
    private long creator;

    /** persistent field */
    private Date updateDate;

    /** persistent field */
    private long updater;

    /** persistent field */
    private org.basuyi.xflow.model.Post post;

    /** persistent field */
    private org.basuyi.xflow.model.User user;

    /** full constructor */
    public UserPost(Long userPostId, Date startDate, Date endDate, Date createDate, long creator, Date updateDate, long updater, org.basuyi.xflow.model.Post post, org.basuyi.xflow.model.User user) {
        this.userPostId = userPostId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = createDate;
        this.creator = creator;
        this.updateDate = updateDate;
        this.updater = updater;
        this.post = post;
        this.user = user;
    }

    /** default constructor */
    public UserPost() {
    }

    /** minimal constructor */
    public UserPost(Long userPostId, Date createDate, long creator, Date updateDate, long updater, org.basuyi.xflow.model.Post post, org.basuyi.xflow.model.User user) {
        this.userPostId = userPostId;
        this.createDate = createDate;
        this.creator = creator;
        this.updateDate = updateDate;
        this.updater = updater;
        this.post = post;
        this.user = user;
    }

    public Long getUserPostId() {
        return this.userPostId;
    }

    public void setUserPostId(Long userPostId) {
        this.userPostId = userPostId;
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

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getCreator() {
        return this.creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public long getUpdater() {
        return this.updater;
    }

    public void setUpdater(long updater) {
        this.updater = updater;
    }

    public org.basuyi.xflow.model.Post getPost() {
        return this.post;
    }

    public void setPost(org.basuyi.xflow.model.Post post) {
        this.post = post;
    }

    public org.basuyi.xflow.model.User getUser() {
        return this.user;
    }

    public void setUser(org.basuyi.xflow.model.User user) {
        this.user = user;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userPostId", getUserPostId())
            .toString();
    }

}
