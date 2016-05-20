package com.company.tax.role.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.core.service.imple.BaseServiceImpl;
import com.company.tax.role.dao.RoleDao;
import com.company.tax.role.entity.Role;
import com.company.tax.role.service.RoleService;

/**
 * @author Dongfuming
 * @date 2016-5-11 下午3:31:41
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	private RoleDao roleDao;
	
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}
	
	/*
	 * 1. 删除该角色对应的所有权限
	 * 2. 更新角色及其权限
	 */
	@Override 
	public void update(Role role) {
		roleDao.deletePrivilegeByRoleId(role.getId());
		roleDao.update(role);
	}
}
