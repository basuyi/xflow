package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ComAttr implements Serializable {

    /** identifier field */
    private Long comAttrId;

    /** nullable persistent field */
    private String comAttrCode;

    /** nullable persistent field */
    private String comAttrName;

    /** nullable persistent field */
    private String comAttrDesc;

    /** nullable persistent field */
    private Long comAttrType;

    /** nullable persistent field */
    private String defaultValue;

    /** nullable persistent field */
    private Long comAttrLength;

    /** nullable persistent field */
    private Long isKey;


    /** nullable persistent field */
    private net.ms.designer.projectbuilder.model.ComDetail comDetail;

    /** full constructor */
    public ComAttr(String comAttrCode, String comAttrName, String comAttrDesc, Long comAttrType, String defaultValue, net.ms.designer.projectbuilder.model.ComDetail comDetail) {
        this.comAttrCode = comAttrCode;
        this.comAttrName = comAttrName;
        this.comAttrDesc = comAttrDesc;
        this.comAttrType = comAttrType;
        this.defaultValue = defaultValue;
        this.comDetail = comDetail;
    }

    /** default constructor */
    public ComAttr() {
    }

    public Long getComAttrId() {
        return this.comAttrId;
    }

    public void setComAttrId(Long comAttrId) {
        this.comAttrId = comAttrId;
    }

    public String getComAttrCode() {
        return this.comAttrCode;
    }

    public void setComAttrCode(String comAttrCode) {
        this.comAttrCode = comAttrCode;
    }

    public String getComAttrName() {
        return this.comAttrName;
    }

    public void setComAttrName(String comAttrName) {
        this.comAttrName = comAttrName;
    }

    public String getComAttrDesc() {
        return this.comAttrDesc;
    }

    public void setComAttrDesc(String comAttrDesc) {
        this.comAttrDesc = comAttrDesc;
    }

    public Long getComAttrType() {
        return this.comAttrType;
    }

    public void setComAttrType(Long comAttrType) {
        this.comAttrType = comAttrType;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public net.ms.designer.projectbuilder.model.ComDetail getComDetail() {
        return this.comDetail;
    }

    public void setComDetail(net.ms.designer.projectbuilder.model.ComDetail comDetail) {
        this.comDetail = comDetail;
    }
    public Long getComAttrLength() {
        return this.comAttrLength;
    }

    public void setComAttrLength(Long comAttrLength) {
        this.comAttrLength = comAttrLength;
    }

    public Long getIsKey() {
        return this.isKey;
    }

    public void setIsKey(Long isKey) {
        this.isKey = isKey;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("comAttrId", getComAttrId())
            .toString();
    }

}
