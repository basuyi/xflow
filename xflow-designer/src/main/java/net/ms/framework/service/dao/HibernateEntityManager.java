package net.ms.framework.service.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateEntityManager<T> extends HibernateDaoSupport implements EntityManager<T> {

	private static final long serialVersionUID = 1L;

	public void saveOrUpdate(T object) {
		this.getHibernateTemplate().saveOrUpdate(object);
	}
	
	public void update(T entity) {
		this.getHibernateTemplate().update((Object)entity);
	}
	
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	public void remove(T entity) {
		this.getHibernateTemplate().delete((Object)entity);
	}

	public List<T> load(Class<T> clsName) {
		return this.getHibernateTemplate().loadAll(clsName);
	}
	
	public List<T> find(T entity) {
		return this.getHibernateTemplate().findByExample(entity);
	}
	
	public T get(Class cls, Serializable id) {
		return (T)this.getHibernateTemplate().get(cls, id);
	}
	
	public List<T> find(String queryString) {
		return this.getHibernateTemplate().find(queryString);
	}
	
	public List<T> find(String queryString, Map<String, Object> params) {
		Set entries = params.entrySet();
		String [] names = new String[params.size()];
		Object [] values = new Object[params.size()];
		
		int i = 0;
		for (Iterator iterator = entries.iterator(); iterator.hasNext(); i++) {
			String name = (String)iterator.next();
			Object value = params.get(name);
			names[i] = name;
			values[i] = value;
		}
		return this.getHibernateTemplate().findByNamedParam(queryString, names, values);
	}
}