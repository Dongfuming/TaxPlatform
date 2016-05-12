package com.company.tax.role.entity;

import java.util.Set;

/**
 * 角色表
 * @author Dongfuming
 * @date 2016-5-11 上午11:52:20
 */
public class Role {
	
	private String id;
	private String name;
	private String state;
	private Set<RolePrivilege> rolePrivilegeSet; // 一个角色含多个权限
	
	//角色状态
	public static String ROLE_STATE_VALID = "1";//有效
	public static String ROLE_STATE_INVALID = "0";//无效
	
	public Role() { }

	public Role(String id, String name, String state,
			Set<RolePrivilege> rolePrivilegeSet) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.rolePrivilegeSet = rolePrivilegeSet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<RolePrivilege> getRolePrivilegeSet() {
		return rolePrivilegeSet;
	}

	public void setRolePrivilegeSet(Set<RolePrivilege> rolePrivilegeSet) {
		this.rolePrivilegeSet = rolePrivilegeSet;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", state=" + state + "]";
	}
}
