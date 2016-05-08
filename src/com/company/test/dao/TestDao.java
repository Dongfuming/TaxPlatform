package com.company.test.dao;

import java.io.Serializable;

import com.company.test.entity.Person;

/**
 * 测试dao
 * @author Dongfuming
 * 2016-5-6 下午5:02:04
 */
public interface TestDao {
	public boolean savePerson(Person person);
	public Person findPersonById(Serializable id);
}
