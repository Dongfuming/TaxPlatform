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
@SuppressWarnings("serial")
public class RoleAction extends BaseAction {
	
	@Resource
	private RoleService roleService;
	private List<Role> roleList; // 列表显示
	private Role role; // 编辑、删除
	private String[] privilegeIdArray; // 新增角色的权限数组
	
	public String listRole() throws Exception {
		System.out.println("list role action");
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP); // 加载角色权限
		try {
			roleList = roleService.findRoles();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		System.out.println("roleList.size = " + roleList.size());
		
		return "listRole";
	}
	
	public String toAddRolePage() {
		transferDataOfPrivilegaMap();
		return "toAddRolePage";
	}
	
	public String addRole() {
		System.out.println("新增的角色 = " + role);
		System.out.println("权限 = " + privilegeIdArray);
		savePrivilegeInRole();
		roleService.save(role);
		
		return "addRoleSuccess";
	}

	// 保存权限
	private void savePrivilegeInRole() {
		HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
		for(int i = 0; i < privilegeIdArray.length; i++) {
			CompositeRolePrivilege composition = new CompositeRolePrivilege(role, privilegeIdArray[i]);
			RolePrivilege rolePrivilege = new RolePrivilege(composition);
			set.add(rolePrivilege);
		}
		role.setRolePrivilegeSet(set);
	}
	
	public String toEditRolePage() {
		// 传权限列表、角色、该角色选中的权限过去
		transferDataOfPrivilegaMap();
		role = roleService.findRoleById(role.getId());
		transferDataOfPrivilegaIdArray();
		
		return "toEditRolePage";
	}
	
	public String editRole() {
		savePrivilegeInRole();
		roleService.update(role);
		return "editRoleSuccess";
	}
	
	public String deleteRole() {
		roleService.delete(role.getId());
		return "deleteRoleSuccess";
	}

	public String deleteSelectedRole() {
		for (String roleId : selectedRow) {
			roleService.delete(roleId);
		}
		return "deleteSelectedRoleSuccess";
	}
	
	/***************** private method *****************/
	private void transferDataOfPrivilegaMap() {
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
	}
	
	private void transferDataOfPrivilegaIdArray() {
		privilegeIdArray = new String[role.getRolePrivilegeSet().size()];
		int i = 0;
		for(RolePrivilege privilege: role.getRolePrivilegeSet()){
			privilegeIdArray[i++] = privilege.getCompositeRolePrivilege().getPrivilege();
		}
	}
	
	/***************** 数据注入 *****************/
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
