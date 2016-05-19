package com.company.login.action;

import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.company.core.constant.Constant;
import com.company.tax.user.entity.User;
import com.company.tax.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录与注销模块
 * @author Dongfuming
 * @date 2016-5-13 下午2:36:52
 */
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {

	@Resource
	private UserService userService;
	private User user;
	private String loginResult;
	
	public String toLoginPage() {
		return "toLoginPage";
	}
	
	public String login() {
		if(user != null) {
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword()) ) {
				List<User> list = userService.findUsersByAccountAndPassword(user.getAccount(), user.getPassword());
				System.out.println("账号 ＝ " + user.getAccount() + "， 密码 ＝ " + user.getPassword() + "，个数 ＝ " + list.size());
				
				if(list != null && list.size() > 0) {
					user = list.get(0);
					user.setUserRoleList(userService.findUserRolesByUserId(user.getId()));
					saveUserInSession();
					//writeDownUserLogin();
					return "loginSuccess";
				} else { 
					loginResult = "帐号或密码不正确！";
				}
			} else {
				loginResult = "帐号或密码不能为空！";
			}
		} else {
			loginResult = "请输入帐号和密码！";
		}
		return toLoginPage();
	}
	
	public String toNoPrivilegePage() {
		return "toNoPrivilegePage";
	}
	
	public String logout() {
		removeUserFromSession();
		return toLoginPage();
	}
	
	private void removeUserFromSession() {
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
	}
	
	private void saveUserInSession() {
		ServletActionContext.getRequest().getSession().setMaxInactiveInterval(1 * 60); // 以秒为单位
		ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
	}
	
	@SuppressWarnings("unused")
	private void writeDownUserLogin() {
		Log log = LogFactory.getLog(this.getClass());
		log.info("用户名称为：" + user.getName() + " 的用户登录了系统。");
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	public String getLoginResult() {
		return loginResult;
	}
}
