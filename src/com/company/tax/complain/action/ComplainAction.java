package com.company.tax.complain.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

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
		if (complain != null) { // 搜索
			if(StringUtils.isNotBlank(complain.getCompTitle())){
				//complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
				queryHelper.addCondition("c.compTitle like ?", "%" + complain.getCompTitle() + "%");
			}
			if (StringUtils.isNotBlank(complain.getState())) {
				queryHelper.addCondition("c.state=?", complain.getState());
			}
		}
		// 状态升序排序，投诉时间升序排序
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
	
	public String dealComplain() {
		// 1.更新投诉状态  2.保存回复
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
	
	public String toYearStatisticChartPage() { // 统计
		return "toYearStatisticChartPage";
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
}
