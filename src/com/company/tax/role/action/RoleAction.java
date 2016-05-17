package com.company.tax.role.action;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.company.core.action.BaseAction;
import com.company.core.constant.Constant;
import com.company.core.util.QueryHelper;
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
	private Role role; // 编辑、删除
	private String[] privilegeIdArray; // 新增角色的权限数组
	private String searchContent; // 暂存搜索内容
	
	public String listRole() throws Exception {
		transferDataOfPrivilegaMap();
		QueryHelper queryHelper = new QueryHelper(Role.class, "r");
		try {
			if(role != null && StringUtils.isNotBlank(role.getName())) { // 搜索
				role.setName(URLDecoder.decode(role.getName(), "utf-8"));
				queryHelper.addCondition("r.name LIKE ?", "%" + role.getName() + "%");
			}
			pageResult = roleService.getPageResult(queryHelper, getPageNo(), getPageSize());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
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
		searchContent = role.getName();
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
		searchContent = role.getName();
		roleService.delete(role.getId());
		return "deleteRoleSuccess";
	}

	public String deleteSelectedRole() {
		searchContent = role.getName();
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
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public String getSearchContent() {
		return searchContent;
	}
}
