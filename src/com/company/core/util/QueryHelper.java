package com.company.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询语句工具类
 * @author Dongfuming
 * @date 2016-5-16 下午1:55:32
 */
public class QueryHelper {

	private String fromClause = ""; // from子句
	private String whereClause = ""; // where子句
	private String orderByClause = ""; // order by子句
	private List<Object> parameters; // 查询条件值集合

	/**
	 * 构造from子句
	 * @param clazz 实体类
	 * @param alias 实体类对应的别名
	 */
	@SuppressWarnings("rawtypes")
	public QueryHelper(Class clazz, String alias){
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	/**
	 * 构造where子句
	 * @param condition 查询条件语句；例如：xxx.name like ?
	 * @param params 查询条件语句中?对应的查询条件值
	 */
	public void addCondition(String condition, Object... params){
		if (whereClause.length() > 1) { // 非第一个查询条件
			whereClause += " AND " + condition;
		} else { // 第一个查询条件
			whereClause += " WHERE " + condition;
		}
		
		if(parameters == null){
			parameters = new ArrayList<Object>();
		}
		if(params != null){
			for(Object param: params){
				parameters.add(param);
			}
		}
	}
	
	/**
	 * 构造order by子句
	 * @param property 排序属性，如：xxx.createTime
	 * @param order 排序顺序，如：DESC 或者 ASC
	 */
	public void addOrderByProperty(String property, String order){
		if (orderByClause.length() > 1) { // 非第一个排序属性
			orderByClause += "," + property + " " + order;
		} else { // 第一个排序属性
			orderByClause = " ORDER BY " + property + " " + order;
		}
	}

	// 查询列表hql语句
	public String getQueryListHql(){
		return fromClause + whereClause + orderByClause;
	}
	
	// 查询统计数的hql语句
	public String getQueryCountHql(){
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
	
	// 查询hql语句中?对应的查询条件值集合
	public List<Object> getParameters(){
		return parameters;
	}
}
