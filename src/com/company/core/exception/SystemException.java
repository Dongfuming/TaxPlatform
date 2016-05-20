package com.company.core.exception;

/**
 * 总的系统异常类
 * @author Dongfuming
 * @date 2016-5-11 上午10:05:13
 */
@SuppressWarnings("serial")
public class SystemException extends Exception {
	
	private String errorMessage;
	
	public SystemException() { }
	
	public SystemException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessage = errorMessage;
	}
	
	public SystemException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public SystemException(Throwable cause) {
		super(cause);
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
