package net.ms.designer.projectbuilder.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 工作流服务接口
 * 
 * @author mashuai
 * @version 0.1
 * @date 2009-12-05
 */
public interface ProjectBuilderDAO<T> {
	public void saveEntity(T entity);
	public List<T> getEntityByModel(T model);
	public T getEntityById(Class<T> cls, Serializable id);
	public void removeEntity(T entity);
}
