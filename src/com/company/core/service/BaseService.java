package com.company.core.service;

import java.io.Serializable;
import java.util.List;

import com.company.core.page.PageResult;
import com.company.core.util.QueryHelper;


/**
 * @author Dongfuming
 * @date 2016-5-16 上午11:33:12
 */
public interface BaseService<T> {

	public void save(T entity);

	public void update(T entity);

	public void delete(Serializable id);

	public T findObjectById(Serializable id);

	public List<T> findObjects();
	
	public List<T> findObjects(String hql, List<Object> parameters);
	
	public List<T> findObjects(QueryHelper queryHelper);
	
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);
}
