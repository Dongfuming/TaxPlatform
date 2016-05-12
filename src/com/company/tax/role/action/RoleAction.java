package com.company.tax.role.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import com.company.core.action.BaseAction;
import com.company.core.constant.Constant;
import com.company.tax.role.entity.CompositeRolePrivilege;
import com.company.tax.role.entity.Role;
import com.company.tax.role.entity.RolePrivilege;
import com.company.tax.role.service.RoleService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Dongfuming
 * @date 2016-5-11 下午3:49:08
 */
public class RoleAction extends BaseAction {
	
	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role;
	private String[] privilegeIdArray;
	
	public String listRole() throws Exception {
		System.out.println("list role action");
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP); //加载权限集合
		try {
			roleList = roleService.findRoles();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		System.out.println("roleList.size = " + roleList.size());
		
		return "listRole";
	}
	
	public String toAddRolePage() {
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP); //加载权限集合
		return "toAddRolePage";
	}
	
	public String addRole() {
		System.out.println("新增的角色 = " + role);
		System.out.println("权限 = " + privilegeIdArray);
		// 保存权限
		HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
		for(int i = 0; i < privilegeIdArray.length; i++) {
			CompositeRolePrivilege composition = new CompositeRolePrivilege(role, privilegeIdArray[i]);
			RolePrivilege rolePrivilege = new RolePrivilege(composition);
			set.add(rolePrivilege);
		}
		role.setRolePrivilegeSet(set);
		roleService.save(role);
		
		return "addRoleSuccess";
	}
	
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Role getRole() {
		return role;
	}
	public void setPrivilegeIdArray(String[] privilegeIdArray) {
		this.privilegeIdArray = privilegeIdArray;
	}
	public String[] getPrivilegeIdArray() {
		return privilegeIdArray;
	}
}
