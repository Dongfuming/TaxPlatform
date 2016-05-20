package com.company.core.action;

import com.company.core.constant.Constant;
import com.company.core.page.PageResult;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 此Action适用于列表显示页,
 * 可查询分页数据，数据封装在pageResult中，
 * 可删除选中的行，行数保存在selectedRow中，
 * @author Dongfuming
 * @date 2016-5-11 上午9:49:12
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {
	
	protected String[] selectedRow; 
	protected PageResult pageResult;  
	private int pageNo;
	private int pageSize;
	
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public PageResult getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	public int getPageNo() {
		if (pageNo < 1) { // 设置默认值
			pageNo = Constant.DEFAULT_PAGE_NUMBER; 
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() { 
		if(pageSize < 1) { // 设置默认值
			pageSize = Constant.DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
