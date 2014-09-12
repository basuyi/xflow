package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Page implements Serializable {

    /** identifier field */
    private Long pageId;

    /** nullable persistent field */
    private String pageName;

    /** nullable persistent field */
    private String pageDesc;

    /** nullable persistent field */
    private String pageCode;

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

    /** full constructor */
    public Page(Long pageId, String pageName, String pageDesc, String pageCode, String pageUrl, Long creator, Date createDate, Date startDate, Date endDate, Long updater, Date updateDate) {
        this.pageId = pageId;
        this.pageName = pageName;
        this.pageDesc = pageDesc;
        this.pageCode = pageCode;
        this.pageUrl = pageUrl;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updater = updater;
        this.updateDate = updateDate;
    }

    /** default constructor */
    public Page() {
    }

    /** minimal constructor */
    public Page(Long pageId) {
        this.pageId = pageId;
    }

    public Long getPageId() {
        return this.pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageDesc() {
        return this.pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }

    public String getPageCode() {
        return this.pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("pageId", getPageId())
            .toString();
    }

}
