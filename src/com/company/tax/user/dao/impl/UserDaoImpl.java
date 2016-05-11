package com.company.tax.user.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.company.core.dao.impl.BaseDaoImpl;
import com.company.tax.user.dao.UserDao;
import com.company.tax.user.entity.User;

/**
 * UserDao实现类
 * @author Dongfuming
 * @date 2016-5-9 上午11:27:41
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		System.out.println("xxx id = " + id + ", account = " + account);
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
}
