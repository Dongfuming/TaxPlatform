package com.company.core.exception;

/**
 * Service层异常
 * @author Dongfuming
 * @date 2016-5-11 上午10:16:04
 */
@SuppressWarnings("serial")
public class ServiceException extends SystemException {

	public ServiceException() {
		super("业务操作错误！");
	}
	
	public ServiceException(String message) {
		super(message);
	}

}
