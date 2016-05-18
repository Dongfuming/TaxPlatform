package com.company.tax.complain.entity;

import java.sql.Timestamp;

/**
 * 投诉回复 实体
 * @author Dongfuming
 * @date 2016-5-17 下午2:07:11
 */
public class ComplainReply {

	private String replyId;
	private Complain complain; // 一个回复，一个投诉
	private String replyer; // 回复人
	private String replyDept; // 回复部门
	private Timestamp replyTime; // 回复时间
	private String replyContent; // 回复内容

	public ComplainReply() { }

	public ComplainReply(Complain complain) {
		this.complain = complain;
	}

	public ComplainReply(Complain complain, String replyer, String replyDept, Timestamp replyTime, String replyContent) {
		this.complain = complain;
		this.replyer = replyer;
		this.replyDept = replyDept;
		this.replyTime = replyTime;
		this.replyContent = replyContent;
	}

	public String getReplyId() {
		return this.replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public Complain getComplain() {
		return this.complain;
	}
	public void setComplain(Complain complain) {
		this.complain = complain;
	}
	public String getReplyer() {
		return this.replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getReplyDept() {
		return this.replyDept;
	}
	public void setReplyDept(String replyDept) {
		this.replyDept = replyDept;
	}
	public Timestamp getReplyTime() {
		return this.replyTime;
	}
	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyContent() {
		return this.replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
}
