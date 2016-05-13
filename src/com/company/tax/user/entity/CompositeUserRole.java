package com.company.tax.user.entity;

import java.io.Serializable;

import com.company.tax.role.entity.Role;

/**
 * 用户--角色，多对多关系， 主键：role.id 和 userId 
 * 复合主键类，实现Serializable接口且定义equals()与hashCode()方法
 * @author Dongfuming
 * @date 2016-5-12 下午4:56:49
 */
@SuppressWarnings("serial")
public class CompositeUserRole implements Serializable {
	
	private Role role; // 角色 
	private String userId; // 用户
	
	public CompositeUserRole() { }
	
	public CompositeUserRole(Role role, String userId) {
		this.role = role;
		this.userId = userId;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) { 
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		CompositeUserRole other = (CompositeUserRole) obj;
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
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
