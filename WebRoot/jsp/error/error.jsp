<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<!-- <base> 标签为页面上的所有链接规定默认地址或默认目标。在 HTML 中，<base> 标签没有结束标签 -->
    <base href="<%= basePath %>" >
    
    <title>系统异常信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <%= basePath %> <br/>
    
  	<img src="<%= request.getContextPath() %>/images/common/error.jpg">
    <br>
    <s:if test="exception.errorMassage != null && exception.errorMassage != ''">
    	<s:property value="exception.errorMsg"/>
    </s:if>
    <s:else>
    	操作失败！<s:property value="exception.message"/>
    </s:else>
    
  </body>
</html>
