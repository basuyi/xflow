package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Menu implements Serializable {

    /** identifier field */
    private Long menuId;

    /** nullable persistent field */
    private String menuName;

    /** nullable persistent field */
    private String menuDesc;

    /** nullable persistent field */
    private Long menuType;

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
    private Set menuItems;

    /** full constructor */
    public Menu(Long menuId, String menuName, String menuDesc, Long menuType, Long creator, Date createDate, Date startDate, Date endDate, Date updateDate, Long updater, Set menuItems) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuDesc = menuDesc;
        this.menuType = menuType;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
        this.updater = updater;
        this.menuItems = menuItems;
    }

    /** default constructor */
    public Menu() {
    }

    /** minimal constructor */
    public Menu(Long menuId, Set menuItems) {
        this.menuId = menuId;
        this.menuItems = menuItems;
    }

    public Long getMenuId() {
        return this.menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDesc() {
        return this.menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public Long getMenuType() {
        return this.menuType;
    }

    public void setMenuType(Long menuType) {
        this.menuType = menuType;
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

    public Set getMenuItems() {
        return this.menuItems;
    }

    public void setMenuItems(Set menuItems) {
        this.menuItems = menuItems;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("menuId", getMenuId())
            .toString();
    }

}
