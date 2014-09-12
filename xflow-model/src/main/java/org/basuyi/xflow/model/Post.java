package org.basuyi.xflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Post implements Serializable {

    /** identifier field */
    private Long postId;

    /** nullable persistent field */
    private String postName;

    /** nullable persistent field */
    private String postDesc;

    /** nullable persistent field */
    private String postCode;

    /** nullable persistent field */
    private Long postType;

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
    private Set userPosts;

    /** full constructor */
    public Post(Long postId, String postName, String postDesc, String postCode, Long postType, Long creator, Date createDate, Date startDate, Date endDate, Date updateDate, Long updater, Set postAuthorities, Set userPosts) {
        this.postId = postId;
        this.postName = postName;
        this.postDesc = postDesc;
        this.postCode = postCode;
        this.postType = postType;
        this.creator = creator;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
        this.updater = updater;
        this.postAuthorities = postAuthorities;
        this.userPosts = userPosts;
    }

    /** default constructor */
    public Post() {
    }

    /** minimal constructor */
    public Post(Long postId, Set postAuthorities, Set userPosts) {
        this.postId = postId;
        this.postAuthorities = postAuthorities;
        this.userPosts = userPosts;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return this.postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostDesc() {
        return this.postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Long getPostType() {
        return this.postType;
    }

    public void setPostType(Long postType) {
        this.postType = postType;
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

    public Set getUserPosts() {
        return this.userPosts;
    }

    public void setUserPosts(Set userPosts) {
        this.userPosts = userPosts;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("postId", getPostId())
            .toString();
    }

}
