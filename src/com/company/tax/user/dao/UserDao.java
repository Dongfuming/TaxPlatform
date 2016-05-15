package com.company.tax.user.dao;

import java.util.List;

import com.company.core.dao.BaseDao;
import com.company.tax.user.entity.User;
import com.company.tax.user.entity.UserRole;

/**
 * UserDao接口
 * @author Dongfuming
 * @date 2016-5-9 上午11:26:17
 */
public interface UserDao extends BaseDao<User> {

	// 获取账号相同 但 id不同的用户
	public List<User> findUserByAccountAndId(String id, String account);
	
	// 保存用户角色
	public void saveUserRole(UserRole userRole);

	// 删除用户角色
	public void deleteUserRolesByUserId(String userId);
		
	// 查找用户的所有用户角色
	public List<UserRole> findUserRolesByUserId(String userId);
	
	public List<User> findUsersByAccountAndPassword(String account, String password);
}
