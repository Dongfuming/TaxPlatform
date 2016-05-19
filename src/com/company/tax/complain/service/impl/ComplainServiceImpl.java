package com.company.tax.complain.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hamcrest.core.IsInstanceOf;
import org.springframework.stereotype.Service;
import com.company.core.service.imple.BaseServiceImpl;
import com.company.core.util.QueryHelper;
import com.company.tax.complain.dao.ComplainDao;
import com.company.tax.complain.entity.Complain;
import com.company.tax.complain.service.ComplainService;

/**
 * @author Dongfuming
 * @date 2016-5-17 下午2:40:52
 */
@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements ComplainService {
	
	private ComplainDao complainDao;
	
	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
	}

	/**
	 * 每月月底将上个月的投诉自动处理为"已失效"
	 */
	@Override
	public void autoDeal() {
		Calendar cal = Calendar.getInstance();
		// 设置失效的时间的为每月1号，0时，0分，0秒
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.SECOND, 0); 
		
		// 查询本月1号0时0分0秒之前的投诉，设为失效
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		queryHelper.addCondition("c.state=?", Complain.COMPLAIN_STATE_UNDONE);
		queryHelper.addCondition("c.compTime < ?", cal.getTime()); 

		List<Complain> list = findObjects(queryHelper);
		if(list != null && list.size() > 0){
			for(Complain complain: list){
				complain.setState(Complain.COMPLAIN_STATE_INVALID);
				this.update(complain);
			}
		}
	}

	/*
	 1. json数据中需要有一年中12个月份和对应的投诉数，故在查询投诉时数需要根据年份查询，并且需要统计访年度的12个月的投诉数；
	 2. 当统计的是非本年度的数据时，因为这是已经过去的时间，所以当某个月没有投诉时，图表中应该显示为0；
	 3. 当统计的是当前年度的数据时，还未到的月份不可能有投诉数据，应该显示为空或者不显示。
	 4. 在调用fusioncharts的js中，统计图表需要接受一定格式的json数据(在demo中有固定json格式), {label: xxx, value: xxx}
	 */
	@Override
	public List<Map<String, Object>> getYearStatisticDataByYear(int year) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Object[]> list = complainDao.getYearStatisticDataByYear(year);
		if(list != null && list.size() > 0) {
			Calendar cal = Calendar.getInstance();
			boolean isThisYear = (cal.get(Calendar.YEAR) == year);
			int thisMonth = cal.get(Calendar.MONTH) + 1; 

			for(Object[] obj: list) {
				System.out.println("月份obj[0] = " + obj[0] + ", 数量obj[1] = " 
									+ obj[1] + ", 月份=Integer? " + (obj[0] instanceof Integer));

				Map<String, Object> map = new HashMap<String, Object>();
				int theMonth = Integer.valueOf((obj[0]) + ""); 
				map.put("label", theMonth+ "月");
				
				if (isThisYear) { 
					if(theMonth > thisMonth) { 
						map.put("value", ""); 
					} else { 
						map.put("value", obj[1]);
					}
				} else { 
					map.put("value", obj[1]);
				}
				resultList.add(map);
			}
		}
		return resultList;
	}
}
