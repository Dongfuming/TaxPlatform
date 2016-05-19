package com.company.tax.complain.service;

import java.util.List;
import java.util.Map;

import com.company.core.service.BaseService;
import com.company.tax.complain.entity.Complain;

/**
 * @author Dongfuming
 * @date 2016-5-17 下午2:39:23
 */
public interface ComplainService extends BaseService<Complain> {
	
	// 自动受理投诉
	public void autoDeal();
	
	public List<Map<String, Object>> getYearStatisticDataByYear(int year);
}
