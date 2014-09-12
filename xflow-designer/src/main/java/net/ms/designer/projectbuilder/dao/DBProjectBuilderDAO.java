package net.ms.designer.projectbuilder.dao;

import java.io.Serializable;
import java.util.List;

import net.ms.framework.service.dao.EntityManager;

/**
 * 工作流服务对象
 * @author mashuai
 * @version 0.1
 * @date 2009-12-05
 */
public class DBProjectBuilderDAO<T> implements ProjectBuilderDAO<T> {
	public EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void saveEntity(T entity) {
		entityManager.saveOrUpdate(entity);
	}

	public List<T> getEntityByModel(T model) {
		// TODO Auto-generated method stub
		return entityManager.find(model);
	}

	public T getEntityById(Class<T> cls, Serializable id) {
		// TODO Auto-generated method stub
		return (T)entityManager.get(cls, id);
	}
	
	public void removeEntity(T entity) {
		entityManager.remove(entity);
	}
}
