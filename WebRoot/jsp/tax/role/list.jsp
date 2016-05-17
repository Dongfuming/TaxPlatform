<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/common/header.jsp" %> 

<html>
<head>
    <title>角色管理</title>
    
    <script type="text/javascript">
  	//全选、全反选
	function doSelectAll(){
		// jquery 1.6 前
		//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
		//prop jquery 1.6+建议使用
		$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
	}
  	//新增
  	function doAdd(){
  		document.forms[0].action = "${basePath}/tax/role/toAddRolePage.action";
  		document.forms[0].submit();
  	}
  	//编辑
  	function doEdit(id){
  		document.forms[0].action = "${basePath}/tax/role/toEditRolePage.action?role.id=" + id;
  		document.forms[0].submit();
  	}
  	//删除
  	function doDelete(id){
  		document.forms[0].action = "${basePath}/tax/role/deleteRole.action?role.id=" + id;
  		document.forms[0].submit();
  	}
  	//批量删除
  	function doDeleteAll(){
  		document.forms[0].action = "${basePath}/tax/role/deleteSelectedRole.action";
  		document.forms[0].submit();
  	}

	var list_url = "${basePath}/tax/role/listRole.action"

	//搜索
	function doSearch() {
  		//重置页号
  		$("#pageNo").val(1);
	  	document.forms[0].action = list_url;
	  	document.forms[0].submit();
	 }
	
    </script>
</head>

<body class="rightBody">
<s:property value="xxx"/>

<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>角色管理 </strong></div> </div>
                <div class="search_art">
                    <li>
                        角色名称：<s:textfield name="role.name" cssClass="s_text" id="roleName"  cssStyle="width:160px;"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td width="120" align="center">角色名称</td>
                            <td align="center">权限</td>
                            <td width="80" align="center">状态</td>
                            <td width="120" align="center">操作</td>
                        </tr>
                       		<s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='id'/>"/></td>
                                <td align="center"><s:property value="name"/></td>
                                <td align="center">
                                	<s:iterator value="rolePrivilegeSet">
                                		<s:property value="#privilegeMap[compositeRolePrivilege.privilege]"/>
                                	</s:iterator>	
                                </td>
                                <td align="center"><s:property value="state==1?'有效':'无效'"/></td>
                                <td align="center">
                                										<!-- 此id是Role里的id属性 -->
                                    <a href="javascript:doEdit('<s:property value='id'/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value='id'/>')">删除</a>
                                </td>
                            </tr>
                           </s:iterator>
                    </table>
                </div>
            </div>
			<jsp:include page="/jsp/common/pageNavigator.jsp"></jsp:include>
		</div>
    </div>
</form>

</body>
</html>