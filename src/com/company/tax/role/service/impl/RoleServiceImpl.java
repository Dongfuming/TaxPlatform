package com.company.tax.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.tax.role.dao.RoleDao;
import com.company.tax.role.entity.Role;
import com.company.tax.role.service.RoleService;

/**
 * @author Dongfuming
 * @date 2016-5-11 下午3:31:41
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}
	
	@Override
	public void update(Role role) {
		// 删除该角色对应的所有权限
		roleDao.deletePrivilegeByRoleId(role.getId());
		// 更新角色及其权限
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable roleId) {
		roleDao.delete(roleId);
	}

	@Override
	public Role findRoleById(Serializable roleId) {
		return roleDao.findObjectById(roleId);
	}

	@Override
	public List<Role> findRoles() {
		return roleDao.findObjects();
	}

}
