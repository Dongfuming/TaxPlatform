package com.company.tax.info.service;

import java.io.Serializable;
import java.util.List;

import com.company.tax.info.entity.Info;


/**
 * @author Dongfuming
 * @date 2016-5-15 下午2:27:22
 */
public interface InfoService {

	public void save(Info info);

	public void update(Info info);

	public void delete(Serializable id);

	public Info findInfoById(Serializable id);

	public List<Info> findInfos();
}
