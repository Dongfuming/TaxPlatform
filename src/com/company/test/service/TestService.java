package com.company.test.service;
import java.io.Serializable;

import com.company.test.entity.Person;

/**
 * 测试Service
 * @author Dongfuming
 * 2016-5-6 下午2:45:52 
 */
public interface TestService {
	public void testFunc();
	public boolean savePerson(Person person);
	public Person findPersonById(Serializable id);
}
