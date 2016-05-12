
package com.company.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.company.core.dao.BaseDao;

/**
 * BaseDao实现类
 * @author Dongfuming
 * @date 2016-5-9 上午11:11:07
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		// 使用反射得到 T 的真实类型
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class<T>)type.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}
	
	@Override
	public void delete(Serializable id) {
		T entity = findObjectById(id);
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public T findObjectById(Serializable id) {
		T object = this.getHibernateTemplate().get(clazz, id);
		return object;
	}

	@Override
	public List<T> findObjects() {
		Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
		List<T> list = query.list();
		return list;
	}

}
