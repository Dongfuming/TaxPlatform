package com.company.test.dao.impl;

import java.io.Serializable;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.company.test.dao.TestDao;
import com.company.test.entity.Person;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

	@Override
	public boolean savePerson(Person person) {
		try {
			this.getHibernateTemplate().save(person);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Person findPersonById(Serializable id) {
		Person person = this.getHibernateTemplate().get(Person.class, id);
		return person;
	}
}
