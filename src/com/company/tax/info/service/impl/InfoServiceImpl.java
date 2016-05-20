package com.company.tax.info.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.core.service.imple.BaseServiceImpl;
import com.company.tax.info.dao.InfoDao;
import com.company.tax.info.entity.Info;
import com.company.tax.info.service.InfoService;


/**
 * @author Dongfuming
 * @date 2016-5-15 下午2:27:22
 */
@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {

	@SuppressWarnings("unused")
	private InfoDao infoDao;
	
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
}
