<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/header.jsp" %> 

<html>
<head>
    <link href="${basePath}/css/nsfw/css.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}/css/nsfw/style.css" rel="stylesheet" type="text/css"/>
    
    <script type="text/javascript">
        //隐藏菜单
        $(document).ready(function () {
            $("dt a").click(function () {
                var cur = $(this);
                cur.parent().next().toggle(300);
                var cur_dl = cur.parent().parent();
                doRemoveCurClass();
                $(cur_dl).addClass("curr");
            });

            $("dd a").each(function () {
                $(this).bind("click", function () {
                    doRemoveCurClass();
                    $(this).addClass("cur");
                });
            });
           
        });

        function doRemoveCurClass() {
            $("dl").each(function () {
                $(this).removeClass("curr");
                $("dd a").each(function () {
                    $(this).removeClass("cur");
                });
            });
        }

        function closeOtherDD(id) {
            $("dd").each(function () {
                if ($(this).attr("id") != id + "dd") {
                    $(this).hide(300);
                }
            });
        }
    </script>
    <style>
        * {
            scrollbar-face-color: #dee3e7; /*立体滚动条的颜色（包括箭头部分的背景色）*/
            scrollbar-highlight-color: #ffffff; /*滚动条的高亮颜色（左阴影？）*/
            scrollbar-shadow-color: #dee3e7; /*立体滚动条阴影的颜色*/
            scrollbar-3dlight-color: #eceaea; /*立体滚动条亮边的颜色*/
            scrollbar-arrow-color: #006699; /*三角箭头的颜色*/
            scrollbar-track-color: #efefef; /*立体滚动条背景颜色*/
            scrollbar-darkshadow-color: #eceaea; /*滚动条的基色*/
        }
    </style>
</head>

<body>
<div class="xzfw" style="width: 210px;">
    <div class="xzfw_nav" style="width:214px;min-height:500px;">
        <div class="nBox" style="width:214px;">
            <div class="x_top" style="width:214px;"></div>
            <div class="sm">
				<dl class="">
                    <dt><a class="yh" href="${basePath }/tax/role/listRole.action" target="mainFrame"><b></b>角色管理<s class="down"></s>
                    </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="yh" href="${basePath }/tax/user/listUser.action" target="mainFrame"><b></b>用户管理<s class="down"></s>
                    </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="xxfb" href="${basePath }/tax/info/listInfo.action" target="mainFrame"><b></b>信息发布管理<s class="down"></s> </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="tssl" href="${basePath }/tax/complain/listComplain.action" target="mainFrame"><b></b>投诉受理管理<s class="down"></s> </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="nszx" href="#" target="mainFrame"><b></b>纳税咨询管理<s class="down"></s> </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="fwdc" href="#" target="mainFrame"><b></b>服务调查管理<s class="down"></s> </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="ygz" href="#" target="mainFrame"><b></b>易告知管理<s class="down"></s> </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="fwyy" style="cursor: pointer;"><b></b>服务预约管理<s class="down"></s> </a></dt>
                    <dd id="fwyygl" style="display:none;">
                        <a class="" href="#" target="mainFrame"><b></b>预约服务</a>
                        <a class="" href="#" target="mainFrame"><b></b>预约事项</a>
                    </dd>
                </dl>
            </div>
        </div>
    </div>
</div>
</body>
</html>
