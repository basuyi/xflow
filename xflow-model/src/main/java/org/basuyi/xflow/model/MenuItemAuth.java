package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MenuItemAuth implements Serializable {

    /** identifier field */
    private Long menuItemAuthId;

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
    private org.basuyi.xflow.model.MenuItem menuItem;

    /** persistent field */
    private org.basuyi.xflow.model.Authority authority;

    /** full constructor */
    public MenuItemAuth(Long menuItemAuthId, Long creator, Date createDate, Date startDate, Date endDate, Date updateDate, Long updater, org.basuyi.xflow.model.MenuItem menuItem, org.basuyi.xflow.model.Authority authority) {
        this.menuItemAuthId = menuItemAuthId;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
        this.updater = updater;
        this.menuItem = menuItem;
        this.authority = authority;
    }

    /** default constructor */
    public MenuItemAuth() {
    }

    /** minimal constructor */
    public MenuItemAuth(Long menuItemAuthId, org.basuyi.xflow.model.MenuItem menuItem, org.basuyi.xflow.model.Authority authority) {
        this.menuItemAuthId = menuItemAuthId;
        this.menuItem = menuItem;
        this.authority = authority;
    }

    public Long getMenuItemAuthId() {
        return this.menuItemAuthId;
    }

    public void setMenuItemAuthId(Long menuItemAuthId) {
        this.menuItemAuthId = menuItemAuthId;
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

    public org.basuyi.xflow.model.MenuItem getMenuItem() {
        return this.menuItem;
    }

    public void setMenuItem(org.basuyi.xflow.model.MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public org.basuyi.xflow.model.Authority getAuthority() {
        return this.authority;
    }

    public void setAuthority(org.basuyi.xflow.model.Authority authority) {
        this.authority = authority;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("menuItemAuthId", getMenuItemAuthId())
            .toString();
    }

}
