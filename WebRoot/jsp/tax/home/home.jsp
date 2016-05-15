<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/header.jsp" %> 

<html>
<head>
    <title>国税协同办公平台-纳税服务</title>
    <link href="${basePath }/css/nsfw/css.css" rel="stylesheet" type="text/css" />
    <link href="${basePath }/css/nsfw/style.css" rel="stylesheet" type="text/css" />
</head>

<frameset cols="*,1222,*" class="bj" frameborder="no" border="0" framespacing="0">
	<!-- 最底层的背景图片 -->
    <frame src="${basePath}/jsp/common/bg.jsp" scrolling="No" noresize="noresize"/>
    
    <frameset rows="156,*" cols="*" frameborder="no" border="0" framespacing="0">
    	<!-- 上面部分 -->
        <%-- <frame src="${basePath }/tax/home/toTaxHomePageTop.action" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" /> --%>
        <frame src="${basePath }/jsp/tax/home/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
        
        <frameset cols="14%,60%" frameborder="no" border="0" framespacing="0">
        	<!-- 左边部分 -->
            <%-- <frame src="${basePath }/tax/home/toTaxHomePageLeft.action" scrolling="yes" noresize="noresize" id="leftFrame" /> --%> 
			<frame src="${basePath }/jsp/tax/home/left.jsp" scrolling="yes" noresize="noresize" id="leftFrame" />
			
            <!-- 中间部分--logo图片 -->
            <frame src="${basePath}/jsp/common/welcome.jsp" name="mainFrame" id="mainFrame" />
        </frameset>
    </frameset>
    
    <!-- 最底层的背景图片 -->
    <frame src="${basePath}/jsp/common/bg.jsp" scrolling="No" noresize="noresize"/>
</frameset>

<body>
${basePath }<br>
</body>
</html>