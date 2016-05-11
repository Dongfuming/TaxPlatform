package com.company.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * BaseDao接口
 * @author Dongfuming
 * @date 2016-5-9 上午11:02:32
 */
public interface BaseDao<T> {

	public void save(T entity);

	public void update(T entity);

	public void delete(Serializable id);

	public T findObjectById(Serializable id);

	public List<T> findObjects();
}
