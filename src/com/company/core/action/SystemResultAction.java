package com.company.core.action;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 在有特殊情况时，如果没有异常信息，但是有错误且有错误信息等内容，
 * 若需要进行友好的错误处理的话，那么可以借助StrutsResultSupport，
 * 需要在struts.xml中配置<result-types>
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
		
		System.out.println("进入了SystemResultAction ..." + action.getClass().getName());
	}

}
