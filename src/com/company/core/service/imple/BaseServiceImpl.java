package com.company.core.service.imple;

import java.io.Serializable;
import java.util.List;

import com.company.core.dao.BaseDao;
import com.company.core.service.BaseService;

/**
 * @author Dongfuming
 * @date 2016-5-16 上午11:34:45
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao; // 通过子类注入
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public T findObjectById(Serializable id) {
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
		return baseDao.findObjects();
	}

}
