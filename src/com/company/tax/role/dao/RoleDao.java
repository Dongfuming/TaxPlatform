package com.company.tax.role.dao;

import com.company.core.dao.BaseDao;
import com.company.tax.role.entity.Role;

/**
 * @author Dongfuming
 * @date 2016-5-11 下午3:21:25
 */
public interface RoleDao extends BaseDao<Role> {
	
	// 删除该角色对应的所有权限
	public void deletePrivilegeByRoleId(String roleId);
}
