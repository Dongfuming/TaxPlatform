package com.company.tax.complain.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;

import com.company.core.dao.impl.BaseDaoImpl;
import com.company.tax.complain.dao.ComplainDao;
import com.company.tax.complain.entity.Complain;

/**
 * @author Dongfuming
 * @date 2016-5-17 下午2:38:07
 */
public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	// 先到数据库里建个month_table的表，只有一列:the_month，里面只放1~12这几个数字
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getYearStatisticDataByYear(int year) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT the_month, COUNT(comp_id)")
		.append(" FROM month_table LEFT JOIN complain ON MONTH(comp_time)=the_month")
		.append(" AND YEAR(comp_time)=?")
		.append(" GROUP BY the_month ")
		.append(" ORDER BY the_month");
		
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sb.toString());
		sqlQuery.setParameter(0, year);
		
		return sqlQuery.list();
	}

}
