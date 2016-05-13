package com.company.tax.user.entity;


/**
 * 用户角色关系表，多对多关系
 * @author Dongfuming
 * @date 2016-5-12 下午5:05:12
 */
public class UserRole {
	
	private CompositeUserRole compositeUserRole; // 复合主键

	public UserRole() { }
	
	public UserRole(CompositeUserRole compositeUserRole) {
		this.compositeUserRole = compositeUserRole;
	}

	public CompositeUserRole getCompositeUserRole() {
		return compositeUserRole;
	}
	
	public void setCompositeUserRole(CompositeUserRole compositeUserRole) {
		this.compositeUserRole = compositeUserRole;
	}
	
	@Override
	public String toString() {
		return "'" + compositeUserRole.getRole().getName() + "'";
	}
}
