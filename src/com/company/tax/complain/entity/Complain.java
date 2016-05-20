package com.company.tax.complain.entity;

import java.sql.Timestamp;
import java.util.Set;

/**
 * 投诉实体，与投诉回复一对多关系
 * @author Dongfuming
 * @date 2016-5-17 下午2:00:56
 */
public class Complain {

	private String compId;
	private String compCompany; // 投诉人单位
	private String compName; // 投诉人姓名
	private String compMobile; // 投诉人手机
	private Boolean isNm; // 是否匿名
	private Timestamp compTime; // 投诉时间
	private String compTitle; // 投诉标题
	private String toCompName; // 被投诉人姓名
	private String toCompDept; // 被投诉人部门
	private String compContent; // 投诉内容
	private String state; // 状态
	private Set<ComplainReply> complainReplySet; // 一个投诉，多个回复
	
	public Complain() { }

	public Complain(String compTitle) {
		this.compTitle = compTitle;
	}

	public Complain(String compCompany, String compName, String compMobile, Boolean isNm, Timestamp compTime, 
			String compTitle, String toCompName, String toCompDept, String compContent, String state,
			Set<ComplainReply> complainReplySet) {
		this.compCompany = compCompany;
		this.compName = compName;
		this.compMobile = compMobile;
		this.isNm = isNm;
		this.compTime = compTime;
		this.compTitle = compTitle;
		this.toCompName = toCompName;
		this.toCompDept = toCompDept;
		this.compContent = compContent;
		this.state = state;
		this.complainReplySet = complainReplySet;
	}

	public String getCompId() {
		return this.compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getCompCompany() {
		return this.compCompany;
	}
	public void setCompCompany(String compCompany) {
		this.compCompany = compCompany;
	}
	public String getCompName() {
		return this.compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompMobile() {
		return this.compMobile;
	}
	public void setCompMobile(String compMobile) {
		this.compMobile = compMobile;
	}
	public Boolean getIsNm() {
		return this.isNm;
	}
	public void setIsNm(Boolean isNm) {
		this.isNm = isNm;
	}
	public Timestamp getCompTime() {
		return this.compTime;
	}
	public void setCompTime(Timestamp compTime) {
		this.compTime = compTime;
	}
	public String getCompTitle() {
		return this.compTitle;
	}
	public void setCompTitle(String compTitle) {
		this.compTitle = compTitle;
	}
	public String getToCompName() {
		return this.toCompName;
	}
	public void setToCompName(String toCompName) {
		this.toCompName = toCompName;
	}
	public String getToCompDept() {
		return this.toCompDept;
	}
	public void setToCompDept(String toCompDept) {
		this.toCompDept = toCompDept;
	}
	public String getCompContent() {
		return this.compContent;
	}
	public void setCompContent(String compContent) {
		this.compContent = compContent;
	}
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Set<ComplainReply> getComplainReplySet() {
		return complainReplySet;
	}
	public void setComplainReplySet(Set<ComplainReply> complainReplySet) {
		this.complainReplySet = complainReplySet;
	}

	@Override
	public String toString() {
		return "Complain [compId=" + compId + ", compCompany=" + compCompany
				+ ", compName=" + compName + ", compMobile=" + compMobile
				+ ", compTitle=" + compTitle + ", toCompName=" + toCompName
				+ ", toCompDept=" + toCompDept + ", compContent=" + compContent
				+ "]";
	}
}
