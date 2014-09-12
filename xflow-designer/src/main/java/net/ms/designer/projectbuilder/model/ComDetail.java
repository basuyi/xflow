package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ComDetail implements Serializable {

    /** identifier field */
    private Long comDetailId;

    /** nullable persistent field */
    private String comDetailCode;

    /** nullable persistent field */
    private String comDetailName;

    /** nullable persistent field */
    private String comDetailDesc;

    /** nullable persistent field */
    private Long comDetailType;

    /** nullable persistent field */
    private net.ms.designer.projectbuilder.model.Component component;

    /** persistent field */
    private ComDetailPosition comDetailPosition;

    /** persistent field */
    private Set comAttrs;

    /** full constructor */
    public ComDetail(String comDetailCode, String comDetailName, String comDetailDesc, Long comDetailType, net.ms.designer.projectbuilder.model.Component component, ComDetailPosition comDetailPosition, Set comAttrs) {
        this.comDetailCode = comDetailCode;
        this.comDetailName = comDetailName;
        this.comDetailDesc = comDetailDesc;
        this.comDetailType = comDetailType;
        this.component = component;
        this.comDetailPosition = comDetailPosition;
        this.comAttrs = comAttrs;
    }

    /** default constructor */
    public ComDetail() {
    }

    /** minimal constructor */
    public ComDetail(ComDetailPosition comDetailPosition, Set comAttrs) {
        this.comDetailPosition = comDetailPosition;
        this.comAttrs = comAttrs;
    }

    public Long getComDetailId() {
        return this.comDetailId;
    }

    public void setComDetailId(Long comDetailId) {
        this.comDetailId = comDetailId;
    }

    public String getComDetailCode() {
        return this.comDetailCode;
    }

    public void setComDetailCode(String comDetailCode) {
        this.comDetailCode = comDetailCode;
    }

    public String getComDetailName() {
        return this.comDetailName;
    }

    public void setComDetailName(String comDetailName) {
        this.comDetailName = comDetailName;
    }

    public String getComDetailDesc() {
        return this.comDetailDesc;
    }

    public void setComDetailDesc(String comDetailDesc) {
        this.comDetailDesc = comDetailDesc;
    }

    public Long getComDetailType() {
        return this.comDetailType;
    }

    public void setComDetailType(Long comDetailType) {
        this.comDetailType = comDetailType;
    }

    public net.ms.designer.projectbuilder.model.Component getComponent() {
        return this.component;
    }

    public void setComponent(net.ms.designer.projectbuilder.model.Component component) {
        this.component = component;
    }

    public ComDetailPosition getComDetailPosition() {
        return this.comDetailPosition;
    }

    public void setComDetailPosition(ComDetailPosition comDetailPosition) {
        this.comDetailPosition = comDetailPosition;
    }

    public Set getComAttrs() {
        return this.comAttrs;
    }

    public void setComAttrs(Set comAttrs) {
        this.comAttrs = comAttrs;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comDetailId", getComDetailId())
            .toString();
    }

}
