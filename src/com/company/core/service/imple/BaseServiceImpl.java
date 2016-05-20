package com.company.core.service.imple;

import java.io.Serializable;
import java.util.List;

import com.company.core.dao.BaseDao;
import com.company.core.page.PageResult;
import com.company.core.service.BaseService;
import com.company.core.util.QueryHelper;

/**
 * 增、删、改、查的业务操作实现
 * @author Dongfuming
 * @date 2016-5-16 上午11:34:45
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao; // 通过子类注入
	
	public void setBaseDao(BaseDao<T> baseDao) { // !!! 子类实例化时必须调用此方法
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

	@Override
	public List<T> findObjects(String hql, List<Object> parameters) {
		return baseDao.findObjects(hql, parameters);
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		return baseDao.findObjects(queryHelper);
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo,
			int pageSize) {
		return baseDao.getPageResult(queryHelper, pageNo, pageSize);
	}
}
