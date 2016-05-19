package com.company.tax.complain.dao;

import java.util.List;

import com.company.core.dao.BaseDao;
import com.company.tax.complain.entity.Complain;

/**
 * @author Dongfuming
 * @date 2016-5-17 下午2:36:54
 */
public interface ComplainDao extends BaseDao<Complain> {

	/**
	 * 根据年份获取统计年度的每个月的投诉数
	 * @param year 要统计的年份
	 * @return 统计数据列表，如[1月：1条，2月：0条]
	 */
	public List<Object[]> getYearStatisticDataByYear(int year);
}
