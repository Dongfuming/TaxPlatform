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
                        <s:if test="roleList != null && roleList.size > 0">
                       		<s:iterator value="roleList" status="st">
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
                           </s:if>
                           <s:else>
                           		<tr>
	  	 							<td align="center" colspan="5">暂时还没有数据</td>
	  	 						</tr>
                           </s:else>
                    </table>
                </div>
            </div>
			<div class="c_pate" style="margin-top: 5px;">
		<table width="100%" class="pageDown" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="right">
                 	总共1条记录，当前第 1 页，共 1 页 &nbsp;&nbsp;
                            <a href="#">上一页</a>&nbsp;&nbsp;<a href="#">下一页</a>
					到&nbsp;<input type="text" style="width: 30px;" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
					max="" value="1" /> &nbsp;&nbsp;
			    </td>
			</tr>
		</table>	
        </div>
        </div>
    </div>
</form>

</body>
</html>