package com.company.tax.user.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.company.core.action.BaseAction;
import com.company.core.util.QueryHelper;
import com.company.tax.role.entity.Role;
import com.company.tax.role.service.RoleService;
import com.company.tax.user.entity.User;
import com.company.tax.user.entity.UserRole;
import com.company.tax.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Dongfuming
 * @date 2016-5-9 下午1:51:26
 */
@SuppressWarnings("serial")
public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	private User user; // 新增、编辑、删除的用户
	private String[] roleIdArray; // 用户的角色
	private String searchContent; // 暂存搜索内容
	
	private File headImg; // 页面file表单域中对应的名称
	private String headImgContentType; 
	private String headImgFileName; 
	
	private File userExcel; // 导入用户
	private String userExcelContentType;
	private String userExcelFileName;
	
	public String listUser() { // 查
		QueryHelper queryHelper = new QueryHelper(User.class, "u");
		try {
			if (user != null && StringUtils.isNotBlank(user.getName())) { // 搜索
				user.setName(URLDecoder.decode(user.getName(), "utf-8"));
				queryHelper.addCondition("u.name like ?", "%" + user.getName() + "%");
			}
			pageResult = userService.getPageResult(queryHelper, getPageNo(), getPageSize());
			// 2016-05-16疑问: pageResult.getItems的元素是Object类型，在JSP页面用<s:iterator>标签
			// 却可以迭代出User类型对象，<s:property>可以直接用User的属性，何解？？？ 反射吗？
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listUser";
	}
	
	public String addUser() { // 增
		saveUserHeadImg();
		userService.saveUserAndRoles(user, roleIdArray);
		return "addUserSuccess";
	}

	public String toAddUserPage() {
		transferDataOfRoleList();
		return "toAddUserPage";
	}
	
	public String toEditUserPage() {
		searchContent = user.getName();
		transferDataOfRoleList();
		transferDataOfRoleIdArray();
		user = userService.findUserById(user.getId());
		return "toEditUserPage";
	}
	
	public String editUser() { // 改
		saveUserHeadImg();
		userService.updateUserAndRoles(user, roleIdArray);
		return "editUserSuccess";
	}

	public String deleteUser() { // 删
		searchContent = user.getName();
		userService.delete(user.getId());
		return "deleteUserSuccess";
	}
	
	public String deleteSelectedUser() { // 批量删除
		searchContent = user.getName();
		for (String userId : selectedRow) {
			userService.delete(userId);
		}
		return "deleteSelectedUserSuccess";
	}
	
	public void exportUserExcel() { // 导出
		try {
			List<User> userList = userService.findUsers();
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			String fileName = new String("用户列表.xls".getBytes(), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportUserExcel(userList, outputStream);
			if(outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String importUserExcel() { // 导入
		System.out.println("excel文件名 = " + userExcelFileName);
		System.out.println("excel文件类型 = " + userExcelContentType);
		
		if(userExcel != null) {
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				userService.importUserExcel(userExcel, userExcelFileName);
			}
		}
		return "importUserSuccess";
	}
	
	public void verifyUserAccount() { // 校验帐号
		try {
			if(user != null && StringUtils.isNotBlank(user.getAccount())){
				// 检查数据库中是否已存在此账号 (如果是编辑的话，应把当前用户id排除后再查找)
				List<User> list = userService.findUserByAccountAndId(user.getId(), user.getAccount());
				System.out.println("已存在的" + user.getAccount() + "用户个数 = " + list.size());
				String unique = "true";
				if(list != null && list.size() > 0) {
					unique = "false";
				}

				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html; charset=utf-8");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(unique.getBytes());
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveUserHeadImg() {
		try {
			if(headImg != null) {
				// 保存在服务器文件里 /Library/Java/tomcat/webapps/TaxPlatform/upload/user
				String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
				String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
				FileUtils.copyFile(headImg, new File(filePath, fileName));
				
				user.setHeadImg("user/" + fileName); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void transferDataOfRoleIdArray() {
		List<UserRole> userRoleList = userService.findUserRolesByUserId(user.getId());
		roleIdArray = new String[userRoleList.size()];
		int i = 0;
		for (UserRole userRole : userRoleList) {
			String roleId = userRole.getCompositeUserRole().getRole().getId();
			roleIdArray[i++] = roleId;
		}
	}
	
	private void transferDataOfRoleList() {
		List<Role> roleList = roleService.findRoles();
		ActionContext.getContext().getContextMap().put("roleList", roleList);
	}

	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelContentType() {
		return userExcelContentType;
	}
	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public String[] getRoleIdArray() {
		return roleIdArray;
	}
	public void setRoleIdArray(String[] roleIdArray) {
		this.roleIdArray = roleIdArray;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public String getSearchContent() {
		return searchContent;
	}
}

