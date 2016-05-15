package com.company.core.checker;

import com.company.tax.user.entity.User;

/**
 * 用户权限验证
 * @author Dongfuming
 * @date 2016-5-13 下午9:51:11
 */
public interface UserPrivilegeChecker {
	
	/** 判断用户是否有该权限。一个用户，多个角色，一个角色，多个权限 */
	public boolean isPrivilegeAccessible(User user, String privilege);
}
