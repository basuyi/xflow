package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OrgAuthority implements Serializable {

    /** identifier field */
    private Long orgAuthId;

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
    private org.basuyi.xflow.model.Organize organize;

    /** persistent field */
    private org.basuyi.xflow.model.Authority authority;

    /** full constructor */
    public OrgAuthority(Long orgAuthId, Long creator, Date createDate, Date startDate, Date endDate, Date updateDate, Long updater, org.basuyi.xflow.model.Organize organize, org.basuyi.xflow.model.Authority authority) {
        this.orgAuthId = orgAuthId;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
        this.updater = updater;
        this.organize = organize;
        this.authority = authority;
    }

    /** default constructor */
    public OrgAuthority() {
    }

    /** minimal constructor */
    public OrgAuthority(Long orgAuthId, org.basuyi.xflow.model.Organize organize, org.basuyi.xflow.model.Authority authority) {
        this.orgAuthId = orgAuthId;
        this.organize = organize;
        this.authority = authority;
    }

    public Long getOrgAuthId() {
        return this.orgAuthId;
    }

    public void setOrgAuthId(Long orgAuthId) {
        this.orgAuthId = orgAuthId;
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

    public org.basuyi.xflow.model.Organize getOrganize() {
        return this.organize;
    }

    public void setOrganize(org.basuyi.xflow.model.Organize organize) {
        this.organize = organize;
    }

    public org.basuyi.xflow.model.Authority getAuthority() {
        return this.authority;
    }

    public void setAuthority(org.basuyi.xflow.model.Authority authority) {
        this.authority = authority;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("orgAuthId", getOrgAuthId())
            .toString();
    }

}
