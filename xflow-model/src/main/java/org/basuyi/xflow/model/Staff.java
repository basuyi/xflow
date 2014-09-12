package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Staff implements Serializable {

    /** identifier field */
    private Long staffId;

    /** nullable persistent field */
    private String staffCode;

    /** nullable persistent field */
    private Long supervisor;

    /** full constructor */
    public Staff(Long staffId, String staffCode, Long supervisor) {
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.supervisor = supervisor;
    }

    /** default constructor */
    public Staff() {
    }

    /** minimal constructor */
    public Staff(Long staffId) {
        this.staffId = staffId;
    }

    public Long getStaffId() {
        return this.staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return this.staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Long getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("staffId", getStaffId())
            .toString();
    }

}
