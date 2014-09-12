package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Package implements Serializable {

    /** identifier field */
    private Long packageId;

    /** nullable persistent field */
    private String packageCode;

    /** nullable persistent field */
    private String packageName;

    /** nullable persistent field */
    private String packageDesc;

    /** nullable persistent field */
    private net.ms.designer.projectbuilder.model.Project project;

    /** persistent field */
    private Set components;
    
    private PackagePosition packagePosition;

    /** full constructor */
    public Package(String packageCode, String packageName, String packageDesc, net.ms.designer.projectbuilder.model.Project project, Set components) {
        this.packageCode = packageCode;
        this.packageName = packageName;
        this.packageDesc = packageDesc;
        this.project = project;
        this.components = components;
    }

    /** default constructor */
    public Package() {
    }

    /** minimal constructor */
    public Package(Set components) {
        this.components = components;
    }

    public Long getPackageId() {
        return this.packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getPackageCode() {
        return this.packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageDesc() {
        return this.packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }

    public net.ms.designer.projectbuilder.model.Project getProject() {
        return this.project;
    }

    public void setProject(net.ms.designer.projectbuilder.model.Project project) {
        this.project = project;
    }

    public Set getComponents() {
        return this.components;
    }

    public void setComponents(Set components) {
        this.components = components;
    }

    /**
	 * @return the packagePositions
	 */
	public PackagePosition getPackagePosition() {
		return packagePosition;
	}

	/**
	 * @param packagePositions the packagePositions to set
	 */
	public void setPackagePosition(PackagePosition packagePosition) {
		this.packagePosition = packagePosition;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("packageId", getPackageId())
            .toString();
    }

}
