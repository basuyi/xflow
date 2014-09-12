package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Organize implements Serializable {

    /** identifier field */
    private Long orgId;

    /** nullable persistent field */
    private String orgName;

    /** nullable persistent field */
    private Long orgType;

    /** persistent field */
    private Date startDate;

    /** persistent field */
    private Date createDate;

    /** persistent field */
    private String updater;

    /** persistent field */
    private Date updateDate;

    /** persistent field */
    private Date creator;

    /** nullable persistent field */
    private Date endDate;

    /** persistent field */
    private Set users;

    /** persistent field */
    private Set orgAuthorities;

    /** full constructor */
    public Organize(Long orgId, String orgName, Long orgType, Date startDate, Date createDate, String updater, Date updateDate, Date creator, Date endDate, Set users, Set orgAuthorities) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgType = orgType;
        this.startDate = startDate;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
        this.creator = creator;
        this.endDate = endDate;
        this.users = users;
        this.orgAuthorities = orgAuthorities;
    }

    /** default constructor */
    public Organize() {
    }

    /** minimal constructor */
    public Organize(Long orgId, Date startDate, Date createDate, String updater, Date updateDate, Date creator, Set users, Set orgAuthorities) {
        this.orgId = orgId;
        this.startDate = startDate;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
        this.creator = creator;
        this.users = users;
        this.orgAuthorities = orgAuthorities;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getOrgType() {
        return this.orgType;
    }

    public void setOrgType(Long orgType) {
        this.orgType = orgType;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdater() {
        return this.updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreator() {
        return this.creator;
    }

    public void setCreator(Date creator) {
        this.creator = creator;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set getUsers() {
        return this.users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

    public Set getOrgAuthorities() {
        return this.orgAuthorities;
    }

    public void setOrgAuthorities(Set orgAuthorities) {
        this.orgAuthorities = orgAuthorities;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("orgId", getOrgId())
            .toString();
    }

}
