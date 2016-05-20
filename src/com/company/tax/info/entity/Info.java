package com.company.tax.info.entity;

import java.sql.Timestamp;

/**
 * 信息实体
 * @author Dongfuming
 * @date 2016-5-15 下午1:58:18
 */
public class Info {

	private String infoId;
	private String type; // 分类
	private String source; // 来源
	private String title; // 标题
	private String content; // 内容
	private String memo; // 备注
	private String creator; // 创建人
	private Timestamp createTime; // 创建时间 
	private String state; // 状态
	
	public Info() { }
	
	public Info(String infoId, String type, String source, String title, String content,
			String memo, String creator, Timestamp createTime, String state) {
		this.infoId = infoId;
		this.type = type;
		this.source = source;
		this.title = title;
		this.content = content;
		this.memo = memo;
		this.creator = creator;
		this.createTime = createTime;
		this.state = state;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
