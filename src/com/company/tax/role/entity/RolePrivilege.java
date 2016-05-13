package com.company.tax.role.entity;

/**
 * 角色权限表
 * @author Dongfuming
 * @date 2016-5-11 下午2:20:28
 */
public class RolePrivilege {

	private CompositeRolePrivilege compositeRolePrivilege; // 复合主键

	public RolePrivilege() { }
	
	public RolePrivilege(CompositeRolePrivilege privilege) {
		this.compositeRolePrivilege = privilege;
	}

	public CompositeRolePrivilege getCompositeRolePrivilege() {
		return compositeRolePrivilege;
	}
	
	public void setCompositeRolePrivilege(
			CompositeRolePrivilege compositeRolePrivilege) {
		this.compositeRolePrivilege = compositeRolePrivilege;
	}
	
	@Override
	public String toString() {
		return "'" + compositeRolePrivilege.getPrivilege() + "'";
	}
}
