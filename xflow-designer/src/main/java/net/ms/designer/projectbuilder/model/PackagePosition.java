package net.ms.designer.projectbuilder.model;

public class PackagePosition extends Position {

    /** nullable persistent field */
    private Long packageId;

    public Long getPackageId() {
        return this.packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }
}
