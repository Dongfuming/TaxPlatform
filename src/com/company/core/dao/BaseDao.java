package com.company.core.dao;

import java.io.Serializable;
import java.util.List;

import com.company.core.page.PageResult;
import com.company.core.util.QueryHelper;

/**
 * BaseDao接口，增、删、改、查
 * @author Dongfuming
 * @date 2016-5-9 上午11:02:32
 */
public interface BaseDao<T> {

	public void save(T entity);

	public void update(T entity);

	public void delete(Serializable id);

	public T findObjectById(Serializable id);

	public List<T> findObjects();
	
	public List<T> findObjects(String hql, List<Object> parameters);
	
	public List<T> findObjects(QueryHelper queryHelper);
	
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);
}
