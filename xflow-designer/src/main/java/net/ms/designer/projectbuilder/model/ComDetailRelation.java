package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ComDetailRelation implements Serializable {

    /** identifier field */
    private Long comDetailRelationId;

    /** nullable persistent field */
    private Long fromComDetailId;

    /** nullable persistent field */
    private Long toComDetailId;

    /** nullable persistent field */
    private Long relationType;

    /** full constructor */
    public ComDetailRelation(Long comDetailRelationId, Long fromComDetailId, Long toComDetailId, Long relationType) {
        this.comDetailRelationId = comDetailRelationId;
        this.fromComDetailId = fromComDetailId;
        this.toComDetailId = toComDetailId;
        this.relationType = relationType;
    }

    /** default constructor */
    public ComDetailRelation() {
    }

    /** minimal constructor */
    public ComDetailRelation(Long comDetailRelationId) {
        this.comDetailRelationId = comDetailRelationId;
    }

    public Long getComDetailRelationId() {
        return this.comDetailRelationId;
    }

    public void setComDetailRelationId(Long comDetailRelationId) {
        this.comDetailRelationId = comDetailRelationId;
    }

    public Long getFromComDetailId() {
        return this.fromComDetailId;
    }

    public void setFromComDetailId(Long fromComDetailId) {
        this.fromComDetailId = fromComDetailId;
    }

    public Long getToComDetailId() {
        return this.toComDetailId;
    }

    public void setToComDetailId(Long toComDetailId) {
        this.toComDetailId = toComDetailId;
    }

    public Long getRelationType() {
        return this.relationType;
    }

    public void setRelationType(Long relationType) {
        this.relationType = relationType;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comDetailRelationId", getComDetailRelationId())
            .toString();
    }

}
