package com.company.tax.info.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
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
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService{

	private InfoDao infoDao;
	
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
	
	@Override
	public void save(Info info) {
		infoDao.save(info);
	}

	@Override
	public void update(Info info) {
		infoDao.update(info);
	}

	@Override
	public void delete(Serializable id) {
		infoDao.delete(id);
	}

	@Override
	public Info findInfoById(Serializable id) {
		return infoDao.findObjectById(id);
	}

	@Override
	public List<Info> findInfos() {
		return infoDao.findObjects();
	}

}
