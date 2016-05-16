package com.company.tax.info.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.core.action.BaseAction;
import com.company.tax.info.entity.Info;
import com.company.tax.info.service.InfoService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Dongfuming
 * @date 2016-5-15 下午2:33:16
 */
@SuppressWarnings("serial")
public class InfoAction extends BaseAction {
	
	@Resource
	private InfoService infoService;
	private List<Info> infoList;
	private Info info;
	
	public String listInfo() {
		transferDataOfInfoTypeMap();
		infoList = infoService.findInfos();
		return "listInfo";
	}
	
	public String toAddInfoPage() {
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		transferDataOfInfoTypeMap();
		
		return "toAddInfoPage";
	}
	
	public String addInfo() {
		info.setCreateTime(new Timestamp(new Date().getTime()));
		infoService.save(info);
		return "addInfoSuccess";
	}
	
	public String toEditInfoPage() {
		info = infoService.findInfoById(info.getInfoId());
		transferDataOfInfoTypeMap();
		return "toEditInfoPage";
	}
	
	public String editInfo() {
		infoService.update(info);
		return "editInfoSuccess";
	}
	
	public String deleteInfo() {
		infoService.delete(info.getInfoId());
		return "deleteInfoSuccess";
	}
	
	public String deleteSelectedInfo() {
		for (String infoId : selectedRow) {
			infoService.delete(infoId);
		}
		return "deleteSelectedInfoSuccess";
	}
	
	public void changeInfoState() { // ajax更新状态
		try {
			Info theInfo= infoService.findInfoById(info.getInfoId());
			theInfo.setState(info.getState());
			infoService.update(theInfo);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write("更新状态成功".getBytes("utf-8"));
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void transferDataOfInfoTypeMap() {
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
	}
	
	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}
	public List<Info> getInfoList() {
		return infoList;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public Info getInfo() {
		return info;
	}
}
