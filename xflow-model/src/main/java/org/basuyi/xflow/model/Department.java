package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Department implements Serializable {

    /** identifier field */
    private Long deptId;

    /** persistent field */
    private String deptName;

    /** nullable persistent field */
    private String deptCode;

    /** nullable persistent field */
    private String deptDesc;

    /** nullable persistent field */
    private Long deptLevel;

    /** nullable persistent field */
    private Long deptType;

    /** nullable persistent field */
    private Long manager;

    /** nullable persistent field */
    private String upperDeptId;

    /** full constructor */
    public Department(Long deptId, String deptName, String deptCode, String deptDesc, Long deptLevel, Long deptType, Long manager, String upperDeptId) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.deptDesc = deptDesc;
        this.deptLevel = deptLevel;
        this.deptType = deptType;
        this.manager = manager;
        this.upperDeptId = upperDeptId;
    }

    /** default constructor */
    public Department() {
    }

    /** minimal constructor */
    public Department(Long deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return this.deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptDesc() {
        return this.deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public Long getDeptLevel() {
        return this.deptLevel;
    }

    public void setDeptLevel(Long deptLevel) {
        this.deptLevel = deptLevel;
    }

    public Long getDeptType() {
        return this.deptType;
    }

    public void setDeptType(Long deptType) {
        this.deptType = deptType;
    }

    public Long getManager() {
        return this.manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    public String getUpperDeptId() {
        return this.upperDeptId;
    }

    public void setUpperDeptId(String upperDeptId) {
        this.upperDeptId = upperDeptId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("deptId", getDeptId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof Department) ) return false;
        Department castOther = (Department) other;
        return new EqualsBuilder()
            .append(this.getDeptId(), castOther.getDeptId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDeptId())
            .toHashCode();
    }

}
