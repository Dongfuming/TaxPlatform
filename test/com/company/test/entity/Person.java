package com.company.test.entity;

/**
 * 测试Hibernate，实体类
 * @author Dongfuming
 * 2016-5-6 下午4:29:46
 */
public class Person {
	
	private String id; // uuid 32位
	private String name;

	public Person() { }

	public Person(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
