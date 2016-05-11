package com.company.test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.company.test.dao.TestDao;
import com.company.test.entity.Person;
import com.company.test.service.TestService;

/**
 * 测试Service
 * @author Dongfuming
 * 2016-5-6 下午2:47:26 
 */
@Service("testService")
public class TestServiceImpl implements TestService {

	@Resource
	private TestDao testDao;
	
	@Override
	public void testFunc() {
		System.out.println("test service implementation class");
	}

	@Override
	public boolean savePerson(Person person) {
		boolean success = testDao.savePerson(person);
		System.out.println("save 操作");
		int i = 1 / 0;  // 遇到异常，回滚
		System.out.println(i);
		testDao.savePerson(new Person("不应该出现"));
		
		return success;
	}

	@Override
	public Person findPersonById(Serializable id) {
		savePerson(new Person("test"));  // find* readonly=true, 抛事务异常且save失败
		Person person = testDao.findPersonById(id);
		return person;
	}

}
