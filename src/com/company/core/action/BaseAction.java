package com.company.core.action;

import com.company.core.page.PageResult;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Dongfuming
 * @date 2016-5-11 上午9:49:12
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {
	
	protected String[] selectedRow; // 删除选中的行
	protected PageResult pageResult; // 分页查询 
	private int pageNo;
	private int pageSize;
	public static int DEFAULT_PAGE_SIZE = 3; 
	
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
		if (pageNo < 1) {
			pageNo = 1; // 设置默认值
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() { // 设置默认值
		if(pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
