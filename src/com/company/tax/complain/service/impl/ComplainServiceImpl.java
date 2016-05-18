package com.company.tax.complain.service.impl;

import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
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
	
	@SuppressWarnings("unused")
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
}
