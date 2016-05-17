package com.company.core.page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dongfuming
 * @date 2016-5-16 下午2:31:07
 */
public class PageResult {

	private long totalCount; // 总记录数
	private int pageNo; // 当前页号
	private int totalPageCount; // 总页数
	private int pageSize; // 每页大小
	private List<Object> items; // 列表记录

	public PageResult(long totalCount, int pageNo, int pageSize, List<Object> items) {
		this.items = (items == null) ? (new ArrayList<Object>()) : items;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		if(totalCount > 0){
			// 计算总页数
			int temp = (int)totalCount / pageSize;
			this.totalPageCount = (totalCount % pageSize == 0) ? temp : (temp + 1);
			this.pageNo = pageNo;
		} else {
			this.pageNo = 0;
		}
	}

	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Object> getItems() {
		return items;
	}
	public void setItems(List<Object> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PageResult [总条数=" + totalCount + ", 当前页=" + pageNo
				+ ", 总页数=" + totalPageCount + ", 每页大小="
				+ pageSize + ", 当前页数据大小=" + items.size() + "]";
	}

}
