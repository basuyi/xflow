package org.basuyi.xflow.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface EntityManager<T> extends Serializable {
	public void saveOrUpdate(T object);
	
	public void update(T entity);
	
	public void save(T entity);
	
	public void remove(T entity);

	public T get(Class<T> cls, Serializable id);
	
	public List<T> load(Class<T> clsName);
	
	public List<T> find(T entity);
	
	public List<T> find(String queryString);
	
	public List<T> find(String queryString, Map<String, Object> params);
	
	public List<T> find4Page(Serializable criteria, int firstResult, int maxResults);
	
	public List<T> findByQBC(Serializable criteria);
}