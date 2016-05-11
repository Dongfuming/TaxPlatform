package com.company.core.exception;

/**
 * Action层异常
 * @author Dongfuming
 * @date 2016-5-11 上午10:13:11
 */
public class ActionException extends SystemException {

	public ActionException() {
		super("请求发生错误！");
	}
	
	public ActionException(String message) {
		super(message);
	}
}
