package com.company.test.action;

import javax.annotation.Resource;

import com.company.test.service.TestService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 测试Action
 * @author Dongfuming
 * 2016-5-6 下午3:31:24
 */
@SuppressWarnings("serial")
public class TestAction extends ActionSupport {

	@Resource
	private TestService testService;
	
	public String execute() throws Exception {
		testService.testFunc();
		
		return SUCCESS;
	}
}
