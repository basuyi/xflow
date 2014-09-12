package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserAuthority implements Serializable {

    /** identifier field */
    private Long userAuthId;

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
    private org.basuyi.xflow.model.Authority authority;

    /** persistent field */
    private org.basuyi.xflow.model.User user;

    /** full constructor */
    public UserAuthority(Long userAuthId, Long creator, Date createDate, Date startDate, Date endDate, Date updateDate, Long updater, org.basuyi.xflow.model.Authority authority, org.basuyi.xflow.model.User user) {
        this.userAuthId = userAuthId;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
        this.updater = updater;
        this.authority = authority;
        this.user = user;
    }

    /** default constructor */
    public UserAuthority() {
    }

    /** minimal constructor */
    public UserAuthority(Long userAuthId, org.basuyi.xflow.model.Authority authority, org.basuyi.xflow.model.User user) {
        this.userAuthId = userAuthId;
        this.authority = authority;
        this.user = user;
    }

    public Long getUserAuthId() {
        return this.userAuthId;
    }

    public void setUserAuthId(Long userAuthId) {
        this.userAuthId = userAuthId;
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

    public org.basuyi.xflow.model.Authority getAuthority() {
        return this.authority;
    }

    public void setAuthority(org.basuyi.xflow.model.Authority authority) {
        this.authority = authority;
    }

    public org.basuyi.xflow.model.User getUser() {
        return this.user;
    }

    public void setUser(org.basuyi.xflow.model.User user) {
        this.user = user;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userAuthId", getUserAuthId())
            .toString();
    }

}
