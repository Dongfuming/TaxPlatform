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
 * @author Dongfuming
 * @date 2016-5-13 下午9:56:24
 */
@Component("userPrivilegeChecker")
public class UserPrivilegeCheckerImpl implements UserPrivilegeChecker {

	@Override
	public boolean isPrivilegeAccessible(User user, String privilege) {
		//List<UserRole> userRoleList = userService.findUserRolesByUserId(user.getId());
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
