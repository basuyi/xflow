package net.ms.designer.projectbuilder.service;

import net.ms.designer.projectbuilder.dao.ProjectBuilderDAO;

public interface DBProjectBuilderDaoAware {
	public void setServiceTx(ProjectBuilderDAO dao);
}
