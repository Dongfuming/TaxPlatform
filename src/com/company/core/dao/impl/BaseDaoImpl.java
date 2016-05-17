
package com.company.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.company.core.dao.BaseDao;
import com.company.core.page.PageResult;
import com.company.core.util.QueryHelper;

/**
 * BaseDao实现类
 * @author Dongfuming
 * @date 2016-5-9 上午11:11:07
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findObjects() {
		Query query = this.getSession().createQuery("FROM " + clazz.getSimpleName());
		List<T> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findObjects(String hql, List<Object> parameters) {
		Query query = this.getSession().createQuery(hql);
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		String hql = queryHelper.getQueryListHql();
		Query query = this.getSession().createQuery(hql);
		List<Object> parameters = queryHelper.getParameters();
		if (parameters != null) {
			for (int i = 0; i < parameters.size();i ++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo,
			int pageSize) {
		// 查询分页里列表数据
		String hql = queryHelper.getQueryListHql();
		System.out.println("查询分页列表的hql = " + hql);
		
		Query query = this.getSession().createQuery(hql);
		List<Object> parameters = queryHelper.getParameters();
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
//		if (pageNo < 1) {
//			pageNo = 1;
//		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Object> items = query.list();
		
		// 查询总条数
		hql = queryHelper.getQueryCountHql();
		System.out.println("查询总条数的hql = " + hql);
		query = null;
		query = this.getSession().createQuery(hql);
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		long totalCount = (Long)query.uniqueResult();

		return new PageResult(totalCount, pageNo, pageSize, items);
	}
}
