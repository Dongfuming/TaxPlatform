package com.company.core.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Dongfuming
 * @date 2016-5-11 上午9:49:12
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {
	
	protected String[] selectedRow; // 删除选中的行
	
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
}
