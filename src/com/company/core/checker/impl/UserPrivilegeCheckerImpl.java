package com.company.core.checker.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.company.core.checker.UserPrivilegeChecker;
import com.company.tax.role.entity.Role;
import com.company.tax.role.entity.RolePrivilege;
import com.company.tax.user.entity.User;
import com.company.tax.user.entity.UserRole;

/**
 * 用户访问‘纳税服务’时的权限检查。
 * 查询用户的所有角色，再查询角色的所有权限，
 * 检查是否有匹配‘纳税服务’的权限。
 * @author Dongfuming
 * @date 2016-5-13 下午9:56:24
 */
@Component("userPrivilegeChecker")
public class UserPrivilegeCheckerImpl implements UserPrivilegeChecker {

	@Override
	public boolean isPrivilegeAccessible(User user, String privilege) {
		// 在登录后，已经set了userRoleList属性
		List<UserRole> userRoleList = user.getUserRoleList();
		
		if (userRoleList != null) {
			for (UserRole userRole : userRoleList) {
				Role role = userRole.getCompositeUserRole().getRole();
				Set<RolePrivilege> rolePrivilegeSet = role.getRolePrivilegeSet();
				for (RolePrivilege rolePrivilege : rolePrivilegeSet) {
					String aPrivilege = rolePrivilege.getCompositeRolePrivilege().getPrivilege();
					if (privilege.equals(aPrivilege)) {
						return true;
					}
				}
			}	
		}
		return false;
	}

}
