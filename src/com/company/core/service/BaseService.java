package com.company.core.service;

import java.io.Serializable;
import java.util.List;


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
}
