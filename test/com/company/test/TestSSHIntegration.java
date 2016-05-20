package com.company.test;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.company.test.entity.Person;
import com.company.test.service.TestService;

/**
 * 测试SSH框架整合
 * @author Dongfuming
 * 2016-5-6 下午2:36:49
 */
public class TestSSHIntegration {
	// spring容器对象
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void loadContext() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSpring() {
		TestService testService = (TestService)context.getBean("testService");
		testService.testFunc();
	}
	
	@Test
	public void testSpringAndStruts() {
		System.out.println("testSpringAndStruts");
		// 在浏览器输入: http://localhost:8080/TaxPlatform/test.action 
		// 查看后台是否能输入service中的testFunc方法中的打印信息
		// System.out.println("test service implementation class");
	}
	
	@Test
	public void testHibernate() {
		SessionFactory factory = (SessionFactory)context.getBean("sessionFactory");
		Session session = factory.openSession();
		session.beginTransaction();
		
		Person p1 = new Person("person007");
		session.save(p1);
		
		session.getTransaction().commit();
	}
	
	@Test
	public void testServiceAndDao() {
		TestService testService = (TestService)context.getBean("testService");
		boolean success = testService.savePerson(new Person("测试测试"));
		System.out.println(success);
	}
	
	@Test
	public void testTransactionReadonly() {
		TestService testService = (TestService)context.getBean("testService");
		// 在find函数中加了save操作
		// spring配置了事务管理，find* readonly=true, 
		// 结果应该是抛出invalidDataAccessApiUsageException，且save失败
		Person person = testService.findPersonById("4028b2ac54857ef10154857ef2260000");
		System.out.println(person.getName() + ", " + person.getId());
	}
	
	@Test
	public void testTransactionRollback() {
		TestService testService = (TestService)context.getBean("testService");
		testService.savePerson(new Person("不应该出现2"));
	}
}
