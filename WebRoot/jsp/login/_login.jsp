<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/header.jsp" %> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="${basePath }/css/login.css" type="text/css" rel="stylesheet">

<script type="text/javascript">
	// 提交登录
	function loginAction(){
		document.forms[0].submit();
	}
	// 重置登录
	function setClean(){
		document.getElementById("account").value = "";
		document.getElementById("password").value = "";
	}
	
	//解决子框架嵌套的问题
	if(window != window.parent){
		window.parent.location.reload(true);
	}
	
	// 在纳税服务的首页中使用了frameset，当前用户的系统登录信息失效后,如果再点击左边的菜单，在右边的显示登录页面，而正确的应该是整个页面返回到登录页。
	// 解决方案：在登录页面中使用js脚本判断，是否当前页面在框架内，即当前页面的窗口是否是顶级窗口，如果是子窗口，则刷新父窗口会跳到登录页面
	if (window != window.parent) {
		window.parent.location.reload(true);
	}
</script>

<style type="text/css">
html { overflow-y: hidden;  }

.password{
      background-color:#f1f3f6;
	  border:1px solid #f1f3f6;
	  font-color:#ccc;
}

#Layer1 {
	position:absolute;
	left:224px;
	top:479px;
	padding-top:5px;
	width:99px;
	height:21px;
	background-color:#fff;
	z-index:1;
}
.password1 {      
	 background-color:#f1f3f6;
	  border:1px solid #f1f3f6;
	  font-color:#ccc;
}

.youbian input{ border:0px none; background-color:transparent; color:#555;padding-left:10px;font-size:16px;width:100%;overflow: hidden;}
</style>
</head>

<body scroll="no">
<s:form name="form1" namespace="/system/login" action="login">
<div id="lo_tf">
<div class="outside">
    <div class="head">
      <table width="1000" height="60" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="840" align="left"><img src="${basePath }/images/login/form_03.png"   width="332" height="47"/></td>
          <td align="center">&nbsp;&nbsp;<a href="#"></a></td>
        </tr>
      </table>
    </div>
    <div class="main2">
	   <div class="content">  
	   <div class="youbian">
	    <table width="251" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="12">&nbsp;</td>
          </tr>
          <tr>
           <td height="45" align="left"></td>
          </tr>
          <tr>
          	<td height="13">&nbsp;
            	<span><div height=20 valign="middle" style="padding-left: 18px"><font color="red" id="errMsg"><s:property value="loginResult"/></font></div></span>
            </td>
          </tr>
          <tr>
            <td height="40">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    	<td height="32" align="left"><span style="color:#767676;font-size:14px;">帐号:</span></td>
  </tr>
</table>
			<table width="100%" height="39" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td background="${basePath }/images/login/shuru_03.png" width=""><table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td align="left">
                    <s:textfield id="account" name="user.account" cssClass="password1" cssStyle="color: #000000" size="31" value="admin"/>
                    </td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
		    <tr>
            <td height="10"><table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="32" align="left"><span style="color:#767676;font-size:14px;">密码:</span></td>
  </tr>
</table></td>
          </tr>
          <tr>
            <td height="40"><table width="100%" height="39" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td background="${basePath }/images/login/shuru_03.png"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td align="left">
                    	<s:password id="password" name="user.password" cssClass="password"  cssStyle="color: #000000" size="31" value="admin"/>
                    </td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
          
		   <tr>
            <td height="10">&nbsp;</td>
          </tr>
          <tr>
            <td height="40"><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
              	<!-- 登录动作 -->
                <td align="right"><a href="#" onclick="javascript:loginAction();"><img src="${basePath }/images/login/form_15.png" width="95" height="37"/></a></td>
                <td width="18">&nbsp;</td>
                <!-- 重置动作 -->
                <td align="left"><img src="${basePath }/images/login/form_17.png" width="95" height="37" onclick="setClean()"/></td>
              </tr>
            </table></td>
          </tr>
        </table>
	    
	  </div>   
       </div>
   </div>
	<div class="foot">版权所有&nbsp;|&nbsp;国税局&nbsp;&nbsp;2016年</div>
</div>
</div>
</s:form>
</body>
</html>