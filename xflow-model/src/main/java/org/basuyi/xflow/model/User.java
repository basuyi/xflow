package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class User implements Serializable {

    /** identifier field */
    private String userId;

    /** persistent field */
    private String userName;

    /** nullable persistent field */
    private Long userType;

    /** nullable persistent field */
    private String loginId;

    /** persistent field */
    private String password;

    /** nullable persistent field */
    private Date startDate;

    /** nullable persistent field */
    private Date endDate;

    /** persistent field */
    private Date createDate;

    /** persistent field */
    private String creator;

    /** persistent field */
    private Date updateDate;

    /** persistent field */
    private String updater;

    /** persistent field */
    private org.basuyi.xflow.model.Organize organize;

    /** persistent field */
    private Set userPosts;

    /** persistent field */
    private Set userAuthorities;

    /** full constructor */
    public User(String userId, String userName, Long userType, String loginId, String password, Date startDate, Date endDate, Date createDate, String creator, Date updateDate, String updater, org.basuyi.xflow.model.Organize organize, Set userPosts, Set userAuthorities) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.loginId = loginId;
        this.password = password;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = createDate;
        this.creator = creator;
        this.updateDate = updateDate;
        this.updater = updater;
        this.organize = organize;
        this.userPosts = userPosts;
        this.userAuthorities = userAuthorities;
    }

    /** default constructor */
    public User() {
    }

    /** minimal constructor */
    public User(String userId, String userName, String password, Date createDate, String creator, Date updateDate, String updater, org.basuyi.xflow.model.Organize organize, Set userPosts, Set userAuthorities) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.creator = creator;
        this.updateDate = updateDate;
        this.updater = updater;
        this.organize = organize;
        this.userPosts = userPosts;
        this.userAuthorities = userAuthorities;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserType() {
        return this.userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public String getLoginId() {
        return this.loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdater() {
        return this.updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public org.basuyi.xflow.model.Organize getOrganize() {
        return this.organize;
    }

    public void setOrganize(org.basuyi.xflow.model.Organize organize) {
        this.organize = organize;
    }

    public Set getUserPosts() {
        return this.userPosts;
    }

    public void setUserPosts(Set userPosts) {
        this.userPosts = userPosts;
    }

    public Set getUserAuthorities() {
        return this.userAuthorities;
    }

    public void setUserAuthorities(Set userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof User) ) return false;
        User castOther = (User) other;
        return new EqualsBuilder()
            .append(this.getUserId(), castOther.getUserId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserId())
            .toHashCode();
    }

}
