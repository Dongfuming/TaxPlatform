package com.company.tax.home.action;

/**
 * 纳税服务Action
 * @author Dongfuming
 * @date 2016-5-13 上午10:43:08
 */
public class HomeAction {

	// 跳转到纳税访问系统首页
	public String toTaxHomePage(){
		return "toTaxHomePage";
	}
	
	// 跳转到纳税访问系统首页-顶部
	public String toTaxHomePageTop(){
		return "toTaxHomePageTop";
	}
	
	// 跳转到纳税访问系统首页-左边菜单
	public String toTaxHomePageLeft(){
		return "toTaxHomePageLeft";
	}
}
