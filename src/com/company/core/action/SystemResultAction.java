package com.company.core.action;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * @author Dongfuming
 * @date 2016-5-11 上午11:00:09
 */
@SuppressWarnings("serial")
public class SystemResultAction extends StrutsResultSupport {

	@Override
	protected void doExecute(String arg0, ActionInvocation invocation)
			throws Exception {
		//HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletResponse response = ServletActionContext.getResponse();
		BaseAction action = (BaseAction)invocation.getAction();
		
		//do something
		System.out.println("进入了 SystemResultAction ..." + action.getClass().getName());
	}

}
