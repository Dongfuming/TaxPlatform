package com.company.tax.complain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.core.service.imple.BaseServiceImpl;
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
}
