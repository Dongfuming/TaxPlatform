package com.company.home.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.company.core.constant.Constant;
import com.company.core.util.HttpServletUtil;
import com.company.core.util.QueryHelper;
import com.company.tax.complain.entity.Complain;
import com.company.tax.complain.service.ComplainService;
import com.company.tax.info.entity.Info;
import com.company.tax.info.service.InfoService;
import com.company.tax.user.entity.User;
import com.company.tax.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 系统首页
 * @author Dongfuming
 * @date 2016-5-13 上午10:02:33
 */
@SuppressWarnings("serial")
public class HomeAction extends ActionSupport {

	@Resource
	private ComplainService complainService; 
	@Resource
	private UserService userService; 
	@Resource
	private InfoService infoService; 
	private Complain complain; 
	private Info info;
	private Map<String, Object> jsonMap;
	
	/*
	 1. 个人资料--从session里面拿
	 2. 信息列表--查询最新5条发布状态的信息，显示在页面中，点击信息标题可查看具体信息
	 3. 我的投诉--查询当前用户投诉的前6条数据，显示在页面中，点击投诉标题可查看投诉信息
	 */
	public String toSystemHomePage() {
		transferDataOfInfoList();
		transferDataOfComplainList();
		
		return "toSystemHomePage";
	}
	
	public String toInfoDetailPage() {
		info = infoService.findObjectById(info.getInfoId());
		transferDataOfInfoTypeMap();
		
		return "toInfoDetailPage";
	}
	
	public String toComplainDetailPage() {
		complain = complainService.findObjectById(complain.getCompId());
		transferDataOfComplainStateMap();
		
		return "toComplainDetailPage";
	}
	
	public String toAddComplainPage() {
		return "toAddComplainPage";
	}
	
	public void addComplain() { 
		complain.setState(Constant.COMPLAIN_STATE_UNDONE);
		complain.setCompTime(new Timestamp(new Date().getTime()));
		complainService.save(complain);
		
		HttpServletUtil.writeOutStream("success");
	}
	
	// 方式1：输出流输出json文本
	public void getUsersJsonWay1() {
		String dept = ServletActionContext.getRequest().getParameter("dept"); // ajax
		if(StringUtils.isNotBlank(dept)) { 
			List<User> userList = getUserListWithDept(dept);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "success");
			jsonObject.accumulate("userList", userList);

			HttpServletUtil.writeOutStream(jsonObject.toString());
		}
	}

	// 方式2：struts输出json文本
	public String getUsersJsonWay2() {
		String dept = ServletActionContext.getRequest().getParameter("dept"); 
		if(StringUtils.isNotBlank(dept)) { 
			List<User> userList = getUserListWithDept(dept);

			jsonMap = new HashMap<String, Object>();
			jsonMap.put("msg", "success");
			jsonMap.put("userList", userList);
		}
		
		return SUCCESS;
	}

	private void transferDataOfInfoList() {
		ActionContext.getContext().getContextMap().put("infoTypeMap", Constant.INFO_TYPE_MAP);
		QueryHelper queryHelper1 = new QueryHelper(Info.class, "i");
		queryHelper1.addOrderByProperty("i.createTime", Constant.ORDER_BY_DESC);
		List<Object> infoList = infoService.getPageResult(queryHelper1, 1, 5).getItems();
		
		ActionContext.getContext().getContextMap().put("infoList", infoList);
	}
	
	private void transferDataOfComplainStateMap() {
		ActionContext.getContext().getContextMap().put("complainStateMap", Constant.COMPLAIN_STATE_MAP);
	}
	
	private void transferDataOfComplainList() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
		
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		queryHelper.addCondition("c.compName = ?", user.getName());
		queryHelper.addOrderByProperty("c.compTime", Constant.ORDER_BY_DESC);
		queryHelper.addOrderByProperty("c.state", Constant.ORDER_BY_DESC);
		List<Object> complainList = complainService.getPageResult(queryHelper, 1, 6).getItems();
		
		ActionContext.getContext().getContextMap().put("complainList", complainList);
		transferDataOfComplainStateMap();
	}
	
	private void transferDataOfInfoTypeMap() {
		ActionContext.getContext().getContextMap().put("infoTypeMap", Constant.INFO_TYPE_MAP);
	}
	
	private List<User> getUserListWithDept(String dept) {
		QueryHelper queryHelper = new QueryHelper(User.class, "u"); 
		queryHelper.addCondition("u.dept = ?", dept); 
		List<User> userList = userService.findObjects(queryHelper);
		
		return userList;
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
	public void setInfo(Info info) {
		this.info = info;
	}
	public Info getInfo() {
		return info;
	}
}
