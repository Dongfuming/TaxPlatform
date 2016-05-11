package com.company.tax.user.dao;

import java.util.List;

import com.company.core.dao.BaseDao;
import com.company.tax.user.entity.User;

/**
 * UserDao接口
 * @author Dongfuming
 * @date 2016-5-9 上午11:26:17
 */
public interface UserDao extends BaseDao<User> {
	public List<User> findUserByAccountAndId(String id, String account);
}
