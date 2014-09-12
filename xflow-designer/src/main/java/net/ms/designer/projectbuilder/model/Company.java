package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Company implements Serializable {

    /** identifier field */
    private Long companyId;

    /** nullable persistent field */
    private String companyCode;

    /** nullable persistent field */
    private String companyName;

    /** nullable persistent field */
    private String companyDesc;

    /** nullable persistent field */
    private Long companyType;

    /** persistent field */
    private Set projects;

    /** full constructor */
    public Company(String companyCode, String companyName, String companyDesc, Long companyType, Set projects) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyDesc = companyDesc;
        this.companyType = companyType;
        this.projects = projects;
    }

    /** default constructor */
    public Company() {
    }

    /** minimal constructor */
    public Company(Set projects) {
        this.projects = projects;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDesc() {
        return this.companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public Long getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(Long companyType) {
        this.companyType = companyType;
    }

    public Set getProjects() {
        return this.projects;
    }

    public void setProjects(Set projects) {
        this.projects = projects;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("companyId", getCompanyId())
            .toString();
    }

}
