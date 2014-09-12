package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Authority implements Serializable {

    /** identifier field */
    private Long authId;

    /** persistent field */
    private String authDesc;

    /** persistent field */
    private String authCode;

    /** nullable persistent field */
    private Long authType;

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
    private Set postAuthorities;

    /** persistent field */
    private Set orgAuthorities;

    /** persistent field */
    private Set userAuthorities;

    /** persistent field */
    private Set menuItemAuths;

    /** full constructor */
    public Authority(Long authId, String authDesc, String authCode, Long authType, Long creator, Date createDate, Date startDate, Date endDate, Date updateDate, Long updater, Set postAuthorities, Set orgAuthorities, Set userAuthorities, Set menuItemAuths) {
        this.authId = authId;
        this.authDesc = authDesc;
        this.authCode = authCode;
        this.authType = authType;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
        this.updater = updater;
        this.postAuthorities = postAuthorities;
        this.orgAuthorities = orgAuthorities;
        this.userAuthorities = userAuthorities;
        this.menuItemAuths = menuItemAuths;
    }

    /** default constructor */
    public Authority() {
    }

    /** minimal constructor */
    public Authority(Long authId, String authDesc, String authCode, Set postAuthorities, Set orgAuthorities, Set userAuthorities, Set menuItemAuths) {
        this.authId = authId;
        this.authDesc = authDesc;
        this.authCode = authCode;
        this.postAuthorities = postAuthorities;
        this.orgAuthorities = orgAuthorities;
        this.userAuthorities = userAuthorities;
        this.menuItemAuths = menuItemAuths;
    }

    public Long getAuthId() {
        return this.authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getAuthDesc() {
        return this.authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Long getAuthType() {
        return this.authType;
    }

    public void setAuthType(Long authType) {
        this.authType = authType;
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

    public Set getPostAuthorities() {
        return this.postAuthorities;
    }

    public void setPostAuthorities(Set postAuthorities) {
        this.postAuthorities = postAuthorities;
    }

    public Set getOrgAuthorities() {
        return this.orgAuthorities;
    }

    public void setOrgAuthorities(Set orgAuthorities) {
        this.orgAuthorities = orgAuthorities;
    }

    public Set getUserAuthorities() {
        return this.userAuthorities;
    }

    public void setUserAuthorities(Set userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public Set getMenuItemAuths() {
        return this.menuItemAuths;
    }

    public void setMenuItemAuths(Set menuItemAuths) {
        this.menuItemAuths = menuItemAuths;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("authId", getAuthId())
            .toString();
    }

}
