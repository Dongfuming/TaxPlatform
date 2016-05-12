package com.company.tax.role.entity;

import java.io.Serializable;

/**
 * 一个角色，多个权限，根据 role.id 和 privilege 查询
 * 复合主键类，实现Serializable接口且定义equals()与hashCode()方法
 * @author Dongfuming
 * @date 2016-5-11 下午1:46:43
 */
public class CompositeRolePrivilege implements Serializable {
	
	private Role role; // 角色 
	private String privilege; // 权限
	
	public CompositeRolePrivilege() { }
	
	public CompositeRolePrivilege(Role role, String privilege) {
		this.role = role;
		this.privilege = privilege;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) { // 比较privilege和role
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		CompositeRolePrivilege other = (CompositeRolePrivilege) obj;
		if (privilege == null) {
			if (other.privilege != null) {
				return false;
			}
		} else if (!privilege.equals(other.privilege)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		return true;
	}
}
