package com.company.home.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.company.core.constant.Constant;
import com.company.core.util.QueryHelper;
import com.company.tax.complain.entity.Complain;
import com.company.tax.complain.service.ComplainService;
import com.company.tax.user.entity.User;
import com.company.tax.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 系统首页
 * @author Dongfuming
 * @date 2016-5-13 上午10:02:33
 */
@SuppressWarnings("serial")
public class HomeAction extends ActionSupport {

	private Complain complain; // 接收‘我要投诉’传过来的数据
	@Resource
	private ComplainService complainService; // ‘我要投诉’功能使用
	@Resource
	private UserService userService; // ‘我要投诉’查看部门及其下人员
	private Map<String, Object> jsonMap;
	
	public String toSystemHomePage() {
		return "toSystemHomePage";
	}
	
	public String toAddComplainPage() {
		return "toAddComplainPage";
	}
	
	public void addComplain() throws Exception { 
		complain.setState(Complain.COMPLAIN_STATE_UNDONE);
		complain.setCompTime(new Timestamp(new Date().getTime()));
		complainService.save(complain);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write("success".getBytes("utf-8"));
		outputStream.close();
	}
	
	// 方式一：输出流输出Json格式的文本内容
	public void getUsersJsonWay1() {
		try {
			String dept = ServletActionContext.getRequest().getParameter("dept"); // ajax传过来的
			System.out.println("\ndept = " + dept);
			if(StringUtils.isNotBlank(dept)) { 
				QueryHelper queryHelper = new QueryHelper(User.class, "u"); 
				queryHelper.addCondition("u.dept = ?", dept); 
				List<User> userList = userService.findObjects(queryHelper);
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "success");
				jsonObject.accumulate("userList", userList);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(jsonObject.toString().getBytes("utf-8"));
				outputStream.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getUsersJsonWay2() {
		try {
			String dept = ServletActionContext.getRequest().getParameter("dept"); // ajax传过来的
			if(StringUtils.isNotBlank(dept)) { 
				QueryHelper queryHelper = new QueryHelper(User.class, "u"); 
				queryHelper.addCondition("u.dept = ?", dept); 
				List<User> userList = userService.findObjects(queryHelper);
				
				jsonMap = new HashMap<String, Object>();
				jsonMap.put("msg", "success");
				jsonMap.put("userList", userList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setComplain(Complain complain) {
		this.complain = complain;
	}
	public Complain getComplain() {
		return complain;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
}
