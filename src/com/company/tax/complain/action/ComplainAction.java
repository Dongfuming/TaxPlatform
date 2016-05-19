package com.company.tax.complain.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import com.company.core.action.BaseAction;
import com.company.core.util.QueryHelper;
import com.company.tax.complain.entity.Complain;
import com.company.tax.complain.entity.ComplainReply;
import com.company.tax.complain.service.ComplainService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Dongfuming
 * @date 2016-5-17 下午2:46:59
 */
@SuppressWarnings("serial")
public class ComplainAction extends BaseAction {
	
	@Resource
	private ComplainService complainService;
	private Complain complain;
	private ComplainReply reply; // 受理--回复
	private String startTime; // 搜索-开始时间
	private String endTime; // 搜索-结束时间
	private Map<String, Object> statisticMap; // 查看统计时ajax传json过去
	
	/** 
	 * 1. 若complain或startTime或endTime不为空，说明是带条件的搜索功能
	 * 2. 若这些属性都为空，则是查询全部
	 * 3. 排序设为：状态升序，投诉时间升序
	 */
	public String listComplain() throws Exception {
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		if(StringUtils.isNotBlank(startTime)) {
			startTime = URLDecoder.decode(startTime, "utf-8");
			queryHelper.addCondition("c.compTime >= ?", DateUtils.parseDate(startTime+":00", "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtils.isNotBlank(endTime)) {
			endTime = URLDecoder.decode(endTime, "utf-8");
			queryHelper.addCondition("c.compTime <= ?", DateUtils.parseDate(endTime+":59", "yyyy-MM-dd HH:mm:ss"));
		}
		if (complain != null) { 
			if(StringUtils.isNotBlank(complain.getCompTitle())){
				queryHelper.addCondition("c.compTitle like ?", "%" + complain.getCompTitle() + "%");
			}
			if (StringUtils.isNotBlank(complain.getState())) {
				queryHelper.addCondition("c.state=?", complain.getState());
			}
		}
		queryHelper.addOrderByProperty("c.state", QueryHelper.ORDER_BY_ASC);
		queryHelper.addOrderByProperty("c.compTime", QueryHelper.ORDER_BY_ASC);
		pageResult = complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
		transferDataOfComplainStateMap();
		return "listComplain";
	}
	
	public String toDealComplainPage() {
		complain = complainService.findObjectById(complain.getCompId());
		transferDataOfComplainStateMap();
		return "toDealComplainPage";
	}
	
	/** 
	 * 1.更新投诉状态  
	 * 2.保存回复 
	 */
	public String dealComplain() {
		complain = complainService.findObjectById(complain.getCompId());
		if ( ! Complain.COMPLAIN_STATE_DONE.equals(complain.getState())) {
			complain.setState(Complain.COMPLAIN_STATE_DONE);
		}
		reply.setComplain(complain);
		reply.setReplyTime(new Timestamp(new Date().getTime()));
		complain.getComplainReplySet().add(reply);
		complainService.update(complain);
		
		return "dealComplainSuccess";
	}
	
	public String toYearStatisticChartPage() { 
		return "toYearStatisticChartPage";
	}
	
	public String getYearStatisticData() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int year = Integer.valueOf(request.getParameter("year")).intValue();
		statisticMap = new HashMap<String, Object>();
		statisticMap.put("msg", "success");
		statisticMap.put("chartData", complainService.getYearStatisticDataByYear(year));
		return "yearStatisticData";
	}
	
	private void transferDataOfComplainStateMap() {
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
	}
	
	public void setComplain(Complain complain) {
		this.complain = complain;
	}
	public Complain getComplain() {
		return complain;
	}
	public void setReply(ComplainReply reply) {
		this.reply = reply;
	}
	public ComplainReply getReply() {
		return reply;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setStatisticMap(Map<String, Object> statisticMap) {
		this.statisticMap = statisticMap;
	}
	public Map<String, Object> getStatisticMap() {
		return statisticMap;
	}
}
