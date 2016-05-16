package com.company.tax.role.service;

import java.io.Serializable;
import java.util.List;

import com.company.core.service.BaseService;
import com.company.tax.role.entity.Role;

/**
 * @author Dongfuming
 * @date 2016-5-11 下午3:29:23
 */
public interface RoleService extends BaseService<Role> { 

	public void save(Role role);

	public void update(Role role);

	public void delete(Serializable roleId);

	public Role findRoleById(Serializable roleId);
	
	public List<Role> findRoles();
}
