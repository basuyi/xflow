package net.ms.designer.projectbuilder.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ComDetailPosition extends Position implements Serializable {

	private Long comDetailId;

	public Long getComDetailId() {
		return comDetailId;
	}

	public void setComDetailId(Long comDetailId) {
		this.comDetailId = comDetailId;
	}

}
