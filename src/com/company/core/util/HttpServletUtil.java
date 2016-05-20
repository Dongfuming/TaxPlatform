package com.company.core.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * @author Dongfuming
 * @date 2016-5-19 下午5:48:48
 */
public class HttpServletUtil {

	public static void writeOutStream(String string) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html; charset=utf-8");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(string.getBytes("utf-8"));
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
