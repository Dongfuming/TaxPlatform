package com.company.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.core.util.QueryHelper;
import com.company.tax.user.entity.User;
import com.company.tax.user.service.UserService;

/**
 * 测试 纳税服务-用户管理 分页查询功能
 * @author Dongfuming
 * @date 2016-5-16 下午4:28:14
 */
public class TestPage {

	// spring容器对象
	private ClassPathXmlApplicationContext context;

	@Before
	public void loadContext() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testPageResult() {
		UserService userService = (UserService)context.getBean("userService");
		QueryHelper queryHelper = new QueryHelper(User.class, "u");
		
//		queryHelper.addCondition("u.name like ?", "%" + user.getName() + "%");
//		pageResult = userService.getPageResult(queryHelper, getPageNo(), getPageSize());
	}
}
