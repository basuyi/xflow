package org.basuyi.xflow.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Parameter implements Serializable {

    /** identifier field */
    private Long paramId;

    /** nullable persistent field */
    private String paramCode;

    /** nullable persistent field */
    private String paramName;

    /** nullable persistent field */
    private String paramDesc;

    /** nullable persistent field */
    private Long paramType;

    /** nullable persistent field */
    private String paramClass;

    /** full constructor */
    public Parameter(Long paramId, String paramCode, String paramName, String paramDesc, Long paramType, String paramClass) {
        this.paramId = paramId;
        this.paramCode = paramCode;
        this.paramName = paramName;
        this.paramDesc = paramDesc;
        this.paramType = paramType;
        this.paramClass = paramClass;
    }

    /** default constructor */
    public Parameter() {
    }

    /** minimal constructor */
    public Parameter(Long paramId) {
        this.paramId = paramId;
    }

    public Long getParamId() {
        return this.paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getParamCode() {
        return this.paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamName() {
        return this.paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDesc() {
        return this.paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public Long getParamType() {
        return this.paramType;
    }

    public void setParamType(Long paramType) {
        this.paramType = paramType;
    }

    public String getParamClass() {
        return this.paramClass;
    }

    public void setParamClass(String paramClass) {
        this.paramClass = paramClass;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("paramId", getParamId())
            .toString();
    }

}
