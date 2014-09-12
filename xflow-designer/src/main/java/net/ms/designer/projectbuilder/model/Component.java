package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Component implements Serializable {

    /** identifier field */
    private Long componentId;

    /** nullable persistent field */
    private String componentCode;

    /** nullable persistent field */
    private String componentName;

    /** nullable persistent field */
    private String componentDesc;

    /** nullable persistent field */
    private net.ms.designer.projectbuilder.model.Package comPackage;

    /** persistent field */
    private Set comWorkflows;

    /** persistent field */
    private ComPosition comPosition;

    /** persistent field */
    private Set comDetials;

    /** full constructor */
    public Component(String componentCode, String componentName, String componentDesc, net.ms.designer.projectbuilder.model.Package comPackage, Set comWorkflows, ComPosition comPosition, Set comDetials) {
        this.componentCode = componentCode;
        this.componentName = componentName;
        this.componentDesc = componentDesc;
        this.comPackage = comPackage;
        this.comWorkflows = comWorkflows;
        this.comPosition = comPosition;
        this.comDetials = comDetials;
    }

    /** default constructor */
    public Component() {
    }

    /** minimal constructor */
    public Component(Set comWorkflows, ComPosition comPosition, Set comDetials) {
        this.comWorkflows = comWorkflows;
        this.comPosition = comPosition;
        this.comDetials = comDetials;
    }

    public Long getComponentId() {
        return this.componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public String getComponentCode() {
        return this.componentCode;
    }

    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentDesc() {
        return this.componentDesc;
    }

    public void setComponentDesc(String componentDesc) {
        this.componentDesc = componentDesc;
    }

    public net.ms.designer.projectbuilder.model.Package getComPackage() {
        return this.comPackage;
    }

    public void setComPackage(net.ms.designer.projectbuilder.model.Package comPackage) {
        this.comPackage = comPackage;
    }

    public Set getComWorkflows() {
        return this.comWorkflows;
    }

    public void setComWorkflows(Set comWorkflows) {
        this.comWorkflows = comWorkflows;
    }

    public ComPosition getComPosition() {
        return this.comPosition;
    }

    public void setComPosition(ComPosition comPosition) {
        this.comPosition = comPosition;
    }

    public Set getComDetials() {
        return this.comDetials;
    }

    public void setComDetials(Set comDetials) {
        this.comDetials = comDetials;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("componentId", getComponentId())
            .toString();
    }

}
