package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserActivity extends Activity implements Serializable {

//    /** identifier field */
//    private Long userActivityId;

    /** nullable persistent field */
    private Long policyType;

    /** nullable persistent field */
    private Long postId;

    /** nullable persistent field */
    private Long userId;

    /** nullable persistent field */
    private Long orgId;

    /** nullable persistent field */
    private Long entryType;

    /** nullable persistent field */
    private String entryValue;

    /** full constructor */
//    public UserAcitivity(Long userActivityId, Long policyType, Long postId, Long userId, Long orgId, Long entryType, String entryValue) {
//        this.userActivityId = userActivityId;
//        this.policyType = policyType;
//        this.postId = postId;
//        this.userId = userId;
//        this.orgId = orgId;
//        this.entryType = entryType;
//        this.entryValue = entryValue;
//    }

    /** default constructor */
    public UserActivity() {
    }

//    /** minimal constructor */
//    public UserActivity(Long userActivityId) {
//        this.userActivityId = userActivityId;
//    }

//    public Long getUserActivityId() {
//        return this.userActivityId;
//    }

//    public void setUserActivityId(Long userActivityId) {
//        this.userActivityId = userActivityId;
//    }

    public Long getPolicyType() {
        return this.policyType;
    }

    public void setPolicyType(Long policyType) {
        this.policyType = policyType;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getEntryType() {
        return this.entryType;
    }

    public void setEntryType(Long entryType) {
        this.entryType = entryType;
    }

    public String getEntryValue() {
        return this.entryValue;
    }

    public void setEntryValue(String entryValue) {
        this.entryValue = entryValue;
    }

//    public String toString() {
//        return new ToStringBuilder(this)
//            .append("userActivityId", getUserActivityId())
//            .toString();
//    }

}
