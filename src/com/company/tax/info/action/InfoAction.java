package com.company.tax.info.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.company.core.action.BaseAction;
import com.company.core.constant.Constant;
import com.company.core.util.HttpServletUtil;
import com.company.core.util.QueryHelper;
import com.company.tax.info.entity.Info;
import com.company.tax.info.service.InfoService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Dongfuming
 * @date 2016-5-15 下午2:33:16
 */
@SuppressWarnings("serial")
public class InfoAction extends BaseAction {
	
	@Resource
	private InfoService infoService;
	private Info info;
	/*
	 * 搜索后，编辑其中一条，跳转时需要把搜索条件也带过去，
	 * 以便回来后还能回到搜索的结果界面,
	 * 但info.title在编辑后既作搜索内容字段，又作更新的标题字段，
	 * 会产生冲突，故另增一个属性专门保存搜索字段。(删除功能不会有冲突)
	 */
	private String searchContent; 
	
	public String listInfo() throws Exception {
		transferDataOfInfoTypeMap();
		transferDataOfPageResult();
		
		return "listInfo";
	}

	public String toAddInfoPage() {
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		transferDataOfInfoTypeMap();
		
		return "toAddInfoPage";
	}
	
	public String addInfo() {
		info.setCreateTime(new Timestamp(new Date().getTime()));
		infoService.save(info);
		
		return "addInfoSuccess";
	}
	
	public String toEditInfoPage() {
		searchContent = info.getTitle();
		info = infoService.findObjectById(info.getInfoId());
		transferDataOfInfoTypeMap();
		
		return "toEditInfoPage";
	}
	
	public String editInfo() {
		infoService.update(info);
		
		return "editInfoSuccess";
	}
	
	public String deleteInfo() {
		searchContent = info.getTitle();
		infoService.delete(info.getInfoId());
		
		return "deleteInfoSuccess";
	}
	
	public String deleteSelectedInfo() {
		searchContent = info.getTitle();
		for (String infoId : selectedRow) {
			infoService.delete(infoId);
		}
		
		return "deleteSelectedInfoSuccess";
	}
	
	public void changeInfoState() { // ajax更新状态
		Info theInfo= infoService.findObjectById(info.getInfoId());
		theInfo.setState(info.getState());
		infoService.update(theInfo);

		HttpServletUtil.writeOutStream("更新状态成功");
	}
	
	private void transferDataOfPageResult() throws UnsupportedEncodingException {
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		if (info != null && StringUtils.isNotBlank(info.getTitle())) {
			info.setTitle(URLDecoder.decode(info.getTitle(), "utf-8"));
			queryHelper.addCondition("i.title LIKE ?", "%" + info.getTitle() + "%");
		}
		queryHelper.addOrderByProperty("i.createTime", Constant.ORDER_BY_DESC);
		
		pageResult = infoService.getPageResult(queryHelper, getPageNo(), getPageSize());
	}
	
	private void transferDataOfInfoTypeMap() {
		ActionContext.getContext().getContextMap().put("infoTypeMap", Constant.INFO_TYPE_MAP);
	}
	
	public void setInfo(Info info) {
		this.info = info;
	}
	public Info getInfo() {
		return info;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public String getSearchContent() {
		return searchContent;
	}
}
