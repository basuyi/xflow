package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Position implements Serializable {

    /** identifier field */
    private Long positionId;

    /** nullable persistent field */
    private Long positionX;

    /** nullable persistent field */
    private Long positionY;

    /** full constructor */
    public Position(Long positionX, Long positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /** default constructor */
    public Position() {
    }

    public Long getPositionId() {
        return this.positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getPositionX() {
        return this.positionX;
    }

    public void setPositionX(Long positionX) {
        this.positionX = positionX;
    }

    public Long getPositionY() {
        return this.positionY;
    }

    public void setPositionY(Long positionY) {
        this.positionY = positionY;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("positionId", getPositionId())
            .toString();
    }

}
