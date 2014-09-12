package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Project implements Serializable {

    /** identifier field */
    private Long projectId;

    /** nullable persistent field */
    private String projectCode;

    /** nullable persistent field */
    private String projectName;

    /** nullable persistent field */
    private String projectDesc;

    /** nullable persistent field */
    private Long projectType;

    /** nullable persistent field */
    private String manager;

    /** nullable persistent field */
    private Long creator;

    /** nullable persistent field */
    private Date createDate;

    /** nullable persistent field */
    private Long status;

    /** nullable persistent field */
    private Date startDate;

    /** nullable persistent field */
    private Date finishDate;

    /** nullable persistent field */
    private String remark;

    /** nullable persistent field */
    private net.ms.designer.projectbuilder.model.Company company;

    /** persistent field */
    private Set packages;

    /** full constructor */
    public Project(String projectCode, String projectName, String projectDesc, Long projectType, String manager, Long creator, Date createDate, Long status, Date startDate, Date finishDate, String remark, net.ms.designer.projectbuilder.model.Company company, Set packages) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectType = projectType;
        this.manager = manager;
        this.creator = creator;
        this.createDate = createDate;
        this.status = status;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.remark = remark;
        this.company = company;
        this.packages = packages;
    }

    /** default constructor */
    public Project() {
    }

    /** minimal constructor */
    public Project(Set packages) {
        this.packages = packages;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return this.projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Long getProjectType() {
        return this.projectType;
    }

    public void setProjectType(Long projectType) {
        this.projectType = projectType;
    }

    public String getManager() {
        return this.manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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

    public Long getStatus() {
        return this.status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return this.finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public net.ms.designer.projectbuilder.model.Company getCompany() {
        return this.company;
    }

    public void setCompany(net.ms.designer.projectbuilder.model.Company company) {
        this.company = company;
    }

    public Set getPackages() {
        return this.packages;
    }

    public void setPackages(Set packages) {
        this.packages = packages;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("projectId", getProjectId())
            .toString();
    }

}
