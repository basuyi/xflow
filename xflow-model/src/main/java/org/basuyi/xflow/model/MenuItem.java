package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MenuItem implements Serializable {

    /** identifier field */
    private Long menuItemId;

    /** persistent field */
    private String menuItemCaption;

    /** nullable persistent field */
    private String menuItemDesc;

    /** nullable persistent field */
    private Long menuItemType;

    /** nullable persistent field */
    private Long upperId;

    /** nullable persistent field */
    private String pageUrl;

    /** nullable persistent field */
    private Long creator;

    /** nullable persistent field */
    private Date createDate;

    /** nullable persistent field */
    private Date startDate;

    /** nullable persistent field */
    private Date endDate;

    /** nullable persistent field */
    private Long updater;

    /** nullable persistent field */
    private Date updateDate;

    /** persistent field */
    private org.basuyi.xflow.model.Menu menu;

    /** persistent field */
    private Set menuItemAuths;

    /** full constructor */
    public MenuItem(Long menuItemId, String menuItemCaption, String menuItemDesc, Long menuItemType, Long upperId, String pageUrl, Long creator, Date createDate, Date startDate, Date endDate, Long updater, Date updateDate, org.basuyi.xflow.model.Menu menu, Set menuItemAuths) {
        this.menuItemId = menuItemId;
        this.menuItemCaption = menuItemCaption;
        this.menuItemDesc = menuItemDesc;
        this.menuItemType = menuItemType;
        this.upperId = upperId;
        this.pageUrl = pageUrl;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updater = updater;
        this.updateDate = updateDate;
        this.menu = menu;
        this.menuItemAuths = menuItemAuths;
    }

    /** default constructor */
    public MenuItem() {
    }

    /** minimal constructor */
    public MenuItem(Long menuItemId, String menuItemCaption, org.basuyi.xflow.model.Menu menu, Set menuItemAuths) {
        this.menuItemId = menuItemId;
        this.menuItemCaption = menuItemCaption;
        this.menu = menu;
        this.menuItemAuths = menuItemAuths;
    }

    public Long getMenuItemId() {
        return this.menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemCaption() {
        return this.menuItemCaption;
    }

    public void setMenuItemCaption(String menuItemCaption) {
        this.menuItemCaption = menuItemCaption;
    }

    public String getMenuItemDesc() {
        return this.menuItemDesc;
    }

    public void setMenuItemDesc(String menuItemDesc) {
        this.menuItemDesc = menuItemDesc;
    }

    public Long getMenuItemType() {
        return this.menuItemType;
    }

    public void setMenuItemType(Long menuItemType) {
        this.menuItemType = menuItemType;
    }

    public Long getUpperId() {
        return this.upperId;
    }

    public void setUpperId(Long upperId) {
        this.upperId = upperId;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
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

    public Long getUpdater() {
        return this.updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public org.basuyi.xflow.model.Menu getMenu() {
        return this.menu;
    }

    public void setMenu(org.basuyi.xflow.model.Menu menu) {
        this.menu = menu;
    }

    public Set getMenuItemAuths() {
        return this.menuItemAuths;
    }

    public void setMenuItemAuths(Set menuItemAuths) {
        this.menuItemAuths = menuItemAuths;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("menuItemId", getMenuItemId())
            .toString();
    }

}
