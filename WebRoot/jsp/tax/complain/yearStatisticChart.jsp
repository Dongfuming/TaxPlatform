<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/header.jsp" %>

<%
	// 获取当前年份
	Calendar cal = Calendar.getInstance();
	int currentYear = cal.get(Calendar.YEAR);
	request.setAttribute("currentYear", currentYear);
	
	// 页面年份下拉框，最近5年
	List yearList = new ArrayList();
	for(int i = currentYear; i > currentYear-5 ; i--){
		yearList.add(i);
	}
	request.setAttribute("yearList", yearList);
%>


<!DOCTYPE HTML>
<html>
  <head>
    <title>年度投诉统计图</title>
     <script type="text/javascript" src="${basePath }/js/fusioncharts/fusioncharts.js"></script>
  	<script type="text/javascript" src="${basePath }/js/fusioncharts/fusioncharts.charts.js"></script>
  	<script type="text/javascript" src="${basePath }/js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
  
    <script type="text/javascript">
    // 加载完dom元素后执行
    $(document).ready(doAnnualStatistic());
    
    function doAnnualStatistic() {
    	// 1、获取年份
  	  var year = $("#year option:selected").val();
  	  if(year == "" || year == undefined) {
  		  year = "${currentYear}";// 默认当前年份
  	  }
  	  // 2、根据年份统计
  	  $.ajax({
  		  url:"${basePath }/tax/complain/getYearStatisticData.action",
  		  data:{"year":year},
  		  type: "post",
  		  dataType:"json",
  		  success: function(data){
  			  if (data != null && data != "" && data != undefined) {
  				  var revenueChart = new FusionCharts({
  				        "type": "line",
  				        "renderAt": "chartContainer",
  				        "width": "600",
  				        "height": "400",
  				        "dataFormat": "json",
  				        "dataSource":  {
  				          "chart": {
  				            "caption": year + " 年度投诉数统计图",
  				            "xAxisName": "月  份",
  				            "yAxisName": "投  诉  数",
  				            "theme": "fint"
  				         },
  				         "data": data.chartData
  				      }
  				  });
  				revenueChart.render();
  			  } else {
  				  alert("统计投诉数失败！");
  			  }
  		  },
  		  error: function(){alert("统计投诉数失败！");}
  	  });
    }
    
    </script>
  </head>
  
  <body>
  	<br>
    <div style="text-align:center;width:100%;"><s:select id="year" list="#request.yearList" onchange="doAnnualStatistic()"></s:select></div>
    <br>
    <div id="chartContainer" style="text-align:center;width:100%;"></div>
  </body>
</html>
