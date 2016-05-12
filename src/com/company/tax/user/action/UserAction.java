package com.company.tax.user.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.company.core.action.BaseAction;
import com.company.core.exception.ActionException;
import com.company.core.exception.ServiceException;
import com.company.tax.user.entity.User;
import com.company.tax.user.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Dongfuming
 * @date 2016-5-9 下午1:51:26
 */
@SuppressWarnings("serial")
public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	private List<User> userList; // 界面显示的用户列表
	private User user; // 新增、编辑、删除的用户
	
	private File headImg; // 页面file表单域中对应的名称
	private String headImgContentType; 
	private String headImgFileName; 
	
	private File userExcel; // 导入用户
	private String userExcelContentType;
	private String userExcelFileName;
	
	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}
	
	public String listUser() throws ActionException { // 查
		try {
			userList = userService.findUsers();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException("请求操作失败。" + e.getMessage());
		}
		return "listUser";
	}
	
	public String addUser() { // 增
		saveUserHeadImg();
		userService.save(user);
		return "addUserSuccess";
	}

	public String toAddUserPage() {
		return "toAddUserPage";
	}
	
	public String toEditUserPage() { 
		user = userService.findUserById(user.getId());
		return "toEditUserPage";
	}
	
	public String editUser() { // 改
		System.out.println("编辑用户 = " + user);
		saveUserHeadImg();
		userService.update(user);
		return "editUserSuccess";
	}
	
	public String deleteUser() { // 删
		userService.delete(user.getId());
		return "deleteUserSuccess";
	}
	
	public String deleteSelectedUser() { // 批量删除
		for (String id : selectedRow) {
			userService.delete(id);
		}
		return "deleteSelectedUserSuccess";
	}
	
	
	public void exportUserExcel() { // 导出
		try {
			//1、查找用户列表
			userList = userService.findUsers();
			//2、导出文件
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportUserExcel(userList, outputStream);
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String importUserExcel() { // 导入
		System.out.println("excel文件名 = " + userExcelFileName);
		System.out.println("excel文件类型 = " + userExcelContentType);
		
		//1、获取excel文件
		if(userExcel != null) {
			//是否是excel
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				//2、导入
				userService.importUserExcel(userExcel, userExcelFileName);
			}
		}
		return "importUserSuccess";
	}
	
	public void verifyUserAccount() { // 校验帐号
		try {
			//1、获取帐号
			if(user != null && StringUtils.isNotBlank(user.getAccount())){
				//2、根据帐号到数据库中校验是否存在该帐号对应的用户
				// 如果是编辑的话，应把当前记录排除后再查看是否有重复账号，所以把id传入查询
				List<User> list = userService.findUserByAccountAndId(user.getId(), user.getAccount());
				System.out.println("已存在的" + user.getAccount() + "用户个数 = " + list.size());
				String unique = "true";
				if(list != null && list.size() > 0){
					//说明该帐号已经存在
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

	/******************* 私有方法 ******************/ 
	private void saveUserHeadImg() {
		try {
			if(headImg != null) {
				// 保存到服务器文件里 /Library/Java/tomcat/webapps/TaxPlatform/upload/user
				String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
				String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
				FileUtils.copyFile(headImg, new File(filePath, fileName));
				
				user.setHeadImg("user/" + fileName); // 设置头像路径
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/******************* 数据注入 ******************/ 
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<User> getUserList() {
		return userList;
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
}

