package com.company.tax.user.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import com.company.core.dao.impl.BaseDaoImpl;
import com.company.tax.user.dao.UserDao;
import com.company.tax.user.entity.User;
import com.company.tax.user.entity.UserRole;

/**
 * UserDao实现类
 * @author Dongfuming
 * @date 2016-5-9 上午11:27:41
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		String hql = "FROM User WHERE account = ?";
		if(StringUtils.isNotBlank(id)){
			hql += " AND id != ?";
		}
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, account);
		if(StringUtils.isNotBlank(id)){
			query.setParameter(1, id);
		}
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		this.getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRolesByUserId(String userId) {
		String hql = "DELETE FROM UserRole WHERE compositeUserRole.userId = ?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, userId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findUserRolesByUserId(String userId) {
		String hql = "FROM UserRole WHERE compositeUserRole.userId = ?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, userId);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersByAccountAndPassword(String account,
			String password) {
		String hql = "FROM User WHERE account = ? AND password = ?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, account);
		query.setParameter(1, password);
		
		return query.list();
	}
}
